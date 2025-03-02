package com.company.wallet.repository;

import com.company.wallet.entities.CurrencyEntity;
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

@ContextConfiguration(classes = {WalletRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.company.wallet.entities"})
@DataJpaTest
class WalletRepositoryTest {
    @Autowired
    private WalletRepository walletRepository;

    /**
     * Test {@link WalletRepository#findAllByOrderByIdAsc()}.
     * <p>
     * Method under test: {@link WalletRepository#findAllByOrderByIdAsc()}
     */
    @Test
    @DisplayName("Test findAllByOrderByIdAsc()")
    @Disabled("TODO: Complete this test")
    @Tag("MaintainedByRecargaPay")
    void testFindAllByOrderByIdAsc() {
        // Arrange
        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("2020-03-01");
        currency.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");

        CurrencyEntity currency2 = new CurrencyEntity();
        currency2.setId(2);
        currency2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency2.setLastUpdatedBy("2020/03/01");
        currency2.setName("42");

        WalletEntity walletEntity2 = new WalletEntity();
        walletEntity2.setBalance(new BigDecimal("2.3"));
        walletEntity2.setCurrency(currency2);
        walletEntity2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity2.setLastUpdatedBy("2020/03/01");
        walletEntity2.setUserId("User Id");
        walletRepository.save(walletEntity);
        walletRepository.save(walletEntity2);

        // Act
        walletRepository.findAllByOrderByIdAsc();
    }

    /**
     * Test {@link WalletRepository#findByUserId(String)}.
     * <p>
     * Method under test: {@link WalletRepository#findByUserId(String)}
     */
    @Test
    @DisplayName("Test findByUserId(String)")
    @Disabled("TODO: Complete this test")
    @Tag("MaintainedByRecargaPay")
    void testFindByUserId() {
        // Arrange
        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("2020-03-01");
        currency.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");

        CurrencyEntity currency2 = new CurrencyEntity();
        currency2.setId(2);
        currency2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency2.setLastUpdatedBy("2020/03/01");
        currency2.setName("42");

        WalletEntity walletEntity2 = new WalletEntity();
        walletEntity2.setBalance(new BigDecimal("2.3"));
        walletEntity2.setCurrency(currency2);
        walletEntity2.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity2.setLastUpdatedBy("2020/03/01");
        walletEntity2.setUserId("User Id");
        walletRepository.save(walletEntity);
        walletRepository.save(walletEntity2);

        // Act
        walletRepository.findByUserId("42");
    }
}
