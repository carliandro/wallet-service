package com.company.wallet.controller;

import com.company.wallet.entities.TransactionEntity;
import com.company.wallet.exceptions.WalletException;
import com.company.wallet.gson.adapter.HibernateProxyTypeAdapter;
import com.company.wallet.gson.exclusion.ExcludeField;
import com.company.wallet.gson.exclusion.GsonExclusionStrategy;
import com.company.wallet.helper.Helper;
import com.company.wallet.service.TransactionService;
import com.company.wallet.view.model.TransactionModel;
import com.google.gson.GsonBuilder;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * Restful controller for managing Transations on the RecargaPay wallet
 *
 * @author Carlianddro Cavalcanti
 */
@RestController
public class TransactionController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private Helper inputParametersValidator;

    @GetMapping(
            value = "/transactions/wallets/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String getTransactionsByWalletId( @PathVariable("id") int id) throws WalletException, ClassNotFoundException {
        logger.info("Called TransactionController.getWalletTransactionsById with parameter walletId={}",id);
        List<TransactionEntity> transactionList = transactionService.getTransactionsByWalletId(id);
        return new GsonBuilder().
                setExclusionStrategies(new GsonExclusionStrategy(ExcludeField.EXCLUDE_WALLET)).
                create().toJson(transactionList);

    }

    /**
     * Creates wallet transaction.
     * <p>
     * Example of  credit transaction JSON body
     * {"globalId":"123","currency":"EUR","walletId": "1","transactionTypeId":"C","amount":"100","description":"add money"}
     * Example of debit transaction JSON body
     * {"globalId":"123","currency":"EUR","walletId": "1","transactionTypeId":"D","amount":"100","description":"withdraw money"}
     * </p>
     * @param transactionModel contains input parameters in the following format:
     *                {"globalId":"123","currency":"EUR","walletId": "1","transactionTypeId":"C","amount":"100","description":"add money"}
     * @return created transaction in JSON format
     * @throws WalletException when couldn't create transaction (e.g. globalId not unique, not enough funds on wallet balance, etc.)
     */

    @PostMapping(
            value = "/transactions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String createWalletTransaction(@Valid @RequestBody TransactionModel transactionModel) throws WalletException, ClassNotFoundException {
        logger.info("Called TransactionController.createWalletTransaction with parameter trasactionGlobalId={}", transactionModel.getGlobalId() );

        TransactionEntity transaction = transactionService.createTransaction(transactionModel.getGlobalId(),transactionModel.getCurrency(),transactionModel.getWalletId(),
                transactionModel.getTransactionTypeId(),transactionModel.getAmount(),transactionModel.getDescription());

        return new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).
                setExclusionStrategies(new GsonExclusionStrategy(ExcludeField.EXCLUDE_TRANSACTIONS)).
                create().toJson(transaction);
    }
}
