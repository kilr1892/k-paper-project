package cn.edu.zju.kpaperproject.pojo;

public class TbSupplier {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private String supplierId;

    private Integer supplierLocationGX;

    private Integer supplierLocationGY;

    private Integer supplierType;

    private Integer supplierFixedCostC;

    private Boolean supplierAlive;

    private Integer supplierAliveTimes;

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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public Integer getSupplierLocationGX() {
        return supplierLocationGX;
    }

    public void setSupplierLocationGX(Integer supplierLocationGX) {
        this.supplierLocationGX = supplierLocationGX;
    }

    public Integer getSupplierLocationGY() {
        return supplierLocationGY;
    }

    public void setSupplierLocationGY(Integer supplierLocationGY) {
        this.supplierLocationGY = supplierLocationGY;
    }

    public Integer getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Integer supplierType) {
        this.supplierType = supplierType;
    }

    public Integer getSupplierFixedCostC() {
        return supplierFixedCostC;
    }

    public void setSupplierFixedCostC(Integer supplierFixedCostC) {
        this.supplierFixedCostC = supplierFixedCostC;
    }

    public Boolean getSupplierAlive() {
        return supplierAlive;
    }

    public void setSupplierAlive(Boolean supplierAlive) {
        this.supplierAlive = supplierAlive;
    }

    public Integer getSupplierAliveTimes() {
        return supplierAliveTimes;
    }

    public void setSupplierAliveTimes(Integer supplierAliveTimes) {
        this.supplierAliveTimes = supplierAliveTimes;
    }
}