/**
	 * ��ͨ������벢����ʱ��
	 * @param key ��
	 * @param value ֵ
	 * @param time ʱ��(��) timeҪ����0 ���timeС�ڵ���0 ������������
	 * @return true�ɹ� false ʧ��
	 */
	public boolean set(String key,Object value,int indexdb,long time){
		try {
			redisTemplate.indexdb.set(indexdb);
			if(time>0){
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			}else{
				redisTemplate.opsForValue().set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ����
	 * @param key ��
	 * @param by Ҫ���Ӽ�(����0)
	 * @return
	 */
	public long incr(String key, long delta){
		if(delta<0){
			throw new RuntimeException("�������ӱ������0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * �ݼ�
	 * @param key ��
	 * @param by Ҫ���ټ�(С��0)
	 * @return
	 */
	public long decr(String key, long delta){
		if(delta<0){
			throw new RuntimeException("�ݼ����ӱ������0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	//================================Map=================================
	/**
	 * HashGet
	 * @param key �� ����Ϊnull
	 * @param item �� ����Ϊnull
	 * @return ֵ
	 */
	public Object hget(String key,String item){
		return redisTemplate.opsForHash().get(key, item);
	}

	/**
	 * ��ȡhashKey��Ӧ�����м�ֵ
	 * @param key ��
	 * @return ��Ӧ�Ķ����ֵ
	 */
	public Map<Object,Object> hmget(String key){
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * HashSet
	 * @param key ��
	 * @param map ��Ӧ�����ֵ
	 * @return true �ɹ� false ʧ��
	 */
	public boolean hmset(String key, Map<String,Object> map){
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * HashSet ������ʱ��
	 * @param key ��
	 * @param map ��Ӧ�����ֵ
	 * @param time ʱ��(��)
	 * @return true�ɹ� falseʧ��
	 */
	public boolean hmset(String key, Map<String,Object> map, int indexdb,long time){
		try {
			redisTemplate.indexdb.set(indexdb);
			redisTemplate.opsForHash().putAll(key, map);
			if(time>0){
				expire(key, indexdb, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ��һ��hash���з�������,��������ڽ�����
	 * @param key ��
	 * @param item ��
	 * @param value ֵ
	 * @return true �ɹ� falseʧ��
	 */
	public boolean hset(String key,String item,Object value) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ��һ��hash���з�������,��������ڽ�����
	 * @param key ��
	 * @param item ��
	 * @param value ֵ
	 * @param time ʱ��(��)  ע��:����Ѵ��ڵ�hash����ʱ��,���ｫ���滻ԭ�е�ʱ��
	 * @return true �ɹ� falseʧ��
	 */
	public boolean hset(String key,String item,Object value,int indexdb, long time) {
		try {
			redisTemplate.indexdb.set(indexdb);
			redisTemplate.opsForHash().put(key, item, value);
			if(time>0){
				expire(key,indexdb, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}