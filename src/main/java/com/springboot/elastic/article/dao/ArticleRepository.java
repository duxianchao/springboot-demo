package com.springboot.elastic.article.dao;

import com.springboot.elastic.article.custom.ArticleRepositoryCustom;
import com.springboot.elastic.article.document.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 文章Repository
 */
public interface ArticleRepository extends ElasticsearchRepository<Article,String>,ArticleRepositoryCustom {

    List<Article> findByAuthorAndArticleName(String author, String articleName);

}
