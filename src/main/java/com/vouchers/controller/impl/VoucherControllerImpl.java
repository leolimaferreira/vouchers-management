package com.vouchers.controller.impl;

import com.vouchers.controller.GenericController;
import com.vouchers.dto.voucher.CreateVoucherDTO;
import com.vouchers.dto.voucher.VoucherSearchResultDTO;
import com.vouchers.service.VoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/vouchers")
@RequiredArgsConstructor
public class VoucherControllerImpl implements GenericController {

    private final VoucherService voucherService;

    @PostMapping
    public ResponseEntity<VoucherSearchResultDTO> create(@RequestBody @Valid CreateVoucherDTO dto) {
        VoucherSearchResultDTO voucher = voucherService.createVoucher(dto);
        URI location = generateHeaderLocation(voucher.id());
        return ResponseEntity.created(location)
                .body(voucher);
    }
}
