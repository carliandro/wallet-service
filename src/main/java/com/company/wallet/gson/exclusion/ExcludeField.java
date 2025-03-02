package com.company.wallet.gson.exclusion;

import com.company.wallet.entities.TransactionEntity;
import com.company.wallet.entities.WalletEntity;

/**
 * Fields to be excluded from serialization when using gson serialization
 *
 * @author Carliandro Cavalcanti
 */
public class ExcludeField {
    public static final String EXCLUDE_WALLET = TransactionEntity.class.getCanonicalName()+ ".wallet";
    public static final String EXCLUDE_TRANSACTIONS = WalletEntity.class.getCanonicalName() + ".transactions";

}
