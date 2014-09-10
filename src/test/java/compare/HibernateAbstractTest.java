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

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import generic.GenericAbstractTest;
import hibernate.HibernateFi;
import hibernate.HibernatePi;
import hibernate.HibernatePsi;
import hibernate.HibernateRo;
import hibernate.HibernateSigma;
import hibernate.HibernateTau;
import hibernate.HibernateBeta;

public abstract class HibernateAbstractTest extends GenericAbstractTest {

	protected static SessionFactory sessionFactory;

	protected static void setUpDatabase() throws Exception {
		GenericAbstractTest.setUpDatabase();
		sessionFactory = buildSessionFactory();
	}

	protected static void tearDownDatabase() throws Exception {
		GenericAbstractTest.tearDownDatabase();
		sessionFactory = null;
	}

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	protected HibernateBeta getBetaEntity() {
		HibernateBeta beta = new HibernateBeta();
		beta.setInteger(INTEGER);
		beta.setBool(BOOLEAN);
		beta.setDecimal(DECIMAL);
		beta.setDoubli(DOUBLE);
		beta.setFloati(FLOAT);
		beta.setLongi(LONG);
		beta.setString(STRING);
		beta.setText(TEXT);
		return beta;
	}

	protected HibernateTau getTauEntity() {
		HibernateTau tau = new HibernateTau();
		tau.setInteger(INTEGER);
		tau.setBool(BOOLEAN);
		tau.setDecimal(DECIMAL);
		tau.setDoubli(DOUBLE);
		tau.setFloati(FLOAT);
		tau.setLongi(LONG);
		tau.setString(STRING);
		tau.setText(TEXT);
		return tau;
	}

	protected HibernateFi getFiEntity() {
		HibernateFi fi = new HibernateFi();
		fi.setInteger(INTEGER);
		fi.setBool(BOOLEAN);
		fi.setDecimal(DECIMAL);
		fi.setDoubli(DOUBLE);
		fi.setFloati(FLOAT);
		fi.setLongi(LONG);
		fi.setString(STRING);
		fi.setText(TEXT);
		return fi;
	}

	protected HibernatePi getPiEntity() {
		HibernatePi pi = new HibernatePi();
		pi.setInteger(INTEGER);
		pi.setBool(BOOLEAN);
		pi.setDecimal(DECIMAL);
		pi.setDoubli(DOUBLE);
		pi.setFloati(FLOAT);
		pi.setLongi(LONG);
		pi.setString(STRING);
		pi.setText(TEXT);
		return pi;
	}

	protected HibernatePsi getPsiEntity() {
		HibernatePsi psi = new HibernatePsi();
		psi.setInteger(INTEGER);
		psi.setBool(BOOLEAN);
		psi.setDecimal(DECIMAL);
		psi.setDoubli(DOUBLE);
		psi.setFloati(FLOAT);
		psi.setLongi(LONG);
		psi.setString(STRING);
		psi.setText(TEXT);
		return psi;
	}

	protected HibernateSigma getSigmaEntity() {
		HibernateSigma sigma = new HibernateSigma();
		sigma.setInteger(INTEGER);
		sigma.setBool(BOOLEAN);
		sigma.setDecimal(DECIMAL);
		sigma.setDoubli(DOUBLE);
		sigma.setFloati(FLOAT);
		sigma.setLongi(LONG);
		sigma.setString(STRING);
		sigma.setText(TEXT);
		return sigma;
	}

	protected HibernateRo getRoEntity() {
		HibernateRo ro = new HibernateRo();
		ro.setInteger(INTEGER);
		ro.setBool(BOOLEAN);
		ro.setDecimal(DECIMAL);
		ro.setDoubli(DOUBLE);
		ro.setFloati(FLOAT);
		ro.setLongi(LONG);
		ro.setString(STRING);
		ro.setText(TEXT);
		return ro;
	}

}
