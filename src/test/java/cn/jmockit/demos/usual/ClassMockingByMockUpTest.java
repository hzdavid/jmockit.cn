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
import mockit.Mock;
import mockit.MockUp;

//用MockUp来mock类
public class ClassMockingByMockUpTest {
	// AnOrdinaryClass的MockUp类，继承MockUp即可
	public static class AnOrdinaryClassMockUp extends MockUp<AnOrdinaryClass> {
		// Mock静态方法
		@Mock
		public static int staticMethod() {
			return 10;
		}

		// Mock普通方法
		@Mock
		public int ordinaryMethod() {
			return 20;
		}

		@Mock
		// Mock final方法
		public final int finalMethod() {
			return 30;
		}

		// Mock native方法
		@Mock
		public int navtiveMethod() {
			return 40;
		}

		// Mock private方法
		@Mock
		private int privateMethod() {
			return 50;
		}
	}

	@Test
	public void testClassMockingByMockUp() {
		new AnOrdinaryClassMockUp();
		AnOrdinaryClass instance = new AnOrdinaryClass();
		// 静态方法被mock了
		Assert.assertTrue(AnOrdinaryClass.staticMethod() == 10);
		// 普通方法被mock了
		Assert.assertTrue(instance.ordinaryMethod() == 20);
		// final方法被mock了
		Assert.assertTrue(instance.finalMethod() == 30);
		// native方法被mock了
		Assert.assertTrue(instance.navtiveMethod() == 40);
		// private方法被mock了
		Assert.assertTrue(instance.callPrivateMethod() == 50);
	}

	@BeforeClass
	// 加载AnOrdinaryClass类的native方法的dll
	public static void loadNative() throws Throwable {
		URI uri = ClassLoader.class.getResource("/").toURI();
		String realPath = new File(uri).getAbsolutePath() + "/libAnOrdinaryClass.dll";
		System.load(realPath);
	}
}
