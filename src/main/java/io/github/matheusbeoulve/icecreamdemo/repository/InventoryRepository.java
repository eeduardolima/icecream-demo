package io.github.matheusbeoulve.icecreamdemo.repository;

import io.github.matheusbeoulve.icecreamdemo.domain.Flavor;
import io.github.matheusbeoulve.icecreamdemo.domain.Inventory;
import org.aspectj.weaver.patterns.IVerificationRequired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByFlavor(Flavor flavor);

}
