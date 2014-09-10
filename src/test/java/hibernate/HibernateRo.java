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

package hibernate;

import java.math.BigDecimal;

import javax.persistence.AssociationOverrides;
import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ro")
@AssociationOverrides({	
		@AssociationOverride(name = "pk.tau", joinColumns = @JoinColumn(name = "tau_id")),
		@AssociationOverride(name = "pk.sigma", joinColumns = @JoinColumn(name = "sigma_id")) 
})
public class HibernateRo {

	@EmbeddedId
	private HibernateRoId pk = new HibernateRoId();

	@Column(name = "strng", length = 45)
	private String string;

	@Column(name = "txt")
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

	public HibernateRoId getPk() {
		return pk;
	}

	public void setPk(HibernateRoId pk) {
		this.pk = pk;
	}

	@Transient
	public HibernateTau getTau() {
		return pk.getTau();
	}

	public void setTau(HibernateTau tau) {
		this.pk.setTau(tau);
	}

	@Transient
	public HibernateSigma getSigma() {
		return pk.getSigma();
	}

	public void setSigma(HibernateSigma sigma) {
		this.pk.setSigma(sigma);
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

}
