insert into wallet (currency_id, user_id) values (1,'user1');
insert into wallet (currency_id, user_id) values (1,'user2');

insert into transaction (global_id,type_id,amount,wallet_id,currency_id,description)
values ('b7e7fa0c-f71b-11ef-9cd2-0242ac120002','C',10,1,1,'add funds');
insert into transaction (global_id,type_id,amount,wallet_id,currency_id,description)
values ('b7e7fc46-f71b-11ef-9cd2-0242ac120002','D',-10,1,1,'remove funds');