@Configuration
@EnableScheduling
public class ScheduleConfig {
    @Autowired
    private Cpu_price_history cpu_price_history;
    
    @Scheduled(cron = "0 0 * * * *")
    public void updateCpuPriceHistory() {
        cpu_price_history.updatePriceHistory();
    }
    @Scheduled(cron = "0 0 * * * *")
    public void updateGpuPriceHistory() {
        cpu_price_history.updatePriceHistory();
    }
    @Scheduled(cron = "0 0 * * * *")
    public void updateRamPriceHistory() {
        cpu_price_history.updatePriceHistory();
    }
    @Scheduled(cron = "0 0 * * * *")
    public void updateMotherboardPriceHistory() {
        cpu_price_history.updatePriceHistory();
    }
    @Scheduled(cron = "0 0 * * * *")
    public void updateStoragePriceHistory() {
        cpu_price_history.updatePriceHistory();
    }
}
