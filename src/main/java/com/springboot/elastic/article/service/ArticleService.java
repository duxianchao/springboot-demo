package com.springboot.elastic.article.service;

import com.springboot.elastic.common.ElasticService;
import com.springboot.elastic.article.document.Article;

import java.util.List;

public interface ArticleService extends ElasticService<Article> {

    List<Article> findByAuthorAndArticleName(String author,String articleName);

    List<Article> findByAuthor(String author);
}
