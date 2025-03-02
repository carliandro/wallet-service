package com.company.wallet.helper;

import com.company.wallet.exceptions.WalletException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Helper to check that some condition is TRUE and throw exception otherwise.
 * @author Carliandro Cavalcanti
 */
@Validated
@Component
public class HelperImpl implements Helper<String,String> {

    /**
     * Throws WalletException with errorMessage and errorCode if condition is not true
     * @param condition
     * @param errorMessage
     * @param errorCode
     * @throws WalletException
     */
    @Override
    public void conditionIsTrue(@NotNull Boolean condition, @NotNull String errorMessage, int errorCode) throws WalletException{
        if(!condition){
            throw new WalletException(errorMessage, errorCode);
        }
    }
}
