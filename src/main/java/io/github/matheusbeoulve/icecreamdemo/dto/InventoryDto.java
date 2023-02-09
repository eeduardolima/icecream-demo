package io.github.matheusbeoulve.icecreamdemo.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class InventoryDto {
    Long id;
    String flavor;
    Long stock;
}
