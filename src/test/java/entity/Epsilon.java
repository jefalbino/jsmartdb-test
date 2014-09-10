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
import java.util.Set;

import com.jsmartdb.framework.types.CascadeType;
import com.jsmartdb.framework.types.JoinType;

import com.jsmartdb.framework.annotation.Column;
import com.jsmartdb.framework.annotation.Id;
import com.jsmartdb.framework.annotation.Join;
import com.jsmartdb.framework.annotation.OneToMany;
import com.jsmartdb.framework.annotation.Table;
import com.jsmartdb.framework.manager.Entity;

@Table(name = "epsilon")
public class Epsilon extends Entity {

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

	@OneToMany(join = @Join(type = JoinType.LEFT_OUTER_JOIN), 
			cascade = {CascadeType.INSERT, CascadeType.UPDATE, CascadeType.DELETE})
	private Set<Iota> iotas;

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

	public Set<Iota> getIotas() {
		return iotas;
	}

	public void setIotas(Set<Iota> iotas) {
		this.iotas = iotas;
	}

}
