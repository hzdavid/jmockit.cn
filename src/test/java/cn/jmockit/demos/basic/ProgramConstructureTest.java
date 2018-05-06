package cn.jmockit.demos.basic;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
import org.junit.Assert;
import org.junit.Test;

import cn.jmockit.demos.HelloJMockit;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

//JMockit的程序结构
public class ProgramConstructureTest {

	// 这是一个测试属性
	@Mocked
	HelloJMockit helloJMockit;

	@Test
	public void test1() {
		// 录制(Record)
		new Expectations() {
			{
				helloJMockit.sayHello();
				// 期待上述调用的返回是"hello,david"，而不是返回"hello,JMockit"
				result = "hello,david";
			}
		};
		// 重放(Replay)
		String msg = helloJMockit.sayHello();
		Assert.assertTrue(msg.equals("hello,david"));
		// 验证(Verification)
		new Verifications() {
			{
				helloJMockit.sayHello();

				times = 1;
			}
		};
	}

	@Test
	public void test2(@Mocked HelloJMockit helloJMockit /* 这是一个测试参数 */) {
		// 录制(Record)
		new Expectations() {
			{
				helloJMockit.sayHello();
				// 期待上述调用的返回是"hello,david"，而不是返回"hello,JMockit"
				result = "hello,david";
			}
		};
		// 重放(Replay)
		String msg = helloJMockit.sayHello();
		Assert.assertTrue(msg.equals("hello,david"));
		// 验证(Verification)
		new Verifications() {
			{
				helloJMockit.sayHello();
				// 验证helloJMockit.sayHello()这个方法调用了1次
				times = 1;
			}
		};
	}
}
