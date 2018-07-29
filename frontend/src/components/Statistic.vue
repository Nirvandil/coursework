<template>
  <b-container fluid>
    <b-row>
      <b-col>
        <b-form @submit="getStatistic" @reset="onReset">
          <b-form-group id="teachers"
                        label="Выберите преподавателя"
                        label-for="teachers">
            <b-form-select id="exampleInput2"
                           :options="teachers"
                           required
                           v-model="targetTeacherId">
            </b-form-select>
          </b-form-group>
          <b-form-group id="from"
                        label="Дата с"
                        label-for="from">
            <b-form-input id="exampleInput3"
                          type="date"
                          required
                          v-model="from">
            </b-form-input>
          </b-form-group>
          <b-form-group id="to"
                        label="Дата по"
                        label-for="to">
            <b-form-input id="exampleInput4"
                          type="date"
                          required
                          v-model="to">
            </b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">Получить</b-button>
          <b-button type="reset" variant="danger">Очистить</b-button>
        </b-form>
      </b-col>
      <b-col>
        <div>
          <br> <br>
          Количество отработанных часов за указанный период: <strong> {{result}} </strong>
        </div>
      </b-col>
    </b-row>
    <br> <br>
    <b-row>
      <b-col>
        <b-form @submit="getGroupStatistic" @reset="onResetGroup">
          <b-form-group id="groups"
                        label="Выберите группу"
                        label-for="groups">
            <b-form-select id="exampleInput7"
                           :options="groups"
                           required
                           v-model="targetGroupId">
            </b-form-select>
          </b-form-group>
          <b-form-group id="disciplines"
                        label="Выберите дисциплину"
                        label-for="disciplines">
            <b-form-select id="exampleInput7"
                           :options="disciplines"
                           required
                           v-model="targetDisciplineId">
            </b-form-select>
          </b-form-group>
          <b-form-group id="from"
                        label="Дата с"
                        label-for="from">
            <b-form-input id="exampleInput8"
                          type="date"
                          required
                          v-model="groupFrom">
            </b-form-input>
          </b-form-group>
          <b-form-group id="to"
                        label="Дата по"
                        label-for="to">
            <b-form-input id="exampleInput9"
                          type="date"
                          required
                          v-model="groupTo">
            </b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">Получить</b-button>
          <b-button type="reset" variant="danger">Очистить</b-button>
        </b-form>
      </b-col>
      <b-col>
        <div>
          <br> <br>
          Количество часов предмета у группы за указанный период: <strong>{{groupResult}}</strong>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
  import {AXIOS} from './http-common'

  export default {
    name: 'Statistic',
    data() {
      return {
        from: '',
        to: '',
        teachers: [],
        targetTeacherId: null,
        disciplines: [],
        groups: [],
        targetDisciplineId: null,
        targetGroupId: null,
        groupFrom: null,
        groupTo: null,
        result: 0,
        groupResult: 0
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.teachers = []
        AXIOS.get('/teachers')
          .then(result => {
            result.data.map(teacher => {
              this.teachers.push({
                text: teacher.name,
                value: teacher.id
              })
            })
          })
          .catch(alert)
        AXIOS.get('/groups')
          .then(result => {
            result.data.map(group => {
              this.groups.push({
                text: group.name,
                value: group.id
              })
            })
          })
          .catch(alert)
        AXIOS.get('/disciplines')
          .then(result => {
            result.data.map(discipline => {
              this.disciplines.push({
                text: discipline.name,
                value: discipline.id
              })
            })
          })
          .catch(alert)
      },
      getStatistic() {
        AXIOS.get(`/statistic/teachers/${this.targetTeacherId}`, {
          params: {
            from: this.from,
            to: this.to
          }
        })
          .then(res => (this.result = res.data))
          .catch(console.log)
      },
      getGroupStatistic() {
        AXIOS.get(`/statistic/groups/${this.targetGroupId}`, {
          params: {
            disciplineId: this.targetDisciplineId,
            from: this.groupFrom,
            to: this.groupTo
          }
        })
          .then(res => (this.groupResult = res.data))
          .catch(console.log)
      },
      onReset() {
        this.targetTeacherId = null
        this.from = null
        this.to = null
        this.result = null
      },
      onResetGroup() {
        this.targetDisciplineId = null
        this.targetGroupId = null
        this.groupTo = null
        this.groupFrom = null
        this.groupResult = 0
      }
    }
  }
</script>

<style scoped>

</style>
