package com.poc.DistributedLocksinSpringBootwithRedisson.service;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Service
public class LockedResourceService {
    @Autowired
    private RedissonClient redissonClient;
    public void accessCriticalSection(String key) {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
       // String generatedString = new String(array, StandardCharsets.UTF_8);
        String generatedString = "RAJA";
        var lock = redissonClient.getLock(key);
        try {
            // Acquire the lock
            boolean isLocked = lock.tryLock();

            if (isLocked) {
                try {
                    // Critical section code goes here
                    System.out.println("Accessing the critical section"+generatedString);
                    // Simulate some work
                    Thread.sleep(10000);
                } finally {
                    // Ensure the lock is released even if an exception occurs
                    lock.unlock();
                }
            } else {
                System.out.println("Could not acquire the lock");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
