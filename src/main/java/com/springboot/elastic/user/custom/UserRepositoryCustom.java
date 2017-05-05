package com.springboot.elastic.user.custom;

import com.springboot.elastic.common.AggEntity;
import com.springboot.elastic.user.document.User;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

/**
 * Created by Administrator on 2017/5/3.
 */
public interface UserRepositoryCustom {

    AggregatedPage<User> aggRangeByAge(AggEntity aggEntity);
    AggregatedPage<User> nestAgg(AggEntity aggEntity);
}
