package com.bank.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@AllArgsConstructor
public class CreateSavings {
    private static final Logger logger = LoggerFactory.getLogger(CreateSavings.class);
    private String accountName;
    private String userName;
    public CreateSavings(){
        logger.debug("CreateSavings DTO created.");
    }
}
