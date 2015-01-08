/* Jonathan Mack
CS440
Final Project: triggers
12/7/07
*/

create or replace trigger location
before insert on farms
for each row
when (new.latitude is null or new.longitude is null or
	new.latitude < 30 or new.latitude > 50 or 
	new.longitude < 70 or new.longitude > 90)
begin
	:new.latitude :=null;
	:new.longitude := null;
	insert into error_log (counter, date_of_entry, message) values
		(error_log_counter.nextval, sysdate, 'IMPROPER_LOCATION');
end;
/

create or replace trigger animals
before insert or update on animals
for each row
when (new.acreage_required is null or new.acreage_required < 0 or
	new.caloric_requirement is null or new.caloric_requirement < 0 or
	new.cost is null or new.cost < 0 or
	new.value is null or new.value < 0)
begin
	raise greenacres.improper_value;
end;
/

create or replace trigger crops
before insert or update on crops
for each row
when (new.bushel_per_acre is null or new.bushel_per_acre < 0 or
	new.kcal_per_bushel is null or new.kcal_per_bushel < 0 or
	new.cost_per_bushel is null or new.cost_per_bushel < 0 or
	new.value_per_bushel is null or new.value_per_bushel < 0)
begin
	raise greenacres.improper_value;
end;
/

create or replace trigger crop_acreage
before insert or update on crop_allocation
for each row
declare
	plot_size		plots.acreage%type;
begin
	select acreage into plot_size from plots where id = :new.plot;
	if  (:new.acreage > plot_size) then
		raise greenacres.improper_value;
	end if;
end;
/

create or replace trigger crop_profit
before insert or update on crops
for each row
when (new.cost_per_bushel > new.value_per_bushel)
begin
	insert into error_log (counter, date_of_entry, message) values
		(error_log_counter.nextval, sysdate, 'COST_EXCEEDS_VALUE');
end;
/

create or replace trigger animal_profit
before insert or update on animals
for each row
when (new.cost > new.value)
begin
	insert into error_log (counter, date_of_entry, message) values
		(error_log_counter.nextval, sysdate, 'COST_EXCEEDS_VALUE');
end;
/

--pause;