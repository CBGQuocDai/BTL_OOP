use defaultdb;
CREATE Table post(
                     postId varchar(10) NOT NULL PRIMARY KEY,
                     userId varchar(10),
                     title varchar(1024),
                     tags varchar(100),
                     type varchar(10),
                     content TEXT,
                     timeUp timestamp
);
CREATE Table interaction(
                            interactionId int primary key,
                            userId int,
                            postId int,
                            Type varchar(255)
);
Create Table comment (
                         commentId int primary key,
                         postId int,
                         userId int,
                         content text,
                         timeUp timestamp
);
CREATE TABLE User(
                     userId int primary key,
                     userName varchar(100),
                     email varchar(100),
                     password varchar(100),
                     gender int,
                     Avatar varchar(255)
);