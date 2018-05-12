<template>
  <b-container fluid>
    <navigation-menu :disc="true"></navigation-menu>
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
    <b-table show-empty
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
      </template>
      <template slot="row-details" slot-scope="row">
        <b-card>
          <span>Список преподавателей</span>
          <ul>
            <li v-for="(value, key) in row.item.teachers" :key="key">{{ value.name }}</li>
          </ul>
        </b-card>
      </template>
    </b-table>

  </b-container>
</template>

<script>
  import {AXIOS} from './http-common'
  import NavigationMenu from './NavigationMenu'

  export default {
    name: 'Teacher',
    components: {NavigationMenu},
    data() {
      return {
        fields: [
          {key: 'name', label: 'Название', sortable: true},
          {key: 'description', label: 'Описание', sortable: true},
          {key: 'actions', label: 'Действия'}
        ],
        currentPage: 1,
        perPage: 10,
        totalRows: 0,
        pageOptions: [5, 10, 15],
        sortBy: null,
        sortDesc: false,
        filter: null,
        modalInfo: {title: '', content: ''}
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
        this.modalInfo.title = `Row index: ${index}`
        this.modalInfo.content = JSON.stringify(item, null, 2)
        this.$root.$emit('bv::show::modal', 'modalInfo', button)
      },
      resetModal() {
        this.modalInfo.title = ''
        this.modalInfo.content = ''
      },
      onFiltered(filteredItems) {
        // Trigger pagination to update the number of buttons/pages due to filtering
        this.totalRows = filteredItems.length
        this.currentPage = 1
      },
      getItems() {
        return AXIOS.get('/disciplines')
          .then(result => result.data)
          .catch(console.log)
      }
    }
  }
</script>
