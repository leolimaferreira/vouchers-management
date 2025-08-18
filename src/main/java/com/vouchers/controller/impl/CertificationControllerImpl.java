package com.vouchers.controller.impl;

import com.vouchers.controller.GenericController;
import com.vouchers.dto.certification.CertificationSearchResultDTO;
import com.vouchers.dto.certification.CreateCertificationDTO;
import com.vouchers.service.CertificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certifications")
@RequiredArgsConstructor
public class CertificationControllerImpl implements GenericController {

    private final CertificationService certificationService;

    @PostMapping
    public ResponseEntity<CertificationSearchResultDTO> create(@RequestBody @Valid CreateCertificationDTO dto) {
        return ResponseEntity.ok(certificationService.createCertification(dto));
    }
}
