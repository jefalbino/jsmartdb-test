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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "tau")
public class HibernateTau {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

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

	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	private HibernateFi fi;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tauId", cascade = CascadeType.ALL)
	private Set<HibernatePsi> psis = new HashSet<HibernatePsi>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "tau_pi", joinColumns = {@JoinColumn(name = "tau_id")}, inverseJoinColumns = {@JoinColumn(name = "pi_id")})
	private List<HibernatePi> pis = new ArrayList<HibernatePi>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.tau", cascade=CascadeType.ALL)
	private Set<HibernateRo> ros = new HashSet<HibernateRo>();

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

	public HibernateFi getFi() {
		return fi;
	}

	public void setFi(HibernateFi fi) {
		this.fi = fi;
	}

	public Set<HibernatePsi> getPsis() {
		return psis;
	}

	public void setPsis(Set<HibernatePsi> psis) {
		this.psis = psis;
	}

	public List<HibernatePi> getPis() {
		return pis;
	}

	public void setPis(List<HibernatePi> pis) {
		this.pis = pis;
	}

	public Set<HibernateRo> getRos() {
		return ros;
	}

	public void setRos(Set<HibernateRo> ros) {
		this.ros = ros;
	}

}
