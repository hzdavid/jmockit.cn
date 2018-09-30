package cn.jmockit.demos;


import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import cn.jmockit.demos.HelloJMockit;
import mockit.Expectations;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//HelloJMockit类的测试类
public class HelloJMockitTest {
	/**
	 * 测试场景：当前是在中国
	 */
	@Test
	public void testSayHelloAtChina() {
		// 假设当前位置是在中国
		new Expectations(Locale.class) {
			{
				Locale.getDefault();
				result = Locale.CHINA;
			}
		};
		// 断言说中文
		Assert.assertTrue("你好，JMockit!".equals((new HelloJMockit()).sayHello()));
	}

	/**
	 * 测试场景：当前是在美国
	 */
	@Test
	public void testSayHelloAtUS() {
		// 假设当前位置是在美国
		new Expectations(Locale.class) {
			{
				Locale.getDefault();
				result = Locale.US;
			}
		};
		// 断言说英文
		Assert.assertTrue("Hello，JMockit!".equals((new HelloJMockit()).sayHello()));
	}
}
