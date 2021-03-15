package com.rewards.model.dto;

import com.rewards.model.Transaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Rewards {

    private Long customerId;

    private String customerName;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    private Long totalPoints;

    private List<Transaction> transactions;
}
