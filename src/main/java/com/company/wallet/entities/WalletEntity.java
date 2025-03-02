package com.company.wallet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *  Wallet entity.
 *
 *  @author Carliandro Cavalcanti
 */

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
@EntityListeners(AuditingEntityListener.class)
public class WalletEntity {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User Id must be provided")
    @Column(name = "user_id")
    private String userId;

    @Min(0)
    @Column(name = "balance",nullable = false)
    @NotNull(message = "Wallet balance must be provided")
        private BigDecimal balance;

    @NotNull(message = "Wallet currency must be provided")
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    @OneToMany(mappedBy = "wallet", fetch = FetchType.LAZY)
    private List<TransactionEntity> transactions;

    public WalletEntity(String userId, CurrencyEntity currency, BigDecimal balance) {
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;
        this.lastUpdated = new Date();
    }

    public WalletEntity(String userId, CurrencyEntity currency, BigDecimal balance, String lastUpdatedBy) {
        this(userId, currency,balance);
        this.lastUpdatedBy = lastUpdatedBy;
    }


}
