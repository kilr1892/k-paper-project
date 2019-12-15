package cn.edu.zju.kpaperproject.pojo;

public class TbSupplierDynamic {
    private Integer id;

    private Integer cycleTimes;

    private String supplierId;

    private Integer supplierTotalAssetsP;

    private Double supplierCreditA;

    private Integer supplierCapacityM;

    private Integer supplierPricePL;

    private Integer supplierPricePU;

    private Integer supplierQualityQs;

    private Double supplierCapacityUtilization;

    private Integer supplierType;

    private Integer avgPrice;

    private Integer avgQuality;

    private Integer supplierActualSaleNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(Integer cycleTimes) {
        this.cycleTimes = cycleTimes;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public Integer getSupplierTotalAssetsP() {
        return supplierTotalAssetsP;
    }

    public void setSupplierTotalAssetsP(Integer supplierTotalAssetsP) {
        this.supplierTotalAssetsP = supplierTotalAssetsP;
    }

    public Double getSupplierCreditA() {
        return supplierCreditA;
    }

    public void setSupplierCreditA(Double supplierCreditA) {
        this.supplierCreditA = supplierCreditA;
    }

    public Integer getSupplierCapacityM() {
        return supplierCapacityM;
    }

    public void setSupplierCapacityM(Integer supplierCapacityM) {
        this.supplierCapacityM = supplierCapacityM;
    }

    public Integer getSupplierPricePL() {
        return supplierPricePL;
    }

    public void setSupplierPricePL(Integer supplierPricePL) {
        this.supplierPricePL = supplierPricePL;
    }

    public Integer getSupplierPricePU() {
        return supplierPricePU;
    }

    public void setSupplierPricePU(Integer supplierPricePU) {
        this.supplierPricePU = supplierPricePU;
    }

    public Integer getSupplierQualityQs() {
        return supplierQualityQs;
    }

    public void setSupplierQualityQs(Integer supplierQualityQs) {
        this.supplierQualityQs = supplierQualityQs;
    }

    public Double getSupplierCapacityUtilization() {
        return supplierCapacityUtilization;
    }

    public void setSupplierCapacityUtilization(Double supplierCapacityUtilization) {
        this.supplierCapacityUtilization = supplierCapacityUtilization;
    }

    public Integer getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Integer supplierType) {
        this.supplierType = supplierType;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Integer getAvgQuality() {
        return avgQuality;
    }

    public void setAvgQuality(Integer avgQuality) {
        this.avgQuality = avgQuality;
    }

    public Integer getSupplierActualSaleNumber() {
        return supplierActualSaleNumber;
    }

    public void setSupplierActualSaleNumber(Integer supplierActualSaleNumber) {
        this.supplierActualSaleNumber = supplierActualSaleNumber;
    }
}