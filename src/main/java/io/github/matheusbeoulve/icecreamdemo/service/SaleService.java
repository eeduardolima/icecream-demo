package io.github.matheusbeoulve.icecreamdemo.service;

import io.github.matheusbeoulve.icecreamdemo.domain.Flavor;
import io.github.matheusbeoulve.icecreamdemo.domain.Inventory;
import io.github.matheusbeoulve.icecreamdemo.domain.Sale;
import io.github.matheusbeoulve.icecreamdemo.domain.SaleStatus;
import io.github.matheusbeoulve.icecreamdemo.dto.SaleDto;
import io.github.matheusbeoulve.icecreamdemo.repository.FlavorRepository;
import io.github.matheusbeoulve.icecreamdemo.repository.InventoryRepository;
import io.github.matheusbeoulve.icecreamdemo.repository.SaleRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.lang.management.RuntimeMXBean;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final FlavorRepository flavorRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, FlavorRepository flavorRepository, InventoryRepository inventoryRepository) {
        this.saleRepository = saleRepository;
        this.flavorRepository = flavorRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public List<SaleDto> allSales() {
        return saleRepository.findAll()
                .stream()
                .map(Sale::saleToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SaleDto newSale(@NonNull String flavorName,
                           @NonNull BigDecimal quantity) {

        Flavor flavor = flavorRepository.findByName(flavorName).orElseThrow(RuntimeException::new);

        Inventory inventory = inventoryRepository.findByFlavor(flavor).orElseThrow(RuntimeException::new);
        BigDecimal newStock = inventory.getStock().subtract(quantity);

        // criar validacao para validar se há quantidade disponível em estoque
//        if (inventory.getStock() < newStock){
//            throw new RuntimeException("Quantidade em estoque inferior ao solicitado.");
//        }

        Inventory i = flavorRepository.findByName(flavorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getInventory()
                .toBuilder()
                .stock(newStock)
                .build();
        inventoryRepository.save(i);

        var s = Sale.builder()
                .flavor(flavor)
                .price(flavor.getCurrentPrice().multiply(quantity))
                .status(SaleStatus.COMPLETED)
                .build();
        var savedSale = saleRepository.save(s);
        return savedSale.saleToDto();
    }

    // criar update para colocar a venda no status cancelado
    @Transactional
    public Optional<SaleDto> updateSale(@NonNull Long id, @NonNull BigDecimal newPrice) {
        return saleRepository.findById(id)
                .map(sale -> sale.toBuilder()
                        .price(newPrice)
                        .build())
                .map(saleRepository::save)
                .map(Sale::saleToDto);
    }
}
