/**
	 * 普通缓存放入并设置时间
	 * @param key 键
	 * @param value 值
	 * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return true成功 false 失败
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
	 * 递增
	 * @param key 键
	 * @param by 要增加几(大于0)
	 * @return
	 */
	public long incr(String key, long delta){
		if(delta<0){
			throw new RuntimeException("递增因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}

	/**
	 * 递减
	 * @param key 键
	 * @param by 要减少几(小于0)
	 * @return
	 */
	public long decr(String key, long delta){
		if(delta<0){
			throw new RuntimeException("递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}

	//================================Map=================================
	/**
	 * HashGet
	 * @param key 键 不能为null
	 * @param item 项 不能为null
	 * @return 值
	 */
	public Object hget(String key,String item){
		return redisTemplate.opsForHash().get(key, item);
	}

	/**
	 * 获取hashKey对应的所有键值
	 * @param key 键
	 * @return 对应的多个键值
	 */
	public Map<Object,Object> hmget(String key){
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * HashSet
	 * @param key 键
	 * @param map 对应多个键值
	 * @return true 成功 false 失败
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
	 * HashSet 并设置时间
	 * @param key 键
	 * @param map 对应多个键值
	 * @param time 时间(秒)
	 * @return true成功 false失败
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
	 * 向一张hash表中放入数据,如果不存在将创建
	 * @param key 键
	 * @param item 项
	 * @param value 值
	 * @return true 成功 false失败
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
	 * 向一张hash表中放入数据,如果不存在将创建
	 * @param key 键
	 * @param item 项
	 * @param value 值
	 * @param time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
	 * @return true 成功 false失败
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
