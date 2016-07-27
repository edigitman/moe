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
  locked      BOOLEAN,
  datecreated TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
  CONSTRAINT exams_pkey PRIMARY KEY (id)
)
WITH (
OIDS =FALSE
);

CREATE TABLE exam_items
(
  id          INTEGER NOT NULL,
  assertion   CHARACTER VARYING(200),
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
  autoSolved  BOOLEAN DEFAULT FALSE,
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
  students    INTEGER DEFAULT 0,
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