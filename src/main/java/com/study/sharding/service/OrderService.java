package com.study.sharding.service;

import com.study.sharding.entity.TOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

/**
 * @Author pukaiquan
 * @Date 2019-07-16 16:23
 */
@Service
public interface OrderService extends MongoRepository<TOrder, Long> {


}
