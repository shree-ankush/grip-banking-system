package com.tsf.bankingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CustomerProfile {

    private int customerId;
    private String name;
    private String email;
    private Long balance;
}
