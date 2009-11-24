alter table REF_ORDERED_CAMERAS drop constraint FKE43D0810152A374
alter table REF_ORDERED_CAMERAS drop constraint FKE43D0810F50B25F1
drop table CAMERA if exists
drop table ENCOMENDAS if exists
drop table REF_ORDERED_CAMERAS if exists
drop table USER if exists
create table CAMERA (ID_CAMERA integer generated by default as identity (start with 1), model varchar(255), price float, primary key (ID_CAMERA))
create table ENCOMENDAS (ID_ORDER integer generated by default as identity (start with 1), username varchar(255), shippingAddress varchar(255), emailAddress varchar(255), purchaseDate timestamp, orderStatus varbinary(255), primary key (ID_ORDER))
create table REF_ORDERED_CAMERAS (orderid integer not null, ID_CAMERA integer not null, idx integer not null, primary key (orderid, idx))
create table USER (ID_USER integer generated by default as identity (start with 1), username varchar(255), password varchar(255), address varchar(255), email varchar(255), name varchar(255), primary key (ID_USER))
alter table REF_ORDERED_CAMERAS add constraint FKE43D0810152A374 foreign key (ID_CAMERA) references CAMERA
alter table REF_ORDERED_CAMERAS add constraint FKE43D0810F50B25F1 foreign key (orderid) references ENCOMENDAS
