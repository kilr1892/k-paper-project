package cn.edu.zju.kpaperproject.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbRelationMatrixExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbRelationMatrixExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberIsNull() {
            addCriterion("experiments_number is null");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberIsNotNull() {
            addCriterion("experiments_number is not null");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberEqualTo(Integer value) {
            addCriterion("experiments_number =", value, "experimentsNumber");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberNotEqualTo(Integer value) {
            addCriterion("experiments_number <>", value, "experimentsNumber");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberGreaterThan(Integer value) {
            addCriterion("experiments_number >", value, "experimentsNumber");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("experiments_number >=", value, "experimentsNumber");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberLessThan(Integer value) {
            addCriterion("experiments_number <", value, "experimentsNumber");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberLessThanOrEqualTo(Integer value) {
            addCriterion("experiments_number <=", value, "experimentsNumber");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberIn(List<Integer> values) {
            addCriterion("experiments_number in", values, "experimentsNumber");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberNotIn(List<Integer> values) {
            addCriterion("experiments_number not in", values, "experimentsNumber");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberBetween(Integer value1, Integer value2) {
            addCriterion("experiments_number between", value1, value2, "experimentsNumber");
            return (Criteria) this;
        }

        public Criteria andExperimentsNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("experiments_number not between", value1, value2, "experimentsNumber");
            return (Criteria) this;
        }

        public Criteria andCycleTimesIsNull() {
            addCriterion("cycle_times is null");
            return (Criteria) this;
        }

        public Criteria andCycleTimesIsNotNull() {
            addCriterion("cycle_times is not null");
            return (Criteria) this;
        }

        public Criteria andCycleTimesEqualTo(Integer value) {
            addCriterion("cycle_times =", value, "cycleTimes");
            return (Criteria) this;
        }

        public Criteria andCycleTimesNotEqualTo(Integer value) {
            addCriterion("cycle_times <>", value, "cycleTimes");
            return (Criteria) this;
        }

        public Criteria andCycleTimesGreaterThan(Integer value) {
            addCriterion("cycle_times >", value, "cycleTimes");
            return (Criteria) this;
        }

        public Criteria andCycleTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("cycle_times >=", value, "cycleTimes");
            return (Criteria) this;
        }

        public Criteria andCycleTimesLessThan(Integer value) {
            addCriterion("cycle_times <", value, "cycleTimes");
            return (Criteria) this;
        }

        public Criteria andCycleTimesLessThanOrEqualTo(Integer value) {
            addCriterion("cycle_times <=", value, "cycleTimes");
            return (Criteria) this;
        }

        public Criteria andCycleTimesIn(List<Integer> values) {
            addCriterion("cycle_times in", values, "cycleTimes");
            return (Criteria) this;
        }

        public Criteria andCycleTimesNotIn(List<Integer> values) {
            addCriterion("cycle_times not in", values, "cycleTimes");
            return (Criteria) this;
        }

        public Criteria andCycleTimesBetween(Integer value1, Integer value2) {
            addCriterion("cycle_times between", value1, value2, "cycleTimes");
            return (Criteria) this;
        }

        public Criteria andCycleTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("cycle_times not between", value1, value2, "cycleTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdIsNull() {
            addCriterion("engine_factory_id is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdIsNotNull() {
            addCriterion("engine_factory_id is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdEqualTo(String value) {
            addCriterion("engine_factory_id =", value, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdNotEqualTo(String value) {
            addCriterion("engine_factory_id <>", value, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdGreaterThan(String value) {
            addCriterion("engine_factory_id >", value, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("engine_factory_id >=", value, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdLessThan(String value) {
            addCriterion("engine_factory_id <", value, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdLessThanOrEqualTo(String value) {
            addCriterion("engine_factory_id <=", value, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdLike(String value) {
            addCriterion("engine_factory_id like", value, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdNotLike(String value) {
            addCriterion("engine_factory_id not like", value, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdIn(List<String> values) {
            addCriterion("engine_factory_id in", values, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdNotIn(List<String> values) {
            addCriterion("engine_factory_id not in", values, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdBetween(String value1, String value2) {
            addCriterion("engine_factory_id between", value1, value2, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryIdNotBetween(String value1, String value2) {
            addCriterion("engine_factory_id not between", value1, value2, "engineFactoryId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(String value) {
            addCriterion("supplier_id =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(String value) {
            addCriterion("supplier_id <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(String value) {
            addCriterion("supplier_id >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_id >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(String value) {
            addCriterion("supplier_id <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(String value) {
            addCriterion("supplier_id <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLike(String value) {
            addCriterion("supplier_id like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotLike(String value) {
            addCriterion("supplier_id not like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<String> values) {
            addCriterion("supplier_id in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<String> values) {
            addCriterion("supplier_id not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(String value1, String value2) {
            addCriterion("supplier_id between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(String value1, String value2) {
            addCriterion("supplier_id not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andMapKeyIsNull() {
            addCriterion("map_key is null");
            return (Criteria) this;
        }

        public Criteria andMapKeyIsNotNull() {
            addCriterion("map_key is not null");
            return (Criteria) this;
        }

        public Criteria andMapKeyEqualTo(String value) {
            addCriterion("map_key =", value, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyNotEqualTo(String value) {
            addCriterion("map_key <>", value, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyGreaterThan(String value) {
            addCriterion("map_key >", value, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyGreaterThanOrEqualTo(String value) {
            addCriterion("map_key >=", value, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyLessThan(String value) {
            addCriterion("map_key <", value, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyLessThanOrEqualTo(String value) {
            addCriterion("map_key <=", value, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyLike(String value) {
            addCriterion("map_key like", value, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyNotLike(String value) {
            addCriterion("map_key not like", value, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyIn(List<String> values) {
            addCriterion("map_key in", values, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyNotIn(List<String> values) {
            addCriterion("map_key not in", values, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyBetween(String value1, String value2) {
            addCriterion("map_key between", value1, value2, "mapKey");
            return (Criteria) this;
        }

        public Criteria andMapKeyNotBetween(String value1, String value2) {
            addCriterion("map_key not between", value1, value2, "mapKey");
            return (Criteria) this;
        }

        public Criteria andRelationScoreIsNull() {
            addCriterion("relation_score is null");
            return (Criteria) this;
        }

        public Criteria andRelationScoreIsNotNull() {
            addCriterion("relation_score is not null");
            return (Criteria) this;
        }

        public Criteria andRelationScoreEqualTo(Double value) {
            addCriterion("relation_score =", value, "relationScore");
            return (Criteria) this;
        }

        public Criteria andRelationScoreNotEqualTo(Double value) {
            addCriterion("relation_score <>", value, "relationScore");
            return (Criteria) this;
        }

        public Criteria andRelationScoreGreaterThan(Double value) {
            addCriterion("relation_score >", value, "relationScore");
            return (Criteria) this;
        }

        public Criteria andRelationScoreGreaterThanOrEqualTo(Double value) {
            addCriterion("relation_score >=", value, "relationScore");
            return (Criteria) this;
        }

        public Criteria andRelationScoreLessThan(Double value) {
            addCriterion("relation_score <", value, "relationScore");
            return (Criteria) this;
        }

        public Criteria andRelationScoreLessThanOrEqualTo(Double value) {
            addCriterion("relation_score <=", value, "relationScore");
            return (Criteria) this;
        }

        public Criteria andRelationScoreIn(List<Double> values) {
            addCriterion("relation_score in", values, "relationScore");
            return (Criteria) this;
        }

        public Criteria andRelationScoreNotIn(List<Double> values) {
            addCriterion("relation_score not in", values, "relationScore");
            return (Criteria) this;
        }

        public Criteria andRelationScoreBetween(Double value1, Double value2) {
            addCriterion("relation_score between", value1, value2, "relationScore");
            return (Criteria) this;
        }

        public Criteria andRelationScoreNotBetween(Double value1, Double value2) {
            addCriterion("relation_score not between", value1, value2, "relationScore");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreIsNull() {
            addCriterion("accumulative_total_score is null");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreIsNotNull() {
            addCriterion("accumulative_total_score is not null");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreEqualTo(Integer value) {
            addCriterion("accumulative_total_score =", value, "accumulativeTotalScore");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreNotEqualTo(Integer value) {
            addCriterion("accumulative_total_score <>", value, "accumulativeTotalScore");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreGreaterThan(Integer value) {
            addCriterion("accumulative_total_score >", value, "accumulativeTotalScore");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("accumulative_total_score >=", value, "accumulativeTotalScore");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreLessThan(Integer value) {
            addCriterion("accumulative_total_score <", value, "accumulativeTotalScore");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreLessThanOrEqualTo(Integer value) {
            addCriterion("accumulative_total_score <=", value, "accumulativeTotalScore");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreIn(List<Integer> values) {
            addCriterion("accumulative_total_score in", values, "accumulativeTotalScore");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreNotIn(List<Integer> values) {
            addCriterion("accumulative_total_score not in", values, "accumulativeTotalScore");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreBetween(Integer value1, Integer value2) {
            addCriterion("accumulative_total_score between", value1, value2, "accumulativeTotalScore");
            return (Criteria) this;
        }

        public Criteria andAccumulativeTotalScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("accumulative_total_score not between", value1, value2, "accumulativeTotalScore");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveIsNull() {
            addCriterion("relation_matrix_alive is null");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveIsNotNull() {
            addCriterion("relation_matrix_alive is not null");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveEqualTo(Boolean value) {
            addCriterion("relation_matrix_alive =", value, "relationMatrixAlive");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveNotEqualTo(Boolean value) {
            addCriterion("relation_matrix_alive <>", value, "relationMatrixAlive");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveGreaterThan(Boolean value) {
            addCriterion("relation_matrix_alive >", value, "relationMatrixAlive");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("relation_matrix_alive >=", value, "relationMatrixAlive");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveLessThan(Boolean value) {
            addCriterion("relation_matrix_alive <", value, "relationMatrixAlive");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveLessThanOrEqualTo(Boolean value) {
            addCriterion("relation_matrix_alive <=", value, "relationMatrixAlive");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveIn(List<Boolean> values) {
            addCriterion("relation_matrix_alive in", values, "relationMatrixAlive");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveNotIn(List<Boolean> values) {
            addCriterion("relation_matrix_alive not in", values, "relationMatrixAlive");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveBetween(Boolean value1, Boolean value2) {
            addCriterion("relation_matrix_alive between", value1, value2, "relationMatrixAlive");
            return (Criteria) this;
        }

        public Criteria andRelationMatrixAliveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("relation_matrix_alive not between", value1, value2, "relationMatrixAlive");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}