package com.anobel.model.parts;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.anobel.model.price.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cpu_cooler_list")
public class CpuCooler {
   @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "height")
    private Integer height;

    @Column(name = "fan_size_mm")
    private Integer fanSize;

    @Column(name = "wattage")
    private Integer wattage;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
	private CpuCooler_price_history cpuCooler_price_history;
    


    

  
}