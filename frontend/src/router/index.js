import Vue from 'vue'
import Router from 'vue-router'
import Hello from '../components/Hello'
import TimeTable from '../components/TimeTable'
import Discipline from '../components/Discipline'
import Teacher from '../components/Teacher'
import Student from '../components/Student'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/timetable',
      name: 'TimeTable',
      component: TimeTable
    },
    {
      path: '/disciplines',
      name: 'Disciplines',
      component: Discipline
    },
    {
      path: '/teachers',
      name: 'Teacher',
      component: Teacher
    },
    {
      path: '/students',
      name: 'Student',
      component: Student
    }
  ]
})
