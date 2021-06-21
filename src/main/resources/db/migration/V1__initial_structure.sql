CREATE TABLE user (
  id bigint NOT NULL AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  active tinyint DEFAULT NULL,
  email varchar(255) NOT NULL,
  login varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  profile_level tinyint DEFAULT NULL,
  created_by bigint DEFAULT NULL,
  updated_by bigint DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_ob8kqyqqgmefl0aco34akdtpe (email),
  UNIQUE KEY UK_ew1hvam8uwaknuaellwhqchhb (login),
  KEY FKdltbr5t0nljpuuo4isxgslt82 (created_by),
  KEY FK2a54xhceitopkkw1hlo3tkv3i (updated_by),
  CONSTRAINT FK2a54xhceitopkkw1hlo3tkv3i FOREIGN KEY (updated_by) REFERENCES user (id),
  CONSTRAINT FKdltbr5t0nljpuuo4isxgslt82 FOREIGN KEY (created_by) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


CREATE TABLE actor (
  id bigint NOT NULL AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  active tinyint DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  created_by bigint DEFAULT NULL,
  updated_by bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK1bya2eoaba3l90k7bfbnjkary (created_by),
  KEY FK90eu2n3ag0wrynhq6ceai15eo (updated_by),
  CONSTRAINT FK1bya2eoaba3l90k7bfbnjkary FOREIGN KEY (created_by) REFERENCES user (id),
  CONSTRAINT FK90eu2n3ag0wrynhq6ceai15eo FOREIGN KEY (updated_by) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE director (
  id bigint NOT NULL AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  active tinyint DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  created_by bigint DEFAULT NULL,
  updated_by bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK2q04na8uq3ha2nt49iuqrp50q (created_by),
  KEY FKr4yxhno7v492g2h82b6la8lpp (updated_by),
  CONSTRAINT FK2q04na8uq3ha2nt49iuqrp50q FOREIGN KEY (created_by) REFERENCES user (id),
  CONSTRAINT FKr4yxhno7v492g2h82b6la8lpp FOREIGN KEY (updated_by) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE movie (
  id bigint NOT NULL AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  active tinyint DEFAULT NULL,
  amount_votes bigint DEFAULT NULL,
  average_score double DEFAULT NULL,
  genre varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  total_score bigint DEFAULT NULL,
  created_by bigint DEFAULT NULL,
  updated_by bigint DEFAULT NULL,
  director_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKjxx409kr79vxtep4t8pcspjh9 (created_by),
  KEY FKmc7wqeq6op292kylmkkrdbyfj (updated_by),
  KEY FKbi47w3cnsfi30gc1nu2avgra2 (director_id),
  CONSTRAINT FKbi47w3cnsfi30gc1nu2avgra2 FOREIGN KEY (director_id) REFERENCES director (id),
  CONSTRAINT FKjxx409kr79vxtep4t8pcspjh9 FOREIGN KEY (created_by) REFERENCES user (id),
  CONSTRAINT FKmc7wqeq6op292kylmkkrdbyfj FOREIGN KEY (updated_by) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE movie_actors (
  movie_id bigint NOT NULL,
  actors_id bigint NOT NULL,
  KEY FKoxmxj61v0a9qs12vboo8rxpno (actors_id),
  KEY FKbsto8yef4btokhveihmkg8876 (movie_id),
  CONSTRAINT FKbsto8yef4btokhveihmkg8876 FOREIGN KEY (movie_id) REFERENCES movie (id),
  CONSTRAINT FKoxmxj61v0a9qs12vboo8rxpno FOREIGN KEY (actors_id) REFERENCES actor (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE movie_votes (
  id bigint NOT NULL AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  active tinyint DEFAULT NULL,
  score int DEFAULT NULL,
  created_by bigint DEFAULT NULL,
  updated_by bigint DEFAULT NULL,
  movie_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKa7696qfs1f7wt2pta0orksbjp (created_by),
  KEY FK7nkjfiuuyt7ogg7m1mqu9j677 (updated_by),
  KEY FKmscio6fembhf576sqvy7xlpam (movie_id),
  CONSTRAINT FK7nkjfiuuyt7ogg7m1mqu9j677 FOREIGN KEY (updated_by) REFERENCES user (id),
  CONSTRAINT FKa7696qfs1f7wt2pta0orksbjp FOREIGN KEY (created_by) REFERENCES user (id),
  CONSTRAINT FKmscio6fembhf576sqvy7xlpam FOREIGN KEY (movie_id) REFERENCES movie (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;