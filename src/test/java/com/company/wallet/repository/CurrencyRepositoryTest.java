package com.company.wallet.repository;

import com.company.wallet.entities.CurrencyEntity;

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

@ContextConfiguration(classes = {CurrencyRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.company.wallet.entities"})
@DataJpaTest
class CurrencyRepositoryTest {
    @Autowired
    private CurrencyRepository currencyRepository;

    /**
     * Test {@link CurrencyRepository#findByName(String)}.
     * <p>
     * Method under test: {@link CurrencyRepository#findByName(String)}
     */
    @Test
    @DisplayName("Test findByName(String)")
    @Disabled("TODO: Complete this test")
    @Tag("MaintainedByRecargaPay")
    void testFindByName() {
        // Arrange
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setId(1);
        currencyEntity
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currencyEntity.setLastUpdatedBy("recarga-pay");
        currencyEntity.setName("Name");

        CurrencyEntity currencyEntity2 = new CurrencyEntity();
        currencyEntity2.setId(2);
        currencyEntity2
                .setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currencyEntity2.setLastUpdatedBy("recarga-pay");
        currencyEntity2.setName("42");
        currencyRepository.save(currencyEntity);
        currencyRepository.save(currencyEntity2);

        // Act
        currencyRepository.findByName("Name");
    }
}
