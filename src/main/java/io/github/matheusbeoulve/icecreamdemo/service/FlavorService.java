package io.github.matheusbeoulve.icecreamdemo.service;

import io.github.matheusbeoulve.icecreamdemo.domain.Flavor;
import io.github.matheusbeoulve.icecreamdemo.dto.FlavorDto;
import io.github.matheusbeoulve.icecreamdemo.repository.FlavorRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlavorService {

    private final FlavorRepository flavorRepository;

    @Autowired
    public FlavorService(FlavorRepository flavorRepository) {
        this.flavorRepository = flavorRepository;
    }

    @Transactional
    public List<FlavorDto> allFlavors() {
        return flavorRepository.findAll()
                .stream()
                .map(Flavor::flavorToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public FlavorDto newFlavor(@NonNull String name,
                               @NonNull BigDecimal price) {
        var f = Flavor.builder()
                .name(name)
                .currentPrice(price)
                .build();
        var savedFlavor = flavorRepository.save(f);
        return savedFlavor.flavorToDto();
    }

    @Transactional
    public Optional<FlavorDto> updateFlavor(@NonNull String flavorName, @NonNull BigDecimal newPrice) {
        return flavorRepository.findByName(flavorName)
                .map(flavor -> flavor.toBuilder()
                        .currentPrice(newPrice)
                        .build())
                .map(flavorRepository::save)
                .map(Flavor::flavorToDto);
    }
}
