package com.rewards.service;

import com.rewards.model.Customer;
import com.rewards.model.Transaction;
import com.rewards.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Transaction> getCustomerTransactions(Long customerId) {
        return customerRepository.findById(customerId)
                .map(Customer::getTransactions)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Transaction addCustomerTransaction(Long customId, Transaction transaction) {
        return customerRepository.findById(customId).map(customer -> {
            customer.getTransactions().add(transaction);
            Customer savedCustomer = customerRepository.save(customer);
            return savedCustomer.getTransactions().get(savedCustomer.getTransactions().size() - 1);
        }).orElseThrow(IllegalArgumentException::new);
    }
}
