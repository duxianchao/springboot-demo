package com.springboot.elastic.user.service;

import com.springboot.elastic.common.ElasticService;
import com.springboot.elastic.user.document.User;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/3.
 */
public interface UserService extends ElasticService<User>{

    Map<String,Long> aggRangeByAge();
}
