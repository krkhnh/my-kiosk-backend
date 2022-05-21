INSERT INTO MENU_CATEGORY (ID, NAME)
VALUES (1, '음료'),
       (2, '단품');

INSERT INTO MENU(NAME, PRICE, MENU_CATEGORY_ID)
VALUES ('콜라', 900, 1),
       ('사이다', 900, 1),
       ('햄버거', 3000, 2),
       ('치킨', 3900, 2);
