package com.gila.notificationtest.repository;

import com.gila.notificationtest.domain.entity.MessageCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageCategoryRepository extends JpaRepository<MessageCategoryEntity, Short> {
    Optional<MessageCategoryEntity> findByCode(String code);
    List<MessageCategoryEntity> findByActiveTrue();
}
