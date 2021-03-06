/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.xd.analytics.metrics.redis;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.xd.analytics.metrics.SharedCounterRepositoryTests;

@Ignore("Maybe issue in configuration of redis CI server environment.  Also see https://github.com/xetorthio/jedis/issues/407")
public class RedisCounterRepositoryTests extends SharedCounterRepositoryTests {

	@AfterClass
	@BeforeClass
	public static void beforeAndAfter() {
		//TODO refactor into clean-up methods based on metric naming strategy classes.
		counterRepository = new RedisCounterRepository(TestUtils.getJedisConnectionFactory());
		StringRedisTemplate stringRedisTemplate = TestUtils.getStringRedisTemplate();
		Set<String> keys = stringRedisTemplate.keys("counts." + "*");
		if (keys.size() > 0) {
			stringRedisTemplate.delete(keys);
		}
	}

}
