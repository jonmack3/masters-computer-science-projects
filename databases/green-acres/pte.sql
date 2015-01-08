/* Jonathan Mack
CS440
Final Project: tests
12/7/07 */

delete from farms;
delete from plots;
delete from crops;
delete from animals;
delete from equipment;
delete from crop_allocation;
delete from personnel;
delete from error_log;

--Rule 1 Test
insert into personnel values('123456789', 'Bob', 
	address_ty('1234567890', 'sname', 'cname', 'al', 'zname'), 
	'winame', 'pw', old_password_ty('op'));
	
insert into farms (id, name, latitude, longitude, manager) values 
	(1, 'ok values', 30, 70, 
	(select ref(p) from personnel p where SSN = '123456789'));

insert into farms (id, name, latitude, longitude, manager) values 
	(2, 'bad values', 0, 100, 
	(select ref(p) from personnel p where SSN = '123456789'));

select id, name, latitude, longitude, deref(manager) from farms;
select * from error_log;
show errors;

pause;

delete from personnel;
delete from farms;
delete from error_log;

--Rule 2 Test
insert into animals (breed, acreage_required, caloric_requirement, cost, value)
	values ('ok', 1, 1, 1, 1);
insert into animals (breed, acreage_required, caloric_requirement, cost, value)
	values ('anbad1', null, 1, 1, 1);
insert into animals (breed, acreage_required, caloric_requirement, cost, value)
	values ('anbad5', -1, 1, 1, 1);

insert into crops (name, bushel_per_acre, kcal_per_bushel,
	cost_per_bushel, value_per_bushel) values ('ok', 1, 1, 1, 1);
insert into crops (name, bushel_per_acre, kcal_per_bushel,
	cost_per_bushel, value_per_bushel) values ('anbad1', null, 1, 1, 1);
insert into crops (name, bushel_per_acre, kcal_per_bushel,
	cost_per_bushel, value_per_bushel) values ('anbad5', -1, 1, 1, 1);
	
select * from crops;
select * from animals;

pause;

delete from crops;
delete from animals;

--Rule 3 Test
insert into farms (id) values (1);
insert into plots (id, farm_id, acreage) values ('1', 1, 10);
insert into plots (id, farm_id, acreage) values ('2', 1, 10);
insert into crops (name, bushel_per_acre, kcal_per_bushel, cost_per_bushel,
	value_per_bushel) values ('1', 1, 1, 1, 1);
insert into crop_allocation (plot, crop, acreage) values ('1', 1, 10);
insert into crop_allocation (plot, crop, acreage) values ('2', 1, 20);

select * from crop_allocation;

pause;

delete from crop_allocation;
delete from plots;
delete from crops;
delete from farms;

--Rule 4 test
insert into crops (name, bushel_per_acre, kcal_per_bushel, cost_per_bushel, 
	value_per_bushel) values ('1', 1, 1, 10, 10);
insert into crops (name, bushel_per_acre, kcal_per_bushel, cost_per_bushel, 
	value_per_bushel) values ('2', 1, 1, 20, 10);
insert into animals (breed, acreage_required, caloric_requirement, 
	cost, value) values ('1', 1, 1, 10, 10);
insert into animals (breed, acreage_required, caloric_requirement, 
	cost, value) values ('2', 1, 1, 20, 10);
	
select * from crops;
select * from animals;
select * from error_log;

pause;

delete from error_log;
delete from animals;
delete from crops;

--Rule 5 test
select * from equipment_location_by_farm;
insert into farms (id, name, longitude, latitude) values (2, 'f2', 40, 80);
insert into farms (id, name, longitude, latitude) values (1, 'f1', 40, 80);
insert into plots (id, farm_id, acreage) values ('p2', 2, 10);
insert into plots (id, farm_id, acreage) values ('p1', 1, 10);
insert into equipment (serialnum, brand, type, current_plot) values
	('e4', 'b1', 't1', 'p1');
insert into equipment (serialnum, brand, type, current_plot) values
	('e3', 'b1', 't2', 'p2');
insert into equipment (serialnum, brand, type, current_plot) values
	('e2', 'b1', 't3', null);
insert into equipment (serialnum, brand, type, current_plot) values
	('e1', 'b1', 't4', 'p2');

col 'Current Farm' format a20
col 'Equipment Type' format a20
col 'Serial Number' format a20
select * from equipment_location_by_farm;

pause;
	
delete from equipment;
delete from plots;
delete from farms;

--Rule 8 test
delete from personnel;
insert into personnel values('123456789', 'Bob', 
	address_ty('1234567890', 'sname1', 'cname1', 'al', 'zname'), 
	'winame', null, old_password_ty(null));
insert into personnel values('223456789', 'Joe', 
	address_ty('2234567890', 'sname2', 'cname2', 'a2', 'zname2'), 
	'winame2', 'pw2', old_password_ty('op2'));

create or replace procedure rule_8_test (new_p in varchar2) is
	person			person_ty;
begin
	select value(p) into person from personnel p where SSN = '123456789';
 	person.change_password(new_p);
	update personnel p set p.password = person.password 
		where SSN = '123456789';
	update personnel p set p.old_passwords = person.old_passwords
		where SSN = '123456789';
end;
/

col old_passwords format a50;
select SSN, password, old_passwords from personnel;
exec rule_8_test (null);
select SSN, password, old_passwords from personnel;
exec rule_8_test ('test1')
select SSN, password, old_passwords from personnel;
exec rule_8_test ('test2');
select SSN, password, old_passwords from personnel;
exec rule_8_test ('test3');
select SSN, password, old_passwords from personnel;
exec rule_8_test ('test4');
select SSN, password, old_passwords from personnel;
exec rule_8_test ('test5');
select SSN, password, old_passwords from personnel;
exec rule_8_test ('t6');
select SSN, password, old_passwords from personnel;
exec rule_8_test ('testtesttesttesttesttesttes7');
select SSN, password, old_passwords from personnel;
pause;

delete from personnel;

--Rule 9 test
delete from crop_allocation;
delete from crops;
delete from animals;
delete from plots;
delete from farms;

insert into farms (id) values (1);

insert into plots (id, farm_id, acreage, animals) values 
	('p1', 1, 1000, animal_placement_ty());
insert into plots (id, farm_id, acreage, animals) values
	('p2', 1, 1000, animal_placement_ty());

insert into crops (name, bushel_per_acre, kcal_per_bushel, cost_per_bushel, 
	value_per_bushel) values ('c1', 10, 7, 2, 3);
insert into crops (name, bushel_per_acre, kcal_per_bushel, cost_per_bushel,
	value_per_bushel) values ('c2', 20, 8, 2, 4);
insert into crops (name, bushel_per_acre, kcal_per_bushel, cost_per_bushel,
	value_per_bushel) values ('c3', 30, 9, 2, 5);

insert into animals (breed, acreage_required, caloric_requirement, 
	cost, value) values ('b1', 1, 12, 3, 5);
insert into animals (breed, acreage_required, caloric_requirement,
	cost, value) values ('b2', 5, 16, 4, 8);
insert into animals (breed, acreage_required, caloric_requirement,
	cost, value) values ('b3', 6, 17, 2, 7);

insert into table (select animals from plots p where p.id = 'p1') values
	('b1', 2);
insert into table (select animals from plots p where p.id = 'p1') values
	('b2', 3);
insert into table (select animals from plots p where p.id = 'p1') values
	('b3', 4);
insert into table (select animals from plots p where p.id = 'p2') values
	('b2', 5);
insert into table (select animals from plots p where p.id = 'p2') values
	('b3', 6);

insert into crop_allocation (plot, crop, acreage) values ('p1', 'c1', 998);
insert into crop_allocation (plot, crop, acreage) values ('p2', 'c2', 999);

clear col;
col manager format a20
col animals format a40
col breed format a5
set numwidth 6
col name format a4
col breed format a5
col crop format a7

select distinct id, a.breed, count, cost, value, caloric_requirement "CR",
	crop, ca.acreage, crops.bushel_per_acre "BpA", crops.kcal_per_bushel "KpB",
	crops.cost_per_bushel "CPB", crops.value_per_bushel "VPB"
	from 
	plots, table(animals) a, animals b, 
	crops, crop_allocation ca
	where 
	a.breed = b.breed 
	and plots.id = ca.plot
	and crops.name = ca.crop
	order by id, breed;

select * from potential_profit_by_plot;

pause;

clear col;

delete from error_log;
delete from personnel;
delete from equipment;
delete from crop_allocation;
delete from crops;
delete from animals;
delete from plots;
delete from farms;

show errors;