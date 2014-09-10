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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jsmartdb.framework.manager.Entity;
import com.jsmartdb.framework.manager.QueryParam;
import com.jsmartdb.framework.types.OrderType;

import entity.Alpha;
import entity.Beta;
import entity.Delta;
import entity.Epsilon;
import entity.Fi;
import entity.Gama;
import entity.Iota;
import entity.Pi;
import entity.Psi;
import entity.Ro;
import entity.Tau;

@SuppressWarnings("unchecked")
public class SelectTest extends AbstractTest {

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


	//############# TESTS FOR SELECTING ENTITY #############

	@Test(expected = RuntimeException.class)
	public void testNullEntitySelect() throws Exception {
		entityManager.selectEntity((Entity) null);
	}

	@Test(expected = RuntimeException.class)
	public void testNullEntityNullFirstAndMaxSelect() throws Exception {
		entityManager.selectEntity((Entity) null, (Integer) null, null);
	}

	@Test(expected = RuntimeException.class)
	public void testEntityNegativeFirstSelect() throws Exception {
		entityManager.selectEntity(new Alpha(), -1, null);
	}

	@Test(expected = RuntimeException.class)
	public void testEntityNegativeMaxSelect() throws Exception {
		entityManager.selectEntity(new Alpha(), null, -1);
	}

	@Test(expected = RuntimeException.class)
	public void testEntityNegativeFirstAndMaxSelect() throws Exception {
		entityManager.selectEntity(new Alpha(), -1, -1);
	}

	@Test
	public void testEntityWhitFirstAndMaxSelect() throws Exception {
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new Alpha(), 0, Integer.MAX_VALUE);
		Assert.assertTrue("Entities must be empty.", entities.isEmpty());
	}

	@Test
	public void testEmptyEntitySelect() throws Exception {
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new Alpha());
		Assert.assertTrue("Entities must be empty.", entities.isEmpty());
	}

	@Test
	public void testOneEntitySelect() throws Exception {
		Alpha alpha = getAlphaEntity();

		entityManager.insertEntity(alpha);
		Assert.assertTrue("The alpha entity must have an id.", alpha.getId() != null);

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains one element.", entities.size() == 1);
		Assert.assertTrue("The alpha entity found must be the same as inserted", entities.get(0).getId().equals(alpha.getId()));
	}

	@Test
	public void testHundredEntitySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(getAlphaEntity());
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOrderByIntegerSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setInteger(i);
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		Alpha alpha = getAlphaEntity();
		alpha.setInteger(null);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha, "integer", OrderType.ASCENDING);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			if (prevId == 0) {
				Assert.assertTrue("The first entity must have id equal to zero.", entity.getInteger().equals(0));
			}
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOrderByIntegerDescendentSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setInteger(i);
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		Alpha alpha = getAlphaEntity();
		alpha.setInteger(null);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha, "integer", OrderType.DESCENDING);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			if (prevId == 0) {
				Assert.assertTrue("The first entity must have id equal to 99.", entity.getInteger().equals(99));
			}
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOnlyIdSelect() throws Exception {
		Integer lastId = 0;
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			entityManager.insertEntity(alpha);
			lastId = alpha.getId();
		}
		entityManager.commitTransaction();

		Alpha alpha = new Alpha();
		alpha.setId(lastId);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains one element.", entities.size() == 1);

		Assert.assertTrue("The alpha entity found must be the same as lastId", entities.get(0).getId().equals(lastId));
	}

	@Test
	public void testHundredEntityOnlyIntegerSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		Alpha alpha = new Alpha();
		alpha.setInteger(INTEGER);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOnlyStringSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		Alpha alpha = new Alpha();
		alpha.setString(STRING);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOnlyTextSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		Alpha alpha = new Alpha();
		alpha.setText(TEXT);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOnlyLongSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		Alpha alpha = new Alpha();
		alpha.setLongi(LONG);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOnlyDecimalSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		Alpha alpha = new Alpha();
		alpha.setDecimal(DECIMAL);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOnlyDoubleSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		Alpha alpha = new Alpha();
		alpha.setDoubli(DOUBLE);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOnlyFloatSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		Alpha alpha = new Alpha();
		alpha.setFloati(FLOAT);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOnlyBoolSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		Alpha alpha = new Alpha();
		alpha.setBool(BOOLEAN);
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityOnlyWrongStringSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		Alpha alpha = new Alpha();
		alpha.setString("no string match");
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must be empty.", entities.isEmpty());
	}

	@Test
	public void testEntityOrderByAndOrderDirSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setInteger(i);
			alpha.setGama(getGamaEntity());
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		Alpha alpha = getAlphaEntity();
		alpha.setInteger(null);
		alpha.setGama(getGamaEntity());
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha, "integer", OrderType.DESCENDING);

		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			if (prevId == 0) {
				Assert.assertTrue("First entity must have integer id equal to 99", entity.getInteger().equals(99));
			}
			Assert.assertTrue("Gama entity must not be null", entity.getGama() != null);
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testEntityOrderByAndOrderDirWithBlockJoinSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setInteger(i);
			alpha.setGama(getGamaEntity());
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		Alpha alpha = getAlphaEntity();
		alpha.setInteger(null);
		alpha.setGama(getGamaEntity());
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha, "integer", OrderType.DESCENDING, true);

		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			if (prevId == 0) {
				Assert.assertTrue("First entity must have integer id equal to 99", entity.getInteger().equals(99));
			}
			Assert.assertTrue("Gama entity must be null", entity.getGama() == null);
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityWithFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(getAlphaEntity(), 0, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityWithLastFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(getAlphaEntity(), 90, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testHundredEntityJoinOneToOneSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setGama(getGamaEntity());
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		Alpha alpha = getAlphaEntity();
		alpha.setGama(getGamaEntity());
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer alphaPrevId = 0;
		Long gamaPrevId = 0l;
		for (Alpha alphax : entities) {
			Assert.assertTrue("Gama cannot be null", alphax.getGama() != null);
			Assert.assertTrue("Gama ids must be different.", !alphax.getGama().getId().equals(gamaPrevId));
			Assert.assertTrue("Alpha ids must be different.", !alphax.getId().equals(alphaPrevId));
			alphaPrevId = alphax.getId();
			gamaPrevId = alphax.getGama().getId();
		}
	}

	@Test
	public void testHundredEntityJoinOneToOneWithFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setGama(getGamaEntity());
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		Alpha alpha = getAlphaEntity();
		alpha.setGama(getGamaEntity());
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha, 0, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Integer alphaPrevId = 0;
		Long gamaPrevId = 0l;
		for (Alpha alphax : entities) {
			Assert.assertTrue("Gama cannot be null", alphax.getGama() != null);
			Assert.assertTrue("Gama ids must be different.", !alphax.getGama().getId().equals(gamaPrevId));
			Assert.assertTrue("Alpha ids must be different.", !alphax.getId().equals(alphaPrevId));
			alphaPrevId = alphax.getId();
			gamaPrevId = alphax.getGama().getId();
		}
	}

	@Test
	public void testHundredEntityJoinOneToOneWithLastFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setGama(getGamaEntity());
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		Alpha alpha = getAlphaEntity();
		alpha.setGama(getGamaEntity());
		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(alpha, 90, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Integer alphaPrevId = 0;
		Long gamaPrevId = 0l;
		for (Alpha alphax : entities) {
			Assert.assertTrue("Gama cannot be null", alphax.getGama() != null);
			Assert.assertTrue("Gama ids must be different.", !alphax.getGama().getId().equals(gamaPrevId));
			Assert.assertTrue("Alpha ids must be different.", !alphax.getId().equals(alphaPrevId));
			alphaPrevId = alphax.getId();
			gamaPrevId = alphax.getGama().getId();
		}
	}

	@Test
	public void testHundredEntityJoinOneToManySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.getBetas().add(getBetaEntity());
			alpha.getBetas().add(getBetaEntity());
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(getAlphaEntity());
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer alphaPrevId = 0;
		Integer betaPrevId = 0;
		for (Alpha alpha : entities) {
			Assert.assertTrue("Beta list must contains 2 entities.", alpha.getBetas().size() == 2);
			for (Beta beta : alpha.getBetas()) {
				Assert.assertTrue("Gama ids must be different.", !beta.getId().equals(betaPrevId));
				betaPrevId = beta.getId();
			}
			Assert.assertTrue("Alpha ids must be different.", !alpha.getId().equals(alphaPrevId));
			alphaPrevId = alpha.getId();			
		}
	}

	@Test
	public void testHundredEntityJoinOneToManyWithFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.getBetas().add(getBetaEntity());
			alpha.getBetas().add(getBetaEntity());
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(getAlphaEntity(), 0, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Integer alphaPrevId = 0;
		Integer betaPrevId = 0;
		for (Alpha alpha : entities) {
			Assert.assertTrue("Beta list must contains 2 entities.", alpha.getBetas().size() == 2);
			for (Beta beta : alpha.getBetas()) {
				Assert.assertTrue("Gama ids must be different.", !beta.getId().equals(betaPrevId));
				betaPrevId = beta.getId();
			}
			Assert.assertTrue("Alpha ids must be different.", !alpha.getId().equals(alphaPrevId));
			alphaPrevId = alpha.getId();			
		}
	}

	@Test
	public void testHundredEntityJoinOneToManyWithLastFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.getBetas().add(getBetaEntity());
			alpha.getBetas().add(getBetaEntity());
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(getAlphaEntity(), 90, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Integer alphaPrevId = 0;
		Integer betaPrevId = 0;
		for (Alpha alpha : entities) {
			Assert.assertTrue("Beta list must contains 2 entities.", alpha.getBetas().size() == 2);
			for (Beta beta : alpha.getBetas()) {
				Assert.assertTrue("Gama ids must be different.", !beta.getId().equals(betaPrevId));
				betaPrevId = beta.getId();
			}
			Assert.assertTrue("Alpha ids must be different.", !alpha.getId().equals(alphaPrevId));
			alphaPrevId = alpha.getId();			
		}
	}

	@Test
	public void testHundredEntityJoinManyToManySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Gama gama = getGamaEntity();
			gama.getDeltas().add(getDeltaEntity());
			gama.getDeltas().add(getDeltaEntity());
			entityManager.insertEntity(gama);
		}
		entityManager.commitTransaction();

		List<Gama> entities = (List<Gama>) entityManager.selectEntity(getGamaEntity());
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Long gamaPrevId = 0l;
		Integer deltaPrevId = 0;
		for (Gama gama : entities) {
			Assert.assertTrue("Delta list must contains 2 entities.", gama.getDeltas().size() == 2);
			for (Delta delta : gama.getDeltas()) {
				Assert.assertTrue("Delta ids must be different.", !delta.getId().equals(deltaPrevId));
				deltaPrevId = delta.getId();
			}
			Assert.assertTrue("Gama ids must be different.", !gama.getId().equals(gamaPrevId));
			gamaPrevId = gama.getId();			
		}
	}

	@Test
	public void testHundredEntityJoinManyToManyWithFirstAndLastSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Gama gama = getGamaEntity();
			gama.getDeltas().add(getDeltaEntity());
			gama.getDeltas().add(getDeltaEntity());
			entityManager.insertEntity(gama);
		}
		entityManager.commitTransaction();

		List<Gama> entities = (List<Gama>) entityManager.selectEntity(getGamaEntity(), 0, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Long gamaPrevId = 0l;
		Integer deltaPrevId = 0;
		for (Gama gama : entities) {
			Assert.assertTrue("Delta list must contains 2 entities.", gama.getDeltas().size() == 2);
			for (Delta delta : gama.getDeltas()) {
				Assert.assertTrue("Delta ids must be different.", !delta.getId().equals(deltaPrevId));
				deltaPrevId = delta.getId();
			}
			Assert.assertTrue("Gama ids must be different.", !gama.getId().equals(gamaPrevId));
			gamaPrevId = gama.getId();			
		}
	}

	@Test
	public void testHundredEntityJoinManyToManyWithLastFirstAndLastSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Gama gama = getGamaEntity();
			gama.getDeltas().add(getDeltaEntity());
			gama.getDeltas().add(getDeltaEntity());
			entityManager.insertEntity(gama);
		}
		entityManager.commitTransaction();

		List<Gama> entities = (List<Gama>) entityManager.selectEntity(getGamaEntity(), 90, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Long gamaPrevId = 0l;
		Integer deltaPrevId = 0;
		for (Gama gama : entities) {
			Assert.assertTrue("Delta list must contains 2 entities.", gama.getDeltas().size() == 2);
			for (Delta delta : gama.getDeltas()) {
				Assert.assertTrue("Delta ids must be different.", !delta.getId().equals(deltaPrevId));
				deltaPrevId = delta.getId();
			}
			Assert.assertTrue("Gama ids must be different.", !gama.getId().equals(gamaPrevId));
			gamaPrevId = gama.getId();			
		}
	}

	@Test
	public void testHundredEntityJoinOneToManyWithJoinEntitySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Epsilon epsilon = getEpsilonEntity();

			Iota iota = getIotaEntity();
			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(epsilon);

			Iota iota2 = getIotaEntity();
			iota2.setAlpha(getAlphaEntity());
			iota2.setEpsilon(epsilon);

			epsilon.getIotas().add(iota);
			epsilon.getIotas().add(iota2);
			entityManager.insertEntity(epsilon);
		}
		entityManager.commitTransaction();

		List<Epsilon> entities = (List<Epsilon>) entityManager.selectEntity(getEpsilonEntity());
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer epsilonPrevId = 0;
		Integer alphaPrevId = 0;
		for (Epsilon epsilon : entities) {
			Assert.assertTrue("Iota list must contains 2 entities.", epsilon.getIotas().size() == 2);
			for (Iota iota : epsilon.getIotas()) {
				Assert.assertTrue("Epsilon id must be equal to its owner.", iota.getEpsilon().getId().equals(epsilon.getId()));
				Assert.assertTrue("Alpha entity must not be null.", iota.getAlpha() != null);
				Assert.assertTrue("Alpha ids must be different.", !iota.getAlpha().getId().equals(alphaPrevId));
				alphaPrevId = iota.getAlpha().getId();
			}
			Assert.assertTrue("Epsilon ids must be different.", !epsilon.getId().equals(epsilonPrevId));
			epsilonPrevId = epsilon.getId();			
		}
	}

	@Test
	public void testHundredEntityJoinOneToManyWithJoinEntityWithFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Epsilon epsilon = getEpsilonEntity();

			Iota iota = getIotaEntity();
			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(epsilon);

			Iota iota2 = getIotaEntity();
			iota2.setAlpha(getAlphaEntity());
			iota2.setEpsilon(epsilon);

			epsilon.getIotas().add(iota);
			epsilon.getIotas().add(iota2);
			entityManager.insertEntity(epsilon);
		}
		entityManager.commitTransaction();

		List<Epsilon> entities = (List<Epsilon>) entityManager.selectEntity(getEpsilonEntity(), 0, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Integer epsilonPrevId = 0;
		Integer alphaPrevId = 0;
		for (Epsilon epsilon : entities) {
			Assert.assertTrue("Iota list must contains 2 entities.", epsilon.getIotas().size() == 2);
			for (Iota iota : epsilon.getIotas()) {
				Assert.assertTrue("Epsilon id must be equal to its owner.", iota.getEpsilon().getId().equals(epsilon.getId()));
				Assert.assertTrue("Alpha entity must not be null.", iota.getAlpha() != null);
				Assert.assertTrue("Alpha ids must be different.", !iota.getAlpha().getId().equals(alphaPrevId));
				alphaPrevId = iota.getAlpha().getId();
			}
			Assert.assertTrue("Epsilon ids must be different.", !epsilon.getId().equals(epsilonPrevId));
			epsilonPrevId = epsilon.getId();			
		}
	}

	@Test
	public void testHundredEntityJoinOneToManyWithJoinEntityWithLastFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Epsilon epsilon = getEpsilonEntity();

			Iota iota = getIotaEntity();
			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(epsilon);

			Iota iota2 = getIotaEntity();
			iota2.setAlpha(getAlphaEntity());
			iota2.setEpsilon(epsilon);

			epsilon.getIotas().add(iota);
			epsilon.getIotas().add(iota2);
			entityManager.insertEntity(epsilon);
		}
		entityManager.commitTransaction();

		List<Epsilon> entities = (List<Epsilon>) entityManager.selectEntity(getEpsilonEntity(), 90, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Integer epsilonPrevId = 0;
		Integer alphaPrevId = 0;
		for (Epsilon epsilon : entities) {
			Assert.assertTrue("Iota list must contains 2 entities.", epsilon.getIotas().size() == 2);
			for (Iota iota : epsilon.getIotas()) {
				Assert.assertTrue("Epsilon id must be equal to its owner.", iota.getEpsilon().getId().equals(epsilon.getId()));
				Assert.assertTrue("Alpha entity must not be null.", iota.getAlpha() != null);
				Assert.assertTrue("Alpha ids must be different.", !iota.getAlpha().getId().equals(alphaPrevId));
				alphaPrevId = iota.getAlpha().getId();
			}
			Assert.assertTrue("Epsilon ids must be different.", !epsilon.getId().equals(epsilonPrevId));
			epsilonPrevId = epsilon.getId();			
		}
	}

	@Test
	public void testTwoHundredJoinEntitySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Epsilon epsilon = getEpsilonEntity();

			Iota iota = getIotaEntity();
			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(epsilon);

			Iota iota2 = getIotaEntity();
			iota2.setAlpha(getAlphaEntity());
			iota2.setEpsilon(epsilon);

			epsilon.getIotas().add(iota);
			epsilon.getIotas().add(iota2);
			entityManager.insertEntity(epsilon);
		}
		entityManager.commitTransaction();

		List<Iota> entities = (List<Iota>) entityManager.selectEntity(getIotaEntity());
		Assert.assertTrue("Entities must contains two hundred elements.", entities.size() == 200);

		List<Integer[]> iotaPrevIds = new ArrayList<Integer[]>();
		for (Iota iota : entities) {			
			Assert.assertTrue("Alpha entity must not be null.", iota.getAlpha() != null);
			Assert.assertTrue("Epsilon entity must not be null.", iota.getEpsilon() != null);

			Integer[] iotaId = new Integer[]{iota.getEpsilon().getId(), iota.getAlpha().getId()};
			for (Integer[] iotaPrevId : iotaPrevIds) {
				Assert.assertTrue("Iota ids must be different.", !Arrays.equals(iotaPrevId, iotaId));
			}
			iotaPrevIds.add(iotaId);
		}
	}

	@Test
	public void testTwoHundredJoinEntityWithFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Epsilon epsilon = getEpsilonEntity();

			Iota iota = getIotaEntity();
			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(epsilon);

			Iota iota2 = getIotaEntity();
			iota2.setAlpha(getAlphaEntity());
			iota2.setEpsilon(epsilon);

			epsilon.getIotas().add(iota);
			epsilon.getIotas().add(iota2);
			entityManager.insertEntity(epsilon);
		}
		entityManager.commitTransaction();

		List<Iota> entities = (List<Iota>) entityManager.selectEntity(getIotaEntity(), 0, 10);
		Assert.assertTrue("Entities must contains two hundred elements.", entities.size() == 10);

		List<Integer[]> iotaPrevIds = new ArrayList<Integer[]>();
		for (Iota iota : entities) {			
			Assert.assertTrue("Alpha entity must not be null.", iota.getAlpha() != null);
			Assert.assertTrue("Epsilon entity must not be null.", iota.getEpsilon() != null);

			Integer[] iotaId = new Integer[]{iota.getEpsilon().getId(), iota.getAlpha().getId()};
			for (Integer[] iotaPrevId : iotaPrevIds) {
				Assert.assertTrue("Iota ids must be different.", !Arrays.equals(iotaPrevId, iotaId));
			}
			iotaPrevIds.add(iotaId);
		}
	}

	@Test
	public void testTwoHundredJoinEntityWithLastFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Epsilon epsilon = getEpsilonEntity();

			Iota iota = getIotaEntity();
			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(epsilon);

			Iota iota2 = getIotaEntity();
			iota2.setAlpha(getAlphaEntity());
			iota2.setEpsilon(epsilon);

			epsilon.getIotas().add(iota);
			epsilon.getIotas().add(iota2);
			entityManager.insertEntity(epsilon);
		}
		entityManager.commitTransaction();

		List<Iota> entities = (List<Iota>) entityManager.selectEntity(getIotaEntity(), 90, 10);
		Assert.assertTrue("Entities must contains two hundred elements.", entities.size() == 10);

		List<Integer[]> iotaPrevIds = new ArrayList<Integer[]>();
		for (Iota iota : entities) {			
			Assert.assertTrue("Alpha entity must not be null.", iota.getAlpha() != null);
			Assert.assertTrue("Epsilon entity must not be null.", iota.getEpsilon() != null);

			Integer[] iotaId = new Integer[]{iota.getEpsilon().getId(), iota.getAlpha().getId()};
			for (Integer[] iotaPrevId : iotaPrevIds) {
				Assert.assertTrue("Iota ids must be different.", !Arrays.equals(iotaPrevId, iotaId));
			}
			iotaPrevIds.add(iotaId);
		}
	}

	@Test
	public void testHundredCompleteEntitySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Tau tau = getTauEntity();

			tau.setFi(getFiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPsis().add(getPsiEntity());
			tau.getPsis().add(getPsiEntity());

			Ro ro = getRoEntity();
			ro.setTau(tau);
			ro.setSigma(getSigmaEntity());

			Ro ro2 = getRoEntity();
			ro2.setTau(tau);
			ro2.setSigma(getSigmaEntity());

			tau.getRos().add(ro);
			tau.getRos().add(ro2);
			entityManager.insertEntity(tau);
		}
		entityManager.commitTransaction();

		List<Tau> entities = (List<Tau>) entityManager.selectEntity(getTauEntity());
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer tauPrevId = 0;
		Integer fiPrevId = 0;
		Integer piPrevId = 0;
		Integer psiPrevId = 0;
		List<Integer[]> roPrevIds = new ArrayList<Integer[]>();
		for (Tau tau : entities) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must not be null.", tau.getFi() != null);
			Assert.assertTrue("The ros entities must contains 2 entities.", tau.getRos().size() == 2);
			Assert.assertTrue("The pi entities must contains 2 entities.", tau.getPis().size() == 2);
			Assert.assertTrue("The psi entities must contains 2 entities.", tau.getPsis().size() == 2);

			Assert.assertTrue("Tau ids must be different.", !tau.getId().equals(tauPrevId));
			tauPrevId = tau.getId();

			Assert.assertTrue("Fi ids must be different.", !tau.getFi().getId().equals(fiPrevId));
			fiPrevId = tau.getFi().getId();

			for (Pi pi : tau.getPis()) {
				Assert.assertTrue("The pi entity id must not be null.", pi.getId() != null);
				Assert.assertTrue("Pi ids must be different.", !pi.getId().equals(piPrevId));
				piPrevId = pi.getId();
			}

			for (Psi psi : tau.getPsis()) {
				Assert.assertTrue("The psi entity id must not be null.", psi.getId() != null);
				Assert.assertTrue("Psi ids must be different.", !psi.getId().equals(psiPrevId));
				psiPrevId = psi.getId();
			}

			for (Ro ro : tau.getRos()) {
				Assert.assertTrue("The tau entity must not be null.", ro.getTau() != null);
				Assert.assertTrue("The sigma entity must not be null.", ro.getSigma() != null);
				Assert.assertTrue("The tau entity must be equal to its owner.", ro.getTau().getId().equals(tau.getId()));

				Integer[] roId = new Integer[]{ro.getTau().getId(), ro.getSigma().getId()};
				for (Integer[] roPrevId : roPrevIds) {
					Assert.assertTrue("Ro ids must be different.", !Arrays.equals(roPrevId, roId));
				}
				roPrevIds.add(roId);
			}
		}
	}

	@Test
	public void testHundredBlockJoinCompleteEntitySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Tau tau = getTauEntity();

			tau.setFi(getFiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPsis().add(getPsiEntity());
			tau.getPsis().add(getPsiEntity());

			Ro ro = getRoEntity();
			ro.setTau(tau);
			ro.setSigma(getSigmaEntity());

			Ro ro2 = getRoEntity();
			ro2.setTau(tau);
			ro2.setSigma(getSigmaEntity());

			tau.getRos().add(ro);
			tau.getRos().add(ro2);
			entityManager.insertEntity(tau);
		}
		entityManager.commitTransaction();

		List<Tau> entities = (List<Tau>) entityManager.selectEntity(getTauEntity(), true);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer tauPrevId = 0;
		for (Tau tau : entities) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must be null.", tau.getFi() == null);
			Assert.assertTrue("The ros entities must be empty.", tau.getRos().isEmpty());
			Assert.assertTrue("The pi entities must be empty.", tau.getPis().isEmpty());
			Assert.assertTrue("The psi entities must be empty.", tau.getPsis().isEmpty());

			Assert.assertTrue("Tau ids must be different.", !tau.getId().equals(tauPrevId));
			tauPrevId = tau.getId();
		}
	}

	@Test
	public void testHundredBlockJoinByClassOneToOneCompleteEntitySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Tau tau = getTauEntity();

			tau.setFi(getFiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPsis().add(getPsiEntity());
			tau.getPsis().add(getPsiEntity());

			Ro ro = getRoEntity();
			ro.setTau(tau);
			ro.setSigma(getSigmaEntity());

			Ro ro2 = getRoEntity();
			ro2.setTau(tau);
			ro2.setSigma(getSigmaEntity());

			tau.getRos().add(ro);
			tau.getRos().add(ro2);
			entityManager.insertEntity(tau);
		}
		entityManager.commitTransaction();

		List<Tau> entities = (List<Tau>) entityManager.selectEntity(getTauEntity(), Fi.class);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer tauPrevId = 0;
		Integer piPrevId = 0;
		Integer psiPrevId = 0;
		List<Integer[]> roPrevIds = new ArrayList<Integer[]>();
		for (Tau tau : entities) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must be null.", tau.getFi() == null);
			Assert.assertTrue("The ros entities must contains 2 entities.", tau.getRos().size() == 2);
			Assert.assertTrue("The pi entities must contains 2 entities.", tau.getPis().size() == 2);
			Assert.assertTrue("The psi entities must contains 2 entities.", tau.getPsis().size() == 2);

			Assert.assertTrue("Tau ids must be different.", !tau.getId().equals(tauPrevId));
			tauPrevId = tau.getId();

			for (Pi pi : tau.getPis()) {
				Assert.assertTrue("The pi entity id must not be null.", pi.getId() != null);
				Assert.assertTrue("Pi ids must be different.", !pi.getId().equals(piPrevId));
				piPrevId = pi.getId();
			}

			for (Psi psi : tau.getPsis()) {
				Assert.assertTrue("The psi entity id must not be null.", psi.getId() != null);
				Assert.assertTrue("Psi ids must be different.", !psi.getId().equals(psiPrevId));
				psiPrevId = psi.getId();
			}

			for (Ro ro : tau.getRos()) {
				Assert.assertTrue("The tau entity must not be null.", ro.getTau() != null);
				Assert.assertTrue("The sigma entity must not be null.", ro.getSigma() != null);
				Assert.assertTrue("The tau entity must be equal to its owner.", ro.getTau().getId().equals(tau.getId()));

				Integer[] roId = new Integer[]{ro.getTau().getId(), ro.getSigma().getId()};
				for (Integer[] roPrevId : roPrevIds) {
					Assert.assertTrue("Ro ids must be different.", !Arrays.equals(roPrevId, roId));
				}
				roPrevIds.add(roId);
			}
		}
	}

	@Test
	public void testHundredBlockJoinByClassOneToManyCompleteEntitySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Tau tau = getTauEntity();

			tau.setFi(getFiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPsis().add(getPsiEntity());
			tau.getPsis().add(getPsiEntity());

			Ro ro = getRoEntity();
			ro.setTau(tau);
			ro.setSigma(getSigmaEntity());

			Ro ro2 = getRoEntity();
			ro2.setTau(tau);
			ro2.setSigma(getSigmaEntity());

			tau.getRos().add(ro);
			tau.getRos().add(ro2);
			entityManager.insertEntity(tau);
		}
		entityManager.commitTransaction();

		List<Tau> entities = (List<Tau>) entityManager.selectEntity(getTauEntity(), Psi.class);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer tauPrevId = 0;
		Integer fiPrevId = 0;
		Integer piPrevId = 0;
		List<Integer[]> roPrevIds = new ArrayList<Integer[]>();
		for (Tau tau : entities) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must not be null.", tau.getFi() != null);
			Assert.assertTrue("The ros entities must contains 2 entities.", tau.getRos().size() == 2);
			Assert.assertTrue("The pi entities must contains 2 entities.", tau.getPis().size() == 2);
			Assert.assertTrue("The psi entities must be empty.", tau.getPsis().isEmpty());

			Assert.assertTrue("Tau ids must be different.", !tau.getId().equals(tauPrevId));
			tauPrevId = tau.getId();

			Assert.assertTrue("Fi ids must be different.", !tau.getFi().getId().equals(fiPrevId));
			fiPrevId = tau.getFi().getId();

			for (Pi pi : tau.getPis()) {
				Assert.assertTrue("The pi entity id must not be null.", pi.getId() != null);
				Assert.assertTrue("Pi ids must be different.", !pi.getId().equals(piPrevId));
				piPrevId = pi.getId();
			}

			for (Ro ro : tau.getRos()) {
				Assert.assertTrue("The tau entity must not be null.", ro.getTau() != null);
				Assert.assertTrue("The sigma entity must not be null.", ro.getSigma() != null);
				Assert.assertTrue("The tau entity must be equal to its owner.", ro.getTau().getId().equals(tau.getId()));

				Integer[] roId = new Integer[]{ro.getTau().getId(), ro.getSigma().getId()};
				for (Integer[] roPrevId : roPrevIds) {
					Assert.assertTrue("Ro ids must be different.", !Arrays.equals(roPrevId, roId));
				}
				roPrevIds.add(roId);
			}
		}
	}

	@Test
	public void testHundredBlockJoinByClassManyToManyCompleteEntitySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Tau tau = getTauEntity();

			tau.setFi(getFiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPsis().add(getPsiEntity());
			tau.getPsis().add(getPsiEntity());

			Ro ro = getRoEntity();
			ro.setTau(tau);
			ro.setSigma(getSigmaEntity());

			Ro ro2 = getRoEntity();
			ro2.setTau(tau);
			ro2.setSigma(getSigmaEntity());

			tau.getRos().add(ro);
			tau.getRos().add(ro2);
			entityManager.insertEntity(tau);
		}
		entityManager.commitTransaction();

		List<Tau> entities = (List<Tau>) entityManager.selectEntity(getTauEntity(), Pi.class);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer tauPrevId = 0;
		Integer fiPrevId = 0;
		Integer psiPrevId = 0;
		List<Integer[]> roPrevIds = new ArrayList<Integer[]>();
		for (Tau tau : entities) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must not be null.", tau.getFi() != null);
			Assert.assertTrue("The ros entities must contains 2 entities.", tau.getRos().size() == 2);
			Assert.assertTrue("The pi entities must be empty.", tau.getPis().isEmpty());
			Assert.assertTrue("The psi entities must contains 2 entities.", tau.getPsis().size() == 2);

			Assert.assertTrue("Tau ids must be different.", !tau.getId().equals(tauPrevId));
			tauPrevId = tau.getId();

			Assert.assertTrue("Fi ids must be different.", !tau.getFi().getId().equals(fiPrevId));
			fiPrevId = tau.getFi().getId();

			for (Psi psi : tau.getPsis()) {
				Assert.assertTrue("The psi entity id must not be null.", psi.getId() != null);
				Assert.assertTrue("Psi ids must be different.", !psi.getId().equals(psiPrevId));
				psiPrevId = psi.getId();
			}

			for (Ro ro : tau.getRos()) {
				Assert.assertTrue("The tau entity must not be null.", ro.getTau() != null);
				Assert.assertTrue("The sigma entity must not be null.", ro.getSigma() != null);
				Assert.assertTrue("The tau entity must be equal to its owner.", ro.getTau().getId().equals(tau.getId()));

				Integer[] roId = new Integer[]{ro.getTau().getId(), ro.getSigma().getId()};
				for (Integer[] roPrevId : roPrevIds) {
					Assert.assertTrue("Ro ids must be different.", !Arrays.equals(roPrevId, roId));
				}
				roPrevIds.add(roId);
			}
		}
	}

	@Test
	public void testHundredBlockJoinByClassOneToManyOfJoinEntityCompleteEntitySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Tau tau = getTauEntity();

			tau.setFi(getFiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPsis().add(getPsiEntity());
			tau.getPsis().add(getPsiEntity());

			Ro ro = getRoEntity();
			ro.setTau(tau);
			ro.setSigma(getSigmaEntity());

			Ro ro2 = getRoEntity();
			ro2.setTau(tau);
			ro2.setSigma(getSigmaEntity());

			tau.getRos().add(ro);
			tau.getRos().add(ro2);
			entityManager.insertEntity(tau);
		}
		entityManager.commitTransaction();
	
		List<Tau> entities = (List<Tau>) entityManager.selectEntity(getTauEntity(), Ro.class);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer tauPrevId = 0;
		Integer fiPrevId = 0;
		Integer piPrevId = 0;
		Integer psiPrevId = 0;
		for (Tau tau : entities) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must not be null.", tau.getFi() != null);
			Assert.assertTrue("The ros entities must be empty.", tau.getRos().isEmpty());
			Assert.assertTrue("The pi entities must contains 2 entities.", tau.getPis().size() == 2);
			Assert.assertTrue("The psi entities must contains 2 entities.", tau.getPsis().size() == 2);

			Assert.assertTrue("Tau ids must be different.", !tau.getId().equals(tauPrevId));
			tauPrevId = tau.getId();

			Assert.assertTrue("Fi ids must be different.", !tau.getFi().getId().equals(fiPrevId));
			fiPrevId = tau.getFi().getId();

			for (Pi pi : tau.getPis()) {
				Assert.assertTrue("The pi entity id must not be null.", pi.getId() != null);
				Assert.assertTrue("Pi ids must be different.", !pi.getId().equals(piPrevId));
				piPrevId = pi.getId();
			}

			for (Psi psi : tau.getPsis()) {
				Assert.assertTrue("The psi entity id must not be null.", psi.getId() != null);
				Assert.assertTrue("Psi ids must be different.", !psi.getId().equals(psiPrevId));
				psiPrevId = psi.getId();
			}
		}
	}

	@Test
	public void testHundredBlockJoinAllClassesCompleteEntitySelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Tau tau = getTauEntity();

			tau.setFi(getFiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPsis().add(getPsiEntity());
			tau.getPsis().add(getPsiEntity());

			Ro ro = getRoEntity();
			ro.setTau(tau);
			ro.setSigma(getSigmaEntity());

			Ro ro2 = getRoEntity();
			ro2.setTau(tau);
			ro2.setSigma(getSigmaEntity());

			tau.getRos().add(ro);
			tau.getRos().add(ro2);
			entityManager.insertEntity(tau);
		}
		entityManager.commitTransaction();
		
		List<Tau> entities = (List<Tau>) entityManager.selectEntity(getTauEntity(), Pi.class, Psi.class, Ro.class, Fi.class);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer tauPrevId = 0;
		for (Tau tau : entities) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must be null.", tau.getFi() == null);
			Assert.assertTrue("The ros entities must be empty.", tau.getRos().isEmpty());
			Assert.assertTrue("The pi entities must be empty.", tau.getPis().isEmpty());
			Assert.assertTrue("The psi entities must be empty.", tau.getPsis().isEmpty());

			Assert.assertTrue("Tau ids must be different.", !tau.getId().equals(tauPrevId));
			tauPrevId = tau.getId();
		}
	}

	@Test
	public void testHundredCompleteEntityWithFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Tau tau = getTauEntity();

			tau.setFi(getFiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPsis().add(getPsiEntity());
			tau.getPsis().add(getPsiEntity());

			Ro ro = getRoEntity();
			ro.setTau(tau);
			ro.setSigma(getSigmaEntity());

			Ro ro2 = getRoEntity();
			ro2.setTau(tau);
			ro2.setSigma(getSigmaEntity());

			tau.getRos().add(ro);
			tau.getRos().add(ro2);
			entityManager.insertEntity(tau);
		}
		entityManager.commitTransaction();

		List<Tau> entities = (List<Tau>) entityManager.selectEntity(getTauEntity(), 0, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Integer tauPrevId = 0;
		Integer fiPrevId = 0;
		Integer piPrevId = 0;
		Integer psiPrevId = 0;
		List<Integer[]> roPrevIds = new ArrayList<Integer[]>();
		for (Tau tau : entities) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must not be null.", tau.getFi() != null);
			Assert.assertTrue("The ros entities must contains 2 entities.", tau.getRos().size() == 2);
			Assert.assertTrue("The pi entities must contains 2 entities.", tau.getPis().size() == 2);
			Assert.assertTrue("The psi entities must contains 2 entities.", tau.getPsis().size() == 2);

			Assert.assertTrue("Tau ids must be different.", !tau.getId().equals(tauPrevId));
			tauPrevId = tau.getId();

			Assert.assertTrue("Fi ids must be different.", !tau.getFi().getId().equals(fiPrevId));
			fiPrevId = tau.getFi().getId();

			for (Pi pi : tau.getPis()) {
				Assert.assertTrue("The pi entity id must not be null.", pi.getId() != null);
				Assert.assertTrue("Pi ids must be different.", !pi.getId().equals(piPrevId));
				piPrevId = pi.getId();
			}

			for (Psi psi : tau.getPsis()) {
				Assert.assertTrue("The psi entity id must not be null.", psi.getId() != null);
				Assert.assertTrue("Psi ids must be different.", !psi.getId().equals(psiPrevId));
				psiPrevId = psi.getId();
			}

			for (Ro ro : tau.getRos()) {
				Assert.assertTrue("The tau entity must not be null.", ro.getTau() != null);
				Assert.assertTrue("The sigma entity must not be null.", ro.getSigma() != null);
				Assert.assertTrue("The tau entity must be equal to its owner.", ro.getTau().getId().equals(tau.getId()));

				Integer[] roId = new Integer[]{ro.getTau().getId(), ro.getSigma().getId()};
				for (Integer[] roPrevId : roPrevIds) {
					Assert.assertTrue("Ro ids must be different.", !Arrays.equals(roPrevId, roId));
				}
				roPrevIds.add(roId);
			}
		}
	}

	@Test
	public void testHundredCompleteEntityWithLastFirstAndMaxSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Tau tau = getTauEntity();

			tau.setFi(getFiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPis().add(getPiEntity());
			tau.getPsis().add(getPsiEntity());
			tau.getPsis().add(getPsiEntity());

			Ro ro = getRoEntity();
			ro.setTau(tau);
			ro.setSigma(getSigmaEntity());

			Ro ro2 = getRoEntity();
			ro2.setTau(tau);
			ro2.setSigma(getSigmaEntity());

			tau.getRos().add(ro);
			tau.getRos().add(ro2);
			entityManager.insertEntity(tau);
		}
		entityManager.commitTransaction();

		List<Tau> entities = (List<Tau>) entityManager.selectEntity(getTauEntity(), 90, 10);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 10);

		Integer tauPrevId = 0;
		Integer fiPrevId = 0;
		Integer piPrevId = 0;
		Integer psiPrevId = 0;
		List<Integer[]> roPrevIds = new ArrayList<Integer[]>();
		for (Tau tau : entities) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must not be null.", tau.getFi() != null);
			Assert.assertTrue("The ros entities must contains 2 entities.", tau.getRos().size() == 2);
			Assert.assertTrue("The pi entities must contains 2 entities.", tau.getPis().size() == 2);
			Assert.assertTrue("The psi entities must contains 2 entities.", tau.getPsis().size() == 2);

			Assert.assertTrue("Tau ids must be different.", !tau.getId().equals(tauPrevId));
			tauPrevId = tau.getId();

			Assert.assertTrue("Fi ids must be different.", !tau.getFi().getId().equals(fiPrevId));
			fiPrevId = tau.getFi().getId();

			for (Pi pi : tau.getPis()) {
				Assert.assertTrue("The pi entity id must not be null.", pi.getId() != null);
				Assert.assertTrue("Pi ids must be different.", !pi.getId().equals(piPrevId));
				piPrevId = pi.getId();
			}

			for (Psi psi : tau.getPsis()) {
				Assert.assertTrue("The psi entity id must not be null.", psi.getId() != null);
				Assert.assertTrue("Psi ids must be different.", !psi.getId().equals(psiPrevId));
				psiPrevId = psi.getId();
			}

			for (Ro ro : tau.getRos()) {
				Assert.assertTrue("The tau entity must not be null.", ro.getTau() != null);
				Assert.assertTrue("The sigma entity must not be null.", ro.getSigma() != null);
				Assert.assertTrue("The tau entity must be equal to its owner.", ro.getTau().getId().equals(tau.getId()));

				Integer[] roId = new Integer[]{ro.getTau().getId(), ro.getSigma().getId()};
				for (Integer[] roPrevId : roPrevIds) {
					Assert.assertTrue("Ro ids must be different.", !Arrays.equals(roPrevId, roId));
				}
				roPrevIds.add(roId);
			}
		}
	}

	@Test(expected = RuntimeException.class)
	public void testNullQueryParamSelect() throws Exception {
		entityManager.selectEntity((QueryParam) null);
	}

	@Test(expected = RuntimeException.class)
	public void testQueryParamEmptyFilterNameSelect() throws Exception {
		entityManager.selectEntity(new QueryParam(Alpha.class, ""));
	}

	@Test(expected = RuntimeException.class)
	public void testQueryParamNullClassSelect() throws Exception {
		entityManager.selectEntity(new QueryParam(null, "AnyName"));
	}

	@Test(expected = RuntimeException.class)
	public void testQueryParamNullFilterNameSelect() throws Exception {
		entityManager.selectEntity(new QueryParam(Alpha.class, null));
	}

	@Test(expected = RuntimeException.class)
	public void testQueryParamInvalidFilterNameSelect() throws Exception {
		entityManager.selectEntity(new QueryParam(Alpha.class, "AnyName"));
	}

	@Test(expected = RuntimeException.class)
	public void testQueryParamWhereAndParamsEmptySelect() throws Exception {
		entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_WHERE_EMPTY));
	}

	@Test(expected = RuntimeException.class)
	public void testQueryParamRepeatedQueryFilterSelect() throws Exception {
		entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_REPEATED_QUERY_FILTER));
	}

	@Test(expected = RuntimeException.class)
	public void testNullQueryParamNullFirstAndMaxSelect() throws Exception {
		entityManager.selectEntity((QueryParam) null, (Integer) null, null);
	}

	@Test(expected = RuntimeException.class)
	public void testQueryParamNegativeFirstAndMaxSelect() throws Exception {
		entityManager.selectEntity(new QueryParam(Alpha.class, "AnyName"), -1, -1);
	}

	@Test
	public void testQueryParamByIdSelect() throws Exception {
		Integer paramId = 0;
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			entityManager.insertEntity(alpha);
			if (i == 50) {
				paramId = alpha.getId();
			}
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_ID).put(Alpha.PARAM_ID, paramId));
		Assert.assertTrue("Entities must contains one element.", entities.size() == 1);
		Assert.assertTrue("Alpha entity id must be the same as selected.", entities.get(0).getId().equals(paramId));
	}

	@Test
	public void testQueryParamByInvalidIdSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_ID).put(Alpha.PARAM_ID, -1));
		Assert.assertTrue("Entities must contains zero elements.", entities.isEmpty());
	}

	@Test
	public void testQueryParamByStringSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_STRING).put(Alpha.PARAM_STRING, STRING));
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testQueryParamByEmptyStringSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_STRING).put(Alpha.PARAM_STRING, ""));
		Assert.assertTrue("Entities must contains zero elements.", entities.isEmpty());
	}

	@Test
	public void testQueryParamByNullStringSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_STRING).put(Alpha.PARAM_STRING, null));
		Assert.assertTrue("Entities must contains zero elements.", entities.isEmpty());
	}

	@Test
	public void testQueryParamByNumberSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_NUMBER).put(Alpha.PARAM_NUMBER, FLOAT));
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testQueryParamByDateSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_DATE)
					.put(Alpha.PARAM_DATE_ONE, new Date(DATE.getTime() - 30000l)).put(Alpha.PARAM_DATE_TWO, new Date(DATE.getTime() + 30000l)));
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testQueryParamByBetweenDateSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_BETWEEN_DATE)
					.put(Alpha.PARAM_DATE_ONE, new Date(DATE.getTime() - 30000l)).put(Alpha.PARAM_DATE_TWO, new Date(DATE.getTime() + 30000l)));
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testQueryParamByIsNullStringSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setString(null);
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_NULL_STRING));
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testQueryParamByNumberAndStringSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_NUMBER_AND_STRING)
																				.put(Alpha.PARAM_NUMBER, FLOAT).put(Alpha.PARAM_STRING, STRING));
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testQueryParamByEmptyNumberSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_NUMBER).put(Alpha.PARAM_NUMBER, ""));
		Assert.assertTrue("Entities must contains zero elements.", entities.isEmpty());
	}

	@Test
	public void testQueryParamByNullNumberSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			entityManager.insertEntity(getAlphaEntity());
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_NUMBER).put(Alpha.PARAM_NUMBER, null));
		Assert.assertTrue("Entities must contains zero elements.", entities.isEmpty());
	}

	@Test
	public void testQueryParamByOneToOneIdSelect() throws Exception {
		Long oneToOneId = 0l;
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			Gama gama = getGamaEntity();
			alpha.setGama(gama);
			entityManager.insertEntity(alpha);
			if (i == 50) {
				oneToOneId = gama.getId();
			}
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_ONE_TO_ONE_ID).put(Alpha.PARAM_ID, oneToOneId));
		Assert.assertTrue("Entities must contains one element.", entities.size() == 1);
		Assert.assertTrue("One to one entity id must be the same as selected.", entities.get(0).getGama().getId().equals(oneToOneId));
	}

	@Test
	public void testQueryParamByOneToOneIdBlockJoinSelect() throws Exception {
		Long oneToOneId = 0l;
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			Gama gama = getGamaEntity();
			alpha.setGama(gama);
			entityManager.insertEntity(alpha);
			if (i == 50) {
				oneToOneId = gama.getId();
			}
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_ONE_TO_ONE_ID).put(Alpha.PARAM_ID, oneToOneId), Gama.class);
		Assert.assertTrue("Entities must contains one element.", entities.size() == 1);
		Assert.assertTrue("One to one entity id must be null.", entities.get(0).getGama() == null);
	}

	@Test
	public void testQueryParamByOneToOneStringSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			Gama gama = getGamaEntity();
			alpha.setGama(gama);
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_ONE_TO_ONE_STRING).put(Alpha.PARAM_STRING, STRING));
		Assert.assertTrue("Entities must contains one element.", entities.size() == 100);

		Long gamaPrevId = 0l;
		Integer alphaPrevId = 0;
		for (Alpha alpha : entities) {
			Assert.assertTrue("The alpha entity must not be null.", alpha != null);
			Assert.assertTrue("The gama entity must not be null.", alpha.getGama() != null);

			Assert.assertTrue("Alpha ids must be different.", !alpha.getId().equals(alphaPrevId));
			alphaPrevId = alpha.getId();

			Assert.assertTrue("Gama ids must be different.", !alpha.getGama().getId().equals(gamaPrevId));
			gamaPrevId = alpha.getGama().getId();
		}
	}

	@Test
	public void testQueryParamByOneToOneFirstAndMaxStringSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			Gama gama = getGamaEntity();
			alpha.setGama(gama);
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_ONE_TO_ONE_STRING).put(Alpha.PARAM_STRING, STRING), 0, 20);
		Assert.assertTrue("Entities must contains one element.", entities.size() == 20);

		Long gamaPrevId = 0l;
		Integer alphaPrevId = 0;
		for (Alpha alpha : entities) {
			Assert.assertTrue("The alpha entity must not be null.", alpha != null);
			Assert.assertTrue("The gama entity must not be null.", alpha.getGama() != null);

			Assert.assertTrue("Alpha ids must be different.", !alpha.getId().equals(alphaPrevId));
			alphaPrevId = alpha.getId();

			Assert.assertTrue("Gama ids must be different.", !alpha.getGama().getId().equals(gamaPrevId));
			gamaPrevId = alpha.getGama().getId();
		}
	}

	@Test
	public void testQueryParamByOneToOneAndManyToManyIdStringSelect() throws Exception {
		Integer deltaId = 0;
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			Gama gama = getGamaEntity();
			Delta delta = getDeltaEntity();
			alpha.setGama(gama);
			gama.getDeltas().add(delta);
			gama.getDeltas().add(getDeltaEntity());
			entityManager.insertEntity(alpha);
			if (i == 50) {
				deltaId = delta.getId();
			}
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_ONE_TO_ONE_DELTA_ID).put(Alpha.PARAM_ID, deltaId));

		Assert.assertTrue("Entities must contains one element.", entities.size() == 1);
		Assert.assertTrue("One to one entity must not be null.", entities.get(0).getGama() != null);
		Assert.assertTrue("One to one entity plus Many to Many entity must have the size equal 1.", entities.get(0).getGama().getDeltas().size() == 1);
		Assert.assertTrue("One to one entity plus Many to Many entity must have the same as selected.", entities.get(0).getGama().getDeltas().get(0).getId().equals(deltaId));
	}

	@Test
	public void testQueryParamByNumberAndStringOrderByAndOrderDirSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setInteger(i);
			alpha.setGama(getGamaEntity());
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_NUMBER_AND_STRING)
																				.put(Alpha.PARAM_NUMBER, FLOAT).put(Alpha.PARAM_STRING, STRING), "integer", OrderType.DESCENDING);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			if (prevId == 0) {
				Assert.assertTrue("First entity must have integer id equal to 99", entity.getInteger().equals(99));
			}
			Assert.assertTrue("Gama entity must not be null", entity.getGama() != null);
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	@Test
	public void testQueryParamByNumberAndStringOrderByAndOrderDirWithBlockJoinSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setInteger(i);
			alpha.setGama(getGamaEntity());
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		List<Alpha> entities = (List<Alpha>) entityManager.selectEntity(new QueryParam(Alpha.class, Alpha.QUERY_BY_NUMBER_AND_STRING)
																				.put(Alpha.PARAM_NUMBER, FLOAT).put(Alpha.PARAM_STRING, STRING), "integer", OrderType.DESCENDING, true);
		Assert.assertTrue("Entities must contains hundred elements.", entities.size() == 100);

		Integer prevId = 0;
		for (Alpha entity : entities) {
			if (prevId == 0) {
				Assert.assertTrue("First entity must have integer id equal to 99", entity.getInteger().equals(99));
			}
			Assert.assertTrue("Gama entity must be null", entity.getGama() == null);
			Assert.assertTrue("Entity ids must be different.", !entity.getId().equals(prevId));
			prevId = entity.getId();
		}
	}

	//############# TESTS FOR SELECTING OBJECT #############


	@Test(expected = RuntimeException.class)
	public void testObjectNullQueryParamSelect() throws Exception {
		entityManager.selectObject((QueryParam) null);
	}

	@Test(expected = RuntimeException.class)
	public void testObjectNullQueryParamNullFirstAndMaxSelect() throws Exception {
		entityManager.selectObject((QueryParam) null, null, null);
	}
	
	@Test(expected = RuntimeException.class)
	public void testObjectQueryParamEmptyFilterNameSelect() throws Exception {
		entityManager.selectObject(new QueryParam(Alpha.class, ""));
	}

	@Test(expected = RuntimeException.class)
	public void testObjectQueryParamNullClassSelect() throws Exception {
		entityManager.selectObject(new QueryParam(null, "AnyName"));
	}

	@Test(expected = RuntimeException.class)
	public void testObjectQueryParamNullFilterNameSelect() throws Exception {
		entityManager.selectObject(new QueryParam(Alpha.class, null));
	}

	@Test(expected = RuntimeException.class)
	public void testObjectQueryParamInvalidFilterNameSelect() throws Exception {
		entityManager.selectObject(new QueryParam(Alpha.class, "AnyName"));
	}

	@Test(expected = RuntimeException.class)
	public void testObjectQueryParamWhereAndParamsEmptySelect() throws Exception {
		entityManager.selectObject(new QueryParam(Alpha.class, Alpha.QUERY_WHERE_EMPTY));
	}

	@Test(expected = RuntimeException.class)
	public void testObjectQueryParamRepeatedQueryFilterSelect() throws Exception {
		entityManager.selectObject(new QueryParam(Alpha.class, Alpha.QUERY_REPEATED_QUERY_FILTER));
	}

	@Test(expected = RuntimeException.class)
	public void testObjectQueryParamNegativeFirstAndMaxSelect() throws Exception {
		entityManager.selectObject(new QueryParam(Alpha.class, "AnyName"), -1, -1);
	}

	@Test
	public void testObjectQueryParamForIntegerAndStringAndFloatSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setInteger(i);
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		List<Object[]> objects = entityManager.selectObject(new QueryParam(Alpha.class, Alpha.QUERY_INTEGER_AND_STRING_AND_FLOAT));
		Assert.assertTrue("Entities must contains hundred elements.", objects.size() == 100);

		for (Object[] object : objects) {
			Assert.assertTrue("Array of objects must contain 3 elements", object.length == 3);
			Assert.assertTrue("Second element must contains an integer", object[0] instanceof Integer);
			Assert.assertTrue("Third element must contains an String", ((String) object[1]).equals(STRING));
			Assert.assertTrue("Third element must contains an String", ((Float) object[2]).equals(FLOAT));
		}
	}

	@Test
	public void testObjectQueryParamForMaximumIntegerAndStringSelect() throws Exception {
		entityManager.initTransaction();
		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setInteger(i);
			entityManager.insertEntity(alpha);
		}
		entityManager.commitTransaction();

		List<Object[]> objects = entityManager.selectObject(new QueryParam(Alpha.class, Alpha.QUERY_MAX_INTEGER_AND_INTEGER_AND_STRING));
		Assert.assertTrue("Entities must contains hundred elements.", objects.size() == 1);

		for (Object[] object : objects) {
			Assert.assertTrue("Array of objects must contain 3 elements", object.length == 3);
			Assert.assertTrue("First element must contains maximum of integer values", ((Integer) object[0]).equals(99));
			Assert.assertTrue("Second element must contains an integer", object[1] instanceof Integer);
			Assert.assertTrue("Third element must contains an String", ((String) object[2]).equals(STRING));
		}
	}

	// TODO: More tests

	//############# TESTS FOR SELECTING SINGLE OBJECT #############


	@Test(expected = RuntimeException.class)
	public void testSingleObjectNullQueryParamSelect() throws Exception {
		entityManager.selectSingleObject((QueryParam) null);
	}
	
	@Test(expected = RuntimeException.class)
	public void testSingleObjectQueryParamEmptyFilterNameSelect() throws Exception {
		entityManager.selectSingleObject(new QueryParam(Alpha.class, ""));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleObjectQueryParamNullClassSelect() throws Exception {
		entityManager.selectSingleObject(new QueryParam(null, "AnyName"));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleObjectQueryParamNullFilterNameSelect() throws Exception {
		entityManager.selectSingleObject(new QueryParam(Alpha.class, null));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleObjectQueryParamInvalidFilterNameSelect() throws Exception {
		entityManager.selectSingleObject(new QueryParam(Alpha.class, "AnyName"));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleObjectQueryParamWhereAndParamsEmptySelect() throws Exception {
		entityManager.selectSingleObject(new QueryParam(Alpha.class, Alpha.QUERY_WHERE_EMPTY));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleObjectQueryParamRepeatedQueryFilterSelect() throws Exception {
		entityManager.selectSingleObject(new QueryParam(Alpha.class, Alpha.QUERY_REPEATED_QUERY_FILTER));
	}

	// TODO: More tests

	//############# TESTS FOR SELECTING SINGLE ENTITY #############


	@Test(expected = RuntimeException.class)
	public void testNullSingleEntitySelect() throws Exception {
		entityManager.selectSingleEntity((Entity) null);
	}

	@Test
	public void testSingleEntitySelect() throws Exception {
		Alpha alpha = (Alpha) entityManager.selectSingleEntity(new Alpha());
		Assert.assertTrue("Alpha must be null.", alpha == null);
	}

	// TODO: More tests


	@Test(expected = RuntimeException.class)
	public void testSingleEntityNullQueryParamSelect() throws Exception {
		entityManager.selectSingleEntity((QueryParam) null);
	}

	@Test(expected = RuntimeException.class)
	public void testSingleEntityQueryParamEmptyFilterNameSelect() throws Exception {
		entityManager.selectSingleEntity(new QueryParam(Alpha.class, ""));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleEntityQueryParamNullClassSelect() throws Exception {
		entityManager.selectSingleEntity(new QueryParam(null, "AnyName"));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleEntityQueryParamNullFilterNameSelect() throws Exception {
		entityManager.selectSingleEntity(new QueryParam(Alpha.class, null));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleEntityQueryParamInvalidFilterNameSelect() throws Exception {
		entityManager.selectSingleEntity(new QueryParam(Alpha.class, "AnyName"));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleEntityQueryParamWhereAndParamsEmptySelect() throws Exception {
		entityManager.selectSingleEntity(new QueryParam(Alpha.class, Alpha.QUERY_WHERE_EMPTY));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleEntityQueryParamRepeatedQueryFilterSelect() throws Exception {
		entityManager.selectSingleEntity(new QueryParam(Alpha.class, Alpha.QUERY_REPEATED_QUERY_FILTER));
	}

	// TODO: More tests


	//############# TESTS FOR SELECTING SINGLE ARRAY #############


	@Test(expected = RuntimeException.class)
	public void testSingleArrayNullQueryParamSelect() throws Exception {
		entityManager.selectSingleArray((QueryParam) null);
	}

	@Test(expected = RuntimeException.class)
	public void testSingleArrayQueryParamEmptyFilterNameSelect() throws Exception {
		entityManager.selectSingleArray(new QueryParam(Alpha.class, ""));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleArrayQueryParamNullClassSelect() throws Exception {
		entityManager.selectSingleArray(new QueryParam(null, "AnyName"));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleArrayQueryParamNullFilterNameSelect() throws Exception {
		entityManager.selectSingleArray(new QueryParam(Alpha.class, null));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleArrayQueryParamInvalidFilterNameSelect() throws Exception {
		entityManager.selectSingleArray(new QueryParam(Alpha.class, "AnyName"));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleArrayQueryParamWhereAndParamsEmptySelect() throws Exception {
		entityManager.selectSingleArray(new QueryParam(Alpha.class, Alpha.QUERY_WHERE_EMPTY));
	}

	@Test(expected = RuntimeException.class)
	public void testSingleArrayQueryParamRepeatedQueryFilterSelect() throws Exception {
		entityManager.selectSingleArray(new QueryParam(Alpha.class, Alpha.QUERY_REPEATED_QUERY_FILTER));
	}

	// TODO: More tests


	//############# TESTS FOR SELECT USING NATIVE QUERY #############


	@Test(expected = RuntimeException.class)
	public void testNullNativeQuerySelect() throws Exception {
		entityManager.executeNativeQuery(null, null);
	}

	@Test(expected = RuntimeException.class)
	public void testEmptyNativeQueryWithNullParamsSelect() throws Exception {
		entityManager.executeNativeQuery("", null);
	}

	@Test(expected = RuntimeException.class)
	public void testEmptyNativeQueryWithEmptyParamsSelect() throws Exception {
		entityManager.executeNativeQuery("", new Object[]{});
	}

	@Test(expected = RuntimeException.class)
	public void testNativeQueryWithEmptyParamsSelect() throws Exception {
		entityManager.executeNativeQuery("select * from iota where id = ?", new Object[]{});
	}

	@Test(expected = RuntimeException.class)
	public void testNativeQueryWithParamsSelect() throws Exception {
		entityManager.executeNativeQuery("select * from iota", new Object[]{"1"});
	}

	// TODO: More tests

}
