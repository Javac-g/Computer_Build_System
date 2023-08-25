@Configuration
@EnableScheduling
public class ScheduleConfig {
 
    @Autowired
    private final Gpu_price_history gpu_price_history;
    @Autowired
    private final Motherboard_price_history motherboard_price_history;
    @Autowired
    private final Storage_price_history storage_price_history;
    @Autowired
    private final Ram_price_history ram_price_history;
	@Autowired
	private final ComputerCase_price_history computerCase_price_history;
    @Autowired
	private final PowerSupply_price_history powerSupply_price_history;
    @Autowired
	private final CpuCooler_price_history cpuCooler_price_history;
    @Autowired
    private Cpu_price_history cpu_price_history;
    
    @Scheduled(cron = "0 0 * * * *")
    public void updateCpuPriceHistory() {
        cpu_price_history.updatePriceHistory();
    }
    @Scheduled(cron = "0 0 * * * *")
    public void updateGpuPriceHistory() {
        gpu_price_history.updatePriceHistory();
    }
    @Scheduled(cron = "0 0 * * * *")
    public void updateRamPriceHistory() {
        ram_price_history.updatePriceHistory();
    }
    @Scheduled(cron = "0 0 * * * *")
    public void updateMotherboardPriceHistory() {
        motherboard_price_history.updatePriceHistory();
    }
    @Scheduled(cron = "0 0 * * * *")
    public void updateStoragePriceHistory() {
        storage_price_history.updatePriceHistory();
    }
     @Scheduled(cron = "0 0 * * * *")
    public void updateCpuCoolerPriceHistory() {
        cpuCooler_price_history.updatePriceHistory();
    }
    @Scheduled(cron = "0 0 * * * *")
    public void updateComputerCasePriceHistory() {
        computerCase_price_history.updatePriceHistory();
    }
    @Scheduled(cron = "0 0 * * * *")
    public void updatePowerSupplyPriceHistory() {
        powerSuply_price_history.updatePriceHistory();
    }
  
}
