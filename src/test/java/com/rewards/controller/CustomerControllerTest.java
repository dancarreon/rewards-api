package com.rewards.controller;

import com.rewards.model.Customer;
import com.rewards.model.Transaction;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends GenericControllerTest {

    @Test
    public void shouldAddCustomer() throws Exception {

        String customer = "{\"name\":\"Test\"}";

        mockMvc.perform(post("/customer")
                .content(customer)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Test");

        when(customerService.getCustomer(1L)).thenReturn(customer);

        mockMvc.perform(get("/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test")));
    }

    @Test
    public void shouldAddTransaction() throws Exception {

        String transaction = "{\"amount\":\"120\",\"date\":\"2021-03-01T00:00:00.000\"}";

        mockMvc.perform(post("/customer/1/transactions")
                .content(transaction)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetTransaction() throws Exception {

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(120.0);
        transaction.setDate(LocalDateTime.now());

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        when(customerService.getCustomerTransactions(1L)).thenReturn(transactionList);

        mockMvc.perform(get("/customer/1/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].amount", is(120.0)))
                .andExpect(jsonPath("$[0].date", not("")));
    }
}
