package com.company.wallet.helper;

import com.company.wallet.exceptions.WalletException;
import jakarta.validation.constraints.NotNull;

/**
 * Helper to check that condition is TRUE.
 * @param <K>
 * @param <V>
 *
 * @author Carliandro Cavalcanti
 */
public interface Helper<K,V> {
    public void conditionIsTrue(@NotNull Boolean condition, @NotNull String errorMessage, int errorCode) throws WalletException;

}
