package com.springboot.elastic.common;


/**
 *封装了ES查询的一些参数的实体类
 */
public class EsEntity {
    //聚合实体类
    private AggEntity aggEntity;
    //查询实体类
    private QueryEntity queryEntity;
    //排序实体类
    private SortEntity sortEntity;
    //分页的实体类
    private PageEntity pageEntity;

    public PageEntity getPageEntity() {
        return pageEntity;
    }

    public void setPageEntity(PageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }

    public AggEntity getAggEntity() {
        return aggEntity;
    }

    public void setAggEntity(AggEntity aggEntity) {
        this.aggEntity = aggEntity;
    }

    public QueryEntity getQueryEntity() {
        return queryEntity;
    }

    public void setQueryEntity(QueryEntity queryEntity) {
        this.queryEntity = queryEntity;
    }

    public SortEntity getSortEntity() {
        return sortEntity;
    }

    public void setSortEntity(SortEntity sortEntity) {
        this.sortEntity = sortEntity;
    }
}
