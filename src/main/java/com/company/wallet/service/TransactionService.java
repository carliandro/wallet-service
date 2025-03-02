package com.company.wallet.service;

import com.company.wallet.entities.TransactionEntity;
import com.company.wallet.exceptions.WalletException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Service for managing transactions
 * @author Carliandro Cavalcanti
 */
public interface TransactionService {
    List<TransactionEntity> getTransactionsByWalletId(@NotNull Integer walletId) throws WalletException;
    TransactionEntity createTransaction(@NotBlank String globalId, @NotBlank  String currencyName, @NotBlank String walletId, @NotBlank String transactionTypeId, @NotBlank String amount, String description) throws WalletException;

}
