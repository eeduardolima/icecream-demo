package io.github.matheusbeoulve.icecreamdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Builder
@Jacksonized
public class FlavorDto {

    Long id;
    String name;
    @JsonProperty("current_price") BigDecimal currentPrice;
}
