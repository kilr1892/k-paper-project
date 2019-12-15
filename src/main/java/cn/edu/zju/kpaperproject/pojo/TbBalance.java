package cn.edu.zju.kpaperproject.pojo;

public class TbBalance {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private Double engineFactoryBalance;

    private Double supplierBalance;

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

    public Double getEngineFactoryBalance() {
        return engineFactoryBalance;
    }

    public void setEngineFactoryBalance(Double engineFactoryBalance) {
        this.engineFactoryBalance = engineFactoryBalance;
    }

    public Double getSupplierBalance() {
        return supplierBalance;
    }

    public void setSupplierBalance(Double supplierBalance) {
        this.supplierBalance = supplierBalance;
    }
}