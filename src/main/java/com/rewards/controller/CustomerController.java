package com.rewards.controller;

import com.rewards.model.Customer;
import com.rewards.model.Transaction;
import com.rewards.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping(value = "/{customerId}/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getCustomerTransactions(@PathVariable Long customerId) {
        return customerService.getCustomerTransactions(customerId);
    }

    @PostMapping(value = "/{customerId}/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction addCustomerTransaction(@PathVariable Long customerId, @Valid @RequestBody Transaction transaction) {
        return customerService.addCustomerTransaction(customerId, transaction);
    }
}
