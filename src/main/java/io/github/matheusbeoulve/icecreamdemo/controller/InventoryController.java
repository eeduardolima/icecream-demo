package io.github.matheusbeoulve.icecreamdemo.controller;

import io.github.matheusbeoulve.icecreamdemo.dto.InventoryDto;
import io.github.matheusbeoulve.icecreamdemo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory/flavor")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryDto>> fullInventory() {
        return ResponseEntity.ok(inventoryService.fullInventory());
    }

    @PostMapping
    public ResponseEntity<InventoryDto> newInventory(@RequestParam String flavor,
                                                     @RequestParam BigDecimal stock) {
        return ResponseEntity.ok(inventoryService.newInventory(flavor, stock));
    }

    @PutMapping("/{flavorName}")
    public ResponseEntity<InventoryDto> updateInventory(@PathVariable String flavorName,
                                                        @RequestParam BigDecimal newStock) {
        return ResponseEntity.ok(inventoryService.updateInventory(flavorName, newStock));
    }
}
