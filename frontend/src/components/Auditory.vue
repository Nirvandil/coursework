<template>
  <b-container fluid>
    <navigation-menu :aud="true"></navigation-menu>
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
    <b-modal id="modalInfo" @hide="resetModal" :title="modalInfo.title" @ok="deleteAuditory">
      <pre>{{ modalInfo.content }}</pre>
    </b-modal>

    <b-modal id="addAuditory" title="Добавить аудиторию">
      <b-form @submit="createAuditory" @reset="onReset">
        <b-form-group id="auditory"
                      label="Номер аудитории"
                      label-for="group">
          <b-form-input id="exampleInput1"
                        required
                        v-model="form.number">
          </b-form-input>
        </b-form-group>
        <b-form-group id="description"
                      label="Описание (опционально)"
                      label-for="teacher">
          <b-form-input id="exampleInput2"
                        v-model="form.description">
          </b-form-input>
        </b-form-group>
        <b-button type="submit" variant="primary">Создать</b-button>
        <b-button type="reset" variant="danger">Очистить</b-button>
      </b-form>
      <div slot="modal-footer" class="w-100">
        <b-btn size="sm" class="float-right" variant="primary" @click="closeAddForm">
          Отмена
        </b-btn>
      </div>
    </b-modal>
    <b-alert :show="showError" variant="danger" dismissible @dismissed="showError=false">{{errorMsg}}</b-alert>
  </b-container>
</template>

<script>
  import {AXIOS} from './http-common'
  import NavigationMenu from './NavigationMenu'

  export default {
    name: 'Auditory',
    components: {NavigationMenu},
    data() {
      return {
        fields: [
          {key: 'number', label: 'Номер аудитории', sortable: true},
          {key: 'description', label: 'Описание аудитории', sortable: false},
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
          number: null,
          description: null
        },
        showError: false,
        errorMsg: ''
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
        this.modalInfo.content = `Вы уверены, что хотите удалить ${item.number} аудиторию?`
        this.toDelete = item.number
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
        return AXIOS.get('/auditories')
          .then(result => result.data)
          .catch(console.log)
      },
      deleteAuditory() {
        AXIOS.delete(`/auditories/${this.toDelete}`)
          .then(_ => this.$refs.table.refresh())
          .catch(err => {
            console.log(err)
            this.showError = true
            this.errorMsg = 'Ошибка: ' + err.response.data
          })
      },
      createAuditory(evt) {
        evt.preventDefault()
        AXIOS.post('/auditories', this.form)
          .then(_ => this.$refs.table.refresh())
          .catch(err => {
            console.log(err)
            this.showError = true
            this.errorMsg = 'Ошибка: ' + err.response.data
          })
      },
      onReset(evt) {
        evt.preventDefault()
        /* Reset our form values */
        this.form.description = null
        this.form.number = null
      },
      showAddForm(button) {
        this.$root.$emit('bv::show::modal', 'addAuditory', button)
      },
      closeAddForm(button) {
        this.$root.$emit('bv::hide::modal', 'addAuditory', button)
      }
    }
  }
</script>
<style scoped>

</style>
