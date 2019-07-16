package com.study.sharding;

import com.mongodb.WriteResult;
import com.mongodb.client.result.DeleteResult;
import com.study.sharding.entity.TOrder;
import com.study.sharding.service.OrderService;
import com.study.sharding.util.Sequence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyShardingApplicationTests {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	OrderService orderService;

	@Test
	public void contextLoads() {
		TOrder order=new TOrder();
//		order.setOrderId(Sequence.getSequenceId());
//		order.setUserId(Sequence.getSequenceId());
//		order.setCreateTime(LocalDateTime.now());
//		order.setStatus(1);
//		mongoTemplate.insert(order);

		//使用fastJson将对象转化为Json
//		String json = JSON.toJSONString(order);
//		mongoTemplate.insert(json, "class");

//		Query query = new Query(Criteria.where("orderId").is("8992505514598402"));
//		DeleteResult result = mongoTemplate.remove(query, "class");
//		System.out.println(result);

//		List list=mongoTemplate.findAll(TOrder.class);
//		System.out.println(list);

//		orderService.insert(order);
		System.out.println(orderService.findAll());


	}

}
