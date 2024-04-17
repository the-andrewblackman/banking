package com.bank.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "savings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Savings {
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Account account;
    @OneToMany(mappedBy = "savings", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Trxnsxctions> trxnsxctions;

    public static Savings savingsDTO(Savings savings){
        return Savings.builder()
                .id(savings.getId())
                .name(savings.getName())
                .available_balance(savings.getAvailable_balance())
                .present_balance(savings.getPresent_balance())
                .account(savings.getAccount())
                .trxnsxctions(savings.getTrxnsxctions())
                .build();
    }
}
