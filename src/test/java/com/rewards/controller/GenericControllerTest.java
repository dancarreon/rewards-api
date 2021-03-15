package com.rewards.controller;

import com.rewards.service.CustomerService;
import com.rewards.service.RewardsService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class GenericControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected CustomerService customerService;

    @MockBean
    protected RewardsService rewardsService;

    @Before
    public void setUp() {
        Mockito.reset(customerService, rewardsService);
    }
}
