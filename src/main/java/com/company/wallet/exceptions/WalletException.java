package com.company.wallet.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Custom wallet exception
 *
 * @author Carliandro Cavalcanti
 */
@Setter
@Getter
public class WalletException extends Exception{
    private int errorCode;

    public WalletException(String message, int errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public WalletException(){
        super();
    }

    public WalletException(String message){
        super(message);
    }

    public WalletException(Exception e){
        super(e);
    }

}
