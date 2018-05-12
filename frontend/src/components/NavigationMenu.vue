<template>
  <div>
    <b-nav fill tabs>
      <b-nav-item :active="teach" href="/#/teachers">Преподаватели</b-nav-item>
      <b-nav-item :active="disc" href="/#/disciplines">Предметы</b-nav-item>
      <b-nav-item :active="aud" href="/#/auditories">Аудитории</b-nav-item>
      <b-nav-item :active="stud" href="/#/students">Студенты</b-nav-item>
      <b-nav-item :active="tt" href="/#/timetable">Расписание</b-nav-item>
    </b-nav>
  </div>
</template>

<script>
  export default {
    name: 'NavigationMenu',
    props: ['teach', 'disc', 'stud', 'tt', 'aud']
  }
</script>

<style scoped>

</style>
