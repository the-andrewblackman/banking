package com.bank.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Entity
@Data
@Table(name = "trxnsxctions")
@AllArgsConstructor
@Builder
public class Trxnsxctions {
    private static final Logger logger = LoggerFactory.getLogger(Trxnsxctions.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String trxnsxctiontype;
    @Column
    private String trxnsxctiondescription;
    @Column
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "checking_id")
    private Checking checking;
    @ManyToOne
    @JoinColumn(name = "savings_id")
    private Savings savings;
    public Trxnsxctions(){
        logger.debug("Transaction entity created.");
    }
    public static Trxnsxctions savingsDTO(Trxnsxctions trxnsxctions) {
        Savings savings = null;
        if (trxnsxctions.getSavings() != null) {
            savings = Savings.builder()
                    .id(trxnsxctions.getSavings().getId())
                    .account(trxnsxctions.getSavings().getAccount())
                    .build();
        }
        return Trxnsxctions.builder()
                .id(trxnsxctions.getId())
                .trxnsxctiontype(trxnsxctions.getTrxnsxctiontype())
                .trxnsxctiondescription(trxnsxctions.getTrxnsxctiondescription())
                .amount(trxnsxctions.getAmount())
                .savings(savings) // Pass the created Checking object
                .build();
    }
    public static Trxnsxctions checkingDTO(Trxnsxctions trxnsxctions) {
        Checking checking = null;
        if (trxnsxctions.getChecking() != null) {
            checking = Checking.builder()
                    .id(trxnsxctions.getChecking().getId())
                    .account(trxnsxctions.getChecking().getAccount())
                    .build();
        }
        return Trxnsxctions.builder()
                .id(trxnsxctions.getId())
                .trxnsxctiontype(trxnsxctions.getTrxnsxctiontype())
                .trxnsxctiondescription(trxnsxctions.getTrxnsxctiondescription())
                .amount(trxnsxctions.getAmount())
                .checking(checking) // Pass the created Checking object
                .build();
    }

}
