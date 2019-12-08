package cn.edu.zju.kpaperproject.pojo;

public class TbOrder {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private String engineFactoryId;

    private String supplierId;

    private String engineToSupplierAp;

    private String supplierEngineToAp;

    private String supplierActualPriceP;

    private String supplierActualQualityQs;

    private String supplierActualNumberM;

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

    public String getEngineToSupplierAp() {
        return engineToSupplierAp;
    }

    public void setEngineToSupplierAp(String engineToSupplierAp) {
        this.engineToSupplierAp = engineToSupplierAp == null ? null : engineToSupplierAp.trim();
    }

    public String getSupplierEngineToAp() {
        return supplierEngineToAp;
    }

    public void setSupplierEngineToAp(String supplierEngineToAp) {
        this.supplierEngineToAp = supplierEngineToAp == null ? null : supplierEngineToAp.trim();
    }

    public String getSupplierActualPriceP() {
        return supplierActualPriceP;
    }

    public void setSupplierActualPriceP(String supplierActualPriceP) {
        this.supplierActualPriceP = supplierActualPriceP == null ? null : supplierActualPriceP.trim();
    }

    public String getSupplierActualQualityQs() {
        return supplierActualQualityQs;
    }

    public void setSupplierActualQualityQs(String supplierActualQualityQs) {
        this.supplierActualQualityQs = supplierActualQualityQs == null ? null : supplierActualQualityQs.trim();
    }

    public String getSupplierActualNumberM() {
        return supplierActualNumberM;
    }

    public void setSupplierActualNumberM(String supplierActualNumberM) {
        this.supplierActualNumberM = supplierActualNumberM == null ? null : supplierActualNumberM.trim();
    }
}