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

import com.jsmartdb.framework.types.CascadeType;
import com.jsmartdb.framework.types.TableType;

import com.jsmartdb.framework.annotation.Column;
import com.jsmartdb.framework.annotation.JoinId;
import com.jsmartdb.framework.annotation.Table;
import com.jsmartdb.framework.manager.Entity;

@Table(name = "ro", type = TableType.JOIN_TABLE)
public class Ro extends Entity {

	@JoinId(column = "tau_id", referer = "id",
			cascade = {CascadeType.INSERT, CascadeType.UPDATE, CascadeType.DELETE})
	private Tau tau;

	@JoinId(column = "sigma_id", referer = "id", 
			cascade = {CascadeType.INSERT, CascadeType.UPDATE, CascadeType.DELETE})
	private Sigma sigma;

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

	public Tau getTau() {
		return tau;
	}

	public void setTau(Tau tau) {
		this.tau = tau;
	}

	public Sigma getSigma() {
		return sigma;
	}

	public void setSigma(Sigma sigma) {
		this.sigma = sigma;
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

	@Override
	public int hashCode() {
        int hash;
        hash = (tau != null ? tau.hashCode() : 0);
        hash = 31 * hash + (sigma != null ? sigma.hashCode() : 0);
        return hash;
    }

	@Override
	public boolean equals(Object obj) {
        if (obj != null && obj instanceof Ro) {
        	return tau != null && tau.equals(((Ro)obj).tau) && sigma != null && sigma.equals(((Ro)obj).sigma);
        }
        return false;
    }

}
