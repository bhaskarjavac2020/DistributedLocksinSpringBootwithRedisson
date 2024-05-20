package com.poc.DistributedLocksinSpringBootwithRedisson.controller;

import com.poc.DistributedLocksinSpringBootwithRedisson.service.LockedResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private LockedResourceService lockedResourceService;

    @GetMapping("/test-lock")
    public String testLock(@RequestParam String key) {
        lockedResourceService.accessCriticalSection(key);
        return "Check the console output";
    }
}
