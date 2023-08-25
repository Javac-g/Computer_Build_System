public class PartPrice {
    private String partName;
    private String merchantName;
    private BigDecimal price;
    private LocalDate date;
    
    public String getPartName(){
      return partName;
    }
    public String getMerchantName(){
      return merchantName;
    }
  public BigDecimal getPrice(){
      return price;
    }
  public LocalDate getDate(){
      return date;
    }
}
