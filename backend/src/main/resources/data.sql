INSERT INTO auditories (id, description, number) VALUES
  (1,  'Аудитория на третьем этаже', 30),
  (2,  'Аудитория на втором этаже', 20),
  (3,  'Аудитория на четвёртом этаже', 42);
INSERT INTO faculties (id, description, name) VALUES
  (2,  'Экономики, менджмента и информационных технологий', 'ЭМиИТ');
INSERT INTO courses (id, number, faculty_id) VALUES
  (3, 3, 2);
INSERT INTO disciplines (id, description, name) VALUES
  (4,  'То, ради чего стоит жить', 'Базы данных'),
  (5, '', 'Основы научных исследований'),
  (6, '', 'Теория вероятностей и математическая статистика'),
  (7, '', 'Информатика и программирование'),
  (8, '', 'Физика');
INSERT INTO groups (id, name, course_id) VALUES
  (5,  'ЗИ16', 3);
INSERT INTO pairs (id, number, start_time, finish_time) VALUES
  (6,  1, '08:00:00', '09:30:00'),
  (7,  2, '09:40:00', '11:10:00'),
  (8,  3, '11:30:00', '13:00:00'),
  (9,  4, '13:10:00', '14:40:00'),
  (10, 5, '14:50:00', '16:20:00'),
  (11, 6, '16:30:00', '18:00:00'),
  (12, 7, '18:10:00', '19:40:00'),
  (13, 8, '19:50:00', '21:20:00');
INSERT INTO students (id, first_name, last_name, middle_name, student_group_id) VALUES
  (14, 'Владимир', 'Сухарев', 'Александрович', 5),
  (15, 'Алексей', 'Пешкун', '', 5);
INSERT INTO teachers (id, first_name, last_name, middle_name) VALUES
  (15, 'Эльдар', 'Батыр', 'Ибрагимович'),
  (16, 'Фатиме', 'Ильясова', 'Серверовна'),
  (17, 'Энвер', 'Умеров', 'Айдерович');
INSERT INTO teachers_disciplines (teachers_id, disciplines_id) VALUES
  (15, 4),
  (16, 5),
  (17, 6),
  (16, 7),
  (17, 8);
INSERT INTO time_table (id) VALUES
  (17);
INSERT INTO work_pairs (id, "date", auditory_id, discipline_id, group_id, pair_id, teacher_id, time_table_id) VALUES
  (16, '09.05.2018', 1, 4, 5, 7, 15, 17),
  (17, '10.05.2018', 1, 4, 5, 6, 15, 17);
