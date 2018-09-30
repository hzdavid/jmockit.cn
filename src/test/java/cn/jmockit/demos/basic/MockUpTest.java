package cn.jmockit.demos.basic;

import java.util.Calendar;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import mockit.Mock;
import mockit.MockUp;

import static org.junit.Assert.assertTrue;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//Mockup & @Mock的Mock方式
public class MockUpTest {

	@Test
	public void testMockUp() {
		// 对Java自带类Calendar的get方法进行定制
		// 只需要把Calendar类传入MockUp类的构造函数即可
		new MockUp<Calendar>(Calendar.class) {
			// 想Mock哪个方法，就给哪个方法加上@Mock， 没有@Mock的方法，不受影响
			@Mock
			public int get(int unit) {
				if (unit == Calendar.YEAR) {
					return 2017;
				}
				if (unit == Calendar.MONTH) {
					return 12;
				}
				if (unit == Calendar.DAY_OF_MONTH) {
					return 25;
				}
				if (unit == Calendar.HOUR_OF_DAY) {
					return 7;
				}
				return 0;
			}
		};
		// 从此Calendar的get方法，就沿用你定制过的逻辑，而不是它原先的逻辑。
		Calendar cal = Calendar.getInstance(Locale.FRANCE);
		Assert.assertEquals(2017, cal.get(Calendar.YEAR));
		Assert.assertEquals(12, cal.get(Calendar.MONDAY));
		Assert.assertEquals(25, cal.get(Calendar.DAY_OF_MONTH));
		Assert.assertEquals(7, cal.get(Calendar.HOUR_OF_DAY));
		// Calendar的其它方法，不受影响
		assertTrue((cal.getFirstDayOfWeek() == Calendar.MONDAY));

	}
}
