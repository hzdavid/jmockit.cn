package cn.jmockit.demos.advance;

import org.junit.Assert;
import org.junit.Test;

import cn.jmockit.demos.AnOrdinaryClassWithBlock;
import mockit.Mock;
import mockit.MockUp;

/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//Mock构造函数&初始代码块
public class ConstructorAndBlockMockingTest {
	// AnOrdinaryClassWithBlock的MockUp类，继承MockUp即可
	public static class AnOrdinaryClassWithBlockMockUp extends MockUp<AnOrdinaryClassWithBlock> {
		// Mock构造函数和初始代码块, 函数名$init就代表类的构造函数
		@Mock
		public void $init(int i) {
		}

		// Mock静态初始代码块,, 函数名$clinit就代表类的静态代码块
		@Mock
		public void $clinit() {
		}
	}

	@Test
	public void testClassMockingByMockUp() {
		new AnOrdinaryClassWithBlockMockUp();
		AnOrdinaryClassWithBlock instance = new AnOrdinaryClassWithBlock(10);
		// 静态初始代码块被mock了
		Assert.assertEquals(0, AnOrdinaryClassWithBlock.j);
		// 构造函数和初始代码块被mock
		Assert.assertEquals(0, instance.getI());
	}

}
