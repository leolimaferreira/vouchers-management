package com.vouchers.service;

import com.vouchers.dto.voucher.CreateVoucherDTO;
import com.vouchers.dto.voucher.VoucherSearchResultDTO;
import com.vouchers.mapper.VoucherMapper;
import com.vouchers.model.Voucher;
import com.vouchers.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoucherService {

    private final VoucherRepository voucherRepository;
    private final VoucherMapper voucherMapper;

    public VoucherSearchResultDTO createVoucher(CreateVoucherDTO dto) {
        Voucher voucher = voucherMapper.toVoucher(dto);
        voucherRepository.save(voucher);
        return voucherMapper.toVoucherSearchResultDTO(voucher);
    }
}
