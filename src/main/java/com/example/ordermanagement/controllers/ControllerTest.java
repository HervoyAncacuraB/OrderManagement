package com.example.ordermanagement.controllers;

import com.example.ordermanagement.domain.OrderDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class ControllerTest {


    private static final Logger log = LoggerFactory.getLogger(ControllerTest.class);

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> handlePostRequest(@Valid @RequestBody OrderDTO orderRequestDTO) {
        log.info("orderRequestDTO" + orderRequestDTO);
        return ResponseEntity.ok().body(orderRequestDTO);
    }
}
