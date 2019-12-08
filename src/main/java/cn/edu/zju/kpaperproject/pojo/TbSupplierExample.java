package cn.edu.zju.kpaperproject.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbSupplierExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbSupplierExample() {
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

        public Criteria andSupplierLocationGXIsNull() {
            addCriterion("supplier_location_g_x is null");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXIsNotNull() {
            addCriterion("supplier_location_g_x is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXEqualTo(Integer value) {
            addCriterion("supplier_location_g_x =", value, "supplierLocationGX");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXNotEqualTo(Integer value) {
            addCriterion("supplier_location_g_x <>", value, "supplierLocationGX");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXGreaterThan(Integer value) {
            addCriterion("supplier_location_g_x >", value, "supplierLocationGX");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_location_g_x >=", value, "supplierLocationGX");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXLessThan(Integer value) {
            addCriterion("supplier_location_g_x <", value, "supplierLocationGX");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_location_g_x <=", value, "supplierLocationGX");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXIn(List<Integer> values) {
            addCriterion("supplier_location_g_x in", values, "supplierLocationGX");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXNotIn(List<Integer> values) {
            addCriterion("supplier_location_g_x not in", values, "supplierLocationGX");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXBetween(Integer value1, Integer value2) {
            addCriterion("supplier_location_g_x between", value1, value2, "supplierLocationGX");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGXNotBetween(Integer value1, Integer value2) {
            addCriterion("supplier_location_g_x not between", value1, value2, "supplierLocationGX");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYIsNull() {
            addCriterion("supplier_location_g_y is null");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYIsNotNull() {
            addCriterion("supplier_location_g_y is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYEqualTo(Integer value) {
            addCriterion("supplier_location_g_y =", value, "supplierLocationGY");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYNotEqualTo(Integer value) {
            addCriterion("supplier_location_g_y <>", value, "supplierLocationGY");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYGreaterThan(Integer value) {
            addCriterion("supplier_location_g_y >", value, "supplierLocationGY");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_location_g_y >=", value, "supplierLocationGY");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYLessThan(Integer value) {
            addCriterion("supplier_location_g_y <", value, "supplierLocationGY");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_location_g_y <=", value, "supplierLocationGY");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYIn(List<Integer> values) {
            addCriterion("supplier_location_g_y in", values, "supplierLocationGY");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYNotIn(List<Integer> values) {
            addCriterion("supplier_location_g_y not in", values, "supplierLocationGY");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYBetween(Integer value1, Integer value2) {
            addCriterion("supplier_location_g_y between", value1, value2, "supplierLocationGY");
            return (Criteria) this;
        }

        public Criteria andSupplierLocationGYNotBetween(Integer value1, Integer value2) {
            addCriterion("supplier_location_g_y not between", value1, value2, "supplierLocationGY");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeIsNull() {
            addCriterion("supplier_type is null");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeIsNotNull() {
            addCriterion("supplier_type is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeEqualTo(Integer value) {
            addCriterion("supplier_type =", value, "supplierType");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeNotEqualTo(Integer value) {
            addCriterion("supplier_type <>", value, "supplierType");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeGreaterThan(Integer value) {
            addCriterion("supplier_type >", value, "supplierType");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_type >=", value, "supplierType");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeLessThan(Integer value) {
            addCriterion("supplier_type <", value, "supplierType");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_type <=", value, "supplierType");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeIn(List<Integer> values) {
            addCriterion("supplier_type in", values, "supplierType");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeNotIn(List<Integer> values) {
            addCriterion("supplier_type not in", values, "supplierType");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeBetween(Integer value1, Integer value2) {
            addCriterion("supplier_type between", value1, value2, "supplierType");
            return (Criteria) this;
        }

        public Criteria andSupplierTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("supplier_type not between", value1, value2, "supplierType");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCIsNull() {
            addCriterion("supplier_fixed_cost_c is null");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCIsNotNull() {
            addCriterion("supplier_fixed_cost_c is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCEqualTo(Integer value) {
            addCriterion("supplier_fixed_cost_c =", value, "supplierFixedCostC");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCNotEqualTo(Integer value) {
            addCriterion("supplier_fixed_cost_c <>", value, "supplierFixedCostC");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCGreaterThan(Integer value) {
            addCriterion("supplier_fixed_cost_c >", value, "supplierFixedCostC");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplier_fixed_cost_c >=", value, "supplierFixedCostC");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCLessThan(Integer value) {
            addCriterion("supplier_fixed_cost_c <", value, "supplierFixedCostC");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCLessThanOrEqualTo(Integer value) {
            addCriterion("supplier_fixed_cost_c <=", value, "supplierFixedCostC");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCIn(List<Integer> values) {
            addCriterion("supplier_fixed_cost_c in", values, "supplierFixedCostC");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCNotIn(List<Integer> values) {
            addCriterion("supplier_fixed_cost_c not in", values, "supplierFixedCostC");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCBetween(Integer value1, Integer value2) {
            addCriterion("supplier_fixed_cost_c between", value1, value2, "supplierFixedCostC");
            return (Criteria) this;
        }

        public Criteria andSupplierFixedCostCNotBetween(Integer value1, Integer value2) {
            addCriterion("supplier_fixed_cost_c not between", value1, value2, "supplierFixedCostC");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveIsNull() {
            addCriterion("supplier_alive is null");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveIsNotNull() {
            addCriterion("supplier_alive is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveEqualTo(Boolean value) {
            addCriterion("supplier_alive =", value, "supplierAlive");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveNotEqualTo(Boolean value) {
            addCriterion("supplier_alive <>", value, "supplierAlive");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveGreaterThan(Boolean value) {
            addCriterion("supplier_alive >", value, "supplierAlive");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("supplier_alive >=", value, "supplierAlive");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveLessThan(Boolean value) {
            addCriterion("supplier_alive <", value, "supplierAlive");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveLessThanOrEqualTo(Boolean value) {
            addCriterion("supplier_alive <=", value, "supplierAlive");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveIn(List<Boolean> values) {
            addCriterion("supplier_alive in", values, "supplierAlive");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveNotIn(List<Boolean> values) {
            addCriterion("supplier_alive not in", values, "supplierAlive");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveBetween(Boolean value1, Boolean value2) {
            addCriterion("supplier_alive between", value1, value2, "supplierAlive");
            return (Criteria) this;
        }

        public Criteria andSupplierAliveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("supplier_alive not between", value1, value2, "supplierAlive");
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