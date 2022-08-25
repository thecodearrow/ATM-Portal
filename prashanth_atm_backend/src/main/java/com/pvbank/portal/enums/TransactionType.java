package com.pvbank.portal.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TransactionType {
    DEPOSIT,
    WITHDRAW
}