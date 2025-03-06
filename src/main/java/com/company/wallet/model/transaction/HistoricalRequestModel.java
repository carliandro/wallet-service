package com.company.wallet.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoricalRequestModel {
    int walletId;
    Date lastUpdatedStart;
    Date lastUpdatedEnd;
}
