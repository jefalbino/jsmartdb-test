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

package batoo;

import java.math.BigDecimal;

public class BatooRo {

	private BatooTau tau;

	private BatooSigma sigma;

	private String string;

	private String text;

	private Integer integer;

	private Long longi;

	private Float floati;

	private Double doubli;

	private Boolean bool;

	private BigDecimal decimal;

	public BatooTau getTau() {
		return tau;
	}

	public void setTau(BatooTau tau) {
		this.tau = tau;
	}

	public BatooSigma getSigma() {
		return sigma;
	}

	public void setSigma(BatooSigma sigma) {
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
        if (obj != null && obj instanceof BatooRo) {
        	return tau != null && tau.equals(((BatooRo)obj).tau) && sigma != null && sigma.equals(((BatooRo)obj).sigma);
        }
        return false;
    }

}
