package com.rewards.service;

import com.rewards.model.Customer;
import com.rewards.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldAddCustomer() {

        Customer customer = new Customer();
        customer.setName("Test");

        Customer customerAdded = customerService.addCustomer(customer);

        assertThat(customerAdded.getId(), equalTo(1));
    }

    @Test
    public void shouldGetCustomer() {

        Customer customer = new Customer();
        customer.setName("Test");

        customerService.addCustomer(customer);

        Customer existingCustomer = customerService.getCustomer(1L);

        assertThat(existingCustomer.getId(), equalTo(1L));
        assertThat(existingCustomer.getName(), equalTo("Test"));
    }
}
