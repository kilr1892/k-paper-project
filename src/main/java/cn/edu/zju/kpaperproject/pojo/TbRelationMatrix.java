package cn.edu.zju.kpaperproject.pojo;

public class TbRelationMatrix {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private String engineFactoryId;

    private String supplierId;

    private Double relationScore;

    private Integer accumulativeTotalScore;

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

    public Double getRelationScore() {
        return relationScore;
    }

    public void setRelationScore(Double relationScore) {
        this.relationScore = relationScore;
    }

    public Integer getAccumulativeTotalScore() {
        return accumulativeTotalScore;
    }

    public void setAccumulativeTotalScore(Integer accumulativeTotalScore) {
        this.accumulativeTotalScore = accumulativeTotalScore;
    }
}