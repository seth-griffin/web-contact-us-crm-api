package com.gtaaustin.CRM.repository;

import com.gtaaustin.CRM.entity.BlacklistedHost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistedHostRepository extends JpaRepository<BlacklistedHost, Long> {
    Optional<BlacklistedHost> findByRemoteHost(String remoteHost);
}
