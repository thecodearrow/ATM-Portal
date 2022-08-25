package com.pvbank.portal.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String userFirstName;
    @Column(nullable = false)
    private String userLastName;
    @Column(unique = true,nullable = false)
    private String loginUserName;
    @Column(nullable = false)
    private String loginPassword;
    @Column(unique = true,nullable = false)
    private String emailID;
    @Column(unique = true, nullable=false)
    private String mobileNumber;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<Account> accounts;



}