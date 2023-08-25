package com.anobel.model.price;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cpu_price_history")
public class Cpu_price_history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "start_price")
    private float start_price;
    @Column(name = "last_price")
    private float last_price;
    @Column(name = "start_price_date")
    private LocalDateTime start_price_date;
    @Column(name = "last_price_date")
    private LocalDateTime last_price_date;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void updatePriceHistory() {
        List<Cpu> cpus = partPriceService.getPrices();
        
        for (Cpu cpu : cpus) {
            String sql = "INSERT INTO cpu_price_history (id, last_price, last_date) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, cpu.getId(), cpu.getLastPrice()cpu.getLast_date());
        }
    }
}
