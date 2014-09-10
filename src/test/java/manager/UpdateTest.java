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
import java.util.Date;

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

public class UpdateTest extends AbstractTest {

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
	public void testNullEntityUpdate() throws Exception {
		entityManager.updateEntity(null);
	}

	@Test
	public void testBasicEntityUpdate() throws Exception {
		Beta beta = getBetaEntity();

		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		beta.setInteger(INTEGER_UPDATE);
		beta.setBool(BOOLEAN_UPDATE);
		beta.setDecimal(DECIMAL_UPDATE);
		beta.setDoubli(DOUBLE_UPDATE);
		beta.setFloati(FLOAT_UPDATE);
		beta.setLongi(LONG_UPDATE);
		beta.setString(STRING_UPDATE);
		beta.setText(TEXT_UPDATE);

		entityManager.updateEntity(beta);

		beta = (Beta) entityManager.selectSingleEntity(beta);

		Assert.assertTrue("The beta entity must not be null.", beta != null);
		Assert.assertTrue("The beta entity must the same id after update.", beta.getId().equals(betaId));
		Assert.assertTrue("The beta entity must have updated Integer value.", beta.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The beta entity must have updated Boolean value.", beta.getBool().equals(BOOLEAN_UPDATE));
		Assert.assertTrue("The beta entity must have updated Decimal value.", beta.getDecimal().equals(DECIMAL_UPDATE));
		Assert.assertTrue("The beta entity must have updated Double value.", beta.getDoubli().equals(DOUBLE_UPDATE));
		Assert.assertTrue("The beta entity must have updated Float value.", beta.getFloati().equals(FLOAT_UPDATE));
		Assert.assertTrue("The beta entity must have updated String value.", beta.getString().equals(STRING_UPDATE));
		Assert.assertTrue("The beta entity must have updated Text value.", beta.getText().equals(TEXT_UPDATE));
	}

	@Test
	public void testBasicEntityWithIntegerUpdate() throws Exception {
		Beta beta = getBetaEntity();

		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		beta.setInteger(INTEGER_UPDATE);

		entityManager.updateEntity(beta);

		beta = (Beta) entityManager.selectSingleEntity(beta);

		Assert.assertTrue("The beta entity must not be null.", beta != null);
		Assert.assertTrue("The beta entity must the same id after update.", beta.getId().equals(betaId));
		Assert.assertTrue("The beta entity must have updated Integer value.", beta.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The beta entity must have updated Boolean value.", beta.getBool().equals(BOOLEAN));
		Assert.assertTrue("The beta entity must have updated Decimal value.", beta.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The beta entity must have updated Double value.", beta.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The beta entity must have updated Float value.", beta.getFloati().equals(FLOAT));
		Assert.assertTrue("The beta entity must have updated String value.", beta.getString().equals(STRING));
		Assert.assertTrue("The beta entity must have updated Text value.", beta.getText().equals(TEXT));
	}

	public void testEmptyEntityUpdate() throws Exception {
		Omega omega = getOmegaEntity();

		entityManager.insertEntity(omega);
		Long omegaId = omega.getId();
		Assert.assertTrue("The omega entity must have an id.", omegaId != null);

		omega = new Omega();
		entityManager.updateEntity(omega);
	}

	@Test
	public void testNullAttributesEntityUpdate() throws Exception {
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

		entityManager.updateEntity(beta);

		beta = (Beta) entityManager.selectSingleEntity(beta);

		Assert.assertTrue("The beta entity must not be null.", beta != null);
		Assert.assertTrue("The beta entity must have an id.", beta.getId().equals(betaId));
		Assert.assertTrue("The beta entity must have updated Integer value null.", beta.getInteger() == null);
		Assert.assertTrue("The beta entity must have updated Boolean value null.", beta.getBool() == null);
		Assert.assertTrue("The beta entity must have updated Decimal value null.", beta.getDecimal() == null);
		Assert.assertTrue("The beta entity must have updated Double value null.", beta.getDoubli() == null);
		Assert.assertTrue("The beta entity must have updated Float value null.", beta.getFloati() == null);
		Assert.assertTrue("The beta entity must have updated String value null.", beta.getString() == null);
		Assert.assertTrue("The beta entity must have updated Text value null.", beta.getText() == null);
	}

	@Test
	public void testMoreThanOnePkIdEntityUpdate() throws Exception {
		// TODO: Allow more than one pk id. // Use teta entity
	}

	@Test(expected = RuntimeException.class)
	public void testColumnExceedLengthEntityUpdate() throws Exception {
		Beta beta = getBetaEntity();

		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		beta.setString(STRING_50);
		entityManager.updateEntity(beta);
	}

	@Test
	public void testTransientEntityUpdate() throws Exception {
		Beta beta = getBetaEntity();

		beta.setTrans("Transient value");
		entityManager.insertEntity(beta);
		Integer betaId = beta.getId();
		Assert.assertTrue("The beta entity must have an id.", betaId != null);

		beta.setTrans("Transient value updated");
		entityManager.updateEntity(beta);

		beta = (Beta) entityManager.selectSingleEntity(beta);
		Assert.assertTrue("The beta entity must not be null.", beta != null);
		Assert.assertTrue("The beta entity must have an id.", beta.getId().equals(betaId));
		Assert.assertTrue("The beta entity must not have transiente value.", beta.getTrans() == null);
	}

	@Test
	public void testJoinEntityUpdate() throws Exception {
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

		iota.setInteger(INTEGER_UPDATE);
		iota.setBool(BOOLEAN_UPDATE);
		iota.setDecimal(DECIMAL_UPDATE);
		iota.setDoubli(DOUBLE_UPDATE);
		iota.setFloati(FLOAT_UPDATE);
		iota.setLongi(LONG_UPDATE);
		iota.setString(STRING_UPDATE);
		iota.setText(TEXT_UPDATE);
		
		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);
		
		epsilon.setInteger(INTEGER_UPDATE);
		epsilon.setBool(BOOLEAN_UPDATE);
		epsilon.setDecimal(DECIMAL_UPDATE);
		epsilon.setDoubli(DOUBLE_UPDATE);
		epsilon.setFloati(FLOAT_UPDATE);
		epsilon.setLongi(LONG_UPDATE);
		epsilon.setString(STRING_UPDATE);
		epsilon.setText(TEXT_UPDATE);

		entityManager.updateEntity(iota);

		iota = (Iota) entityManager.selectSingleEntity(iota);

		Assert.assertTrue("The iota entity must not be null.", iota != null);
		Assert.assertTrue("The alpha entity must not be null.", iota.getAlpha() != null);
		Assert.assertTrue("The epsilon entity must not be null.", iota.getEpsilon() != null);
		
		Assert.assertTrue("The alpha entity must have same id after updating.", iota.getAlpha().getId().equals(alphaId));
		Assert.assertTrue("The epsilon entity must have same id after updating.", iota.getEpsilon().getId().equals(epsilonId));
		
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

	@Test
	public void testCascadeOneToOneEntityUpdate() throws Exception {
		Alpha alpha = getAlphaEntity();
		Gama gama = getGamaEntity();

		alpha.setGama(gama);
		entityManager.insertEntity(alpha);

		Integer alphaId = alpha.getId();
		Long gamaId = gama.getId();

		Assert.assertTrue("The gama entity must have an id.", gamaId != null);
		Assert.assertTrue("The alpha entity must have an id.", alphaId != null);

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);
		
		gama.setInteger(INTEGER_UPDATE);
		gama.setBool(BOOLEAN_UPDATE);
		gama.setDecimal(DECIMAL_UPDATE);
		gama.setDoubli(DOUBLE_UPDATE);
		gama.setFloati(FLOAT_UPDATE);
		gama.setLongi(LONG_UPDATE);
		gama.setString(STRING_UPDATE);
		gama.setText(TEXT_UPDATE);

		entityManager.updateEntity(alpha);

		alpha = (Alpha) entityManager.selectSingleEntity(alpha);

		Assert.assertTrue("The alpha entity must not be null.", alpha != null);
		Assert.assertTrue("The gama entity must not be null.", alpha.getGama() != null);

		Assert.assertTrue("The alpha entity must have same id after updating.", alpha.getId().equals(alphaId));
		Assert.assertTrue("The gama entity must have same id after updating.", alpha.getGama().getId().equals(gamaId));

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

	@Test
	public void testCascadeOneToManyEntityUpdate() throws Exception {
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

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);
		
		beta.setInteger(INTEGER_UPDATE);
		beta.setBool(BOOLEAN_UPDATE);
		beta.setDecimal(DECIMAL_UPDATE);
		beta.setDoubli(DOUBLE_UPDATE);
		beta.setFloati(FLOAT_UPDATE);
		beta.setLongi(LONG_UPDATE);
		beta.setString(STRING_UPDATE);
		beta.setText(TEXT_UPDATE);

		beta2.setInteger(INTEGER_UPDATE);
		beta2.setBool(BOOLEAN_UPDATE);
		beta2.setDecimal(DECIMAL_UPDATE);
		beta2.setDoubli(DOUBLE_UPDATE);
		beta2.setFloati(FLOAT_UPDATE);
		beta2.setLongi(LONG_UPDATE);
		beta2.setString(STRING_UPDATE);
		beta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(alpha);

		alpha = (Alpha) entityManager.selectSingleEntity(alpha);

		Assert.assertTrue("The alpha entity must not be null.", alpha != null);
		Assert.assertTrue("The beta entities must contains 2 entities.", alpha.getBetas().size() == 2);

		Assert.assertTrue("The alpha entity must have same id after updating.", alpha.getId().equals(alphaId));

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

	@Test
	public void testCascadeManyToManyEntityUpdate() throws Exception {
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

		gama.setInteger(INTEGER_UPDATE);
		gama.setBool(BOOLEAN_UPDATE);
		gama.setDecimal(DECIMAL_UPDATE);
		gama.setDoubli(DOUBLE_UPDATE);
		gama.setFloati(FLOAT_UPDATE);
		gama.setLongi(LONG_UPDATE);
		gama.setString(STRING_UPDATE);
		gama.setText(TEXT_UPDATE);
		
		delta.setInteger(INTEGER_UPDATE);
		delta.setBool(BOOLEAN_UPDATE);
		delta.setDecimal(DECIMAL_UPDATE);
		delta.setDoubli(DOUBLE_UPDATE);
		delta.setFloati(FLOAT_UPDATE);
		delta.setLongi(LONG_UPDATE);
		delta.setString(STRING_UPDATE);
		delta.setText(TEXT_UPDATE);

		delta2.setInteger(INTEGER_UPDATE);
		delta2.setBool(BOOLEAN_UPDATE);
		delta2.setDecimal(DECIMAL_UPDATE);
		delta2.setDoubli(DOUBLE_UPDATE);
		delta2.setFloati(FLOAT_UPDATE);
		delta2.setLongi(LONG_UPDATE);
		delta2.setString(STRING_UPDATE);
		delta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(gama);

		gama = (Gama) entityManager.selectSingleEntity(gama);

		Assert.assertTrue("The gama entity must not be null.", gama != null);
		Assert.assertTrue("The delta entities must contains 2 entities.", gama.getDeltas().size() == 2);

		Assert.assertTrue("The gama entity must have same id after updating.", gama.getId().equals(gamaId));

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

	@Test
	public void testCascadeJoinManyToManyEntityUpdate() throws Exception {
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

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);

		alpha2.setInteger(INTEGER_UPDATE);
		alpha2.setBool(BOOLEAN_UPDATE);
		alpha2.setDecimal(DECIMAL_UPDATE);
		alpha2.setDoubli(DOUBLE_UPDATE);
		alpha2.setFloati(FLOAT_UPDATE);
		alpha2.setLongi(LONG_UPDATE);
		alpha2.setString(STRING_UPDATE);
		alpha2.setText(TEXT_UPDATE);
		alpha2.setDate(DATE_UPDATE);
		
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

		iota2.setInteger(INTEGER_UPDATE);
		iota2.setBool(BOOLEAN_UPDATE);
		iota2.setDecimal(DECIMAL_UPDATE);
		iota2.setDoubli(DOUBLE_UPDATE);
		iota2.setFloati(FLOAT_UPDATE);
		iota2.setLongi(LONG_UPDATE);
		iota2.setString(STRING_UPDATE);
		iota2.setText(TEXT_UPDATE);

		entityManager.updateEntity(epsilon);

		epsilon = (Epsilon) entityManager.selectSingleEntity(epsilon);

		Assert.assertTrue("The epsilon entity must not be null.", epsilon != null);
		Assert.assertTrue("The iota entities must contains 2 entities.", epsilon.getIotas().size() == 2);

		Assert.assertTrue("The epsilonId entity must have same id after updating.", epsilon.getId().equals(epsilonId));

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

			Assert.assertTrue("The epsilonId entity must have same id on join entity.", iotax.getEpsilon().getId().equals(epsilonId));
			Assert.assertTrue("The epsilon entity must have updated Integer value.", iotax.getEpsilon().getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Boolean value.", iotax.getEpsilon().getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Decimal value.", iotax.getEpsilon().getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Double value.", iotax.getEpsilon().getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Float value.", iotax.getEpsilon().getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated String value.", iotax.getEpsilon().getString().equals(STRING_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Text value.", iotax.getEpsilon().getText().equals(TEXT_UPDATE));
		}
	}

	@Test
	public void testCascadeCompleteEntityUpdate() throws Exception {		
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

		Integer tauId = tau.getId();
		Integer fiId = tau.getId();

		Assert.assertTrue("The fi entity must have an id.", fi.getId() != null);
		Assert.assertTrue("The pi entity must have an id.", pi.getId() != null);
		Assert.assertTrue("The pi2 entity must have an id.", pi2.getId() != null);
		Assert.assertTrue("The psi entity must have an id.", psi.getId() != null);
		Assert.assertTrue("The psi2 entity must have an id.", psi2.getId() != null);
		Assert.assertTrue("The sigma entity must have an id.", sigma.getId() != null);
		Assert.assertTrue("The sigma2 entity must have an id.", sigma2.getId() != null);
		Assert.assertTrue("The tau entity must have an id.", tau.getId() != null);

		tau.setInteger(INTEGER_UPDATE);
		tau.setBool(BOOLEAN_UPDATE);
		tau.setDecimal(DECIMAL_UPDATE);
		tau.setDoubli(DOUBLE_UPDATE);
		tau.setFloati(FLOAT_UPDATE);
		tau.setLongi(LONG_UPDATE);
		tau.setString(STRING_UPDATE);
		tau.setText(TEXT_UPDATE);

		fi.setInteger(INTEGER_UPDATE);
		fi.setBool(BOOLEAN_UPDATE);
		fi.setDecimal(DECIMAL_UPDATE);
		fi.setDoubli(DOUBLE_UPDATE);
		fi.setFloati(FLOAT_UPDATE);
		fi.setLongi(LONG_UPDATE);
		fi.setString(STRING_UPDATE);
		fi.setText(TEXT_UPDATE);

		psi.setInteger(INTEGER_UPDATE);
		psi.setBool(BOOLEAN_UPDATE);
		psi.setDecimal(DECIMAL_UPDATE);
		psi.setDoubli(DOUBLE_UPDATE);
		psi.setFloati(FLOAT_UPDATE);
		psi.setLongi(LONG_UPDATE);
		psi.setString(STRING_UPDATE);
		psi.setText(TEXT_UPDATE);

		psi2.setInteger(INTEGER_UPDATE);
		psi2.setBool(BOOLEAN_UPDATE);
		psi2.setDecimal(DECIMAL_UPDATE);
		psi2.setDoubli(DOUBLE_UPDATE);
		psi2.setFloati(FLOAT_UPDATE);
		psi2.setLongi(LONG_UPDATE);
		psi2.setString(STRING_UPDATE);
		psi2.setText(TEXT_UPDATE);

		pi.setInteger(INTEGER_UPDATE);
		pi.setBool(BOOLEAN_UPDATE);
		pi.setDecimal(DECIMAL_UPDATE);
		pi.setDoubli(DOUBLE_UPDATE);
		pi.setFloati(FLOAT_UPDATE);
		pi.setLongi(LONG_UPDATE);
		pi.setString(STRING_UPDATE);
		pi.setText(TEXT_UPDATE);

		pi2.setInteger(INTEGER_UPDATE);
		pi2.setBool(BOOLEAN_UPDATE);
		pi2.setDecimal(DECIMAL_UPDATE);
		pi2.setDoubli(DOUBLE_UPDATE);
		pi2.setFloati(FLOAT_UPDATE);
		pi2.setLongi(LONG_UPDATE);
		pi2.setString(STRING_UPDATE);
		pi2.setText(TEXT_UPDATE);

		sigma.setInteger(INTEGER_UPDATE);
		sigma.setBool(BOOLEAN_UPDATE);
		sigma.setDecimal(DECIMAL_UPDATE);
		sigma.setDoubli(DOUBLE_UPDATE);
		sigma.setFloati(FLOAT_UPDATE);
		sigma.setLongi(LONG_UPDATE);
		sigma.setString(STRING_UPDATE);
		sigma.setText(TEXT_UPDATE);

		sigma2.setInteger(INTEGER_UPDATE);
		sigma2.setBool(BOOLEAN_UPDATE);
		sigma2.setDecimal(DECIMAL_UPDATE);
		sigma2.setDoubli(DOUBLE_UPDATE);
		sigma2.setFloati(FLOAT_UPDATE);
		sigma2.setLongi(LONG_UPDATE);
		sigma2.setString(STRING_UPDATE);
		sigma2.setText(TEXT_UPDATE);

		ro.setInteger(INTEGER_UPDATE);
		ro.setBool(BOOLEAN_UPDATE);
		ro.setDecimal(DECIMAL_UPDATE);
		ro.setDoubli(DOUBLE_UPDATE);
		ro.setFloati(FLOAT_UPDATE);
		ro.setLongi(LONG_UPDATE);
		ro.setString(STRING_UPDATE);
		ro.setText(TEXT_UPDATE);

		ro2.setInteger(INTEGER_UPDATE);
		ro2.setBool(BOOLEAN_UPDATE);
		ro2.setDecimal(DECIMAL_UPDATE);
		ro2.setDoubli(DOUBLE_UPDATE);
		ro2.setFloati(FLOAT_UPDATE);
		ro2.setLongi(LONG_UPDATE);
		ro2.setString(STRING_UPDATE);
		ro2.setText(TEXT_UPDATE);

		entityManager.updateEntity(tau);

		tau = (Tau) entityManager.selectSingleEntity(tau);

		Assert.assertTrue("The tau entity must not be null.", tau != null);
		Assert.assertTrue("The fi entity must not be null.", tau.getFi() != null);
		Assert.assertTrue("The ros entities must contains 2 entities.", tau.getRos().size() == 2);
		Assert.assertTrue("The pi entities must contains 2 entities.", tau.getPis().size() == 2);
		Assert.assertTrue("The psi entities must contains 2 entities.", tau.getPsis().size() == 2);

		Assert.assertTrue("The tauId entity must have same id after updating.", tau.getId().equals(tauId));
		Assert.assertTrue("The gama entity must have same id after updating.", tau.getFi().getId().equals(fiId));

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

			Assert.assertTrue("The tauId entity must have same id on join entity.", rox.getTau().getId().equals(tauId));
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

	@Test
	public void testBlockCascadeJoinEntityUpdate() throws Exception {
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

		iota.setInteger(INTEGER_UPDATE);
		iota.setBool(BOOLEAN_UPDATE);
		iota.setDecimal(DECIMAL_UPDATE);
		iota.setDoubli(DOUBLE_UPDATE);
		iota.setFloati(FLOAT_UPDATE);
		iota.setLongi(LONG_UPDATE);
		iota.setString(STRING_UPDATE);
		iota.setText(TEXT_UPDATE);
		
		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		
		epsilon.setInteger(INTEGER_UPDATE);
		epsilon.setBool(BOOLEAN_UPDATE);
		epsilon.setDecimal(DECIMAL_UPDATE);
		epsilon.setDoubli(DOUBLE_UPDATE);
		epsilon.setFloati(FLOAT_UPDATE);
		epsilon.setLongi(LONG_UPDATE);
		epsilon.setString(STRING_UPDATE);
		epsilon.setText(TEXT_UPDATE);

		entityManager.updateEntity(iota, true);

		iota = (Iota) entityManager.selectSingleEntity(iota);

		Assert.assertTrue("The iota entity must not be null.", iota != null);
		Assert.assertTrue("The alpha entity must not be null.", iota.getAlpha() != null);
		Assert.assertTrue("The epsilon entity must not be null.", iota.getEpsilon() != null);
		
		Assert.assertTrue("The alpha entity must have same id after updating.", iota.getAlpha().getId().equals(alphaId));
		Assert.assertTrue("The epsilon entity must have same id after updating.", iota.getEpsilon().getId().equals(epsilonId));
		
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

	@Test
	public void testBlockCascadeOneToOneEntityUpdate() throws Exception {
		Alpha alpha = getAlphaEntity();
		Gama gama = getGamaEntity();

		alpha.setGama(gama);
		entityManager.insertEntity(alpha);

		Integer alphaId = alpha.getId();
		Long gamaId = gama.getId();

		Assert.assertTrue("The gama entity must have an id.", gamaId != null);
		Assert.assertTrue("The alpha entity must have an id.", alphaId != null);

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);
		
		gama.setInteger(INTEGER_UPDATE);
		gama.setBool(BOOLEAN_UPDATE);
		gama.setDecimal(DECIMAL_UPDATE);
		gama.setDoubli(DOUBLE_UPDATE);
		gama.setFloati(FLOAT_UPDATE);
		gama.setLongi(LONG_UPDATE);
		gama.setString(STRING_UPDATE);
		gama.setText(TEXT_UPDATE);

		entityManager.updateEntity(alpha, true);

		// Avoid putting gama values on select
		alpha.setGama(new Gama());

		alpha = (Alpha) entityManager.selectSingleEntity(alpha);

		Assert.assertTrue("The alpha entity must not be null.", alpha != null);
		Assert.assertTrue("The gama entity must not be null.", alpha.getGama() != null);

		Assert.assertTrue("The alpha entity must have same id after updating.", alpha.getId().equals(alphaId));
		Assert.assertTrue("The gama entity must have same id after updating.", alpha.getGama().getId().equals(gamaId));

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

	@Test
	public void testBlockCascadeOneToManyEntityUpdate() throws Exception {
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

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);
		
		beta.setInteger(INTEGER_UPDATE);
		beta.setBool(BOOLEAN_UPDATE);
		beta.setDecimal(DECIMAL_UPDATE);
		beta.setDoubli(DOUBLE_UPDATE);
		beta.setFloati(FLOAT_UPDATE);
		beta.setLongi(LONG_UPDATE);
		beta.setString(STRING_UPDATE);
		beta.setText(TEXT_UPDATE);

		beta2.setInteger(INTEGER_UPDATE);
		beta2.setBool(BOOLEAN_UPDATE);
		beta2.setDecimal(DECIMAL_UPDATE);
		beta2.setDoubli(DOUBLE_UPDATE);
		beta2.setFloati(FLOAT_UPDATE);
		beta2.setLongi(LONG_UPDATE);
		beta2.setString(STRING_UPDATE);
		beta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(alpha, true);

		// Avoid putting beta entities values on select
		alpha.getBetas().clear();
		alpha = (Alpha) entityManager.selectSingleEntity(alpha);

		Assert.assertTrue("The alpha entity must not be null.", alpha != null);
		Assert.assertTrue("The beta entities must contains 2 entities.", alpha.getBetas().size() == 2);

		Assert.assertTrue("The alpha entity must have same id after updating.", alpha.getId().equals(alphaId));

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

	@Test
	public void testBlockCascadeManyToManyEntityUpdate() throws Exception {
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

		gama.setInteger(INTEGER_UPDATE);
		gama.setBool(BOOLEAN_UPDATE);
		gama.setDecimal(DECIMAL_UPDATE);
		gama.setDoubli(DOUBLE_UPDATE);
		gama.setFloati(FLOAT_UPDATE);
		gama.setLongi(LONG_UPDATE);
		gama.setString(STRING_UPDATE);
		gama.setText(TEXT_UPDATE);
		
		delta.setInteger(INTEGER_UPDATE);
		delta.setBool(BOOLEAN_UPDATE);
		delta.setDecimal(DECIMAL_UPDATE);
		delta.setDoubli(DOUBLE_UPDATE);
		delta.setFloati(FLOAT_UPDATE);
		delta.setLongi(LONG_UPDATE);
		delta.setString(STRING_UPDATE);
		delta.setText(TEXT_UPDATE);

		delta2.setInteger(INTEGER_UPDATE);
		delta2.setBool(BOOLEAN_UPDATE);
		delta2.setDecimal(DECIMAL_UPDATE);
		delta2.setDoubli(DOUBLE_UPDATE);
		delta2.setFloati(FLOAT_UPDATE);
		delta2.setLongi(LONG_UPDATE);
		delta2.setString(STRING_UPDATE);
		delta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(gama, true);

		// Avoid putting delta entities values on select
		gama.getDeltas().clear();
		gama = (Gama) entityManager.selectSingleEntity(gama);

		Assert.assertTrue("The gama entity must not be null.", gama != null);
		Assert.assertTrue("The delta entities must contains 2 entities.", gama.getDeltas().size() == 2);

		Assert.assertTrue("The gama entity must have same id after updating.", gama.getId().equals(gamaId));

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

	@Test
	public void testBlockCascadeJoinManyToManyEntityUpdate() throws Exception {
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

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);

		alpha2.setInteger(INTEGER_UPDATE);
		alpha2.setBool(BOOLEAN_UPDATE);
		alpha2.setDecimal(DECIMAL_UPDATE);
		alpha2.setDoubli(DOUBLE_UPDATE);
		alpha2.setFloati(FLOAT_UPDATE);
		alpha2.setLongi(LONG_UPDATE);
		alpha2.setString(STRING_UPDATE);
		alpha2.setText(TEXT_UPDATE);
		alpha2.setDate(DATE_UPDATE);
		
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

		iota2.setInteger(INTEGER_UPDATE);
		iota2.setBool(BOOLEAN_UPDATE);
		iota2.setDecimal(DECIMAL_UPDATE);
		iota2.setDoubli(DOUBLE_UPDATE);
		iota2.setFloati(FLOAT_UPDATE);
		iota2.setLongi(LONG_UPDATE);
		iota2.setString(STRING_UPDATE);
		iota2.setText(TEXT_UPDATE);

		entityManager.updateEntity(epsilon, true);

		// Avoid putting iota entities values on select
		epsilon.getIotas().clear();
		epsilon = (Epsilon) entityManager.selectSingleEntity(epsilon);

		Assert.assertTrue("The epsilon entity must not be null.", epsilon != null);
		Assert.assertTrue("The iota entities must contains 2 entities.", epsilon.getIotas().size() == 2);

		Assert.assertTrue("The epsilonId entity must have same id after updating.", epsilon.getId().equals(epsilonId));

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

			Assert.assertTrue("The epsilonId entity must have same id on join entity.", iotax.getEpsilon().getId().equals(epsilonId));
			Assert.assertTrue("The epsilon entity must have updated Integer value.", iotax.getEpsilon().getInteger().equals(INTEGER_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Boolean value.", iotax.getEpsilon().getBool().equals(BOOLEAN_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Decimal value.", iotax.getEpsilon().getDecimal().equals(DECIMAL_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Double value.", iotax.getEpsilon().getDoubli().equals(DOUBLE_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Float value.", iotax.getEpsilon().getFloati().equals(FLOAT_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated String value.", iotax.getEpsilon().getString().equals(STRING_UPDATE));
			Assert.assertTrue("The epsilon entity must have updated Text value.", iotax.getEpsilon().getText().equals(TEXT_UPDATE));
		}
	}

	@Test
	public void testBlockCascadeCompleteEntityUpdate() throws Exception {
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

		Integer tauId = tau.getId();
		Integer fiId = tau.getId();

		Assert.assertTrue("The fi entity must have an id.", fi.getId() != null);
		Assert.assertTrue("The pi entity must have an id.", pi.getId() != null);
		Assert.assertTrue("The pi2 entity must have an id.", pi2.getId() != null);
		Assert.assertTrue("The psi entity must have an id.", psi.getId() != null);
		Assert.assertTrue("The psi2 entity must have an id.", psi2.getId() != null);
		Assert.assertTrue("The sigma entity must have an id.", sigma.getId() != null);
		Assert.assertTrue("The sigma2 entity must have an id.", sigma2.getId() != null);
		Assert.assertTrue("The tau entity must have an id.", tau.getId() != null);

		tau.setInteger(INTEGER_UPDATE);
		tau.setBool(BOOLEAN_UPDATE);
		tau.setDecimal(DECIMAL_UPDATE);
		tau.setDoubli(DOUBLE_UPDATE);
		tau.setFloati(FLOAT_UPDATE);
		tau.setLongi(LONG_UPDATE);
		tau.setString(STRING_UPDATE);
		tau.setText(TEXT_UPDATE);

		fi.setInteger(INTEGER_UPDATE);
		fi.setBool(BOOLEAN_UPDATE);
		fi.setDecimal(DECIMAL_UPDATE);
		fi.setDoubli(DOUBLE_UPDATE);
		fi.setFloati(FLOAT_UPDATE);
		fi.setLongi(LONG_UPDATE);
		fi.setString(STRING_UPDATE);
		fi.setText(TEXT_UPDATE);

		psi.setInteger(INTEGER_UPDATE);
		psi.setBool(BOOLEAN_UPDATE);
		psi.setDecimal(DECIMAL_UPDATE);
		psi.setDoubli(DOUBLE_UPDATE);
		psi.setFloati(FLOAT_UPDATE);
		psi.setLongi(LONG_UPDATE);
		psi.setString(STRING_UPDATE);
		psi.setText(TEXT_UPDATE);

		psi2.setInteger(INTEGER_UPDATE);
		psi2.setBool(BOOLEAN_UPDATE);
		psi2.setDecimal(DECIMAL_UPDATE);
		psi2.setDoubli(DOUBLE_UPDATE);
		psi2.setFloati(FLOAT_UPDATE);
		psi2.setLongi(LONG_UPDATE);
		psi2.setString(STRING_UPDATE);
		psi2.setText(TEXT_UPDATE);

		pi.setInteger(INTEGER_UPDATE);
		pi.setBool(BOOLEAN_UPDATE);
		pi.setDecimal(DECIMAL_UPDATE);
		pi.setDoubli(DOUBLE_UPDATE);
		pi.setFloati(FLOAT_UPDATE);
		pi.setLongi(LONG_UPDATE);
		pi.setString(STRING_UPDATE);
		pi.setText(TEXT_UPDATE);

		pi2.setInteger(INTEGER_UPDATE);
		pi2.setBool(BOOLEAN_UPDATE);
		pi2.setDecimal(DECIMAL_UPDATE);
		pi2.setDoubli(DOUBLE_UPDATE);
		pi2.setFloati(FLOAT_UPDATE);
		pi2.setLongi(LONG_UPDATE);
		pi2.setString(STRING_UPDATE);
		pi2.setText(TEXT_UPDATE);

		sigma.setInteger(INTEGER_UPDATE);
		sigma.setBool(BOOLEAN_UPDATE);
		sigma.setDecimal(DECIMAL_UPDATE);
		sigma.setDoubli(DOUBLE_UPDATE);
		sigma.setFloati(FLOAT_UPDATE);
		sigma.setLongi(LONG_UPDATE);
		sigma.setString(STRING_UPDATE);
		sigma.setText(TEXT_UPDATE);

		sigma2.setInteger(INTEGER_UPDATE);
		sigma2.setBool(BOOLEAN_UPDATE);
		sigma2.setDecimal(DECIMAL_UPDATE);
		sigma2.setDoubli(DOUBLE_UPDATE);
		sigma2.setFloati(FLOAT_UPDATE);
		sigma2.setLongi(LONG_UPDATE);
		sigma2.setString(STRING_UPDATE);
		sigma2.setText(TEXT_UPDATE);

		ro.setInteger(INTEGER_UPDATE);
		ro.setBool(BOOLEAN_UPDATE);
		ro.setDecimal(DECIMAL_UPDATE);
		ro.setDoubli(DOUBLE_UPDATE);
		ro.setFloati(FLOAT_UPDATE);
		ro.setLongi(LONG_UPDATE);
		ro.setString(STRING_UPDATE);
		ro.setText(TEXT_UPDATE);

		ro2.setInteger(INTEGER_UPDATE);
		ro2.setBool(BOOLEAN_UPDATE);
		ro2.setDecimal(DECIMAL_UPDATE);
		ro2.setDoubli(DOUBLE_UPDATE);
		ro2.setFloati(FLOAT_UPDATE);
		ro2.setLongi(LONG_UPDATE);
		ro2.setString(STRING_UPDATE);
		ro2.setText(TEXT_UPDATE);

		entityManager.updateEntity(tau, true);

		// Avoid putting updated entities values on select
		tau.setFi(null);
		tau.getPis().clear();
		tau.getPsis().clear();
		tau.getRos().clear();
		tau = (Tau) entityManager.selectSingleEntity(tau);

		Assert.assertTrue("The tau entity must not be null.", tau != null);
		Assert.assertTrue("The fi entity must not be null.", tau.getFi() != null);
		Assert.assertTrue("The ros entities must contains 2 entities.", tau.getRos().size() == 2);
		Assert.assertTrue("The pi entities must contains 2 entities.", tau.getPis().size() == 2);
		Assert.assertTrue("The psi entities must contains 2 entities.", tau.getPsis().size() == 2);

		Assert.assertTrue("The tauId entity must have same id after updating.", tau.getId().equals(tauId));
		Assert.assertTrue("The gama entity must have same id after updating.", tau.getFi().getId().equals(fiId));

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

			Assert.assertTrue("The tauId entity must have same id on join entity.", rox.getTau().getId().equals(tauId));
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

	@Test
	public void testNonTransactionalCommitEntityUpdate() throws Exception {
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

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);
		
		beta.setInteger(INTEGER_UPDATE);
		beta.setBool(BOOLEAN_UPDATE);
		beta.setDecimal(DECIMAL_UPDATE);
		beta.setDoubli(DOUBLE_UPDATE);
		beta.setFloati(FLOAT_UPDATE);
		beta.setLongi(LONG_UPDATE);
		beta.setString(STRING_UPDATE);
		beta.setText(TEXT_UPDATE);

		beta2.setInteger(INTEGER_UPDATE);
		beta2.setBool(BOOLEAN_UPDATE);
		beta2.setDecimal(DECIMAL_UPDATE);
		beta2.setDoubli(DOUBLE_UPDATE);
		beta2.setFloati(FLOAT_UPDATE);
		beta2.setLongi(LONG_UPDATE);
		beta2.setString(STRING_UPDATE);
		beta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(alpha);


		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		gama2.setInteger(INTEGER_UPDATE);
		gama2.setBool(BOOLEAN_UPDATE);
		gama2.setDecimal(DECIMAL_UPDATE);
		gama2.setDoubli(DOUBLE_UPDATE);
		gama2.setFloati(FLOAT_UPDATE);
		gama2.setLongi(LONG_UPDATE);
		gama2.setString(STRING_UPDATE);
		gama2.setText(TEXT_UPDATE);
		
		delta.setInteger(INTEGER_UPDATE);
		delta.setBool(BOOLEAN_UPDATE);
		delta.setDecimal(DECIMAL_UPDATE);
		delta.setDoubli(DOUBLE_UPDATE);
		delta.setFloati(FLOAT_UPDATE);
		delta.setLongi(LONG_UPDATE);
		delta.setString(STRING_UPDATE);
		delta.setText(TEXT_UPDATE);

		delta2.setInteger(INTEGER_UPDATE);
		delta2.setBool(BOOLEAN_UPDATE);
		delta2.setDecimal(DECIMAL_UPDATE);
		delta2.setDoubli(DOUBLE_UPDATE);
		delta2.setFloati(FLOAT_UPDATE);
		delta2.setLongi(LONG_UPDATE);
		delta2.setString(STRING_UPDATE);
		delta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(gama2);


		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		alpha2.setInteger(INTEGER_UPDATE);
		alpha2.setBool(BOOLEAN_UPDATE);
		alpha2.setDecimal(DECIMAL_UPDATE);
		alpha2.setDoubli(DOUBLE_UPDATE);
		alpha2.setFloati(FLOAT_UPDATE);
		alpha2.setLongi(LONG_UPDATE);
		alpha2.setString(STRING_UPDATE);
		alpha2.setText(TEXT_UPDATE);
		
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

		entityManager.updateEntity(epsilon);


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
	public void testTransactionalCommitEntityUpdate() throws Exception {
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

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);

		gama.setInteger(INTEGER_UPDATE);
		gama.setBool(BOOLEAN_UPDATE);
		gama.setDecimal(DECIMAL_UPDATE);
		gama.setDoubli(DOUBLE_UPDATE);
		gama.setFloati(FLOAT_UPDATE);
		gama.setLongi(LONG_UPDATE);
		gama.setString(STRING_UPDATE);
		gama.setText(TEXT_UPDATE);
		
		beta.setInteger(INTEGER_UPDATE);
		beta.setBool(BOOLEAN_UPDATE);
		beta.setDecimal(DECIMAL_UPDATE);
		beta.setDoubli(DOUBLE_UPDATE);
		beta.setFloati(FLOAT_UPDATE);
		beta.setLongi(LONG_UPDATE);
		beta.setString(STRING_UPDATE);
		beta.setText(TEXT_UPDATE);

		beta2.setInteger(INTEGER_UPDATE);
		beta2.setBool(BOOLEAN_UPDATE);
		beta2.setDecimal(DECIMAL_UPDATE);
		beta2.setDoubli(DOUBLE_UPDATE);
		beta2.setFloati(FLOAT_UPDATE);
		beta2.setLongi(LONG_UPDATE);
		beta2.setString(STRING_UPDATE);
		beta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(alpha);


		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		gama2.setInteger(INTEGER_UPDATE);
		gama2.setBool(BOOLEAN_UPDATE);
		gama2.setDecimal(DECIMAL_UPDATE);
		gama2.setDoubli(DOUBLE_UPDATE);
		gama2.setFloati(FLOAT_UPDATE);
		gama2.setLongi(LONG_UPDATE);
		gama2.setString(STRING_UPDATE);
		gama2.setText(TEXT_UPDATE);
		
		delta.setInteger(INTEGER_UPDATE);
		delta.setBool(BOOLEAN_UPDATE);
		delta.setDecimal(DECIMAL_UPDATE);
		delta.setDoubli(DOUBLE_UPDATE);
		delta.setFloati(FLOAT_UPDATE);
		delta.setLongi(LONG_UPDATE);
		delta.setString(STRING_UPDATE);
		delta.setText(TEXT_UPDATE);

		delta2.setInteger(INTEGER_UPDATE);
		delta2.setBool(BOOLEAN_UPDATE);
		delta2.setDecimal(DECIMAL_UPDATE);
		delta2.setDoubli(DOUBLE_UPDATE);
		delta2.setFloati(FLOAT_UPDATE);
		delta2.setLongi(LONG_UPDATE);
		delta2.setString(STRING_UPDATE);
		delta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(gama2);


		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		alpha2.setInteger(INTEGER_UPDATE);
		alpha2.setBool(BOOLEAN_UPDATE);
		alpha2.setDecimal(DECIMAL_UPDATE);
		alpha2.setDoubli(DOUBLE_UPDATE);
		alpha2.setFloati(FLOAT_UPDATE);
		alpha2.setLongi(LONG_UPDATE);
		alpha2.setString(STRING_UPDATE);
		alpha2.setText(TEXT_UPDATE);
		alpha2.setDate(DATE_UPDATE);
		
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

		entityManager.updateEntity(epsilon);


		Assert.assertTrue("The gama entity must be returned.", entityManager.selectSingleEntity(gama) != null);

		Assert.assertTrue("The beta entity must be returned.", entityManager.selectSingleEntity(beta) != null);
		Assert.assertTrue("The beta2 entity must be returned.", entityManager.selectSingleEntity(beta2) != null);
		Assert.assertTrue("The alpha entity must be returned.", entityManager.selectSingleEntity(alpha) != null);

		Assert.assertTrue("The delta entity must be returned.", entityManager.selectSingleEntity(delta) != null);
		Assert.assertTrue("The delta2 entity must be returned.", entityManager.selectSingleEntity(delta2) != null);
		Assert.assertTrue("The gama2 entity must be returned.", entityManager.selectSingleEntity(gama2) != null);

		Assert.assertTrue("The alpha2 entity must be returned.", entityManager.selectSingleEntity(alpha2) != null);
		Assert.assertTrue("The epsilon entity must be returned.", entityManager.selectSingleEntity(epsilon) != null);

		entityManager.commitTransaction();
	}

	@Test
	public void testTransactionalCommitStepEntityUpdate() throws Exception {
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

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);
	
		gama.setInteger(INTEGER_UPDATE);
		gama.setBool(BOOLEAN_UPDATE);
		gama.setDecimal(DECIMAL_UPDATE);
		gama.setDoubli(DOUBLE_UPDATE);
		gama.setFloati(FLOAT_UPDATE);
		gama.setLongi(LONG_UPDATE);
		gama.setString(STRING_UPDATE);
		gama.setText(TEXT_UPDATE);
		
		beta.setInteger(INTEGER_UPDATE);
		beta.setBool(BOOLEAN_UPDATE);
		beta.setDecimal(DECIMAL_UPDATE);
		beta.setDoubli(DOUBLE_UPDATE);
		beta.setFloati(FLOAT_UPDATE);
		beta.setLongi(LONG_UPDATE);
		beta.setString(STRING_UPDATE);
		beta.setText(TEXT_UPDATE);

		beta2.setInteger(INTEGER_UPDATE);
		beta2.setBool(BOOLEAN_UPDATE);
		beta2.setDecimal(DECIMAL_UPDATE);
		beta2.setDoubli(DOUBLE_UPDATE);
		beta2.setFloati(FLOAT_UPDATE);
		beta2.setLongi(LONG_UPDATE);
		beta2.setString(STRING_UPDATE);
		beta2.setText(TEXT_UPDATE);

		entityManager.initTransaction();
		entityManager.updateEntity(alpha);
		entityManager.commitTransaction();


		entityManager.initTransaction();
		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);
		entityManager.commitTransaction();

		gama2.setInteger(INTEGER_UPDATE);
		gama2.setBool(BOOLEAN_UPDATE);
		gama2.setDecimal(DECIMAL_UPDATE);
		gama2.setDoubli(DOUBLE_UPDATE);
		gama2.setFloati(FLOAT_UPDATE);
		gama2.setLongi(LONG_UPDATE);
		gama2.setString(STRING_UPDATE);
		gama2.setText(TEXT_UPDATE);
		
		delta.setInteger(INTEGER_UPDATE);
		delta.setBool(BOOLEAN_UPDATE);
		delta.setDecimal(DECIMAL_UPDATE);
		delta.setDoubli(DOUBLE_UPDATE);
		delta.setFloati(FLOAT_UPDATE);
		delta.setLongi(LONG_UPDATE);
		delta.setString(STRING_UPDATE);
		delta.setText(TEXT_UPDATE);

		delta2.setInteger(INTEGER_UPDATE);
		delta2.setBool(BOOLEAN_UPDATE);
		delta2.setDecimal(DECIMAL_UPDATE);
		delta2.setDoubli(DOUBLE_UPDATE);
		delta2.setFloati(FLOAT_UPDATE);
		delta2.setLongi(LONG_UPDATE);
		delta2.setString(STRING_UPDATE);
		delta2.setText(TEXT_UPDATE);

		entityManager.initTransaction();
		entityManager.updateEntity(gama2);
		entityManager.commitTransaction();


		entityManager.initTransaction();
		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);
		entityManager.commitTransaction();

		alpha2.setInteger(INTEGER_UPDATE);
		alpha2.setBool(BOOLEAN_UPDATE);
		alpha2.setDecimal(DECIMAL_UPDATE);
		alpha2.setDoubli(DOUBLE_UPDATE);
		alpha2.setFloati(FLOAT_UPDATE);
		alpha2.setLongi(LONG_UPDATE);
		alpha2.setString(STRING_UPDATE);
		alpha2.setText(TEXT_UPDATE);
		alpha2.setDate(DATE_UPDATE);
		
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

		entityManager.initTransaction();
		entityManager.updateEntity(epsilon);
		entityManager.commitTransaction();

		gama = (Gama) entityManager.selectSingleEntity(gama);
		beta = (Beta) entityManager.selectSingleEntity(beta);
		beta2 = (Beta) entityManager.selectSingleEntity(beta2);
		alpha = (Alpha) entityManager.selectSingleEntity(alpha);
		delta = (Delta) entityManager.selectSingleEntity(delta);
		delta2 = (Delta) entityManager.selectSingleEntity(delta2);
		gama2 = (Gama) entityManager.selectSingleEntity(gama2);
		alpha2 = (Alpha) entityManager.selectSingleEntity(alpha2);
		epsilon = (Epsilon) entityManager.selectSingleEntity(epsilon);

		Assert.assertTrue("The gama entity must be returned.", gama != null);
		Assert.assertTrue("The gama entity must have updated Integer value.", gama.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The gama entity must have updated Boolean value.", gama.getBool().equals(BOOLEAN_UPDATE));
		Assert.assertTrue("The gama entity must have updated Decimal value.", gama.getDecimal().equals(DECIMAL_UPDATE));
		Assert.assertTrue("The gama entity must have updated Double value.", gama.getDoubli().equals(DOUBLE_UPDATE));
		Assert.assertTrue("The gama entity must have updated Float value.", gama.getFloati().equals(FLOAT_UPDATE));
		Assert.assertTrue("The gama entity must have updated String value.", gama.getString().equals(STRING_UPDATE));
		Assert.assertTrue("The gama entity must have updated Text value.", gama.getText().equals(TEXT_UPDATE));

		Assert.assertTrue("The beta entity must be returned.", beta != null);
		Assert.assertTrue("The beta entity must have updated Integer value.", beta.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The beta entity must have updated Boolean value.", beta.getBool().equals(BOOLEAN_UPDATE));
		Assert.assertTrue("The beta entity must have updated Decimal value.", beta.getDecimal().equals(DECIMAL_UPDATE));
		Assert.assertTrue("The beta entity must have updated Double value.", beta.getDoubli().equals(DOUBLE_UPDATE));
		Assert.assertTrue("The beta entity must have updated Float value.", beta.getFloati().equals(FLOAT_UPDATE));
		Assert.assertTrue("The beta entity must have updated String value.", beta.getString().equals(STRING_UPDATE));
		Assert.assertTrue("The beta entity must have updated Text value.", beta.getText().equals(TEXT_UPDATE));
		
		Assert.assertTrue("The beta2 entity must be returned.", beta2 != null);
		Assert.assertTrue("The beta2 entity must have updated Integer value.", beta2.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The beta2 entity must have updated Boolean value.", beta2.getBool().equals(BOOLEAN_UPDATE));
		Assert.assertTrue("The beta2 entity must have updated Decimal value.", beta2.getDecimal().equals(DECIMAL_UPDATE));
		Assert.assertTrue("The beta2 entity must have updated Double value.", beta2.getDoubli().equals(DOUBLE_UPDATE));
		Assert.assertTrue("The beta2 entity must have updated Float value.", beta2.getFloati().equals(FLOAT_UPDATE));
		Assert.assertTrue("The beta2 entity must have updated String value.", beta2.getString().equals(STRING_UPDATE));
		Assert.assertTrue("The beta2 entity must have updated Text value.", beta2.getText().equals(TEXT_UPDATE));

		Assert.assertTrue("The alpha entity must be returned.", alpha != null);
		Assert.assertTrue("The alpha entity must have updated Integer value.", alpha.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The alpha entity must have updated Boolean value.", alpha.getBool().equals(BOOLEAN_UPDATE));
		Assert.assertTrue("The alpha entity must have updated Decimal value.", alpha.getDecimal().equals(DECIMAL_UPDATE));
		Assert.assertTrue("The alpha entity must have updated Double value.", alpha.getDoubli().equals(DOUBLE_UPDATE));
		Assert.assertTrue("The alpha entity must have updated Float value.", alpha.getFloati().equals(FLOAT_UPDATE));
		Assert.assertTrue("The alpha entity must have updated String value.", alpha.getString().equals(STRING_UPDATE));
		Assert.assertTrue("The alpha entity must have updated Text value.", alpha.getText().equals(TEXT_UPDATE));
		Assert.assertTrue("The alpha entity must have updated Date value.", alpha.getDate().getTime() == DATE_UPDATE.getTime());

		Assert.assertTrue("The delta entity must be returned.", delta != null);
		Assert.assertTrue("The delta entity must have updated Integer value.", delta.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The delta entity must have updated Boolean value.", delta.getBool().equals(BOOLEAN_UPDATE));
		Assert.assertTrue("The delta entity must have updated Decimal value.", delta.getDecimal().equals(DECIMAL_UPDATE));
		Assert.assertTrue("The delta entity must have updated Double value.", delta.getDoubli().equals(DOUBLE_UPDATE));
		Assert.assertTrue("The delta entity must have updated Float value.", delta.getFloati().equals(FLOAT_UPDATE));
		Assert.assertTrue("The delta entity must have updated String value.", delta.getString().equals(STRING_UPDATE));
		Assert.assertTrue("The delta entity must have updated Text value.", delta.getText().equals(TEXT_UPDATE));

		Assert.assertTrue("The delta2 entity must be returned.", delta2 != null);
		Assert.assertTrue("The delta2 entity must have updated Integer value.", delta2.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The delta2 entity must have updated Boolean value.", delta2.getBool().equals(BOOLEAN_UPDATE));
		Assert.assertTrue("The delta2 entity must have updated Decimal value.", delta2.getDecimal().equals(DECIMAL_UPDATE));
		Assert.assertTrue("The delta2 entity must have updated Double value.", delta2.getDoubli().equals(DOUBLE_UPDATE));
		Assert.assertTrue("The delta2 entity must have updated Float value.", delta2.getFloati().equals(FLOAT_UPDATE));
		Assert.assertTrue("The delta2 entity must have updated String value.", delta2.getString().equals(STRING_UPDATE));
		Assert.assertTrue("The delta2 entity must have updated Text value.", delta2.getText().equals(TEXT_UPDATE));

		Assert.assertTrue("The gama2 entity must be returned.", gama2 != null);
		Assert.assertTrue("The gama2 entity must have updated Integer value.", gama2.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The gama2 entity must have updated Boolean value.", gama2.getBool().equals(BOOLEAN_UPDATE));
		Assert.assertTrue("The gama2 entity must have updated Decimal value.", gama2.getDecimal().equals(DECIMAL_UPDATE));
		Assert.assertTrue("The gama2 entity must have updated Double value.", gama2.getDoubli().equals(DOUBLE_UPDATE));
		Assert.assertTrue("The gama2 entity must have updated Float value.", gama2.getFloati().equals(FLOAT_UPDATE));
		Assert.assertTrue("The gama2 entity must have updated String value.", gama2.getString().equals(STRING_UPDATE));
		Assert.assertTrue("The gama2 entity must have updated Text value.", gama2.getText().equals(TEXT_UPDATE));

		Assert.assertTrue("The alpha2 entity must be returned.", alpha2 != null);
		Assert.assertTrue("The alpha2 entity must have updated Integer value.", alpha2.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The alpha2 entity must have updated Boolean value.", alpha2.getBool().equals(BOOLEAN_UPDATE));
		Assert.assertTrue("The alpha2 entity must have updated Decimal value.", alpha2.getDecimal().equals(DECIMAL_UPDATE));
		Assert.assertTrue("The alpha2 entity must have updated Double value.", alpha2.getDoubli().equals(DOUBLE_UPDATE));
		Assert.assertTrue("The alpha2 entity must have updated Float value.", alpha2.getFloati().equals(FLOAT_UPDATE));
		Assert.assertTrue("The alpha2 entity must have updated String value.", alpha2.getString().equals(STRING_UPDATE));
		Assert.assertTrue("The alpha2 entity must have updated Text value.", alpha2.getText().equals(TEXT_UPDATE));
		Assert.assertTrue("The alpha2 entity must have updated Date value.", alpha2.getDate().getTime() == DATE_UPDATE.getTime());

		Assert.assertTrue("The epsilon entity must be returned.", epsilon != null);
		Assert.assertTrue("The epsilon entity must have updated Integer value.", epsilon.getInteger().equals(INTEGER_UPDATE));
		Assert.assertTrue("The epsilon entity must have updated Boolean value.", epsilon.getBool().equals(BOOLEAN_UPDATE));
		Assert.assertTrue("The epsilon entity must have updated Decimal value.", epsilon.getDecimal().equals(DECIMAL_UPDATE));
		Assert.assertTrue("The epsilon entity must have updated Double value.", epsilon.getDoubli().equals(DOUBLE_UPDATE));
		Assert.assertTrue("The epsilon entity must have updated Float value.", epsilon.getFloati().equals(FLOAT_UPDATE));
		Assert.assertTrue("The epsilon entity must have updated String value.", epsilon.getString().equals(STRING_UPDATE));
		Assert.assertTrue("The epsilon entity must have updated Text value.", epsilon.getText().equals(TEXT_UPDATE));
	}

	@Test
	public void testTransactionalRollbackStepEntityUpdate() throws Exception {
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

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);
		
		gama.setInteger(INTEGER_UPDATE);
		gama.setBool(BOOLEAN_UPDATE);
		gama.setDecimal(DECIMAL_UPDATE);
		gama.setDoubli(DOUBLE_UPDATE);
		gama.setFloati(FLOAT_UPDATE);
		gama.setLongi(LONG_UPDATE);
		gama.setString(STRING_UPDATE);
		gama.setText(TEXT_UPDATE);
		
		beta.setInteger(INTEGER_UPDATE);
		beta.setBool(BOOLEAN_UPDATE);
		beta.setDecimal(DECIMAL_UPDATE);
		beta.setDoubli(DOUBLE_UPDATE);
		beta.setFloati(FLOAT_UPDATE);
		beta.setLongi(LONG_UPDATE);
		beta.setString(STRING_UPDATE);
		beta.setText(TEXT_UPDATE);

		beta2.setInteger(INTEGER_UPDATE);
		beta2.setBool(BOOLEAN_UPDATE);
		beta2.setDecimal(DECIMAL_UPDATE);
		beta2.setDoubli(DOUBLE_UPDATE);
		beta2.setFloati(FLOAT_UPDATE);
		beta2.setLongi(LONG_UPDATE);
		beta2.setString(STRING_UPDATE);
		beta2.setText(TEXT_UPDATE);

		entityManager.initTransaction();
		entityManager.updateEntity(alpha);
		entityManager.rollbackTransaction();


		entityManager.initTransaction();
		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);
		entityManager.commitTransaction();

		gama2.setInteger(INTEGER_UPDATE);
		gama2.setBool(BOOLEAN_UPDATE);
		gama2.setDecimal(DECIMAL_UPDATE);
		gama2.setDoubli(DOUBLE_UPDATE);
		gama2.setFloati(FLOAT_UPDATE);
		gama2.setLongi(LONG_UPDATE);
		gama2.setString(STRING_UPDATE);
		gama2.setText(TEXT_UPDATE);
		
		delta.setInteger(INTEGER_UPDATE);
		delta.setBool(BOOLEAN_UPDATE);
		delta.setDecimal(DECIMAL_UPDATE);
		delta.setDoubli(DOUBLE_UPDATE);
		delta.setFloati(FLOAT_UPDATE);
		delta.setLongi(LONG_UPDATE);
		delta.setString(STRING_UPDATE);
		delta.setText(TEXT_UPDATE);

		delta2.setInteger(INTEGER_UPDATE);
		delta2.setBool(BOOLEAN_UPDATE);
		delta2.setDecimal(DECIMAL_UPDATE);
		delta2.setDoubli(DOUBLE_UPDATE);
		delta2.setFloati(FLOAT_UPDATE);
		delta2.setLongi(LONG_UPDATE);
		delta2.setString(STRING_UPDATE);
		delta2.setText(TEXT_UPDATE);

		entityManager.initTransaction();
		entityManager.updateEntity(gama2);
		entityManager.rollbackTransaction();


		entityManager.initTransaction();
		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);
		entityManager.commitTransaction();

		alpha2.setInteger(INTEGER_UPDATE);
		alpha2.setBool(BOOLEAN_UPDATE);
		alpha2.setDecimal(DECIMAL_UPDATE);
		alpha2.setDoubli(DOUBLE_UPDATE);
		alpha2.setFloati(FLOAT_UPDATE);
		alpha2.setLongi(LONG_UPDATE);
		alpha2.setString(STRING_UPDATE);
		alpha2.setText(TEXT_UPDATE);
		alpha2.setDate(DATE_UPDATE);
		
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

		entityManager.initTransaction();
		entityManager.updateEntity(epsilon);
		entityManager.rollbackTransaction();

		Long gamaId = gama.getId();
		gama = new Gama();
		gama.setId(gamaId);
		gama = (Gama) entityManager.selectSingleEntity(gama);

		Integer betaId = beta.getId();
		beta = new Beta();
		beta.setId(betaId);
		beta = (Beta) entityManager.selectSingleEntity(beta);

		Integer beta2Id = beta2.getId();
		beta2 = new Beta();
		beta2.setId(beta2Id);
		beta2 = (Beta) entityManager.selectSingleEntity(beta2);

		Integer alphaId = alpha.getId();
		alpha = new Alpha();
		alpha.setId(alphaId);
		alpha = (Alpha) entityManager.selectSingleEntity(alpha);

		Integer deltaId = delta.getId();
		delta = new Delta();
		delta.setId(deltaId);
		delta = (Delta) entityManager.selectSingleEntity(delta);

		Integer delta2Id = delta2.getId();
		delta2 = new Delta();
		delta2.setId(delta2Id);
		delta2 = (Delta) entityManager.selectSingleEntity(delta2);

		Long gama2Id = gama.getId();
		gama2 = new Gama();
		gama2.setId(gama2Id);
		gama2 = (Gama) entityManager.selectSingleEntity(gama2);

		Integer alpha2Id = alpha.getId();
		alpha2 = new Alpha();
		alpha2.setId(alpha2Id);
		alpha2 = (Alpha) entityManager.selectSingleEntity(alpha2);

		Integer epsilonId = epsilon.getId();
		epsilon = new Epsilon();
		epsilon.setId(epsilonId);
		epsilon = (Epsilon) entityManager.selectSingleEntity(epsilon);

		Assert.assertTrue("The gama entity must be returned.", gama != null);
		Assert.assertTrue("The gama entity must not have updated Integer value.", gama.getInteger().equals(INTEGER));
		Assert.assertTrue("The gama entity must not have updated Boolean value.", gama.getBool().equals(BOOLEAN));
		Assert.assertTrue("The gama entity must not have updated Decimal value.", gama.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The gama entity must not have updated Double value.", gama.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The gama entity must not have updated Float value.", gama.getFloati().equals(FLOAT));
		Assert.assertTrue("The gama entity must not have updated String value.", gama.getString().equals(STRING));
		Assert.assertTrue("The gama entity must not have updated Text value.", gama.getText().equals(TEXT));

		Assert.assertTrue("The beta entity must be returned.", beta != null);
		Assert.assertTrue("The beta entity must not have updated Integer value.", beta.getInteger().equals(INTEGER));
		Assert.assertTrue("The beta entity must not have updated Boolean value.", beta.getBool().equals(BOOLEAN));
		Assert.assertTrue("The beta entity must not have updated Decimal value.", beta.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The beta entity must not have updated Double value.", beta.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The beta entity must not have updated Float value.", beta.getFloati().equals(FLOAT));
		Assert.assertTrue("The beta entity must not have updated String value.", beta.getString().equals(STRING));
		Assert.assertTrue("The beta entity must not have updated Text value.", beta.getText().equals(TEXT));
		
		Assert.assertTrue("The beta2 entity must be returned.", beta2 != null);
		Assert.assertTrue("The beta2 entity must not have updated Integer value.", beta2.getInteger().equals(INTEGER));
		Assert.assertTrue("The beta2 entity must not have updated Boolean value.", beta2.getBool().equals(BOOLEAN));
		Assert.assertTrue("The beta2 entity must not have updated Decimal value.", beta2.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The beta2 entity must not have updated Double value.", beta2.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The beta2 entity must not have updated Float value.", beta2.getFloati().equals(FLOAT));
		Assert.assertTrue("The beta2 entity must not have updated String value.", beta2.getString().equals(STRING));
		Assert.assertTrue("The beta2 entity must not have updated Text value.", beta2.getText().equals(TEXT));

		Assert.assertTrue("The alpha entity must be returned.", alpha != null);
		Assert.assertTrue("The alpha entity must not have updated Integer value.", alpha.getInteger().equals(INTEGER));
		Assert.assertTrue("The alpha entity must not have updated Boolean value.", alpha.getBool().equals(BOOLEAN));
		Assert.assertTrue("The alpha entity must not have updated Decimal value.", alpha.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The alpha entity must not have updated Double value.", alpha.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The alpha entity must not have updated Float value.", alpha.getFloati().equals(FLOAT));
		Assert.assertTrue("The alpha entity must not have updated String value.", alpha.getString().equals(STRING));
		Assert.assertTrue("The alpha entity must not have updated Text value.", alpha.getText().equals(TEXT));
		Assert.assertTrue("The alpha entity must not have updated Date value.", alpha.getDate().getTime() == DATE.getTime());

		Assert.assertTrue("The delta entity must be returned.", delta != null);
		Assert.assertTrue("The delta entity must not have updated Integer value.", delta.getInteger().equals(INTEGER));
		Assert.assertTrue("The delta entity must not have updated Boolean value.", delta.getBool().equals(BOOLEAN));
		Assert.assertTrue("The delta entity must not have updated Decimal value.", delta.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The delta entity must not have updated Double value.", delta.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The delta entity must not have updated Float value.", delta.getFloati().equals(FLOAT));
		Assert.assertTrue("The delta entity must not have updated String value.", delta.getString().equals(STRING));
		Assert.assertTrue("The delta entity must not have updated Text value.", delta.getText().equals(TEXT));

		Assert.assertTrue("The delta2 entity must be returned.", delta2 != null);
		Assert.assertTrue("The delta2 entity must not have updated Integer value.", delta2.getInteger().equals(INTEGER));
		Assert.assertTrue("The delta2 entity must not have updated Boolean value.", delta2.getBool().equals(BOOLEAN));
		Assert.assertTrue("The delta2 entity must not have updated Decimal value.", delta2.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The delta2 entity must not have updated Double value.", delta2.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The delta2 entity must not have updated Float value.", delta2.getFloati().equals(FLOAT));
		Assert.assertTrue("The delta2 entity must not have updated String value.", delta2.getString().equals(STRING));
		Assert.assertTrue("The delta2 entity must not have updated Text value.", delta2.getText().equals(TEXT));

		Assert.assertTrue("The gama2 entity must be returned.", gama2 != null);
		Assert.assertTrue("The gama2 entity must not have updated Integer value.", gama2.getInteger().equals(INTEGER));
		Assert.assertTrue("The gama2 entity must not have updated Boolean value.", gama2.getBool().equals(BOOLEAN));
		Assert.assertTrue("The gama2 entity must not have updated Decimal value.", gama2.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The gama2 entity must not have updated Double value.", gama2.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The gama2 entity must not have updated Float value.", gama2.getFloati().equals(FLOAT));
		Assert.assertTrue("The gama2 entity must not have updated String value.", gama2.getString().equals(STRING));
		Assert.assertTrue("The gama2 entity must not have updated Text value.", gama2.getText().equals(TEXT));

		Assert.assertTrue("The alpha2 entity must be returned.", alpha2 != null);
		Assert.assertTrue("The alpha2 entity must not have updated Integer value.", alpha2.getInteger().equals(INTEGER));
		Assert.assertTrue("The alpha2 entity must not have updated Boolean value.", alpha2.getBool().equals(BOOLEAN));
		Assert.assertTrue("The alpha2 entity must not have updated Decimal value.", alpha2.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The alpha2 entity must not have updated Double value.", alpha2.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The alpha2 entity must not have updated Float value.", alpha2.getFloati().equals(FLOAT));
		Assert.assertTrue("The alpha2 entity must not have updated String value.", alpha2.getString().equals(STRING));
		Assert.assertTrue("The alpha2 entity must not have updated Text value.", alpha2.getText().equals(TEXT));
		Assert.assertTrue("The alpha2 entity must not have updated Date value.", alpha2.getDate().getTime() == DATE.getTime());

		Assert.assertTrue("The epsilon entity must be returned.", epsilon != null);
		Assert.assertTrue("The epsilon entity must not have updated Integer value.", epsilon.getInteger().equals(INTEGER));
		Assert.assertTrue("The epsilon entity must not have updated Boolean value.", epsilon.getBool().equals(BOOLEAN));
		Assert.assertTrue("The epsilon entity must not have updated Decimal value.", epsilon.getDecimal().equals(DECIMAL));
		Assert.assertTrue("The epsilon entity must not have updated Double value.", epsilon.getDoubli().equals(DOUBLE));
		Assert.assertTrue("The epsilon entity must not have updated Float value.", epsilon.getFloati().equals(FLOAT));
		Assert.assertTrue("The epsilon entity must not have updated String value.", epsilon.getString().equals(STRING));
		Assert.assertTrue("The epsilon entity must not have updated Text value.", epsilon.getText().equals(TEXT));
	}

	@Test
	public void testReopenedTransactionalCommitEntityUpdate() throws Exception {
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

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);
		
		beta.setInteger(INTEGER_UPDATE);
		beta.setBool(BOOLEAN_UPDATE);
		beta.setDecimal(DECIMAL_UPDATE);
		beta.setDoubli(DOUBLE_UPDATE);
		beta.setFloati(FLOAT_UPDATE);
		beta.setLongi(LONG_UPDATE);
		beta.setString(STRING_UPDATE);
		beta.setText(TEXT_UPDATE);

		beta2.setInteger(INTEGER_UPDATE);
		beta2.setBool(BOOLEAN_UPDATE);
		beta2.setDecimal(DECIMAL_UPDATE);
		beta2.setDoubli(DOUBLE_UPDATE);
		beta2.setFloati(FLOAT_UPDATE);
		beta2.setLongi(LONG_UPDATE);
		beta2.setString(STRING_UPDATE);
		beta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(alpha);


		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		gama2.setInteger(INTEGER_UPDATE);
		gama2.setBool(BOOLEAN_UPDATE);
		gama2.setDecimal(DECIMAL_UPDATE);
		gama2.setDoubli(DOUBLE_UPDATE);
		gama2.setFloati(FLOAT_UPDATE);
		gama2.setLongi(LONG_UPDATE);
		gama2.setString(STRING_UPDATE);
		gama2.setText(TEXT_UPDATE);
		
		delta.setInteger(INTEGER_UPDATE);
		delta.setBool(BOOLEAN_UPDATE);
		delta.setDecimal(DECIMAL_UPDATE);
		delta.setDoubli(DOUBLE_UPDATE);
		delta.setFloati(FLOAT_UPDATE);
		delta.setLongi(LONG_UPDATE);
		delta.setString(STRING_UPDATE);
		delta.setText(TEXT_UPDATE);

		delta2.setInteger(INTEGER_UPDATE);
		delta2.setBool(BOOLEAN_UPDATE);
		delta2.setDecimal(DECIMAL_UPDATE);
		delta2.setDoubli(DOUBLE_UPDATE);
		delta2.setFloati(FLOAT_UPDATE);
		delta2.setLongi(LONG_UPDATE);
		delta2.setString(STRING_UPDATE);
		delta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(gama2);


		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		alpha2.setInteger(INTEGER_UPDATE);
		alpha2.setBool(BOOLEAN_UPDATE);
		alpha2.setDecimal(DECIMAL_UPDATE);
		alpha2.setDoubli(DOUBLE_UPDATE);
		alpha2.setFloati(FLOAT_UPDATE);
		alpha2.setLongi(LONG_UPDATE);
		alpha2.setString(STRING_UPDATE);
		alpha2.setText(TEXT_UPDATE);
		alpha2.setDate(DATE_UPDATE);
		
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

		entityManager.updateEntity(epsilon);

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

		entityManager.rollbackTransaction();
	}

	@Test
	public void testTranscationalRollbackEntityUpdate() throws Exception {
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

		alpha.setInteger(INTEGER_UPDATE);
		alpha.setBool(BOOLEAN_UPDATE);
		alpha.setDecimal(DECIMAL_UPDATE);
		alpha.setDoubli(DOUBLE_UPDATE);
		alpha.setFloati(FLOAT_UPDATE);
		alpha.setLongi(LONG_UPDATE);
		alpha.setString(STRING_UPDATE);
		alpha.setText(TEXT_UPDATE);
		alpha.setDate(DATE_UPDATE);
		
		beta.setInteger(INTEGER_UPDATE);
		beta.setBool(BOOLEAN_UPDATE);
		beta.setDecimal(DECIMAL_UPDATE);
		beta.setDoubli(DOUBLE_UPDATE);
		beta.setFloati(FLOAT_UPDATE);
		beta.setLongi(LONG_UPDATE);
		beta.setString(STRING_UPDATE);
		beta.setText(TEXT_UPDATE);

		beta2.setInteger(INTEGER_UPDATE);
		beta2.setBool(BOOLEAN_UPDATE);
		beta2.setDecimal(DECIMAL_UPDATE);
		beta2.setDoubli(DOUBLE_UPDATE);
		beta2.setFloati(FLOAT_UPDATE);
		beta2.setLongi(LONG_UPDATE);
		beta2.setString(STRING_UPDATE);
		beta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(alpha);


		gama2.getDeltas().add(delta);
		gama2.getDeltas().add(delta2);
		entityManager.insertEntity(gama2);

		gama2.setInteger(INTEGER_UPDATE);
		gama2.setBool(BOOLEAN_UPDATE);
		gama2.setDecimal(DECIMAL_UPDATE);
		gama2.setDoubli(DOUBLE_UPDATE);
		gama2.setFloati(FLOAT_UPDATE);
		gama2.setLongi(LONG_UPDATE);
		gama2.setString(STRING_UPDATE);
		gama2.setText(TEXT_UPDATE);
		
		delta.setInteger(INTEGER_UPDATE);
		delta.setBool(BOOLEAN_UPDATE);
		delta.setDecimal(DECIMAL_UPDATE);
		delta.setDoubli(DOUBLE_UPDATE);
		delta.setFloati(FLOAT_UPDATE);
		delta.setLongi(LONG_UPDATE);
		delta.setString(STRING_UPDATE);
		delta.setText(TEXT_UPDATE);

		delta2.setInteger(INTEGER_UPDATE);
		delta2.setBool(BOOLEAN_UPDATE);
		delta2.setDecimal(DECIMAL_UPDATE);
		delta2.setDoubli(DOUBLE_UPDATE);
		delta2.setFloati(FLOAT_UPDATE);
		delta2.setLongi(LONG_UPDATE);
		delta2.setString(STRING_UPDATE);
		delta2.setText(TEXT_UPDATE);

		entityManager.updateEntity(gama2);


		iota.setAlpha(alpha2);
		iota.setEpsilon(epsilon);
		epsilon.getIotas().add(iota);
		entityManager.insertEntity(epsilon);

		alpha2.setInteger(INTEGER_UPDATE);
		alpha2.setBool(BOOLEAN_UPDATE);
		alpha2.setDecimal(DECIMAL_UPDATE);
		alpha2.setDoubli(DOUBLE_UPDATE);
		alpha2.setFloati(FLOAT_UPDATE);
		alpha2.setLongi(LONG_UPDATE);
		alpha2.setString(STRING_UPDATE);
		alpha2.setText(TEXT_UPDATE);
		alpha2.setDate(DATE_UPDATE);
		
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

		entityManager.updateEntity(epsilon);

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
