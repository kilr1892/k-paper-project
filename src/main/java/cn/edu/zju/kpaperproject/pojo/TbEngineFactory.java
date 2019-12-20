package cn.edu.zju.kpaperproject.pojo;

public class TbEngineFactory {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private String engineFactoryId;

    private Double engineFactoryLocationGX;

    private Double engineFactoryLocationGY;

    private Boolean engineFactoryAlive;

    private Integer engineFactoryAliveTimes;

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

    public Double getEngineFactoryLocationGX() {
        return engineFactoryLocationGX;
    }

    public void setEngineFactoryLocationGX(Double engineFactoryLocationGX) {
        this.engineFactoryLocationGX = engineFactoryLocationGX;
    }

    public Double getEngineFactoryLocationGY() {
        return engineFactoryLocationGY;
    }

    public void setEngineFactoryLocationGY(Double engineFactoryLocationGY) {
        this.engineFactoryLocationGY = engineFactoryLocationGY;
    }

    public Boolean getEngineFactoryAlive() {
        return engineFactoryAlive;
    }

    public void setEngineFactoryAlive(Boolean engineFactoryAlive) {
        this.engineFactoryAlive = engineFactoryAlive;
    }

    public Integer getEngineFactoryAliveTimes() {
        return engineFactoryAliveTimes;
    }

    public void setEngineFactoryAliveTimes(Integer engineFactoryAliveTimes) {
        this.engineFactoryAliveTimes = engineFactoryAliveTimes;
    }
}