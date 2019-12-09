package cn.edu.zju.kpaperproject.pojo;

public class TbRelationMatrix {
    private Integer id;

    private Integer experimentsNumber;

    private Integer cycleTimes;

    private String engineFactoryId;

    private String supplierId;

    private String mapKey;

    private Double relationScore;

    private Integer accumulativeTotalScore;

    private Boolean relationMatrixAlive;

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

    public String getMapKey() {
        return mapKey;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey == null ? null : mapKey.trim();
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

    public Boolean getRelationMatrixAlive() {
        return relationMatrixAlive;
    }

    public void setRelationMatrixAlive(Boolean relationMatrixAlive) {
        this.relationMatrixAlive = relationMatrixAlive;
    }
}