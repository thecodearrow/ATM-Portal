package com.pvbank.portal.entity;

import com.pvbank.portal.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;




@Table(name = "transactions")
@Entity
@Getter
@Setter
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TransactionType type; //WITHDRAW, DEPOSIT
    private Timestamp timestamp=new Timestamp(System.currentTimeMillis());
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user; //linked to user id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account; //linked to account id
    private Double transactionAmount;

}
