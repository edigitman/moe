DROP TABLE users;
DROP TABLE verification_token;
DROP TABLE exams;
DROP TABLE exam_items;
DROP TABLE exam_item_answers;
DROP TABLE exam_groups;
DROP TABLE exam_group_user;
DROP TABLE exam_instances;
DROP TABLE student_exam_answers;
DROP TABLE student_exam_instances;

DROP SEQUENCE users_seq;
DROP SEQUENCE verification_token_seq;
DROP SEQUENCE exams_seq;
DROP SEQUENCE exam_items_seq;
DROP SEQUENCE exam_item_answers_seq;
DROP SEQUENCE exam_groups_seq;
DROP SEQUENCE exam_group_user_seq;
DROP SEQUENCE exam_instances_seq;
DROP SEQUENCE student_exam_answers_seq;
DROP SEQUENCE student_exam_instances_seq;

CREATE TABLE users
(
  id          INTEGER NOT NULL,
  email       CHARACTER VARYING(60),
  name        CHARACTER VARYING(50),
  firstname   CHARACTER VARYING(50),
  lastname    CHARACTER VARYING(50),
  password    CHARACTER VARYING(50),
  dateofbirth DATE,
  enabled     BOOLEAN,
  role        CHARACTER VARYING(20),
  datecreated TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE TABLE verification_token
(
  id          INTEGER NOT NULL,
  token       CHARACTER VARYING(200),
  verified    SMALLINT,
  userid      INTEGER,
  expiredate  TIMESTAMP WITHOUT TIME ZONE,
  datecreated TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT verification_token_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE TABLE exams
(
  id          INTEGER NOT NULL,
  name        CHARACTER VARYING(200),
  owner       INTEGER,
  difficulty  SMALLINT,
  points      INTEGER,
  items      INTEGER,
  locked      BOOLEAN DEFAULT FALSE ,
  datecreated TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT exams_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE TABLE exam_items
(
  id          INTEGER NOT NULL,
  title       CHARACTER VARYING(50),
  assertion   CHARACTER VARYING(2000),
  difficulty  SMALLINT,
  points      INTEGER,
  type        SMALLINT,
  examid      INTEGER,
  datecreated TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT exam_items_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE TABLE exam_item_answers
(
  id          INTEGER NOT NULL,
  correct     BOOLEAN,
  value       CHARACTER VARYING(200),
  itemid      INTEGER,
  datecreated TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT exam_item_answers_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE TABLE exam_instances
(
  id          INTEGER NOT NULL,
  name        CHARACTER VARYING(200),
  status      INTEGER,
  startdate   TIMESTAMP WITHOUT TIME ZONE,
  enddate     TIMESTAMP WITHOUT TIME ZONE,
  examid      INTEGER,
  egroupid    INTEGER,
  owner       INTEGER,
  autoSolved  BOOLEAN                     DEFAULT FALSE,
  datecreated TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT exam_instances_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE TABLE exam_groups
(
  id          INTEGER NOT NULL,
  name        CHARACTER VARYING(200),
  owner       INTEGER,
  students    INTEGER                     DEFAULT 0,
  locked      BOOLEAN                     DEFAULT FALSE,
  datecreated TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT exam_groups_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE TABLE exam_group_user
(
  id          INTEGER NOT NULL,
  groupid     INTEGER,
  studentid   INTEGER,
  datecreated TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT exam_group_user_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE TABLE student_exam_answers
(
  id                    INTEGER NOT NULL,
  studentexaminstanceid INTEGER,
  examitemid            INTEGER,
  ownerid               INTEGER,
  value                 CHARACTER VARYING(200),
  rawAnswer             CHARACTER VARYING(50),
  solvable              BOOLEAN,
  correct               BOOLEAN,
  reviewed              BOOLEAN DEFAULT FALSE,
  points                INTEGER DEFAULT 0,
  datecreated           TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT student_exam_answers_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE TABLE student_exam_instances
(
  id          INTEGER NOT NULL,
  examid      INTEGER,
  studid      INTEGER,
  status      INTEGER,
  dateupdated TIMESTAMP WITHOUT TIME ZONE,
  datecreated TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT student_exam_instances_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE SEQUENCE users_seq INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE verification_token_seq INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE exams_seq INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE exam_items_seq INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE exam_item_answers_seq INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE exam_instances_seq INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE exam_groups_seq INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE exam_group_user_seq INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE student_exam_instances_seq INCREMENT BY 1 START WITH 1000;
CREATE SEQUENCE student_exam_answers_seq INCREMENT BY 1 START WITH 1000;

ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_seq');
ALTER TABLE verification_token ALTER COLUMN id SET DEFAULT nextval('verification_token_seq');
ALTER TABLE exams ALTER COLUMN id SET DEFAULT nextval('exams_seq');
ALTER TABLE exam_items ALTER COLUMN id SET DEFAULT nextval('exam_items_seq');
ALTER TABLE exam_item_answers ALTER COLUMN id SET DEFAULT nextval('exam_item_answers_seq');
ALTER TABLE exam_instances ALTER COLUMN id SET DEFAULT nextval('exam_instances_seq');
ALTER TABLE exam_groups ALTER COLUMN id SET DEFAULT nextval('exam_groups_seq');
ALTER TABLE exam_group_user ALTER COLUMN id SET DEFAULT nextval('exam_group_user_seq');
ALTER TABLE student_exam_instances ALTER COLUMN id SET DEFAULT nextval('student_exam_instances_seq');
ALTER TABLE student_exam_answers ALTER COLUMN id SET DEFAULT nextval('student_exam_answers_seq');

INSERT INTO users (email, name, password, role, enabled)
VALUES ('prof@mail.com', 'profesor', 'secret', 'PROFESOR', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud@mail.com', 'student', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud1@mail.com', 'student1', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud2@mail.com', 'student2', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud3@mail.com', 'student3', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud4@mail.com', 'student4', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud5@mail.com', 'student5', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud6@mail.com', 'student6', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud7@mail.com', 'student7', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud8@mail.com', 'studen8', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud9@mail.com', 'student9', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('stud0@mail.com', 'student0', 'secret', 'STUDENT', TRUE);

INSERT INTO users (email, name, password, role, enabled)
VALUES ('admin@mail.com', 'administrator', 'secret', 'ADMIN', TRUE);


INSERT INTO exams (owner, name, difficulty, points, items) VALUES (1000, 'exam 1', 2, 100, 5); -- id 1000

INSERT INTO exam_items (title, assertion, points, type, examid)
VALUES ('se da problema 1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed aliquet, leo nec vulputate euismod, nibh mi rhoncus quam, et posuere risus nulla non nulla. Nullam hendrerit dictum velit, at facilisis neque semper et. Integer facilisis dui sit amet fringilla scelerisque. Proin eleifend mattis iaculis. Maecenas quis sem quis arcu pharetra ultricies. Cras ipsum dui, maximus eget quam pretium, semper imperdiet nisi. Ut pellentesque ultrices interdum. Donec eget ante nisl. Morbi in tortor sit amet lorem gravida ullamcorper at et purus. Curabitur vel odio eleifend, vehicula dolor sit amet, imperdiet risus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur vehicula congue urna, in suscipit nulla feugiat id. Pellentesque blandit, lectus id viverra aliquam, dui justo dictum mauris, non ornare ipsum nulla et dui. Sed aliquam ex nisl, sit amet dignissim augue sodales ut. Proin aliquam neque elementum luctus accumsan. Proin ac justo sit amet tellus ornare pulvinar.',15, 1, 1000); -- id 1000
INSERT INTO exam_items (title, assertion, points, type, examid)
VALUES ('se da problema 2', 'Vestibulum gravida ultrices leo et porta. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec tincidunt nisl suscipit sapien consequat accumsan. Fusce a elementum enim. Ut condimentum vulputate eros, quis rhoncus diam. Suspendisse ac lorem erat. Donec mattis interdum lacus tincidunt commodo. Praesent aliquam convallis erat, eleifend iaculis ex auctor ac. Vivamus massa nisl, feugiat dignissim accumsan quis, posuere a elit. Duis vitae dui ante. Nunc scelerisque congue blandit.', 26, 2, 1000); -- id 1001
INSERT INTO exam_items (title, assertion, points, type, examid)
VALUES ('se da problema 3', 'Nulla euismod nisl id consectetur dignissim. Aenean nunc diam, tempus sed rutrum eget, tincidunt nec dolor. Vivamus eget eleifend massa. In hac habitasse platea dictumst. Integer ullamcorper tortor placerat, tincidunt velit at, blandit ante. Fusce in feugiat risus. Mauris magna mi, sagittis non lorem eu, fermentum molestie neque. Mauris vitae orci facilisis urna laoreet placerat. Etiam non ipsum ut neque aliquet tempor. Mauris egestas erat at dolor commodo, eget convallis sem auctor. Nullam lobortis justo eu justo convallis tincidunt. Aenean tempor ultrices metus, id viverra nisi euismod non. Aliquam erat volutpat. Donec porttitor enim sed vulputate consequat. Vestibulum id ullamcorper ex. Praesent mattis, massa non efficitur sagittis, augue diam tempus urna, a fringilla ex justo at magna.', 19, 2, 1000); -- id 1002
INSERT INTO exam_items (title, assertion, points, type, examid)
VALUES ('se da problema 4', 'Nullam dapibus ipsum quis nisi varius, quis facilisis arcu mattis. Sed sed urna placerat, porta justo sed, scelerisque diam. Nulla in enim congue, egestas odio vel, hendrerit erat. Etiam mollis velit nec metus efficitur, sit amet feugiat lectus imperdiet. Fusce vitae congue velit. Curabitur tempor, justo sit amet iaculis varius, lectus felis sagittis diam, ac tempus nisl ex ut massa. Nullam posuere tincidunt nisi vitae sollicitudin. Cras dolor felis, aliquet vitae cursus non, ornare in mi. Nulla facilisi. Vestibulum maximus lacus et commodo interdum. Ut pulvinar auctor est sit amet ullamcorper.', 30, 3, 1000); -- id 1003
INSERT INTO exam_items (title, assertion, points, type, examid)
VALUES ('se da problema 5', 'Maecenas rutrum elit non lorem viverra accumsan. Sed augue nisl, maximus id quam vel, malesuada facilisis mauris. Nullam feugiat, metus non commodo fermentum, diam massa imperdiet urna, et finibus augue est ac arcu. Sed dignissim nisi vitae tincidunt finibus. Ut et laoreet augue. Curabitur condimentum tincidunt metus, non laoreet ex volutpat in. Maecenas tincidunt justo ut dui lacinia fermentum. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Pellentesque sagittis euismod arcu, vitae fermentum mauris volutpat eget.', 10, 1, 1000); -- id 1004

INSERT INTO exam_item_answers (correct, value, itemid) VALUES (FALSE, 'raspuns 1', 1000);
INSERT INTO exam_item_answers (correct, value, itemid) VALUES (TRUE, 'raspuns 2', 1000);
INSERT INTO exam_item_answers (correct, value, itemid) VALUES (FALSE, 'raspuns 3', 1000);

INSERT INTO exam_item_answers (correct, value, itemid) VALUES (TRUE, 'raspuns 1', 1001);
INSERT INTO exam_item_answers (correct, value, itemid) VALUES (FALSE, 'raspuns 2', 1001);
INSERT INTO exam_item_answers (correct, value, itemid) VALUES (FALSE, 'raspuns 3', 1001);

INSERT INTO exam_item_answers (correct, value, itemid) VALUES (FALSE, 'raspuns 1', 1002);
INSERT INTO exam_item_answers (correct, value, itemid) VALUES (TRUE, 'raspuns 2', 1002);
INSERT INTO exam_item_answers (correct, value, itemid) VALUES (TRUE, 'raspuns 3', 1002);

INSERT INTO exam_item_answers (correct, value, itemid) VALUES (FALSE, 'raspuns 1', 1004);
INSERT INTO exam_item_answers (correct, value, itemid) VALUES (TRUE, 'raspuns 2', 1004);
INSERT INTO exam_item_answers (correct, value, itemid) VALUES (FALSE , 'raspuns 3', 1004);

INSERT INTO exam_groups (name, owner, students) VALUES ('class IX', 1000, 3);

INSERT INTO exam_group_user (groupid, studentid) VALUES (1000, 1001);
INSERT INTO exam_group_user (groupid, studentid) VALUES (1000, 1002);
INSERT INTO exam_group_user (groupid, studentid) VALUES (1000, 1003);

INSERT INTO exam_instances (name, status, examid, egroupid, owner)
    VALUES ('exam instance', 2, 1000, 1000, 1000);
