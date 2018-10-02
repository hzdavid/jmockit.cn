package cn.jmockit.demos.mockuplibs;

import java.util.Map;

import org.apache.dubbo.config.spring.ReferenceBean;

import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//dubbo消费bean的MockUp(伪类）
@SuppressWarnings("rawtypes")
public class DubboConsumerBeanMockUp extends MockUp<ReferenceBean> {
	// 自定义的消费bean mock对象
	private Map<String, Object> mockMap;

	public DubboConsumerBeanMockUp() {
	}

	public DubboConsumerBeanMockUp(Map<String, Object> mockMap) {
		this.mockMap = mockMap;
	}

	// 对ReferenceBean的getObject方法的Mock
	@SuppressWarnings("unchecked")
	@Mock
	public Object getObject(Invocation inv) throws Exception {
		ReferenceBean ref = inv.getInvokedInstance();
		String interfaceName = ref.getInterface();
		Object mock = mockMap.get(interfaceName);
		if (mock != null) {
			return mock;
		}
		return (new MockUp(Class.forName(interfaceName)) {
		}).getMockInstance();
	}
}