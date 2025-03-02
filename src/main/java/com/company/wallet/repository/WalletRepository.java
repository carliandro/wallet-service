package com.company.wallet.repository;

import com.company.wallet.entities.WalletEntity;
import com.company.wallet.exceptions.WalletException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Wallet JPA repository
 * <p> Generates SQL queries to access the database to manage Wallet entities</p>
 * @author Carliandro Cavalcanti
 */
@Transactional(rollbackOn = WalletException.class)
public interface WalletRepository extends JpaRepository<WalletEntity, Integer> {
    List<WalletEntity> findAllByOrderByIdAsc();
    List<WalletEntity> findByUserId(String userId);

}