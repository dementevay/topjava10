DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, dateTime, description,calories)
VALUES (100000, '2017-04-10 09:00:02', 'Завтрак', 500);

INSERT INTO meals (user_id, dateTime, description,calories)
VALUES (100000, '2017-04-10 13:30:00', 'Обед', 1000);

INSERT INTO meals (user_id, dateTime, description,calories)
VALUES (100000, '2017-04-10 20:30:00', 'Ужин', 500);

INSERT INTO meals (user_id, dateTime, description,calories)
VALUES (100001, '2017-04-11 08:00:00', 'Завтрак', 500);

INSERT INTO meals (user_id, dateTime, description,calories)
VALUES (100001, '2017-04-11 14:00:00', 'Обед', 1200);

INSERT INTO meals (user_id, dateTime, description,calories)
VALUES (100001, '2017-04-11 21:20:00', 'Ужин', 500);