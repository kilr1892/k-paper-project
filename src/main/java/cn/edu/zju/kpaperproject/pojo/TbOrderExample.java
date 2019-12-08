package cn.edu.zju.kpaperproject.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbOrderExample() {
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

        public Criteria andEngineToSupplierApIsNull() {
            addCriterion("engine_to_supplier_ap is null");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApIsNotNull() {
            addCriterion("engine_to_supplier_ap is not null");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApEqualTo(String value) {
            addCriterion("engine_to_supplier_ap =", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApNotEqualTo(String value) {
            addCriterion("engine_to_supplier_ap <>", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApGreaterThan(String value) {
            addCriterion("engine_to_supplier_ap >", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApGreaterThanOrEqualTo(String value) {
            addCriterion("engine_to_supplier_ap >=", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApLessThan(String value) {
            addCriterion("engine_to_supplier_ap <", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApLessThanOrEqualTo(String value) {
            addCriterion("engine_to_supplier_ap <=", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApLike(String value) {
            addCriterion("engine_to_supplier_ap like", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApNotLike(String value) {
            addCriterion("engine_to_supplier_ap not like", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApIn(List<String> values) {
            addCriterion("engine_to_supplier_ap in", values, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApNotIn(List<String> values) {
            addCriterion("engine_to_supplier_ap not in", values, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApBetween(String value1, String value2) {
            addCriterion("engine_to_supplier_ap between", value1, value2, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApNotBetween(String value1, String value2) {
            addCriterion("engine_to_supplier_ap not between", value1, value2, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApIsNull() {
            addCriterion("supplier_engine_to_ap is null");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApIsNotNull() {
            addCriterion("supplier_engine_to_ap is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApEqualTo(String value) {
            addCriterion("supplier_engine_to_ap =", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApNotEqualTo(String value) {
            addCriterion("supplier_engine_to_ap <>", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApGreaterThan(String value) {
            addCriterion("supplier_engine_to_ap >", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_engine_to_ap >=", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApLessThan(String value) {
            addCriterion("supplier_engine_to_ap <", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApLessThanOrEqualTo(String value) {
            addCriterion("supplier_engine_to_ap <=", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApLike(String value) {
            addCriterion("supplier_engine_to_ap like", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApNotLike(String value) {
            addCriterion("supplier_engine_to_ap not like", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApIn(List<String> values) {
            addCriterion("supplier_engine_to_ap in", values, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApNotIn(List<String> values) {
            addCriterion("supplier_engine_to_ap not in", values, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApBetween(String value1, String value2) {
            addCriterion("supplier_engine_to_ap between", value1, value2, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApNotBetween(String value1, String value2) {
            addCriterion("supplier_engine_to_ap not between", value1, value2, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePIsNull() {
            addCriterion("supplier_actual_price_p is null");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePIsNotNull() {
            addCriterion("supplier_actual_price_p is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePEqualTo(String value) {
            addCriterion("supplier_actual_price_p =", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePNotEqualTo(String value) {
            addCriterion("supplier_actual_price_p <>", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePGreaterThan(String value) {
            addCriterion("supplier_actual_price_p >", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_actual_price_p >=", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePLessThan(String value) {
            addCriterion("supplier_actual_price_p <", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePLessThanOrEqualTo(String value) {
            addCriterion("supplier_actual_price_p <=", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePLike(String value) {
            addCriterion("supplier_actual_price_p like", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePNotLike(String value) {
            addCriterion("supplier_actual_price_p not like", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePIn(List<String> values) {
            addCriterion("supplier_actual_price_p in", values, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePNotIn(List<String> values) {
            addCriterion("supplier_actual_price_p not in", values, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePBetween(String value1, String value2) {
            addCriterion("supplier_actual_price_p between", value1, value2, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePNotBetween(String value1, String value2) {
            addCriterion("supplier_actual_price_p not between", value1, value2, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsIsNull() {
            addCriterion("supplier_actual_quality_qs is null");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsIsNotNull() {
            addCriterion("supplier_actual_quality_qs is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsEqualTo(String value) {
            addCriterion("supplier_actual_quality_qs =", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsNotEqualTo(String value) {
            addCriterion("supplier_actual_quality_qs <>", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsGreaterThan(String value) {
            addCriterion("supplier_actual_quality_qs >", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_actual_quality_qs >=", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsLessThan(String value) {
            addCriterion("supplier_actual_quality_qs <", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsLessThanOrEqualTo(String value) {
            addCriterion("supplier_actual_quality_qs <=", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsLike(String value) {
            addCriterion("supplier_actual_quality_qs like", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsNotLike(String value) {
            addCriterion("supplier_actual_quality_qs not like", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsIn(List<String> values) {
            addCriterion("supplier_actual_quality_qs in", values, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsNotIn(List<String> values) {
            addCriterion("supplier_actual_quality_qs not in", values, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsBetween(String value1, String value2) {
            addCriterion("supplier_actual_quality_qs between", value1, value2, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsNotBetween(String value1, String value2) {
            addCriterion("supplier_actual_quality_qs not between", value1, value2, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMIsNull() {
            addCriterion("supplier_actual_number_m is null");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMIsNotNull() {
            addCriterion("supplier_actual_number_m is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMEqualTo(String value) {
            addCriterion("supplier_actual_number_m =", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMNotEqualTo(String value) {
            addCriterion("supplier_actual_number_m <>", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMGreaterThan(String value) {
            addCriterion("supplier_actual_number_m >", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_actual_number_m >=", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMLessThan(String value) {
            addCriterion("supplier_actual_number_m <", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMLessThanOrEqualTo(String value) {
            addCriterion("supplier_actual_number_m <=", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMLike(String value) {
            addCriterion("supplier_actual_number_m like", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMNotLike(String value) {
            addCriterion("supplier_actual_number_m not like", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMIn(List<String> values) {
            addCriterion("supplier_actual_number_m in", values, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMNotIn(List<String> values) {
            addCriterion("supplier_actual_number_m not in", values, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMBetween(String value1, String value2) {
            addCriterion("supplier_actual_number_m between", value1, value2, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMNotBetween(String value1, String value2) {
            addCriterion("supplier_actual_number_m not between", value1, value2, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreIsNull() {
            addCriterion("engine_factory_to_supplier_score is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreIsNotNull() {
            addCriterion("engine_factory_to_supplier_score is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreEqualTo(Integer value) {
            addCriterion("engine_factory_to_supplier_score =", value, "engineFactoryToSupplierScore");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreNotEqualTo(Integer value) {
            addCriterion("engine_factory_to_supplier_score <>", value, "engineFactoryToSupplierScore");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreGreaterThan(Integer value) {
            addCriterion("engine_factory_to_supplier_score >", value, "engineFactoryToSupplierScore");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_to_supplier_score >=", value, "engineFactoryToSupplierScore");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreLessThan(Integer value) {
            addCriterion("engine_factory_to_supplier_score <", value, "engineFactoryToSupplierScore");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_to_supplier_score <=", value, "engineFactoryToSupplierScore");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreIn(List<Integer> values) {
            addCriterion("engine_factory_to_supplier_score in", values, "engineFactoryToSupplierScore");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreNotIn(List<Integer> values) {
            addCriterion("engine_factory_to_supplier_score not in", values, "engineFactoryToSupplierScore");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_to_supplier_score between", value1, value2, "engineFactoryToSupplierScore");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToSupplierScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_to_supplier_score not between", value1, value2, "engineFactoryToSupplierScore");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreIsNull() {
            addCriterion("supplier_to_engine_factory_score is null");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreIsNotNull() {
            addCriterion("supplier_to_engine_factory_score is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreEqualTo(Integer value) {
            addCriterion("supplier_to_engine_factory_score =", value, "supplierToEngineFactoryScore");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreNotEqualTo(Integer value) {
            addCriterion("supplier_to_engine_factory_score <>", value, "supplierToEngineFactoryScore");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreGreaterThan(Integer value) {
            addCriterion("supplier_to_engine_factory_score >", value, "supplierToEngineFactoryScore");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_to_engine_factory_score >=", value, "supplierToEngineFactoryScore");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreLessThan(Integer value) {
            addCriterion("supplier_to_engine_factory_score <", value, "supplierToEngineFactoryScore");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_to_engine_factory_score <=", value, "supplierToEngineFactoryScore");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreIn(List<Integer> values) {
            addCriterion("supplier_to_engine_factory_score in", values, "supplierToEngineFactoryScore");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreNotIn(List<Integer> values) {
            addCriterion("supplier_to_engine_factory_score not in", values, "supplierToEngineFactoryScore");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreBetween(Integer value1, Integer value2) {
            addCriterion("supplier_to_engine_factory_score between", value1, value2, "supplierToEngineFactoryScore");
            return (Criteria) this;
        }

        public Criteria andSupplierToEngineFactoryScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("supplier_to_engine_factory_score not between", value1, value2, "supplierToEngineFactoryScore");
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