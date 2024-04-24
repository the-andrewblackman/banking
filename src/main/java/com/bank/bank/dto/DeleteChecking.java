package com.bank.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@Data
public class DeleteChecking {
    private static final Logger logger = LoggerFactory.getLogger(DeleteChecking.class);
    private String accountName;
    private String userName;
    public DeleteChecking(){
        logger.debug("DeleteChecking DTO created.");
    }
}
