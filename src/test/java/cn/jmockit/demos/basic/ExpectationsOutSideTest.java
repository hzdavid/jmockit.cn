package cn.jmockit.demos.basic;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import mockit.Expectations;
import mockit.Mocked;

//通过 Expectations对外部类的mock对象进行录制
public class ExpectationsOutSideTest {
	@Mocked
	Calendar cal;

	@Test
	public void testRecordOutside() {
		new Expectations() {
			{
				// 对cal.get方法进行录制，并匹配参数 Calendar.YEAR
				cal.get(Calendar.YEAR);
				result = 2016;// 年份不再返回当前小时。而是返回2016年
				// 对cal.get方法进行录制，并匹配参数 Calendar.HOUR_OF_DAY
				cal.get(Calendar.HOUR_OF_DAY);
				result = 7;// 小时不再返回当前小时。而是返回早上7点钟
			}
		};
		Assert.assertTrue(cal.get(Calendar.YEAR) == 2016);
		Assert.assertTrue(cal.get(Calendar.HOUR_OF_DAY) == 7);
		// 因为没有录制过，所以这里月份返回默认值 0
		Assert.assertTrue(cal.get(Calendar.DAY_OF_MONTH) == 0);
	}

}
