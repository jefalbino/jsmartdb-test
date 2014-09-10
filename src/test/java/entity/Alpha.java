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

package entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.jsmartdb.framework.types.CascadeType;
import com.jsmartdb.framework.types.JoinLevel;
import com.jsmartdb.framework.types.JoinType;

import com.jsmartdb.framework.annotation.Column;
import com.jsmartdb.framework.annotation.Join;
import com.jsmartdb.framework.annotation.JoinColumn;
import com.jsmartdb.framework.annotation.Id;
import com.jsmartdb.framework.annotation.OneToMany;
import com.jsmartdb.framework.annotation.OneToOne;
import com.jsmartdb.framework.annotation.QueryFilter;
import com.jsmartdb.framework.annotation.QueryFilters;
import com.jsmartdb.framework.annotation.Table;
import com.jsmartdb.framework.manager.Entity;

@Table(name = "alpha", orderBy = "id")
@QueryFilters(filters = {
		@QueryFilter(name = Alpha.QUERY_WHERE_EMPTY, where = ""),
		@QueryFilter(name = Alpha.QUERY_REPEATED_QUERY_FILTER, where = "1 = 1"),
		@QueryFilter(name = Alpha.QUERY_REPEATED_QUERY_FILTER, where = "1 = 1"),

		@QueryFilter(name = Alpha.QUERY_BY_ID, where = "id = " + Alpha.PARAM_ID),
		@QueryFilter(name = Alpha.QUERY_BY_STRING, where = "string = " + Alpha.PARAM_STRING),
		@QueryFilter(name = Alpha.QUERY_BY_NUMBER, where = "floati = " + Alpha.PARAM_NUMBER),
		@QueryFilter(name = Alpha.QUERY_BY_DATE, where = "date >=" + Alpha.PARAM_DATE_ONE + "and date <" + Alpha.PARAM_DATE_TWO),
		@QueryFilter(name = Alpha.QUERY_BY_BETWEEN_DATE, where = "date between" + Alpha.PARAM_DATE_ONE + "and" + Alpha.PARAM_DATE_TWO),
		@QueryFilter(name = Alpha.QUERY_BY_NUMBER_AND_STRING, where = "floati =" + Alpha.PARAM_NUMBER + "and string =" + Alpha.PARAM_STRING),
		@QueryFilter(name = Alpha.QUERY_BY_NULL_STRING, where = "string is null"),

		@QueryFilter(name = Alpha.QUERY_BY_ONE_TO_ONE_ID, where = "gama.id = " + Alpha.PARAM_ID),
		@QueryFilter(name = Alpha.QUERY_BY_ONE_TO_ONE_STRING, where = "gama.string = " + Alpha.PARAM_STRING),
		@QueryFilter(name = Alpha.QUERY_BY_ONE_TO_ONE_DELTA_ID, where = "gama.deltas.id = " + Alpha.PARAM_ID),

		@QueryFilter(name = Alpha.QUERY_MAX_INTEGER_AND_INTEGER_AND_STRING, select = "max(integer),integer,string", where = "1 = 1"),
		@QueryFilter(name = Alpha.QUERY_INTEGER_AND_STRING_AND_FLOAT, select = "integer,string,floati", where = "1 = 1"),
})
public class Alpha extends Entity {

	public static final String QUERY_WHERE_EMPTY = "QUERY_WHERE_EMPTY";
	public static final String QUERY_REPEATED_QUERY_FILTER = "QUERY_REPEATED_QUERY_FILTER";

	public static final String QUERY_BY_ID = "QUERY_BY_ID";
	public static final String QUERY_BY_STRING = "QUERY_BY_STRING";
	public static final String QUERY_BY_NUMBER = "QUERY_BY_NUMBER";
	public static final String QUERY_BY_NUMBER_AND_STRING = "QUERY_BY_NUMBER_AND_STRING";
	public static final String QUERY_BY_ONE_TO_ONE_ID = "QUERY_BY_ONE_TO_ONE_ID";
	public static final String QUERY_BY_ONE_TO_ONE_STRING = "QUERY_BY_ONE_TO_ONE_STRING";
	public static final String QUERY_BY_ONE_TO_ONE_DELTA_ID = "QUERY_BY_ONE_TO_ONE_DELTA_ID";
	public static final String QUERY_BY_DATE = "QUERY_BY_DATE";
	public static final String QUERY_BY_BETWEEN_DATE = "QUERY_BY_BETWEEN_DATE";
	public static final String QUERY_BY_NULL_STRING = "QUERY_BY_NULL_STRING";
	public static final String QUERY_MAX_INTEGER_AND_INTEGER_AND_STRING = "QUERY_MAX_INTEGER_AND_INTEGER_AND_STRING";
	public static final String QUERY_INTEGER_AND_STRING_AND_FLOAT = "QUERY_INTEGER_AND_STRING_AND_FLOAT";

	public static final String PARAM_ID = "PARAM_ID";
	public static final String PARAM_STRING = "PARAM_STRING";
	public static final String PARAM_NUMBER = "PARAM_NUMBER";
	public static final String PARAM_DATE_ONE = "PARAM_DATE_ONE";
	public static final String PARAM_DATE_TWO = "PARAM_DATE_TWO";

	@Id(name = "id", generated = true)
	private Integer id;

	@Column(name = "strng", length = 45)
	private String string;

	@Column(name = "txt", length = Column.NO_LENGTH)
	private String text;

	@Column(name = "intgr")
	private Integer integer;

	@Column(name = "lng")
	private Long longi;

	@Column(name = "flt")
	private Float floati;

	@Column(name = "dbl")
	private Double doubli;

	@Column(name = "bl")
	private Boolean bool;

	@Column(name = "dcml")
	private BigDecimal decimal;

	@Column(name = "date")
	private Date date;

	@OneToOne(joinColumn = @JoinColumn(column = "gama_id", referer = "id"),
			cascade = {CascadeType.INSERT, CascadeType.UPDATE, CascadeType.DELETE},
			join = @Join(type = JoinType.LEFT_OUTER_JOIN, level = JoinLevel.JOIN_ALL_LEVELS))
	private Gama gama;

	@OneToMany(joinColumn = @JoinColumn(column = "id", referer = "alphaId"), 
			cascade = {CascadeType.INSERT, CascadeType.UPDATE, CascadeType.DELETE},
			join = @Join(type = JoinType.LEFT_OUTER_JOIN))
	private Set<Beta> betas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getInteger() {
		return integer;
	}

	public void setInteger(Integer integer) {
		this.integer = integer;
	}

	public Long getLongi() {
		return longi;
	}

	public void setLongi(Long longi) {
		this.longi = longi;
	}

	public Float getFloati() {
		return floati;
	}

	public void setFloati(Float floati) {
		this.floati = floati;
	}

	public Double getDoubli() {
		return doubli;
	}

	public void setDoubli(Double doubli) {
		this.doubli = doubli;
	}

	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}

	public BigDecimal getDecimal() {
		return decimal;
	}

	public void setDecimal(BigDecimal decimal) {
		this.decimal = decimal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Gama getGama() {
		return gama;
	}

	public void setGama(Gama gama) {
		this.gama = gama;
	}

	public Set<Beta> getBetas() {
		return betas;
	}

	public void setBetas(Set<Beta> betas) {
		this.betas = betas;
	}

}
