/**
	 * ��ȡlist����ĳ���
	 * @param key ��
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
	 * ͨ������ ��ȡlist�е�ֵ
	 * @param key ��
	 * @param index ����  index>=0ʱ�� 0 ��ͷ��1 �ڶ���Ԫ�أ��������ƣ�index<0ʱ��-1����β��-2�����ڶ���Ԫ�أ���������
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
	 * ��list���뻺��
	 * @param key ��
	 * @param value ֵ
	 * @param time ʱ��(��)
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
	 * ��list���뻺��
	 * @param key ��
	 * @param value ֵ
	 * @param time ʱ��(��)
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
	 * ��list���뻺��
	 * @param key ��
	 * @param value ֵ
	 * @param time ʱ��(��)
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
	 * ��list���뻺��
	 * @param key ��
	 * @param value ֵ
	 * @param time ʱ��(��)
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
	 * ���������޸�list�е�ĳ������
	 * @param key ��
	 * @param index ����
	 * @param value ֵ
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
	 * �Ƴ�N��ֵΪvalue
	 * @param key ��
	 * @param count �Ƴ����ٸ�
	 * @param value ֵ
	 * @return �Ƴ��ĸ���
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