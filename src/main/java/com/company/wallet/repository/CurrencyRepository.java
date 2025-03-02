package com.company.wallet.repository;

import com.company.wallet.entities.CurrencyEntity;
import com.company.wallet.exceptions.WalletException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Currency JPA repository
 * <p> Generates SQL queries to access the database to manage Currency entities</p>
 * @author Carliandro Cavalcanti
 */
@Transactional(rollbackOn = WalletException.class)
public interface CurrencyRepository  extends JpaRepository<CurrencyEntity, Integer> {
    CurrencyEntity findByName(String name);
}