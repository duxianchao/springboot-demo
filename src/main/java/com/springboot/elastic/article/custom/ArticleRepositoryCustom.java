package com.springboot.elastic.article.custom;

import com.springboot.elastic.article.document.Article;
import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 * ArticleRepository+Custom
 */
public interface ArticleRepositoryCustom {

    List<Article> findByAuthor(String author);
}
