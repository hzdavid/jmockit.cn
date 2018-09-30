package cn.jmockit.demos;

import java.util.ArrayList;
import java.util.List;

public class SayHello implements ISayHello {
	@Override
	public String sayHello(String who, int gender) {
		// 性别校验
		if (gender != FEMALE) {
			if (gender != MALE) {
				throw new IllegalArgumentException("illegal gender");
			}
		}
		// 根据不同性别，返回不同打招呼的内容
		switch (gender) {
		case FEMALE:
			return "hello Mrs " + who; 
		case MALE:
			return "hello Mr " + who;
		default:
			return "hello  " + who;
		}
	}

	@Override
	public List<String> sayHello(String[] who, int[] gender) {
		// 参数校验
		if (who == null || gender == null) {
			return null;
		}
		if (who.length != gender.length) {
			throw new IllegalArgumentException();
		}
		//把向每个人打招呼的内容，保存到result中。
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < gender.length; i++) {
			result.add(this.sayHello(who[i], gender[i]));
		}
		return result;
	}

}
