package com.gtaaustin.CRM.repository;

import com.gtaaustin.CRM.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    Template findByCodeKey(String codeKey);
}
