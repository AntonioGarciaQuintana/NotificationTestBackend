package com.gila.notificationtest.repository;

import com.gila.notificationtest.domain.entity.SentNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SentNotificationRepository extends JpaRepository<SentNotificationEntity, Long> {

    List<SentNotificationEntity> findAllByOrderByTimestampDesc();
}
