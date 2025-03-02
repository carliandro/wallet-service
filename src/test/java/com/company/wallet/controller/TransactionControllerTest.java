package com.company.wallet.controller;

import static org.mockito.Mockito.when;

import com.company.wallet.exceptions.exceptionHandler.RestResponseEntityExceptionHandler;
import com.company.wallet.helper.Helper;
import com.company.wallet.service.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TransactionController.class, RestResponseEntityExceptionHandler.class})
@ExtendWith(SpringExtension.class)
class TransactionControllerTest {
    @MockBean
    private Helper helper;

    @Autowired
    private RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;

    @Autowired
    private TransactionController transactionController;

    @MockBean
    private TransactionService transactionService;

    /**
     * Test {@link TransactionController#getWalletTransactionsById(int)}.
     * <p>
     * Method under test: {@link TransactionController#getWalletTransactionsById(int)}
     */
    @Test
    @DisplayName("Test getWalletTransactionsById(int)")
    @Tag("MaintainedByRecargaPay")
    void testGetWalletTransactionsById() throws Exception {
        // Arrange
        when(transactionService.getTransactionsByWalletId(Mockito.<Integer>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wallets/{id}/transactions", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(transactionController)
                .setControllerAdvice(restResponseEntityExceptionHandler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("null"));
    }
}
