package com.iqbal.securitybasic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "account_transactions")
public class AccountTransactions {
    @Id
    private String transactionId;

    private long accountNumber;

    private int customerId;

    private Date transactionDt;

    private String transactionSummary;

    private String transactionType;

    private int transactionAmt;

    private int closingBalance;

    private String createDt;
}
