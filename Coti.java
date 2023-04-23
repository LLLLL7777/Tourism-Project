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
	 * ָ������ʧЧʱ��
	 * @param key ��
	 * @param time ʱ��(��)
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
	 * ����key ��ȡ����ʱ��
	 * @param key �� ����Ϊnull
	 * @return ʱ��(��) ����0����Ϊ������Ч
	 */
	public long getExpire(String key){
		return redisTemplate.getExpire(key,TimeUnit.SECONDS);
	}

	/**
	 * �ж�key�Ƿ����
	 * @param key ��
	 * @return true ���� false������
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
	 * ɾ������
	 * @param key ���Դ�һ��ֵ ����
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
	 * ��ͨ�����ȡ
	 * @param key ��
	 * @return ֵ
	 */
	public Object get(String key, int indexdb){
		redisTemplate.indexdb.set(indexdb);
		return key==null?null:redisTemplate.opsForValue().get(key);
	}

	/**
	 * ��ͨ�������
	 * @param key ��
	 * @param value ֵ
	 * @return true�ɹ� falseʧ��
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



