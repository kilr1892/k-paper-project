package cn.edu.zju.kpaperproject.pojo;

public class EngineFactoryFinalProvision {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private String engineFactoryId;

    private Integer finalProductNumber;

    private Integer finalMarketPrice;

    private Integer finalMarketQuality;

    private Integer marketNeedNumber;

    private Integer actualSaleNumber;

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

    public Integer getFinalProductNumber() {
        return finalProductNumber;
    }

    public void setFinalProductNumber(Integer finalProductNumber) {
        this.finalProductNumber = finalProductNumber;
    }

    public Integer getFinalMarketPrice() {
        return finalMarketPrice;
    }

    public void setFinalMarketPrice(Integer finalMarketPrice) {
        this.finalMarketPrice = finalMarketPrice;
    }

    public Integer getFinalMarketQuality() {
        return finalMarketQuality;
    }

    public void setFinalMarketQuality(Integer finalMarketQuality) {
        this.finalMarketQuality = finalMarketQuality;
    }

    public Integer getMarketNeedNumber() {
        return marketNeedNumber;
    }

    public void setMarketNeedNumber(Integer marketNeedNumber) {
        this.marketNeedNumber = marketNeedNumber;
    }

    public Integer getActualSaleNumber() {
        return actualSaleNumber;
    }

    public void setActualSaleNumber(Integer actualSaleNumber) {
        this.actualSaleNumber = actualSaleNumber;
    }
}