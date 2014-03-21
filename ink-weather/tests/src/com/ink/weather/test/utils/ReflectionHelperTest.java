package com.ink.weather.test.utils;

import com.ink.weather.dto.ResultDTO;
import com.ink.weather.dto.base.IDTO;
import com.ink.weather.utils.ReflectionHelper;

import junit.framework.TestCase;

/**
 * When reflection creates a new instance of an object that implements 
 * an interface is created, it MUST validate as true for the 
 * "object instanceof interface" condition.
 * @author samkirton
 */
public class ReflectionHelperTest extends TestCase {

	public ReflectionHelperTest(String name) {
		super(name);
	}
	
	public void testInterfaceType() {
		// given the ResultDTO class definition
		Class<?> classDef = ResultDTO.class;
		
		// when a new instance of ResultDTO is created through reflection
		IDTO genericDTO = (IDTO)ReflectionHelper.newInstance(classDef);
		
		// then the generic DTO returned must be an instance of the classDef
		assertTrue(genericDTO instanceof ResultDTO);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
}
