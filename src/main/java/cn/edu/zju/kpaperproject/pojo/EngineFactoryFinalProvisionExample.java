package cn.edu.zju.kpaperproject.pojo;

import java.util.ArrayList;
import java.util.List;

public class EngineFactoryFinalProvisionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EngineFactoryFinalProvisionExample() {
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

        public Criteria andFinalProductNumberIsNull() {
            addCriterion("final_product_number is null");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberIsNotNull() {
            addCriterion("final_product_number is not null");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberEqualTo(Integer value) {
            addCriterion("final_product_number =", value, "finalProductNumber");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberNotEqualTo(Integer value) {
            addCriterion("final_product_number <>", value, "finalProductNumber");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberGreaterThan(Integer value) {
            addCriterion("final_product_number >", value, "finalProductNumber");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("final_product_number >=", value, "finalProductNumber");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberLessThan(Integer value) {
            addCriterion("final_product_number <", value, "finalProductNumber");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberLessThanOrEqualTo(Integer value) {
            addCriterion("final_product_number <=", value, "finalProductNumber");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberIn(List<Integer> values) {
            addCriterion("final_product_number in", values, "finalProductNumber");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberNotIn(List<Integer> values) {
            addCriterion("final_product_number not in", values, "finalProductNumber");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberBetween(Integer value1, Integer value2) {
            addCriterion("final_product_number between", value1, value2, "finalProductNumber");
            return (Criteria) this;
        }

        public Criteria andFinalProductNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("final_product_number not between", value1, value2, "finalProductNumber");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceIsNull() {
            addCriterion("final_market_price is null");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceIsNotNull() {
            addCriterion("final_market_price is not null");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceEqualTo(Integer value) {
            addCriterion("final_market_price =", value, "finalMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceNotEqualTo(Integer value) {
            addCriterion("final_market_price <>", value, "finalMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceGreaterThan(Integer value) {
            addCriterion("final_market_price >", value, "finalMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("final_market_price >=", value, "finalMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceLessThan(Integer value) {
            addCriterion("final_market_price <", value, "finalMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceLessThanOrEqualTo(Integer value) {
            addCriterion("final_market_price <=", value, "finalMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceIn(List<Integer> values) {
            addCriterion("final_market_price in", values, "finalMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceNotIn(List<Integer> values) {
            addCriterion("final_market_price not in", values, "finalMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceBetween(Integer value1, Integer value2) {
            addCriterion("final_market_price between", value1, value2, "finalMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFinalMarketPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("final_market_price not between", value1, value2, "finalMarketPrice");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityIsNull() {
            addCriterion("final_market_quality is null");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityIsNotNull() {
            addCriterion("final_market_quality is not null");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityEqualTo(Integer value) {
            addCriterion("final_market_quality =", value, "finalMarketQuality");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityNotEqualTo(Integer value) {
            addCriterion("final_market_quality <>", value, "finalMarketQuality");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityGreaterThan(Integer value) {
            addCriterion("final_market_quality >", value, "finalMarketQuality");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityGreaterThanOrEqualTo(Integer value) {
            addCriterion("final_market_quality >=", value, "finalMarketQuality");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityLessThan(Integer value) {
            addCriterion("final_market_quality <", value, "finalMarketQuality");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityLessThanOrEqualTo(Integer value) {
            addCriterion("final_market_quality <=", value, "finalMarketQuality");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityIn(List<Integer> values) {
            addCriterion("final_market_quality in", values, "finalMarketQuality");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityNotIn(List<Integer> values) {
            addCriterion("final_market_quality not in", values, "finalMarketQuality");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityBetween(Integer value1, Integer value2) {
            addCriterion("final_market_quality between", value1, value2, "finalMarketQuality");
            return (Criteria) this;
        }

        public Criteria andFinalMarketQualityNotBetween(Integer value1, Integer value2) {
            addCriterion("final_market_quality not between", value1, value2, "finalMarketQuality");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberIsNull() {
            addCriterion("market_need_number is null");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberIsNotNull() {
            addCriterion("market_need_number is not null");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberEqualTo(Integer value) {
            addCriterion("market_need_number =", value, "marketNeedNumber");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberNotEqualTo(Integer value) {
            addCriterion("market_need_number <>", value, "marketNeedNumber");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberGreaterThan(Integer value) {
            addCriterion("market_need_number >", value, "marketNeedNumber");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("market_need_number >=", value, "marketNeedNumber");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberLessThan(Integer value) {
            addCriterion("market_need_number <", value, "marketNeedNumber");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberLessThanOrEqualTo(Integer value) {
            addCriterion("market_need_number <=", value, "marketNeedNumber");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberIn(List<Integer> values) {
            addCriterion("market_need_number in", values, "marketNeedNumber");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberNotIn(List<Integer> values) {
            addCriterion("market_need_number not in", values, "marketNeedNumber");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberBetween(Integer value1, Integer value2) {
            addCriterion("market_need_number between", value1, value2, "marketNeedNumber");
            return (Criteria) this;
        }

        public Criteria andMarketNeedNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("market_need_number not between", value1, value2, "marketNeedNumber");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberIsNull() {
            addCriterion("actual_sale_number is null");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberIsNotNull() {
            addCriterion("actual_sale_number is not null");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberEqualTo(Integer value) {
            addCriterion("actual_sale_number =", value, "actualSaleNumber");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberNotEqualTo(Integer value) {
            addCriterion("actual_sale_number <>", value, "actualSaleNumber");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberGreaterThan(Integer value) {
            addCriterion("actual_sale_number >", value, "actualSaleNumber");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("actual_sale_number >=", value, "actualSaleNumber");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberLessThan(Integer value) {
            addCriterion("actual_sale_number <", value, "actualSaleNumber");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberLessThanOrEqualTo(Integer value) {
            addCriterion("actual_sale_number <=", value, "actualSaleNumber");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberIn(List<Integer> values) {
            addCriterion("actual_sale_number in", values, "actualSaleNumber");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberNotIn(List<Integer> values) {
            addCriterion("actual_sale_number not in", values, "actualSaleNumber");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberBetween(Integer value1, Integer value2) {
            addCriterion("actual_sale_number between", value1, value2, "actualSaleNumber");
            return (Criteria) this;
        }

        public Criteria andActualSaleNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("actual_sale_number not between", value1, value2, "actualSaleNumber");
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