package com.lpy.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Objects;

public class RedisUtil {

    GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<>();

    JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);

    private volatile static RedisUtil redisUtil;

    private Jedis jedis;

    private RedisUtil() {
        init();
    }

    public static RedisUtil getInstance() {
        if (redisUtil == null) {
            synchronized (RedisUtil.class) {
                if (redisUtil == null) {
                    redisUtil = new RedisUtil();
                }
            }
        }
        return redisUtil;
    }

    private void init() {
        this.jedis = jedisPool.getResource();
    }

    public String set(String key, String value) {
        jedis.set(key, value);
        jedis.close();
        return value;
    }

    public int set(String key, int value) {
        jedis.set(key, String.valueOf(value));
        jedis.close();
        return value;
    }

    public int set(int key, int value) {
        jedis.set(String.valueOf(key), String.valueOf(value));
        jedis.close();
        return value;
    }

    public String set(String key, String value, long exTime) {
        jedis.setex(key, exTime, value);
        jedis.close();
        return value;
    }

    public int set(int key, int value, long exTime) {
        jedis.setex(String.valueOf(key), exTime, String.valueOf(value));
        jedis.close();
        return value;
    }

    public String set(int key, String value, long exTime) {
        jedis.setex(String.valueOf(key), exTime, value);
        jedis.close();
        return value;
    }

    public String get(String key) {
        String value=jedis.get(key);
        jedis.close();
        return value;
    }

    public String get(int key) {
        String value=jedis.get(String.valueOf(key));
        jedis.close();
        return value;
    }

    public long del(String key) {
        long res=jedis.del(key);
        jedis.close();
        return res;
    }

    public long del(int key) {
        long res=jedis.del(String.valueOf(key));
        jedis.close();
        return res;
    }

    public boolean setIfAbsent(String key, String value) {
        if (Objects.nonNull(get(key))) {
            return false;
        }
        set(key, value);
        return true;
    }

    public boolean setIfAbsent(int key, int value) {
        if (Objects.nonNull(get(key))) {
            return false;
        }
        set(key, value);
        return true;
    }

    public boolean setIfAbsent(String key, String value, long exTime) {
        if (Objects.nonNull(jedis.get(key))) {
            jedis.close();
            return false;
        }
        jedis.setex(key, exTime, value);
        jedis.close();
        return true;
    }

    public boolean setIfAbsent(int key, int value, long exTime) {
        if (Objects.nonNull(get(key))) {
            jedis.close();
            return false;
        }
        set(key, value, exTime);
        jedis.close();
        return true;
    }

    public boolean isNotNull(String key) {
        return Objects.nonNull(get(key));
    }
    public boolean isNotNull(int key) {
        return Objects.nonNull(get(key));
    }
    public boolean isNotNull(long key) {
        return Objects.nonNull(get(String.valueOf(key)));
    }
}
