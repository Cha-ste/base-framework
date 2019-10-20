package com.ocean.baseframework;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ocean.baseframework.thread.ThreadDemo;
import com.ocean.entity.UserInfo;
import com.ocean.mapper.back.UserInfoMapper;
import com.ocean.utils.JedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseFrameworkApplicationTests {
	@Autowired
	UserInfoMapper userInfoMapper;

	@Test
	public void contextLoads() {
		PageHelper.startPage(1, 1);
		new PageInfo<>();
	}

	@Test
	public void test() {
		System.out.println(UUID.randomUUID());
	}

	@Test
	public void testJedis() {
		System.out.println(JedisUtils.getJedis().ping());
	}

	@Test
	public void testMybatis () {
		
	}

	@Test
	public void testMultipartDatasource() {
		UserInfo userInfo = userInfoMapper.get("1");
		System.out.println(userInfo.toString());
	}



}
