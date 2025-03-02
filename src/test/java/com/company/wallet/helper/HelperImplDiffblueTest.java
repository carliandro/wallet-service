package com.company.wallet.helper;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.company.wallet.exceptions.WalletException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HelperImpl.class})
@ExtendWith(SpringExtension.class)
class HelperImplTest {
    @Autowired
    private HelperImpl helperImpl;

    /**
     * Test {@link HelperImpl#conditionIsTrue(Boolean, String, int)}.
     * <ul>
     *   <li>When {@code false}.</li>
     *   <li>Then throw {@link WalletException}.</li>
     * </ul>
     * <p>
     * Method under test: {@link HelperImpl#conditionIsTrue(Boolean, String, int)}
     */
    @Test
    @DisplayName("Test conditionIsTrue(Boolean, String, int); when 'false'; then throw WalletException")
    @Tag("MaintainedByRecargaPay")
    void testConditionIsTrue_whenFalse_thenThrowWalletException() throws WalletException {
        // Arrange, Act and Assert
        assertThrows(WalletException.class, () -> helperImpl.conditionIsTrue(false, "An error occurred", -1));
    }
}
