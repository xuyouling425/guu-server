package cn.net.guu.system.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.net.guu.core.mappers.BaseExample;

public class SysAccessExample extends BaseExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    public SysAccessExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
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

        public Criteria andAccessIdIsNull() {
            addCriterion("access_id is null");
            return (Criteria) this;
        }

        public Criteria andAccessIdIsNotNull() {
            addCriterion("access_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccessIdEqualTo(String value) {
            addCriterion("access_id =", value, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdNotEqualTo(String value) {
            addCriterion("access_id <>", value, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdGreaterThan(String value) {
            addCriterion("access_id >", value, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdGreaterThanOrEqualTo(String value) {
            addCriterion("access_id >=", value, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdLessThan(String value) {
            addCriterion("access_id <", value, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdLessThanOrEqualTo(String value) {
            addCriterion("access_id <=", value, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdLike(String value) {
            addCriterion("access_id like", value, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdNotLike(String value) {
            addCriterion("access_id not like", value, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdIn(List<String> values) {
            addCriterion("access_id in", values, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdNotIn(List<String> values) {
            addCriterion("access_id not in", values, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdBetween(String value1, String value2) {
            addCriterion("access_id between", value1, value2, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessIdNotBetween(String value1, String value2) {
            addCriterion("access_id not between", value1, value2, "accessId");
            return (Criteria) this;
        }

        public Criteria andAccessNameIsNull() {
            addCriterion("access_name is null");
            return (Criteria) this;
        }

        public Criteria andAccessNameIsNotNull() {
            addCriterion("access_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccessNameEqualTo(String value) {
            addCriterion("access_name =", value, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameNotEqualTo(String value) {
            addCriterion("access_name <>", value, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameGreaterThan(String value) {
            addCriterion("access_name >", value, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameGreaterThanOrEqualTo(String value) {
            addCriterion("access_name >=", value, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameLessThan(String value) {
            addCriterion("access_name <", value, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameLessThanOrEqualTo(String value) {
            addCriterion("access_name <=", value, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameLike(String value) {
            addCriterion("access_name like", value, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameNotLike(String value) {
            addCriterion("access_name not like", value, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameIn(List<String> values) {
            addCriterion("access_name in", values, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameNotIn(List<String> values) {
            addCriterion("access_name not in", values, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameBetween(String value1, String value2) {
            addCriterion("access_name between", value1, value2, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessNameNotBetween(String value1, String value2) {
            addCriterion("access_name not between", value1, value2, "accessName");
            return (Criteria) this;
        }

        public Criteria andAccessCodeIsNull() {
            addCriterion("access_code is null");
            return (Criteria) this;
        }

        public Criteria andAccessCodeIsNotNull() {
            addCriterion("access_code is not null");
            return (Criteria) this;
        }

        public Criteria andAccessCodeEqualTo(String value) {
            addCriterion("access_code =", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotEqualTo(String value) {
            addCriterion("access_code <>", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeGreaterThan(String value) {
            addCriterion("access_code >", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeGreaterThanOrEqualTo(String value) {
            addCriterion("access_code >=", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeLessThan(String value) {
            addCriterion("access_code <", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeLessThanOrEqualTo(String value) {
            addCriterion("access_code <=", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeLike(String value) {
            addCriterion("access_code like", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotLike(String value) {
            addCriterion("access_code not like", value, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeIn(List<String> values) {
            addCriterion("access_code in", values, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotIn(List<String> values) {
            addCriterion("access_code not in", values, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeBetween(String value1, String value2) {
            addCriterion("access_code between", value1, value2, "accessCode");
            return (Criteria) this;
        }

        public Criteria andAccessCodeNotBetween(String value1, String value2) {
            addCriterion("access_code not between", value1, value2, "accessCode");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIsNull() {
            addCriterion("creat_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIsNotNull() {
            addCriterion("creat_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatTimeEqualTo(Date value) {
            addCriterion("creat_time =", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotEqualTo(Date value) {
            addCriterion("creat_time <>", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeGreaterThan(Date value) {
            addCriterion("creat_time >", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creat_time >=", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeLessThan(Date value) {
            addCriterion("creat_time <", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeLessThanOrEqualTo(Date value) {
            addCriterion("creat_time <=", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIn(List<Date> values) {
            addCriterion("creat_time in", values, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotIn(List<Date> values) {
            addCriterion("creat_time not in", values, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeBetween(Date value1, Date value2) {
            addCriterion("creat_time between", value1, value2, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotBetween(Date value1, Date value2) {
            addCriterion("creat_time not between", value1, value2, "creatTime");
            return (Criteria) this;
        }

        public Criteria andModuleIsNull() {
            addCriterion("module is null");
            return (Criteria) this;
        }

        public Criteria andModuleIsNotNull() {
            addCriterion("module is not null");
            return (Criteria) this;
        }

        public Criteria andModuleEqualTo(String value) {
            addCriterion("module =", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotEqualTo(String value) {
            addCriterion("module <>", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThan(String value) {
            addCriterion("module >", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThanOrEqualTo(String value) {
            addCriterion("module >=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThan(String value) {
            addCriterion("module <", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThanOrEqualTo(String value) {
            addCriterion("module <=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLike(String value) {
            addCriterion("module like", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotLike(String value) {
            addCriterion("module not like", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleIn(List<String> values) {
            addCriterion("module in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotIn(List<String> values) {
            addCriterion("module not in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleBetween(String value1, String value2) {
            addCriterion("module between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotBetween(String value1, String value2) {
            addCriterion("module not between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andAccessStatusIsNull() {
            addCriterion("access_status is null");
            return (Criteria) this;
        }

        public Criteria andAccessStatusIsNotNull() {
            addCriterion("access_status is not null");
            return (Criteria) this;
        }

        public Criteria andAccessStatusEqualTo(Integer value) {
            addCriterion("access_status =", value, "accessStatus");
            return (Criteria) this;
        }

        public Criteria andAccessStatusNotEqualTo(Integer value) {
            addCriterion("access_status <>", value, "accessStatus");
            return (Criteria) this;
        }

        public Criteria andAccessStatusGreaterThan(Integer value) {
            addCriterion("access_status >", value, "accessStatus");
            return (Criteria) this;
        }

        public Criteria andAccessStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("access_status >=", value, "accessStatus");
            return (Criteria) this;
        }

        public Criteria andAccessStatusLessThan(Integer value) {
            addCriterion("access_status <", value, "accessStatus");
            return (Criteria) this;
        }

        public Criteria andAccessStatusLessThanOrEqualTo(Integer value) {
            addCriterion("access_status <=", value, "accessStatus");
            return (Criteria) this;
        }

        public Criteria andAccessStatusIn(List<Integer> values) {
            addCriterion("access_status in", values, "accessStatus");
            return (Criteria) this;
        }

        public Criteria andAccessStatusNotIn(List<Integer> values) {
            addCriterion("access_status not in", values, "accessStatus");
            return (Criteria) this;
        }

        public Criteria andAccessStatusBetween(Integer value1, Integer value2) {
            addCriterion("access_status between", value1, value2, "accessStatus");
            return (Criteria) this;
        }

        public Criteria andAccessStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("access_status not between", value1, value2, "accessStatus");
            return (Criteria) this;
        }

        public Criteria andIssysIsNull() {
            addCriterion("issys is null");
            return (Criteria) this;
        }

        public Criteria andIssysIsNotNull() {
            addCriterion("issys is not null");
            return (Criteria) this;
        }

        public Criteria andIssysEqualTo(Integer value) {
            addCriterion("issys =", value, "issys");
            return (Criteria) this;
        }

        public Criteria andIssysNotEqualTo(Integer value) {
            addCriterion("issys <>", value, "issys");
            return (Criteria) this;
        }

        public Criteria andIssysGreaterThan(Integer value) {
            addCriterion("issys >", value, "issys");
            return (Criteria) this;
        }

        public Criteria andIssysGreaterThanOrEqualTo(Integer value) {
            addCriterion("issys >=", value, "issys");
            return (Criteria) this;
        }

        public Criteria andIssysLessThan(Integer value) {
            addCriterion("issys <", value, "issys");
            return (Criteria) this;
        }

        public Criteria andIssysLessThanOrEqualTo(Integer value) {
            addCriterion("issys <=", value, "issys");
            return (Criteria) this;
        }

        public Criteria andIssysIn(List<Integer> values) {
            addCriterion("issys in", values, "issys");
            return (Criteria) this;
        }

        public Criteria andIssysNotIn(List<Integer> values) {
            addCriterion("issys not in", values, "issys");
            return (Criteria) this;
        }

        public Criteria andIssysBetween(Integer value1, Integer value2) {
            addCriterion("issys between", value1, value2, "issys");
            return (Criteria) this;
        }

        public Criteria andIssysNotBetween(Integer value1, Integer value2) {
            addCriterion("issys not between", value1, value2, "issys");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table guu_sys_access
     *
     * @mbggenerated do_not_delete_during_merge Wed Jul 23 23:14:26 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table guu_sys_access
     *
     * @mbggenerated Wed Jul 23 23:14:26 CST 2014
     */
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