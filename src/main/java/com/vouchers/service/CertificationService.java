package com.vouchers.service;

import com.vouchers.dto.certification.CertificationSearchResultDTO;
import com.vouchers.dto.certification.CreateCertificationDTO;
import com.vouchers.mapper.CertificationMapper;
import com.vouchers.model.Certification;
import com.vouchers.repository.CertificationRepository;
import com.vouchers.validator.CertificationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
