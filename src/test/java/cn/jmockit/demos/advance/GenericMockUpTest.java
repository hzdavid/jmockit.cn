package cn.jmockit.demos.advance;

import org.junit.Assert;
import org.junit.Test;

import cn.jmockit.demos.AnOrdinaryInterface;
import mockit.Capturing;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//Mock泛型
public class GenericMockUpTest {
	@Test
	public <T extends AnOrdinaryInterface> void testMockUp() {
		// 通过传给MockUp一个泛型类型变量，MockUp可以对这个类型变量的上限进行Mock，以后所有这个上限的方法调用，就会走Mock后的逻辑
		new MockUp<T>() {
			@Mock
			public int method1() {
				return 10;
			}

			@Mock
			public int method2() {
				return 20;
			}
		};
		// 自定义一个AnOrdinaryInterface的实现
		AnOrdinaryInterface instance1 = new AnOrdinaryInterface() {
			@Override
			public int method1() {
				return 1;
			}

			@Override
			public int method2() {
				return 1;
			}
		};
		// 再定义一个AnOrdinaryInterface的实现
		AnOrdinaryInterface instance2 = new AnOrdinaryInterface() {
			@Override
			public int method1() {
				return 2;
			}

			@Override
			public int method2() {

				return 2;
			}
		};
		// 发现自定义的实现没有起作用，而是被Mock逻辑替代了
		Assert.assertEquals(10, instance1.method1());
		Assert.assertEquals(10, instance2.method1());
		Assert.assertEquals(20, instance1.method2());
		Assert.assertEquals(20, instance2.method2());
	}

	// 其实用@Capturing也是一样的效果
	@Test
	public <T extends AnOrdinaryInterface> void sameEffect(@Capturing AnOrdinaryInterface instance) {
		new Expectations() {
			{
				instance.method1();
				result = 10;
				instance.method2();
				result = 20;
			}
		};
		// 自定义一个AnOrdinaryInterface的实现
		AnOrdinaryInterface instance1 = new AnOrdinaryInterface() {
			@Override
			public int method1() {
				return 1;
			}

			@Override
			public int method2() {
				return 1;
			}
		};
		// 再定义一个AnOrdinaryInterface的实现
		AnOrdinaryInterface instance2 = new AnOrdinaryInterface() {
			@Override
			public int method1() {
				return 2;
			}

			@Override
			public int method2() {

				return 2;
			}
		};
		// 发现自定义的实现没有起作用，而是被Mock逻辑替代了
		Assert.assertEquals(10, instance1.method1());
		Assert.assertEquals(10, instance2.method1());
		Assert.assertEquals(20, instance1.method2());
		Assert.assertEquals(20, instance2.method2());
	}
}
