package com.springboot.elastic.user.dao;

import com.springboot.elastic.user.custom.UserRepositoryCustom;
import com.springboot.elastic.user.document.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by Administrator on 2017/5/3.
 */
public interface UserRepository extends ElasticsearchRepository<User,String>,UserRepositoryCustom {
}
