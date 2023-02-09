package io.github.matheusbeoulve.icecreamdemo.service;

import io.github.matheusbeoulve.icecreamdemo.domain.Flavor;
import io.github.matheusbeoulve.icecreamdemo.domain.Inventory;
import io.github.matheusbeoulve.icecreamdemo.dto.InventoryDto;
import io.github.matheusbeoulve.icecreamdemo.repository.FlavorRepository;
import io.github.matheusbeoulve.icecreamdemo.repository.InventoryRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final FlavorRepository flavorRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(FlavorRepository flavorRepository,
                            InventoryRepository inventoryRepository) {
        this.flavorRepository = flavorRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public List<InventoryDto> fullInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(Inventory::toInventoryDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public InventoryDto newInventory(@NonNull String flavorName,
                                     @NonNull Long stock) {
        Flavor f = flavorRepository.findByName(flavorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Inventory i = Inventory.builder()
                .flavor(f)
                .stock(stock)
                .build();
        return inventoryRepository.save(i).toInventoryDto();
    }

    @Transactional
    public InventoryDto updateInventory(String flavorName, Long newStock) {
        Inventory i = flavorRepository.findByName(flavorName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getInventory()
                .toBuilder()
                .stock(newStock)
                .build();
        return inventoryRepository.save(i).toInventoryDto();
    }
}
