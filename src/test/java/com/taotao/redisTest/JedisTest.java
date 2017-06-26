package com.taotao.redisTest;

import java.util.HashSet;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	@Test
	public void testJedisSingle(){
		Jedis jedis =new Jedis("192.168.6.136", 6379);
		jedis.set("a", "10");
		String redisValue = jedis.get("a");
		System.out.print(redisValue);
		jedis.close();
	}
	
	@Test
	public void testPoolJedis(){
		JedisPool jedisPool =new JedisPool("192.168.6.136", 6379);
		Jedis jedis = jedisPool.getResource();
		jedis.set("a2", "11");
		String outString = jedis.get("a2");
		System.out.println(outString);
		jedisPool.close();
		jedis.close();
	}
	@Test
	public void testJedisCluster(){
		HashSet<HostAndPort> nodes =new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.6.136", 7001));
		nodes.add(new HostAndPort("192.168.6.136", 7002));
		nodes.add(new HostAndPort("192.168.6.136", 7003));
		nodes.add(new HostAndPort("192.168.6.136", 7004));
		nodes.add(new HostAndPort("192.168.6.136", 7005));
		nodes.add(new HostAndPort("192.168.6.136", 7006));
		JedisCluster jedisCluster =new JedisCluster(nodes);
		jedisCluster.set("c", "100");
		String outString = jedisCluster.get("c");
		System.out.println(outString);
		jedisCluster.close();
	}
	@Test
	public void testSpringJedisSingle(){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool jedisPool = (JedisPool) context.getBean("jedisClient");
		Jedis jedis = jedisPool.getResource();
		jedis.set("a3", "1010");
		String outString = jedis.get("a3");
		System.out.println(outString);
		jedisPool.close();
		jedis.close();
	}
	@Test
	public void testSpringJedisCluster(){
		ApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster = (JedisCluster) context.getBean("redisClient");
		jedisCluster.set("a3", "1234");
		String outString = jedisCluster.get("a3");
		System.out.println(outString);
		jedisCluster.close();
	}
}
