package com.vouchers.mapper;

import com.vouchers.dto.certification.CertificationSearchResultDTO;
import com.vouchers.dto.certification.CreateCertificationDTO;
import com.vouchers.dto.user.UpdateCertificationDTO;
import com.vouchers.model.Certification;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CertificationMapper {

    Certification toCertification(CreateCertificationDTO dto);

    CertificationSearchResultDTO toCertificationSearchResultDTO(Certification certification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCertificationFromDto(UpdateCertificationDTO dto, @MappingTarget Certification certification);
}
