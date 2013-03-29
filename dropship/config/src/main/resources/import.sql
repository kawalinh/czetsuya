--users
insert into CRM_USERS (id, disabled, created, firstname, lastname, password, salt, username) values 
(1, false, '2012-10-05 13:33:11.92', 'Edward', 'Legaspi', 'ef215432900e9484a0ef819197d650df96c7891bcdf45991b930b5293ee29729', 'czetsuya@gmail.com-1350277238', 'czetsuya@gmail.com');
insert into CRM_USERS (id, disabled, created, firstname, lastname, password, salt, username) values 
(2, false, '2012-10-05 13:33:11.92', 'Edward', 'Legaspi', 'bf29b3a2fbf73947df02c8b6a1ff6535e8040b288abab7396ab9b0c954675ddc', 'legaspi_edward@secure-id-online.com-1350277306', 'legaspi_edward@secure-id-online.com');

--roles
insert into CRM_ROLES (id, name, description) values (1, 'administrator', 'Administration');
insert into CRM_ROLES (id, name, description) values (2, 'merchant', 'Merchant');

--user roles
insert into CRM_USER_ROLES (user_id, role_id) values (1, 1);
insert into CRM_USER_ROLES (user_id, role_id) values (2, 2);

--permissions
insert into CRM_PERMISSIONS (id, action, role) values (1, 'administrator', 'administrator');
insert into CRM_PERMISSIONS (id, action, role) values (2, 'merchant', 'merchant');

--block website
insert into CRM_BLOCK_WEBSITES (id, domain) values (1, 'google.com');
insert into CRM_BLOCK_WEBSITES (id, domain) values (2, 'hotmail.com');
insert into CRM_BLOCK_WEBSITES (id, domain) values (3, 'aol.com');
insert into CRM_BLOCK_WEBSITES (id, domain) values (4, 'doba.com');

insert into CRM_ADDRESS_COUNTRIES (id, iso_code, name) values (1, 'PH', 'Philippines');
insert into CRM_ADDRESS_COUNTRIES (id, iso_code, name) values (2, 'US', 'USA');

insert into CRM_MEMBERSHIP_PLANS (id, code, description, long_description, fee, duration) VALUES (1, 'SI', 'Silver - 90 Days Membership', 'SILVER Term: Three (3) Months - Short Term Financial Needs', 89.00, 90);
insert into CRM_MEMBERSHIP_PLANS (id, code, description, long_description, fee, duration) VALUES (2, 'GO', 'Gold - 1 Year Membership', 'GOLD Term: One (1) Year - Full Year Term Financial Needs', 289.00, 366);
insert into CRM_MEMBERSHIP_PLANS (id, code, description, long_description, fee, duration) VALUES (3, 'PL', 'Platinum - 5 Year Membership', 'PLATINUM Term: 5 Year Membership - Limited Exclusive Membership Only', 498.00, 1830);

insert into CRM_PROMO_CODES (id, code, discount, duration, expiry_date, material, start_date, type, disabled, created) values (1, 'edward1234', 100, 30, '2012-12-31 00:00:00.000', 'exodus', '2012-11-01 00:00:00.000', 'NONE', false, '2012-11-01 00:00:00.000');
insert into CRM_PROMO_CODES (id, code, discount, duration, expiry_date, material, start_date, type, disabled, created) values (2, 'edward1235', 100, 30, '2012-11-05 00:00:00.000', 'exodus', '2012-11-01 00:00:00.000', 'NONE', false, '2012-11-01 00:00:00.000');

insert into CRM_MARKETING_CODES (id, code, duration, expiry_date, start_date, disabled, created) values (1, 'edward1234', 30, '2012-12-31 00:00:00.000', '2012-11-01 00:00:00.000', false, '2012-11-01 00:00:00.000');

insert into CRM_SUBSCRIPTIONS
(id, version, amount, business_plan, business_program, discount, duration, promo_code, user_id)
values
(nextval('crm_subscriptions_seq'), 0, 10, 'SI', 'DS', 100, 10, 'edward1234', 4);