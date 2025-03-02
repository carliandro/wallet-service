package com.company.wallet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 *  Currency entity.
 *
 *  @author Carliandro Cavalcanti
 */
@Entity
@NoArgsConstructor

@Setter
@Getter
@Table(name = "currency")
@EntityListeners(AuditingEntityListener.class)
public class CurrencyEntity {

    @Id
    @Column(name = "id",nullable = false)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

}
