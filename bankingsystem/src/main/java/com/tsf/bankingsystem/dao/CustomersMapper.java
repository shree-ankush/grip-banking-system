package com.tsf.bankingsystem.dao;

import com.tsf.bankingsystem.model.CustomerProfile;
import com.tsf.bankingsystem.model.Transactions;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@ReadWriteMapper
@Component
public interface CustomersMapper {
    @Select("SELECT * from customers ")
    CustomerProfile[] getCustomers();

    @Select("SELECT * FROM transaction")
    Transactions[] getTransactions();

    @Select("SELECT balance FROM customers WHERE email=#{email}")
    Long getBalanceFromEmail(@Param("email")String email);

    @Update("UPDATE customers SET balance=#{balance} WHERE email=#{email}")
    void updateBalanceFromEmail(@Param("balance")Long balance,@Param("email")String email);

    @Insert("INSERT INTO transaction VALUES"+"(#{senderEmail},#{recieverEmail},#{amount},#{timestamp},#{transactionId})")
    void insertNewTransaction(@Param("senderEmail")String  senderEmail,@Param("recieverEmail") String recieverEmail,@Param("amount")Long amount,@Param("timestamp")java.sql.Timestamp timeStamp,@Param("transactionId")String transactionId);


}
