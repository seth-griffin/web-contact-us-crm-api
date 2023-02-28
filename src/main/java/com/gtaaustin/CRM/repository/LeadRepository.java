package com.gtaaustin.CRM.repository;

import com.gtaaustin.CRM.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.ArrayList;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    @Query(value = "from Lead t where remoteHost = :remoteHost AND createDate BETWEEN :startDate AND :endDate")
    public ArrayList<Lead> getAllBetweenDates(@Param("remoteHost")String remoteHost, @Param("startDate")Date startDate, @Param("endDate")Date endDate);
}
