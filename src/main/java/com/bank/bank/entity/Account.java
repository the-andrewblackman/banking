package com.bank.bank.entity;

import com.bank.bank.controller.BankController;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@Builder
public class Account {
    private static final Logger logger = LoggerFactory.getLogger(Account.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Checking> checking;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Savings> savings;
    public Account(){
        logger.debug("Account entity created.");
    }
}
