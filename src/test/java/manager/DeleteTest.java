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

public class DeleteTest extends AbstractTest {

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
	public void testNullEntityDelete() throws Exception {
		entityManager.deleteEntity(null);
	}

	@Test
	public void testBasicEntityDelete() throws Exception {
		Beta beta = getBetaEntity();
		
		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		entityManager.deleteEntity(beta);

		beta = (Beta) entityManager.selectSingleEntity(beta);

		Assert.assertTrue("The beta entity must be null.", beta == null);
	}

	@Test
	public void testBasicEntityOnlyWithIdDelete() throws Exception {
		Beta beta = getBetaEntity();

		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		beta = new Beta();
		beta.setId(betaId);
		entityManager.deleteEntity(beta);

		beta = (Beta) entityManager.selectSingleEntity(beta);

		Assert.assertTrue("The beta entity must be null.", beta == null);
	}

	@Test
	public void testSecondIdEntityDelete() throws Exception {
		Omega omega = getOmegaEntity();
		
		entityManager.insertEntity(omega);
		Long omegaId = omega.getId();
		Assert.assertTrue("The omega entity must have an id.", omegaId != null);

		entityManager.deleteEntity(omega);

		omega = (Omega) entityManager.selectSingleEntity(omega);

		Assert.assertTrue("The omega entity must be null.", omega == null);
	}

	@Test
	public void testIdNullEntityDelete() throws Exception {
		Omega omega = getOmegaEntity();

		entityManager.insertEntity(omega);
		Long omegaId = omega.getId();
		Assert.assertTrue("The omega entity must have an id.", omegaId != null);
		
		omega.setId(null);
		entityManager.deleteEntity(omega);

		omega = (Omega) entityManager.selectSingleEntity(omega);

		Assert.assertTrue("The omega entity must be null.", omega == null);
	}

	@Test(expected = RuntimeException.class)
	public void testEmptyEntityDelete() throws Exception {
		Omega omega = getOmegaEntity();
		
		entityManager.insertEntity(omega);
		Long omegaId = omega.getId();
		Assert.assertTrue("The omega entity must have an id.", omegaId != null);
		
		omega = new Omega();
		entityManager.deleteEntity(omega);
	}

	@Test
	public void testNullAttributesEntityDelete() throws Exception {
		Beta beta = getBetaEntity();
		
		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		beta.setInteger(null);
		beta.setBool(null);
		beta.setDecimal(null);
		beta.setDoubli(null);
		beta.setFloati(null);
		beta.setLongi(null);
		beta.setString(null);
		beta.setText(null);

		entityManager.deleteEntity(beta);

		beta = (Beta) entityManager.selectSingleEntity(beta);

		Assert.assertTrue("The beta entity must be null.", beta == null);
	}

	@Test
	public void testMoreThanOnePkIdEntityDelete() throws Exception {
		// TODO: Allow more than one pk id. // Use teta entity
	}

	@Test(expected = RuntimeException.class)
	public void testColumnExceedLengthEntityDelete() throws Exception {
		Beta beta = getBetaEntity();

		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		beta.setString(STRING_50);
		entityManager.deleteEntity(beta);
	}

	@Test
	public void testTransientEntityDelete() throws Exception {
		Beta beta = getBetaEntity();
		
		beta.setTrans("Transient value");
		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		beta.setTrans("Transient value updated");
		entityManager.deleteEntity(beta);

		beta = (Beta) entityManager.selectSingleEntity(beta);

		Assert.assertTrue("The beta entity must be null.", beta == null);
	}

	@Test(expected = RuntimeException.class)
	public void testOnlyTransientEntityDelete() throws Exception {
		Beta beta = getBetaEntity();
		
		beta.setTrans("Transient value");
		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		beta = new Beta();
		beta.setTrans("Transient value updated");
		entityManager.deleteEntity(beta);

		beta = (Beta) entityManager.selectSingleEntity(beta);
	}

	@Test
	public void testJoinEntityDelete() throws Exception {
		Alpha alpha = getAlphaEntity();
		Epsilon epsilon = getEpsilonEntity();
		Iota iota = getIotaEntity();
		
		iota.setAlpha(alpha);
		iota.setEpsilon(epsilon);
		entityManager.insertEntity(iota);
		
		Integer alphaId = alpha.getId();
		Integer epsilonId = epsilon.getId();
		Assert.assertTrue("The alpha entity must have an id.", alphaId != null);
		Assert.assertTrue("The epsilon entity must have an id.", epsilonId != null);

		entityManager.deleteEntity(iota);

		iota = (Iota) entityManager.selectSingleEntity(iota);
		epsilon = (Epsilon) entityManager.selectSingleEntity(epsilon);
		alpha = (Alpha) entityManager.selectSingleEntity(alpha);

		Assert.assertTrue("The iota entity must be null.", iota == null);
		Assert.assertTrue("The epsilon entity must be null.", epsilon == null);
		Assert.assertTrue("The alpha entity must be null.", alpha == null);
	}

	@Test
	public void testCascadeOneToOneEntityDelete() throws Exception {
		Alpha alpha = getAlphaEntity();
		Gama gama = getGamaEntity();
		
		alpha.setGama(gama);
		entityManager.insertEntity(alpha);

		Integer alphaId = alpha.getId();
		Long gamaId = gama.getId();

		Assert.assertTrue("The gama entity must have an id.", gamaId != null);
		Assert.assertTrue("The alpha entity must have an id.", alphaId != null);

		entityManager.deleteEntity(alpha);

		alpha = (Alpha) entityManager.selectSingleEntity(alpha);
		gama = (Gama) entityManager.selectSingleEntity(gama);

		Assert.assertTrue("The alpha entity must be null.", alpha == null);
		Assert.assertTrue("The gama entity must be null.", gama == null);
	}

	@Test
	public void testCascadeOneToManyEntityDelete() throws Exception {
		Alpha alpha = getAlphaEntity();
		Beta beta = getBetaEntity();
		Beta beta2 = getBetaEntity();
		
		alpha.getBetas().add(beta);
		alpha.getBetas().add(beta2);
		entityManager.insertEntity(alpha);

		Integer betaId = beta.getId();
		Integer beta2Id = beta2.getId();
		Integer alphaId = alpha.getId();

		Assert.assertTrue("The beta entity must have an id.", betaId != null);
		Assert.assertTrue("The beta2 entity must have an id.", beta2Id != null);
		Assert.assertTrue("The alpha entity must have an id.", alphaId != null);

		entityManager.deleteEntity(alpha);

		alpha = (Alpha) entityManager.selectSingleEntity(alpha);
		beta = (Beta) entityManager.selectSingleEntity(beta);
		beta2 = (Beta) entityManager.selectSingleEntity(beta2);

		Assert.assertTrue("The alpha entity must be null.", alpha == null);
		Assert.assertTrue("The beta entity must be null.", beta == null);
		Assert.assertTrue("The beta2 entity must be null.", beta2 == null);
	}

	@Test
	public void testCascadeManyToManyEntityDelete() throws Exception {
		Gama gama = getGamaEntity();
		Delta delta = getDeltaEntity();
		Delta delta2 = getDeltaEntity();
		
		gama.getDeltas().add(delta);
		gama.getDeltas().add(delta2);
		entityManager.insertEntity(gama);

		Long gamaId = gama.getId();
		Integer deltaId = delta.getId();
		Integer delta2Id = delta2.getId();

		Assert.assertTrue("The delta entity must have an id.", deltaId != null);
		Assert.assertTrue("The delta2 entity must have an id.", delta2Id != null);
		Assert.assertTrue("The gama entity must have an id.", gamaId != null);

		entityManager.deleteEntity(gama);

		gama = (Gama) entityManager.selectSingleEntity(gama);
		delta = (Delta) entityManager.selectSingleEntity(delta);
		delta2 = (Delta) entityManager.selectSingleEntity(delta2);

		Assert.assertTrue("The gama entity must be null.", gama == null);
		Assert.assertTrue("The delta entity must be null.", delta == null);
		Assert.assertTrue("The delta2 entity must be null.", delta2 == null);
	}

	@Test
	public void testCascadeJoinManyToManyEntityDelete() throws Exception {
		Alpha alpha = getAlphaEntity();
		Alpha alpha2 = getAlphaEntity();
		Epsilon epsilon = getEpsilonEntity();
		Iota iota = getIotaEntity();
		Iota iota2 = getIotaEntity();
		
		iota.setAlpha(alpha);
		iota.setEpsilon(epsilon);

		iota2.setAlpha(alpha2);
		iota2.setEpsilon(epsilon);

		epsilon.getIotas().add(iota);
		epsilon.getIotas().add(iota2);
		entityManager.insertEntity(epsilon);

		Integer alphaId = alpha.getId();
		Integer alpha2Id = alpha2.getId();
		Integer epsilonId = epsilon.getId();

		Assert.assertTrue("The alpha entity must have an id.", alphaId != null);
		Assert.assertTrue("The alpha2 entity must have an id.", alpha2Id != null);
		Assert.assertTrue("The epsilon entity must have an id.", epsilonId != null);

		entityManager.deleteEntity(epsilon);

		epsilon = (Epsilon) entityManager.selectSingleEntity(epsilon);
		alpha = (Alpha) entityManager.selectSingleEntity(alpha);
		alpha2 = (Alpha) entityManager.selectSingleEntity(alpha2);
		iota = (Iota) entityManager.selectSingleEntity(iota);
		iota2 = (Iota) entityManager.selectSingleEntity(iota2);

		Assert.assertTrue("The epsilon entity must be null.", epsilon == null);
		Assert.assertTrue("The alpha entity must be null.", alpha == null);
		Assert.assertTrue("The alpha2 entity must be null.", alpha2 == null);
		Assert.assertTrue("The iota entity must be null.", iota == null);
		Assert.assertTrue("The iota2 entity must be null.", iota2 == null);
	}

	@Test
	public void testCascadeCompleteEntityDelete() throws Exception {
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

		entityManager.deleteEntity(tau);

		tau = (Tau) entityManager.selectSingleEntity(tau);
		fi = (Fi) entityManager.selectSingleEntity(fi);
		pi = (Pi) entityManager.selectSingleEntity(pi);
		pi2 = (Pi) entityManager.selectSingleEntity(pi2);
		psi = (Psi) entityManager.selectSingleEntity(psi);
		psi2 = (Psi) entityManager.selectSingleEntity(psi2);
		sigma = (Sigma) entityManager.selectSingleEntity(sigma);
		sigma2 = (Sigma) entityManager.selectSingleEntity(sigma2);
		ro = (Ro) entityManager.selectSingleEntity(ro);
		ro2 = (Ro) entityManager.selectSingleEntity(ro2);

		Assert.assertTrue("The tau entity must be null.", tau == null);
		Assert.assertTrue("The fi entity must be null.", fi == null);
		Assert.assertTrue("The pi entity must be null.", pi == null);
		Assert.assertTrue("The pi2 entity must be null.", pi2 == null);
		Assert.assertTrue("The psi entity must be null.", psi == null);
		Assert.assertTrue("The psi2 entity must be null.", psi2 == null);
		Assert.assertTrue("The sigma entity must be null.", sigma == null);
		Assert.assertTrue("The sigma2 entity must be null.", sigma2 == null);
		Assert.assertTrue("The ro entity must be null.", ro == null);
		Assert.assertTrue("The ro2 entity must be null.", ro2 == null);
	}

	@Test
	public void testBlockJoinEntityDelete() throws Exception {
		Alpha alpha = getAlphaEntity();
		Epsilon epsilon = getEpsilonEntity();
		Iota iota = getIotaEntity();

		iota.setAlpha(alpha);
		iota.setEpsilon(epsilon);
		entityManager.insertEntity(iota);
		
		Integer alphaId = alpha.getId();
		Integer epsilonId = epsilon.getId();
		Assert.assertTrue("The alpha entity must have an id.", alphaId != null);
		Assert.assertTrue("The epsilon entity must have an id.", epsilonId != null);

		entityManager.deleteEntity(iota, true);

		iota = (Iota) entityManager.selectSingleEntity(iota);
		epsilon = (Epsilon) entityManager.selectSingleEntity(epsilon);
		alpha = (Alpha) entityManager.selectSingleEntity(alpha);

		Assert.assertTrue("The iota entity must be null.", iota == null);
		Assert.assertTrue("The epsilon entity must not be null.", epsilon != null);
		Assert.assertTrue("The alpha entity must not be null.", alpha != null);
	}

	@Test
	public void testBlockCascadeOneToOneEntityDelete() throws Exception {
		Alpha alpha = getAlphaEntity();
		Gama gama = getGamaEntity();

		alpha.setGama(gama);
		entityManager.insertEntity(alpha);

		Integer alphaId = alpha.getId();
		Long gamaId = gama.getId();

		Assert.assertTrue("The gama entity must have an id.", gamaId != null);
		Assert.assertTrue("The alpha entity must have an id.", alphaId != null);

		entityManager.deleteEntity(alpha, true);

		alpha = (Alpha) entityManager.selectSingleEntity(alpha);
		gama = (Gama) entityManager.selectSingleEntity(gama);

		Assert.assertTrue("The alpha entity must be null.", alpha == null);
		Assert.assertTrue("The gama entity must not be null.", gama != null);
	}

	@Test(expected = RuntimeException.class)
	public void testBlockCascadeOneToManyEntityDelete() throws Exception {
		Alpha alpha = getAlphaEntity();
		Beta beta = getBetaEntity();
		Beta beta2 = getBetaEntity();

		alpha.getBetas().add(beta);
		alpha.getBetas().add(beta2);
		entityManager.insertEntity(alpha);

		Integer betaId = beta.getId();
		Integer beta2Id = beta2.getId();
		Integer alphaId = alpha.getId();

		Assert.assertTrue("The beta entity must have an id.", betaId != null);
		Assert.assertTrue("The beta2 entity must have an id.", beta2Id != null);
		Assert.assertTrue("The alpha entity must have an id.", alphaId != null);

		entityManager.deleteEntity(alpha, true);
	}

	@Test(expected = RuntimeException.class)
	public void testBlockCascadeManyToManyEntityDelete() throws Exception {
		Gama gama = getGamaEntity();
		Delta delta = getDeltaEntity();
		Delta delta2 = getDeltaEntity();
		
		gama.getDeltas().add(delta);
		gama.getDeltas().add(delta2);
		entityManager.insertEntity(gama);

		Long gamaId = gama.getId();
		Integer deltaId = delta.getId();
		Integer delta2Id = delta2.getId();

		Assert.assertTrue("The delta entity must have an id.", deltaId != null);
		Assert.assertTrue("The delta2 entity must have an id.", delta2Id != null);
		Assert.assertTrue("The gama entity must have an id.", gamaId != null);

		entityManager.deleteEntity(gama, true);
	}

	@Test(expected = RuntimeException.class)
	public void testBlockCascadeJoinManyToManyEntityDelete() throws Exception {
		Alpha alpha = getAlphaEntity();
		Alpha alpha2 = getAlphaEntity();
		Epsilon epsilon = getEpsilonEntity();
		Iota iota = getIotaEntity();
		Iota iota2 = getIotaEntity();

		iota.setAlpha(alpha);
		iota.setEpsilon(epsilon);

		iota2.setAlpha(alpha2);
		iota2.setEpsilon(epsilon);

		epsilon.getIotas().add(iota);
		epsilon.getIotas().add(iota2);
		entityManager.insertEntity(epsilon);

		Integer alphaId = alpha.getId();
		Integer alpha2Id = alpha2.getId();
		Integer epsilonId = epsilon.getId();

		Assert.assertTrue("The alpha entity must have an id.", alphaId != null);
		Assert.assertTrue("The alpha2 entity must have an id.", alpha2Id != null);
		Assert.assertTrue("The epsilon entity must have an id.", epsilonId != null);

		entityManager.deleteEntity(epsilon, true);
	}

	@Test(expected = RuntimeException.class)
	public void testBlockCascadeCompleteEntityDelete() throws Exception {
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

		entityManager.deleteEntity(tau, true);
	}

	@Test
	public void testNonTransactionalCommitEntityDelete() throws Exception {
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

		entityManager.deleteEntity(alpha);


		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		entityManager.deleteEntity(gama2);


		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		entityManager.deleteEntity(epsilon);


		Assert.assertTrue("The gama entity must not be returned.", entityManager.selectSingleEntity(gama) == null);

		Assert.assertTrue("The beta entity must not be returned.", entityManager.selectSingleEntity(beta) == null);
		Assert.assertTrue("The beta2 entity must not be returned.", entityManager.selectSingleEntity(beta2) == null);
		Assert.assertTrue("The alpha entity must not be returned.", entityManager.selectSingleEntity(alpha) == null);

		Assert.assertTrue("The delta entity must not be returned.", entityManager.selectSingleEntity(delta) == null);
		Assert.assertTrue("The delta2 entity must not be returned.", entityManager.selectSingleEntity(delta2) == null);
		Assert.assertTrue("The gama2 entity must not be returned.", entityManager.selectSingleEntity(gama2) == null);

		Assert.assertTrue("The alpha2 entity must not be returned.", entityManager.selectSingleEntity(alpha2) == null);
		Assert.assertTrue("The epsilon entity must not be returned.", entityManager.selectSingleEntity(epsilon) == null);
	}

	@Test
	public void testTransactionalCommitStepEntityDelete() throws Exception {
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
		entityManager.commitTransaction();

		entityManager.initTransaction();
		entityManager.deleteEntity(alpha);
		entityManager.commitTransaction();


		entityManager.initTransaction();
		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);
		entityManager.commitTransaction();

		entityManager.initTransaction();
		entityManager.deleteEntity(gama2);
		entityManager.commitTransaction();


		entityManager.initTransaction();
		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);
		entityManager.commitTransaction();

		entityManager.initTransaction();
		entityManager.deleteEntity(epsilon);
		entityManager.commitTransaction();

		Assert.assertTrue("The gama entity must not be returned.", entityManager.selectSingleEntity(gama) == null);

		Assert.assertTrue("The beta entity must not be returned.", entityManager.selectSingleEntity(beta) == null);
		Assert.assertTrue("The beta2 entity must not be returned.", entityManager.selectSingleEntity(beta2) == null);
		Assert.assertTrue("The alpha entity must not be returned.", entityManager.selectSingleEntity(alpha) == null);

		Assert.assertTrue("The delta entity must not be returned.", entityManager.selectSingleEntity(delta) == null);
		Assert.assertTrue("The delta2 entity must not be returned.", entityManager.selectSingleEntity(delta2) == null);
		Assert.assertTrue("The gama2 entity must not be returned.", entityManager.selectSingleEntity(gama2) == null);

		Assert.assertTrue("The alpha2 entity must not be returned.", entityManager.selectSingleEntity(alpha2) == null);
		Assert.assertTrue("The epsilon entity must not be returned.", entityManager.selectSingleEntity(epsilon) == null);
	}

	@Test
	public void testTransactionalRollbackStepEntityDelete() throws Exception {
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
		entityManager.commitTransaction();

		entityManager.initTransaction();
		entityManager.deleteEntity(alpha);
		entityManager.rollbackTransaction();


		entityManager.initTransaction();
		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);
		entityManager.commitTransaction();

		entityManager.initTransaction();
		entityManager.deleteEntity(gama2);
		entityManager.rollbackTransaction();


		entityManager.initTransaction();
		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);
		entityManager.commitTransaction();

		entityManager.initTransaction();
		entityManager.deleteEntity(epsilon);
		entityManager.rollbackTransaction();

		Assert.assertTrue("The gama entity must be returned.", entityManager.selectSingleEntity(gama) != null);

		Assert.assertTrue("The beta entity must be returned.", entityManager.selectSingleEntity(beta) != null);
		Assert.assertTrue("The beta2 entity must be returned.", entityManager.selectSingleEntity(beta2) != null);
		Assert.assertTrue("The alpha entity must be returned.", entityManager.selectSingleEntity(alpha) != null);

		Assert.assertTrue("The delta entity must be returned.", entityManager.selectSingleEntity(delta) != null);
		Assert.assertTrue("The delta2 entity must be returned.", entityManager.selectSingleEntity(delta2) != null);
		Assert.assertTrue("The gama2 entity must be returned.", entityManager.selectSingleEntity(gama2) != null);

		Assert.assertTrue("The alpha2 entity must be returned.", entityManager.selectSingleEntity(alpha2) != null);
		Assert.assertTrue("The epsilon entity must be returned.", entityManager.selectSingleEntity(epsilon) != null);
	}

	@Test
	public void testTransactionalCommitEntityDelete() throws Exception {
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

		entityManager.deleteEntity(alpha);


		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		entityManager.deleteEntity(gama2);


		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		entityManager.deleteEntity(epsilon);


		Assert.assertTrue("The gama entity must not be returned.", entityManager.selectSingleEntity(gama) == null);

		Assert.assertTrue("The beta entity must not be returned.", entityManager.selectSingleEntity(beta) == null);
		Assert.assertTrue("The beta2 entity must not be returned.", entityManager.selectSingleEntity(beta2) == null);
		Assert.assertTrue("The alpha entity must not be returned.", entityManager.selectSingleEntity(alpha) == null);

		Assert.assertTrue("The delta entity must not be returned.", entityManager.selectSingleEntity(delta) == null);
		Assert.assertTrue("The delta2 entity must not be returned.", entityManager.selectSingleEntity(delta2) == null);
		Assert.assertTrue("The gama2 entity must not be returned.", entityManager.selectSingleEntity(gama2) == null);

		Assert.assertTrue("The alpha2 entity must not be returned.", entityManager.selectSingleEntity(alpha2) == null);
		Assert.assertTrue("The epsilon entity must not be returned.", entityManager.selectSingleEntity(epsilon) == null);

		entityManager.commitTransaction();
	}

	@Test
	public void testReopenedTransactionalCommitEntityDelete() throws Exception {
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

		entityManager.deleteEntity(alpha);


		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		entityManager.deleteEntity(gama2);


		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		entityManager.deleteEntity(epsilon);

		entityManager.commitTransaction();

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

		entityManager.rollbackTransaction();
	}

	@Test
	public void testTranscationalRollbackEntityDelete() throws Exception {
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

		entityManager.deleteEntity(alpha);


		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		entityManager.deleteEntity(gama2);


		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		entityManager.deleteEntity(epsilon);

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

		entityManager.rollbackTransaction();
	}

}
