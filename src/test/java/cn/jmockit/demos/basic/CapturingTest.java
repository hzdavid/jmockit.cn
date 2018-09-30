package cn.jmockit.demos.basic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Assert;
import org.junit.Test;

import cn.jmockit.demos.IPrivilege;
import mockit.Capturing;
import mockit.Expectations;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//@Capturing注解用途
public class CapturingTest {
	// 测试用户ID
	private long testUserId = 123456l;
	// 权限检验类，可能是人工写的
	IPrivilege privilegeManager1 = new IPrivilege() {
		@Override
		public boolean isAllow(long userId) {
			if (userId == testUserId) {
				return false;
			}
			return true;
		}
	};
	// 权限检验类，可能是JDK动态代理生成。我们通常AOP来做权限校验。
	private IPrivilege privilegeManager2 = (IPrivilege) Proxy.newProxyInstance(IPrivilege.class.getClassLoader(),
			new Class[] { IPrivilege.class }, new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) {
					if ((long) args[0] == testUserId) {
						return false;
					}
					return true;
				}
			});

	// 有Capturing情形
	@Test
	public void testCapturing(@Capturing IPrivilege privilegeManager) {
		// 加上了JMockit的API @Capturing,
		// JMockit会帮我们实例化这个对象，它除了具有@Mocked的特点，还能影响它的子类/实现类
		new Expectations() {
			{
				// 对IPrivilege的所有实现类录制，假设测试用户有权限
				privilegeManager.isAllow(testUserId);
				result = true;
			}
		};
		// 不管权限校验的实现类是哪个，这个测试用户都有权限
		Assert.assertTrue(privilegeManager1.isAllow(testUserId));
		Assert.assertTrue(privilegeManager2.isAllow(testUserId));
	}
	// 没有Capturing情形
	@Test
	public void testWithoutCaputring() {
		// 不管权限校验的实现类是哪个，这个测试用户没有权限
		Assert.assertTrue(!privilegeManager1.isAllow(testUserId));
		Assert.assertTrue(!privilegeManager2.isAllow(testUserId));
	}
}
