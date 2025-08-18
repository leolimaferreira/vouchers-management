package com.vouchers.repository;

import com.vouchers.model.Certification;
import com.vouchers.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VoucherRepository extends JpaRepository<Voucher, UUID> {
    List<Voucher> findAllByCertification(Certification certification);
}
