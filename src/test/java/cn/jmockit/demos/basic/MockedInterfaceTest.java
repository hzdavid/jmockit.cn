package cn.jmockit.demos.basic;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;

import mockit.Mocked;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//@Mocked注解用途
public class MockedInterfaceTest {

	// 加上了JMockit的API @Mocked, JMockit会帮我们实例化这个对象，尽管这个对象的类型是一个接口，不用担心它为null
	@Mocked
	private HttpSession session;

	// 当@Mocked作用于interface
	@Test
	public void testMockedInterface() {
		// （返回类型为String）也不起作用了，返回了null
		Assert.assertNull(session.getId());
		// （返回类型为原始类型）也不起作用了，返回了0
		Assert.assertEquals(0L, session.getCreationTime());
		// (返回类型为原非始类型，非String，返回的对象不为空，这个对象也是JMockit帮你实例化的，同样这个实例化的对象也是一个Mocked对象)
		Assert.assertNotNull(session.getServletContext());
		// Mocked对象返回的Mocked对象，（返回类型为String）的方法也不起作用了，返回了null
		Assert.assertNull(session.getServletContext().getContextPath());
	}
}
