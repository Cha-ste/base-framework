package com.ocean.baseframework;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseFrameworkApplicationTests {

	@Test
	public void contextLoads() {
		PageHelper.startPage(1, 1);
		new PageInfo<>();
	}

	@Test
	public void test() {
		System.out.println(UUID.randomUUID());
	}

}
