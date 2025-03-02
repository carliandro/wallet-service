package com.company.wallet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.company.wallet.entities.CurrencyEntity;
import com.company.wallet.entities.WalletEntity;
import com.company.wallet.exceptions.WalletException;
import com.company.wallet.helper.Helper;
import com.company.wallet.repository.CurrencyRepository;
import com.company.wallet.repository.TransactionRepository;
import com.company.wallet.repository.WalletRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WalletServiceImpl.class})
@ExtendWith(SpringExtension.class)
class WalletServiceImplTest {

    @MockBean
    private WalletRepository walletRepository;

    @Autowired
    private WalletServiceImpl walletServiceImpl;

    @MockBean
    private CurrencyRepository currencyRepository;

    @MockBean
    private Helper helper;

    @MockBean
    private TransactionRepository transactionRepository;

    /**
     * Test {@link WalletServiceImpl#findAll()}.
     * <ul>
     *   <li>Given {@link WalletRepository} {@link WalletRepository#findAllByOrderByIdAsc()} return {@code null}.</li>
     *   <li>Then return {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#findAll()}
     */
    @Test
    @DisplayName("Test findAll(); given WalletRepository findAllByOrderByIdAsc() return 'null'; then return 'null'")
    @Tag("MaintainedByRecargaPay")
    void testFindAll_givenWalletRepositoryFindAllByOrderByIdAscReturnNull_thenReturnNull() throws WalletException {
        // Arrange
        when(walletRepository.findAllByOrderByIdAsc()).thenReturn(null);

        // Act
        List<WalletEntity> actualFindAllResult = walletServiceImpl.findAll();

        // Assert
        verify(walletRepository).findAllByOrderByIdAsc();
        assertNull(actualFindAllResult);
    }

    /**
     * Test {@link WalletServiceImpl#findAll()}.
     * <ul>
     *   <li>Then throw {@link ObjectNotFoundException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#findAll()}
     */
    @Test
    @DisplayName("Test findAll(); then throw ObjectNotFoundException")
    @Tag("MaintainedByRecargaPay")
    void testFindAll_thenThrowObjectNotFoundException() throws WalletException {
        // Arrange
        when(walletRepository.findAllByOrderByIdAsc())
                .thenThrow(new ObjectNotFoundException((Object) "Identifier", "Called WalletServiceImpl.findAll"));

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> walletServiceImpl.findAll());
        verify(walletRepository).findAllByOrderByIdAsc();
    }

    /**
     * Test {@link WalletServiceImpl#findById(Integer)}.
     * <ul>
     *   <li>Given {@link CurrencyEntity} (default constructor) Id is one.</li>
     *   <li>Then return {@link WalletEntity#WalletEntity()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#findById(Integer)}
     */
    @Test
    @DisplayName("Test findById(Integer); given CurrencyEntity (default constructor) Id is one; then return WalletEntity()")
    @Tag("MaintainedByRecargaPay")
    void testFindById_givenCurrencyEntityIdIsOne_thenReturnWalletEntity() throws WalletException {
        // Arrange
        doNothing().when(helper).conditionIsTrue(Mockito.<Boolean>any(), Mockito.<String>any(), Mockito.anyInt());

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
        Optional<WalletEntity> ofResult = Optional.of(walletEntity);
        when(walletRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        WalletEntity actualFindByIdResult = walletServiceImpl.findById(1);

        // Assert
        verify(helper).conditionIsTrue(eq(true), eq("No wallet with id 1 exists in the system."), eq(400));
        verify(walletRepository).findById(eq(1));
        assertSame(walletEntity, actualFindByIdResult);
    }

    /**
     * Test {@link WalletServiceImpl#findById(Integer)}.
     * <ul>
     *   <li>Then throw {@link ObjectNotFoundException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#findById(Integer)}
     */
    @Test
    @DisplayName("Test findById(Integer); then throw ObjectNotFoundException")
    @Tag("MaintainedByRecargaPay")
    void testFindById_thenThrowObjectNotFoundException() throws WalletException {
        // Arrange
        when(walletRepository.findById(Mockito.<Integer>any()))
                .thenThrow(new ObjectNotFoundException((Object) "Identifier", "Called WalletServiceImpl.findById with id={}"));

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> walletServiceImpl.findById(1));
        verify(walletRepository).findById(eq(1));
    }

    /**
     * Test {@link WalletServiceImpl#findByUserId(String)}.
     * <ul>
     *   <li>Given {@link WalletRepository} {@link WalletRepository#findByUserId(String)} return {@code null}.</li>
     *   <li>Then return {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#findByUserId(String)}
     */
    @Test
    @DisplayName("Test findByUserId(String); given WalletRepository findByUserId(String) return 'null'; then return 'null'")
    @Tag("MaintainedByRecargaPay")
    void testFindByUserId_givenWalletRepositoryFindByUserIdReturnNull_thenReturnNull() throws WalletException {
        // Arrange
        when(walletRepository.findByUserId(Mockito.<String>any())).thenReturn(null);

        // Act
        List<WalletEntity> actualFindByUserIdResult = walletServiceImpl.findByUserId("42");

        // Assert
        verify(walletRepository).findByUserId(eq("42"));
        assertNull(actualFindByUserIdResult);
    }

    /**
     * Test {@link WalletServiceImpl#findByUserId(String)}.
     * <ul>
     *   <li>Then throw {@link ObjectNotFoundException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#findByUserId(String)}
     */
    @Test
    @DisplayName("Test findByUserId(String); then throw ObjectNotFoundException")
    @Tag("MaintainedByRecargaPay")
    void testFindByUserId_thenThrowObjectNotFoundException() throws WalletException {
        // Arrange
        when(walletRepository.findByUserId(Mockito.<String>any())).thenThrow(
                new ObjectNotFoundException((Object) "Identifier", "Called WalletServiceImpl.findByUserId with uaerId={}"));

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> walletServiceImpl.findByUserId("42"));
        verify(walletRepository).findByUserId(eq("42"));
    }

    /**
     * Test {@link WalletServiceImpl#createWallet(String, String)}.
     * <ul>
     *   <li>Then return {@link WalletEntity#WalletEntity()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#createWallet(String, String)}
     */
    @Test
    @DisplayName("Test createWallet(String, String); then return WalletEntity()")
    @Tag("MaintainedByRecargaPay")
    void testCreateWallet_thenReturnWalletEntity() throws WalletException {
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

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");
        when(walletRepository.save(Mockito.<WalletEntity>any())).thenReturn(walletEntity);

        // Act
        WalletEntity actualCreateWalletResult = walletServiceImpl.createWallet("42", "GBP");

        // Assert
        verify(helper).conditionIsTrue(eq(true), eq("No currency GBP exists in the system."), eq(400));
        verify(currencyRepository).findByName(eq("GBP"));
        verify(walletRepository).save(isA(WalletEntity.class));
        assertSame(walletEntity, actualCreateWalletResult);
    }

    /**
     * Test {@link WalletServiceImpl#createWallet(String, String)}.
     * <ul>
     *   <li>Then throw {@link WalletException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#createWallet(String, String)}
     */
    @Test
    @DisplayName("Test createWallet(String, String); then throw WalletException")
    @Tag("MaintainedByRecargaPay")
    void testCreateWallet_thenThrowWalletException() throws WalletException {
        // Arrange
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(1);
        currencyEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currencyEntity.setLastUpdatedBy("2020-03-01");
        currencyEntity.setName("Name");
        when(currencyRepository.findByName(Mockito.<String>any())).thenReturn(currencyEntity);
        doNothing().when(helper).conditionIsTrue(Mockito.<Boolean>any(), Mockito.<String>any(), Mockito.anyInt());
        when(walletRepository.save(Mockito.<WalletEntity>any())).thenThrow(
                new ObjectNotFoundException((Object) "Identifier", "Called WalletServiceImpl.createWallet with uaerId={}"));

        // Act and Assert
        assertThrows(WalletException.class, () -> walletServiceImpl.createWallet("42", "GBP"));
        verify(helper).conditionIsTrue(eq(true), eq("No currency GBP exists in the system."), eq(400));
        verify(currencyRepository).findByName(eq("GBP"));
        verify(walletRepository).save(isA(WalletEntity.class));
    }

    /**
     * Test {@link WalletServiceImpl#updateWalletAmount(WalletEntity, String, Boolean)}.
     * <ul>
     *   <li>Then throw {@link ObjectNotFoundException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#updateWalletAmount(WalletEntity, String, Boolean)}
     */
    @Test
    @DisplayName("Test updateWalletAmount(WalletEntity, String, Boolean); then throw ObjectNotFoundException")
    @Tag("MaintainedByRecargaPay")
    void testUpdateWalletAmount_thenThrowObjectNotFoundException() throws WalletException {
        // Arrange
        doNothing().when(helper).conditionIsTrue(Mockito.<Boolean>any(), Mockito.<String>any(), Mockito.anyInt());
        when(walletRepository.save(Mockito.<WalletEntity>any())).thenThrow(new ObjectNotFoundException(
                (Object) "Identifier", "Called WalletServiceImpl.updateWalletAmount with walletId={}"));

        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("2020-03-01");
        currency.setName("Name");

        WalletEntity wallet = new WalletEntity();
        wallet.setBalance(new BigDecimal("2.3"));
        wallet.setCurrency(currency);
        wallet.setId(1);
        wallet.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet.setLastUpdatedBy("2020-03-01");
        wallet.setUserId("42");

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> walletServiceImpl.updateWalletAmount(wallet, "10", true));
        verify(helper).conditionIsTrue(eq(true),
                eq("Wallet 1 has not enough funds to perform debit transaction with amount 10"), eq(400));
        verify(walletRepository).save(isA(WalletEntity.class));
    }

    /**
     * Test {@link WalletServiceImpl#updateWalletAmount(WalletEntity, String, Boolean)}.
     * <ul>
     *   <li>Then throw {@link WalletException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#updateWalletAmount(WalletEntity, String, Boolean)}
     */
    @Test
    @DisplayName("Test updateWalletAmount(WalletEntity, String, Boolean); then throw WalletException")
    @Tag("MaintainedByRecargaPay")
    void testUpdateWalletAmount_thenThrowWalletException() throws WalletException {
        // Arrange
        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("2020-03-01");
        currency.setName("Name");

        WalletEntity wallet = new WalletEntity();
        wallet.setBalance(new BigDecimal("2.3"));
        wallet.setCurrency(currency);
        wallet.setId(1);
        wallet.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet.setLastUpdatedBy("2020-03-01");
        wallet.setUserId("42");

        // Act and Assert
        assertThrows(WalletException.class, () -> walletServiceImpl.updateWalletAmount(wallet,
                "Called WalletServiceImpl.updateWalletAmount with walletId={}", true));
    }

    /**
     * Test {@link WalletServiceImpl#updateWalletAmount(WalletEntity, String, Boolean)}.
     * <ul>
     *   <li>Then {@link WalletEntity#WalletEntity()} Balance is {@link BigDecimal#BigDecimal(String)} with {@code 12.3}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#updateWalletAmount(WalletEntity, String, Boolean)}
     */
    @Test
    @DisplayName("Test updateWalletAmount(WalletEntity, String, Boolean); then WalletEntity() Balance is BigDecimal(String) with '12.3'")
    @Tag("MaintainedByRecargaPay")
    void testUpdateWalletAmount_thenWalletEntityBalanceIsBigDecimalWith123() throws WalletException {
        // Arrange
        doNothing().when(helper).conditionIsTrue(Mockito.<Boolean>any(), Mockito.<String>any(), Mockito.anyInt());

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
        when(walletRepository.save(Mockito.<WalletEntity>any())).thenReturn(walletEntity);

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

        // Act
        WalletEntity actualUpdateWalletAmountResult = walletServiceImpl.updateWalletAmount(wallet, "10", true);

        // Assert
        verify(helper).conditionIsTrue(eq(true),
                eq("Wallet 1 has not enough funds to perform debit transaction with amount 10"), eq(400));
        verify(walletRepository).save(isA(WalletEntity.class));
        assertEquals("wallet-microservice", wallet.getLastUpdatedBy());
        BigDecimal expectedBalance = new BigDecimal("12.3");
        assertEquals(expectedBalance, wallet.getBalance());
        assertSame(walletEntity, actualUpdateWalletAmountResult);
    }

    /**
     * Test {@link WalletServiceImpl#updateWalletAmount(WalletEntity, String, Boolean)}.
     * <ul>
     *   <li>When {@code false}.</li>
     *   <li>Then {@link WalletEntity#WalletEntity()} Balance is {@link BigDecimal#BigDecimal(String)} with {@code -7.7}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletServiceImpl#updateWalletAmount(WalletEntity, String, Boolean)}
     */
    @Test
    @DisplayName("Test updateWalletAmount(WalletEntity, String, Boolean); when 'false'; then WalletEntity() Balance is BigDecimal(String) with '-7.7'")
    @Tag("MaintainedByRecargaPay")
    void testUpdateWalletAmount_whenFalse_thenWalletEntityBalanceIsBigDecimalWith77() throws WalletException {
        // Arrange
        doNothing().when(helper).conditionIsTrue(Mockito.<Boolean>any(), Mockito.<String>any(), Mockito.anyInt());

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
        when(walletRepository.save(Mockito.<WalletEntity>any())).thenReturn(walletEntity);

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

        // Act
        WalletEntity actualUpdateWalletAmountResult = walletServiceImpl.updateWalletAmount(wallet, "10", false);

        // Assert
        verify(helper).conditionIsTrue(eq(false),
                eq("Wallet 1 has not enough funds to perform debit transaction with amount 10"), eq(400));
        verify(walletRepository).save(isA(WalletEntity.class));
        assertEquals("wallet-microservice", wallet.getLastUpdatedBy());
        BigDecimal expectedBalance = new BigDecimal("-7.7");
        assertEquals(expectedBalance, wallet.getBalance());
        assertSame(walletEntity, actualUpdateWalletAmountResult);
    }
}
