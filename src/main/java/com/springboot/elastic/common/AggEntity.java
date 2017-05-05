package com.springboot.elastic.common;

import java.util.List;

/**
 * ElasticSearch 桶聚合包装参数的实体类
 */
public class AggEntity {
    private String aggName;
    private String field;
    private List<RangeEntity> rangeEntities;
    private List<AggEntity> subList;
    private Integer size;

    public String getAggName() {
        return aggName;
    }
    public void setAggName(String aggName) {
        this.aggName = aggName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<RangeEntity> getRangeEntities() {
        return rangeEntities;
    }

    public void setRangeEntities(List<RangeEntity> rangeEntities) {
        this.rangeEntities = rangeEntities;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<AggEntity> getSubList() {
        return subList;
    }

    public void setSubList(List<AggEntity> subList) {
        this.subList = subList;
    }
}
