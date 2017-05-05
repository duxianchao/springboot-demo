package com.springboot.elastic.user;

import com.springboot.elastic.user.document.User;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/5/4.
 */
public class UserResultsMapper implements ResultsMapper {

    @Override
    public EntityMapper getEntityMapper() {
        return null;
    }

    @Override
    public <T> T mapResult(GetResponse response, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> LinkedList<T> mapResults(MultiGetResponse responses, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        return null;
    }
}
