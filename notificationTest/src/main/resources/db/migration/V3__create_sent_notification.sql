CREATE TABLE sent_notification (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    notification_type_id SMALLINT UNSIGNED NOT NULL,
    message_category_id SMALLINT UNSIGNED NOT NULL,

    message VARCHAR(500) NOT NULL,

    user_id BIGINT NULL,
    user_name VARCHAR(100) NULL,
    user_email VARCHAR(150) NULL,
    user_phone VARCHAR(20) NULL,

    delivered BOOLEAN NOT NULL DEFAULT FALSE,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sent_notification_type
        FOREIGN KEY (notification_type_id)
        REFERENCES notification_type(id),

    CONSTRAINT fk_sent_notification_category
        FOREIGN KEY (message_category_id)
        REFERENCES message_category(id),

    INDEX idx_sent_notification_type (notification_type_id),
    INDEX idx_sent_notification_category (message_category_id),
    INDEX idx_sent_notification_user (user_id),
    INDEX idx_sent_notification_timestamp (timestamp)
);
