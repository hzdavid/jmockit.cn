package cn.jmockit.demos.mockuplibs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.alibaba.rocketmq.client.producer.TransactionSendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import mockit.Mock;
import mockit.MockUp;
/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//MQ消息发送者 的MockUp(伪类） 
public class RocketMQProducerMockUp extends MockUp<DefaultMQProducer> {

	@Mock
	void init() throws MQClientException {
		// 构造函数也什么都不做
	}

	@Mock
	void start() throws MQClientException {
		// 启动，什么都不做 
	}

	@Mock
	void shutdown() {
		// 关闭，也什么都不做 
	}

	@Mock
	List<MessageQueue> fetchPublishMessageQueues(final String topic) throws MQClientException {
		// 欺骗调用方，返回不存在的消息队列，因为消息并不会真正发送嘛
		List<MessageQueue> queues = new ArrayList<MessageQueue>();
		MessageQueue q = new MessageQueue();
		q.setBrokerName("testbrokername");
		q.setQueueId(1);
		q.setTopic("testtopic");
		queues.add(q);
		return queues;
	}

	// 下面是对各个send方法的mock,都返回消息成功结果
	@Mock
	SendResult send(final Message msg)
			throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		return newSuccessSendResult();
	}

	@Mock
	SendResult send(final Message msg, final long timeout)
			throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		return newSuccessSendResult();
	}

	@Mock
	void send(final Message msg, final SendCallback sendCallback)
			throws MQClientException, RemotingException, InterruptedException {
		sendCallback.onSuccess(this.newSuccessSendResult());
	}

	@Mock
	void send(final Message msg, final SendCallback sendCallback, final long timeout)
			throws MQClientException, RemotingException, InterruptedException {
		sendCallback.onSuccess(this.newSuccessSendResult());
	}

	@Mock
	void sendOneway(final Message msg) throws MQClientException, RemotingException, InterruptedException {

	}

	@Mock
	SendResult send(final Message msg, final MessageQueue mq)
			throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		return newSuccessSendResult();
	}

	@Mock
	SendResult send(final Message msg, final MessageQueue mq, final long timeout)
			throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		return newSuccessSendResult();
	}

	@Mock
	void send(final Message msg, final MessageQueue mq, final SendCallback sendCallback)
			throws MQClientException, RemotingException, InterruptedException {
		sendCallback.onSuccess(this.newSuccessSendResult());
	}

	@Mock
	void send(final Message msg, final MessageQueue mq, final SendCallback sendCallback, long timeout)
			throws MQClientException, RemotingException, InterruptedException {
		sendCallback.onSuccess(this.newSuccessSendResult());
	}

	@Mock
	void sendOneway(final Message msg, final MessageQueue mq)
			throws MQClientException, RemotingException, InterruptedException {

	}

	@Mock
	SendResult send(final Message msg, final MessageQueueSelector selector, final Object arg)
			throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		return newSuccessSendResult();
	}

	@Mock
	SendResult send(final Message msg, final MessageQueueSelector selector, final Object arg, final long timeout)
			throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		return newSuccessSendResult();
	}

	@Mock
	void send(final Message msg, final MessageQueueSelector selector, final Object arg, final SendCallback sendCallback)
			throws MQClientException, RemotingException, InterruptedException {
		sendCallback.onSuccess(this.newSuccessSendResult());
	}

	@Mock
	void send(final Message msg, final MessageQueueSelector selector, final Object arg, final SendCallback sendCallback,
			final long timeout) throws MQClientException, RemotingException, InterruptedException {
		sendCallback.onSuccess(this.newSuccessSendResult());
	}

	@Mock
	void sendOneway(final Message msg, final MessageQueueSelector selector, final Object arg)
			throws MQClientException, RemotingException, InterruptedException {

	}

	@Mock
	TransactionSendResult sendMessageInTransaction(final Message msg, final LocalTransactionExecuter tranExecuter,
			final Object arg) throws MQClientException {
		return newTransactionSendResult();
	}

	private TransactionSendResult newTransactionSendResult() {
		TransactionSendResult success = new TransactionSendResult();
		success.setSendStatus(SendStatus.SEND_OK);
		success.setMsgId(UUID.randomUUID().toString());
		MessageQueue q = new MessageQueue();
		q.setBrokerName("testbrokername");
		q.setQueueId(1);
		q.setTopic("testtopic");
		success.setMessageQueue(q);
		success.setLocalTransactionState(LocalTransactionState.COMMIT_MESSAGE);
		return success;
	}

	private SendResult newSuccessSendResult() {
		SendResult success = new SendResult();
		success.setSendStatus(SendStatus.SEND_OK);
		success.setMsgId(UUID.randomUUID().toString());
		MessageQueue q = new MessageQueue();
		q.setBrokerName("testbrokername");
		q.setQueueId(1);
		q.setTopic("testtopic");
		success.setMessageQueue(q);
		return success;
	}
}