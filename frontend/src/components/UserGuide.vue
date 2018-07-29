<template>
    <b-card title="Справка пользователя"
            tag="article"
            class="mb-6">
      <p class="card-text">
        Данный сайт предназначен для работы с расписанием ВУЗа и содержит следующие разделы.
      </p>
      <ol>
        <li>
          <a href="/#/timetable" target="_blank">/timetable</a>
          <p>
            Непосредственно расписание и средства для работы с ним: создание пар, удаление пар, а также просмотр
            текущего
            расписания с фильтрацией и сортировкой. Сегодняшние пары и пары не обычного типа подсвечиваются.
          </p>
        </li>
        <li>
          <a href="/#/statistic/" target="_blank">/statistic</a>
          <p>
            Набор отчётов, формируемых на основании существующего описания.
          </p>
        </li>
        <li>
          <a href="/#/students/" target="_blank">/students</a>
          <p>
            Справочник студентов с поддержкой фильтрации и сортировки.
          </p>
        </li>
        <li>
          <a href="/#/teachers/" target="_blank">/teachers</a>
          <p>
            Справочник преподавателей с поддержкой фильтрации и сортировки.
          </p>
        </li>
        <li>
          <a href="/#/disciplines/" target="_blank">/disciplines</a>
          <p>
            Справочник дисциплин с поддержкой фильтрации и сортировки.
          </p>
        </li>
        <li>
          <a href="/#/auditories/" target="_blank">/auditories</a>
          <p>
            Справочник аудиторий с возможностью создания, удаления, а также с поддержкой фильтрации и сортировки.
          </p>
        </li>
        <li>
          <a href="/swagger-ui.html" target="_blank">/swagger-ui.html</a>
          <p>
            Раздел с документацией для разработчиков.
          </p>
        </li>
      </ol>
      <b-button href="/#/" variant="primary">На домашнюю страницу</b-button>
    </b-card>
</template>

<script>
  export default {
    name: 'userguide'
  }
</script>

<style scoped>

</style>
