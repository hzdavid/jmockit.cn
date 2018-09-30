package cn.jmockit.demos.usual;


import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.util.Assert;

import cn.jmockit.demos.ISayHello;
import cn.jmockit.demos.SayHello;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//代码覆盖率测试，观察覆盖率的计算方式,去target/coverage-report目录下，查看SayHello这个类的覆盖率
public class CodeCoverageTest {
	ISayHello sayHello = new SayHello();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	//测试 sayHello(String who, int gender);
	@Test
	public void testSayHello1() {
		Assert.isTrue(sayHello.sayHello("david", ISayHello.MALE).equals("hello Mr david"));
		Assert.isTrue(sayHello.sayHello("lucy", ISayHello.FEMALE).equals("hello Mrs lucy"));
		thrown.expect(IllegalArgumentException.class);
		sayHello.sayHello("david", 3);
	}
	//测试 sayHello(String[] who, int[] gender)
	@Test
	public void testSayHello2() {
		String[] who = new String[] { "david", "lucy" };
		int[] gender = new int[] { ISayHello.MALE, ISayHello.FEMALE };
		List<String> result = sayHello.sayHello(who, gender);
		Assert.isTrue(result.get(0).equals("hello Mr david"));
		Assert.isTrue(result.get(1).equals("hello Mrs lucy"));
	}

}
