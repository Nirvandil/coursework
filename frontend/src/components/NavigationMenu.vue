<template>
  <div>
    <b-nav fill tabs>
      <b-nav-item :active="teach" href="/#/teachers">Учителя</b-nav-item>
      <b-nav-item :active="disc" href="/#/disciplines">Предметы</b-nav-item>
      <b-nav-item :active="stud" href="/#/students">Студенты</b-nav-item>
      <b-nav-item :active="tt" href="/#/timetable">Расписание</b-nav-item>
    </b-nav>
  </div>
</template>

<script>
  export default {
    name: 'NavigationMenu',
    props: ['teach', 'disc', 'stud', 'tt']
  }
</script>

<style scoped>

</style>
