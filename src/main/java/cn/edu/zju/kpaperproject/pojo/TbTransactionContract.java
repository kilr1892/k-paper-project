package cn.edu.zju.kpaperproject.pojo;

public class TbTransactionContract {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private String engineFactoryId;

    private String supplierId;

    private Integer engineFactoryNeedServiceNumber;

    private Integer orderPrice;

    private Integer orderQuality;

    private Double matchDegree;

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

    public Integer getEngineFactoryNeedServiceNumber() {
        return engineFactoryNeedServiceNumber;
    }

    public void setEngineFactoryNeedServiceNumber(Integer engineFactoryNeedServiceNumber) {
        this.engineFactoryNeedServiceNumber = engineFactoryNeedServiceNumber;
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderQuality() {
        return orderQuality;
    }

    public void setOrderQuality(Integer orderQuality) {
        this.orderQuality = orderQuality;
    }

    public Double getMatchDegree() {
        return matchDegree;
    }

    public void setMatchDegree(Double matchDegree) {
        this.matchDegree = matchDegree;
    }
}