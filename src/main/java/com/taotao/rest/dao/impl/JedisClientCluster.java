package com.taotao.rest.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import com.taotao.rest.dao.JedisClient;

public class JedisClientCluster implements JedisClient {

	@Autowired
	private JedisCluster jedisCluster; 
	@Override
	public String get(String key) {
		String string = jedisCluster.get(key);
		return string;
	}

	@Override
	public String set(String key, String value) {
		String string = jedisCluster.set(key,value);
		return string;
	}

	@Override
	public String hget(String hkey, String key) {
		String string = jedisCluster.hget(hkey, key);
		return string;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Long result = jedisCluster.hset(hkey, key, value);
		return result;
	}

	@Override
	public long incr(String key) {
		Long result = jedisCluster.incr(key);
		return result;
	}

	@Override
	public long expire(String key, int second) {
		Long result = jedisCluster.expire(key, second);
		return result;
	}

	@Override
	public long ttl(String key) {
		Long result = jedisCluster.ttl(key);
		return result;
	}

	@Override
	public long del(String key) {
		Long result = jedisCluster.del(key);
		return result;
	}

	@Override
	public long hdel(String hkey, String key) {
		Long result = jedisCluster.hdel(hkey, key);
		return result;
	}


}
