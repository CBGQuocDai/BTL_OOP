CREATE Table report(
                       reportId int not null primary KEY,
                       postId int,
                       commentId int,
                       reason VARCHAR(255),
                       content VARCHAR(255),
                       time timestamp
);
CREATE Table post(
                     postId int NOT NULL PRIMARY KEY,
                     userId int,
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
                            commentId int,
                            Type varchar(255),
                            time timestamp
);
Create Table comment (
                         commentId int primary key,
                         parentComment int,
                         postId int,
                         username varchar(50),
                         content text,
                         timeUp timestamp
);
CREATE TABLE user(
                     userId int primary key,
                     userName varchar(100),
                     email varchar(100),
                     password varchar(100),
                     gender int,
                     avatar varchar(255),
                     role varchar(50)

);
CREATE Table follow(
                       followId int primary key,
                       userIdSrc int, -- người đi follow
                       userIdDst int, -- người được follow
                       time timestamp
);
insert into user(userId,userName,email,password,gender,avatar,role)
values(1,'user1','user1@gmail.com','12345',1,'/file/user1.png','user');
insert into user(userId,userName,email,password,gender,avatar,role)
values(2,'user2','user2@gmail.com','12345',1,'/file/user2.png','user');
insert into user(userId,userName,email,password,gender,avatar,role)
values(3,'user3','user3@gmail.com','12345',0,'/file/user3.png','user');
insert into user(userId,userName,email,password,gender,avatar,role)
values(4,'user4','user4@gmail.com','12345',0,'/file/user4.png','user');
insert into user(userId,userName,email,password,gender,avatar,role)
values(5,'user5','user5@gmail.com','12345',1,'/file/user5.png','user');