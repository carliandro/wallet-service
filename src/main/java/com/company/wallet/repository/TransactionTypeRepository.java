package com.company.wallet.repository;

import com.company.wallet.entities.TransactionTypeEntity;
import com.company.wallet.exceptions.WalletException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Transaction type JPA repository
 *  <p> Generates SQL queries to access the database to manage TransactionType entities</p>
 * @author Carliandro Cavalcanti
 */
@Transactional(rollbackOn = WalletException.class)
public interface TransactionTypeRepository extends JpaRepository<TransactionTypeEntity, String> {
}
