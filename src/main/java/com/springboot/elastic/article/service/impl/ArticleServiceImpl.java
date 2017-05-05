package com.springboot.elastic.article.service.impl;

import com.google.common.collect.Lists;
import com.springboot.elastic.article.dao.ArticleRepository;
import com.springboot.elastic.article.document.Article;
import com.springboot.elastic.article.service.ArticleService;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void save(List<Article> articles) {
        articleRepository.save(articles);
    }

    @Override
    public List<Article> findAll() {
        Iterable<Article> all = articleRepository.findAll();
        return Lists.newArrayList(all);
    }


    @Override
    public Page<Article> findAll(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return articleRepository.findAll(pageable);
    }

    @Override
    public boolean exists(String id) {
        return articleRepository.exists(id);
    }

    @Override
    public long count() {
        return articleRepository.count();
    }

    @Override
    public void delete(String id) {
        articleRepository.delete(id);
    }

    @Override
    public void delete(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public void delete(List<Article> articles) {
        articleRepository.delete(articles);
    }

    @Override
    public void deleteAll() {
        articleRepository.deleteAll();
    }

    @Override
    public Article findOne(String id) {
        return articleRepository.findOne(id);
    }


    @Override
    public List<Article> findByAuthorAndArticleName(String author, String articleName) {
        List<Article> list =  articleRepository.findByAuthorAndArticleName(author,articleName);
        return list;
    }

    @Override
    public List<Article> findByAuthor(String author) {
        return articleRepository.findByAuthor(author);
    }


    private ElasticsearchTemplate elasticsearchTemplate;
    void test(){
        /*
        private ElasticsearchTemplate elasticsearchTemplate;
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(matchAllQuery())
            .withFilter(boolFilter().must(termFilter("id", documentId)))
            .build();
        Page<SampleEntity> sampleEntities =
            elasticsearchTemplate.queryForPage(searchQuery,SampleEntity.class);
         */
        QueryBuilder queryBuilder = null;
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        /*
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

}
