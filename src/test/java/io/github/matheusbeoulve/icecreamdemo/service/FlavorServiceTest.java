package io.github.matheusbeoulve.icecreamdemo.service;

import io.github.matheusbeoulve.icecreamdemo.domain.Flavor;
import io.github.matheusbeoulve.icecreamdemo.dto.FlavorDto;
import io.github.matheusbeoulve.icecreamdemo.repository.FlavorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlavorServiceTest {

    @Mock
    FlavorRepository flavorRepository;

    @InjectMocks
    FlavorService flavorService;

    @Test
    void shouldReturnAllFlavors() {
        var laranja = Flavor.builder()
                .id(1L)
                .name("laranja")
                .currentPrice(BigDecimal.ONE)
                .build();

        when(flavorRepository.findAll())
                .thenReturn(List.of(laranja));

        var actual = flavorService.allFlavors();

        var expected = FlavorDto.builder()
                .id(1L)
                .name("laranja")
                .currentPrice(BigDecimal.ONE)
                .build();

        assertEquals(List.of(expected), actual);
    }
}
