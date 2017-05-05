package com.springboot.elastic.user.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springboot.elastic.common.AggEntity;
import com.springboot.elastic.common.RangeEntity;
import com.springboot.elastic.user.dao.UserRepository;
import com.springboot.elastic.user.document.User;
import com.springboot.elastic.user.service.UserService;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.facet.FacetResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/3.
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void save(List<User> list) {
        userRepository.save(list);
    }

    @Override
    public List<User> findAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public Page<User> findAll(int page, int size) {
        return null;
    }

    @Override
    public boolean exists(String id) {
        return userRepository.exists(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void delete(List<User> list) {
        userRepository.delete(list);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User findOne(String id) {
        return null;
    }

    @Override
    public Map<String,Long> aggRangeByAge() {
        /**
         * 需求
         * 按年龄进行分段统计数据，10以下，10-20，20-30,30-40,40-50,50-60,60以上
         */
        AggEntity aggEntity = new AggEntity();
        aggEntity.setAggName("ageRange");
        aggEntity.setField("age");
        RangeEntity rangeEntity1 = new RangeEntity("10岁以下",null,10);
        RangeEntity rangeEntity2 = new RangeEntity("10岁-20岁",10,20);
        RangeEntity rangeEntity3 = new RangeEntity("20岁-30岁",20,30);
        RangeEntity rangeEntity4 = new RangeEntity("30岁-40岁",30,40);
        RangeEntity rangeEntity5 = new RangeEntity("40岁-50岁",40,50);
        RangeEntity rangeEntity6 = new RangeEntity("50岁-60岁",50,60);
        RangeEntity rangeEntity7 = new RangeEntity("60岁以上",60,null);
        aggEntity.setRangeEntities(Lists.newArrayList(rangeEntity1,rangeEntity2,
                rangeEntity3,rangeEntity4,rangeEntity5,rangeEntity6,rangeEntity7));

        AggregatedPage<User> userAggregatedPage = userRepository.aggRangeByAge(aggEntity);

        System.out.println("每页查询数量:"+userAggregatedPage.getSize());
        System.out.println("当前页码:"+userAggregatedPage.getNumber());
        System.out.println("当前页查出多少："+userAggregatedPage.getNumberOfElements());
        System.out.println("页数："+userAggregatedPage.getTotalPages());
        System.out.println("总数："+userAggregatedPage.getTotalElements());


        List<User> users = userAggregatedPage.getContent();
        users.forEach(System.out::println);

        Aggregations aggregations = userAggregatedPage.getAggregations();
        Range range = aggregations.get("ageRange");
        List<? extends Range.Bucket> buckets = range.getBuckets();

        Map<String,Long> map = Maps.newHashMap();
        buckets.forEach(bucket-> map.put(bucket.getKeyAsString(),bucket.getDocCount()));
        return map;
    }
}
