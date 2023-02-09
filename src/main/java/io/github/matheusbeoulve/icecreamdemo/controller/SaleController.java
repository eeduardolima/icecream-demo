package io.github.matheusbeoulve.icecreamdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventory/flavor")
public class SaleController {

    @GetMapping
    public ResponseEntity<?> allSales() {
        return ResponseEntity.ok().build();
    }
}
