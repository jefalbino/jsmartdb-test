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

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entity.Alpha;
import entity.Beta;
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

@SuppressWarnings("unchecked")
public class BatchDeleteTest extends AbstractTest {

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
	public void testNullListEntityBatchDelete() throws Exception {
		entityManager.deleteBatchEntity(null);
	}

	@Test
	public void testBasicListEntityBatchDelete() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			betas.add(getBetaEntity());
		}

		entityManager.insertBatchEntity(betas);

		entityManager.deleteBatchEntity(betas);

		betas = (List<Beta>) entityManager.selectEntity(getBetaEntity());

		Assert.assertTrue("The list of entities must be empty", betas.isEmpty());
	}

	@Test
	public void testBasicEntityOnlyWithIdListEntityBatchDelete() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			betas.add(getBetaEntity());
		}

		entityManager.insertBatchEntity(betas);

		List<Beta> betax = new ArrayList<Beta>();
		for (Beta beta : betas) {
			Beta b = new Beta();
			b.setId(beta.getId());
			betax.add(b);
		}

		entityManager.deleteBatchEntity(betax);

		betas = (List<Beta>) entityManager.selectEntity(getBetaEntity());

		Assert.assertTrue("The list of entities must be empty", betas.isEmpty());
	}

	@Test
	public void testSecondIdListEntityBatchDelete() throws Exception {
		List<Omega> omegas = new ArrayList<Omega>();

		for (int i = 0; i < 100; i++) {
			omegas.add(getOmegaEntity());
		}

		entityManager.insertBatchEntity(omegas);

		entityManager.deleteBatchEntity(omegas);

		omegas = (List<Omega>) entityManager.selectEntity(getOmegaEntity());

		Assert.assertTrue("The list of entities must be empty", omegas.isEmpty());
	}

	@Test
	public void testIdNullListEntityBatchDelete() throws Exception {
		List<Omega> omegas = new ArrayList<Omega>();

		for (int i = 0; i < 100; i++) {
			omegas.add(getOmegaEntity());
		}

		entityManager.insertBatchEntity(omegas);

		for (Omega omega : omegas) {
			omega.setId(null);
		}

		entityManager.deleteBatchEntity(omegas);

		omegas = (List<Omega>) entityManager.selectEntity(getOmegaEntity());

		Assert.assertTrue("The list of entities must be empty", omegas.isEmpty());
	}

	@Test(expected = RuntimeException.class)
	public void testEmptyListEntityBatchDelete() throws Exception {
		List<Omega> omegas = new ArrayList<Omega>();

		for (int i = 0; i < 100; i++) {
			omegas.add(getOmegaEntity());
		}

		entityManager.insertBatchEntity(omegas);

		omegas.clear();
		for (int i = 0; i < 100; i++) {
			omegas.add(new Omega());
		}

		entityManager.deleteBatchEntity(omegas);
	}

	@Test
	public void testNullAttributesListEntityBatchDelete() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			betas.add(getBetaEntity());
		}

		entityManager.insertBatchEntity(betas);

		for (Beta beta : betas) {
			beta.setInteger(null);
			beta.setBool(null);
			beta.setDecimal(null);
			beta.setDoubli(null);
			beta.setFloati(null);
			beta.setLongi(null);
			beta.setString(null);
			beta.setText(null);
		}

		entityManager.deleteBatchEntity(betas);

		betas = (List<Beta>) entityManager.selectEntity(getBetaEntity());

		Assert.assertTrue("The list of entities must be empty", betas.isEmpty());
	}

	@Test
	public void testMoreThanOnePkIdListEntityBatchDelete() throws Exception {
		// TODO: Allow more than one pk id. // Use teta entity
	}

	@Test(expected = RuntimeException.class)
	public void testColumnExceedLengthListEntityBatchDelete() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			betas.add(getBetaEntity());
		}

		entityManager.insertBatchEntity(betas);

		for (Beta beta : betas) {
			beta.setString(STRING_50);
		}

		entityManager.deleteBatchEntity(betas);
	}

	@Test
	public void testTransientListEntityBatchDelete() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			betas.add(getBetaEntity());
		}

		entityManager.insertBatchEntity(betas);

		for (Beta beta : betas) {
			beta.setTrans("Transient value updated");
		}

		entityManager.deleteBatchEntity(betas);

		betas = (List<Beta>) entityManager.selectEntity(getBetaEntity());

		Assert.assertTrue("The list of entities must be empty", betas.isEmpty());
	}

	@Test
	public void testJoinListEntityBatchDelete() throws Exception {
		List<Iota> iotas = new ArrayList<Iota>();

		for (int i = 0; i < 100; i++) {
			Iota iota = getIotaEntity();
			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(getEpsilonEntity());
			iotas.add(iota);
		}

		entityManager.insertBatchEntity(iotas);

		entityManager.deleteBatchEntity(iotas);

		iotas = (List<Iota>) entityManager.selectEntity(getIotaEntity());

		Assert.assertTrue("The list of entities must be empty", iotas.isEmpty());
	}

	@Test
	public void testCascadeOneToOneListEntityBatchDelete() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setGama(getGamaEntity());
			alphas.add(alpha);
		}

		entityManager.insertBatchEntity(alphas);

		entityManager.deleteBatchEntity(alphas);

		alphas = (List<Alpha>) entityManager.selectEntity(getAlphaEntity());

		Assert.assertTrue("The list of entities must be empty", alphas.isEmpty());
	}

	@Test
	public void testCascadeOneToManyListEntityBatchDelete() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.getBetas().add(getBetaEntity());
			alpha.getBetas().add(getBetaEntity());
			alphas.add(alpha);
		}
		
		entityManager.insertBatchEntity(alphas);

		entityManager.deleteBatchEntity(alphas);

		alphas = (List<Alpha>) entityManager.selectEntity(getAlphaEntity());

		Assert.assertTrue("The list of entities must be empty", alphas.isEmpty());
	}

	@Test
	public void testCascadeManyToManyListEntityBatchDelete() throws Exception {
		List<Gama> gamas = new ArrayList<Gama>();

		for (int i = 0; i < 100; i++) {
			Gama gama = getGamaEntity();
			gama.getDeltas().add(getDeltaEntity());
			gama.getDeltas().add(getDeltaEntity());
			gamas.add(gama);
		}

		entityManager.insertBatchEntity(gamas);

		entityManager.deleteBatchEntity(gamas);

		gamas = (List<Gama>) entityManager.selectEntity(getGamaEntity());

		Assert.assertTrue("The list of entities must be empty", gamas.isEmpty());
	}

	@Test
	public void testCascadeJoinManyToManyListEntityBatchDelete() throws Exception {
		List<Epsilon> epsilons = new ArrayList<Epsilon>();

		for (int i = 0; i < 100; i++) {
			Epsilon epsilon = getEpsilonEntity();
			Iota iota = getIotaEntity();
			Iota iota2 = getIotaEntity();

			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(epsilon);

			iota2.setAlpha(getAlphaEntity());
			iota2.setEpsilon(epsilon);

			epsilon.getIotas().add(iota);
			epsilon.getIotas().add(iota2);
			epsilons.add(epsilon);
		}

		entityManager.insertBatchEntity(epsilons);

		entityManager.deleteBatchEntity(epsilons);

		epsilons = (List<Epsilon>) entityManager.selectEntity(getEpsilonEntity());

		Assert.assertTrue("The list of entities must be empty", epsilons.isEmpty());
	}

	@Test
	public void testCascadeCompleteListEntityBatchDelete() throws Exception {
		List<Tau> taus = new ArrayList<Tau>();

		for (int i = 0; i < 100; i++) {
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
			taus.add(tau);
		}

		entityManager.insertBatchEntity(taus);

		entityManager.deleteBatchEntity(taus);

		taus = (List<Tau>) entityManager.selectEntity(getTauEntity());

		Assert.assertTrue("The list of entities must be empty", taus.isEmpty());
	}

	@Test
	public void testBlockJoinListEntityBatchDelete() throws Exception {
		List<Iota> iotas = new ArrayList<Iota>();

		for (int i = 0; i < 100; i++) {
			Iota iota = getIotaEntity();
			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(getEpsilonEntity());
			iotas.add(iota);
		}

		entityManager.insertBatchEntity(iotas);

		entityManager.deleteBatchEntity(iotas, true);

		iotas = (List<Iota>) entityManager.selectEntity(getIotaEntity());

		Assert.assertTrue("The list of entities must be empty", iotas.isEmpty());

		List<Epsilon> epsilons = (List<Epsilon>) entityManager.selectEntity(getEpsilonEntity());

		Assert.assertTrue("The list of entities must contains 100 elements", epsilons.size() == 100);

		List<Alpha> alphas = (List<Alpha>) entityManager.selectEntity(getAlphaEntity());
	
		Assert.assertTrue("The list of entities must contains 100 elements", alphas.size() == 100);
	}

	@Test
	public void testBlockCascadeOneToOneListEntityBatchDelete() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setGama(getGamaEntity());
			alphas.add(alpha);
		}

		entityManager.insertBatchEntity(alphas);

		entityManager.deleteBatchEntity(alphas, true);

		alphas = (List<Alpha>) entityManager.selectEntity(getAlphaEntity());

		Assert.assertTrue("The list of entities must be empty", alphas.isEmpty());

		List<Gama> gamas = (List<Gama>) entityManager.selectEntity(getGamaEntity());
		
		Assert.assertTrue("The list of entities must contains 100 elements", gamas.size() == 100);
	}

	@Test(expected = RuntimeException.class)
	public void testBlockCascadeOneToManyListEntityBatchDelete() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.getBetas().add(getBetaEntity());
			alpha.getBetas().add(getBetaEntity());
			alphas.add(alpha);
		}
		
		entityManager.insertBatchEntity(alphas);

		entityManager.deleteBatchEntity(alphas, true);
	}

	@Test(expected = RuntimeException.class)
	public void testBlockCascadeManyToManyListEntityBatchDelete() throws Exception {
		List<Gama> gamas = new ArrayList<Gama>();

		for (int i = 0; i < 100; i++) {
			Gama gama = getGamaEntity();
			gama.getDeltas().add(getDeltaEntity());
			gama.getDeltas().add(getDeltaEntity());
			gamas.add(gama);
		}

		entityManager.insertBatchEntity(gamas);

		entityManager.deleteBatchEntity(gamas, true);
	}

	@Test(expected = RuntimeException.class)
	public void testBlockCascadeJoinManyToManyListEntityBatchDelete() throws Exception {
		List<Epsilon> epsilons = new ArrayList<Epsilon>();

		for (int i = 0; i < 100; i++) {
			Epsilon epsilon = getEpsilonEntity();
			Iota iota = getIotaEntity();
			Iota iota2 = getIotaEntity();

			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(epsilon);

			iota2.setAlpha(getAlphaEntity());
			iota2.setEpsilon(epsilon);

			epsilon.getIotas().add(iota);
			epsilon.getIotas().add(iota2);
			epsilons.add(epsilon);
		}

		entityManager.insertBatchEntity(epsilons);

		entityManager.deleteBatchEntity(epsilons, true);
	}

	@Test(expected = RuntimeException.class)
	public void testBlockCascadeCompleteListEntityBatchDelete() throws Exception {
		List<Tau> taus = new ArrayList<Tau>();

		for (int i = 0; i < 100; i++) {
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
			taus.add(tau);
		}

		entityManager.insertBatchEntity(taus);

		entityManager.deleteBatchEntity(taus, true);
	}

}
