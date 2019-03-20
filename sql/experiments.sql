CREATE TABLE colonist (
  colonist_id serial primary key,
  colonist_name varchar(20) UNIQUE NOT NULL,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp);

-- Create a colonist

INSERT INTO colonist (colonist_name) VALUES
    ('Han');

-- Or Create multiple colonists at once

INSERT INTO colonist (colonist_name)
VALUES
    ('Han'),
    ('Luke'),
    ('Leia'),
    ('Vader');

-- Create their professions

CREATE TABLE profession(
  profession_id serial primary key,
  profession_name varchar(255) UNIQUE NOT NULL,
  profession_salary integer NOT NULL
);

INSERT INTO profession (profession_name, profession_salary)
VALUES
    ('Jedi', 245345),
    ('Pilot', 88353),
    ('Princess', 5464678),
    ('Dark Lord', 10453456);

CREATE TABLE colonist_profession
(
  colonist_id integer NOT NULL,
  profession_id integer NOT NULL,
  grant_date timestamp default current_timestamp,
  PRIMARY KEY (colonist_id, profession_id),
  CONSTRAINT colonist_profession_profession_id_fkey FOREIGN KEY (profession_id)
      REFERENCES profession (profession_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT colonist_profession_colonist_id_fkey FOREIGN KEY (colonist_id)
      REFERENCES colonist (colonist_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- Grant their professions - I would need to know colonist_id & profession_id

INSERT INTO colonist_profession (colonist_id, profession_id)
WITH
  myconstants (ColonistName, ProfessionName) as (
    values ('Luke', 'Jedi')
  ),
  colonist_table AS (
    SELECT colonist_id
    FROM colonist, myconstants
    WHERE colonist_name=ColonistName
  ),
  profession_table AS (
    SELECT profession_id
    FROM profession, myconstants
    WHERE profession_name=ProfessionName
  )
  select colonist_table.colonist_id, profession_table.profession_id
  from colonist_table,profession_table;


-- Q & A - Q gets made first!

CREATE TABLE question (
  question_id serial primary key,
  question_label varchar(255) UNIQUE NULL,
  question varchar(255) NOT NULL,
  answer varchar(255) NULL default 'no answer',
  answer_viz varchar(100) NULL,
  colonist_id serial references colonist(colonist_id) ON DELETE CASCADE,
  created_at timestamp default current_timestamp);

CREATE TABLE answer (
  answer_id serial primary key,
  answer jsonb NOT NULL,
  colonist_id serial references colonist(colonist_id) ON DELETE CASCADE,
  question_label varchar(255) references question(question_label),
  created_at timestamp default current_timestamp);

INSERT INTO question (question_label, question, answer, answer_viz, colonist_id) VALUES
  ('gamertag', 'What is your gamer tag?', '', 'string', 4),
  ('background', 'Which background would you like for your profile?', '', 'image', 4),
  ('you', 'Which image best represents you?', '','bubble', 4),
  ('games', 'Which of these games do you like?', '','bubble', 4),
  ('teams', 'Which of these teams do you like?', '','bubble',4),
  ('casters', 'Which of these casters do you like?', '','bubble',4),
  ('rival', 'Who is your rival?', 'headwinds','string',4),
  ('pin', 'Which of these would like to pin next to your gamertag?','headwinds', 'select', 4);

CREATE TABLE achievement (
  achievement_id serial primary key,
  achievement_label varchar(255) NULL,
  achievement varchar(255) NOT NULL);

CREATE TABLE profile (
  profile_id serial primary key,
  colonist_experience int NOT NULL default 0,
  colonist_level SMALLINT NOT NULL default 0,
  answered_total SMALLINT NOT NULL default 0,
  colonist_id serial references colonist(colonist_id) ON DELETE CASCADE,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp);

-- add Han to test with
INSERT INTO profile (colonist_id) VALUES (4);

-- OR all at once...
INSERT INTO profile (colonist_id) VALUES (1),(2),(3),(4),(5),(6);

CREATE TABLE events (
  event_id serial primary key,
  event_label varchar(255) NULL,
  event_device varchar(255) NULL,
  event_app varchar(255) NULL,
  event_who varchar(255) NULL,
  event_description varchar(255) NOT NULL,
  event_json jsonb,
  created_at timestamp default current_timestamp);


-- from the terminal, sign into postgresql using the username postgres
$ psql --u postgres

-- list the databases
postgres=# \l

-- connect to shed
postgres=# \c shed
