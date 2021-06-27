--PARAM CONSTANTS
insert into param_type_clt values (param_type_seq.nextval, current_timestamp(), 'WGT', 'Parcel weight in kilograms');
insert into param_type_clt values (param_type_seq.nextval, current_timestamp(), 'VOL', 'Parcel volume in cubic centimeter');

--RELATIONAL OPERATION CONSTANTS
insert into relational_operation_clt values (rel_operation_clt_seq.nextval, current_timestamp(), 'LT', 'Less than');
insert into relational_operation_clt values (rel_operation_clt_seq.nextval, current_timestamp(), 'GT', 'Greater than');
insert into relational_operation_clt values (rel_operation_clt_seq.nextval, current_timestamp(), 'GE', 'Greater than OR equal');
insert into relational_operation_clt values (rel_operation_clt_seq.nextval, current_timestamp(), 'LE', 'Less than OR equal');

--ARITHMETIC OPERATION CONSTANTS
insert into arithmetic_operation_clt values (arith_operation_clt_seq.nextval, current_timestamp(), 'ADD', 'Addition');
insert into arithmetic_operation_clt values (arith_operation_clt_seq.nextval, current_timestamp(), 'SUB', 'Subtraction');
insert into arithmetic_operation_clt values (arith_operation_clt_seq.nextval, current_timestamp(), 'MUL', 'Multiplication');
insert into arithmetic_operation_clt values (arith_operation_clt_seq.nextval, current_timestamp(), 'DIV', 'Division');

--CONDITIONS
insert into condition values (condition_seq.nextval, current_timestamp(), 50, 1, 2);
insert into condition values (condition_seq.nextval, current_timestamp(), 10, 1, 2);
insert into condition values (condition_seq.nextval, current_timestamp(), 1500, 2, 1);
insert into condition values (condition_seq.nextval, current_timestamp(), 2500, 2, 1);
insert into condition values (condition_seq.nextval, current_timestamp(), 2500, 2, 3);

--COST EXPRESSIONS
insert into cost_expression values (cost_expression_seq.nextval, current_timestamp(), 20, 3, 1);
insert into cost_expression values (cost_expression_seq.nextval, current_timestamp(), 0.03, 3, 2);
insert into cost_expression values (cost_expression_seq.nextval, current_timestamp(), 0.04, 3, 2);
insert into cost_expression values (cost_expression_seq.nextval, current_timestamp(), 0.05, 3, 2);

--RULES
insert into rule values (rule_seq.nextval, 1, 1, 'Reject', 1, null);
insert into rule values (rule_seq.nextval, 1, 2, 'Heavy Parcel', 2, 1);
insert into rule values (rule_seq.nextval, 1, 3, 'Small Parcel', 3, 2);
insert into rule values (rule_seq.nextval, 1, 4, 'Medium Parcel', 4, 3);
insert into rule values (rule_seq.nextval, 1, 5, 'Large Parcel', 5, 4);