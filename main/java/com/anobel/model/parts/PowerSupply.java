
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.anobel.model.price.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "power_supply_list")
public class PowerSupply {

    @Id
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "brand")
    private String brand;
    
    @Column(name = "model")
    private String model;
    
    @Column(name = "wattage")
    private Integer wattage;
    
    @Column(name = "efficiency")
    private String efficiency;
    
    @Column(name = "length")
    private Integer length;
    
    @Column(name = "power_connector")
    private String powerConnector;
    
    
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_id")
	private PowerSupply_price_history powerSupply_price_history;
    
    
}
