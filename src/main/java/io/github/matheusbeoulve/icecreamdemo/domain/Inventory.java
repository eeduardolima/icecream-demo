package io.github.matheusbeoulve.icecreamdemo.domain;

import io.github.matheusbeoulve.icecreamdemo.dto.InventoryDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "inventory")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private Flavor flavor;

    @Column(nullable = false)
    private Long stock;

    @CreationTimestamp
    private LocalDateTime creation;

    @UpdateTimestamp
    private LocalDateTime update;

    public InventoryDto toInventoryDto() {
        return InventoryDto.builder()
                .id(id)
                .stock(stock)
                .flavor(flavor.getName())
                .build();
    }
}
