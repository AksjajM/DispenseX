package oose2324.group5.dispenseX.Controller;

import oose2324.group5.dispenseX.Model.Interval;
import oose2324.group5.dispenseX.Service.IntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateIntervalController {
    private final IntervalService service;

    @Autowired
    public CreateIntervalController(IntervalService service) {
        this.service = service;
    }

    @PostMapping("/create-interval")
    public ResponseEntity<Interval> createInterval(@Validated @RequestBody Interval interval) {
        Interval createdInterval = service.createInterval(interval);

        // Return the created status with a 201 Created status code
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInterval);
    }
}
