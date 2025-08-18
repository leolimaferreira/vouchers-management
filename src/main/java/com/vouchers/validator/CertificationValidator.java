package com.vouchers.validator;

import com.vouchers.exception.DuplicatedRegistryException;
import com.vouchers.model.Certification;
import com.vouchers.model.User;
import com.vouchers.repository.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CertificationValidator {

    private final CertificationRepository certificationRepository;

    public void validateCreationAndUpdate(Certification certification) {
        if (isDuplicateUser(certification)) {
            throw new DuplicatedRegistryException("Certification already registered");
        }
    }

    private boolean isDuplicateUser(Certification certification) {
        Optional<Certification> certificationOptional = certificationRepository.findByCode(certification.getCode());
        if (certification.getId() == null) {
            return certificationOptional.isPresent();
        }
        return certificationOptional.isPresent() && !certification.getId().equals(certificationOptional.get().getId());
    }
}
