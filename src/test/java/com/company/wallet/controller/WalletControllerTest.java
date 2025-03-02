package com.company.wallet.controller;

import static org.mockito.Mockito.when;

import com.company.wallet.entities.CurrencyEntity;
import com.company.wallet.entities.WalletEntity;
import com.company.wallet.exceptions.exceptionHandler.RestResponseEntityExceptionHandler;
import com.company.wallet.helper.Helper;
import com.company.wallet.service.WalletService;
import com.company.wallet.view.model.WalletModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {WalletController.class, RestResponseEntityExceptionHandler.class})
@ExtendWith(SpringExtension.class)
class WalletControllerTest {

    @Autowired
    private RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;

    @Autowired
    private WalletController walletController;

    @MockBean
    private WalletService walletService;

    @MockBean
    private Helper helper;

    /**
     * Test {@link WalletController#getWalletsByUserId(String)}.
     * <p>
     * Method under test: {@link WalletController#getWalletsByUserId(String)}
     */
    @Test
    @DisplayName("Test getWalletsByUserId(String)")
    @Tag("MaintainedByRecargaPay")
    void testGetWalletsByUserId() throws Exception {
        // Arrange
        when(walletService.findByUserId(Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets/user").param("userId", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("null"));
    }

    /**
     * Test {@link WalletController#getWalletById(int)}.
     * <ul>
     *   <li>Given {@link CurrencyEntity} (default constructor) LastUpdatedBy is empty string.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletController#getWalletById(int)}
     */
    @Test
    @DisplayName("Test getWalletById(int); given CurrencyEntity (default constructor) LastUpdatedBy is empty string")
    @Tag("MaintainedByRecargaPay")
    void testGetWalletById_givenCurrencyEntityLastUpdatedByIsEmptyString() throws Exception {
        // Arrange
        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("");
        currency.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");
        when(walletService.findById(Mockito.<Integer>any())).thenReturn(walletEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"userId\":\"42\",\"balance\":2.3,\"currency\":{\"id\":1,\"name\":\"Name\",\"lastUpdated\":\"Dec 31, 1969,"
                                        + " 9:00:00â¯PM\",\"lastUpdatedBy\":\"\"},\"lastUpdated\":\"Dec 31, 1969, 9:00:00â¯PM\",\"lastUpdatedBy\":\"2020"
                                        + "-03-01\"}"));
    }

    /**
     * Test {@link WalletController#getWalletById(int)}.
     * <p>
     * Method under test: {@link WalletController#getWalletById(int)}
     */
    @Test
    @DisplayName("Test getWalletById(int)")
    @Tag("MaintainedByRecargaPay")
    void testGetWalletById() throws Exception {
        // Arrange
        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("Called WalletController.getWalletById with id={}");
        currency.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");
        when(walletService.findById(Mockito.<Integer>any())).thenReturn(walletEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"userId\":\"42\",\"balance\":2.3,\"currency\":{\"id\":1,\"name\":\"Name\",\"lastUpdated\":\"Dec 31, 1969,"
                                        + " 9:00:00â¯PM\",\"lastUpdatedBy\":\"Called WalletController.getWalletById with id\\u003d{}\"},\"lastUpdated\":\"Dec"
                                        + " 31, 1969, 9:00:00â¯PM\",\"lastUpdatedBy\":\"2020-03-01\"}"));
    }

    /**
     * Test {@link WalletController#getWalletById(int)}.
     * <ul>
     *   <li>Given {@link WalletEntity#WalletEntity()} Balance is {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletController#getWalletById(int)}
     */
    @Test
    @DisplayName("Test getWalletById(int); given WalletEntity() Balance is 'null'")
    @Tag("MaintainedByRecargaPay")
    void testGetWalletById_givenWalletEntityBalanceIsNull() throws Exception {
        // Arrange
        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("2020-03-01");
        currency.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(null);
        walletEntity.setCurrency(currency);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");
        when(walletService.findById(Mockito.<Integer>any())).thenReturn(walletEntity);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"userId\":\"42\",\"currency\":{\"id\":1,\"name\":\"Name\",\"lastUpdated\":\"Dec 31, 1969, 9:00:00â¯PM"
                                        + "\",\"lastUpdatedBy\":\"2020-03-01\"},\"lastUpdated\":\"Dec 31, 1969, 9:00:00â¯PM\",\"lastUpdatedBy\":\"2020"
                                        + "-03-01\"}"));
    }

    /**
     * Test {@link WalletController#getWalletById(int)}.
     * <ul>
     *   <li>Given {@link CurrencyEntity} (default constructor) LastUpdatedBy is {@code 2020-03-01}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletController#getWalletById(int)}
     */
    @Test
    @DisplayName("Test getWalletById(int); given CurrencyEntity (default constructor) LastUpdatedBy is '2020-03-01'")
    @Tag("MaintainedByRecargaPay")
    void testGetWalletById_givenCurrencyEntityLastUpdatedByIs20200301() throws Exception {
        // Arrange
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets/{id}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"userId\":\"42\",\"balance\":2.3,\"currency\":{\"id\":1,\"name\":\"Name\",\"lastUpdated\":\"Dec 31, 1969,"
                                        + " 9:00:00â¯PM\",\"lastUpdatedBy\":\"2020-03-01\"},\"lastUpdated\":\"Dec 31, 1969, 9:00:00â¯PM\",\"lastUpdatedBy"
                                        + "\":\"2020-03-01\"}"));
    }

    /**
     * Test {@link WalletController#createWallet(WalletModel)}.
     * <p>
     * Method under test: {@link WalletController#createWallet(WalletModel)}
     */
    @Test
    @DisplayName("Test createWallet(WalletModel)")
    @Tag("MaintainedByRecargaPay")
    void testCreateWallet() throws Exception {
        // Arrange
        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("Called WalletController.createWallet with userId={}");
        currency.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");
        when(walletService.createWallet(Mockito.<String>any(), Mockito.<String>any())).thenReturn(walletEntity);

        WalletModel walletModel = new WalletModel();
        walletModel.setCurrency("GBP");
        walletModel.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(walletModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"userId\":\"42\",\"balance\":2.3,\"currency\":{\"id\":1,\"name\":\"Name\",\"lastUpdated\":\"Dec 31, 1969,"
                                        + " 9:00:00â¯PM\",\"lastUpdatedBy\":\"Called WalletController.createWallet with userId\\u003d{}\"},\"lastUpdated\":\"Dec"
                                        + " 31, 1969, 9:00:00â¯PM\",\"lastUpdatedBy\":\"2020-03-01\"}"));
    }

    /**
     * Test {@link WalletController#createWallet(WalletModel)}.
     * <ul>
     *   <li>Given {@link CurrencyEntity} (default constructor) LastUpdatedBy is {@code 2020-03-01}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletController#createWallet(WalletModel)}
     */
    @Test
    @DisplayName("Test createWallet(WalletModel); given CurrencyEntity (default constructor) LastUpdatedBy is '2020-03-01'")
    @Tag("MaintainedByRecargaPay")
    void testCreateWallet_givenCurrencyEntityLastUpdatedByIs20200301() throws Exception {
        // Arrange
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
        when(walletService.createWallet(Mockito.<String>any(), Mockito.<String>any())).thenReturn(walletEntity);

        WalletModel walletModel = new WalletModel();
        walletModel.setCurrency("GBP");
        walletModel.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(walletModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"userId\":\"42\",\"balance\":2.3,\"currency\":{\"id\":1,\"name\":\"Name\",\"lastUpdated\":\"Dec 31, 1969,"
                                        + " 9:00:00â¯PM\",\"lastUpdatedBy\":\"2020-03-01\"},\"lastUpdated\":\"Dec 31, 1969, 9:00:00â¯PM\",\"lastUpdatedBy"
                                        + "\":\"2020-03-01\"}"));
    }

    /**
     * Test {@link WalletController#createWallet(WalletModel)}.
     * <ul>
     *   <li>Given {@link CurrencyEntity} (default constructor) LastUpdatedBy is empty string.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletController#createWallet(WalletModel)}
     */
    @Test
    @DisplayName("Test createWallet(WalletModel); given CurrencyEntity (default constructor) LastUpdatedBy is empty string")
    @Tag("MaintainedByRecargaPay")
    void testCreateWallet_givenCurrencyEntityLastUpdatedByIsEmptyString() throws Exception {
        // Arrange
        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("");
        currency.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(new BigDecimal("2.3"));
        walletEntity.setCurrency(currency);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");
        when(walletService.createWallet(Mockito.<String>any(), Mockito.<String>any())).thenReturn(walletEntity);

        WalletModel walletModel = new WalletModel();
        walletModel.setCurrency("GBP");
        walletModel.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(walletModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"userId\":\"42\",\"balance\":2.3,\"currency\":{\"id\":1,\"name\":\"Name\",\"lastUpdated\":\"Dec 31, 1969,"
                                        + " 9:00:00â¯PM\",\"lastUpdatedBy\":\"\"},\"lastUpdated\":\"Dec 31, 1969, 9:00:00â¯PM\",\"lastUpdatedBy\":\"2020"
                                        + "-03-01\"}"));
    }

    /**
     * Test {@link WalletController#createWallet(WalletModel)}.
     * <ul>
     *   <li>Given {@link WalletEntity#WalletEntity()} Balance is {@code null}.</li>
     * </ul>
     * <p>
     * Method under test: {@link WalletController#createWallet(WalletModel)}
     */
    @Test
    @DisplayName("Test createWallet(WalletModel); given WalletEntity() Balance is 'null'")
    @Tag("MaintainedByRecargaPay")
    void testCreateWallet_givenWalletEntityBalanceIsNull() throws Exception {
        // Arrange
        CurrencyEntity currency = new CurrencyEntity();
        currency.setId(1);
        currency.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        currency.setLastUpdatedBy("2020-03-01");
        currency.setName("Name");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setBalance(null);
        walletEntity.setCurrency(currency);
        walletEntity.setId(1);
        walletEntity.setLastUpdated(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        walletEntity.setLastUpdatedBy("2020-03-01");
        walletEntity.setUserId("42");
        when(walletService.createWallet(Mockito.<String>any(), Mockito.<String>any())).thenReturn(walletEntity);

        WalletModel walletModel = new WalletModel();
        walletModel.setCurrency("GBP");
        walletModel.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(walletModel);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/wallets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"userId\":\"42\",\"currency\":{\"id\":1,\"name\":\"Name\",\"lastUpdated\":\"Dec 31, 1969, 9:00:00â¯PM"
                                        + "\",\"lastUpdatedBy\":\"2020-03-01\"},\"lastUpdated\":\"Dec 31, 1969, 9:00:00â¯PM\",\"lastUpdatedBy\":\"2020"
                                        + "-03-01\"}"));
    }

    /**
     * Test {@link WalletController#getAll()}.
     * <p>
     * Method under test: {@link WalletController#getAll()}
     */
    @Test
    @DisplayName("Test getAll()")
    @Tag("MaintainedByRecargaPay")
    void testGetAll() throws Exception {
        // Arrange
        when(walletService.findAll()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(walletController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("null"));
    }
}
