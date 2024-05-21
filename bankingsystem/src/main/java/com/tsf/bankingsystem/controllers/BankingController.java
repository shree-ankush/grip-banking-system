package com.tsf.bankingsystem.controllers;

import com.tsf.bankingsystem.dao.CustomersMapper;
import com.tsf.bankingsystem.exception.GenericResponse;
import com.tsf.bankingsystem.exception.StatusResponse;
import com.tsf.bankingsystem.model.CustomerProfile;
import com.tsf.bankingsystem.model.Transactions;
import com.tsf.bankingsystem.model.TransferAmount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class BankingController {


    Date dt = new Date();
    @Autowired
    private CustomersMapper customersMapper;


    @GetMapping("/customers")
    public CustomerProfile[] getCustomers(){
        return customersMapper.getCustomers();
    }

    public String generateRandomId(){
        UUID id = UUID.randomUUID();
        return id.toString();
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/transfer")
    public ResponseEntity<GenericResponse> transferMoney(@RequestBody TransferAmount transferAmount){
        Long senderCurrentBal = customersMapper.getBalanceFromEmail(transferAmount.getSenderEmail().trim());
        Long recieverCurrentBal = customersMapper.getBalanceFromEmail(transferAmount.getReceiverEmail().trim());
        log.info("senderCurrentBal currentBal : {} {}",senderCurrentBal ,transferAmount.getTransferAmount());

        if (senderCurrentBal <=0 || (senderCurrentBal < transferAmount.getTransferAmount())){
            GenericResponse<Object> genericResponse = GenericResponse.builder()
                    .statusResponse(StatusResponse.builder()
                            .success(false).message("Not Enough Balance").build())
                    .build();
            return  ResponseEntity.ok(genericResponse);
        }
        else{


            log.info("senderCurr, recieverCurr, timestamp : {} {} {}",senderCurrentBal,recieverCurrentBal,new Timestamp(dt.getTime()));
            customersMapper.insertNewTransaction( transferAmount.getSenderEmail(), transferAmount.getReceiverEmail(), transferAmount.getTransferAmount(), new Timestamp(dt.getTime()),this.generateRandomId());

            senderCurrentBal-=transferAmount.getTransferAmount();
            customersMapper.updateBalanceFromEmail(senderCurrentBal,transferAmount.getSenderEmail());
            recieverCurrentBal+=transferAmount.getTransferAmount();
            customersMapper.updateBalanceFromEmail(recieverCurrentBal, transferAmount.getReceiverEmail());
            GenericResponse<Object> genericResponse = GenericResponse.builder()
                    .statusResponse(StatusResponse.builder()
                            .success(true).message("Transfer Succesful.").build())
                    .build();
            return ResponseEntity.ok(genericResponse);

        }

    }

    @GetMapping("/get-transactions")
    public Transactions[] getTransactions(){
        log.info("this is obj : {}",customersMapper.getTransactions());
        return customersMapper.getTransactions() ;
    }


}
