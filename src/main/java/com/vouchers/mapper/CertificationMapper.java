package com.vouchers.mapper;

import com.vouchers.dto.certification.CertificationSearchResultDTO;
import com.vouchers.dto.certification.CreateCertificationDTO;
import com.vouchers.model.Certification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificationMapper {

    Certification toCertification(CreateCertificationDTO dto);

    CertificationSearchResultDTO toCertificationSearchResultDTO(Certification certification);
}
