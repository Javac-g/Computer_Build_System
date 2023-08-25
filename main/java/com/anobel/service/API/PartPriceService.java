@Service
public class PartPriceService {
    private final String API_ENDPOINT = "https://api.example.com/prices";
    
    @Autowired
    private RestTemplate restTemplate;
    
    public List<PartPrice> getPrices() {
        ResponseEntity<PartPrice[]> response = restTemplate.getForEntity(API_ENDPOINT, PartPrice[].class);
        return Arrays.asList(response.getBody());
    }
}
