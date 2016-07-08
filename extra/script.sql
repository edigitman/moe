CREATE TABLE users
(
  id integer NOT NULL,
  email character varying(200),
  name character varying(200),
  firstname character varying(200),
  lastname character varying(200),
  password character varying(200),
  dateofbirth date,
  enabled integer,
  role character varying(200),
  datecreated timestamp without time zone DEFAULT now(),
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE verification_token
(
  id integer NOT NULL,
  token character varying(200),
  verified integer,
  userid integer,
  expiredate timestamp without time zone,
  datecreated timestamp without time zone DEFAULT now(),
  CONSTRAINT verification_token_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);

CREATE TABLE exams
(
  id integer NOT NULL,
  name character varying(200),
  owner integer,
  difficulty character varying(200),
  datecreated timestamp without time zone DEFAULT now(),
  CONSTRAINT exams_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);

CREATE TABLE exam_items
(
  id integer NOT NULL,
  assertion character varying(200),
  difficulty integer,
  points integer,
  type integer,
  examid integer,
  datecreated timestamp without time zone DEFAULT now(),
  CONSTRAINT exam_items_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);

CREATE TABLE exam_item_answers
(
  id integer NOT NULL,
  correct integer,
  value character varying(200),
  itemid integer,
  datecreated timestamp without time zone DEFAULT now(),
  CONSTRAINT exam_item_answers_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);

CREATE TABLE exam_instances
(
  id integer NOT NULL,
  name character varying(200),
  status integer,
  startdate timestamp without time zone,
  enddate timestamp without time zone,
  points integer,
  examid integer,
  egroupid integer,
  datecreated timestamp without time zone DEFAULT now(),
  CONSTRAINT exam_instances_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);

CREATE TABLE exam_groups
(
  id integer NOT NULL,
  name character varying(200),
  owner integer,
  datecreated timestamp without time zone DEFAULT now(),
  CONSTRAINT exam_groups_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);

CREATE TABLE exam_group_user
(
  id integer NOT NULL,
  groupid integer,
  studentid integer,
  datecreated timestamp without time zone DEFAULT now(),
  CONSTRAINT exam_group_user_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);

CREATE TABLE student_exam_answers
(
  id integer NOT NULL,
  studentexaminstanceid integer,
  examitemid integer,
  ownerid integer,
  value character varying(200),
  datecreated timestamp without time zone DEFAULT now(),
  CONSTRAINT student_exam_answers_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);

CREATE TABLE student_exam_instances
(
  id integer NOT NULL,
  examid integer,
  studid integer,
  status integer,
  dateupdated timestamp without time zone,
  datecreated timestamp without time zone DEFAULT now(),
  CONSTRAINT student_exam_instances_pkey PRIMARY KEY (id)
)
WITH (
OIDS=FALSE
);

create sequence users_seq increment by 1 start with 1000;
create sequence verification_token_seq increment by 1 start with 1000;
create sequence exams_seq increment by 1 start with 1000;
create sequence exam_items_seq increment by 1 start with 1000;
create sequence exam_item_answers_seq increment by 1 start with 1000;
create sequence exam_instances_seq increment by 1 start with 1000;
create sequence exam_groups_seq increment by 1 start with 1000;
create sequence exam_group_user_seq increment by 1 start with 1000;
create sequence student_exam_instances_seq increment by 1 start with 1000;
create sequence student_exam_answers_seq increment by 1 start with 1000;

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

insert INTO users(email,name, password, role, enabled)
VALUES ('prof@mail.com', 'profesor', 'secret', 'PROFESOR', 1);

insert INTO users(email,name, password, role, enabled)
VALUES ('stud@mail.com', 'student', 'secret', 'STUDENT', 1);

insert INTO users(email,name, password, role, enabled)
VALUES ('admin@mail.com', 'administrator', 'secret', 'ADMIN', 1);