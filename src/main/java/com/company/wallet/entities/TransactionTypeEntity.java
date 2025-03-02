package com.company.wallet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 *  Transaction type entity.
 *  <p>
 *  Type can be credit or debit ('C' and 'D' respectively )
 *  </p>
 *  @author Carliandro Cavalcanti
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "transaction_type")
@EntityListeners(AuditingEntityListener.class)
public class TransactionTypeEntity {

    @Setter
    @Id
    @Column(name = "id",nullable = false, unique = true)
    private String id;

    @Setter
    @Column(name = "description")
    private String description;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

}
