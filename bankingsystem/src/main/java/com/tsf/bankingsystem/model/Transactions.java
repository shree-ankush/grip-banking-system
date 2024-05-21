package com.tsf.bankingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Transactions {

    private String transactionId;
    private String senderEmail;
    private String recieverEmail;
    private String transactionAmount ;
    private Timestamp timestamp;

}
