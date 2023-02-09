package io.github.matheusbeoulve.icecreamdemo.controller;

import io.github.matheusbeoulve.icecreamdemo.dto.FlavorDto;
import io.github.matheusbeoulve.icecreamdemo.service.FlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flavor")
public class FlavorController {

    private final FlavorService flavorService;

    @Autowired
    public FlavorController(FlavorService flavorService) {
        this.flavorService = flavorService;
    }

    @GetMapping
    public ResponseEntity<List<FlavorDto>> inStockFlavors() {
        return ResponseEntity.ok(flavorService.allFlavors());
    }

    @PostMapping
    public ResponseEntity<FlavorDto> newFlavor(@RequestParam String name,
                                               @RequestParam BigDecimal price) {
        return ResponseEntity.ok(flavorService.newFlavor(name, price));
    }

    @PutMapping("/{name}")
    public ResponseEntity<FlavorDto> updateFlavor(@PathVariable String name,
                                                  @RequestParam("new_price") BigDecimal newPrice) {
        return ResponseEntity.of(flavorService.updateFlavor(name, newPrice));
    }
}
