package com.springboot.elastic.user.dao;

import com.springboot.elastic.common.AggEntity;
import com.springboot.elastic.common.RangeEntity;
import com.springboot.elastic.user.UserResultsExtractor;
import com.springboot.elastic.user.custom.UserRepositoryCustom;
import com.springboot.elastic.user.document.User;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.RangeBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public AggregatedPage<User> aggRangeByAge(AggEntity aggEntity) {
        if(aggEntity == null){
            return null;
        }
        String name = aggEntity.getAggName();
        String field = aggEntity.getField();
        List<RangeEntity> entities = aggEntity.getRangeEntities();
        RangeBuilder rangeBuilder = AggregationBuilders.range(name).field(field);
        entities.forEach(rangeEntity -> {
            if(rangeEntity.getFrom() == null){
                rangeBuilder.addUnboundedTo(rangeEntity.getName(),rangeEntity.getTo());
            }else if(rangeEntity.getTo() == null){
                rangeBuilder.addUnboundedFrom(rangeEntity.getName(),rangeEntity.getFrom());
            }else{
                rangeBuilder.addRange(rangeEntity.getName(),rangeEntity.getFrom(),rangeEntity.getTo());
            }
        });
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("age").lte(45));

        HighlightBuilder.Field field1 = new HighlightBuilder.Field("userName");
        field1.preTags("<p>");
        field1.postTags("</p>");
        SearchQuery query = new NativeSearchQueryBuilder()
                .addAggregation(rangeBuilder)
                .withSort(SortBuilders.fieldSort("age").order(SortOrder.DESC))
                .withFilter(boolQuery)
                .withFields("userName","gender","age")
                .withHighlightFields(field1)
                .withMinScore(0.5f)
                .withSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .withQuery(boolQuery)
                .withPageable(new PageRequest(382, 20))
                .build();

        User user = elasticsearchTemplate.query(query, new UserResultsExtractor());
        System.out.println(user);

//        elasticsearchTemplate.queryForPage()


        return elasticsearchTemplate.queryForPage(query,User.class);
    }

    @Override
    public AggregatedPage<User> nestAgg(AggEntity aggEntity) {
        return null;
    }
    /**
     * private ElasticsearchTemplate elasticsearchTemplate;

     SearchQuery searchQuery = new NativeSearchQueryBuilder()
     .withQuery(matchAllQuery())
     .withFilter(boolFilter().must(termFilter("id", documentId)))
     .build();

     Page<SampleEntity> sampleEntities =
     elasticsearchTemplate.queryForPage(searchQuery,SampleEntity.class);
     ------------------------------------------------------------------------
     SearchQuery searchQuery = new NativeSearchQueryBuilder()
     .withQuery(matchAllQuery())
     .withIndices("test-index")
     .withTypes("test-type")
     .withPageable(new PageRequest(0,1))
     .build();
     String scrollId = elasticsearchTemplate.scan(searchQuery,1000,false);
     List<SampleEntity> sampleEntities = new ArrayList<SampleEntity>();
     boolean hasRecords = true;
     while (hasRecords){
     Page<SampleEntity> page = elasticsearchTemplate.scroll(scrollId, 5000L , new ResultsMapper<SampleEntity>()
     {
     @Override
     public Page<SampleEntity> mapResults(SearchResponse response) {
     List<SampleEntity> chunk = new ArrayList<SampleEntity>();
     for(SearchHit searchHit : response.getHits()){
     if(response.getHits().getHits().length <= 0) {
     return null;
     }
     SampleEntity user = new SampleEntity();
     user.setId(searchHit.getId());
     user.setMessage((String)searchHit.getSource().get("message"));
     chunk.add(user);
     }
     return new PageImpl<SampleEntity>(chunk);
     }
     });
     if(page != null) {
     sampleEntities.addAll(page.getContent());
     hasRecords = page.hasNextPage();
     }
     else{
     hasRecords = false;
     }
     }
     }
     */
}
