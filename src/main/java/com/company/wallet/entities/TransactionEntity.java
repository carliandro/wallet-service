package com.company.wallet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  Transaction entity.
 *
 *  @author Carliandro Cavalcanti
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transaction")
@EntityListeners(AuditingEntityListener.class)
public class TransactionEntity {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Transaction globalId must not be empty")
    @NotNull(message = "Transaction globalId must be provided")
    @Column(name = "global_id", unique = true, nullable = false)
    private String globalId;

    @NotNull(message = "Trnansaction typeId must be provided")
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TransactionTypeEntity type;

    @NotNull(message = "Transaction amount must be provided")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @NotNull(message = "Transaction wallet must be provided")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private WalletEntity wallet;

    @NotNull(message = "Transaction currency must be provided")
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;

    @Column(name = "description")
    String description;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date lastUpdated;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    public TransactionEntity(String globalId, TransactionTypeEntity type, BigDecimal amount, WalletEntity wallet, CurrencyEntity currency, String description) {
        this.globalId = globalId;
        this.type = type;
        this.amount = amount;
        this.wallet = wallet;
        this.currency = currency;
        this.description = description;
        this.lastUpdated = new Date();
    }

    public TransactionEntity(String globalId, TransactionTypeEntity type, BigDecimal amount, WalletEntity wallet, CurrencyEntity currency, String description, String lastUpdatedBy) {
        this(globalId, type, amount, wallet, currency, description);
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
