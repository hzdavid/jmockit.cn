package cn.jmockit.demos.basic;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import mockit.Expectations;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//通过Expectations对其构造函数mock对象进行录制
public class ExpectationsConstructorTest2 {

	// 把类传入Expectations的构造函数
	@Test
	public void testRecordConstructor1() {
		Calendar cal = Calendar.getInstance();
		// 把待Mock的类传入Expectations的构造函数，可以达到只mock类的部分行为的目的
		new Expectations(Calendar.class) {
			{
				// 只对get方法并且参数为Calendar.HOUR_OF_DAY进行录制
				cal.get(Calendar.HOUR_OF_DAY);
				result = 7;// 小时永远返回早上7点钟
			}
		};
		Calendar now = Calendar.getInstance();
		// 因为下面的调用mock过了，小时永远返回7点钟了
		Assert.assertEquals(7, now.get(Calendar.HOUR_OF_DAY));
		// 因为下面的调用没有mock过，所以方法的行为不受mock影响，
		Assert.assertEquals(now.get(Calendar.DAY_OF_MONTH),LocalDate.now().getDayOfMonth());
	}

	// 把对象传入Expectations的构造函数
	@Test
	public void testRecordConstructor2() {
		Calendar cal = Calendar.getInstance();
		// 把待Mock的对象传入Expectations的构造函数，可以达到只mock类的部分行为的目的，但只对这个对象影响
		new Expectations(cal) {
			{
				// 只对get方法并且参数为Calendar.HOUR_OF_DAY进行录制
				cal.get(Calendar.HOUR_OF_DAY);
				result = 7;// 小时永远返回早上7点钟
			}
		};

		// 因为下面的调用mock过了，小时永远返回7点钟了
		Assert.assertEquals(7, cal.get(Calendar.HOUR_OF_DAY));
		// 因为下面的调用没有mock过，所以方法的行为不受mock影响，
		Assert.assertEquals(cal.get(Calendar.DAY_OF_MONTH), LocalDate.now().getDayOfMonth());

		// now是另一个对象，上面录制只对cal对象的影响，所以now的方法行为没有任何变化
		Calendar now = Calendar.getInstance();
		// 不受mock影响
		Assert.assertEquals(now.get(Calendar.HOUR_OF_DAY), LocalTime.now().getHour());
		// 不受mock影响
		Assert.assertEquals(now.get(Calendar.DAY_OF_MONTH), LocalDate.now().getDayOfMonth());
	}
}
