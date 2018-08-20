package org.chobit.ash.model;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author robin
 */
public class ParamMap extends HashMap<String, Object> {


    public ParamMap() {
        super();
    }


    public ParamMap(int initialCapacity) {
        super(initialCapacity);
    }


    public void set(String key, Object value) {
        if (null != key && null != value) {
            super.put(key, value);
        } else {
            throw new NullPointerException();
        }
    }

    public Object get(String key) {
        return super.get(key);
    }

    public Object getOrElse(String key, Object defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.get(key);
    }

    public void setString(String key, String value) {
        this.set(key, value);
    }

    public String getString(String key) {
        return (String) this.get(key);
    }

    public String getOrElse(String key, String defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.getString(key);
    }

    public void setInt(String key, Integer value) {
        this.set(key, value);
    }

    public Integer getInt(String key) {
        return (Integer) this.get(key);
    }

    public Integer getOrElse(String key, Integer defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.getInt(key);
    }

    public void setLong(String key, Long value) {
        this.set(key, value);
    }

    public Long getLong(String key) {
        return (Long) this.get(key);
    }

    public Long getOrElse(String key, Long defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.getLong(key);
    }

    public void setDouble(String key, Double value) {
        this.set(key, value);
    }

    public Double getDouble(String key) {
        return (Double) this.get(key);
    }

    public Double getOrElse(String key, Double defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.getDouble(key);
    }

    public void setBoolean(String key, Boolean value) {
        this.set(key, value);
    }

    public Boolean getBoolean(String key) {
        return (Boolean) this.get(key);
    }

    public Boolean getOrElse(String key, Boolean defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.getBoolean(key);
    }

    public void setBigInteger(String key, BigInteger value) {
        this.set(key, value);
    }

    public BigInteger getBigInteger(String key) {
        return (BigInteger) this.get(key);
    }

    public BigInteger getOrElse(String key, BigInteger defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.getBigInteger(key);
    }

    public void setBigDecimal(String key, BigDecimal value) {
        this.set(key, value);
    }

    public BigDecimal getBigDecimal(String key) {
        return (BigDecimal) this.get(key);
    }

    public BigDecimal getOrElse(String key, BigDecimal defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.getBigDecimal(key);
    }

    public void setDate(String key, Date value) {
        this.set(key, value);
    }

    public Date getDate(String key) {
        return (Date) this.get(key);
    }

    public Date getOrElse(String key, Date defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.getDate(key);
    }

    public void setTimestamp(String key, Timestamp value) {
        this.set(key, value);
    }

    public Timestamp getTimestamp(String key) {
        return (Timestamp) this.get(key);
    }

    public Timestamp getOrElse(String key, Timestamp defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.getTimestamp(key);
    }

    public void setParamMap(String key, ParamMap value) {
        this.set(key, value);
    }

    public ParamMap getParamMap(String key) {
        ParamMap map = new ParamMap();
        if (null != this.get(key)) {
            map.putAll((Map) this.get(key));
        }

        return map;
    }

    public ParamMap getOrElse(String key, ParamMap defaultValue) {
        return !this.containsKey(key) ? defaultValue : this.getParamMap(key);
    }
}
