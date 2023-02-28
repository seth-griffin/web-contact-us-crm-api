package com.gtaaustin.CRM.repository;

import com.gtaaustin.CRM.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByCodeKey(String codeKey);

}
