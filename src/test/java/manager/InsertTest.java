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

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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

public class InsertTest extends AbstractTest {

	@BeforeClass
	public static void setUpDatabase() throws Exception {
		AbstractTest.setUpDatabase();
	}

	@After
	public void tearDown() throws Exception {
		AbstractTest.clearDatabase();
	}

	@AfterClass
	public static void tearDownDatabase() throws Exception {
		AbstractTest.tearDownDatabase();
	}

	@Test(expected = RuntimeException.class)
	public void testNullEntityInsert() throws Exception {
		entityManager.insertEntity(null);
	}

	@Test
	public void testBasicEntityInsert() throws Exception {
		Beta beta = getBetaEntity();
		entityManager.insertEntity(beta);
		Assert.assertTrue("The beta entity must have an id.", beta.getId() != null);

		beta = (Beta) entityManager.selectSingleEntity(beta);

		Assert.assertTrue("The beta entity must not be null.", beta != null);
		Assert.assertTrue("The beta entity must have an id.", beta.getId() != null);
		Assert.assertTrue("The beta entity must have inserted Integer value.", beta.getInteger().equals(INTEGER));
		Assert.assertTrue("The beta entity must have inserted Boolean value.", beta.getBool().equals(BOOLEAN));
		Assert.assertTrue("The beta entity must have inserted Decimal value.", beta.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The beta entity must have inserted Double value.", beta.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The beta entity must have inserted Float value.", beta.getFloati().equals(FLOAT));
		Assert.assertTrue("The beta entity must have inserted String value.", beta.getString().equals(STRING));
		Assert.assertTrue("The beta entity must have inserted Text value.", beta.getText().equals(TEXT));
	}

	@Test
	public void testSecondIdEntityInsert() throws Exception {
		Omega omega = getOmegaEntity();
		entityManager.insertEntity(omega);
		Assert.assertTrue("The omega entity must have an id.", omega.getId() != null);
	}

	@Test
	public void testIdNotNullEntityInsert() throws Exception {
		Omega omega = getOmegaEntity();
		omega.setId(1l);
		entityManager.insertEntity(omega);

		omega = (Omega) entityManager.selectSingleEntity(omega);
		Assert.assertTrue("The omega entity must not be null.", omega != null);
		Assert.assertTrue("The omega entity must have an id.", omega.getId() != null);
	}

	@Test(expected = RuntimeException.class)
	public void testEmptyEntityInsert() throws Exception {
		entityManager.insertEntity(new Omega());
	}

	@Test
	public void testNullAttributesEntityInsert() throws Exception {
		Beta beta = getBetaEntity();

		beta.setInteger(null);
		beta.setBool(null);
		beta.setDecimal(null);
		beta.setDoubli(null);
		beta.setFloati(null);
		beta.setLongi(null);
		beta.setString(null);
		beta.setText(null);
		
		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		beta = (Beta) entityManager.selectSingleEntity(beta);

		Assert.assertTrue("The beta entity must not be null.", beta != null);
		Assert.assertTrue("The beta entity must have an id.", beta.getId().equals(betaId));
		Assert.assertTrue("The beta entity must have inserted Integer value null.", beta.getInteger() == null);
		Assert.assertTrue("The beta entity must have inserted Boolean value null.", beta.getBool() == null);
		Assert.assertTrue("The beta entity must have inserted Decimal value null.", beta.getDecimal() == null);
		Assert.assertTrue("The beta entity must have inserted Double value null.", beta.getDoubli() == null);
		Assert.assertTrue("The beta entity must have inserted Float value null.", beta.getFloati() == null);
		Assert.assertTrue("The beta entity must have inserted String value null.", beta.getString() == null);
		Assert.assertTrue("The beta entity must have inserted Text value null.", beta.getText() == null);
	}

	@Test
	public void testMoreThanOneIdEntityInsert() throws Exception {
		Teta teta = getTetaEntity();
		teta.setId(1l);
		entityManager.insertEntity(teta);
		Assert.assertTrue("The teta entity must have an second id.", teta.getIdn() != null);
	}

	@Test(expected = RuntimeException.class)
	public void testColumnExceedLengthEntityInsert() throws Exception {
		Beta beta = getBetaEntity();
		beta.setString(STRING_50);
		entityManager.insertEntity(beta);
	}

	@Test
	public void testTransientEntityInsert() throws Exception {
		Beta beta = getBetaEntity();
		beta.setTrans("Transient value");
		entityManager.insertEntity(beta);
		Assert.assertTrue("The beta entity must have an id.", beta.getId() != null);
		
		beta = (Beta) entityManager.selectSingleEntity(beta);
		Assert.assertTrue("The beta entity must not be null.", beta != null);
		Assert.assertTrue("The beta entity must not have transiente value.", beta.getTrans() == null);
	}

	@Test
	public void testJoinEntityInsert() throws Exception {
		Alpha alpha = getAlphaEntity();
		Iota iota = getIotaEntity();
		Epsilon epsilon = getEpsilonEntity();

		iota.setAlpha(alpha);
		iota.setEpsilon(epsilon);
		entityManager.insertEntity(iota);
		Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);
		Assert.assertTrue("The epsilon entity must have an id.", epsilon.getId() != null);

		iota = (Iota) entityManager.selectSingleEntity(iota);

		Assert.assertTrue("The iota entity must not be null.", iota != null);
		Assert.assertTrue("The alpha entity must not be null.", iota.getAlpha() != null);
		Assert.assertTrue("The epsilon entity must not be null.", iota.getEpsilon() != null);
	}

	@Test
	public void testCascadeOneToOneEntityInsert() throws Exception {
		Alpha alpha = getAlphaEntity();
		Gama gama = getGamaEntity();
		alpha.setGama(gama);
		entityManager.insertEntity(alpha);

		Assert.assertTrue("The gama entity must have an id.", gama.getId() != null);
		Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);
	}

	@Test
	public void testCascadeOneToManyEntityInsert() throws Exception {
		Alpha alpha = getAlphaEntity();
		Beta beta = getBetaEntity();
		Beta beta2 = getBetaEntity();
		
		alpha.getBetas().add(beta);
		alpha.getBetas().add(beta2);
		entityManager.insertEntity(alpha);

		Assert.assertTrue("The beta entity must have an id.", beta.getId() != null);
		Assert.assertTrue("The beta2 entity must have an id.", beta2.getId() != null);
		Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);
	}

	@Test
	public void testCascadeManyToManyEntityInsert() throws Exception {
		Gama gama = getGamaEntity();
		Delta delta = getDeltaEntity();
		Delta delta2 = getDeltaEntity();

		gama.getDeltas().add(delta);
		gama.getDeltas().add(delta2);
		entityManager.insertEntity(gama);

		Assert.assertTrue("The delta entity must have an id.", delta.getId() != null);
		Assert.assertTrue("The delta2 entity must have an id.", delta2.getId() != null);
		Assert.assertTrue("The gama entity must have an id.", gama.getId() != null);
	}

	@Test
	public void testCascadeJoinManyToManyEntityInsert() throws Exception {
		Iota iota = getIotaEntity();
		Iota iota2 = getIotaEntity();
		Alpha alpha = getAlphaEntity();
		Alpha alpha2 = getAlphaEntity();
		Epsilon epsilon = getEpsilonEntity();

		iota.setAlpha(alpha);
		iota.setEpsilon(epsilon);

		iota2.setAlpha(alpha2);
		iota2.setEpsilon(epsilon);

		epsilon.getIotas().add(iota);
		epsilon.getIotas().add(iota2);
		entityManager.insertEntity(epsilon);
		Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);
		Assert.assertTrue("The alpha2 entity must have an id.", alpha2.getId() != null);
		Assert.assertTrue("The epsilon entity must have an id.", epsilon.getId() != null);
		
		iota = (Iota) entityManager.selectSingleEntity(iota);

		Assert.assertTrue("The iota entity must not be null.", iota != null);
		Assert.assertTrue("The alpha entity must not be null.", iota.getAlpha() != null);
		Assert.assertTrue("The epsilon entity must not be null.", iota.getEpsilon() != null);
		
		iota2 = (Iota) entityManager.selectSingleEntity(iota2);

		Assert.assertTrue("The iota2 entity must not be null.", iota2 != null);
		Assert.assertTrue("The alpha entity must not be null.", iota2.getAlpha() != null);
		Assert.assertTrue("The epsilon entity must not be null.", iota2.getEpsilon() != null);
	}

	@Test
	public void testCascadeCompleteEntityInsert() throws Exception {
		Tau tau = getTauEntity();
		Fi fi = getFiEntity();
		Pi pi = getPiEntity();
		Pi pi2 = getPiEntity();
		Psi psi = getPsiEntity();
		Psi psi2 = getPsiEntity();
		Ro ro = getRoEntity();
		Ro ro2 = getRoEntity();
		Sigma sigma = getSigmaEntity();
		Sigma sigma2 = getSigmaEntity();

		tau.setFi(fi);

		tau.getPis().add(pi);
		tau.getPis().add(pi2);

		tau.getPsis().add(psi);
		tau.getPsis().add(psi2);
		
		ro.setTau(tau);
		ro.setSigma(sigma);
		ro2.setTau(tau);
		ro2.setSigma(sigma2);

		tau.getRos().add(ro);
		tau.getRos().add(ro2);

		entityManager.insertEntity(tau);
		Assert.assertTrue("The fi entity must have an id.", fi.getId() != null);
		Assert.assertTrue("The pi entity must have an id.", pi.getId() != null);
		Assert.assertTrue("The pi2 entity must have an id.", pi2.getId() != null);
		Assert.assertTrue("The psi entity must have an id.", psi.getId() != null);
		Assert.assertTrue("The psi2 entity must have an id.", psi2.getId() != null);
		Assert.assertTrue("The sigma entity must have an id.", sigma.getId() != null);
		Assert.assertTrue("The sigma2 entity must have an id.", sigma2.getId() != null);
		Assert.assertTrue("The tau entity must have an id.", tau.getId() != null);
	}

	@Test(expected = RuntimeException.class)
	public void testBlockCascadeJoinEntityInsert() throws Exception {
		Iota iota = getIotaEntity();
		Alpha alpha = getAlphaEntity();
		Epsilon epsilon = getEpsilonEntity();
		iota.setAlpha(alpha);
		iota.setEpsilon(epsilon);
		entityManager.insertEntity(iota, true);
	}

	@Test
	public void testBlockCascadeOneToOneEntityInsert() throws Exception {
		Alpha alpha = getAlphaEntity();
		Gama gama = getGamaEntity();
		alpha.setGama(gama);
		entityManager.insertEntity(alpha, true);
		Assert.assertTrue("The gama entity must not have an id.", gama.getId() == null);
		Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);
	}

	@Test
	public void testBlockCascadeOneToManyEntityInsert() throws Exception {
		Alpha alpha = getAlphaEntity();
		Beta beta = getBetaEntity();
		Beta beta2 = getBetaEntity();

		alpha.getBetas().add(beta);
		alpha.getBetas().add(beta2);
		entityManager.insertEntity(alpha, true);

		Assert.assertTrue("The beta entity must not have an id.", beta.getId() == null);
		Assert.assertTrue("The beta2 entity must not have an id.", beta2.getId() == null);
		Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);
	}

	@Test
	public void testBlockCascadeManyToManyEntityInsert() throws Exception {
		Gama gama = getGamaEntity();
		Delta delta = getDeltaEntity();
		Delta delta2 = getDeltaEntity();
		
		gama.getDeltas().add(delta);
		gama.getDeltas().add(delta2);
		entityManager.insertEntity(gama, true);

		Assert.assertTrue("The delta entity must not have an id.", delta.getId() == null);
		Assert.assertTrue("The delta2 entity must not have an id.", delta2.getId() == null);
		Assert.assertTrue("The gama entity must have an id.", gama.getId() != null);
	}

	@Test
	public void testBlockCascadeJoinManyToManyEntityInsert() throws Exception {
		Alpha alpha = getAlphaEntity();
		Alpha alpha2 = getAlphaEntity();
		Iota iota = getIotaEntity();
		Iota iota2 = getIotaEntity();
		Epsilon epsilon = getEpsilonEntity();

		iota.setAlpha(alpha);
		iota.setEpsilon(epsilon);

		iota2.setAlpha(alpha2);
		iota2.setEpsilon(epsilon);

		epsilon.getIotas().add(iota);
		epsilon.getIotas().add(iota2);

		entityManager.insertEntity(epsilon, true);
		Assert.assertTrue("The alpha entity must not have an id.", alpha.getId() == null);
		Assert.assertTrue("The alpha2 entity must not have an id.", alpha2.getId() == null);
		Assert.assertTrue("The epsilon entity must have an id.", epsilon.getId() != null);
		
		iota = (Iota) entityManager.selectSingleEntity(iota);

		Assert.assertTrue("The iota entity must be null.", iota == null);
		
		iota2 = (Iota) entityManager.selectSingleEntity(iota2);

		Assert.assertTrue("The iota2 entity must be null.", iota2 == null);
	}

	@Test
	public void testBlockCascadeCompleteEntityInsert() throws Exception {
		Tau tau = getTauEntity();
		Fi fi = getFiEntity();
		Pi pi = getPiEntity();
		Pi pi2 = getPiEntity();
		Psi psi = getPsiEntity();
		Psi psi2 = getPsiEntity();
		Ro ro = getRoEntity();
		Ro ro2 = getRoEntity();
		Sigma sigma = getSigmaEntity();
		Sigma sigma2 = getSigmaEntity();

		tau.setFi(fi);

		tau.getPis().add(pi);
		tau.getPis().add(pi2);

		tau.getPsis().add(psi);
		tau.getPsis().add(psi2);
		
		ro.setTau(tau);
		ro.setSigma(sigma);
		ro2.setTau(tau);
		ro2.setSigma(sigma2);

		tau.getRos().add(ro);
		tau.getRos().add(ro2);

		entityManager.insertEntity(tau, true);
	
		ro = (Ro) entityManager.selectSingleEntity(ro);
		ro2 = (Ro) entityManager.selectSingleEntity(ro2);
		
		Assert.assertTrue("The ro entity must be null.", ro == null);
		Assert.assertTrue("The ro2 entity must be null.", ro2 == null);

		Assert.assertTrue("The fi entity must not have an id.", fi.getId() == null);
		Assert.assertTrue("The pi entity must not have an id.", pi.getId() == null);
		Assert.assertTrue("The pi2 entity must not have an id.", pi2.getId() == null);
		Assert.assertTrue("The psi entity must not have an id.", psi.getId() == null);
		Assert.assertTrue("The psi2 entity must not have an id.", psi2.getId() == null);
		Assert.assertTrue("The sigma entity must not have an id.", sigma.getId() == null);
		Assert.assertTrue("The sigma2 entity must not have an id.", sigma2.getId() == null);
		Assert.assertTrue("The tau entity must have an id.", tau.getId() != null);
	}

	@Test
	public void testNonTransactionalCommitEntityInsert() throws Exception {
		Alpha alpha = getAlphaEntity();
		Alpha alpha2 = getAlphaEntity();
		Gama gama = getGamaEntity();
		Gama gama2 = getGamaEntity();
		Beta beta = getBetaEntity();
		Beta beta2 = getBetaEntity();
		Delta delta = getDeltaEntity();
		Delta delta2 = getDeltaEntity();
		Epsilon epsilon = getEpsilonEntity();
		Iota iota = getIotaEntity();

		alpha.setGama(gama);
		alpha.getBetas().add(beta);
		alpha.getBetas().add(beta2);
		entityManager.insertEntity(alpha);

		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		Assert.assertTrue("The gama entity must be returned.", entityManager.selectSingleEntity(gama) != null);

		Assert.assertTrue("The beta entity must be returned.", entityManager.selectSingleEntity(beta) != null);
		Assert.assertTrue("The beta2 entity must be returned.", entityManager.selectSingleEntity(beta2) != null);
		Assert.assertTrue("The alpha entity must be returned.", entityManager.selectSingleEntity(alpha) != null);

		Assert.assertTrue("The delta entity must be returned.", entityManager.selectSingleEntity(delta) != null);
		Assert.assertTrue("The delta2 entity must be returned.", entityManager.selectSingleEntity(delta2) != null);
		Assert.assertTrue("The gama2 entity must be returned.", entityManager.selectSingleEntity(gama2) != null);

		Assert.assertTrue("The alpha2 entity must be returned.", entityManager.selectSingleEntity(alpha2) != null);
		Assert.assertTrue("The epsilon entity must be returned.", entityManager.selectSingleEntity(epsilon) != null);

		Assert.assertTrue("The iota entity must be returned.", entityManager.selectSingleEntity(iota) != null);
	}

	@Test
	public void testTransactionalCommitEntityInsert() throws Exception {
		entityManager.initTransaction();

		Alpha alpha = getAlphaEntity();
		Alpha alpha2 = getAlphaEntity();
		Gama gama = getGamaEntity();
		Gama gama2 = getGamaEntity();
		Beta beta = getBetaEntity();
		Beta beta2 = getBetaEntity();
		Delta delta = getDeltaEntity();
		Delta delta2 = getDeltaEntity();
		Epsilon epsilon = getEpsilonEntity();
		Iota iota = getIotaEntity();

		alpha.setGama(gama);
		alpha.getBetas().add(beta);
		alpha.getBetas().add(beta2);
		entityManager.insertEntity(alpha);

		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		Assert.assertTrue("The gama entity must must be returned.", entityManager.selectSingleEntity(gama) != null);

		Assert.assertTrue("The beta entity must must be returned.", entityManager.selectSingleEntity(beta) != null);
		Assert.assertTrue("The beta2 entity must must be returned.", entityManager.selectSingleEntity(beta2) != null);
		Assert.assertTrue("The alpha entity must must be returned.", entityManager.selectSingleEntity(alpha) != null);

		Assert.assertTrue("The delta entity must must be returned.", entityManager.selectSingleEntity(delta) != null);
		Assert.assertTrue("The delta2 entity must must be returned.", entityManager.selectSingleEntity(delta2) != null);
		Assert.assertTrue("The gama2 entity must must be returned.", entityManager.selectSingleEntity(gama2) != null);

		Assert.assertTrue("The alpha2 entity must must be returned.", entityManager.selectSingleEntity(alpha2) != null);
		Assert.assertTrue("The epsilon entity must must be returned.", entityManager.selectSingleEntity(epsilon) != null);

		Assert.assertTrue("The iota entity must be returned.", entityManager.selectSingleEntity(iota) != null);

		entityManager.commitTransaction();
	}

	@Test
	public void testReopenedTransactionalCommitEntityInsert() throws Exception {
		entityManager.initTransaction();

		Alpha alpha = getAlphaEntity();
		Alpha alpha2 = getAlphaEntity();
		Gama gama = getGamaEntity();
		Gama gama2 = getGamaEntity();
		Beta beta = getBetaEntity();
		Beta beta2 = getBetaEntity();
		Delta delta = getDeltaEntity();
		Delta delta2 = getDeltaEntity();
		Epsilon epsilon = getEpsilonEntity();
		Iota iota = getIotaEntity();

		alpha.setGama(gama);
		alpha.getBetas().add(beta);
		alpha.getBetas().add(beta2);
		entityManager.insertEntity(alpha);

		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		entityManager.commitTransaction();

		entityManager.initTransaction();

		Assert.assertTrue("The gama entity must be returned.", entityManager.selectSingleEntity(gama) != null);

		Assert.assertTrue("The beta entity must be returned.", entityManager.selectSingleEntity(beta) != null);
		Assert.assertTrue("The beta2 entity must be returned.", entityManager.selectSingleEntity(beta2) != null);
		Assert.assertTrue("The alpha entity must be returned.", entityManager.selectSingleEntity(alpha) != null);

		Assert.assertTrue("The delta entity must be returned.", entityManager.selectSingleEntity(delta) != null);
		Assert.assertTrue("The delta2 entity must be returned.", entityManager.selectSingleEntity(delta2) != null);
		Assert.assertTrue("The gama2 entity must be returned.", entityManager.selectSingleEntity(gama2) != null);

		Assert.assertTrue("The alpha2 entity must be returned.", entityManager.selectSingleEntity(alpha2) != null);
		Assert.assertTrue("The epsilon entity must be returned.", entityManager.selectSingleEntity(epsilon) != null);

		Assert.assertTrue("The iota entity must be returned.", entityManager.selectSingleEntity(iota) != null);

		entityManager.rollbackTransaction();
	}

	@Test
	public void testTranscationalRollbackEntityInsert() throws Exception {
		entityManager.initTransaction();

		Alpha alpha = getAlphaEntity();
		Alpha alpha2 = getAlphaEntity();
		Gama gama = getGamaEntity();
		Gama gama2 = getGamaEntity();
		Beta beta = getBetaEntity();
		Beta beta2 = getBetaEntity();
		Delta delta = getDeltaEntity();
		Delta delta2 = getDeltaEntity();
		Epsilon epsilon = getEpsilonEntity();
		Iota iota = getIotaEntity();

		alpha.setGama(gama);

		alpha.getBetas().add(beta);
		alpha.getBetas().add(beta2);
		entityManager.insertEntity(alpha);

		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		entityManager.rollbackTransaction();

		entityManager.initTransaction();

		Assert.assertTrue("The gama entity must not be returned.", entityManager.selectSingleEntity(gama) == null);

		Assert.assertTrue("The beta entity must not be returned.", entityManager.selectSingleEntity(beta) == null);
		Assert.assertTrue("The beta2 entity must not be returned.", entityManager.selectSingleEntity(beta2) == null);
		Assert.assertTrue("The alpha entity must not be returned.", entityManager.selectSingleEntity(alpha) == null);

		Assert.assertTrue("The delta entity must not be returned.", entityManager.selectSingleEntity(delta) == null);
		Assert.assertTrue("The delta2 entity must not be returned.", entityManager.selectSingleEntity(delta2) == null);
		Assert.assertTrue("The gama2 entity must not be returned.", entityManager.selectSingleEntity(gama2) == null);

		Assert.assertTrue("The alpha2 entity must not be returned.", entityManager.selectSingleEntity(alpha2) == null);
		Assert.assertTrue("The epsilon entity must not be returned.", entityManager.selectSingleEntity(epsilon) == null);
	
		Assert.assertTrue("The iota entity must not be returned.", entityManager.selectSingleEntity(iota) == null);

		entityManager.rollbackTransaction();
	}

}
