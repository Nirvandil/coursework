<template>
  <b-container fluid>
    <navigation-menu :tt="true"></navigation-menu>
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
    <b-row>
      <b-col md="11" class="col-11"></b-col>
      <b-col md="1" class="my-1 col-1">
        <b-button variant="primary" id="addButton" @click.stop="showAddForm($event.target)">Добавить</b-button>
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
        <!-- We use @click.stop here to prevent a 'row-clicked' event from also happening -->
        <b-button size="sm" variant="danger" @click.stop="info(row.item, row.index, $event.target)" class="mr-1">
          Удалить
        </b-button>
      </template>
    </b-table>

    <!-- Info modal -->
    <b-modal id="modalInfo" @hide="resetModal" :title="modalInfo.title" @ok="deletePair">
      <pre>{{ modalInfo.content }}</pre>
    </b-modal>

    <b-modal id="addPair" title="Добавить пару">
      <b-form @submit="createPair" @reset="onReset">
        <b-form-group id="group"
                      label="Имя группы"
                      label-for="group">
          <b-form-select id="exampleInput1"
                         :options="groups"
                         required
                         v-model="form.groupName">
          </b-form-select>
        </b-form-group>
        <b-form-group id="teacher"
                      label="Преподаватель"
                      label-for="teacher">
          <b-form-select id="exampleInput2"
                         :options="teachers"
                         required
                         v-model="form.teacherId"
                         @change="filterDisciplines">
          </b-form-select>
        </b-form-group>
        <b-form-group id="discipline"
                      label="Дисциплина"
                      label-for="discipline">
          <b-form-select id="exampleInput3"
                         :options="disciplines"
                         required
                         v-model="form.discipline">
          </b-form-select>
        </b-form-group>
        <b-form-group id="type"
                      label="Тип занятия"
                      label-for="discipline">
          <b-form-select id="exampleInput7"
                         :options="[{text: 'Экзамен', value: 'EXAM'}, {text: 'Обычное', value: 'REGULAR'}]"
                         required
                         v-model="form.type">
          </b-form-select>
        </b-form-group>
        <b-form-group id="auditory"
                      label="Аудитория"
                      label-for="auditory">
          <b-form-select id="exampleInput4"
                         :options="auditories"
                         required
                         v-model="form.auditoryNumber">
          </b-form-select>
        </b-form-group>
        <b-form-group id="pair"
                      label="Номер пары"
                      label-for="pair">
          <b-form-select id="exampleInput5"
                         :options="[1, 2, 3, 4, 5, 6, 7]"
                         required
                         v-model="form.pairNumber">
          </b-form-select>
        </b-form-group>
        <b-form-group id="date"
                      label="День недели"
                      label-for="date">
          <b-form-input id="exampleInput6"
                        type="date"
                        required
                        v-model="form.date">
          </b-form-input>
        </b-form-group>
        <b-button type="submit" variant="primary">Создать</b-button>
        <b-button type="reset" variant="danger">Очистить</b-button>
      </b-form>
      <div slot="modal-footer" class="w-100">
        <b-btn size="sm" class="float-right" variant="primary" @click="closeAddForm">
          Закрыть
        </b-btn>
      </div>
    </b-modal>
  </b-container>
</template>

<script>
  import {AXIOS} from './http-common'
  import NavigationMenu from './NavigationMenu'

  export default {
    name: 'timetable',
    components: {NavigationMenu},
    data() {
      return {
        fields: [
          {key: 'pairNumber', label: 'Номер пары', sortable: false},
          {key: 'groupName', label: 'Имя группы', sortable: true},
          {key: 'discipline', label: 'Предмет', sortable: true},
          {key: 'teacher', label: 'Преподаватель', sortable: true},
          {key: 'auditoryNumber', label: 'Номер аудитории', sortable: true},
          {key: 'date', label: 'Дата', sortable: true},
          {key: 'actions', label: 'Действия'}
        ],
        currentPage: 1,
        perPage: 10,
        totalRows: 0,
        pageOptions: [5, 10, 15],
        sortBy: 'date',
        sortDesc: false,
        filter: null,
        modalInfo: {title: '', content: ''},
        toDelete: null,
        form: {
          teacherId: null,
          groupName: null,
          discipline: null,
          auditoryNumber: null,
          date: null,
          pairNumber: null,
          type: 'REGULAR'
        },
        teachers: [],
        groups: [],
        disciplines: [],
        auditories: [],
        timeTableId: null
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
            this.timeTableId = result.data[0].id
            this.totalRows = items.length
            return items.map(item => {
              const pairDate = new Date(item.date).setHours(0, 0, 0, 0)
              if (today === pairDate) {
                item._rowVariant = 'info'
              }
              if (item.type === 'EXAM') {
                item._rowVariant = 'warning'
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
      },
      createPair(evt) {
        evt.preventDefault()
        this.form.timeTableId = this.timeTableId
        AXIOS.post('/work-pairs', this.form)
          .then(_ => this.$refs.table.refresh())
          .catch(console.log)
      },
      onReset(evt) {
        evt.preventDefault()
        /* Reset our form values */
        this.form.date = null
        this.form.pairNumber = ''
        this.form.auditoryNumber = null
        this.form.discipline = null
        this.form.groupName = ''
        this.form.teacherId = ''
      },
      showAddForm(button) {
        this.populateData()
        this.$root.$emit('bv::show::modal', 'addPair', button)
      },
      closeAddForm(button) {
        this.$root.$emit('bv::hide::modal', 'addPair', button)
      },
      filterDisciplines(value) {
        this.disciplines = this.disciplines.filter(disc => {
          for (let i = 0; i < disc.teachers.length; i++) {
            if (disc.teachers[i].id === value) return true
          }
          return false
        })
      },
      populateData() {
        AXIOS.get('/teachers')
          .then(res => (this.teachers = res.data.map(item => {
            return {
              text: item.name,
              value: item.id
            }
          })))
          .catch(console.log)
        AXIOS.get('/disciplines')
          .then(res => (this.disciplines = res.data.map(item => {
            return {
              text: item.name,
              value: item.name,
              teachers: item.teachers
            }
          })))
          .catch(console.log)
        AXIOS.get('/auditories')
          .then(res => (this.auditories = res.data.map(item => item.number)))
          .catch(console.log)
        AXIOS.get('/groups')
          .then(res => (this.groups = res.data.map(item => item.name)))
          .catch(console.log)
      }
    }
  }
</script>
<style scoped>

</style>
