
-- 这里创建数据库先

CREATE TABLE student
(
  id INT(11) PRIMARY KEY NOT NULL,
  name VARCHAR(255),
  age INT(11)
);

INSERT INTO test.student (id, name, age) VALUES (1, '张三', 12);
INSERT INTO test.student (id, name, age) VALUES (2, '李四', 34);
INSERT INTO test.student (id, name, age) VALUES (3, '王五', 1212);