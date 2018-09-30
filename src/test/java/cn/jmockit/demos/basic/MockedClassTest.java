package cn.jmockit.demos.basic;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import mockit.Mocked;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//@Mocked注解用途
public class MockedClassTest {
	// 加上了JMockit的API @Mocked, JMockit会帮我们实例化这个对象，不用担心它为null
	@Mocked
	private Locale locale;

	// 当@Mocked作用于class
	@Test
	public void testMockedClass() {
		// 静态方法不起作用了,返回了null
		Assert.assertNull(Locale.getDefault());
		// 非静态方法（返回类型为String）也不起作用了，返回了null
		Assert.assertNull(locale.getCountry());
		// 自已new一个，也同样如此，方法都被mock了
		Locale chinaLocale = new Locale("zh", "CN");
		Assert.assertNull(chinaLocale.getCountry());
	}
 
}
