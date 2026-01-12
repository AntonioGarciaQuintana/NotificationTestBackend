package com.gila.notificationtest.repository;

import com.gila.notificationtest.domain.entity.NotificationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationTypeEntity, Short> {
    Optional<NotificationTypeEntity> findByCode(String code);
}
