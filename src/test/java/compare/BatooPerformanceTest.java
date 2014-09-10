/*
 * JSmartDB - Java ORM Framework
 * Copyright (c) 2014, Jeferson Albino da Silva, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this library. If not, see <http://www.gnu.org/licenses/>.
*/

package compare;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class BatooPerformanceTest extends BatooAbstractTest {

	private static final Integer INTEGER_UPDATE = 30;
	private static final Boolean BOOLEAN_UPDATE = false;
	private static final BigDecimal DECIMAL_UPDATE = new BigDecimal(50);
	private static final Double DOUBLE_UPDATE = 40.5d;
	private static final Float FLOAT_UPDATE = 100.0f;
	private static final Long LONG_UPDATE = 3000l;
	protected static final String STRING_UPDATE = "String updated";
	protected static final String TEXT_UPDATE = "A looooooonnnnnnnnnng text that could be used in the database are now updated.";
	protected static final Date DATE_UPDATE = new Date(1405911599000l);

	@Rule
	public TestName name = new TestName();

	@BeforeClass
	public static void setUpDatabase() throws Exception {
		BatooAbstractTest.setUpDatabase();
	}

	@Before
	public void setUp() throws Exception {

		// TODO

		if (name.getMethodName().equals("basicUpdateTest") 
				|| name.getMethodName().equals("basicDeleteTest") 
				|| name.getMethodName().equals("basicSelectTest")) {
			// TODO
		}

		if (name.getMethodName().equals("complexUpdateTest") 
				|| name.getMethodName().equals("complexDeleteTest") 
				|| name.getMethodName().equals("complexSelectTest")) {
			// TODO
		}
	}

	@After
	public void tearDown() throws Exception{
		// TODO
	}

	@AfterClass
	public static void tearDownDatabase() throws Exception {
		BatooAbstractTest.tearDownDatabase();
	}

	@Test
	public void basicInsertTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		// TODO
		System.out.println("Batoo basicInsertTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	@Test
	public void complexInsertTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		// TODO
		System.out.println("Batoo complexInsertTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	@Test
	public void basicUpdateTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		// TODO
		System.out.println("Batoo basicUpdateTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	@Test
	public void complexUpdateTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		// TODO
		System.out.println("Batoo complexUpdateTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	@Test
	public void basicDeleteTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		// TODO
		System.out.println("Batoo basicDeleteTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	@Test
	public void complexDeleteTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		// TODO
		System.out.println("Batoo complexDeleteTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	@Test
	public void basicSelectTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		// TODO
		System.out.println("Batoo basicSelectTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	@Test
	public void complexSelectTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		// TODO
		System.out.println("Batoo complexSelectTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

}
