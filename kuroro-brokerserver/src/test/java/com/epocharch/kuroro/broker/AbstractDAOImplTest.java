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


package com.epocharch.kuroro.broker;

import com.epocharch.kuroro.common.inner.dao.impl.mongodb.NewMongoClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = "classpath:applicationContext-test.xml")
public abstract class AbstractDAOImplTest extends
		AbstractJUnit4SpringContextTests {

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractDAOImplTest.class);
	protected static final String TOPIC_NAME = "default";
	protected static final String CONSUMER_ID = "consumer1";
	protected static final String IP = "192.168.92.63";

	@Autowired
	private NewMongoClient newMongoClient;

	@Before
	public void setUp() {
		LOG.info("---setup-----");
	}

	@BeforeClass
	public synchronized static void setUpClass() throws Exception {

	}
}
