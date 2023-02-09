package io.github.matheusbeoulve.icecreamdemo.domain;

import io.github.matheusbeoulve.icecreamdemo.dto.FlavorDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "flavors")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Flavor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(unique = true, updatable = false, nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal currentPrice;

    @OneToMany(mappedBy = "flavor")
    private List<Sale> sales;

    @OneToOne(mappedBy = "flavor")
    private Inventory inventory;

    @CreationTimestamp
    private LocalDateTime creation;

    @UpdateTimestamp
    private LocalDateTime update;

    public FlavorDto flavorToDto() {
        return FlavorDto.builder()
                .id(id)
                .name(name)
                .currentPrice(currentPrice)
                .build();
    }
}
