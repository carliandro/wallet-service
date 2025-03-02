package com.company.wallet.service;

import com.company.wallet.entities.WalletEntity;
import com.company.wallet.exceptions.WalletException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Service for managing wallets
 * @author Carliandro Cavalcanti
 */
public interface WalletService {
    List<WalletEntity> findAll() throws WalletException;
    WalletEntity findById(@NotNull Integer id) throws WalletException;
    List<WalletEntity> findByUserId(@NotBlank String userId) throws WalletException;
    WalletEntity createWallet(@NotBlank String userId, @NotBlank String currencyName) throws WalletException;
    WalletEntity updateWalletAmount(@NotNull WalletEntity wallet, @NotBlank String amount, @NotNull Boolean isCredit) throws WalletException;

}