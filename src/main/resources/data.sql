
INSERT INTO USER(id, username) values
    (1, 'user 1');

INSERT INTO TODO_ITEM(id, title, description, user_id, CREATE_DATE) VALUES
    (1, 'Item 1', 'Todo item 1', 1, CURRENT_TIMESTAMP()),
    (2, 'Item 1', 'Todo item 1', 1, CURRENT_TIMESTAMP()),
    (3, 'Item 1', 'Todo item 1', 1, CURRENT_TIMESTAMP());