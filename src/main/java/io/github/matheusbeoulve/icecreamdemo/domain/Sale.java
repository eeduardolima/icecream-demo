package io.github.matheusbeoulve.icecreamdemo.domain;

import io.github.matheusbeoulve.icecreamdemo.dto.FlavorDto;
import io.github.matheusbeoulve.icecreamdemo.dto.SaleDto;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "sales")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(updatable = false, nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SaleStatus status;

    @ManyToOne
    @JoinColumn
    private Flavor flavor;

    @CreationTimestamp
    private LocalDateTime creation;

    @UpdateTimestamp
    private LocalDateTime update;

    public SaleDto saleToDto() {
        return SaleDto.builder()
                .id(id)
                .flavorName(flavor.getName())
                .price(flavor.getCurrentPrice())
                .totalPrice(price)
                .build();
    }
}
