CREATE TABLE IF NOT EXISTS mountain (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  height int(11) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS test_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS post (
  id int(11) NOT NULL AUTO_INCREMENT,
  content varchar(200) NOT NULL,
  star int(20) NOT NULL,
  user_id int(20),
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES test_user (id)
);