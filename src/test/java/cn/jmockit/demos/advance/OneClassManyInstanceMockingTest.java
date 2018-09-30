package cn.jmockit.demos.advance;

import org.junit.Assert;
import org.junit.Test;

import cn.jmockit.demos.AnOrdinaryClass;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//一个类多个实例的Mock 
public class OneClassManyInstanceMockingTest {
	// Mock方法一: 把实例传入Expectations的构造函数。适用场景： 只Mock实例的部分方法，对实例的类的其它实例不产生影响
	@Test
	public void testMocking1() {
		AnOrdinaryClass instance1 = new AnOrdinaryClass();
		AnOrdinaryClass instance2 = new AnOrdinaryClass();
		// 直接把实例传给Expectations的构造函数即可Mock这个实例
		new Expectations(instance1, instance2) {
			{
				instance1.ordinaryMethod();
				result = 20;
				instance2.ordinaryMethod();
				result = 200;
			}
		};
		AnOrdinaryClass instance3 = new AnOrdinaryClass();
		// instance1的ordinaryMethod被Mock了
		Assert.assertTrue(instance1.ordinaryMethod() == 20);
		// instance2的ordinaryMethod被Mock了
		Assert.assertTrue(instance2.ordinaryMethod() == 200);
		// instance3不受影响。
		Assert.assertTrue(instance3.ordinaryMethod() == 2);
	}
	// Mock方法二: 用@Mocked。适用场景： 类的所实例都需要Mock，但不同实例也能保留不同的Mock逻辑
	@Test
	public void testMocking2(@Mocked AnOrdinaryClass instance1, @Mocked AnOrdinaryClass instance2) {
		new Expectations() {
			{
				instance1.ordinaryMethod();
				result = 20;
				instance2.ordinaryMethod();
				result = 200;
			}
		};
		AnOrdinaryClass instance3 = new AnOrdinaryClass();
		// instance1的ordinaryMethod被Mock了
		Assert.assertEquals(20, instance1.ordinaryMethod());
		// instance2的ordinaryMethod被Mock了
		Assert.assertEquals(200, instance2.ordinaryMethod());
		// instance3受@Mock的影响。@Mock会把类的所有方法都Mock，返回类型为基本数据类型的返回0
		Assert.assertEquals(0, instance3.ordinaryMethod());
	}
	// Mock方法二: 用@Injectable。适用场景： 不是类的所实例都需要Mock，不同实例也能保留不同的Mock逻辑
	@Test
	public void testMocking3(@Injectable AnOrdinaryClass instance1, @Injectable AnOrdinaryClass instance2) {
		new Expectations() {
			{
				instance1.ordinaryMethod();
				result = 20;
				instance2.ordinaryMethod();
				result = 200;
			}
		};
		AnOrdinaryClass instance3 = new AnOrdinaryClass();
		// instance1的ordinaryMethod被Mock了
		Assert.assertEquals(20, instance1.ordinaryMethod());
		// instance2的ordinaryMethod被Mock了
		Assert.assertEquals(200, instance2.ordinaryMethod());
		// instance3不受@Injectable的影响。因为@Injectable只影响某个实例
		Assert.assertEquals(2, instance3.ordinaryMethod());
	}

}
