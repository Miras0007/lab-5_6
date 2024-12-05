INSERT INTO users (username, email, password_hash, role_id)
VALUES (
    'admin',
    'admin@example.com',
    '$2a$10$DowJonesIndexExampleHashForPassword12345', -- Замените на реальный BCrypt-хэш пароля
    (SELECT role_id FROM roles WHERE name = 'ADMIN')
);
