/*
 * Copyright 2017 EpochArch.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.epocharch.kuroro.producer.netty;

import com.epocharch.kuroro.common.inner.wrap.WrappedMessage;
import com.epocharch.kuroro.common.inner.wrap.WrappedProducerAck;
import com.epocharch.kuroro.common.netty.component.SimpleClient;
import com.epocharch.kuroro.common.netty.component.SimpleThreadPool;
import com.epocharch.kuroro.common.protocol.json.JsonDecoder;
import com.epocharch.kuroro.common.protocol.json.JsonEncoder;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

public class ProducerClientPipeLineFactory implements ChannelPipelineFactory {

	private SimpleClient client;
	private SimpleThreadPool threadPool;

	public ProducerClientPipeLineFactory(SimpleClient client,
			SimpleThreadPool threadPool) {
		this.threadPool = threadPool;
		this.client = client;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(
				Integer.MAX_VALUE, 0, 4, 0, 4));
		pipeline.addLast("jsonDecoder", new JsonDecoder(
				WrappedProducerAck.class));
		pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline.addLast("jsonEncoder", new JsonEncoder(WrappedMessage.class));
		pipeline.addLast("handler",
				new MessageClientHandler(client, threadPool));
		return pipeline;
	}

}
