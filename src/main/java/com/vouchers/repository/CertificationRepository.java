package com.vouchers.repository;

import com.vouchers.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CertificationRepository extends JpaRepository<Certification, UUID> {
    Optional<Certification> findByCode(String code);
}
