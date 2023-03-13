package io.github.matheusbeoulve.icecreamdemo.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Builder
@Jacksonized
public class SaleDto {

    Long id;
    String flavorName;
    BigDecimal price;
    BigDecimal totalPrice;
}
