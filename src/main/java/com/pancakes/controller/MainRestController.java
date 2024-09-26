package com.pancakes.controller;

import com.pancakes.model.Product;
import com.pancakes.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

    private SchedulerService<Product> schedulerService;

    @Autowired
    public MainRestController(SchedulerService<Product> schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping("/start")
    public ResponseEntity<?> home(@RequestParam int duration) {

        schedulerService.setInterval(duration);
        schedulerService.scheduleCall();

        return ResponseEntity.ok()
                .body("Scheduler Started at " + duration + " min interval");
    }

    @PostMapping("/stop")
    public ResponseEntity<?> home() {
        schedulerService.stopScheduler();

        return ResponseEntity.ok()
                .body("Scheduler Stopped");
    }
}
