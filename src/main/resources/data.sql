INSERT INTO USER(id, username, password) VALUES 
	(1, 'user', '$2a$10$AnhdY3uwamZiovwu5MlgRucDb8LdxhM7w3ztmEi.0X6sM5.Rxz/nq'),
	(2, 'user2', '$2a$10$xeyk.bslG1VTxZhMNcBtJO6LUYc90WcHi0AuK67HeWJ40xR/KcRiS');
INSERT INTO TODO_ITEM(id, title, description, user_id, CREATE_DATE) VALUES
    (1, 'Item 1', 'Todo item 1', 1, CURRENT_TIMESTAMP()),
    (2, 'Item 1', 'Todo item 1', 1, CURRENT_TIMESTAMP()),
    (3, 'Item 1', 'Todo item 1', 1, CURRENT_TIMESTAMP());