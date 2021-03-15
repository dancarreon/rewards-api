package com.rewards.controller;

import com.rewards.model.dto.Rewards;
import com.rewards.service.RewardsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer/{customerId}/rewards")
public class RewardsController {

    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Rewards getCustomerRewards(@PathVariable Long customerId) {
        return rewardsService.getCustomerRewards(customerId);
    }
}
