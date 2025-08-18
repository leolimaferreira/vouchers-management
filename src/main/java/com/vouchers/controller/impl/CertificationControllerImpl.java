package com.vouchers.controller.impl;

import com.vouchers.controller.GenericController;
import com.vouchers.dto.certification.CertificationSearchResultDTO;
import com.vouchers.dto.certification.CreateCertificationDTO;
import com.vouchers.dto.user.UpdateCertificationDTO;
import com.vouchers.service.CertificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/certifications")
@RequiredArgsConstructor
public class CertificationControllerImpl implements GenericController {

    private final CertificationService certificationService;

    @PostMapping
    public ResponseEntity<CertificationSearchResultDTO> create(@RequestBody @Valid CreateCertificationDTO dto) {
        CertificationSearchResultDTO certification = certificationService.createCertification(dto);
        URI location = generateHeaderLocation(certification.id());
        return ResponseEntity.created(location)
                .body(certification);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificationSearchResultDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(certificationService.findCertificationById(id));
    }

    @GetMapping
    public ResponseEntity<List<CertificationSearchResultDTO>> findAll() {
        return ResponseEntity.ok(certificationService.findAllCertifications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody @Valid UpdateCertificationDTO dto) {
        certificationService.updateCertification(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        certificationService.deleteCertification(id);
        return ResponseEntity.noContent().build();
    }
}
