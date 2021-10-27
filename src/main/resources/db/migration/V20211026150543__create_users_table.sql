create table `users`
(
    id BIGINT(20) AUTO_INCREMENT NOT NULL PRIMARY KEY ,
    name VARCHAR(50) NOT NULL ,
    age BIGINT(20) NOT NULL ,
    isMale BOOLEAN NOT NULL
)