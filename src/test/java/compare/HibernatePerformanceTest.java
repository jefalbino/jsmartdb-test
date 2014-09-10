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

import hibernate.HibernateBeta;
import hibernate.HibernatePi;
import hibernate.HibernatePsi;
import hibernate.HibernateRo;
import hibernate.HibernateTau;

import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;


public class HibernatePerformanceTest extends HibernateAbstractTest {

	private static final Integer INTEGER_UPDATE = 30;
	private static final Boolean BOOLEAN_UPDATE = false;
	private static final BigDecimal DECIMAL_UPDATE = new BigDecimal(50);
	private static final Double DOUBLE_UPDATE = 40.5d;
	private static final Float FLOAT_UPDATE = 100.0f;
	private static final Long LONG_UPDATE = 3000l;
	protected static final String STRING_UPDATE = "String updated";
	protected static final String TEXT_UPDATE = "A looooooonnnnnnnnnng text that could be used in the database are now updated.";
	protected static final Date DATE_UPDATE = new Date(1405911599000l);

	private Session session;

	private HibernateBeta basic;
	private HibernateTau complex;

	//@Rule
	public TestName name = new TestName();

	//@BeforeClass
	public static void setUpDatabase() throws Exception {
		HibernateAbstractTest.setUpDatabase();
	}

	//@Before
	public void setUp() throws Exception {
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		basic = getBetaEntity();

		complex = getTauEntity();
		complex.setFi(getFiEntity());
		complex.getPis().add(getPiEntity());
		complex.getPis().add(getPiEntity());

		complex.getPsis().add(getPsiEntity());
		complex.getPsis().add(getPsiEntity());
		
		HibernateRo ro = getRoEntity();
		HibernateRo ro2 = getRoEntity();
		ro.setTau(complex);
		ro.setSigma(getSigmaEntity());
		ro2.setTau(complex);
		ro2.setSigma(getSigmaEntity());

		complex.getRos().add(ro);
		complex.getRos().add(ro2);

		if (name.getMethodName().equals("basicUpdateTest") 
				|| name.getMethodName().equals("basicDeleteTest") 
				|| name.getMethodName().equals("basicSelectTest")) {
			session.save(basic);
		}

		if (name.getMethodName().equals("complexUpdateTest") 
				|| name.getMethodName().equals("complexDeleteTest") 
				|| name.getMethodName().equals("complexSelectTest")) {
			session.save(complex);
		}
	}

	//@After
	public void tearDown() throws Exception{
		session.getTransaction().commit();
	}

	//@AfterClass
	public static void tearDownDatabase() throws Exception {
		HibernateAbstractTest.tearDownDatabase();
	}

	//@Test
	public void basicInsertTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		session.save(basic);
		System.out.println("Hibernate basicInsertTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	//@Test
	public void complexInsertTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		session.save(complex);
		System.out.println("Hibernate complexInsertTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	//@Test
	public void basicUpdateTest() throws Exception {
		long initialTime = System.currentTimeMillis();

		basic.setInteger(INTEGER_UPDATE);
		basic.setBool(BOOLEAN_UPDATE);
		basic.setDecimal(DECIMAL_UPDATE);
		basic.setDoubli(DOUBLE_UPDATE);
		basic.setFloati(FLOAT_UPDATE);
		basic.setLongi(LONG_UPDATE);
		basic.setString(STRING_UPDATE);
		basic.setText(TEXT_UPDATE);

		session.update(basic);
		System.out.println("Hibernate basicUpdateTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	//@Test
	public void complexUpdateTest() throws Exception {
		long initialTime = System.currentTimeMillis();

		complex.setInteger(INTEGER_UPDATE);
		complex.setBool(BOOLEAN_UPDATE);
		complex.setDecimal(DECIMAL_UPDATE);
		complex.setDoubli(DOUBLE_UPDATE);
		complex.setFloati(FLOAT_UPDATE);
		complex.setLongi(LONG_UPDATE);
		complex.setString(STRING_UPDATE);
		complex.setText(TEXT_UPDATE);

		complex.getFi().setInteger(INTEGER_UPDATE);
		complex.getFi().setBool(BOOLEAN_UPDATE);
		complex.getFi().setDecimal(DECIMAL_UPDATE);
		complex.getFi().setDoubli(DOUBLE_UPDATE);
		complex.getFi().setFloati(FLOAT_UPDATE);
		complex.getFi().setLongi(LONG_UPDATE);
		complex.getFi().setString(STRING_UPDATE);
		complex.getFi().setText(TEXT_UPDATE);

		for (HibernatePsi psi : complex.getPsis()) {
			psi.setInteger(INTEGER_UPDATE);
			psi.setBool(BOOLEAN_UPDATE);
			psi.setDecimal(DECIMAL_UPDATE);
			psi.setDoubli(DOUBLE_UPDATE);
			psi.setFloati(FLOAT_UPDATE);
			psi.setLongi(LONG_UPDATE);
			psi.setString(STRING_UPDATE);
			psi.setText(TEXT_UPDATE);
		}

		for (HibernatePi pi : complex.getPis()) {
			pi.setInteger(INTEGER_UPDATE);
			pi.setBool(BOOLEAN_UPDATE);
			pi.setDecimal(DECIMAL_UPDATE);
			pi.setDoubli(DOUBLE_UPDATE);
			pi.setFloati(FLOAT_UPDATE);
			pi.setLongi(LONG_UPDATE);
			pi.setString(STRING_UPDATE);
			pi.setText(TEXT_UPDATE);
		}

		for (HibernateRo ro : complex.getRos()) {
			ro.setInteger(INTEGER_UPDATE);
			ro.setBool(BOOLEAN_UPDATE);
			ro.setDecimal(DECIMAL_UPDATE);
			ro.setDoubli(DOUBLE_UPDATE);
			ro.setFloati(FLOAT_UPDATE);
			ro.setLongi(LONG_UPDATE);
			ro.setString(STRING_UPDATE);
			ro.setText(TEXT_UPDATE);

			ro.getSigma().setInteger(INTEGER_UPDATE);
			ro.getSigma().setBool(BOOLEAN_UPDATE);
			ro.getSigma().setDecimal(DECIMAL_UPDATE);
			ro.getSigma().setDoubli(DOUBLE_UPDATE);
			ro.getSigma().setFloati(FLOAT_UPDATE);
			ro.getSigma().setLongi(LONG_UPDATE);
			ro.getSigma().setString(STRING_UPDATE);
			ro.getSigma().setText(TEXT_UPDATE);
		}

		session.update(complex);
		System.out.println("Hibernate complexUpdateTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	//@Test
	public void basicDeleteTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		session.delete(basic);
		System.out.println("Hibernate basicDeleteTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	//@Test
	public void complexDeleteTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		session.delete(complex);
		System.out.println("Hibernate complexDeleteTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	//@Test
	public void basicSelectTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		// TODO
		System.out.println("Hibernate basicSelectTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

	//@Test
	public void complexSelectTest() throws Exception {
		long initialTime = System.currentTimeMillis();
		// TODO
		System.out.println("Hibernate complexSelectTest :" + (System.currentTimeMillis() - initialTime) + "ms");
	}

}
