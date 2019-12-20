package cn.edu.zju.kpaperproject.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbEngineFactoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbEngineFactoryExample() {
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

        public Criteria andEngineFactoryLocationGXIsNull() {
            addCriterion("engine_factory_location_g_x is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXIsNotNull() {
            addCriterion("engine_factory_location_g_x is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXEqualTo(Double value) {
            addCriterion("engine_factory_location_g_x =", value, "engineFactoryLocationGX");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXNotEqualTo(Double value) {
            addCriterion("engine_factory_location_g_x <>", value, "engineFactoryLocationGX");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXGreaterThan(Double value) {
            addCriterion("engine_factory_location_g_x >", value, "engineFactoryLocationGX");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXGreaterThanOrEqualTo(Double value) {
            addCriterion("engine_factory_location_g_x >=", value, "engineFactoryLocationGX");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXLessThan(Double value) {
            addCriterion("engine_factory_location_g_x <", value, "engineFactoryLocationGX");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXLessThanOrEqualTo(Double value) {
            addCriterion("engine_factory_location_g_x <=", value, "engineFactoryLocationGX");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXIn(List<Double> values) {
            addCriterion("engine_factory_location_g_x in", values, "engineFactoryLocationGX");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXNotIn(List<Double> values) {
            addCriterion("engine_factory_location_g_x not in", values, "engineFactoryLocationGX");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXBetween(Double value1, Double value2) {
            addCriterion("engine_factory_location_g_x between", value1, value2, "engineFactoryLocationGX");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGXNotBetween(Double value1, Double value2) {
            addCriterion("engine_factory_location_g_x not between", value1, value2, "engineFactoryLocationGX");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYIsNull() {
            addCriterion("engine_factory_location_g_y is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYIsNotNull() {
            addCriterion("engine_factory_location_g_y is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYEqualTo(Double value) {
            addCriterion("engine_factory_location_g_y =", value, "engineFactoryLocationGY");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYNotEqualTo(Double value) {
            addCriterion("engine_factory_location_g_y <>", value, "engineFactoryLocationGY");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYGreaterThan(Double value) {
            addCriterion("engine_factory_location_g_y >", value, "engineFactoryLocationGY");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYGreaterThanOrEqualTo(Double value) {
            addCriterion("engine_factory_location_g_y >=", value, "engineFactoryLocationGY");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYLessThan(Double value) {
            addCriterion("engine_factory_location_g_y <", value, "engineFactoryLocationGY");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYLessThanOrEqualTo(Double value) {
            addCriterion("engine_factory_location_g_y <=", value, "engineFactoryLocationGY");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYIn(List<Double> values) {
            addCriterion("engine_factory_location_g_y in", values, "engineFactoryLocationGY");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYNotIn(List<Double> values) {
            addCriterion("engine_factory_location_g_y not in", values, "engineFactoryLocationGY");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYBetween(Double value1, Double value2) {
            addCriterion("engine_factory_location_g_y between", value1, value2, "engineFactoryLocationGY");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryLocationGYNotBetween(Double value1, Double value2) {
            addCriterion("engine_factory_location_g_y not between", value1, value2, "engineFactoryLocationGY");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveIsNull() {
            addCriterion("engine_factory_alive is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveIsNotNull() {
            addCriterion("engine_factory_alive is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveEqualTo(Boolean value) {
            addCriterion("engine_factory_alive =", value, "engineFactoryAlive");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveNotEqualTo(Boolean value) {
            addCriterion("engine_factory_alive <>", value, "engineFactoryAlive");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveGreaterThan(Boolean value) {
            addCriterion("engine_factory_alive >", value, "engineFactoryAlive");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("engine_factory_alive >=", value, "engineFactoryAlive");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveLessThan(Boolean value) {
            addCriterion("engine_factory_alive <", value, "engineFactoryAlive");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveLessThanOrEqualTo(Boolean value) {
            addCriterion("engine_factory_alive <=", value, "engineFactoryAlive");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveIn(List<Boolean> values) {
            addCriterion("engine_factory_alive in", values, "engineFactoryAlive");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveNotIn(List<Boolean> values) {
            addCriterion("engine_factory_alive not in", values, "engineFactoryAlive");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveBetween(Boolean value1, Boolean value2) {
            addCriterion("engine_factory_alive between", value1, value2, "engineFactoryAlive");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("engine_factory_alive not between", value1, value2, "engineFactoryAlive");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesIsNull() {
            addCriterion("engine_factory_alive_times is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesIsNotNull() {
            addCriterion("engine_factory_alive_times is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesEqualTo(Integer value) {
            addCriterion("engine_factory_alive_times =", value, "engineFactoryAliveTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesNotEqualTo(Integer value) {
            addCriterion("engine_factory_alive_times <>", value, "engineFactoryAliveTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesGreaterThan(Integer value) {
            addCriterion("engine_factory_alive_times >", value, "engineFactoryAliveTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_alive_times >=", value, "engineFactoryAliveTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesLessThan(Integer value) {
            addCriterion("engine_factory_alive_times <", value, "engineFactoryAliveTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_alive_times <=", value, "engineFactoryAliveTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesIn(List<Integer> values) {
            addCriterion("engine_factory_alive_times in", values, "engineFactoryAliveTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesNotIn(List<Integer> values) {
            addCriterion("engine_factory_alive_times not in", values, "engineFactoryAliveTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_alive_times between", value1, value2, "engineFactoryAliveTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryAliveTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_alive_times not between", value1, value2, "engineFactoryAliveTimes");
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