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

public class BatchInsertTest extends AbstractTest {

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
	public void testNullListEntityBatchInsert() throws Exception {
		entityManager.insertBatchEntity(null);
	}

	@Test
	public void testBasicListEntityBatchInsert() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			betas.add(getBetaEntity());
		}

		entityManager.insertBatchEntity(betas);

		for (Beta beta : betas) {
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
	}

	@Test
	public void testSecondIdListEntityBatchInsert() throws Exception {
		List<Omega> omegas = new ArrayList<Omega>();

		for (int i = 0; i < 100; i++) {
			omegas.add(getOmegaEntity());
		}

		entityManager.insertBatchEntity(omegas);

		for (Omega omega : omegas) {
			Assert.assertTrue("The omega entity must have an id.", omega.getId() != null);
		}
	}

	@Test(expected = RuntimeException.class)
	public void testIdNotNullListEntityBatchInsert() throws Exception {
		List<Omega> omegas = new ArrayList<Omega>();

		for (int i = 0; i < 100; i++) {
			Omega omega = getOmegaEntity();
			omega.setId(1l);
			omegas.add(omega);
		}

		entityManager.insertBatchEntity(omegas);
	}

	@Test(expected = RuntimeException.class)
	public void testEmptyListWithEntityBatchInsert() throws Exception {
		List<Omega> omegas = new ArrayList<Omega>();

		for (int i = 0; i < 100; i++) {
			omegas.add(new Omega());
		}

		entityManager.insertBatchEntity(omegas);
	}

	@Test
	public void testNullAttributesListEntityBatchInsert() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			Beta beta = getBetaEntity();
			beta.setInteger(null);
			beta.setBool(null);
			beta.setDecimal(null);
			beta.setDoubli(null);
			beta.setFloati(null);
			beta.setLongi(null);
			beta.setString(null);
			beta.setText(null);
			betas.add(beta);
		}

		entityManager.insertBatchEntity(betas);

		for (Beta beta : betas) {
			beta = (Beta) entityManager.selectSingleEntity(beta);

			Assert.assertTrue("The beta entity must not be null.", beta != null);
			Assert.assertTrue("The beta entity must have inserted Integer value null.", beta.getInteger() == null);
			Assert.assertTrue("The beta entity must have inserted Boolean value null.", beta.getBool() == null);
			Assert.assertTrue("The beta entity must have inserted Decimal value null.", beta.getDecimal() == null);
			Assert.assertTrue("The beta entity must have inserted Double value null.", beta.getDoubli() == null);
			Assert.assertTrue("The beta entity must have inserted Float value null.", beta.getFloati() == null);
			Assert.assertTrue("The beta entity must have inserted String value null.", beta.getString() == null);
			Assert.assertTrue("The beta entity must have inserted Text value null.", beta.getText() == null);
		}
	}

	@Test
	public void testMoreThanOnePkIdListEntityBatchInsert() throws Exception {
		// TODO: Allow more than one pk id. // Use teta entity
	}

	@Test(expected = RuntimeException.class)
	public void testColumnExceedLengthListEntityBatchInsert() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			Beta beta = getBetaEntity();
			beta.setString(STRING_50);
			betas.add(beta);
		}

		entityManager.insertBatchEntity(betas);
	}

	@Test
	public void testTransientListEntityBatchInsert() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			Beta beta = getBetaEntity();
			beta.setTrans("Transient value");
			betas.add(beta);
		}

		entityManager.insertBatchEntity(betas);

		for (Beta beta : betas) {
			Assert.assertTrue("The beta entity must have an id.", beta.getId() != null);
			
			beta = (Beta) entityManager.selectSingleEntity(beta);
			Assert.assertTrue("The beta entity must not be null.", beta != null);
			Assert.assertTrue("The beta entity must not have transiente value.", beta.getTrans() == null);
		}
	}

	@Test
	public void testJoinListEntityBatchInsert() throws Exception {
		List<Iota> iotas = new ArrayList<Iota>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			Iota iota = getIotaEntity();
			Epsilon epsilon = getEpsilonEntity();
			iota.setAlpha(alpha);
			iota.setEpsilon(epsilon);
			iotas.add(iota);
		}

		entityManager.insertBatchEntity(iotas);

		for (Iota iota : iotas) {
			Assert.assertTrue("The alpha entity must have an id.", iota.getAlpha().getId() != null);
			Assert.assertTrue("The epsilon entity must have an id.", iota.getEpsilon().getId() != null);
	
			iota = (Iota) entityManager.selectSingleEntity(iota);
	
			Assert.assertTrue("The iota entity must not be null.", iota != null);
			Assert.assertTrue("The alpha entity must not be null.", iota.getAlpha() != null);
			Assert.assertTrue("The epsilon entity must not be null.", iota.getEpsilon() != null);
		}
		// TODO: Case column with auto generated id, how to retrieve the value
	}

	@Test
	public void testCascadeOneToOneListEntityBatchInsert() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			Gama gama = getGamaEntity();
			alpha.setGama(gama);
			alphas.add(alpha);
		}

		entityManager.insertBatchEntity(alphas);

		for (Alpha alpha : alphas) {
			Assert.assertTrue("The gama entity must have an id.", alpha.getGama().getId() != null);
			Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);
		}
	}

	@Test
	public void testCascadeOneToManyListEntityBatchInsert() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.getBetas().add(getBetaEntity());
			alpha.getBetas().add(getBetaEntity());
			alphas.add(alpha);
		}

		entityManager.insertBatchEntity(alphas);

		for (Alpha alpha : alphas) {
			for (Beta beta : alpha.getBetas()) {
				Assert.assertTrue("The beta entity must have an id.", beta.getId() != null);
			}
			Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);
		}
	}

	@Test
	public void testCascadeManyToManyListEntityBatchInsert() throws Exception {
		List<Gama> gamas = new ArrayList<Gama>();

		for (int i = 0; i < 100; i++) {
			Gama gama = getGamaEntity();
			gama.getDeltas().add(getDeltaEntity());
			gama.getDeltas().add(getDeltaEntity());
			gamas.add(gama);
		}

		entityManager.insertBatchEntity(gamas);

		for (Gama gama : gamas) {
			for (Delta delta : gama.getDeltas()) {
				Assert.assertTrue("The delta entity must have an id.", delta.getId() != null);
			}
			Assert.assertTrue("The gama entity must have an id.", gama.getId() != null);
		}
	}

	@Test
	public void testCascadeJoinManyToManyListEntityBatchInsert() throws Exception {
		List<Epsilon> epsilons = new ArrayList<Epsilon>();

		for (int i = 0; i < 100; i++) {
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
			epsilons.add(epsilon);
		}

		entityManager.insertBatchEntity(epsilons);

		for (Epsilon epsilon : epsilons) {
			Assert.assertTrue("The epsilon entity must have an id.", epsilon.getId() != null);

			for (Iota iota : epsilon.getIotas()) {	
				Assert.assertTrue("The iota entity must not be null.", iota != null);
				Assert.assertTrue("The alpha entity must not be null.", iota.getAlpha().getId() != null);
				Assert.assertTrue("The epsilon entity must not be null.", iota.getEpsilon().getId() != null);
			}
		}
	}

	@Test
	public void testCascadeCompleteListEntityBatchInsert() throws Exception {
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

		for (Tau tau : taus) {
			Assert.assertTrue("The tau entity must have an id.", tau.getId() != null);
			Assert.assertTrue("The fi entity must have an id.", tau.getFi().getId() != null);
			for (Pi pi : tau.getPis()) {
				Assert.assertTrue("The pi entity must have an id.", pi.getId() != null);
			}
			for (Psi psi : tau.getPsis()) {
				Assert.assertTrue("The psi entity must have an id.", psi.getId() != null);
			}
			for (Ro ro : tau.getRos()) {
				Assert.assertTrue("The sigma entity must have an id.", ro.getSigma().getId() != null);
			}
		}
	}

	@Test(expected = RuntimeException.class)
	public void testBlockCascadeJoinListEntityBatchInsert() throws Exception {
		List<Iota> iotas = new ArrayList<Iota>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			Iota iota = getIotaEntity();
			Epsilon epsilon = getEpsilonEntity();
			iota.setAlpha(alpha);
			iota.setEpsilon(epsilon);
			iotas.add(iota);
		}

		entityManager.insertBatchEntity(iotas, true);

		for (Iota iota : iotas) {
			Assert.assertTrue("The alpha entity must not have an id.", iota.getAlpha().getId() == null);
			Assert.assertTrue("The epsilon entity must not have an id.", iota.getEpsilon().getId() == null);
		}
		// TODO: Case column with auto generated id, how to retrieve the value
	}

	@Test
	public void testBlockCascadeOneToOneListEntityBatchInsert() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			Gama gama = getGamaEntity();
			alpha.setGama(gama);
			alphas.add(alpha);
		}

		entityManager.insertBatchEntity(alphas, true);

		for (Alpha alpha : alphas) {
			Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);
			Assert.assertTrue("The gama entity must not have an id.", alpha.getGama().getId() == null);
		}
	}

	@Test
	public void testBlockCascadeOneToManyListEntityBatchInsert() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.getBetas().add(getBetaEntity());
			alpha.getBetas().add(getBetaEntity());
			alphas.add(alpha);
		}

		entityManager.insertBatchEntity(alphas, true);

		for (Alpha alpha : alphas) {
			for (Beta beta : alpha.getBetas()) {
				Assert.assertTrue("The beta entity must not have an id.", beta.getId() == null);
			}
			Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);
		}
	}

	@Test
	public void testBlockCascadeManyToManyListEntityBatchInsert() throws Exception {
		List<Gama> gamas = new ArrayList<Gama>();

		for (int i = 0; i < 100; i++) {
			Gama gama = getGamaEntity();
			gama.getDeltas().add(getDeltaEntity());
			gama.getDeltas().add(getDeltaEntity());
			gamas.add(gama);
		}

		entityManager.insertBatchEntity(gamas, true);

		for (Gama gama : gamas) {
			for (Delta delta : gama.getDeltas()) {
				Assert.assertTrue("The delta entity must not have an id.", delta.getId() == null);
			}
			Assert.assertTrue("The gama entity must have an id.", gama.getId() != null);
		}
	}

	@Test
	public void testBlockCascadeJoinManyToManyListEntityBatchInsert() throws Exception {
		List<Epsilon> epsilons = new ArrayList<Epsilon>();

		for (int i = 0; i < 100; i++) {
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
			epsilons.add(epsilon);
		}

		entityManager.insertBatchEntity(epsilons, true);

		for (Epsilon epsilon : epsilons) {
			Assert.assertTrue("The epsilon entity must have an id.", epsilon.getId() != null);

			for (Iota iota : epsilon.getIotas()) {
				Assert.assertTrue("The alpha entity must not have an id.", iota.getAlpha().getId() == null);
				Assert.assertTrue("The epsilon entity must have an id.", iota.getEpsilon().getId() != null);
			}
		}
	}

	@Test
	public void testBlockCascadeCompleteListEntityBatchInsert() throws Exception {
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

		entityManager.insertBatchEntity(taus, true);

		for (Tau tau : taus) {
			Assert.assertTrue("The tau entity must have an id.", tau.getId() != null);
			Assert.assertTrue("The fi entity must not have an id.", tau.getFi().getId() == null);
			for (Pi pi : tau.getPis()) {
				Assert.assertTrue("The pi entity must not have an id.", pi.getId() == null);
			}
			for (Psi psi : tau.getPsis()) {
				Assert.assertTrue("The psi entity must not have an id.", psi.getId() == null);
			}
			for (Ro ro : tau.getRos()) {
				Assert.assertTrue("The sigma entity must not have an id.", ro.getSigma().getId() == null);
			}
		}
	}
	
}
