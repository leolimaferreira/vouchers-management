package com.vouchers.controller.impl;

import com.vouchers.controller.GenericController;
import com.vouchers.dto.voucher.CreateVoucherDTO;
import com.vouchers.dto.voucher.UpdateVoucherDTO;
import com.vouchers.dto.voucher.VoucherSearchResultDTO;
import com.vouchers.service.VoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<VoucherSearchResultDTO> findById(@PathVariable UUID id) {
        VoucherSearchResultDTO voucher = voucherService.findVoucherById(id);
        return ResponseEntity.ok(voucher);
    }

    @GetMapping
    public ResponseEntity<List<VoucherSearchResultDTO>> findAll() {
        List<VoucherSearchResultDTO> vouchers = voucherService.findAllVouchers();
        return ResponseEntity.ok(vouchers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoucherSearchResultDTO> update(@PathVariable UUID id, @RequestBody @Valid UpdateVoucherDTO dto) {
        voucherService.updateVoucher(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        voucherService.deleteVoucher(id);
        return ResponseEntity.noContent().build();
    }
}
