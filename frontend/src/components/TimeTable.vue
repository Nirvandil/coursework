<template>
  <b-container fluid>
    <!-- User Interface controls -->
    <b-row>
      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Фильтр" class="mb-0">
          <b-input-group>
            <b-form-input v-model="filter" placeholder="Введите фильтр (возможны регулярные выражения)"></b-form-input>
            <b-input-group-append>
              <b-btn :disabled="!filter" @click="filter = ''">Очистить</b-btn>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>
      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Сортировка" class="mb-0">
          <b-input-group>
            <b-form-select v-model="sortBy" :options="sortOptions">
              <option slot="first" :value="null">-- none --</option>
            </b-form-select>
            <b-form-select :disabled="!sortBy" v-model="sortDesc" slot="append">
              <option :value="false">Asc</option>
              <option :value="true">Desc</option>
            </b-form-select>
          </b-input-group>
        </b-form-group>
      </b-col>
      <b-col md="6" class="my-1">
        <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" class="my-0"></b-pagination>
      </b-col>
      <b-col md="6" class="my-1">
        <b-form-group horizontal label="На странице по" class="mb-0">
          <b-form-select :options="pageOptions" v-model="perPage"></b-form-select>
        </b-form-group>
      </b-col>
    </b-row>

    <!-- Main table element -->
    <b-table ref="table"
             show-empty
             stacked="md"
             :items="getItems"
             :fields="fields"
             :current-page="currentPage"
             :per-page="perPage"
             :filter="filter"
             :sort-by.sync="sortBy"
             :sort-desc.sync="sortDesc"
             no-provider-filtering="true"
             no-provider-sorting="true"
             no-provider-paging="true"
             @filtered="onFiltered"
    >
      <template slot="actions" slot-scope="row">
        <b-button size="sm" @click.stop="row.toggleDetails">
          {{ row.detailsShowing ? 'Скрыть' : 'Показать' }} детали
        </b-button>
        <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
        <b-button size="sm" variant="danger" @click.stop="info(row.item, row.index, $event.target)" class="mr-1">
          Удалить
        </b-button>
      </template>
      <template slot="row-details" slot-scope="row">
        <b-card>
          <ul>
            <li v-for="(value, key) in row.item" :key="key">{{ key }}: {{ value}}</li>
          </ul>
        </b-card>
      </template>
    </b-table>

    <!-- Info modal -->
    <b-modal id="modalInfo" @hide="resetModal" :title="modalInfo.title" @ok="deletePair">
      <pre>{{ modalInfo.content }}</pre>
    </b-modal>

  </b-container>
</template>

<script>
  import {AXIOS} from './http-common'

  export default {
    name: 'timetable',
    data() {
      return {
        fields: [
          {key: 'pairNumber', label: 'Номер пары', sortable: true},
          {key: 'groupName', label: 'Имя группы', sortable: true},
          {key: 'discipline', label: 'Предмет', sortable: true},
          {key: 'teacher', label: 'Преподаватель', sortable: true},
          {key: 'auditoryNumber', label: 'Номер аудитории', sortable: true},
          {key: 'date', label: 'Дата', sortable: true},
          {key: 'actions', label: 'Действия'}
        ],
        currentPage: 1,
        perPage: 5,
        totalRows: 0,
        pageOptions: [5, 10, 15],
        sortBy: null,
        sortDesc: false,
        filter: null,
        modalInfo: {title: '', content: ''},
        toDelete: null
      }
    },
    computed: {
      sortOptions() {
        // Create an options list from our fields
        return this.fields
          .filter(f => f.sortable)
          .map(f => {
            return {text: f.label, value: f.key}
          })
      }
    },
    methods: {
      info(item, index, button) {
        this.modalInfo.title = `Подтвердите действие`
        this.modalInfo.content =
          `Вы уверены, что хотите удалить ${item.pairNumber} пару
        ${item.discipline} группы ${item.groupName}
        за ${item.date}?`
        this.toDelete = item.id
        this.$root.$emit('bv::show::modal', 'modalInfo', button)
      },
      resetModal() {
        this.modalInfo.title = ''
        this.modalInfo.content = ''
        this.toDelete = null
      },
      onFiltered(filteredItems) {
        this.totalRows = filteredItems.length
        this.currentPage = 1
      },
      getItems() {
        const today = new Date().setHours(0, 0, 0, 0)
        return AXIOS.get('/time-table')
          .then(result => {
            const items = result.data[0]['workPairs'] || []
            this.totalRows = items.length
            return items.map(item => {
              const pairDate = new Date(item.date).setHours(0, 0, 0, 0)
              if (today === pairDate) {
                item._rowVariant = 'info'
              }
              return item
            })
          })
          .catch(console.log)
      },
      deletePair() {
        AXIOS.delete(`/work-pairs/${this.toDelete}`)
          .then(_ => this.$refs.table.refresh())
          .catch(console.log)
      }
    }
  }
</script>
