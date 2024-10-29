CREATE Table report(
                       reportId int not null AUTO_INCREMENT primary KEY ,
                       postId int,
                       commentId int,
                       reason VARCHAR(255),
                       content VARCHAR(255),
                       time timestamp
);
CREATE Table post(
                     postId int NOT NULL AUTO_INCREMENT PRIMARY KEY,
                     nameAuthor VARCHAR(50),
                     userId int,
                     title varchar(1024),
                     tags varchar(100),
                     type varchar(10),
                     content TEXT,
                     time timestamp
);
CREATE Table interaction(
                            interactionId int AUTO_INCREMENT primary key,
                            userId int,
                            postId int,
                            commentId int,
                            Type varchar(255),
                            time timestamp
);
Create Table comment (
                         commentId int AUTO_INCREMENT primary key,
                         parentComment int,
                         postId int,
                         username VARCHAR(50),
                         userId int,
                         content text,
                         time timestamp
);
CREATE TABLE user(
                     userId int AUTO_INCREMENT primary key,
                     userName varchar(100),
                     email varchar(100),
                     password varchar(100),
                     gender int,
                     avatar varchar(255),
                     role varchar(50)

);
CREATE Table follow(
                       followId int AUTO_INCREMENT primary key,
                       userIdSrc int, -- người đi follow
                       userIdDst int, -- người được follow
                       time timestamp
);
CREATE TABLE notifications (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               message VARCHAR(1024) NOT NULL,
                               postId INT NOT NULL,
                               userId INT NOT NULL,
                               time TIMESTAMP
);
insert into user(userId,userName,email,password,gender,avatar,role)
values(1,'user1','user1@gmail.com','12345',1,'/file/1.png','user');
insert into user(userId,userName,email,password,gender,avatar,role)
values(2,'user2','user2@gmail.com','12345',1,'/file/2.png','user');
insert into user(userId,userName,email,password,gender,avatar,role)
values(3,'user3','user3@gmail.com','12345',0,'/file/3.png','user');
insert into user(userId,userName,email,password,gender,avatar,role)
values(4,'user4','user4@gmail.com','12345',0,'/file/4.png','user');
insert into user(userId,userName,email,password,gender,avatar,role)
values(5,'user5','user5@gmail.com','12345',1,'/file/5.png','user');