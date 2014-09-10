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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

@SuppressWarnings("unchecked")
public class BatchUpdateTest extends AbstractTest {

	private static final Integer INTEGER_UPDATE = 30;
	private static final Boolean BOOLEAN_UPDATE = false;
	private static final BigDecimal DECIMAL_UPDATE = new BigDecimal(50);
	private static final Double DOUBLE_UPDATE = 40.5d;
	private static final Float FLOAT_UPDATE = 100.0f;
	private static final Long LONG_UPDATE = 3000l;
	protected static final String STRING_UPDATE = "String updated";
	protected static final String TEXT_UPDATE = "A looooooonnnnnnnnnng text that could be used in the database are now updated.";
	protected static final Date DATE_UPDATE = new Date(1405911599000l);

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
	public void testNullListEntityBatchUpdate() throws Exception {
		entityManager.updateBatchEntity(null);
	}

	@Test
	public void testBasicListEntityBatchUpdate() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			betas.add(getBetaEntity());
		}

		entityManager.insertBatchEntity(betas);

		for (Beta beta : betas) {
			beta.setInteger(INTEGER_UPDATE);
			beta.setBool(BOOLEAN_UPDATE);
			beta.setDecimal(DECIMAL_UPDATE);
			beta.setDoubli(DOUBLE_UPDATE);
			beta.setFloati(FLOAT_UPDATE);
			beta.setLongi(LONG_UPDATE);
			beta.setString(STRING_UPDATE);
			beta.setText(TEXT_UPDATE);
		}

		entityManager.updateBatchEntity(betas);

		Beta betax = getBetaEntity();
		betax.setInteger(INTEGER_UPDATE);
		betax.setBool(BOOLEAN_UPDATE);
		betax.setDecimal(DECIMAL_UPDATE);
		betax.setDoubli(DOUBLE_UPDATE);
		betax.setFloati(FLOAT_UPDATE);
		betax.setLongi(LONG_UPDATE);
		betax.setString(STRING_UPDATE);
		betax.setText(TEXT_UPDATE);

		betas = (List<Beta>) entityManager.selectEntity(betax);

		Assert.assertTrue("The list of entities must have 100 elements", betas.size() == 100);

		for (Beta beta : betas) {
			Assert.assertTrue("The beta entity must not be null.", beta != null);
			Assert.assertTrue("The beta entity must have an id.", beta.getId() != null);
			Assert.assertTrue("The beta entity must have updated Integer value.", beta.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The beta entity must have updated Boolean value.", beta.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The beta entity must have updated Decimal value.", beta.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The beta entity must have updated Double value.", beta.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The beta entity must have updated Float value.", beta.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The beta entity must have updated String value.", beta.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The beta entity must have updated Text value.", beta.getText().equals(TEXT_UPDATE));
		}
	}

	@Test
	public void testBasicEntityWithIntegerListEntityBatchUpdate() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			betas.add(getBetaEntity());
		}

		entityManager.insertBatchEntity(betas);

		for (Beta beta : betas) {
			beta.setInteger(INTEGER_UPDATE);
		}

		entityManager.updateBatchEntity(betas);

		Beta betax = getBetaEntity();
		betax.setInteger(INTEGER_UPDATE);
		betas = (List<Beta>) entityManager.selectEntity(betax);

		Assert.assertTrue("The list of entities must have 100 elements", betas.size() == 100);

		for (Beta beta : betas) {
			Assert.assertTrue("The beta entity must not be null.", beta != null);
			Assert.assertTrue("The beta entity must have an id.", beta.getId() != null);
			Assert.assertTrue("The beta entity must have updated Integer value.", beta.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The beta entity must not have updated Boolean value.", beta.getBool().equals(BOOLEAN));
			Assert.assertTrue("The beta entity must not have updated Decimal value.", beta.getDecimal().equals(DECIMAL));
			Assert.assertTrue("The beta entity must not have updated Double value.", beta.getDoubli().equals(DOUBLE));
			Assert.assertTrue("The beta entity must not have updated Float value.", beta.getFloati().equals(FLOAT));
			Assert.assertTrue("The beta entity must not have updated String value.", beta.getString().equals(STRING));
			Assert.assertTrue("The beta entity must not have updated Text value.", beta.getText().equals(TEXT));
		}
	}

	@Test
	public void testEmptyEntityListEntityBatchUpdate() throws Exception {
		List<Omega> omegas = new ArrayList<Omega>();

		for (int i = 0; i < 100; i++) {
			omegas.add(new Omega());
		}

		entityManager.updateBatchEntity(omegas);
	}

	@Test
	public void testNullAttributesListEntityBatchUpdate() throws Exception {
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

		entityManager.updateBatchEntity(betas);

		Beta betax = getBetaEntity();
		betax.setInteger(null);
		betax.setBool(null);
		betax.setDecimal(null);
		betax.setDoubli(null);
		betax.setFloati(null);
		betax.setLongi(null);
		betax.setString(null);
		betax.setText(null);
		betas = (List<Beta>) entityManager.selectEntity(betax);

		Assert.assertTrue("The list of entities must have 100 elements", betas.size() == 100);

		for (Beta beta : betas) {
			Assert.assertTrue("The beta entity must not be null.", beta != null);
			Assert.assertTrue("The beta entity must have an id.", beta.getId() != null);
			Assert.assertTrue("The beta entity must have updated Integer value null.", beta.getInteger() == null);
			Assert.assertTrue("The beta entity must have updated Boolean value null.", beta.getBool() == null);
			Assert.assertTrue("The beta entity must have updated Decimal value null.", beta.getDecimal() == null);
			Assert.assertTrue("The beta entity must have updated Double value null.", beta.getDoubli() == null);
			Assert.assertTrue("The beta entity must have updated Float value null.", beta.getFloati() == null);
			Assert.assertTrue("The beta entity must have updated String value null.", beta.getString() == null);
			Assert.assertTrue("The beta entity must have updated Text value null.", beta.getText() == null);
		}
	}

	@Test(expected = RuntimeException.class)
	public void testColumnExceedLengthListEntityBatchUpdate() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			betas.add(getBetaEntity());
		}

		entityManager.insertBatchEntity(betas);

		for (Beta beta : betas) {
			beta.setString(STRING_50);
		}

		entityManager.updateBatchEntity(betas);
	}

	@Test
	public void testTransientListEntityBatchUpdate() throws Exception {
		List<Beta> betas = new ArrayList<Beta>();

		for (int i = 0; i < 100; i++) {
			betas.add(getBetaEntity());
		}

		entityManager.insertBatchEntity(betas);

		for (Beta beta : betas) {
			beta.setTrans("Transient value updated");
		}

		entityManager.updateBatchEntity(betas);

		betas = (List<Beta>) entityManager.selectEntity(getBetaEntity());

		Assert.assertTrue("The list of entities must have 100 elements", betas.size() == 100);

		for (Beta beta : betas) {
			Assert.assertTrue("The beta entity must not be null.", beta != null);
			Assert.assertTrue("The beta entity must have an id.", beta.getId() != null);
			Assert.assertTrue("The beta entity must not have transiente value.", beta.getTrans() == null);
			Assert.assertTrue("The beta entity must not have updated Integer value.", beta.getInteger().equals(INTEGER));
			Assert.assertTrue("The beta entity must not have updated Boolean value.", beta.getBool().equals(BOOLEAN));
			Assert.assertTrue("The beta entity must not have updated Decimal value.", beta.getDecimal().equals(DECIMAL));
			Assert.assertTrue("The beta entity must not have updated Double value.", beta.getDoubli().equals(DOUBLE));
			Assert.assertTrue("The beta entity must not have updated Float value.", beta.getFloati().equals(FLOAT));
			Assert.assertTrue("The beta entity must not have updated String value.", beta.getString().equals(STRING));
			Assert.assertTrue("The beta entity must not have updated Text value.", beta.getText().equals(TEXT));
		}
	}

	@Test
	public void testJoinListEntityBatchUpdate() throws Exception {
		List<Iota> iotas = new ArrayList<Iota>();

		for (int i = 0; i < 100; i++) {
			Iota iota = getIotaEntity();
			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(getEpsilonEntity());
			iotas.add(iota);
		}

		entityManager.insertBatchEntity(iotas);

		for (Iota iota : iotas) {
			iota.setInteger(INTEGER_UPDATE);
			iota.setBool(BOOLEAN_UPDATE);
			iota.setDecimal(DECIMAL_UPDATE);
			iota.setDoubli(DOUBLE_UPDATE);
			iota.setFloati(FLOAT_UPDATE);
			iota.setLongi(LONG_UPDATE);
			iota.setString(STRING_UPDATE);
			iota.setText(TEXT_UPDATE);
			
			iota.getAlpha().setInteger(INTEGER_UPDATE);
			iota.getAlpha().setBool(BOOLEAN_UPDATE);
			iota.getAlpha().setDecimal(DECIMAL_UPDATE);
			iota.getAlpha().setDoubli(DOUBLE_UPDATE);
			iota.getAlpha().setFloati(FLOAT_UPDATE);
			iota.getAlpha().setLongi(LONG_UPDATE);
			iota.getAlpha().setString(STRING_UPDATE);
			iota.getAlpha().setText(TEXT_UPDATE);
			iota.getAlpha().setDate(DATE_UPDATE);
			
			iota.getEpsilon().setInteger(INTEGER_UPDATE);
			iota.getEpsilon().setBool(BOOLEAN_UPDATE);
			iota.getEpsilon().setDecimal(DECIMAL_UPDATE);
			iota.getEpsilon().setDoubli(DOUBLE_UPDATE);
			iota.getEpsilon().setFloati(FLOAT_UPDATE);
			iota.getEpsilon().setLongi(LONG_UPDATE);
			iota.getEpsilon().setString(STRING_UPDATE);
			iota.getEpsilon().setText(TEXT_UPDATE);
		}

		entityManager.updateBatchEntity(iotas);

		Iota iotax = getIotaEntity();
		iotax.setInteger(INTEGER_UPDATE);
		iotax.setBool(BOOLEAN_UPDATE);
		iotax.setDecimal(DECIMAL_UPDATE);
		iotax.setDoubli(DOUBLE_UPDATE);
		iotax.setFloati(FLOAT_UPDATE);
		iotax.setLongi(LONG_UPDATE);
		iotax.setString(STRING_UPDATE);
		iotax.setText(TEXT_UPDATE);
		iotas = (List<Iota>) entityManager.selectEntity(iotax);

		Assert.assertTrue("The list of entities must have 100 elements", iotas.size() == 100);

		for (Iota iota : iotas) {
			Assert.assertTrue("The iota entity must not be null.", iota != null);
			Assert.assertTrue("The alpha entity must not be null.", iota.getAlpha() != null);
			Assert.assertTrue("The epsilon entity must not be null.", iota.getEpsilon() != null);
			
			Assert.assertTrue("The alpha entity must have id.", iota.getAlpha().getId() != null);
			Assert.assertTrue("The epsilon entity must have id.", iota.getEpsilon().getId() != null);
			
			Assert.assertTrue("The iota entity must have updated Integer value.", iota.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The iota entity must have updated Boolean value.", iota.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The iota entity must have updated Decimal value.", iota.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The iota entity must have updated Double value.", iota.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The iota entity must have updated Float value.", iota.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The iota entity must have updated String value.", iota.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The iota entity must have updated Text value.", iota.getText().equals(TEXT_UPDATE));
			
			Assert.assertTrue("The alpha entity must have updated Integer value.", iota.getAlpha().getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Boolean value.", iota.getAlpha().getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Decimal value.", iota.getAlpha().getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Double value.", iota.getAlpha().getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Float value.", iota.getAlpha().getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The alpha entity must have updated String value.", iota.getAlpha().getString().equals(STRING_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Text value.", iota.getAlpha().getText().equals(TEXT_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Date value.", iota.getAlpha().getDate().getTime() == DATE_UPDATE.getTime());
			
			Assert.assertTrue("The epsilon entity must have updated Integer value.", iota.getEpsilon().getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Boolean value.", iota.getEpsilon().getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Decimal value.", iota.getEpsilon().getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Double value.", iota.getEpsilon().getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Float value.", iota.getEpsilon().getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated String value.", iota.getEpsilon().getString().equals(STRING_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Text value.", iota.getEpsilon().getText().equals(TEXT_UPDATE));
		}
	}

	@Test
	public void testCascadeOneToOneListEntityBatchUpdate() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setGama(getGamaEntity());
			alphas.add(alpha);
		}

		entityManager.insertBatchEntity(alphas);

		for (Alpha alpha : alphas) {
			alpha.setInteger(INTEGER_UPDATE);
			alpha.setBool(BOOLEAN_UPDATE);
			alpha.setDecimal(DECIMAL_UPDATE);
			alpha.setDoubli(DOUBLE_UPDATE);
			alpha.setFloati(FLOAT_UPDATE);
			alpha.setLongi(LONG_UPDATE);
			alpha.setString(STRING_UPDATE);
			alpha.setText(TEXT_UPDATE);
			alpha.setDate(DATE_UPDATE);
			
			alpha.getGama().setInteger(INTEGER_UPDATE);
			alpha.getGama().setBool(BOOLEAN_UPDATE);
			alpha.getGama().setDecimal(DECIMAL_UPDATE);
			alpha.getGama().setDoubli(DOUBLE_UPDATE);
			alpha.getGama().setFloati(FLOAT_UPDATE);
			alpha.getGama().setLongi(LONG_UPDATE);
			alpha.getGama().setString(STRING_UPDATE);
			alpha.getGama().setText(TEXT_UPDATE);
		}

		entityManager.updateBatchEntity(alphas);

		Alpha alphax = getAlphaEntity();
		alphax.setInteger(INTEGER_UPDATE);
		alphax.setBool(BOOLEAN_UPDATE);
		alphax.setDecimal(DECIMAL_UPDATE);
		alphax.setDoubli(DOUBLE_UPDATE);
		alphax.setFloati(FLOAT_UPDATE);
		alphax.setLongi(LONG_UPDATE);
		alphax.setString(STRING_UPDATE);
		alphax.setText(TEXT_UPDATE);
		alphax.setDate(DATE_UPDATE);
		alphas = (List<Alpha>) entityManager.selectEntity(alphax);

		Assert.assertTrue("The list of entities must have 100 elements", alphas.size() == 100);

		for (Alpha alpha : alphas) {
			Assert.assertTrue("The alpha entity must not be null.", alpha != null);
			Assert.assertTrue("The gama entity must not be null.", alpha.getGama() != null);
	
			Assert.assertTrue("The alpha entity must have id.", alpha.getId() != null);
			Assert.assertTrue("The gama entity must have id.", alpha.getGama().getId() != null);
	
			Assert.assertTrue("The alpha entity must have updated Integer value.", alpha.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Boolean value.", alpha.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Decimal value.", alpha.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Double value.", alpha.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Float value.", alpha.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The alpha entity must have updated String value.", alpha.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Text value.", alpha.getText().equals(TEXT_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Date value.", alpha.getDate().getTime() == DATE_UPDATE.getTime());
			
			Assert.assertTrue("The gama entity must have updated Integer value.", alpha.getGama().getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The gama entity must have updated Boolean value.", alpha.getGama().getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The gama entity must have updated Decimal value.", alpha.getGama().getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The gama entity must have updated Double value.", alpha.getGama().getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The gama entity must have updated Float value.", alpha.getGama().getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The gama entity must have updated String value.", alpha.getGama().getString().equals(STRING_UPDATE));
			Assert.assertTrue("The gama entity must have updated Text value.", alpha.getGama().getText().equals(TEXT_UPDATE));
		}
	}

	@Test
	public void testCascadeOneToManyListEntityBatchUpdate() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.getBetas().add(getBetaEntity());
			alpha.getBetas().add(getBetaEntity());
			alphas.add(alpha);
		}
		
		entityManager.insertBatchEntity(alphas);

		for (Alpha alpha : alphas) {
			alpha.setInteger(INTEGER_UPDATE);
			alpha.setBool(BOOLEAN_UPDATE);
			alpha.setDecimal(DECIMAL_UPDATE);
			alpha.setDoubli(DOUBLE_UPDATE);
			alpha.setFloati(FLOAT_UPDATE);
			alpha.setLongi(LONG_UPDATE);
			alpha.setString(STRING_UPDATE);
			alpha.setText(TEXT_UPDATE);
			alpha.setDate(DATE_UPDATE);

			for (Beta beta : alpha.getBetas()) {
				beta.setInteger(INTEGER_UPDATE);
				beta.setBool(BOOLEAN_UPDATE);
				beta.setDecimal(DECIMAL_UPDATE);
				beta.setDoubli(DOUBLE_UPDATE);
				beta.setFloati(FLOAT_UPDATE);
				beta.setLongi(LONG_UPDATE);
				beta.setString(STRING_UPDATE);
				beta.setText(TEXT_UPDATE);
			}
		}

		entityManager.updateBatchEntity(alphas);

		Alpha alphax = getAlphaEntity();
		alphax.setInteger(INTEGER_UPDATE);
		alphax.setBool(BOOLEAN_UPDATE);
		alphax.setDecimal(DECIMAL_UPDATE);
		alphax.setDoubli(DOUBLE_UPDATE);
		alphax.setFloati(FLOAT_UPDATE);
		alphax.setLongi(LONG_UPDATE);
		alphax.setString(STRING_UPDATE);
		alphax.setText(TEXT_UPDATE);
		alphax.setDate(DATE_UPDATE);
		alphas = (List<Alpha>) entityManager.selectEntity(alphax);

		Assert.assertTrue("The list of entities must have 100 elements", alphas.size() == 100);

		for (Alpha alpha : alphas) {
			Assert.assertTrue("The alpha entity must not be null.", alpha != null);
			Assert.assertTrue("The beta entities must contains 2 entities.", alpha.getBetas().size() == 2);
	
			Assert.assertTrue("The alpha entity must have id.", alpha.getId() != null);
	
			Assert.assertTrue("The alpha entity must have updated Integer value.", alpha.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Boolean value.", alpha.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Decimal value.", alpha.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Double value.", alpha.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Float value.", alpha.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The alpha entity must have updated String value.", alpha.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Text value.", alpha.getText().equals(TEXT_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Date value.", alpha.getDate().getTime() == DATE_UPDATE.getTime());
	
			for (Beta betax : alpha.getBetas()) {
				Assert.assertTrue("The beta entity id must not be null.", betax.getId() != null);
				Assert.assertTrue("The beta entity must have updated Integer value.", betax.getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The beta entity must have updated Boolean value.", betax.getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The beta entity must have updated Decimal value.", betax.getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The beta entity must have updated Double value.", betax.getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The beta entity must have updated Float value.", betax.getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The beta entity must have updated String value.", betax.getString().equals(STRING_UPDATE));
				Assert.assertTrue("The beta entity must have updated Text value.", betax.getText().equals(TEXT_UPDATE));
			}
		}
	}

	@Test
	public void testCascadeManyToManyListEntityBatchUpdate() throws Exception {
		List<Gama> gamas = new ArrayList<Gama>();

		for (int i = 0; i < 100; i++) {
			Gama gama = getGamaEntity();
			gama.getDeltas().add(getDeltaEntity());
			gama.getDeltas().add(getDeltaEntity());
			gamas.add(gama);
		}

		entityManager.insertBatchEntity(gamas);

		for (Gama gama : gamas) {
			gama.setInteger(INTEGER_UPDATE);
			gama.setBool(BOOLEAN_UPDATE);
			gama.setDecimal(DECIMAL_UPDATE);
			gama.setDoubli(DOUBLE_UPDATE);
			gama.setFloati(FLOAT_UPDATE);
			gama.setLongi(LONG_UPDATE);
			gama.setString(STRING_UPDATE);
			gama.setText(TEXT_UPDATE);

			for (Delta delta : gama.getDeltas()) {
				delta.setInteger(INTEGER_UPDATE);
				delta.setBool(BOOLEAN_UPDATE);
				delta.setDecimal(DECIMAL_UPDATE);
				delta.setDoubli(DOUBLE_UPDATE);
				delta.setFloati(FLOAT_UPDATE);
				delta.setLongi(LONG_UPDATE);
				delta.setString(STRING_UPDATE);
				delta.setText(TEXT_UPDATE);
			}
		}

		entityManager.updateBatchEntity(gamas);

		Gama gamax = getGamaEntity();
		gamax.setInteger(INTEGER_UPDATE);
		gamax.setBool(BOOLEAN_UPDATE);
		gamax.setDecimal(DECIMAL_UPDATE);
		gamax.setDoubli(DOUBLE_UPDATE);
		gamax.setFloati(FLOAT_UPDATE);
		gamax.setLongi(LONG_UPDATE);
		gamax.setString(STRING_UPDATE);
		gamax.setText(TEXT_UPDATE);
		gamas = (List<Gama>) entityManager.selectEntity(gamax);

		Assert.assertTrue("The list of entities must have 100 elements", gamas.size() == 100);

		for (Gama gama : gamas) {
			Assert.assertTrue("The gama entity must not be null.", gama != null);
			Assert.assertTrue("The delta entities must contains 2 entities.", gama.getDeltas().size() == 2);
	
			Assert.assertTrue("The gama entity must have id.", gama.getId() != null);
	
			Assert.assertTrue("The gama entity must have updated Integer value.", gama.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The gama entity must have updated Boolean value.", gama.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The gama entity must have updated Decimal value.", gama.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The gama entity must have updated Double value.", gama.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The gama entity must have updated Float value.", gama.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The gama entity must have updated String value.", gama.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The gama entity must have updated Text value.", gama.getText().equals(TEXT_UPDATE));
	
			for (Delta deltax : gama.getDeltas()) {
				Assert.assertTrue("The delta entity id must not be null.", deltax.getId() != null);
				Assert.assertTrue("The delta entity must have updated Integer value.", deltax.getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The delta entity must have updated Boolean value.", deltax.getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The delta entity must have updated Decimal value.", deltax.getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The delta entity must have updated Double value.", deltax.getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The delta entity must have updated Float value.", deltax.getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The delta entity must have updated String value.", deltax.getString().equals(STRING_UPDATE));
				Assert.assertTrue("The delta entity must have updated Text value.", deltax.getText().equals(TEXT_UPDATE));
			}
		}
	}

	@Test
	public void testCascadeJoinManyToManyListEntityBatchUpdate() throws Exception {
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

		for (Epsilon epsilon : epsilons) {
			for (Iota iota : epsilon.getIotas()) {
				epsilon.setInteger(INTEGER_UPDATE);
				epsilon.setBool(BOOLEAN_UPDATE);
				epsilon.setDecimal(DECIMAL_UPDATE);
				epsilon.setDoubli(DOUBLE_UPDATE);
				epsilon.setFloati(FLOAT_UPDATE);
				epsilon.setLongi(LONG_UPDATE);
				epsilon.setString(STRING_UPDATE);
				epsilon.setText(TEXT_UPDATE);

				iota.setInteger(INTEGER_UPDATE);
				iota.setBool(BOOLEAN_UPDATE);
				iota.setDecimal(DECIMAL_UPDATE);
				iota.setDoubli(DOUBLE_UPDATE);
				iota.setFloati(FLOAT_UPDATE);
				iota.setLongi(LONG_UPDATE);
				iota.setString(STRING_UPDATE);
				iota.setText(TEXT_UPDATE);
				
				iota.getAlpha().setInteger(INTEGER_UPDATE);
				iota.getAlpha().setBool(BOOLEAN_UPDATE);
				iota.getAlpha().setDecimal(DECIMAL_UPDATE);
				iota.getAlpha().setDoubli(DOUBLE_UPDATE);
				iota.getAlpha().setFloati(FLOAT_UPDATE);
				iota.getAlpha().setLongi(LONG_UPDATE);
				iota.getAlpha().setString(STRING_UPDATE);
				iota.getAlpha().setText(TEXT_UPDATE);
				iota.getAlpha().setDate(DATE_UPDATE);
			}
		}

		entityManager.updateBatchEntity(epsilons);

		Epsilon epsilonx = getEpsilonEntity();
		epsilonx.setInteger(INTEGER_UPDATE);
		epsilonx.setBool(BOOLEAN_UPDATE);
		epsilonx.setDecimal(DECIMAL_UPDATE);
		epsilonx.setDoubli(DOUBLE_UPDATE);
		epsilonx.setFloati(FLOAT_UPDATE);
		epsilonx.setLongi(LONG_UPDATE);
		epsilonx.setString(STRING_UPDATE);
		epsilonx.setText(TEXT_UPDATE);
		epsilons = (List<Epsilon>) entityManager.selectEntity(epsilonx);

		Assert.assertTrue("The list of entities must have 100 elements", epsilons.size() == 100);
		
		for (Epsilon epsilon : epsilons) {
			Assert.assertTrue("The epsilon entity must not be null.", epsilon != null);
			Assert.assertTrue("The iota entities must contains 2 entities.", epsilon.getIotas().size() == 2);
	
			Assert.assertTrue("The epsilonId entity must have id.", epsilon.getId() != null);
	
			Assert.assertTrue("The epsilon entity must have updated Integer value.", epsilon.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Boolean value.", epsilon.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Decimal value.", epsilon.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Double value.", epsilon.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Float value.", epsilon.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated String value.", epsilon.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Text value.", epsilon.getText().equals(TEXT_UPDATE));
	
			for (Iota iotax : epsilon.getIotas()) {
				Assert.assertTrue("The alpha entity must not be null.", iotax.getAlpha() != null);
				Assert.assertTrue("The epsilon entity must not be null.", iotax.getEpsilon() != null);
	
				Assert.assertTrue("The iota entity must have updated Integer value.", iotax.getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The iota entity must have updated Boolean value.", iotax.getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The iota entity must have updated Decimal value.", iotax.getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The iota entity must have updated Double value.", iotax.getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The iota entity must have updated Float value.", iotax.getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The iota entity must have updated String value.", iotax.getString().equals(STRING_UPDATE));
				Assert.assertTrue("The iota entity must have updated Text value.", iotax.getText().equals(TEXT_UPDATE));
	
				Assert.assertTrue("The alpha entity must have updated Integer value.", iotax.getAlpha().getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The alpha entity must have updated Boolean value.", iotax.getAlpha().getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The alpha entity must have updated Decimal value.", iotax.getAlpha().getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The alpha entity must have updated Double value.", iotax.getAlpha().getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The alpha entity must have updated Float value.", iotax.getAlpha().getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The alpha entity must have updated String value.", iotax.getAlpha().getString().equals(STRING_UPDATE));
				Assert.assertTrue("The alpha entity must have updated Text value.", iotax.getAlpha().getText().equals(TEXT_UPDATE));
				Assert.assertTrue("The alpha entity must have updated Date value.", iotax.getAlpha().getDate().getTime() == DATE_UPDATE.getTime());
	
				Assert.assertTrue("The epsilonId entity must have same id on join entity.", iotax.getEpsilon().getId().equals(epsilon.getId()));
				Assert.assertTrue("The epsilon entity must have updated Integer value.", iotax.getEpsilon().getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated Boolean value.", iotax.getEpsilon().getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated Decimal value.", iotax.getEpsilon().getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated Double value.", iotax.getEpsilon().getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated Float value.", iotax.getEpsilon().getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated String value.", iotax.getEpsilon().getString().equals(STRING_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated Text value.", iotax.getEpsilon().getText().equals(TEXT_UPDATE));
			}
		}
	}

	@Test
	public void testCascadeCompleteListEntityBatchUpdate() throws Exception {
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
			tau.setInteger(INTEGER_UPDATE);
			tau.setBool(BOOLEAN_UPDATE);
			tau.setDecimal(DECIMAL_UPDATE);
			tau.setDoubli(DOUBLE_UPDATE);
			tau.setFloati(FLOAT_UPDATE);
			tau.setLongi(LONG_UPDATE);
			tau.setString(STRING_UPDATE);
			tau.setText(TEXT_UPDATE);
	
			tau.getFi().setInteger(INTEGER_UPDATE);
			tau.getFi().setBool(BOOLEAN_UPDATE);
			tau.getFi().setDecimal(DECIMAL_UPDATE);
			tau.getFi().setDoubli(DOUBLE_UPDATE);
			tau.getFi().setFloati(FLOAT_UPDATE);
			tau.getFi().setLongi(LONG_UPDATE);
			tau.getFi().setString(STRING_UPDATE);
			tau.getFi().setText(TEXT_UPDATE);
	
			for (Psi psi : tau.getPsis()) {
				psi.setInteger(INTEGER_UPDATE);
				psi.setBool(BOOLEAN_UPDATE);
				psi.setDecimal(DECIMAL_UPDATE);
				psi.setDoubli(DOUBLE_UPDATE);
				psi.setFloati(FLOAT_UPDATE);
				psi.setLongi(LONG_UPDATE);
				psi.setString(STRING_UPDATE);
				psi.setText(TEXT_UPDATE);
			}

			for (Pi pi : tau.getPis()) {
				pi.setInteger(INTEGER_UPDATE);
				pi.setBool(BOOLEAN_UPDATE);
				pi.setDecimal(DECIMAL_UPDATE);
				pi.setDoubli(DOUBLE_UPDATE);
				pi.setFloati(FLOAT_UPDATE);
				pi.setLongi(LONG_UPDATE);
				pi.setString(STRING_UPDATE);
				pi.setText(TEXT_UPDATE);
			}
	
			for (Ro ro : tau.getRos()) {
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
		}

		entityManager.updateBatchEntity(taus);

		Tau taux = getTauEntity();
		taux.setInteger(INTEGER_UPDATE);
		taux.setBool(BOOLEAN_UPDATE);
		taux.setDecimal(DECIMAL_UPDATE);
		taux.setDoubli(DOUBLE_UPDATE);
		taux.setFloati(FLOAT_UPDATE);
		taux.setLongi(LONG_UPDATE);
		taux.setString(STRING_UPDATE);
		taux.setText(TEXT_UPDATE);
		taus = (List<Tau>) entityManager.selectEntity(taux);

		Assert.assertTrue("The list of entities must have 100 elements", taus.size() == 100);

		for (Tau tau : taus) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must not be null.", tau.getFi() != null);
			Assert.assertTrue("The ros entities must contains 2 entities.", tau.getRos().size() == 2);
			Assert.assertTrue("The pi entities must contains 2 entities.", tau.getPis().size() == 2);
			Assert.assertTrue("The psi entities must contains 2 entities.", tau.getPsis().size() == 2);
	
			Assert.assertTrue("The tauId entity must have id.", tau.getId() != null);
			Assert.assertTrue("The gama entity must have id.", tau.getFi() != null);
	
			Assert.assertTrue("The tau entity must have updated Integer value.", tau.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The tau entity must have updated Boolean value.", tau.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The tau entity must have updated Decimal value.", tau.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The tau entity must have updated Double value.", tau.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The tau entity must have updated Float value.", tau.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The tau entity must have updated String value.", tau.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The tau entity must have updated Text value.", tau.getText().equals(TEXT_UPDATE));
	
			Assert.assertTrue("The fi entity must have updated Integer value.", tau.getFi().getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The fi entity must have updated Boolean value.", tau.getFi().getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The fi entity must have updated Decimal value.", tau.getFi().getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The fi entity must have updated Double value.", tau.getFi().getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The fi entity must have updated Float value.", tau.getFi().getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The fi entity must have updated String value.", tau.getFi().getString().equals(STRING_UPDATE));
			Assert.assertTrue("The fi entity must have updated Text value.", tau.getFi().getText().equals(TEXT_UPDATE));
	
			for (Pi pix : tau.getPis()) {
				Assert.assertTrue("The pi entity id must not be null.", pix.getId() != null);
				Assert.assertTrue("The pi entity must have updated Integer value.", pix.getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The pi entity must have updated Boolean value.", pix.getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The pi entity must have updated Decimal value.", pix.getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The pi entity must have updated Double value.", pix.getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The pi entity must have updated Float value.", pix.getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The pi entity must have updated String value.", pix.getString().equals(STRING_UPDATE));
				Assert.assertTrue("The pi entity must have updated Text value.", pix.getText().equals(TEXT_UPDATE));
			}
	
			for (Psi psix : tau.getPsis()) {
				Assert.assertTrue("The psi entity id must not be null.", psix.getId() != null);
				Assert.assertTrue("The psi entity must have updated Integer value.", psix.getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The psi entity must have updated Boolean value.", psix.getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The psi entity must have updated Decimal value.", psix.getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The psi entity must have updated Double value.", psix.getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The psi entity must have updated Float value.", psix.getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The psi entity must have updated String value.", psix.getString().equals(STRING_UPDATE));
				Assert.assertTrue("The psi entity must have updated Text value.", psix.getText().equals(TEXT_UPDATE));
			}
	
			for (Ro rox : tau.getRos()) {
				Assert.assertTrue("The tau entity must not be null.", rox.getTau() != null);
				Assert.assertTrue("The sigma entity must not be null.", rox.getSigma() != null);
	
				Assert.assertTrue("The ro entity must have updated Integer value.", rox.getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The ro entity must have updated Boolean value.", rox.getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The ro entity must have updated Decimal value.", rox.getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The ro entity must have updated Double value.", rox.getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The ro entity must have updated Float value.", rox.getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The ro entity must have updated String value.", rox.getString().equals(STRING_UPDATE));
				Assert.assertTrue("The ro entity must have updated Text value.", rox.getText().equals(TEXT_UPDATE));
	
				Assert.assertTrue("The tauId entity must have same id on join entity.", rox.getTau().getId().equals(tau.getId()));
				Assert.assertTrue("The tau entity must have updated Integer value.", rox.getTau().getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The tau entity must have updated Boolean value.", rox.getTau().getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The tau entity must have updated Decimal value.", rox.getTau().getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The tau entity must have updated Double value.", rox.getTau().getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The tau entity must have updated Float value.", rox.getTau().getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The tau entity must have updated String value.", rox.getTau().getString().equals(STRING_UPDATE));
				Assert.assertTrue("The tau entity must have updated Text value.", rox.getTau().getText().equals(TEXT_UPDATE));
	
				Assert.assertTrue("The sigma entity must have updated Integer value.", rox.getSigma().getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The sigma entity must have updated Boolean value.", rox.getSigma().getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The sigma entity must have updated Decimal value.", rox.getSigma().getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The sigma entity must have updated Double value.", rox.getSigma().getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The sigma entity must have updated Float value.", rox.getSigma().getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The sigma entity must have updated String value.", rox.getSigma().getString().equals(STRING_UPDATE));
				Assert.assertTrue("The sigma entity must have updated Text value.", rox.getSigma().getText().equals(TEXT_UPDATE));
			}
		}
	}

	@Test
	public void testBlockCascadeJoinListEntityBatchUpdate() throws Exception {
		List<Iota> iotas = new ArrayList<Iota>();

		for (int i = 0; i < 100; i++) {
			Iota iota = getIotaEntity();
			iota.setAlpha(getAlphaEntity());
			iota.setEpsilon(getEpsilonEntity());
			iotas.add(iota);
		}

		entityManager.insertBatchEntity(iotas);

		for (Iota iota : iotas) {
			iota.setInteger(INTEGER_UPDATE);
			iota.setBool(BOOLEAN_UPDATE);
			iota.setDecimal(DECIMAL_UPDATE);
			iota.setDoubli(DOUBLE_UPDATE);
			iota.setFloati(FLOAT_UPDATE);
			iota.setLongi(LONG_UPDATE);
			iota.setString(STRING_UPDATE);
			iota.setText(TEXT_UPDATE);
			
			iota.getAlpha().setInteger(INTEGER_UPDATE);
			iota.getAlpha().setBool(BOOLEAN_UPDATE);
			iota.getAlpha().setDecimal(DECIMAL_UPDATE);
			iota.getAlpha().setDoubli(DOUBLE_UPDATE);
			iota.getAlpha().setFloati(FLOAT_UPDATE);
			iota.getAlpha().setLongi(LONG_UPDATE);
			iota.getAlpha().setString(STRING_UPDATE);
			iota.getAlpha().setText(TEXT_UPDATE);
			iota.getAlpha().setDate(DATE_UPDATE);
			
			iota.getEpsilon().setInteger(INTEGER_UPDATE);
			iota.getEpsilon().setBool(BOOLEAN_UPDATE);
			iota.getEpsilon().setDecimal(DECIMAL_UPDATE);
			iota.getEpsilon().setDoubli(DOUBLE_UPDATE);
			iota.getEpsilon().setFloati(FLOAT_UPDATE);
			iota.getEpsilon().setLongi(LONG_UPDATE);
			iota.getEpsilon().setString(STRING_UPDATE);
			iota.getEpsilon().setText(TEXT_UPDATE);
		}

		entityManager.updateBatchEntity(iotas, true);

		Iota iotax = getIotaEntity();
		iotax.setInteger(INTEGER_UPDATE);
		iotax.setBool(BOOLEAN_UPDATE);
		iotax.setDecimal(DECIMAL_UPDATE);
		iotax.setDoubli(DOUBLE_UPDATE);
		iotax.setFloati(FLOAT_UPDATE);
		iotax.setLongi(LONG_UPDATE);
		iotax.setString(STRING_UPDATE);
		iotax.setText(TEXT_UPDATE);
		iotas = (List<Iota>) entityManager.selectEntity(iotax);

		Assert.assertTrue("The list of entities must have 100 elements", iotas.size() == 100);

		for (Iota iota : iotas) {
			Assert.assertTrue("The iota entity must not be null.", iota != null);
			Assert.assertTrue("The alpha entity must not be null.", iota.getAlpha() != null);
			Assert.assertTrue("The epsilon entity must not be null.", iota.getEpsilon() != null);
			
			Assert.assertTrue("The alpha entity must have id.", iota.getAlpha().getId() != null);
			Assert.assertTrue("The epsilon entity must have id.", iota.getEpsilon().getId() != null);
			
			Assert.assertTrue("The iota entity must have updated Integer value.", iota.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The iota entity must have updated Boolean value.", iota.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The iota entity must have updated Decimal value.", iota.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The iota entity must have updated Double value.", iota.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The iota entity must have updated Float value.", iota.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The iota entity must have updated String value.", iota.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The iota entity must have updated Text value.", iota.getText().equals(TEXT_UPDATE));
			
			Assert.assertTrue("The alpha entity must have updated Integer value.", iota.getAlpha().getInteger().equals(INTEGER));
			Assert.assertTrue("The alpha entity must have updated Boolean value.", iota.getAlpha().getBool().equals(BOOLEAN));
			Assert.assertTrue("The alpha entity must have updated Decimal value.", iota.getAlpha().getDecimal().equals(DECIMAL));
			Assert.assertTrue("The alpha entity must have updated Double value.", iota.getAlpha().getDoubli().equals(DOUBLE));
			Assert.assertTrue("The alpha entity must have updated Float value.", iota.getAlpha().getFloati().equals(FLOAT));
			Assert.assertTrue("The alpha entity must have updated String value.", iota.getAlpha().getString().equals(STRING));
			Assert.assertTrue("The alpha entity must have updated Text value.", iota.getAlpha().getText().equals(TEXT));
			
			Assert.assertTrue("The epsilon entity must have updated Integer value.", iota.getEpsilon().getInteger().equals(INTEGER));
			Assert.assertTrue("The epsilon entity must have updated Boolean value.", iota.getEpsilon().getBool().equals(BOOLEAN));
			Assert.assertTrue("The epsilon entity must have updated Decimal value.", iota.getEpsilon().getDecimal().equals(DECIMAL));
			Assert.assertTrue("The epsilon entity must have updated Double value.", iota.getEpsilon().getDoubli().equals(DOUBLE));
			Assert.assertTrue("The epsilon entity must have updated Float value.", iota.getEpsilon().getFloati().equals(FLOAT));
			Assert.assertTrue("The epsilon entity must have updated String value.", iota.getEpsilon().getString().equals(STRING));
			Assert.assertTrue("The epsilon entity must have updated Text value.", iota.getEpsilon().getText().equals(TEXT));
		}
	}

	@Test
	public void testBlockCascadeOneToOneListEntityBatchUpdate() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.setGama(getGamaEntity());
			alphas.add(alpha);
		}

		entityManager.insertBatchEntity(alphas);

		for (Alpha alpha : alphas) {
			alpha.setInteger(INTEGER_UPDATE);
			alpha.setBool(BOOLEAN_UPDATE);
			alpha.setDecimal(DECIMAL_UPDATE);
			alpha.setDoubli(DOUBLE_UPDATE);
			alpha.setFloati(FLOAT_UPDATE);
			alpha.setLongi(LONG_UPDATE);
			alpha.setString(STRING_UPDATE);
			alpha.setText(TEXT_UPDATE);
			alpha.setDate(DATE_UPDATE);
			
			alpha.getGama().setInteger(INTEGER_UPDATE);
			alpha.getGama().setBool(BOOLEAN_UPDATE);
			alpha.getGama().setDecimal(DECIMAL_UPDATE);
			alpha.getGama().setDoubli(DOUBLE_UPDATE);
			alpha.getGama().setFloati(FLOAT_UPDATE);
			alpha.getGama().setLongi(LONG_UPDATE);
			alpha.getGama().setString(STRING_UPDATE);
			alpha.getGama().setText(TEXT_UPDATE);
		}

		entityManager.updateBatchEntity(alphas, true);

		Alpha alphax = getAlphaEntity();
		alphax.setInteger(INTEGER_UPDATE);
		alphax.setBool(BOOLEAN_UPDATE);
		alphax.setDecimal(DECIMAL_UPDATE);
		alphax.setDoubli(DOUBLE_UPDATE);
		alphax.setFloati(FLOAT_UPDATE);
		alphax.setLongi(LONG_UPDATE);
		alphax.setString(STRING_UPDATE);
		alphax.setText(TEXT_UPDATE);
		alphax.setDate(DATE_UPDATE);
		alphas = (List<Alpha>) entityManager.selectEntity(alphax);

		Assert.assertTrue("The list of entities must have 100 elements", alphas.size() == 100);

		for (Alpha alpha : alphas) {
			Assert.assertTrue("The alpha entity must not be null.", alpha != null);
			Assert.assertTrue("The gama entity must not be null.", alpha.getGama() != null);
	
			Assert.assertTrue("The alpha entity must have id.", alpha.getId() != null);
			Assert.assertTrue("The gama entity must have id.", alpha.getGama().getId() != null);
	
			Assert.assertTrue("The alpha entity must have updated Integer value.", alpha.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Boolean value.", alpha.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Decimal value.", alpha.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Double value.", alpha.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Float value.", alpha.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The alpha entity must have updated String value.", alpha.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Text value.", alpha.getText().equals(TEXT_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Date value.", alpha.getDate().getTime() == DATE_UPDATE.getTime());
			
			Assert.assertTrue("The gama entity must not have updated Integer value.", alpha.getGama().getInteger().equals(INTEGER));
			Assert.assertTrue("The gama entity must not have updated Boolean value.", alpha.getGama().getBool().equals(BOOLEAN));
			Assert.assertTrue("The gama entity must not have updated Decimal value.", alpha.getGama().getDecimal().equals(DECIMAL));
			Assert.assertTrue("The gama entity must not have updated Double value.", alpha.getGama().getDoubli().equals(DOUBLE));
			Assert.assertTrue("The gama entity must not have updated Float value.", alpha.getGama().getFloati().equals(FLOAT));
			Assert.assertTrue("The gama entity must not have updated String value.", alpha.getGama().getString().equals(STRING));
			Assert.assertTrue("The gama entity must not have updated Text value.", alpha.getGama().getText().equals(TEXT));
		}
	}

	@Test
	public void testBlockCascadeOneToManyListEntityBatchUpdate() throws Exception {
		List<Alpha> alphas = new ArrayList<Alpha>();

		for (int i = 0; i < 100; i++) {
			Alpha alpha = getAlphaEntity();
			alpha.getBetas().add(getBetaEntity());
			alpha.getBetas().add(getBetaEntity());
			alphas.add(alpha);
		}
		
		entityManager.insertBatchEntity(alphas);

		for (Alpha alpha : alphas) {
			alpha.setInteger(INTEGER_UPDATE);
			alpha.setBool(BOOLEAN_UPDATE);
			alpha.setDecimal(DECIMAL_UPDATE);
			alpha.setDoubli(DOUBLE_UPDATE);
			alpha.setFloati(FLOAT_UPDATE);
			alpha.setLongi(LONG_UPDATE);
			alpha.setString(STRING_UPDATE);
			alpha.setText(TEXT_UPDATE);
			alpha.setDate(DATE_UPDATE);

			for (Beta beta : alpha.getBetas()) {
				beta.setInteger(INTEGER_UPDATE);
				beta.setBool(BOOLEAN_UPDATE);
				beta.setDecimal(DECIMAL_UPDATE);
				beta.setDoubli(DOUBLE_UPDATE);
				beta.setFloati(FLOAT_UPDATE);
				beta.setLongi(LONG_UPDATE);
				beta.setString(STRING_UPDATE);
				beta.setText(TEXT_UPDATE);
			}
		}

		entityManager.updateBatchEntity(alphas, true);

		Alpha alphax = getAlphaEntity();
		alphax.setInteger(INTEGER_UPDATE);
		alphax.setBool(BOOLEAN_UPDATE);
		alphax.setDecimal(DECIMAL_UPDATE);
		alphax.setDoubli(DOUBLE_UPDATE);
		alphax.setFloati(FLOAT_UPDATE);
		alphax.setLongi(LONG_UPDATE);
		alphax.setString(STRING_UPDATE);
		alphax.setText(TEXT_UPDATE);
		alphax.setDate(DATE_UPDATE);
		alphas = (List<Alpha>) entityManager.selectEntity(alphax);

		Assert.assertTrue("The list of entities must have 100 elements", alphas.size() == 100);

		for (Alpha alpha : alphas) {
			Assert.assertTrue("The alpha entity must not be null.", alpha != null);
			Assert.assertTrue("The beta entities must contains 2 entities.", alpha.getBetas().size() == 2);
	
			Assert.assertTrue("The alpha entity must have id.", alpha.getId() != null);
	
			Assert.assertTrue("The alpha entity must have updated Integer value.", alpha.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Boolean value.", alpha.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Decimal value.", alpha.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Double value.", alpha.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Float value.", alpha.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The alpha entity must have updated String value.", alpha.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Text value.", alpha.getText().equals(TEXT_UPDATE));
			Assert.assertTrue("The alpha entity must have updated Date value.", alpha.getDate().getTime() == DATE_UPDATE.getTime());
	
			for (Beta betax : alpha.getBetas()) {
				Assert.assertTrue("The beta entity id must not be null.", betax.getId() != null);
				Assert.assertTrue("The beta entity must not have updated Integer value.", betax.getInteger().equals(INTEGER));
				Assert.assertTrue("The beta entity must not have updated Boolean value.", betax.getBool().equals(BOOLEAN));
				Assert.assertTrue("The beta entity must not have updated Decimal value.", betax.getDecimal().equals(DECIMAL));
				Assert.assertTrue("The beta entity must not have updated Double value.", betax.getDoubli().equals(DOUBLE));
				Assert.assertTrue("The beta entity must not have updated Float value.", betax.getFloati().equals(FLOAT));
				Assert.assertTrue("The beta entity must not have updated String value.", betax.getString().equals(STRING));
				Assert.assertTrue("The beta entity must not have updated Text value.", betax.getText().equals(TEXT));
			}
		}
	}

	@Test
	public void testBlockCascadeManyToManyListEntityBatchUpdate() throws Exception {
		List<Gama> gamas = new ArrayList<Gama>();

		for (int i = 0; i < 100; i++) {
			Gama gama = getGamaEntity();
			gama.getDeltas().add(getDeltaEntity());
			gama.getDeltas().add(getDeltaEntity());
			gamas.add(gama);
		}

		entityManager.insertBatchEntity(gamas);

		for (Gama gama : gamas) {
			gama.setInteger(INTEGER_UPDATE);
			gama.setBool(BOOLEAN_UPDATE);
			gama.setDecimal(DECIMAL_UPDATE);
			gama.setDoubli(DOUBLE_UPDATE);
			gama.setFloati(FLOAT_UPDATE);
			gama.setLongi(LONG_UPDATE);
			gama.setString(STRING_UPDATE);
			gama.setText(TEXT_UPDATE);

			for (Delta delta : gama.getDeltas()) {
				delta.setInteger(INTEGER_UPDATE);
				delta.setBool(BOOLEAN_UPDATE);
				delta.setDecimal(DECIMAL_UPDATE);
				delta.setDoubli(DOUBLE_UPDATE);
				delta.setFloati(FLOAT_UPDATE);
				delta.setLongi(LONG_UPDATE);
				delta.setString(STRING_UPDATE);
				delta.setText(TEXT_UPDATE);
			}
		}

		entityManager.updateBatchEntity(gamas, true);

		Gama gamax = getGamaEntity();
		gamax.setInteger(INTEGER_UPDATE);
		gamax.setBool(BOOLEAN_UPDATE);
		gamax.setDecimal(DECIMAL_UPDATE);
		gamax.setDoubli(DOUBLE_UPDATE);
		gamax.setFloati(FLOAT_UPDATE);
		gamax.setLongi(LONG_UPDATE);
		gamax.setString(STRING_UPDATE);
		gamax.setText(TEXT_UPDATE);
		gamas = (List<Gama>) entityManager.selectEntity(gamax);

		Assert.assertTrue("The list of entities must have 100 elements", gamas.size() == 100);

		for (Gama gama : gamas) {
			Assert.assertTrue("The gama entity must not be null.", gama != null);
			Assert.assertTrue("The delta entities must contains 2 entities.", gama.getDeltas().size() == 2);
	
			Assert.assertTrue("The gama entity must have id.", gama.getId() != null);
	
			Assert.assertTrue("The gama entity must have updated Integer value.", gama.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The gama entity must have updated Boolean value.", gama.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The gama entity must have updated Decimal value.", gama.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The gama entity must have updated Double value.", gama.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The gama entity must have updated Float value.", gama.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The gama entity must have updated String value.", gama.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The gama entity must have updated Text value.", gama.getText().equals(TEXT_UPDATE));
	
			for (Delta deltax : gama.getDeltas()) {
				Assert.assertTrue("The delta entity id must not be null.", deltax.getId() != null);
				Assert.assertTrue("The delta entity must not have updated Integer value.", deltax.getInteger().equals(INTEGER));
				Assert.assertTrue("The delta entity must not have updated Boolean value.", deltax.getBool().equals(BOOLEAN));
				Assert.assertTrue("The delta entity must not have updated Decimal value.", deltax.getDecimal().equals(DECIMAL));
				Assert.assertTrue("The delta entity must not have updated Double value.", deltax.getDoubli().equals(DOUBLE));
				Assert.assertTrue("The delta entity must not have updated Float value.", deltax.getFloati().equals(FLOAT));
				Assert.assertTrue("The delta entity must not have updated String value.", deltax.getString().equals(STRING));
				Assert.assertTrue("The delta entity must not have updated Text value.", deltax.getText().equals(TEXT));
			}
		}
	}

	@Test
	public void testBlockCascadeJoinManyToManyListEntityBatchUpdate() throws Exception {
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

		for (Epsilon epsilon : epsilons) {
			for (Iota iota : epsilon.getIotas()) {
				epsilon.setInteger(INTEGER_UPDATE);
				epsilon.setBool(BOOLEAN_UPDATE);
				epsilon.setDecimal(DECIMAL_UPDATE);
				epsilon.setDoubli(DOUBLE_UPDATE);
				epsilon.setFloati(FLOAT_UPDATE);
				epsilon.setLongi(LONG_UPDATE);
				epsilon.setString(STRING_UPDATE);
				epsilon.setText(TEXT_UPDATE);

				iota.setInteger(INTEGER_UPDATE);
				iota.setBool(BOOLEAN_UPDATE);
				iota.setDecimal(DECIMAL_UPDATE);
				iota.setDoubli(DOUBLE_UPDATE);
				iota.setFloati(FLOAT_UPDATE);
				iota.setLongi(LONG_UPDATE);
				iota.setString(STRING_UPDATE);
				iota.setText(TEXT_UPDATE);
				
				iota.getAlpha().setInteger(INTEGER_UPDATE);
				iota.getAlpha().setBool(BOOLEAN_UPDATE);
				iota.getAlpha().setDecimal(DECIMAL_UPDATE);
				iota.getAlpha().setDoubli(DOUBLE_UPDATE);
				iota.getAlpha().setFloati(FLOAT_UPDATE);
				iota.getAlpha().setLongi(LONG_UPDATE);
				iota.getAlpha().setString(STRING_UPDATE);
				iota.getAlpha().setText(TEXT_UPDATE);
				iota.getAlpha().setDate(DATE_UPDATE);
			}
		}

		entityManager.updateBatchEntity(epsilons, true);

		Epsilon epsilonx = getEpsilonEntity();
		epsilonx.setInteger(INTEGER_UPDATE);
		epsilonx.setBool(BOOLEAN_UPDATE);
		epsilonx.setDecimal(DECIMAL_UPDATE);
		epsilonx.setDoubli(DOUBLE_UPDATE);
		epsilonx.setFloati(FLOAT_UPDATE);
		epsilonx.setLongi(LONG_UPDATE);
		epsilonx.setString(STRING_UPDATE);
		epsilonx.setText(TEXT_UPDATE);
		epsilons = (List<Epsilon>) entityManager.selectEntity(epsilonx);

		Assert.assertTrue("The list of entities must have 100 elements", epsilons.size() == 100);
		
		for (Epsilon epsilon : epsilons) {
			Assert.assertTrue("The epsilon entity must not be null.", epsilon != null);
			Assert.assertTrue("The iota entities must contains 2 entities.", epsilon.getIotas().size() == 2);
	
			Assert.assertTrue("The epsilonId entity must have id.", epsilon.getId() != null);
	
			Assert.assertTrue("The epsilon entity must have updated Integer value.", epsilon.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Boolean value.", epsilon.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Decimal value.", epsilon.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Double value.", epsilon.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Float value.", epsilon.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated String value.", epsilon.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Text value.", epsilon.getText().equals(TEXT_UPDATE));
	
			for (Iota iotax : epsilon.getIotas()) {
				Assert.assertTrue("The alpha entity must not be null.", iotax.getAlpha() != null);
				Assert.assertTrue("The epsilon entity must not be null.", iotax.getEpsilon() != null);
	
				Assert.assertTrue("The iota entity must not have updated Integer value.", iotax.getInteger().equals(INTEGER));
				Assert.assertTrue("The iota entity must not have updated Boolean value.", iotax.getBool().equals(BOOLEAN));
				Assert.assertTrue("The iota entity must not have updated Decimal value.", iotax.getDecimal().equals(DECIMAL));
				Assert.assertTrue("The iota entity must not have updated Double value.", iotax.getDoubli().equals(DOUBLE));
				Assert.assertTrue("The iota entity must not have updated Float value.", iotax.getFloati().equals(FLOAT));
				Assert.assertTrue("The iota entity must not have updated String value.", iotax.getString().equals(STRING));
				Assert.assertTrue("The iota entity must not have updated Text value.", iotax.getText().equals(TEXT));
	
				Assert.assertTrue("The alpha entity must not have updated Integer value.", iotax.getAlpha().getInteger().equals(INTEGER));
				Assert.assertTrue("The alpha entity must not have updated Boolean value.", iotax.getAlpha().getBool().equals(BOOLEAN));
				Assert.assertTrue("The alpha entity must not have updated Decimal value.", iotax.getAlpha().getDecimal().equals(DECIMAL));
				Assert.assertTrue("The alpha entity must not have updated Double value.", iotax.getAlpha().getDoubli().equals(DOUBLE));
				Assert.assertTrue("The alpha entity must not have updated Float value.", iotax.getAlpha().getFloati().equals(FLOAT));
				Assert.assertTrue("The alpha entity must not have updated String value.", iotax.getAlpha().getString().equals(STRING));
				Assert.assertTrue("The alpha entity must not have updated Text value.", iotax.getAlpha().getText().equals(TEXT));
				Assert.assertTrue("The alpha entity must not have updated Date value.", iotax.getAlpha().getDate().getTime() == DATE.getTime());
	
				Assert.assertTrue("The epsilonId entity must have same id on join entity.", iotax.getEpsilon().getId().equals(epsilon.getId()));
				Assert.assertTrue("The epsilon entity must have updated Integer value.", iotax.getEpsilon().getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated Boolean value.", iotax.getEpsilon().getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated Decimal value.", iotax.getEpsilon().getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated Double value.", iotax.getEpsilon().getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated Float value.", iotax.getEpsilon().getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated String value.", iotax.getEpsilon().getString().equals(STRING_UPDATE));
				Assert.assertTrue("The epsilon entity must have updated Text value.", iotax.getEpsilon().getText().equals(TEXT_UPDATE));
			}
		}
	}

	@Test
	public void testBlockCascadeCompleteListEntityBatchUpdate() throws Exception {
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
			tau.setInteger(INTEGER_UPDATE);
			tau.setBool(BOOLEAN_UPDATE);
			tau.setDecimal(DECIMAL_UPDATE);
			tau.setDoubli(DOUBLE_UPDATE);
			tau.setFloati(FLOAT_UPDATE);
			tau.setLongi(LONG_UPDATE);
			tau.setString(STRING_UPDATE);
			tau.setText(TEXT_UPDATE);
	
			tau.getFi().setInteger(INTEGER_UPDATE);
			tau.getFi().setBool(BOOLEAN_UPDATE);
			tau.getFi().setDecimal(DECIMAL_UPDATE);
			tau.getFi().setDoubli(DOUBLE_UPDATE);
			tau.getFi().setFloati(FLOAT_UPDATE);
			tau.getFi().setLongi(LONG_UPDATE);
			tau.getFi().setString(STRING_UPDATE);
			tau.getFi().setText(TEXT_UPDATE);
	
			for (Psi psi : tau.getPsis()) {
				psi.setInteger(INTEGER_UPDATE);
				psi.setBool(BOOLEAN_UPDATE);
				psi.setDecimal(DECIMAL_UPDATE);
				psi.setDoubli(DOUBLE_UPDATE);
				psi.setFloati(FLOAT_UPDATE);
				psi.setLongi(LONG_UPDATE);
				psi.setString(STRING_UPDATE);
				psi.setText(TEXT_UPDATE);
			}

			for (Pi pi : tau.getPis()) {
				pi.setInteger(INTEGER_UPDATE);
				pi.setBool(BOOLEAN_UPDATE);
				pi.setDecimal(DECIMAL_UPDATE);
				pi.setDoubli(DOUBLE_UPDATE);
				pi.setFloati(FLOAT_UPDATE);
				pi.setLongi(LONG_UPDATE);
				pi.setString(STRING_UPDATE);
				pi.setText(TEXT_UPDATE);
			}
	
			for (Ro ro : tau.getRos()) {
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
		}

		entityManager.updateBatchEntity(taus, true);

		Tau taux = getTauEntity();
		taux.setInteger(INTEGER_UPDATE);
		taux.setBool(BOOLEAN_UPDATE);
		taux.setDecimal(DECIMAL_UPDATE);
		taux.setDoubli(DOUBLE_UPDATE);
		taux.setFloati(FLOAT_UPDATE);
		taux.setLongi(LONG_UPDATE);
		taux.setString(STRING_UPDATE);
		taux.setText(TEXT_UPDATE);
		taus = (List<Tau>) entityManager.selectEntity(taux);

		Assert.assertTrue("The list of entities must have 100 elements", taus.size() == 100);

		for (Tau tau : taus) {
			Assert.assertTrue("The tau entity must not be null.", tau != null);
			Assert.assertTrue("The fi entity must not be null.", tau.getFi() != null);
			Assert.assertTrue("The ros entities must contains 2 entities.", tau.getRos().size() == 2);
			Assert.assertTrue("The pi entities must contains 2 entities.", tau.getPis().size() == 2);
			Assert.assertTrue("The psi entities must contains 2 entities.", tau.getPsis().size() == 2);
	
			Assert.assertTrue("The tauId entity must have id.", tau.getId() != null);
			Assert.assertTrue("The gama entity must have id.", tau.getFi().getId() != null);
	
			Assert.assertTrue("The tau entity must have updated Integer value.", tau.getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The tau entity must have updated Boolean value.", tau.getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The tau entity must have updated Decimal value.", tau.getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The tau entity must have updated Double value.", tau.getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The tau entity must have updated Float value.", tau.getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The tau entity must have updated String value.", tau.getString().equals(STRING_UPDATE));
			Assert.assertTrue("The tau entity must have updated Text value.", tau.getText().equals(TEXT_UPDATE));
	
			Assert.assertTrue("The fi entity must have updated Integer value.", tau.getFi().getInteger().equals(INTEGER));
			Assert.assertTrue("The fi entity must have updated Boolean value.", tau.getFi().getBool().equals(BOOLEAN));
			Assert.assertTrue("The fi entity must have updated Decimal value.", tau.getFi().getDecimal().equals(DECIMAL));
			Assert.assertTrue("The fi entity must have updated Double value.", tau.getFi().getDoubli().equals(DOUBLE));
			Assert.assertTrue("The fi entity must have updated Float value.", tau.getFi().getFloati().equals(FLOAT));
			Assert.assertTrue("The fi entity must have updated String value.", tau.getFi().getString().equals(STRING));
			Assert.assertTrue("The fi entity must have updated Text value.", tau.getFi().getText().equals(TEXT));

			for (Pi pix : tau.getPis()) {
				Assert.assertTrue("The pi entity id must not be null.", pix.getId() != null);
				Assert.assertTrue("The pi entity must have updated Integer value.", pix.getInteger().equals(INTEGER));
				Assert.assertTrue("The pi entity must have updated Boolean value.", pix.getBool().equals(BOOLEAN));
				Assert.assertTrue("The pi entity must have updated Decimal value.", pix.getDecimal().equals(DECIMAL));
				Assert.assertTrue("The pi entity must have updated Double value.", pix.getDoubli().equals(DOUBLE));
				Assert.assertTrue("The pi entity must have updated Float value.", pix.getFloati().equals(FLOAT));
				Assert.assertTrue("The pi entity must have updated String value.", pix.getString().equals(STRING));
				Assert.assertTrue("The pi entity must have updated Text value.", pix.getText().equals(TEXT));
			}
	
			for (Psi psix : tau.getPsis()) {
				Assert.assertTrue("The psi entity id must not be null.", psix.getId() != null);
				Assert.assertTrue("The psi entity must have updated Integer value.", psix.getInteger().equals(INTEGER));
				Assert.assertTrue("The psi entity must have updated Boolean value.", psix.getBool().equals(BOOLEAN));
				Assert.assertTrue("The psi entity must have updated Decimal value.", psix.getDecimal().equals(DECIMAL));
				Assert.assertTrue("The psi entity must have updated Double value.", psix.getDoubli().equals(DOUBLE));
				Assert.assertTrue("The psi entity must have updated Float value.", psix.getFloati().equals(FLOAT));
				Assert.assertTrue("The psi entity must have updated String value.", psix.getString().equals(STRING));
				Assert.assertTrue("The psi entity must have updated Text value.", psix.getText().equals(TEXT));
			}

			for (Ro rox : tau.getRos()) {
				Assert.assertTrue("The tau entity must not be null.", rox.getTau() != null);
				Assert.assertTrue("The sigma entity must not be null.", rox.getSigma() != null);
	
				Assert.assertTrue("The ro entity must have updated Integer value.", rox.getInteger().equals(INTEGER));
				Assert.assertTrue("The ro entity must have updated Boolean value.", rox.getBool().equals(BOOLEAN));
				Assert.assertTrue("The ro entity must have updated Decimal value.", rox.getDecimal().equals(DECIMAL));
				Assert.assertTrue("The ro entity must have updated Double value.", rox.getDoubli().equals(DOUBLE));
				Assert.assertTrue("The ro entity must have updated Float value.", rox.getFloati().equals(FLOAT));
				Assert.assertTrue("The ro entity must have updated String value.", rox.getString().equals(STRING));
				Assert.assertTrue("The ro entity must have updated Text value.", rox.getText().equals(TEXT));
	
				Assert.assertTrue("The tauId entity must have same id on join entity.", rox.getTau().getId().equals(tau.getId()));
				Assert.assertTrue("The tau entity must have updated Integer value.", rox.getTau().getInteger().equals(INTEGER_UPDATE));
				Assert.assertTrue("The tau entity must have updated Boolean value.", rox.getTau().getBool().equals(BOOLEAN_UPDATE));
				Assert.assertTrue("The tau entity must have updated Decimal value.", rox.getTau().getDecimal().equals(DECIMAL_UPDATE));
				Assert.assertTrue("The tau entity must have updated Double value.", rox.getTau().getDoubli().equals(DOUBLE_UPDATE));
				Assert.assertTrue("The tau entity must have updated Float value.", rox.getTau().getFloati().equals(FLOAT_UPDATE));
				Assert.assertTrue("The tau entity must have updated String value.", rox.getTau().getString().equals(STRING_UPDATE));
				Assert.assertTrue("The tau entity must have updated Text value.", rox.getTau().getText().equals(TEXT_UPDATE));
	
				Assert.assertTrue("The sigma entity must have updated Integer value.", rox.getSigma().getInteger().equals(INTEGER));
				Assert.assertTrue("The sigma entity must have updated Boolean value.", rox.getSigma().getBool().equals(BOOLEAN));
				Assert.assertTrue("The sigma entity must have updated Decimal value.", rox.getSigma().getDecimal().equals(DECIMAL));
				Assert.assertTrue("The sigma entity must have updated Double value.", rox.getSigma().getDoubli().equals(DOUBLE));
				Assert.assertTrue("The sigma entity must have updated Float value.", rox.getSigma().getFloati().equals(FLOAT));
				Assert.assertTrue("The sigma entity must have updated String value.", rox.getSigma().getString().equals(STRING));
				Assert.assertTrue("The sigma entity must have updated Text value.", rox.getSigma().getText().equals(TEXT));
			}
		}
	}

}
