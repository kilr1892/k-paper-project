package cn.edu.zju.kpaperproject.pojo;

public class OrderPlus {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private String engineFactoryId;

    private String supplierId;

    private Double engineToSupplierAp;

    private Double supplierEngineToAp;

    private Boolean engineWhetherPerformContract;

    private Boolean supplierWhetherPerformContract;

    private Integer supplierActualPriceP;

    private Integer supplierActualQualityQs;

    private Integer supplierActualNumberM;

    private Integer engineFactoryToSupplierScore;

    private Integer supplierToEngineFactoryScore;

    private Double engineFactoryInitCredit;

    private Double engineFactoryNewCredit;

    private Integer engineFactoryToServiceOfferPriceLow;

    private Integer engineFactoryToServiceOfferPriceUpper;

    private Double supplierInitCredit;

    private Double supplierNewCredit;

    private Double relationshipStrength;

    private Integer engineFactoryProfit;

    private Integer supplierProfit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExperimentsNumber() {
        return experimentsNumber;
    }

    public void setExperimentsNumber(Integer experimentsNumber) {
        this.experimentsNumber = experimentsNumber;
    }

    public Integer getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(Integer cycleTimes) {
        this.cycleTimes = cycleTimes;
    }

    public String getEngineFactoryId() {
        return engineFactoryId;
    }

    public void setEngineFactoryId(String engineFactoryId) {
        this.engineFactoryId = engineFactoryId == null ? null : engineFactoryId.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public Double getEngineToSupplierAp() {
        return engineToSupplierAp;
    }

    public void setEngineToSupplierAp(Double engineToSupplierAp) {
        this.engineToSupplierAp = engineToSupplierAp;
    }

    public Double getSupplierEngineToAp() {
        return supplierEngineToAp;
    }

    public void setSupplierEngineToAp(Double supplierEngineToAp) {
        this.supplierEngineToAp = supplierEngineToAp;
    }

    public Boolean getEngineWhetherPerformContract() {
        return engineWhetherPerformContract;
    }

    public void setEngineWhetherPerformContract(Boolean engineWhetherPerformContract) {
        this.engineWhetherPerformContract = engineWhetherPerformContract;
    }

    public Boolean getSupplierWhetherPerformContract() {
        return supplierWhetherPerformContract;
    }

    public void setSupplierWhetherPerformContract(Boolean supplierWhetherPerformContract) {
        this.supplierWhetherPerformContract = supplierWhetherPerformContract;
    }

    public Integer getSupplierActualPriceP() {
        return supplierActualPriceP;
    }

    public void setSupplierActualPriceP(Integer supplierActualPriceP) {
        this.supplierActualPriceP = supplierActualPriceP;
    }

    public Integer getSupplierActualQualityQs() {
        return supplierActualQualityQs;
    }

    public void setSupplierActualQualityQs(Integer supplierActualQualityQs) {
        this.supplierActualQualityQs = supplierActualQualityQs;
    }

    public Integer getSupplierActualNumberM() {
        return supplierActualNumberM;
    }

    public void setSupplierActualNumberM(Integer supplierActualNumberM) {
        this.supplierActualNumberM = supplierActualNumberM;
    }

    public Integer getEngineFactoryToSupplierScore() {
        return engineFactoryToSupplierScore;
    }

    public void setEngineFactoryToSupplierScore(Integer engineFactoryToSupplierScore) {
        this.engineFactoryToSupplierScore = engineFactoryToSupplierScore;
    }

    public Integer getSupplierToEngineFactoryScore() {
        return supplierToEngineFactoryScore;
    }

    public void setSupplierToEngineFactoryScore(Integer supplierToEngineFactoryScore) {
        this.supplierToEngineFactoryScore = supplierToEngineFactoryScore;
    }

    public Double getEngineFactoryInitCredit() {
        return engineFactoryInitCredit;
    }

    public void setEngineFactoryInitCredit(Double engineFactoryInitCredit) {
        this.engineFactoryInitCredit = engineFactoryInitCredit;
    }

    public Double getEngineFactoryNewCredit() {
        return engineFactoryNewCredit;
    }

    public void setEngineFactoryNewCredit(Double engineFactoryNewCredit) {
        this.engineFactoryNewCredit = engineFactoryNewCredit;
    }

    public Integer getEngineFactoryToServiceOfferPriceLow() {
        return engineFactoryToServiceOfferPriceLow;
    }

    public void setEngineFactoryToServiceOfferPriceLow(Integer engineFactoryToServiceOfferPriceLow) {
        this.engineFactoryToServiceOfferPriceLow = engineFactoryToServiceOfferPriceLow;
    }

    public Integer getEngineFactoryToServiceOfferPriceUpper() {
        return engineFactoryToServiceOfferPriceUpper;
    }

    public void setEngineFactoryToServiceOfferPriceUpper(Integer engineFactoryToServiceOfferPriceUpper) {
        this.engineFactoryToServiceOfferPriceUpper = engineFactoryToServiceOfferPriceUpper;
    }

    public Double getSupplierInitCredit() {
        return supplierInitCredit;
    }

    public void setSupplierInitCredit(Double supplierInitCredit) {
        this.supplierInitCredit = supplierInitCredit;
    }

    public Double getSupplierNewCredit() {
        return supplierNewCredit;
    }

    public void setSupplierNewCredit(Double supplierNewCredit) {
        this.supplierNewCredit = supplierNewCredit;
    }

    public Double getRelationshipStrength() {
        return relationshipStrength;
    }

    public void setRelationshipStrength(Double relationshipStrength) {
        this.relationshipStrength = relationshipStrength;
    }

    public Integer getEngineFactoryProfit() {
        return engineFactoryProfit;
    }

    public void setEngineFactoryProfit(Integer engineFactoryProfit) {
        this.engineFactoryProfit = engineFactoryProfit;
    }

    public Integer getSupplierProfit() {
        return supplierProfit;
    }

    public void setSupplierProfit(Integer supplierProfit) {
        this.supplierProfit = supplierProfit;
    }
}