package com.feelcode.tourism.base.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Lazy
@Component
public class RedisUtil{

	@Autowired
	private RedisTemplate redisTemplate;

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	//=============================common============================
	/**
	 * 指定缓存失效时间
	 * @param key 键
	 * @param time 时间(秒)
	 * @return
	 */
	public boolean expire(String key,int indexdb,long time){
		try {
			if(time>0){
				redisTemplate.indexdb.set(indexdb);
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据key 获取过期时间
	 * @param key 键 不能为null
	 * @return 时间(秒) 返回0代表为永久有效
	 */
	public long getExpire(String key){
		return redisTemplate.getExpire(key,TimeUnit.SECONDS);
	}

	/**
	 * 判断key是否存在
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public boolean hasKey(String key){
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除缓存
	 * @param key 可以传一个值 或多个
	 */
	@SuppressWarnings("unchecked")
	public void del(int indexdb, String ... key){
		redisTemplate.indexdb.set(indexdb);
		if(key!=null&&key.length>0){
			if(key.length==1){
				redisTemplate.delete(key[0]);
			}else{
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	//============================String=============================
	/**
	 * 普通缓存获取
	 * @param key 键
	 * @return 值
	 */
	public Object get(String key, int indexdb){
		redisTemplate.indexdb.set(indexdb);
		return key==null?null:redisTemplate.opsForValue().get(key);
	}

	/**
	 * 普通缓存放入
	 * @param key 键
	 * @param value 值
	 * @return true成功 false失败
	 */
	public boolean set(String key,Object value,int indexdb) {
		try {
			redisTemplate.indexdb.set(indexdb);
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}



