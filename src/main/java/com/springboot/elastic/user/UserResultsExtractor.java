package com.springboot.elastic.user;

import com.springboot.elastic.user.document.User;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.ResultsExtractor;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/4.
 */
public class UserResultsExtractor implements ResultsExtractor<User> {

    @Override
    public User extract(SearchResponse response) {
        SearchHits hits = response.getHits();
        SearchHit[] hits1 = hits.getHits();
        for (int i = 0; i <hits1.length ; i++) {
            SearchHit searchHit = hits1[i];
            System.out.println("UserResultsExtractor:"+searchHit.getHighlightFields());
            System.out.println("json:"+searchHit.sourceAsString());
        }


        return null;
    }
}
