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

package manager;

import com.jsmartdb.framework.manager.EntityManager;
import com.jsmartdb.framework.manager.EntityManagerFactory;

import entity.Alpha;
import entity.Beta;
import entity.Delta;
import entity.Epsilon;
import entity.Fi;
import entity.Gama;
import entity.Iota;
import entity.Omega;
import entity.Pi;
import entity.Psi;
import entity.Ro;
import entity.Sigma;
import entity.Tau;
import entity.Teta;

import generic.GenericAbstractTest;

public abstract class AbstractTest extends GenericAbstractTest {

	protected static EntityManager entityManager;

	protected static void setUpDatabase() throws Exception {
		GenericAbstractTest.setUpDatabase();
		entityManager = EntityManagerFactory.getNewInstance();
	}

	protected static void tearDownDatabase() throws Exception {
		GenericAbstractTest.tearDownDatabase();
		entityManager = null;
	}

	protected static void clearDatabase() throws Exception {
		entityManager.initTransaction();
		entityManager.executeNativeQuery("delete from iota;");
		entityManager.executeNativeQuery("delete from gama_delta;");
		entityManager.executeNativeQuery("delete from delta;");
		entityManager.executeNativeQuery("delete from beta;");
		entityManager.executeNativeQuery("delete from alpha;");
		entityManager.executeNativeQuery("delete from gama;");
		entityManager.executeNativeQuery("delete from epsilon;");
		entityManager.executeNativeQuery("delete from omega;");
		entityManager.executeNativeQuery("delete from teta;");

		entityManager.executeNativeQuery("delete from ro;");
		entityManager.executeNativeQuery("delete from tau_pi;");
		entityManager.executeNativeQuery("delete from pi;");
		entityManager.executeNativeQuery("delete from psi;");
		entityManager.executeNativeQuery("delete from sigma;");
		entityManager.executeNativeQuery("delete from tau;");
		entityManager.executeNativeQuery("delete from fi;");
		entityManager.commitTransaction();
	}

	protected Alpha getAlphaEntity() {
		Alpha alpha = new Alpha();
		alpha.setInteger(INTEGER);
		alpha.setBool(BOOLEAN);
		alpha.setDecimal(DECIMAL);
		alpha.setDoubli(DOUBLE);
		alpha.setFloati(FLOAT);
		alpha.setLongi(LONG);
		alpha.setString(STRING);
		alpha.setText(TEXT);
		alpha.setDate(DATE);
		return alpha;
	}

	protected Beta getBetaEntity() {
		Beta beta = new Beta();
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

	protected Gama getGamaEntity() {
		Gama gama = new Gama();
		gama.setInteger(INTEGER);
		gama.setBool(BOOLEAN);
		gama.setDecimal(DECIMAL);
		gama.setDoubli(DOUBLE);
		gama.setFloati(FLOAT);
		gama.setLongi(LONG);
		gama.setString(STRING);
		gama.setText(TEXT);
		return gama;
	}

	protected Delta getDeltaEntity() {
		Delta delta = new Delta();
		delta.setInteger(INTEGER);
		delta.setBool(BOOLEAN);
		delta.setDecimal(DECIMAL);
		delta.setDoubli(DOUBLE);
		delta.setFloati(FLOAT);
		delta.setLongi(LONG);
		delta.setString(STRING);
		delta.setText(TEXT);
		return delta;
	}

	protected Omega getOmegaEntity() {
		Omega omega = new Omega();
		omega.setIdn(ID_SECOND);
		omega.setInteger(INTEGER);
		omega.setBool(BOOLEAN);
		omega.setDecimal(DECIMAL);
		omega.setDoubli(DOUBLE);
		omega.setFloati(FLOAT);
		omega.setLongi(LONG);
		omega.setString(STRING);
		omega.setText(TEXT);
		return omega;
	}

	protected Iota getIotaEntity() {
		Iota iota = new Iota();
		iota.setInteger(INTEGER);
		iota.setBool(BOOLEAN);
		iota.setDecimal(DECIMAL);
		iota.setDoubli(DOUBLE);
		iota.setFloati(FLOAT);
		iota.setLongi(LONG);
		iota.setString(STRING);
		iota.setText(TEXT);
		return iota;
	}

	protected Epsilon getEpsilonEntity() {
		Epsilon epsilon = new Epsilon();
		epsilon.setInteger(INTEGER);
		epsilon.setBool(BOOLEAN);
		epsilon.setDecimal(DECIMAL);
		epsilon.setDoubli(DOUBLE);
		epsilon.setFloati(FLOAT);
		epsilon.setLongi(LONG);
		epsilon.setString(STRING);
		epsilon.setText(TEXT);
		return epsilon;
	}

	protected Tau getTauEntity() {
		Tau tau = new Tau();
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

	protected Fi getFiEntity() {
		Fi fi = new Fi();
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

	protected Pi getPiEntity() {
		Pi pi = new Pi();
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

	protected Psi getPsiEntity() {
		Psi psi = new Psi();
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

	protected Sigma getSigmaEntity() {
		Sigma sigma = new Sigma();
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

	protected Ro getRoEntity() {
		Ro ro = new Ro();
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

	protected Teta getTetaEntity() {
		Teta teta = new Teta();
		teta.setInteger(INTEGER);
		teta.setBool(BOOLEAN);
		teta.setDecimal(DECIMAL);
		teta.setDoubli(DOUBLE);
		teta.setFloati(FLOAT);
		teta.setLongi(LONG);
		teta.setString(STRING);
		teta.setText(TEXT);
		return teta;
	}

}
