package com.company.wallet.repository;

import com.company.wallet.entities.CurrencyEntity;
import com.company.wallet.entities.TransactionEntity;
import com.company.wallet.entities.TransactionTypeEntity;
import com.company.wallet.entities.WalletEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {TransactionRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.company.wallet.entities"})
@DataJpaTest
class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Test {@link TransactionRepository#findByWallet(WalletEntity)}.
     * <p>
     * Method under test: {@link TransactionRepository#findByWallet(WalletEntity)}
     */
    @Test
    @DisplayName("Test findByWallet(WalletEntity)")
    @Disabled("TODO: Complete this test")
    @Tag("MaintainedByRecargaPay")
    void testFindByWallet() {
        // Arrange
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
        transactionEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transactionEntity.setLastUpdatedBy("2020-03-01");
        transactionEntity.setType(type);
        transactionEntity.setWallet(wallet);

        CurrencyEntity currency3 = new CurrencyEntity();
        currency3.setId(2);
        currency3.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency3.setLastUpdatedBy("2020/03/01");
        currency3.setName("42");

        TransactionTypeEntity type2 = new TransactionTypeEntity();
        type2.setDescription("Description");
        type2.setId("Id");
        type2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        type2.setLastUpdatedBy("2020/03/01");

        CurrencyEntity currency4 = new CurrencyEntity();
        currency4.setId(2);
        currency4.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency4.setLastUpdatedBy("2020/03/01");
        currency4.setName("42");

        WalletEntity wallet2 = new WalletEntity();
        wallet2.setBalance(new BigDecimal("2.3"));
        wallet2.setCurrency(currency4);
        wallet2.setId(2);
        wallet2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet2.setLastUpdatedBy("2020/03/01");
        wallet2.setUserId("User Id");

        TransactionEntity transactionEntity2 = new TransactionEntity();
        transactionEntity2.setAmount(new BigDecimal("2.3"));
        transactionEntity2.setCurrency(currency3);
        transactionEntity2.setDescription("Description");
        transactionEntity2.setGlobalId("Global Id");
        transactionEntity2
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transactionEntity2.setLastUpdatedBy("2020/03/01");
        transactionEntity2.setType(type2);
        transactionEntity2.setWallet(wallet2);
        transactionRepository.save(transactionEntity);
        transactionRepository.save(transactionEntity2);

        CurrencyEntity currency5 = new CurrencyEntity();
        currency5.setId(1);
        currency5.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency5.setLastUpdatedBy("2020-03-01");
        currency5.setName("Name");

        WalletEntity wallet3 = new WalletEntity();
        wallet3.setBalance(new BigDecimal("2.3"));
        wallet3.setCurrency(currency5);
        wallet3.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet3.setLastUpdatedBy("2020-03-01");
        wallet3.setUserId("42");

        // Act
        transactionRepository.findByWallet(wallet3);
    }

    /**
     * Test {@link TransactionRepository#findByGlobalId(String)}.
     * <p>
     * Method under test: {@link TransactionRepository#findByGlobalId(String)}
     */
    @Test
    @DisplayName("Test findByGlobalId(String)")
    @Disabled("TODO: Complete this test")
    @Tag("MaintainedByRecargaPay")
    void testFindByGlobalId() {
        // Arrange
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
        transactionEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transactionEntity.setLastUpdatedBy("2020-03-01");
        transactionEntity.setType(type);
        transactionEntity.setWallet(wallet);

        CurrencyEntity currency3 = new CurrencyEntity();
        currency3.setId(2);
        currency3.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency3.setLastUpdatedBy("2020/03/01");
        currency3.setName("42");

        TransactionTypeEntity type2 = new TransactionTypeEntity();
        type2.setDescription("Description");
        type2.setId("Id");
        type2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        type2.setLastUpdatedBy("2020/03/01");

        CurrencyEntity currency4 = new CurrencyEntity();
        currency4.setId(2);
        currency4.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency4.setLastUpdatedBy("2020/03/01");
        currency4.setName("42");

        WalletEntity wallet2 = new WalletEntity();
        wallet2.setBalance(new BigDecimal("2.3"));
        wallet2.setCurrency(currency4);
        wallet2.setId(2);
        wallet2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        wallet2.setLastUpdatedBy("2020/03/01");
        wallet2.setUserId("User Id");

        TransactionEntity transactionEntity2 = new TransactionEntity();
        transactionEntity2.setAmount(new BigDecimal("2.3"));
        transactionEntity2.setCurrency(currency3);
        transactionEntity2.setDescription("Description");
        transactionEntity2.setGlobalId("Global Id");
        transactionEntity2
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        transactionEntity2.setLastUpdatedBy("2020/03/01");
        transactionEntity2.setType(type2);
        transactionEntity2.setWallet(wallet2);
        transactionRepository.save(transactionEntity);
        transactionRepository.save(transactionEntity2);

        // Act
        transactionRepository.findByGlobalId("42");
    }
}
