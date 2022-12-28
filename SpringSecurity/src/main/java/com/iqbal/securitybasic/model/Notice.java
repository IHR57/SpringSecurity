package com.iqbal.securitybasic.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "notice_details")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noticeId;

    private String noticeSummary;

    private String noticeDetails;

    private Date noticBegDt;

    private Date noticEndDt;

    private Date createDt;

    private Date updateDt;
}
