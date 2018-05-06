package cn.jmockit.demos.usual;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
import java.io.File;
import java.net.URI;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import cn.jmockit.demos.AnOrdinaryClass;
import cn.jmockit.demos.utils.JNITools;
import mockit.Expectations;

//用Expectations来mock类
public class ClassMockingByExpectationsTest {

	@Test
	public void testClassMockingByExpectation() {
		AnOrdinaryClass instanceToRecord = new AnOrdinaryClass();
		new Expectations(AnOrdinaryClass.class) {
			{
				// mock静态方法
				AnOrdinaryClass.staticMethod();
				result = 10;
				// mock普通方法
				instanceToRecord.ordinaryMethod();
				result = 20;
				// mock final方法
				instanceToRecord.finalMethod();
				result = 30;
				// native, private方法无法用Expectations来Mock
			}
		};
		AnOrdinaryClass instance = new AnOrdinaryClass();
		Assert.assertTrue(AnOrdinaryClass.staticMethod() == 10);
		Assert.assertTrue(instance.ordinaryMethod() == 20);
		Assert.assertTrue(instance.finalMethod() == 30);
		// 用Expectations无法mock native方法
		Assert.assertTrue(instance.navtiveMethod() == 4);
		// 用Expectations无法mock private方法
		Assert.assertTrue(instance.callPrivateMethod() == 5);
	}

	@BeforeClass
	// 加载AnOrdinaryClass类的native方法的native实现
	public static void loadNative() throws Throwable {
		JNITools.loadNative();
	}
}
