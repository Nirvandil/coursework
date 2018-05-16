INSERT INTO university (id, name, description) VALUES
  (1, 'Крымский государственный инженерно-педагогический университет', '')^;
INSERT INTO auditories (id, description, number) VALUES
  (1,  '', '243б'),
  (2,  '', '233a'),
  (3,  '', '239'),
  (4,  '', '245'),
  (5,  '', '247'),
  (6,  '', '189'),
  (7,  '', '236'),
  (8,  '', '243a'),
  (9,  '', '238a')^;
INSERT INTO faculties (id, description, name, university_id) VALUES
  (2,  'Экономики, менджмента и информационных технологий', 'ЭМиИТ', 1)^;
INSERT INTO courses (id, number, faculty_id) VALUES
  (3, 3, 2)^;
INSERT INTO disciplines (id, description, name) VALUES
  (4,  'То, ради чего стоит жить', 'Базы данных'),
  (5,  '', 'Основы научных исследований'),
  (6,  '', 'Теория вероятностей и математическая статистика'),
  (7,  '', 'Информатика и программирование'),
  (8,  '', 'Физика'),
  (9,  '', 'Иностранный язык'),
  (10, '', 'Педагогика'),
  (11, '', 'Архитектура встроенных систем'),
  (12, '', 'Разработка серверных приложений')^;
INSERT INTO `groups` (id, `name`, course_id) VALUES
  (5,  'ЗИ16', 3)^;
INSERT INTO pairs (id, number, start_time, finish_time) VALUES
  (1, 1, '08:00:00', '09:30:00'),
  (2, 2, '09:40:00', '11:10:00'),
  (3, 3, '11:30:00', '13:00:00'),
  (4, 4, '13:10:00', '14:40:00'),
  (5, 5, '14:50:00', '16:20:00'),
  (6, 6, '16:30:00', '18:00:00'),
  (7, 7, '18:10:00', '19:40:00'),
  (8, 8, '19:50:00', '21:20:00')^;
INSERT INTO students (id, first_name, last_name, middle_name, student_group_id) VALUES
  (14, 'Владимир', 'Сухарев', 'Александрович', 5),
  (15, 'Алексей', 'Пешкун', 'Анатольевич', 5),
  (16, 'Сергей', 'Акулиненко', 'Игоревич', 5),
  (17, 'Анастасия', 'Дейнега', 'Геннадьевна', 5),
  (18, 'Андрей', 'Захарчук', 'Викторович', 5),
  (19, 'Александр', 'Кех', 'Викторович', 5),
  (20, 'Александр', 'Котляров', 'Анатольевич', 5),
  (21, 'Ильяс', 'Мамадиев', 'Аблитарович', 5),
  (22, 'Алим', 'Меназизов', 'Ленурович', 5),
  (23, 'Родион', 'Мирошниченко', 'Андреевич', 5),
  (24, 'Азиз', 'Ниметуллаев', 'Шевкетович', 5),
  (25, 'Андрей', 'Реканд', 'Дмитриевич', 5),
  (26, 'Константин', 'Смирнов', 'Игоревич', 5),
  (27, 'Николай', 'Сухин', 'Владимирович', 5),
  (28, 'Инна', 'Таракчеева', 'Николаевна', 5),
  (29, 'Юрий', 'Удод', 'Юрьевич', 5),
  (30, 'Владимир', 'Чепюк', 'Алимович', 5),
  (31, 'Алексей', 'Зайцев', 'Евгеньевич', 5),
  (32, 'Ян', 'Куимов', 'Анатольевич', 5),
  (33, 'Дмитрий', 'Макин', 'Александрович', 5)^;
INSERT INTO teachers (id, first_name, last_name, middle_name, university_id) VALUES
  (15, 'Эльдар', 'Батыр', 'Ибрагимович', 1),
  (16, 'Фатиме', 'Ильясова', 'Серверовна', 1),
  (17, 'Энвер', 'Умеров', 'Айдерович', 1),
  (18, 'Элина', 'Насибуллаева', 'Расимовна', 1),
  (19, 'Зарема', 'Асанова', 'Ризаевна', 1),
  (20, 'Ленмар', 'Абдурайимов', 'Нариманович', 1)^;
INSERT INTO teachers_disciplines (teachers_id, disciplines_id) VALUES
  (15, 4),
  (16, 5),
  (16, 7),
  (17, 6),
  (17, 8),
  (18, 9),
  (19, 10),
  (20, 11),
  (20, 12)^;
INSERT INTO time_table (id) VALUES
  (17)^;
INSERT INTO pair_types(id, `type`) VALUES
(2, 'EXAM'),
(1, 'REGULAR')^;
INSERT INTO work_pairs
(id, date, auditory_id, discipline_id, group_id, pair_id, teacher_id, time_table_id, type_id)
VALUES
  (16, '2018-05-11', 1, 9, 5, 2, 18, 17, 1),
  (17, '2018-05-11', 2, 6, 5, 3, 17, 17, 1),
  (18, '2018-05-11', 7, 5, 5, 4, 16, 17, 1),
  (19, '2018-05-11', 3, 9, 5, 5, 15, 17, 1),
  (20, '2018-05-12', 3, 6, 5, 2, 17, 17, 1),
  (21, '2018-05-12', 3, 6, 5, 3, 17, 17, 1),
  (22, '2018-05-14', 4, 6, 5, 2, 17, 17, 1),
  (23, '2018-05-14', 4, 6, 5, 3, 17, 17, 1),
  (24, '2018-05-14', 4, 10, 5, 4, 19, 17, 1),
  (25, '2018-05-14', 4, 10, 5, 5, 19, 17, 1)^;
