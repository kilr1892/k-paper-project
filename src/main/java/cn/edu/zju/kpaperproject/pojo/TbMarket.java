package cn.edu.zju.kpaperproject.pojo;

public class TbMarket {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private String marketActualDemandAd;

    private String engineFactoryId;

    private String engineFactoryActualSellingAm;

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

    public String getMarketActualDemandAd() {
        return marketActualDemandAd;
    }

    public void setMarketActualDemandAd(String marketActualDemandAd) {
        this.marketActualDemandAd = marketActualDemandAd == null ? null : marketActualDemandAd.trim();
    }

    public String getEngineFactoryId() {
        return engineFactoryId;
    }

    public void setEngineFactoryId(String engineFactoryId) {
        this.engineFactoryId = engineFactoryId == null ? null : engineFactoryId.trim();
    }

    public String getEngineFactoryActualSellingAm() {
        return engineFactoryActualSellingAm;
    }

    public void setEngineFactoryActualSellingAm(String engineFactoryActualSellingAm) {
        this.engineFactoryActualSellingAm = engineFactoryActualSellingAm == null ? null : engineFactoryActualSellingAm.trim();
    }
}