insert into Users (id, username, password, is_Employee) values (default, 'admin', '$2a$10$umyl5zPAMwAfxvkf4XgIVuzyXXQWx0rCwJ6FGd0pT0DeMJy.uM2V.', default)
insert into Users (id, username, password, is_Employee) values (default, 'root', '$2a$10$M9krDP7xVlsVCMi.DfAiK.c6JwxjJIYVvCYI/U5T1UMdj8RR.piYi', default)
insert into Users (id, username, password, is_Employee) values (default, 'guest', '$2a$10$M9krDP7xVlsVCMi.DfAiK.c6JwxjJIYVvCYI/U5T1UMdj8RR.piYi', default)
insert into Users (id, username, password, is_Employee) values (default, 'employee', '$2a$10$M9krDP7xVlsVCMi.DfAiK.c6JwxjJIYVvCYI/U5T1UMdj8RR.piYi', TRUE)

insert into Wallet(id, address, balance, owner) values (default, 'AAAAAAAAAAA', 19.8998, 3)
insert into Wallet(id, address, balance, owner) values (default, 'ROOOOOOOOOT', 45.8999, 2)

insert into Product(id, name, stock, id_Seller, category, price, description) values (default, 'Kidney', 2, 3, 'Organs', 0.50000000, 'Well preserved kidneys')
insert into Product(id, name, stock, id_Seller, category, price, description) values (default, 'Marijuana', 19, 3, 'Drugs', 0.00000030, 'Marijuana from Malanje')
insert into Product(id, name, stock, id_Seller, category, price, description) values (default, 'Eye', 1, 2, 'Organs', 0.50000000, 'Blue eye from a kid')
insert into Product(id, name, stock, id_Seller, category, price, description) values (default, 'Non licensed shotgun', 2, 3, 'Weapons', 0.93000000, default)