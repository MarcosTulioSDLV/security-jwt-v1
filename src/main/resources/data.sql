
INSERT INTO TB_ROLE (role_name) VALUES
 ('ROLE_ADMIN'),
 ('ROLE_USER');

INSERT INTO TB_USER (username,password) VALUES
('marcos', '$2a$10$lxnVhZbwaFkC4RE9chB1SeP9VyiEyEgJL6yBbyzR1e.LsWUbbCIvK'),
('maria', '$2a$10$lxnVhZbwaFkC4RE9chB1SeP9VyiEyEgJL6yBbyzR1e.LsWUbbCIvK');

-- For Relationship between Users and Roles
INSERT INTO TB_USER_ROLE (user_id, role_id) VALUES
((SELECT id FROM TB_USER WHERE username = 'marcos'),
(SELECT id FROM TB_ROLE WHERE role_name = 'ROLE_ADMIN'));

INSERT INTO TB_USER_ROLE (user_id, role_id) VALUES
((SELECT id FROM TB_USER WHERE username = 'maria'),
(SELECT id FROM TB_ROLE WHERE role_name = 'ROLE_USER'));

INSERT INTO TB_PRODUCT (product_code,name,price,country,creation_date) VALUES
('123','Pc',1000,'Colombia',CURRENT_TIMESTAMP),
('124','Tv',1200,'USA',CURRENT_TIMESTAMP),
('125','Ball',100,'Brazil',CURRENT_TIMESTAMP);