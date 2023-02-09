package io.github.matheusbeoulve.icecreamdemo.repository;

import io.github.matheusbeoulve.icecreamdemo.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {}
