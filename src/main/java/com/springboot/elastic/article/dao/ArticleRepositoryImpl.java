package com.springboot.elastic.article.dao;

import com.google.common.collect.Lists;
import com.springboot.elastic.article.custom.ArticleRepositoryCustom;
import com.springboot.elastic.article.document.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 * ArticleRepository+Impl
 */
public class ArticleRepositoryImpl implements ArticleRepositoryCustom {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Article> findByAuthor(String author) {
        /*SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withFilter(boolFilter().must(termFilter("id", documentId)))
                .build();

        Page<SampleEntity> sampleEntities =
                elasticsearchTemplate.queryForPage(searchQuery,SampleEntity.class);*/





        System.out.println("author:"+author);
        System.out.println(elasticsearchTemplate);
        ArrayList<Article> list = Lists.newArrayList();
        return list;
    }
}
