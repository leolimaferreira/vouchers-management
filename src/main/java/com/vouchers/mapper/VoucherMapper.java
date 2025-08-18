package com.vouchers.mapper;

import com.vouchers.dto.voucher.CreateVoucherDTO;
import com.vouchers.dto.voucher.VoucherSearchResultDTO;
import com.vouchers.model.Voucher;
import com.vouchers.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class VoucherMapper {

    @Autowired
    CertificationRepository certificationRepository;

    @Mapping(target = "certification", expression = "java(certificationRepository.findById(dto.certificationId()).orElse(null))")
    public abstract Voucher toVoucher(CreateVoucherDTO dto);

    public abstract VoucherSearchResultDTO toVoucherSearchResultDTO(Voucher voucher);
}
