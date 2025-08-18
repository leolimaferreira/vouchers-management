package com.vouchers.validator;

import com.vouchers.exception.DuplicatedRegistryException;
import com.vouchers.model.Certification;
import com.vouchers.model.Voucher;
import com.vouchers.repository.CertificationRepository;
import com.vouchers.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CertificationValidator {

    private final CertificationRepository certificationRepository;
    private final VoucherRepository voucherRepository;

    public void validateCreationAndUpdate(Certification certification) {
        if (isDuplicateCertification(certification)) {
            throw new DuplicatedRegistryException("Certification already registered");
        }
    }

    public void validateDeletion(Certification certification) {
        if (hasAssociatedVoucher(certification)) {
            throw new DuplicatedRegistryException("Certification cannot be deleted because it is associated with a voucher");
        }
    }

    private boolean isDuplicateCertification(Certification certification) {
        Optional<Certification> certificationOptional = certificationRepository.findByCode(certification.getCode());
        if (certification.getId() == null) {
            return certificationOptional.isPresent();
        }
        return certificationOptional.isPresent() && !certification.getId().equals(certificationOptional.get().getId());
    }

    private boolean hasAssociatedVoucher(Certification certification) {
        List<Voucher> vouchers = voucherRepository.findAllByCertification(certification);
        return !vouchers.isEmpty();
    }
}
