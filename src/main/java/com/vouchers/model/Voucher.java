package com.vouchers.model;

import com.vouchers.model.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vouchers")
@Getter
@Setter
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "supplier", unique = false, length = 100)
    private String supplier;

    @ManyToOne
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @Column(name = "value", unique = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(name = "expiration_date", unique = false)
    private LocalDate expirationDate = LocalDate.now().plusDays(1);

    @Column(name = "rules", unique = false, length = 1000)
    private List<String> rules;

    @Enumerated(EnumType.STRING)
    private Status status;
}
