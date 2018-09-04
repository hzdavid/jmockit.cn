package cn.jmockit.demos.advance;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;

//Mock方法中还可以调用老方法
public class InvocationMockUpTest {
	@Test
	public void testMockUp() {
		// 对Java自带类Calendar的get方法进行定制
		new MockUp<Calendar>(Calendar.class) {
			// 申明参数invocation，表示老方法的调用
			@Mock
			public int get(Invocation invocation, int unit) {
				// 只希望时间是早上7点
				if (unit == Calendar.HOUR_OF_DAY) {
					return 7;
				}
				// 其它时间（年份，月份，日，分，秒均不变)
				return invocation.proceed(unit);
			}
		};
		Calendar now = Calendar.getInstance();
		// 只有小时变成Mock方法
		Assert.assertEquals(7, now.get(Calendar.HOUR_OF_DAY));
		// 其它的还是走老的方法

		Assert.assertEquals(now.get(Calendar.MONTH),LocalDate.now().getMonthValue());
		Assert.assertEquals(now.get(Calendar.DAY_OF_MONTH), LocalDate.now().getDayOfMonth());
	}

}
