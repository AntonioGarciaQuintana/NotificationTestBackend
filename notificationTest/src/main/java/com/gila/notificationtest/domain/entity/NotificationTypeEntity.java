package com.gila.notificationtest.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notification_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationTypeEntity {

    @Id
    @Column(nullable = false)
    private Short id;

    @Column(nullable = false, unique = true, length = 30)
    private String code;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    private Boolean active;
}
