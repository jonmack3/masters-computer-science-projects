/* Jonathan Mack
CS440
Final Project: delete required tables/types/constraints
12/7/07 */

clear col;

alter table farms drop constraint farms_manager_fk;
alter table plots drop constraint plots_farm_id_fk;
alter table equipment drop constraint equipment_current_plot_fk;
alter table crop_allocation drop constraint crop_allocation_plot_fk;
alter table crop_allocation drop constraint crop_allocation_crop_fk;

drop sequence error_log_counter;

drop view potential_profit_by_plot;
drop view equipment_location_by_farm;

drop table Farms;
drop table Plots;
drop table Crops;
drop table Animals;
drop table Equipment;
drop table Crop_Allocation;
drop table Personnel;
drop table Error_log;

drop type animal_placement_ty;
drop type animal_info_ty;
drop type person_ty;
drop type address_ty;
drop type old_password_ty;

--pause;