/* Jonathan Mack
CS440
Final Project: table/type/view creation
12/7/07 */

create or replace type Animal_info_ty as object (
	BREED	varchar2(20),
	COUNT	number
);
/

create or replace type Animal_placement_ty as table of Animal_info_ty;
/

create or replace type Address_ty as object (
	PHONE		varchar2(15),
	STREET		varchar2(20),
	CITY		varchar2(20),
	STATE		char(2),
	ZIP			varchar2(10)
);
/

create or replace type Old_Password_ty as varray(5) of varchar2(20);
/

create or replace type person_ty as object (
	SSN					char(9),
	NAME				varchar2(20),
	ADDRESS				Address_ty,
	WEB_ID				varchar2(12),
	PASSWORD			varchar2(20),
	OLD_PASSWORDS		Old_Password_ty,
	member procedure 	CHANGE_PASSWORD (p_new varchar2),
	member function 	GET_CURRENT_PASSWORD return varchar2
);
/

create or replace type body person_ty as
	member procedure change_password(p_new varchar2) is
    begin
	if(p_new is null or length(p_new) < 5 or length(p_new) > 20 
		or upper(password) = upper(p_new)) then
			raise greenacres.password_change_failure;
	end if;
	
	if (old_passwords is not null and old_passwords.count > 0) then
		for i in 1 .. old_passwords.count loop
			if upper(p_new) = upper(old_passwords(i)) then
				raise greenacres.password_change_failure;
			end if;
		end loop;
	end if;

	if old_passwords is null then
		old_passwords := old_password_ty();
	end if;

	if old_passwords.count < 5 then
		old_passwords.extend();
	end if;

	for i in reverse 2 .. old_passwords.count loop
		old_passwords(i) := old_passwords(i-1);
	end loop;

	old_passwords(1) := password;
	password := p_new;
	end change_password;

    member function get_current_password return varchar2 is
    begin
        return password;
    end get_current_password;
end;
/

create or replace package GREENACRES as
	IMPROPER_VALUE				exception;
	PASSWORD_CHANGE_FAILURE		exception;

	function
		GET_PERSON_REF (p_person_name varchar2) return ref Person_ty;
end GREENACRES;
/

create table Personnel of person_ty;

create table Farms (
	ID		number(3)
		constraint Farms_ID_pk primary key deferrable initially immediate,
	NAME		varchar2(20),
	LATITUDE	number,
	LONGITUDE	number,
	MANAGER		ref person_ty
		constraint farms_manager_fk references personnel
			deferrable initially immediate
);

create table Plots (
	ID			char(8)
		constraint plots_id_pk primary key deferrable initially immediate,
	FARM_ID		number(3)
		constraint plots_farm_id_fk references farms(id)
			deferrable initially immediate,
	ACREAGE		number
		constraint plots_acreage_ck check (acreage > 0)
			deferrable initially immediate
		constraint plots_acreage_nn not null
			deferrable initially immediate,
	ANIMALS		animal_placement_ty)
	nested table animals store as animal_placement_tab;

create table Crops (
	NAME					varchar2(20)
		constraint crops_name_pk primary key 
			deferrable initially immediate,
	BUSHEL_PER_ACRE			number
		constraint crops_bushel_per_acre_nn not null
			deferrable initially immediate
		constraint crops_bushel_per_acre_ck check (bushel_per_acre >= 0)
			deferrable initially immediate,
	KCAL_PER_BUSHEL			number
		constraint crops_kcal_per_bushel_nn not null
			deferrable initially immediate
		constraint crops_kcal_per_bushel_ck check (kcal_per_bushel >= 0)
			deferrable initially immediate,
	COST_PER_BUSHEL			number(8,2)
		constraint crops_cost_per_bushel_nn not null
			deferrable initially immediate
		constraint crops_cost_per_bushel_ck check (cost_per_bushel >= 0)
			deferrable initially immediate,
	VALUE_PER_BUSHEL		number(8,2)
		constraint crops_value_per_bushel_nn not null
			deferrable initially immediate
		constraint crops_value_per_bushel_ck check (value_per_bushel >= 0)
			deferrable initially immediate 
);

create table Animals (
	BREED					varchar2(20)
		constraint animals_breed_pk primary key 
			deferrable initially immediate,
	ACREAGE_REQUIRED		number
		constraint animals_acreage_reqd_nn not null
			deferrable initially immediate
		constraint animals_acreage_reqd_ck check (acreage_required >= 0)
			deferrable initially immediate,
	CALORIC_REQUIREMENT		number
		constraint animals_caloric_req_nn not null
			deferrable initially immediate
		constraint animals_caloric_req_ck check (caloric_requirement >= 0)
			deferrable initially immediate,
	COST					number(8,2)
		constraint animals_cost_nn not null
			deferrable initially immediate
		constraint animals_cost_ck check (cost >= 0)
			deferrable initially immediate,
	VALUE					number(8,2)
		constraint animals_value_nn not null
			deferrable initially immediate
		constraint animals_value_ck check (value >= 0)
			deferrable initially immediate
);

pause;

create table Equipment (
	SERIALNUM		char(6)
		constraint equipment_serialnum_pk primary key
			deferrable initially immediate,
	BRAND			varchar2(20),
	TYPE			varchar2(30),
	CURRENT_PLOT	char(8)
		constraint equipment_current_plot_fk references plots(ID)
			deferrable initially immediate
);

create table Crop_Allocation (
	PLOT			char(8)
		constraint crop_allocation_plot_uq unique
			deferrable initially immediate
		constraint crop_allocation_plot_fk references plots(ID)
			deferrable initially immediate,
	CROP			varchar2(20)
		constraint crop_allocation_crop_fk references crops(NAME)
			deferrable initially immediate,
	ACREAGE			number
);

alter table Crop_Allocation add constraint crop_allocation_ck 
	primary key (PLOT, CROP) deferrable initially immediate;

alter table personnel
	add constraint personnel_SSN_pk primary key (SSN)
		deferrable initially immediate
	add constraint personnel_WEB_ID_uq unique (WEB_ID)
		deferrable initially immediate
	add constraint personnel_NAME_uq unique (NAME)
		deferrable initially immediate;

create table Error_log (
	counter				number
		constraint error_log_counter_pk primary key 
			deferrable initially immediate,
	Date_of_entry		date,
	Message				varchar2(400)
);

create sequence error_log_counter increment by 1 start with 1;

create or replace view Equipment_Location_By_Farm
	("Current Farm", "Equipment Type", "Serial Number") as
	select nvl(name, 'In Barn'), type, serialnum from 
	farms f, plots p, equipment e where
	f.id(+) = p.farm_id and e.current_plot = p.id(+)
	order by name nulls last, type;	

create or replace view potential_profit_by_plot ("Plot ID", 
	"Net Animal Profit", "Net Crop Profit", "Total Profit") as
select profit_per_acre.id, tap, ppa*(ta-ea), tap + ppa*(ta-ea) 
from
	(select id, sum(count*(value-cost-(value_per_bushel-cost_per_bushel)/
		kcal_per_bushel*caloric_requirement)) tap
	from plots p, table(animals) a, animals b, crops, crop_allocation ca
	where a.breed = b.breed and p.id = ca.plot and crops.name = ca.crop
	group by id) net_animal_profit,
	(select id, (value_per_bushel-cost_per_bushel)*bushel_per_acre ppa
	from plots p, crops, crop_allocation ca
	where p.id = ca.plot and crops.name = ca.crop) profit_per_acre,
	(select id, sum(count*caloric_requirement/kcal_per_bushel/
		bushel_per_acre) ea
	from plots p, table(animals) a, animals b, crops, crop_allocation ca
	where a.breed = b.breed and p.id = ca.plot and crops.name = ca.crop
	group by id) eaten_acreage,
	(select id, ca.acreage ta from 
	plots p, crop_allocation ca where p.id = ca.plot) total_acreage
where
	profit_per_acre.id = eaten_acreage.id and 
	eaten_acreage.id = total_acreage.id and 
	total_acreage.id = net_animal_profit.id;

grant all on farms to raymond;
grant all on plots to raymond;
grant all on crops to raymond;
grant all on animals to raymond;
grant all on equipment to raymond;
grant all on crop_allocation to raymond;
grant all on personnel to raymond;
grant all on error_log to raymond;
grant all on potential_profit_by_plot to raymond;
grant all on equipment_location_by_farm to raymond;

grant execute on greenacres to raymond;
grant execute on animal_info_ty to raymond;
grant execute on animal_placement_ty to raymond;
grant execute on address_ty to raymond;
grant execute on person_ty to raymond;
grant execute on old_password_ty to raymond;

show errors;

pause;