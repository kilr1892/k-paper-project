package cn.edu.zju.kpaperproject.pojo;

import java.util.ArrayList;
import java.util.List;

public class OrderPlusExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderPlusExample() {
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

        public Criteria andEngineToSupplierApEqualTo(Double value) {
            addCriterion("engine_to_supplier_ap =", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApNotEqualTo(Double value) {
            addCriterion("engine_to_supplier_ap <>", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApGreaterThan(Double value) {
            addCriterion("engine_to_supplier_ap >", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApGreaterThanOrEqualTo(Double value) {
            addCriterion("engine_to_supplier_ap >=", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApLessThan(Double value) {
            addCriterion("engine_to_supplier_ap <", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApLessThanOrEqualTo(Double value) {
            addCriterion("engine_to_supplier_ap <=", value, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApIn(List<Double> values) {
            addCriterion("engine_to_supplier_ap in", values, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApNotIn(List<Double> values) {
            addCriterion("engine_to_supplier_ap not in", values, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApBetween(Double value1, Double value2) {
            addCriterion("engine_to_supplier_ap between", value1, value2, "engineToSupplierAp");
            return (Criteria) this;
        }

        public Criteria andEngineToSupplierApNotBetween(Double value1, Double value2) {
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

        public Criteria andSupplierEngineToApEqualTo(Double value) {
            addCriterion("supplier_engine_to_ap =", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApNotEqualTo(Double value) {
            addCriterion("supplier_engine_to_ap <>", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApGreaterThan(Double value) {
            addCriterion("supplier_engine_to_ap >", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApGreaterThanOrEqualTo(Double value) {
            addCriterion("supplier_engine_to_ap >=", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApLessThan(Double value) {
            addCriterion("supplier_engine_to_ap <", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApLessThanOrEqualTo(Double value) {
            addCriterion("supplier_engine_to_ap <=", value, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApIn(List<Double> values) {
            addCriterion("supplier_engine_to_ap in", values, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApNotIn(List<Double> values) {
            addCriterion("supplier_engine_to_ap not in", values, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApBetween(Double value1, Double value2) {
            addCriterion("supplier_engine_to_ap between", value1, value2, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andSupplierEngineToApNotBetween(Double value1, Double value2) {
            addCriterion("supplier_engine_to_ap not between", value1, value2, "supplierEngineToAp");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractIsNull() {
            addCriterion("engine_whether_perform_contract is null");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractIsNotNull() {
            addCriterion("engine_whether_perform_contract is not null");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractEqualTo(Boolean value) {
            addCriterion("engine_whether_perform_contract =", value, "engineWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractNotEqualTo(Boolean value) {
            addCriterion("engine_whether_perform_contract <>", value, "engineWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractGreaterThan(Boolean value) {
            addCriterion("engine_whether_perform_contract >", value, "engineWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractGreaterThanOrEqualTo(Boolean value) {
            addCriterion("engine_whether_perform_contract >=", value, "engineWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractLessThan(Boolean value) {
            addCriterion("engine_whether_perform_contract <", value, "engineWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractLessThanOrEqualTo(Boolean value) {
            addCriterion("engine_whether_perform_contract <=", value, "engineWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractIn(List<Boolean> values) {
            addCriterion("engine_whether_perform_contract in", values, "engineWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractNotIn(List<Boolean> values) {
            addCriterion("engine_whether_perform_contract not in", values, "engineWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractBetween(Boolean value1, Boolean value2) {
            addCriterion("engine_whether_perform_contract between", value1, value2, "engineWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andEngineWhetherPerformContractNotBetween(Boolean value1, Boolean value2) {
            addCriterion("engine_whether_perform_contract not between", value1, value2, "engineWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractIsNull() {
            addCriterion("supplier_whether_perform_contract is null");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractIsNotNull() {
            addCriterion("supplier_whether_perform_contract is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractEqualTo(Boolean value) {
            addCriterion("supplier_whether_perform_contract =", value, "supplierWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractNotEqualTo(Boolean value) {
            addCriterion("supplier_whether_perform_contract <>", value, "supplierWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractGreaterThan(Boolean value) {
            addCriterion("supplier_whether_perform_contract >", value, "supplierWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractGreaterThanOrEqualTo(Boolean value) {
            addCriterion("supplier_whether_perform_contract >=", value, "supplierWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractLessThan(Boolean value) {
            addCriterion("supplier_whether_perform_contract <", value, "supplierWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractLessThanOrEqualTo(Boolean value) {
            addCriterion("supplier_whether_perform_contract <=", value, "supplierWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractIn(List<Boolean> values) {
            addCriterion("supplier_whether_perform_contract in", values, "supplierWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractNotIn(List<Boolean> values) {
            addCriterion("supplier_whether_perform_contract not in", values, "supplierWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractBetween(Boolean value1, Boolean value2) {
            addCriterion("supplier_whether_perform_contract between", value1, value2, "supplierWhetherPerformContract");
            return (Criteria) this;
        }

        public Criteria andSupplierWhetherPerformContractNotBetween(Boolean value1, Boolean value2) {
            addCriterion("supplier_whether_perform_contract not between", value1, value2, "supplierWhetherPerformContract");
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

        public Criteria andSupplierActualPricePEqualTo(Integer value) {
            addCriterion("supplier_actual_price_p =", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePNotEqualTo(Integer value) {
            addCriterion("supplier_actual_price_p <>", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePGreaterThan(Integer value) {
            addCriterion("supplier_actual_price_p >", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_actual_price_p >=", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePLessThan(Integer value) {
            addCriterion("supplier_actual_price_p <", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_actual_price_p <=", value, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePIn(List<Integer> values) {
            addCriterion("supplier_actual_price_p in", values, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePNotIn(List<Integer> values) {
            addCriterion("supplier_actual_price_p not in", values, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePBetween(Integer value1, Integer value2) {
            addCriterion("supplier_actual_price_p between", value1, value2, "supplierActualPriceP");
            return (Criteria) this;
        }

        public Criteria andSupplierActualPricePNotBetween(Integer value1, Integer value2) {
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

        public Criteria andSupplierActualQualityQsEqualTo(Integer value) {
            addCriterion("supplier_actual_quality_qs =", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsNotEqualTo(Integer value) {
            addCriterion("supplier_actual_quality_qs <>", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsGreaterThan(Integer value) {
            addCriterion("supplier_actual_quality_qs >", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_actual_quality_qs >=", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsLessThan(Integer value) {
            addCriterion("supplier_actual_quality_qs <", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_actual_quality_qs <=", value, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsIn(List<Integer> values) {
            addCriterion("supplier_actual_quality_qs in", values, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsNotIn(List<Integer> values) {
            addCriterion("supplier_actual_quality_qs not in", values, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsBetween(Integer value1, Integer value2) {
            addCriterion("supplier_actual_quality_qs between", value1, value2, "supplierActualQualityQs");
            return (Criteria) this;
        }

        public Criteria andSupplierActualQualityQsNotBetween(Integer value1, Integer value2) {
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

        public Criteria andSupplierActualNumberMEqualTo(Integer value) {
            addCriterion("supplier_actual_number_m =", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMNotEqualTo(Integer value) {
            addCriterion("supplier_actual_number_m <>", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMGreaterThan(Integer value) {
            addCriterion("supplier_actual_number_m >", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_actual_number_m >=", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMLessThan(Integer value) {
            addCriterion("supplier_actual_number_m <", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_actual_number_m <=", value, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMIn(List<Integer> values) {
            addCriterion("supplier_actual_number_m in", values, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMNotIn(List<Integer> values) {
            addCriterion("supplier_actual_number_m not in", values, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMBetween(Integer value1, Integer value2) {
            addCriterion("supplier_actual_number_m between", value1, value2, "supplierActualNumberM");
            return (Criteria) this;
        }

        public Criteria andSupplierActualNumberMNotBetween(Integer value1, Integer value2) {
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

        public Criteria andEngineFactoryInitCreditIsNull() {
            addCriterion("engine_factory_init_credit is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditIsNotNull() {
            addCriterion("engine_factory_init_credit is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditEqualTo(Double value) {
            addCriterion("engine_factory_init_credit =", value, "engineFactoryInitCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditNotEqualTo(Double value) {
            addCriterion("engine_factory_init_credit <>", value, "engineFactoryInitCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditGreaterThan(Double value) {
            addCriterion("engine_factory_init_credit >", value, "engineFactoryInitCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditGreaterThanOrEqualTo(Double value) {
            addCriterion("engine_factory_init_credit >=", value, "engineFactoryInitCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditLessThan(Double value) {
            addCriterion("engine_factory_init_credit <", value, "engineFactoryInitCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditLessThanOrEqualTo(Double value) {
            addCriterion("engine_factory_init_credit <=", value, "engineFactoryInitCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditIn(List<Double> values) {
            addCriterion("engine_factory_init_credit in", values, "engineFactoryInitCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditNotIn(List<Double> values) {
            addCriterion("engine_factory_init_credit not in", values, "engineFactoryInitCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditBetween(Double value1, Double value2) {
            addCriterion("engine_factory_init_credit between", value1, value2, "engineFactoryInitCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryInitCreditNotBetween(Double value1, Double value2) {
            addCriterion("engine_factory_init_credit not between", value1, value2, "engineFactoryInitCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditIsNull() {
            addCriterion("engine_factory_new_credit is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditIsNotNull() {
            addCriterion("engine_factory_new_credit is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditEqualTo(Double value) {
            addCriterion("engine_factory_new_credit =", value, "engineFactoryNewCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditNotEqualTo(Double value) {
            addCriterion("engine_factory_new_credit <>", value, "engineFactoryNewCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditGreaterThan(Double value) {
            addCriterion("engine_factory_new_credit >", value, "engineFactoryNewCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditGreaterThanOrEqualTo(Double value) {
            addCriterion("engine_factory_new_credit >=", value, "engineFactoryNewCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditLessThan(Double value) {
            addCriterion("engine_factory_new_credit <", value, "engineFactoryNewCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditLessThanOrEqualTo(Double value) {
            addCriterion("engine_factory_new_credit <=", value, "engineFactoryNewCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditIn(List<Double> values) {
            addCriterion("engine_factory_new_credit in", values, "engineFactoryNewCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditNotIn(List<Double> values) {
            addCriterion("engine_factory_new_credit not in", values, "engineFactoryNewCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditBetween(Double value1, Double value2) {
            addCriterion("engine_factory_new_credit between", value1, value2, "engineFactoryNewCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryNewCreditNotBetween(Double value1, Double value2) {
            addCriterion("engine_factory_new_credit not between", value1, value2, "engineFactoryNewCredit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowIsNull() {
            addCriterion("engine_factory_to_service_offer_price_low is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowIsNotNull() {
            addCriterion("engine_factory_to_service_offer_price_low is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowEqualTo(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_low =", value, "engineFactoryToServiceOfferPriceLow");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowNotEqualTo(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_low <>", value, "engineFactoryToServiceOfferPriceLow");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowGreaterThan(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_low >", value, "engineFactoryToServiceOfferPriceLow");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_low >=", value, "engineFactoryToServiceOfferPriceLow");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowLessThan(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_low <", value, "engineFactoryToServiceOfferPriceLow");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_low <=", value, "engineFactoryToServiceOfferPriceLow");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowIn(List<Integer> values) {
            addCriterion("engine_factory_to_service_offer_price_low in", values, "engineFactoryToServiceOfferPriceLow");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowNotIn(List<Integer> values) {
            addCriterion("engine_factory_to_service_offer_price_low not in", values, "engineFactoryToServiceOfferPriceLow");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_to_service_offer_price_low between", value1, value2, "engineFactoryToServiceOfferPriceLow");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceLowNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_to_service_offer_price_low not between", value1, value2, "engineFactoryToServiceOfferPriceLow");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperIsNull() {
            addCriterion("engine_factory_to_service_offer_price_upper is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperIsNotNull() {
            addCriterion("engine_factory_to_service_offer_price_upper is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperEqualTo(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_upper =", value, "engineFactoryToServiceOfferPriceUpper");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperNotEqualTo(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_upper <>", value, "engineFactoryToServiceOfferPriceUpper");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperGreaterThan(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_upper >", value, "engineFactoryToServiceOfferPriceUpper");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_upper >=", value, "engineFactoryToServiceOfferPriceUpper");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperLessThan(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_upper <", value, "engineFactoryToServiceOfferPriceUpper");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_to_service_offer_price_upper <=", value, "engineFactoryToServiceOfferPriceUpper");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperIn(List<Integer> values) {
            addCriterion("engine_factory_to_service_offer_price_upper in", values, "engineFactoryToServiceOfferPriceUpper");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperNotIn(List<Integer> values) {
            addCriterion("engine_factory_to_service_offer_price_upper not in", values, "engineFactoryToServiceOfferPriceUpper");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_to_service_offer_price_upper between", value1, value2, "engineFactoryToServiceOfferPriceUpper");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryToServiceOfferPriceUpperNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_to_service_offer_price_upper not between", value1, value2, "engineFactoryToServiceOfferPriceUpper");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditIsNull() {
            addCriterion("supplier_init_credit is null");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditIsNotNull() {
            addCriterion("supplier_init_credit is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditEqualTo(Double value) {
            addCriterion("supplier_init_credit =", value, "supplierInitCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditNotEqualTo(Double value) {
            addCriterion("supplier_init_credit <>", value, "supplierInitCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditGreaterThan(Double value) {
            addCriterion("supplier_init_credit >", value, "supplierInitCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditGreaterThanOrEqualTo(Double value) {
            addCriterion("supplier_init_credit >=", value, "supplierInitCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditLessThan(Double value) {
            addCriterion("supplier_init_credit <", value, "supplierInitCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditLessThanOrEqualTo(Double value) {
            addCriterion("supplier_init_credit <=", value, "supplierInitCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditIn(List<Double> values) {
            addCriterion("supplier_init_credit in", values, "supplierInitCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditNotIn(List<Double> values) {
            addCriterion("supplier_init_credit not in", values, "supplierInitCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditBetween(Double value1, Double value2) {
            addCriterion("supplier_init_credit between", value1, value2, "supplierInitCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierInitCreditNotBetween(Double value1, Double value2) {
            addCriterion("supplier_init_credit not between", value1, value2, "supplierInitCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditIsNull() {
            addCriterion("supplier_new_credit is null");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditIsNotNull() {
            addCriterion("supplier_new_credit is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditEqualTo(Double value) {
            addCriterion("supplier_new_credit =", value, "supplierNewCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditNotEqualTo(Double value) {
            addCriterion("supplier_new_credit <>", value, "supplierNewCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditGreaterThan(Double value) {
            addCriterion("supplier_new_credit >", value, "supplierNewCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditGreaterThanOrEqualTo(Double value) {
            addCriterion("supplier_new_credit >=", value, "supplierNewCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditLessThan(Double value) {
            addCriterion("supplier_new_credit <", value, "supplierNewCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditLessThanOrEqualTo(Double value) {
            addCriterion("supplier_new_credit <=", value, "supplierNewCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditIn(List<Double> values) {
            addCriterion("supplier_new_credit in", values, "supplierNewCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditNotIn(List<Double> values) {
            addCriterion("supplier_new_credit not in", values, "supplierNewCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditBetween(Double value1, Double value2) {
            addCriterion("supplier_new_credit between", value1, value2, "supplierNewCredit");
            return (Criteria) this;
        }

        public Criteria andSupplierNewCreditNotBetween(Double value1, Double value2) {
            addCriterion("supplier_new_credit not between", value1, value2, "supplierNewCredit");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthIsNull() {
            addCriterion("relationship_strength is null");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthIsNotNull() {
            addCriterion("relationship_strength is not null");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthEqualTo(Double value) {
            addCriterion("relationship_strength =", value, "relationshipStrength");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthNotEqualTo(Double value) {
            addCriterion("relationship_strength <>", value, "relationshipStrength");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthGreaterThan(Double value) {
            addCriterion("relationship_strength >", value, "relationshipStrength");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthGreaterThanOrEqualTo(Double value) {
            addCriterion("relationship_strength >=", value, "relationshipStrength");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthLessThan(Double value) {
            addCriterion("relationship_strength <", value, "relationshipStrength");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthLessThanOrEqualTo(Double value) {
            addCriterion("relationship_strength <=", value, "relationshipStrength");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthIn(List<Double> values) {
            addCriterion("relationship_strength in", values, "relationshipStrength");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthNotIn(List<Double> values) {
            addCriterion("relationship_strength not in", values, "relationshipStrength");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthBetween(Double value1, Double value2) {
            addCriterion("relationship_strength between", value1, value2, "relationshipStrength");
            return (Criteria) this;
        }

        public Criteria andRelationshipStrengthNotBetween(Double value1, Double value2) {
            addCriterion("relationship_strength not between", value1, value2, "relationshipStrength");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitIsNull() {
            addCriterion("engine_factory_profit is null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitIsNotNull() {
            addCriterion("engine_factory_profit is not null");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitEqualTo(Integer value) {
            addCriterion("engine_factory_profit =", value, "engineFactoryProfit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitNotEqualTo(Integer value) {
            addCriterion("engine_factory_profit <>", value, "engineFactoryProfit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitGreaterThan(Integer value) {
            addCriterion("engine_factory_profit >", value, "engineFactoryProfit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitGreaterThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_profit >=", value, "engineFactoryProfit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitLessThan(Integer value) {
            addCriterion("engine_factory_profit <", value, "engineFactoryProfit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitLessThanOrEqualTo(Integer value) {
            addCriterion("engine_factory_profit <=", value, "engineFactoryProfit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitIn(List<Integer> values) {
            addCriterion("engine_factory_profit in", values, "engineFactoryProfit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitNotIn(List<Integer> values) {
            addCriterion("engine_factory_profit not in", values, "engineFactoryProfit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_profit between", value1, value2, "engineFactoryProfit");
            return (Criteria) this;
        }

        public Criteria andEngineFactoryProfitNotBetween(Integer value1, Integer value2) {
            addCriterion("engine_factory_profit not between", value1, value2, "engineFactoryProfit");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitIsNull() {
            addCriterion("supplier_profit is null");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitIsNotNull() {
            addCriterion("supplier_profit is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitEqualTo(Integer value) {
            addCriterion("supplier_profit =", value, "supplierProfit");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitNotEqualTo(Integer value) {
            addCriterion("supplier_profit <>", value, "supplierProfit");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitGreaterThan(Integer value) {
            addCriterion("supplier_profit >", value, "supplierProfit");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_profit >=", value, "supplierProfit");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitLessThan(Integer value) {
            addCriterion("supplier_profit <", value, "supplierProfit");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_profit <=", value, "supplierProfit");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitIn(List<Integer> values) {
            addCriterion("supplier_profit in", values, "supplierProfit");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitNotIn(List<Integer> values) {
            addCriterion("supplier_profit not in", values, "supplierProfit");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitBetween(Integer value1, Integer value2) {
            addCriterion("supplier_profit between", value1, value2, "supplierProfit");
            return (Criteria) this;
        }

        public Criteria andSupplierProfitNotBetween(Integer value1, Integer value2) {
            addCriterion("supplier_profit not between", value1, value2, "supplierProfit");
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