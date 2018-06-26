package org.com.cay.spring.boot.repository;

import org.com.cay.spring.boot.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Cay on 2018/5/9.
 */
@Repository
public class IUserRepository {

	private ConcurrentMap<Integer, User> users = new ConcurrentHashMap<>();

	private static final AtomicInteger idGenerator = new AtomicInteger();

	/**
	 * 如果保存成功，返回<code>true</code>
	 * 否则，返回<code>false</code>
	 * @param user
	 * @return
	 */
	public boolean save(User user){
		//id从1开始
		Integer id = idGenerator.incrementAndGet();
		user.setId(id);
		return users.put(id, user) == null;
	}

	public Collection<User> all(){
		return users.values();
	}
}
