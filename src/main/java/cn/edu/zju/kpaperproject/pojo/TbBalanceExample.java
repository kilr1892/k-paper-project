package cn.edu.zju.kpaperproject.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbBalanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbBalanceExample() {
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

        public Criteria andEngineFactoryBalanceIsNull() {
            addCriterion("engine_factory_balance is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceIsNotNull() {
            addCriterion("engine_factory_balance is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceEqualTo(Double value) {
            addCriterion("engine_factory_balance =", value, "engineFactoryBalance");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceNotEqualTo(Double value) {
            addCriterion("engine_factory_balance <>", value, "engineFactoryBalance");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceGreaterThan(Double value) {
            addCriterion("engine_factory_balance >", value, "engineFactoryBalance");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceGreaterThanOrEqualTo(Double value) {
            addCriterion("engine_factory_balance >=", value, "engineFactoryBalance");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceLessThan(Double value) {
            addCriterion("engine_factory_balance <", value, "engineFactoryBalance");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceLessThanOrEqualTo(Double value) {
            addCriterion("engine_factory_balance <=", value, "engineFactoryBalance");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceIn(List<Double> values) {
            addCriterion("engine_factory_balance in", values, "engineFactoryBalance");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceNotIn(List<Double> values) {
            addCriterion("engine_factory_balance not in", values, "engineFactoryBalance");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceBetween(Double value1, Double value2) {
            addCriterion("engine_factory_balance between", value1, value2, "engineFactoryBalance");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryBalanceNotBetween(Double value1, Double value2) {
            addCriterion("engine_factory_balance not between", value1, value2, "engineFactoryBalance");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceIsNull() {
            addCriterion("supplier_balance is null");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceIsNotNull() {
            addCriterion("supplier_balance is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceEqualTo(Double value) {
            addCriterion("supplier_balance =", value, "supplierBalance");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceNotEqualTo(Double value) {
            addCriterion("supplier_balance <>", value, "supplierBalance");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceGreaterThan(Double value) {
            addCriterion("supplier_balance >", value, "supplierBalance");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceGreaterThanOrEqualTo(Double value) {
            addCriterion("supplier_balance >=", value, "supplierBalance");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceLessThan(Double value) {
            addCriterion("supplier_balance <", value, "supplierBalance");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceLessThanOrEqualTo(Double value) {
            addCriterion("supplier_balance <=", value, "supplierBalance");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceIn(List<Double> values) {
            addCriterion("supplier_balance in", values, "supplierBalance");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceNotIn(List<Double> values) {
            addCriterion("supplier_balance not in", values, "supplierBalance");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceBetween(Double value1, Double value2) {
            addCriterion("supplier_balance between", value1, value2, "supplierBalance");
            return (Criteria) this;
        }

        public Criteria andSupplierBalanceNotBetween(Double value1, Double value2) {
            addCriterion("supplier_balance not between", value1, value2, "supplierBalance");
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