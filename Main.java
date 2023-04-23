/**
	 * 获取list缓存的长度
	 * @param key 键
	 * @return
	 */
	public long lGetListSize(String key){
		try {
			return redisTemplate.opsForList().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 通过索引 获取list中的值
	 * @param key 键
	 * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
	 * @return
	 */
	public Object lGetIndex(String key,long index){
		try {
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @param time 时间(秒)
	 * @return
	 */
	public boolean lSet(String key, Object value) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @param time 时间(秒)
	 * @return
	 */
	public boolean lSet(String key, Object value,int indexdb, long time) {
		try {
			redisTemplate.indexdb.set(indexdb);
			redisTemplate.opsForList().rightPush(key, value);
			if (time > 0) expire(key, indexdb, time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @param time 时间(秒)
	 * @return
	 */
	public boolean lSet(String key, List<Object> value) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @param time 时间(秒)
	 * @return
	 */
	public boolean lSet(String key, List<Object> value, int indexdb, long time) {
		try {
			redisTemplate.indexdb.set(indexdb);
			redisTemplate.opsForList().rightPushAll(key, value);
			if (time > 0) expire(key, indexdb, time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据索引修改list中的某条数据
	 * @param key 键
	 * @param index 索引
	 * @param value 值
	 * @return
	 */
	public boolean lUpdateIndex(String key, long index,Object value) {
		try {
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 移除N个值为value
	 * @param key 键
	 * @param count 移除多少个
	 * @param value 值
	 * @return 移除的个数
	 */
	public long lRemove(String key,long count,Object value) {
		try {
			Long remove = redisTemplate.opsForList().remove(key, count, value);
			return remove;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void main(String[] args) {
		/*JedisPool jedisPool = new JedisPool(null,"localhost",6379,100,"123456");
		Jedis jedis = jedisPool.getResource();
		//r.get("", RedisConstants.datebase4);
		jedis.select(RedisConstants.datebase4);
		Set<String> str =  jedis.keys("*");
		for (String string : str) {
			System.out.println(string);
		}*/
	}
}