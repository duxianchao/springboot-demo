package com.springboot.elastic.common;

import org.springframework.data.domain.Page;
import java.util.List;

/**
 * <p>定义了一些公共的查询方法供service实现</p>
 * @author czt
 * @date 2017-05-03
 * @param <T>
 */
public interface ElasticService<T> {
    void save(T t);
    void save(List<T> list);
    List<T> findAll();
    Page<T> findAll(int page, int size);
    boolean exists(String id);
    long count();
    void delete(String id);
    void delete(T t);
    void delete(List<T> list);
    void deleteAll();
    T findOne(String id);
}
