/**
	 * ɾ��hash���е�ֵ
	 * @param key �� ����Ϊnull
	 * @param item �� ����ʹ��� ����Ϊnull
	 */
	public void hdel(String key, Object... item){
		redisTemplate.opsForHash().delete(key,item);
	}

	/**
	 * �ж�hash�����Ƿ��и����ֵ
	 * @param key �� ����Ϊnull
	 * @param item �� ����Ϊnull
	 * @return true ���� false������
	 */
	public boolean hHasKey(String key, String item){
		return redisTemplate.opsForHash().hasKey(key, item);
	}

	/**
	 * hash���� ���������,�ͻᴴ��һ�� �����������ֵ����
	 * @param key ��
	 * @param item ��
	 * @param by Ҫ���Ӽ�(����0)
	 * @return
	 */
	public double hincr(String key, String item,double by){
		return redisTemplate.opsForHash().increment(key, item, by);
	}

	/**
	 * hash�ݼ�
	 * @param key ��
	 * @param item ��
	 * @param by Ҫ���ټ�(С��0)
	 * @return
	 */
	public double hdecr(String key, String item,double by){
		return redisTemplate.opsForHash().increment(key, item,-by);
	}

	//============================set=============================
	/**
	 * ����key��ȡSet�е�����ֵ
	 * @param key ��
	 * @return
	 */
	public Set<Object> sGet(String key){
		try {
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ����value��һ��set�в�ѯ,�Ƿ����
	 * @param key ��
	 * @param value ֵ
	 * @return true ���� false������
	 */
	public boolean sHasKey(String key,Object value){
		try {
			return redisTemplate.opsForSet().isMember(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * �����ݷ���set����
	 * @param key ��
	 * @param values ֵ �����Ƕ��
	 * @return �ɹ�����
	 */
	public long sSet(String key, Object...values) {
		try {
			return redisTemplate.opsForSet().add(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * ��set���ݷ��뻺��
	 * @param key ��
	 * @param time ʱ��(��)
	 * @param values ֵ �����Ƕ��
	 * @return �ɹ�����
	 */
	public long sSetAndTime(String key,int indexdb,long time,Object...values) {
		try {
			redisTemplate.indexdb.set(indexdb);
			Long count = redisTemplate.opsForSet().add(key, values);
			if(time>0) expire(key, indexdb, time);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * ��ȡset����ĳ���
	 * @param key ��
	 * @return
	 */
	public long sGetSetSize(String key){
		try {
			return redisTemplate.opsForSet().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * �Ƴ�ֵΪvalue��
	 * @param key ��
	 * @param values ֵ �����Ƕ��
	 * @return �Ƴ��ĸ���
	 */
	public long setRemove(String key, Object ...values) {
		try {
			Long count = redisTemplate.opsForSet().remove(key, values);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	//===============================list=================================

	/**
	 * ��ȡlist���������
	 * @param key ��
	 * @param start ��ʼ
	 * @param end ����  0 �� -1��������ֵ
	 * @return
	 */
	public List<Object> lGet(String key,long start, long end){
		try {
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}