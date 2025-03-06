package com.company.wallet.repository;

import com.company.wallet.entities.TransactionEntity;
import com.company.wallet.entities.WalletEntity;
import com.company.wallet.exceptions.WalletException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Transaction JPA repository
 *  <p> Generates SQL queries to access the database to manage Transaction entities</p>
 * @author Carliandro Cavalcanti
 */
@Transactional(rollbackOn = WalletException.class)
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findByWallet(WalletEntity wallet);
    TransactionEntity findByGlobalId(String globalId);
    List<TransactionEntity> findAllByLastUpdatedBetween(Date lastUpdatedStart, Date lastUpdatedEnd);
}
