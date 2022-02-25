package com.bank.credit.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("credit")
public class Credit {
    @Id
    private String id;
    private String idClient;
    private String cardNumber;
    private String typeCredit;
    private String accountNumber;
    private Double balance;
    private Double credit;
    private Double debt;

}
