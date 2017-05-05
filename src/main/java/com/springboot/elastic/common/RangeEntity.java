package com.springboot.elastic.common;

/**
 * ES范围查询的实体类
 * 包左不包右
 * @author czt
 * @
 */
public class RangeEntity {

    //the key to use for this range in the response
    private String name;
    //开始 null代表是addUnboundedTo
    //the lower bound on the distances, inclusive
    private Integer from;
    //到达 null代表是addUnboundedFrom
    // the upper bound on the distances, exclusive
    private Integer to;

    public RangeEntity() { }

    public RangeEntity(String name, Integer from, Integer to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getFrom() {
        return from;
    }
    public void setFrom(Integer from) {
        this.from = from;
    }
    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}
