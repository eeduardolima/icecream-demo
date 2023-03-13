package io.github.matheusbeoulve.icecreamdemo.controller;

import io.github.matheusbeoulve.icecreamdemo.domain.Flavor;
import io.github.matheusbeoulve.icecreamdemo.dto.InventoryDto;
import io.github.matheusbeoulve.icecreamdemo.dto.SaleDto;
import io.github.matheusbeoulve.icecreamdemo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sale")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleDto>> allSales() {
        return ResponseEntity.ok(saleService.allSales());
    }

    @PostMapping
    public ResponseEntity<SaleDto> newSale(@RequestParam String flavorName,
                                           @RequestParam BigDecimal quantity) {
        return ResponseEntity.ok(saleService.newSale(flavorName, quantity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDto> updateSale(@PathVariable Long id,
                                              @RequestParam("new_price") BigDecimal newPrice){
        return ResponseEntity.of(saleService.updateSale(id, newPrice));
    }
}