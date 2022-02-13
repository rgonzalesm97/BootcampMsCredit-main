package com.bank.credit.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Credit {
    @Id
    private String id;
    private String nameProduct;
    private String cardNumber;
    private String typeCredit;
    private String accountNumber;
    private Double balance;
    private int creditLimit;

}
