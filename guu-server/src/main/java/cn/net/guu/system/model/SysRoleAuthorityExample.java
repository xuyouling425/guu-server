package cn.net.guu.system.model;

import java.util.ArrayList;
import java.util.List;

import cn.net.guu.core.mappers.BaseExample;

public class SysRoleAuthorityExample extends BaseExample{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    public SysRoleAuthorityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
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
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
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

        public Criteria andRoleAuthorityIdIsNull() {
            addCriterion("role_authority_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdIsNotNull() {
            addCriterion("role_authority_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdEqualTo(String value) {
            addCriterion("role_authority_id =", value, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdNotEqualTo(String value) {
            addCriterion("role_authority_id <>", value, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdGreaterThan(String value) {
            addCriterion("role_authority_id >", value, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdGreaterThanOrEqualTo(String value) {
            addCriterion("role_authority_id >=", value, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdLessThan(String value) {
            addCriterion("role_authority_id <", value, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdLessThanOrEqualTo(String value) {
            addCriterion("role_authority_id <=", value, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdLike(String value) {
            addCriterion("role_authority_id like", value, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdNotLike(String value) {
            addCriterion("role_authority_id not like", value, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdIn(List<String> values) {
            addCriterion("role_authority_id in", values, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdNotIn(List<String> values) {
            addCriterion("role_authority_id not in", values, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdBetween(String value1, String value2) {
            addCriterion("role_authority_id between", value1, value2, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleAuthorityIdNotBetween(String value1, String value2) {
            addCriterion("role_authority_id not between", value1, value2, "roleAuthorityId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(String value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(String value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(String value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(String value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(String value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLike(String value) {
            addCriterion("role_id like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotLike(String value) {
            addCriterion("role_id not like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<String> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<String> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(String value1, String value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(String value1, String value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdIsNull() {
            addCriterion("authority_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdIsNotNull() {
            addCriterion("authority_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdEqualTo(String value) {
            addCriterion("authority_id =", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdNotEqualTo(String value) {
            addCriterion("authority_id <>", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdGreaterThan(String value) {
            addCriterion("authority_id >", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdGreaterThanOrEqualTo(String value) {
            addCriterion("authority_id >=", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdLessThan(String value) {
            addCriterion("authority_id <", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdLessThanOrEqualTo(String value) {
            addCriterion("authority_id <=", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdLike(String value) {
            addCriterion("authority_id like", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdNotLike(String value) {
            addCriterion("authority_id not like", value, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdIn(List<String> values) {
            addCriterion("authority_id in", values, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdNotIn(List<String> values) {
            addCriterion("authority_id not in", values, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdBetween(String value1, String value2) {
            addCriterion("authority_id between", value1, value2, "authorityId");
            return (Criteria) this;
        }

        public Criteria andAuthorityIdNotBetween(String value1, String value2) {
            addCriterion("authority_id not between", value1, value2, "authorityId");
            return (Criteria) this;
        }

        public Criteria andRaStatusIsNull() {
            addCriterion("ra_status is null");
            return (Criteria) this;
        }

        public Criteria andRaStatusIsNotNull() {
            addCriterion("ra_status is not null");
            return (Criteria) this;
        }

        public Criteria andRaStatusEqualTo(Integer value) {
            addCriterion("ra_status =", value, "raStatus");
            return (Criteria) this;
        }

        public Criteria andRaStatusNotEqualTo(Integer value) {
            addCriterion("ra_status <>", value, "raStatus");
            return (Criteria) this;
        }

        public Criteria andRaStatusGreaterThan(Integer value) {
            addCriterion("ra_status >", value, "raStatus");
            return (Criteria) this;
        }

        public Criteria andRaStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("ra_status >=", value, "raStatus");
            return (Criteria) this;
        }

        public Criteria andRaStatusLessThan(Integer value) {
            addCriterion("ra_status <", value, "raStatus");
            return (Criteria) this;
        }

        public Criteria andRaStatusLessThanOrEqualTo(Integer value) {
            addCriterion("ra_status <=", value, "raStatus");
            return (Criteria) this;
        }

        public Criteria andRaStatusIn(List<Integer> values) {
            addCriterion("ra_status in", values, "raStatus");
            return (Criteria) this;
        }

        public Criteria andRaStatusNotIn(List<Integer> values) {
            addCriterion("ra_status not in", values, "raStatus");
            return (Criteria) this;
        }

        public Criteria andRaStatusBetween(Integer value1, Integer value2) {
            addCriterion("ra_status between", value1, value2, "raStatus");
            return (Criteria) this;
        }

        public Criteria andRaStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("ra_status not between", value1, value2, "raStatus");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated do_not_delete_during_merge Sat Aug 02 00:13:15 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table guu_sys_role_authority
     *
     * @mbggenerated Sat Aug 02 00:13:15 CST 2014
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