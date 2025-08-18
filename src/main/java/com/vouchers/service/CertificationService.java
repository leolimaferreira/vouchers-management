package com.vouchers.service;

import com.vouchers.dto.certification.CertificationSearchResultDTO;
import com.vouchers.dto.certification.CreateCertificationDTO;
import com.vouchers.dto.user.UpdateCertificationDTO;
import com.vouchers.exception.NotFoundException;
import com.vouchers.mapper.CertificationMapper;
import com.vouchers.model.Certification;
import com.vouchers.repository.CertificationRepository;
import com.vouchers.validator.CertificationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final CertificationMapper certificationMapper;
    private final CertificationValidator certificationValidator;

    public CertificationSearchResultDTO createCertification(CreateCertificationDTO dto) {
        Certification certification = certificationMapper.toCertification(dto);
        certificationValidator.validateCreationAndUpdate(certification);
        certificationRepository.save(certification);
        return certificationMapper.toCertificationSearchResultDTO(certification);
    }

    public CertificationSearchResultDTO findCertificationById(UUID id) {
        Certification certification = certificationRepository.findById(id).orElseThrow( () -> new NotFoundException("Certification with ID: " + id + " not found"));
        return certificationMapper.toCertificationSearchResultDTO(certification);
    }

    public List<CertificationSearchResultDTO> findAllCertifications() {
        return certificationRepository.findAll()
                .stream()
                .map(certificationMapper::toCertificationSearchResultDTO)
                .toList();
    }

    public void updateCertification(UUID id, UpdateCertificationDTO dto) {
        Certification certification = certificationRepository.findById(id).orElseThrow( () -> new NotFoundException("Certification with ID: " + id + " not found"));
        certificationMapper.updateCertificationFromDto(dto, certification);
        certificationValidator.validateCreationAndUpdate(certification);
        certificationRepository.save(certification);
    }

    public void deleteCertification(UUID id) {
        Certification certification = certificationRepository.findById(id).orElseThrow( () -> new NotFoundException("Certification with ID: " + id + " not found"));
        certificationValidator.validateDeletion(certification);
        certificationRepository.delete(certification);
    }
}
