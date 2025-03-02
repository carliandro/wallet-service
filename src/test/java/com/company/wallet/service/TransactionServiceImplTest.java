package com.company.wallet.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.wallet.entities.CurrencyEntity;
import com.company.wallet.entities.TransactionEntity;
import com.company.wallet.entities.TransactionTypeEntity;
import com.company.wallet.entities.WalletEntity;
import com.company.wallet.exceptions.WalletException;
import com.company.wallet.helper.Helper;
import com.company.wallet.repository.CurrencyRepository;
import com.company.wallet.repository.TransactionRepository;
import com.company.wallet.repository.TransactionTypeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TransactionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {
    @MockBean
    private CurrencyRepository currencyRepository;

    @MockBean
    private Helper helper;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @MockBean
    private TransactionTypeRepository transactionTypeRepository;

    @MockBean
    private WalletService walletService;

    /**
     * Test {@link TransactionServiceImpl#getTransactionsByWalletId(Integer)}.
     * <p>
     * Method under test: {@link TransactionServiceImpl#getTransactionsByWalletId(Integer)}
     */
    @Test
    @DisplayName("Test getTransactionsByWalletId(Integer)")
    @Tag("MaintainedByRecargaPay")
    void testGetTransactionsByWalletId() throws WalletException {
        // Arrange
        when(transactionRepository.findByWallet(Mockito.<WalletEntity>any())).thenReturn(null);

        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("2020-03-01");
        currency.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");
        when(walletService.findById(Mockito.<Integer>any())).thenReturn(walletEntity);

        // Act
        List<TransactionEntity> actualTransactionsByWalletId = transactionServiceImpl.getTransactionsByWalletId(1);

        // Assert
        verify(transactionRepository).findByWallet(isA(WalletEntity.class));
        verify(walletService).findById(eq(1));
        assertNull(actualTransactionsByWalletId);
    }

    /**
     * Test {@link TransactionServiceImpl#createTransaction(String, String, String, String, String, String)}.
     * <ul>
     *   <li>Then return {@link TransactionEntity#TransactionEntity()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link TransactionServiceImpl#createTransaction(String, String, String, String, String, String)}
     */
    @Test
    @DisplayName("Test createTransaction(String, String, String, String, String, String); then return TransactionEntity()")
    @Tag("MaintainedByDiffblue")
    void testCreateTransaction_thenReturnTransactionEntity() throws WalletException {
        // Arrange
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(1);
        currencyEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currencyEntity.setLastUpdatedBy("2020-03-01");
        currencyEntity.setName("Name");
        when(currencyRepository.findByName(Mockito.<String>any())).thenReturn(currencyEntity);
        doNothing().when(helper).conditionIsTrue(Mockito.<Boolean>any(), Mockito.<String>any(), anyInt());

        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("2020-03-01");
        currency.setName("Name");

        TransactionTypeEntity type = new TransactionTypeEntity();
        type.setDescription("The characteristics of someone or something");
        type.setId("42");
        type.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        type.setLastUpdatedBy("2020-03-01");

        CurrencyEntity currency2 = new CurrencyEntity();
        currency2.setId(1);
        currency2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency2.setLastUpdatedBy("2020-03-01");
        currency2.setName("Name");

        WalletEntity wallet = new WalletEntity();
        wallet.setBalance(new BigDecimal("2.3"));
        wallet.setCurrency(currency2);
        wallet.setId(1);
        wallet.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet.setLastUpdatedBy("2020-03-01");
        wallet.setUserId("42");

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(new BigDecimal("2.3"));
        transactionEntity.setCurrency(currency);
        transactionEntity.setDescription("The characteristics of someone or something");
        transactionEntity.setGlobalId("42");
        transactionEntity.setId(1);
        transactionEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transactionEntity.setLastUpdatedBy("2020-03-01");
        transactionEntity.setType(type);
        transactionEntity.setWallet(wallet);
        when(transactionRepository.save(Mockito.<TransactionEntity>any())).thenReturn(transactionEntity);

        TransactionTypeEntity transactionTypeEntity = new TransactionTypeEntity();
        transactionTypeEntity.setDescription("The characteristics of someone or something");
        transactionTypeEntity.setId("42");
        transactionTypeEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transactionTypeEntity.setLastUpdatedBy("2020-03-01");
        when(transactionTypeRepository.getOne(Mockito.<String>any())).thenReturn(transactionTypeEntity);

        CurrencyEntity currency3 = new CurrencyEntity();
        currency3.setId(1);
        currency3.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency3.setLastUpdatedBy("2020-03-01");
        currency3.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency3);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");

        CurrencyEntity currency4 = new CurrencyEntity();
        currency4.setId(1);
        currency4.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency4.setLastUpdatedBy("2020-03-01");
        currency4.setName("Name");

        WalletEntity walletEntity2 = new WalletEntity();
        walletEntity2.setBalance(new BigDecimal("2.3"));
        walletEntity2.setCurrency(currency4);
        walletEntity2.setId(1);
        walletEntity2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity2.setLastUpdatedBy("2020-03-01");
        walletEntity2.setUserId("42");
        when(walletService.updateWalletAmount(Mockito.<WalletEntity>any(), Mockito.<String>any(), Mockito.<Boolean>any()))
                .thenReturn(walletEntity2);
        when(walletService.findById(Mockito.<Integer>any())).thenReturn(walletEntity);

        // Act
        TransactionEntity actualCreateTransactionResult = transactionServiceImpl.createTransaction("42", "GBP", "42", "42",
                "10", "The characteristics of someone or something");

        // Assert
        verify(helper, atLeast(1)).conditionIsTrue(eq(true), Mockito.<String>any(), eq(400));
        verify(currencyRepository).findByName(eq("GBP"));
        verify(walletService).findById(eq(42));
        verify(walletService).updateWalletAmount(isA(WalletEntity.class), eq("10"), eq(false));
        verify(transactionTypeRepository).getOne(eq("42"));
        verify(transactionRepository).save(isA(TransactionEntity.class));
        assertSame(transactionEntity, actualCreateTransactionResult);
    }

    /**
     * Test {@link TransactionServiceImpl#createTransaction(String, String, String, String, String, String)}.
     * <ul>
     *   <li>Then return {@link TransactionEntity#TransactionEntity()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link TransactionServiceImpl#createTransaction(String, String, String, String, String, String)}
     */
    @Test
    @DisplayName("Test createTransaction(String, String, String, String, String, String); then return TransactionEntity()")
    @Tag("MaintainedByDiffblue")
    void testCreateTransaction_thenReturnTransactionEntity2() throws WalletException {
        // Arrange
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(1);
        currencyEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currencyEntity.setLastUpdatedBy("2020-03-01");
        currencyEntity.setName("Name");
        when(currencyRepository.findByName(Mockito.<String>any())).thenReturn(currencyEntity);
        doNothing().when(helper).conditionIsTrue(Mockito.<Boolean>any(), Mockito.<String>any(), Mockito.anyInt());

        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("2020-03-01");
        currency.setName("Name");

        TransactionTypeEntity type = new TransactionTypeEntity();
        type.setDescription("The characteristics of someone or something");
        type.setId("42");
        type.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        type.setLastUpdatedBy("2020-03-01");

        CurrencyEntity currency2 = new CurrencyEntity();
        currency2.setId(1);
        currency2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency2.setLastUpdatedBy("2020-03-01");
        currency2.setName("Name");

        WalletEntity wallet = new WalletEntity();
        wallet.setBalance(new BigDecimal("2.3"));
        wallet.setCurrency(currency2);
        wallet.setId(1);
        wallet.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet.setLastUpdatedBy("2020-03-01");
        wallet.setUserId("42");

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(new BigDecimal("2.3"));
        transactionEntity.setCurrency(currency);
        transactionEntity.setDescription("The characteristics of someone or something");
        transactionEntity.setGlobalId("42");
        transactionEntity.setId(1);
        transactionEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transactionEntity.setLastUpdatedBy("2020-03-01");
        transactionEntity.setType(type);
        transactionEntity.setWallet(wallet);
        when(transactionRepository.save(Mockito.<TransactionEntity>any())).thenReturn(transactionEntity);

        TransactionTypeEntity transactionTypeEntity = new TransactionTypeEntity();
        transactionTypeEntity.setDescription("The characteristics of someone or something");
        transactionTypeEntity.setId("42");
        transactionTypeEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transactionTypeEntity.setLastUpdatedBy("2020-03-01");
        when(transactionTypeRepository.getOne(Mockito.<String>any())).thenReturn(transactionTypeEntity);

        CurrencyEntity currency3 = new CurrencyEntity();
        currency3.setId(1);
        currency3.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency3.setLastUpdatedBy("2020-03-01");
        currency3.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency3);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");

        CurrencyEntity currency4 = new CurrencyEntity();
        currency4.setId(1);
        currency4.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency4.setLastUpdatedBy("2020-03-01");
        currency4.setName("Name");

        WalletEntity walletEntity2 = new WalletEntity();
        walletEntity2.setBalance(new BigDecimal("2.3"));
        walletEntity2.setCurrency(currency4);
        walletEntity2.setId(1);
        walletEntity2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity2.setLastUpdatedBy("2020-03-01");
        walletEntity2.setUserId("42");
        when(walletService.updateWalletAmount(Mockito.<WalletEntity>any(), Mockito.<String>any(), Mockito.<Boolean>any()))
                .thenReturn(walletEntity2);
        when(walletService.findById(Mockito.<Integer>any())).thenReturn(walletEntity);

        // Act
        TransactionEntity actualCreateTransactionResult = transactionServiceImpl.createTransaction("42", "GBP", "42", "42",
                "10", "The characteristics of someone or something");

        // Assert
        verify(helper, atLeast(1)).conditionIsTrue(eq(true), Mockito.<String>any(), eq(400));
        verify(currencyRepository).findByName(eq("GBP"));
        verify(walletService).findById(eq(42));
        verify(walletService).updateWalletAmount(isA(WalletEntity.class), eq("10"), eq(false));
        verify(transactionTypeRepository).getOne(eq("42"));
        verify(transactionRepository).save(isA(TransactionEntity.class));
        assertSame(transactionEntity, actualCreateTransactionResult);
    }

    /**
     * Test {@link TransactionServiceImpl#createTransaction(String, String, String, String, String, String)}.
     * <ul>
     *   <li>Then throw {@link WalletException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link TransactionServiceImpl#createTransaction(String, String, String, String, String, String)}
     */
    @Test
    @DisplayName("Test createTransaction(String, String, String, String, String, String); then throw WalletException")
    @Tag("MaintainedByDiffblue")
    void testCreateTransaction_thenThrowWalletException() throws WalletException {
        // Arrange
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(1);
        currencyEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currencyEntity.setLastUpdatedBy("2020-03-01");
        currencyEntity.setName("Name");
        when(currencyRepository.findByName(Mockito.<String>any())).thenReturn(currencyEntity);
        doNothing().when(helper).conditionIsTrue(Mockito.<Boolean>any(), Mockito.<String>any(), Mockito.anyInt());

        TransactionTypeEntity transactionTypeEntity = new TransactionTypeEntity();
        transactionTypeEntity.setDescription("The characteristics of someone or something");
        transactionTypeEntity.setId("42");
        transactionTypeEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transactionTypeEntity.setLastUpdatedBy("2020-03-01");
        when(transactionTypeRepository.getOne(Mockito.<String>any())).thenReturn(transactionTypeEntity);

        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("2020-03-01");
        currency.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");
        when(walletService.updateWalletAmount(Mockito.<WalletEntity>any(), Mockito.<String>any(), Mockito.<Boolean>any()))
                .thenThrow(new WalletException("An error occurred"));
        when(walletService.findById(Mockito.<Integer>any())).thenReturn(walletEntity);

        // Act and Assert
        assertThrows(WalletException.class, () -> transactionServiceImpl.createTransaction("42", "GBP", "42", "42", "10",
                "The characteristics of someone or something"));
        verify(helper, atLeast(1)).conditionIsTrue(eq(true), Mockito.<String>any(), eq(400));
        verify(currencyRepository).findByName(eq("GBP"));
        verify(walletService).findById(eq(42));
        verify(walletService).updateWalletAmount(isA(WalletEntity.class), eq("10"), eq(false));
        verify(transactionTypeRepository).getOne(eq("42"));
    }
}
