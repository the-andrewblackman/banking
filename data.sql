insert into account(name) values('John Steinbeck');
insert into account(name) values('Frank Herbert');

insert into checking(name,available_balance,present_balance,account_id) values('checking_1',5,5,1);
insert into checking(name,available_balance,present_balance,account_id) values('checking_2',10,9,2);

insert into savings(name,available_balance,present_balance,account_id) values('checking_3',200,200,1);

insert into trxnsxctions(trxnsxctiontype,trxnsxctiondescription,amount,checking_id,savings_id) values('credit','Starbucks',5,1,null);
insert into trxnsxctions(trxnsxctiontype,trxnsxctiondescription,amount,checking_id,savings_id) values('debit','Toy',10,1,null);
insert into trxnsxctions(trxnsxctiontype,trxnsxctiondescription,amount,checking_id,savings_id) values('credit','Mall',15,2,1);
insert into trxnsxctions(trxnsxctiontype,trxnsxctiondescription,amount,checking_id,savings_id) values('credit','Restaurant',15,2,null);

