package com.bank.bank.dto;

import com.bank.bank.controller.BankController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;

@AllArgsConstructor
@Data
public class CreateChecking {

    private static final Logger logger = LoggerFactory.getLogger(CreateChecking.class);
    private String accountName;
    private String userName;
    public CreateChecking(){
        logger.debug("CreateChecking DTO created.");
    }
}
