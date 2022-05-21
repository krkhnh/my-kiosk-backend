INSERT INTO MENU_CATEGORY (ID, NAME)
VALUES (1, '음료'),
       (2, '단품');

INSERT INTO MENU(NAME, PRICE)
VALUES ('콜라', 900),
       ('사이다', 900),
       ('햄버거', 3000),
       ('치킨', 3900);

INSERT INTO "menu-menu_category" (MENU_ID, MENU_CATEGORY_ID)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2);
