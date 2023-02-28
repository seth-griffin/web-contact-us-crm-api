package com.gtaaustin.CRM.repository;

import com.gtaaustin.CRM.entity.Intent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IntentRepository extends JpaRepository<Intent, Long> {

}
