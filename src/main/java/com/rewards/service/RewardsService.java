package com.rewards.service;

import com.rewards.model.Transaction;
import com.rewards.model.dto.Rewards;
import com.rewards.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class RewardsService {

    private final CustomerRepository customerRepository;

    public RewardsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Rewards getCustomerRewards(Long customerId) {

        Rewards rewards = new Rewards();

        return customerRepository.findById(customerId).map(customer -> {

            List<Transaction> transactionList = new LinkedList<>();
            LocalDateTime toDate = LocalDateTime.now();
            LocalDateTime fromDate = toDate.minusDays(90);

            customer.getTransactions().forEach(transaction -> {

                if (transaction.getDate().isAfter(fromDate)) {

                    Double amount = transaction.getAmount();

                    if (amount >= 100) {
                        Long points = (long) (((amount - 100) * 2) + (50));
                        rewards.setTotalPoints(rewards.getTotalPoints() != null ? rewards.getTotalPoints() + points : points);
                    }

                    transactionList.add(transaction);
                }
            });

            rewards.setCustomerId(customer.getId());
            rewards.setCustomerName(customer.getName());
            rewards.setTransactions(transactionList);
            rewards.setToDate(toDate);
            rewards.setFromDate(fromDate);

            return rewards;

        }).orElseThrow(IllegalArgumentException::new);
    }
}
