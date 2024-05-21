package com.tsf.bankingsystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TransferAmount {
    @JsonProperty(value = "senderEmail")
    private String senderEmail;
    @JsonProperty(value = "receiverEmail")
    private String receiverEmail;
    @JsonProperty(value = "transferAmount")
    private Long transferAmount;

}
