package com.rewards.controller;

import com.rewards.model.Customer;
import com.rewards.model.Transaction;
import com.rewards.model.dto.Rewards;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RewardsControllerTest extends GenericControllerTest {

    @Test
    public void shouldGetRewards() throws Exception {

        Rewards rewards = new Rewards();
        rewards.setCustomerId(1L);
        rewards.setCustomerName("Test");
        rewards.setTotalPoints(90L);

        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(120.0);
        transaction.setDate(LocalDateTime.now());
        transactionList.add(transaction);

        rewards.setTransactions(transactionList);

        when(rewardsService.getCustomerRewards(1L)).thenReturn(rewards);

        mockMvc.perform(get("/customer/1/rewards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(1)))
                .andExpect(jsonPath("$.customerName", is("Test")))
                .andExpect(jsonPath("$.totalPoints", is(90)))
                .andExpect(jsonPath("$.transactions", iterableWithSize(1)));
    }
}
