USE quizfun;

insert into `user`(`USERNAME`,`VERSION`,`PASSWORD`,`ACCOUNT_NON_EXPIRED`,`ACCOUNT_NON_LOCKED`,`CREDENTIALS_NON_EXPIRED`,`ENABLED`,`CREATED_BY`,`CREATED_DATE`) values ('http://isuru.myopenid.com/',0,'7246355de97d8297cdb1009639b4477d0cd57662',1,1,1,1,'http://isuru.myopenid.com/','2008-12-02 10:52:34');
insert into `user_authority`(`USERNAME`,`AUTHORITY`) values ('http://isuru.myopenid.com/','ROLE_USER');
insert into `user_authority`(`USERNAME`,`AUTHORITY`) values ('http://isuru.myopenid.com/','ROLE_LECTURER');
insert into `user_authority`(`USERNAME`,`AUTHORITY`) values ('http://isuru.myopenid.com/','ROLE_ADMIN');
