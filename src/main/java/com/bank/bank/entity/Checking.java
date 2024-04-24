package com.bank.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "checking")
@Data
@AllArgsConstructor
@Builder
public class Checking {

    private static final Logger logger = LoggerFactory.getLogger(Checking.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    @Column
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @Column
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer available_balance;
    @Column
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer present_balance;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @OneToMany(mappedBy = "checking", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trxnsxctions> trxnsxctions;

    public Checking(){
        logger.debug("Checking entity created.");
    }
    public static Checking checkingDTO(Checking checking){
        return Checking.builder()
                .id(checking.getId())
                .name(checking.getName())
                .available_balance(checking.getAvailable_balance())
                .present_balance(checking.getPresent_balance())
                .account(checking.getAccount())
                .trxnsxctions(checking.getTrxnsxctions())
                .build();
    }
}
