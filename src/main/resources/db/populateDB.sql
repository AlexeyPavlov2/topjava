DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  (to_timestamp('2015.05.30 10:00','yyyy.MM.dd HH24:mi'), 'Завтрак', 500, 100000),
  (to_timestamp('2015.05.30 13:00','yyyy.MM.dd HH24:mi'), 'Обед', 1000, 100000),
  (to_timestamp('2015.05.30 20:00','yyyy.MM.dd HH24:mi'), 'Ужин', 500, 100000),
  (to_timestamp('2015.05.31 10:00','yyyy.MM.dd HH24:mi'), 'Завтрак', 1000, 100000),
  (to_timestamp('2015.05.31 13:00','yyyy.MM.dd HH24:mi'), 'Обед', 500, 100000),
  (to_timestamp('2015.05.31 20:00','yyyy.MM.dd HH24:mi'), 'Ужин', 510, 100000),
  (to_timestamp('2015.05.31 20:00','yyyy.MM.dd HH24:mi'), 'Ужин', 555, 100001);

