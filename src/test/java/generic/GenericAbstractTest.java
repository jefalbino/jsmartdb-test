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

package generic;

import java.math.BigDecimal;
import java.util.Date;

import com.googlecode.flyway.core.Flyway;

public abstract class GenericAbstractTest {

	private static final String DB_URL = "jdbc:mysql://localhost:3306";
	private static final String DB_USER = "root";
	private static final String DB_PSWD = "root";
	private static final String DB_SCHEMA = "jsmartdb";

	protected static final Integer ID_SECOND = 1;
	protected static final Integer INTEGER = 1;
	protected static final Boolean BOOLEAN = true;
	protected static final BigDecimal DECIMAL = new BigDecimal(10);
	protected static final Double DOUBLE = 20.0d;
	protected static final Float FLOAT = 15.50f;
	protected static final Long LONG = 100000000000l;
	protected static final String STRING = "String";
	protected static final String TEXT = "A looooooonnnnnnnnnng text that could be used in the database.";
	protected static final String STRING_50 = "01234567890123456789012345678901234567890123456789";
	protected static final Date DATE = new Date(1405011510000l);

	private static Flyway flyway;

	protected static void setUpDatabase() throws Exception {
		flyway = new Flyway();
		flyway.setDataSource(DB_URL, DB_USER, DB_PSWD);
		flyway.setSchemas(DB_SCHEMA);
		flyway.setInitOnMigrate(true);
		flyway.setLocations("/db/migration");
		flyway.setSqlMigrationPrefix("v0");
		flyway.migrate();
	}

	protected static void tearDownDatabase() throws Exception {
		flyway.clean();
		flyway = null;
	}

}
