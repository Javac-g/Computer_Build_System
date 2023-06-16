package com.anobel.model;

import com.anobel.model.parts.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Assembly")
public class Assembly {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assembly_generator")
	@SequenceGenerator(name = "assembly_generator", sequenceName = "assembly_seq", allocationSize = 1)
    @Column(name = "id",nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purpose_id")
    private Purpose purpose;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpu_id")
    private Cpu cpu;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_board_id")
    private Motherboard motherboard;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ram_id")
    private Ram ram;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpu_id")
    private Gpu gpu;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "power_supply_id")
	private PowerSupply powerSupply;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computer_case_id")
	private ComputerCase computerCase;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cpu_cooler_id")
	private CpuCooler cpuCooler;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Assembly assembly = (Assembly) o;
        return getId() != null && getId().equals(assembly.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
	


}
