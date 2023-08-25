@Configuration
@EnableScheduling
public class ScheduleConfig {
    @Autowired
    private PriceHistoryService priceHistoryService;
    
    @Scheduled(cron = "0 0 * * * *")
    public void updatePriceHistory() {
        priceHistoryService.updatePriceHistory();
    }
}
