package com.study.sharding;

import com.study.sharding.entity.TOrder;
import com.study.sharding.mapper.OrderMapper;
import com.study.sharding.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyShardingApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void contextLoads() {
        for (int i=0;i<100000;i++) {
            rabbitTemplate.convertAndSend("GPS_QUERY", "test "+i);
        }

        //往tenmao.blog.channel发送消息"hello world"
//        for (int i=0;i<100;i++) {
//            redisTemplate.opsForList().leftPush("query","test "+i);
//            //stringRedisTemplate.convertAndSend("tenmao.blog.channel", "hello world "+i);
//        }

        //System.out.println(redisTemplate.opsForList().rightPop("query"));
//        TOrder order = new TOrder();
//		List<TOrder> list = orderMapper.selectById(order);
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
//		System.out.println(orderService.findAll());

        //条件查询 地址 https://blog.csdn.net/weixin_39214304/article/details/84791953

        //如果实体类中包含有基本数据类型的属性，那么在使用repository.find(Example)时，
        // 需要把这些属性忽略掉，因为基本数据类型在新建对象时会有默认值，这时如果你按照
        // 别的属性查找数据时，这些属性也会附带到条件里。比如上文代码中有一个根据用户名
        // 查找用户的方法，此时你期望的是在查询过程中只有用户名起作用，那么这时候就要加
        // 上下面的代码。如果不加，则条件中会多出age=0&createTime=0，查出的结果有误。
        //ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("age","createTime");
        //Example<User> example = Example.of(user, matcher);

//        order.setOrderId(8992584491376641l);
//        Example<TOrder> example = Example.of(order);
//
//        //排序
//        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
//        List<TOrder> all = orderService.findAll(sort);

//        System.out.println("result=== " + all);

    }

}
