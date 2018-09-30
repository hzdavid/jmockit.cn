package cn.jmockit.demos.usual;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.jmockit.demos.AnOrdinaryClass;
import cn.jmockit.demos.utils.JNITools;
import mockit.Expectations;

/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//mock实例
public class InstanceMockingByExpectationsTest {
	@Test
	public void testInstanceMockingByExpectation() {
		AnOrdinaryClass instance = new AnOrdinaryClass();
		// 直接把实例传给Expectations的构造函数即可Mock这个实例
		new Expectations(instance) {
			{
				// 尽管这里也可以Mock静态方法，但不推荐在这里写。静态方法的Mock应该是针对类的
				// mock普通方法
				instance.ordinaryMethod();
				result = 20;
				// mock final方法
				instance.finalMethod();
				result = 30;
				instance.navtiveMethod();
				// native, private方法无法用Expectations来Mock
			}
		};
		Assert.assertEquals(1, AnOrdinaryClass.staticMethod());
		Assert.assertEquals(20, instance.ordinaryMethod());
		Assert.assertEquals(30, instance.finalMethod());
		// 用Expectations无法mock native方法
		Assert.assertEquals(4, instance.navtiveMethod());
		// 用Expectations无法mock private方法
		Assert.assertEquals(5, instance.callPrivateMethod());
	}

	// 加载AnOrdinaryClass类的native方法的native实现
	@BeforeClass
	public static void loadNative() throws Throwable {
		JNITools.loadNative();
	}
}
