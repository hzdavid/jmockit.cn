package cn.jmockit.demos.usual;

import org.junit.Assert;
import org.junit.Test;

import cn.jmockit.demos.AnOrdinaryClass;
import cn.jmockit.demos.utils.JNITools;
import mockit.Expectations;

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
				// native, private方法无法用Expectations来Mock
			}
		};
		Assert.assertTrue(AnOrdinaryClass.staticMethod() == 1);
		Assert.assertTrue(instance.ordinaryMethod() == 20);
		Assert.assertTrue(instance.finalMethod() == 30);
		// 用Expectations无法mock native方法
		Assert.assertTrue(instance.navtiveMethod() == 4);
		// 用Expectations无法mock private方法
		Assert.assertTrue(instance.callPrivateMethod() == 5);
	}

	// 加载AnOrdinaryClass类的native方法的native实现
	public static void loadNative() throws Throwable {
		JNITools.loadNative();
	}
}
