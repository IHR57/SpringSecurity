package com.iqbal.securitybasic.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;

@Data
@Entity
@Table(name = "loans")
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private int loanNumber;

    private int customerId;

    private Date startDt;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;

    private String createDt;
}
