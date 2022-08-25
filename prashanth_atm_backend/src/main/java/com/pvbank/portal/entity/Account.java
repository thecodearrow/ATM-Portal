package com.pvbank.portal.entity;

import com.pvbank.portal.utils.RandomGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "accounts")
@Entity
@Getter
@Setter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @Column(unique = true)
    private String accountNumber= RandomGenerator.getRandomNDigits(12);
    private String ifscCode=RandomGenerator.getRandomString();
    @Column(unique = true)
    private String debitCardNumber= RandomGenerator.getRandomNDigits(8);
    private String pin= RandomGenerator.getRandomNDigits(4);
//  private String accountType; //can be a ENUM
    private Double balanceAmount= 0.0;


}
