package cn.edu.zju.kpaperproject.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbEngineFactoryDynamicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbEngineFactoryDynamicExample() {
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

        public Criteria andEngineFactoryTotalAssetsPIsNull() {
            addCriterion("engine_factory_total_assets_p is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPIsNotNull() {
            addCriterion("engine_factory_total_assets_p is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPEqualTo(Integer value) {
            addCriterion("engine_factory_total_assets_p =", value, "engineFactoryTotalAssetsP");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPNotEqualTo(Integer value) {
            addCriterion("engine_factory_total_assets_p <>", value, "engineFactoryTotalAssetsP");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPGreaterThan(Integer value) {
            addCriterion("engine_factory_total_assets_p >", value, "engineFactoryTotalAssetsP");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_total_assets_p >=", value, "engineFactoryTotalAssetsP");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPLessThan(Integer value) {
            addCriterion("engine_factory_total_assets_p <", value, "engineFactoryTotalAssetsP");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_total_assets_p <=", value, "engineFactoryTotalAssetsP");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPIn(List<Integer> values) {
            addCriterion("engine_factory_total_assets_p in", values, "engineFactoryTotalAssetsP");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPNotIn(List<Integer> values) {
            addCriterion("engine_factory_total_assets_p not in", values, "engineFactoryTotalAssetsP");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_total_assets_p between", value1, value2, "engineFactoryTotalAssetsP");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryTotalAssetsPNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_total_assets_p not between", value1, value2, "engineFactoryTotalAssetsP");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHIsNull() {
            addCriterion("engine_factory_credit_h is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHIsNotNull() {
            addCriterion("engine_factory_credit_h is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHEqualTo(Double value) {
            addCriterion("engine_factory_credit_h =", value, "engineFactoryCreditH");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHNotEqualTo(Double value) {
            addCriterion("engine_factory_credit_h <>", value, "engineFactoryCreditH");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHGreaterThan(Double value) {
            addCriterion("engine_factory_credit_h >", value, "engineFactoryCreditH");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHGreaterThanOrEqualTo(Double value) {
            addCriterion("engine_factory_credit_h >=", value, "engineFactoryCreditH");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHLessThan(Double value) {
            addCriterion("engine_factory_credit_h <", value, "engineFactoryCreditH");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHLessThanOrEqualTo(Double value) {
            addCriterion("engine_factory_credit_h <=", value, "engineFactoryCreditH");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHIn(List<Double> values) {
            addCriterion("engine_factory_credit_h in", values, "engineFactoryCreditH");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHNotIn(List<Double> values) {
            addCriterion("engine_factory_credit_h not in", values, "engineFactoryCreditH");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHBetween(Double value1, Double value2) {
            addCriterion("engine_factory_credit_h between", value1, value2, "engineFactoryCreditH");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCreditHNotBetween(Double value1, Double value2) {
            addCriterion("engine_factory_credit_h not between", value1, value2, "engineFactoryCreditH");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMIsNull() {
            addCriterion("engine_factory_capacity_m is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMIsNotNull() {
            addCriterion("engine_factory_capacity_m is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMEqualTo(Integer value) {
            addCriterion("engine_factory_capacity_m =", value, "engineFactoryCapacityM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMNotEqualTo(Integer value) {
            addCriterion("engine_factory_capacity_m <>", value, "engineFactoryCapacityM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMGreaterThan(Integer value) {
            addCriterion("engine_factory_capacity_m >", value, "engineFactoryCapacityM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_capacity_m >=", value, "engineFactoryCapacityM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMLessThan(Integer value) {
            addCriterion("engine_factory_capacity_m <", value, "engineFactoryCapacityM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_capacity_m <=", value, "engineFactoryCapacityM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMIn(List<Integer> values) {
            addCriterion("engine_factory_capacity_m in", values, "engineFactoryCapacityM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMNotIn(List<Integer> values) {
            addCriterion("engine_factory_capacity_m not in", values, "engineFactoryCapacityM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_capacity_m between", value1, value2, "engineFactoryCapacityM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityMNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_capacity_m not between", value1, value2, "engineFactoryCapacityM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLIsNull() {
            addCriterion("engine_factory_price_p_l is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLIsNotNull() {
            addCriterion("engine_factory_price_p_l is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLEqualTo(Integer value) {
            addCriterion("engine_factory_price_p_l =", value, "engineFactoryPricePL");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLNotEqualTo(Integer value) {
            addCriterion("engine_factory_price_p_l <>", value, "engineFactoryPricePL");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLGreaterThan(Integer value) {
            addCriterion("engine_factory_price_p_l >", value, "engineFactoryPricePL");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_price_p_l >=", value, "engineFactoryPricePL");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLLessThan(Integer value) {
            addCriterion("engine_factory_price_p_l <", value, "engineFactoryPricePL");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_price_p_l <=", value, "engineFactoryPricePL");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLIn(List<Integer> values) {
            addCriterion("engine_factory_price_p_l in", values, "engineFactoryPricePL");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLNotIn(List<Integer> values) {
            addCriterion("engine_factory_price_p_l not in", values, "engineFactoryPricePL");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_price_p_l between", value1, value2, "engineFactoryPricePL");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePLNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_price_p_l not between", value1, value2, "engineFactoryPricePL");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePUIsNull() {
            addCriterion("engine_factory_price_p_u is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePUIsNotNull() {
            addCriterion("engine_factory_price_p_u is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePUEqualTo(Integer value) {
            addCriterion("engine_factory_price_p_u =", value, "engineFactoryPricePU");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePUNotEqualTo(Integer value) {
            addCriterion("engine_factory_price_p_u <>", value, "engineFactoryPricePU");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePUGreaterThan(Integer value) {
            addCriterion("engine_factory_price_p_u >", value, "engineFactoryPricePU");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePUGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_price_p_u >=", value, "engineFactoryPricePU");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePULessThan(Integer value) {
            addCriterion("engine_factory_price_p_u <", value, "engineFactoryPricePU");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePULessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_price_p_u <=", value, "engineFactoryPricePU");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePUIn(List<Integer> values) {
            addCriterion("engine_factory_price_p_u in", values, "engineFactoryPricePU");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePUNotIn(List<Integer> values) {
            addCriterion("engine_factory_price_p_u not in", values, "engineFactoryPricePU");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePUBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_price_p_u between", value1, value2, "engineFactoryPricePU");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryPricePUNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_price_p_u not between", value1, value2, "engineFactoryPricePU");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQIsNull() {
            addCriterion("engine_factory_quality_q is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQIsNotNull() {
            addCriterion("engine_factory_quality_q is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQEqualTo(Integer value) {
            addCriterion("engine_factory_quality_q =", value, "engineFactoryQualityQ");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQNotEqualTo(Integer value) {
            addCriterion("engine_factory_quality_q <>", value, "engineFactoryQualityQ");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQGreaterThan(Integer value) {
            addCriterion("engine_factory_quality_q >", value, "engineFactoryQualityQ");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_quality_q >=", value, "engineFactoryQualityQ");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQLessThan(Integer value) {
            addCriterion("engine_factory_quality_q <", value, "engineFactoryQualityQ");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_quality_q <=", value, "engineFactoryQualityQ");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQIn(List<Integer> values) {
            addCriterion("engine_factory_quality_q in", values, "engineFactoryQualityQ");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQNotIn(List<Integer> values) {
            addCriterion("engine_factory_quality_q not in", values, "engineFactoryQualityQ");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_quality_q between", value1, value2, "engineFactoryQualityQ");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryQualityQNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_quality_q not between", value1, value2, "engineFactoryQualityQ");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDIsNull() {
            addCriterion("engine_factory_demand_forecast_d is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDIsNotNull() {
            addCriterion("engine_factory_demand_forecast_d is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDEqualTo(Integer value) {
            addCriterion("engine_factory_demand_forecast_d =", value, "engineFactoryDemandForecastD");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDNotEqualTo(Integer value) {
            addCriterion("engine_factory_demand_forecast_d <>", value, "engineFactoryDemandForecastD");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDGreaterThan(Integer value) {
            addCriterion("engine_factory_demand_forecast_d >", value, "engineFactoryDemandForecastD");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_demand_forecast_d >=", value, "engineFactoryDemandForecastD");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDLessThan(Integer value) {
            addCriterion("engine_factory_demand_forecast_d <", value, "engineFactoryDemandForecastD");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_demand_forecast_d <=", value, "engineFactoryDemandForecastD");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDIn(List<Integer> values) {
            addCriterion("engine_factory_demand_forecast_d in", values, "engineFactoryDemandForecastD");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDNotIn(List<Integer> values) {
            addCriterion("engine_factory_demand_forecast_d not in", values, "engineFactoryDemandForecastD");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_demand_forecast_d between", value1, value2, "engineFactoryDemandForecastD");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryDemandForecastDNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_demand_forecast_d not between", value1, value2, "engineFactoryDemandForecastD");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationIsNull() {
            addCriterion("engine_factory_capacity_utilization is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationIsNotNull() {
            addCriterion("engine_factory_capacity_utilization is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationEqualTo(Double value) {
            addCriterion("engine_factory_capacity_utilization =", value, "engineFactoryCapacityUtilization");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationNotEqualTo(Double value) {
            addCriterion("engine_factory_capacity_utilization <>", value, "engineFactoryCapacityUtilization");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationGreaterThan(Double value) {
            addCriterion("engine_factory_capacity_utilization >", value, "engineFactoryCapacityUtilization");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationGreaterThanOrEqualTo(Double value) {
            addCriterion("engine_factory_capacity_utilization >=", value, "engineFactoryCapacityUtilization");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationLessThan(Double value) {
            addCriterion("engine_factory_capacity_utilization <", value, "engineFactoryCapacityUtilization");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationLessThanOrEqualTo(Double value) {
            addCriterion("engine_factory_capacity_utilization <=", value, "engineFactoryCapacityUtilization");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationIn(List<Double> values) {
            addCriterion("engine_factory_capacity_utilization in", values, "engineFactoryCapacityUtilization");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationNotIn(List<Double> values) {
            addCriterion("engine_factory_capacity_utilization not in", values, "engineFactoryCapacityUtilization");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationBetween(Double value1, Double value2) {
            addCriterion("engine_factory_capacity_utilization between", value1, value2, "engineFactoryCapacityUtilization");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryCapacityUtilizationNotBetween(Double value1, Double value2) {
            addCriterion("engine_factory_capacity_utilization not between", value1, value2, "engineFactoryCapacityUtilization");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityIsNull() {
            addCriterion("engine_factory_innovation_probability is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityIsNotNull() {
            addCriterion("engine_factory_innovation_probability is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityEqualTo(Double value) {
            addCriterion("engine_factory_innovation_probability =", value, "engineFactoryInnovationProbability");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityNotEqualTo(Double value) {
            addCriterion("engine_factory_innovation_probability <>", value, "engineFactoryInnovationProbability");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityGreaterThan(Double value) {
            addCriterion("engine_factory_innovation_probability >", value, "engineFactoryInnovationProbability");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityGreaterThanOrEqualTo(Double value) {
            addCriterion("engine_factory_innovation_probability >=", value, "engineFactoryInnovationProbability");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityLessThan(Double value) {
            addCriterion("engine_factory_innovation_probability <", value, "engineFactoryInnovationProbability");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityLessThanOrEqualTo(Double value) {
            addCriterion("engine_factory_innovation_probability <=", value, "engineFactoryInnovationProbability");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityIn(List<Double> values) {
            addCriterion("engine_factory_innovation_probability in", values, "engineFactoryInnovationProbability");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityNotIn(List<Double> values) {
            addCriterion("engine_factory_innovation_probability not in", values, "engineFactoryInnovationProbability");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityBetween(Double value1, Double value2) {
            addCriterion("engine_factory_innovation_probability between", value1, value2, "engineFactoryInnovationProbability");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationProbabilityNotBetween(Double value1, Double value2) {
            addCriterion("engine_factory_innovation_probability not between", value1, value2, "engineFactoryInnovationProbability");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationIsNull() {
            addCriterion("engine_factory_whether_innovation is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationIsNotNull() {
            addCriterion("engine_factory_whether_innovation is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationEqualTo(Boolean value) {
            addCriterion("engine_factory_whether_innovation =", value, "engineFactoryWhetherInnovation");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationNotEqualTo(Boolean value) {
            addCriterion("engine_factory_whether_innovation <>", value, "engineFactoryWhetherInnovation");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationGreaterThan(Boolean value) {
            addCriterion("engine_factory_whether_innovation >", value, "engineFactoryWhetherInnovation");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationGreaterThanOrEqualTo(Boolean value) {
            addCriterion("engine_factory_whether_innovation >=", value, "engineFactoryWhetherInnovation");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationLessThan(Boolean value) {
            addCriterion("engine_factory_whether_innovation <", value, "engineFactoryWhetherInnovation");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationLessThanOrEqualTo(Boolean value) {
            addCriterion("engine_factory_whether_innovation <=", value, "engineFactoryWhetherInnovation");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationIn(List<Boolean> values) {
            addCriterion("engine_factory_whether_innovation in", values, "engineFactoryWhetherInnovation");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationNotIn(List<Boolean> values) {
            addCriterion("engine_factory_whether_innovation not in", values, "engineFactoryWhetherInnovation");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationBetween(Boolean value1, Boolean value2) {
            addCriterion("engine_factory_whether_innovation between", value1, value2, "engineFactoryWhetherInnovation");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryWhetherInnovationNotBetween(Boolean value1, Boolean value2) {
            addCriterion("engine_factory_whether_innovation not between", value1, value2, "engineFactoryWhetherInnovation");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesIsNull() {
            addCriterion("engine_factory_innovation_times is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesIsNotNull() {
            addCriterion("engine_factory_innovation_times is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesEqualTo(Integer value) {
            addCriterion("engine_factory_innovation_times =", value, "engineFactoryInnovationTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesNotEqualTo(Integer value) {
            addCriterion("engine_factory_innovation_times <>", value, "engineFactoryInnovationTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesGreaterThan(Integer value) {
            addCriterion("engine_factory_innovation_times >", value, "engineFactoryInnovationTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_innovation_times >=", value, "engineFactoryInnovationTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesLessThan(Integer value) {
            addCriterion("engine_factory_innovation_times <", value, "engineFactoryInnovationTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_innovation_times <=", value, "engineFactoryInnovationTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesIn(List<Integer> values) {
            addCriterion("engine_factory_innovation_times in", values, "engineFactoryInnovationTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesNotIn(List<Integer> values) {
            addCriterion("engine_factory_innovation_times not in", values, "engineFactoryInnovationTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_innovation_times between", value1, value2, "engineFactoryInnovationTimes");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInnovationTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_innovation_times not between", value1, value2, "engineFactoryInnovationTimes");
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