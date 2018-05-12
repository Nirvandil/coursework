<template>
  <div class="hello">
    <h3>{{ msg }}</h3>
    <h4>Исходный код этого проекта можно найти здесь: </h4>
    <ul>
      <li>
        <a href="https://github.com/Nirvandil/coursework" target="_blank">github.com/Nirvandil/coursework</a>
      </li>
    </ul>
    <h4>Этот сайт содержит кое-что ещё :)</h4>
    <b-list-group>
      <b-list-group-item>
        Можно посмотреть и отредактировать расписание (сегодняшние пары подсвечиваются):
        <a href="/#/timetable" target="_blank">/timetable</a>
      </b-list-group-item>
      <b-list-group-item>
        Можно просмотреть список студентов:
        <a href="/#/students/" target="_blank">/students</a>
      </b-list-group-item>
      <b-list-group-item>
        Можно просмотреть список преподавателей:
        <a href="/#/teachers/" target="_blank">/teachers</a>
      </b-list-group-item>
      <b-list-group-item>
        Можно просмотреть список аудиторий:
        <a href="/#/auditories/" target="_blank">/auditories</a>
      </b-list-group-item>
    </b-list-group>
    <br>
    <br>
    <p>Этот сайт мог бы быть несколько функциональнее, но это просто proof of concept, поэтому прошу понять и
      простить</p>
  </div>
</template>

<script>
  export default {
    name: 'hello',

    data() {
      return {
        msg: 'Добро пожаловать в курсовую работу по базам данных студента Сухарева Владимира группы ЗИ16.'
      }
    }
  }

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
