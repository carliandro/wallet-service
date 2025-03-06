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
     * Test {@link TransactionRepository#findByGlobalId(String)}.
     * <p>
     * Method under test: {@link TransactionRepository#findByGlobalId(String)}
     */
    @Test
    @DisplayName("Test findByGlobalId(String)")
    @Disabled
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

        WalletEntity wallet = new WalletEntity();
        wallet.setBalance(new BigDecimal("2.3"));
        wallet.setCurrency(currency);
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


        transactionRepository.save(transactionEntity);
        // Act
        transactionRepository.findByGlobalId("42");
    }
}
