package com.springboot.elastic.common;

import org.elasticsearch.search.sort.SortOrder;

/**
 * 排序条件实体类
 */
public class SortEntity {

    //排序字段
    private String sortField;
    //desc asc
    private SortOrder sortOrder;


    public String getSortField() {
        return sortField;
    }
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }
    public SortOrder getSortOrder() {
        return sortOrder;
    }
    public void setSortOrder(SortOrder sortOrder) {
        this.sortOrder = sortOrder;
    }
}
