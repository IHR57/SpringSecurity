package com.iqbal.securitybasic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @Id
    private long accountNumber;

    private int customerId;

    private String accountType;

    private String branchAddress;

    private String CreateDt;
}
