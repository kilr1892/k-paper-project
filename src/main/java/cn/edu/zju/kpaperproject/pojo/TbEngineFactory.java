package cn.edu.zju.kpaperproject.pojo;

public class TbEngineFactory {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private String engineFactoryId;

    private Integer engineFactoryLocationGX;

    private Integer engineFactoryLocationGY;

    private Integer engineFactoryFixedCostC;

    private Boolean engineFactoryAlive;

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

    public Integer getEngineFactoryLocationGX() {
        return engineFactoryLocationGX;
    }

    public void setEngineFactoryLocationGX(Integer engineFactoryLocationGX) {
        this.engineFactoryLocationGX = engineFactoryLocationGX;
    }

    public Integer getEngineFactoryLocationGY() {
        return engineFactoryLocationGY;
    }

    public void setEngineFactoryLocationGY(Integer engineFactoryLocationGY) {
        this.engineFactoryLocationGY = engineFactoryLocationGY;
    }

    public Integer getEngineFactoryFixedCostC() {
        return engineFactoryFixedCostC;
    }

    public void setEngineFactoryFixedCostC(Integer engineFactoryFixedCostC) {
        this.engineFactoryFixedCostC = engineFactoryFixedCostC;
    }

    public Boolean getEngineFactoryAlive() {
        return engineFactoryAlive;
    }

    public void setEngineFactoryAlive(Boolean engineFactoryAlive) {
        this.engineFactoryAlive = engineFactoryAlive;
    }
}