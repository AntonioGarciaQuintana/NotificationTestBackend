CREATE TABLE notification_type (
    id SMALLINT UNSIGNED NOT NULL,
    code VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    UNIQUE KEY uk_notification_type_code (code)
);

CREATE TABLE message_category (
    id SMALLINT UNSIGNED NOT NULL,
    code VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    UNIQUE KEY uk_message_category_code (code)
);
