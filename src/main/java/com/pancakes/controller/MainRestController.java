package com.pancakes.controller;

import com.pancakes.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {

    private SchedulerService schedulerService;

    @Autowired
    public MainRestController(SchedulerService schedulerService) {
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
