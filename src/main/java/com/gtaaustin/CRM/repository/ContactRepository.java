package com.gtaaustin.CRM.repository;

import com.gtaaustin.CRM.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Topic, Long> {

}
