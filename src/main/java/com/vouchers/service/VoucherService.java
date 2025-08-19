package com.vouchers.service;

import com.vouchers.dto.voucher.CreateVoucherDTO;
import com.vouchers.dto.voucher.UpdateVoucherDTO;
import com.vouchers.dto.voucher.VoucherSearchResultDTO;
import com.vouchers.exception.NotFoundException;
import com.vouchers.mapper.VoucherMapper;
import com.vouchers.model.Voucher;
import com.vouchers.model.enums.Status;
import com.vouchers.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public VoucherSearchResultDTO findVoucherById(UUID id) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Voucher not found"));
        return voucherMapper.toVoucherSearchResultDTO(voucher);
    }

    public List<VoucherSearchResultDTO> findAllVouchers() {
        return voucherRepository.findAll()
                .stream()
                .map(voucherMapper::toVoucherSearchResultDTO)
                .toList();
    }

    public void updateVoucher(UUID id, UpdateVoucherDTO dto) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Voucher not found"));
        voucher.setStatus(dto.status());
        voucherRepository.save(voucher);
    }

    public void deleteVoucher(UUID id) {
        Voucher voucher = voucherRepository.findById(id).orElseThrow(() -> new NotFoundException("Voucher not found"));
        voucher.setStatus(Status.CANCELLED);
        voucherRepository.save(voucher);
    }
}
