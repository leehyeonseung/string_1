package com_study.stringStudy_1;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/junit.xml")
public class JunitTest {
	@Autowired ApplicationContext context;
	
//	static JunitTest testObject;
	static Set<JunitTest> testObjects = new HashSet<JunitTest>();
	static ApplicationContext contextObject = null;

	@Test
	public void test1() {
//		assertThat(this, is(not(sameInstance(testObject))));
//		testObject = this;
//		assertThat(testObjects, not(hasItem(this)));
//		testObject = this;
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		assertThat(contextObject == null || contextObject == this.context,is(true));
		contextObject =this.context;
	}

	@Test
	public void test2() {
//		assertThat(this, is(not(sameInstance(testObject))));
//		testObject = this;
//		assertThat(testObjects, not(hasItem(this)));
//		testObjects = this;
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject =this.context;
	}

	@Test
	public void test3() {
//		assertThat(this, is(not(sameInstance(testObject))));
//		testObject = this;
//		assertThat(testObjects, not(hasItem(this)));
//		testObjects = this;
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		
		assertThat(contextObject , either(is(nullValue())).or(is(this.context)));
		contextObject =this.context;
	}
}
