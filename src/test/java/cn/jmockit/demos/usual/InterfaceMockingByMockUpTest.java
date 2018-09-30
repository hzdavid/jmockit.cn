package cn.jmockit.demos.usual;

import org.junit.Assert;
import org.junit.Test;

import cn.jmockit.demos.AnOrdinaryInterface;
import mockit.Mock;
import mockit.MockUp;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//用MockUp来mock接口
public class InterfaceMockingByMockUpTest {

	@Test
	public void testInterfaceMockingByMockUp() {
		// 手工通过MockUp创建这个接口的实例
		AnOrdinaryInterface anOrdinaryInterface = new MockUp<AnOrdinaryInterface>(AnOrdinaryInterface.class) {
			// 对方法Mock
			@Mock
			public int method1() {
				return 10;
			}

			@Mock
			public int method2() {
				return 20;
			}
		}.getMockInstance();

		Assert.assertEquals(10, anOrdinaryInterface.method1());
		Assert.assertEquals(20, anOrdinaryInterface.method2());
	}
}
