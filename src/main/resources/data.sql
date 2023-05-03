insert into brand(name, country)
values ('Fender', 'USA');
insert into brand(name, country)
values ('Gibson', 'USA');
insert into brand(name, country)
values ('Ibanez', 'Mexico');

insert into guitar(brand_id, model, price)
values (1, 'Stratocaster', 1799.00);
insert into guitar(brand_id, model, price)
values (1, 'Telecaster', 1599.00);
insert into guitar(brand_id, model, price)
values (2, 'Les Paul', 2999.00);

insert into stock_item(id, guitar_id, quantity)
values (NEXT VALUE FOR STOCKITEM_SEQ, 1, random() * 15);
insert into stock_item(id, guitar_id, quantity)
values (NEXT VALUE FOR STOCKITEM_SEQ, 2, random() * 15);
insert into stock_item(id, guitar_id, quantity)
values (NEXT VALUE FOR STOCKITEM_SEQ, 3, random() * 15);

