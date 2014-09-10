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

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class HibernateRoId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private HibernateTau tau;

	@ManyToOne
	private HibernateSigma sigma;

	public HibernateTau getTau() {
		return tau;
	}

	public void setTau(HibernateTau tau) {
		this.tau = tau;
	}

	public HibernateSigma getSigma() {
		return sigma;
	}

	public void setSigma(HibernateSigma sigma) {
		this.sigma = sigma;
	}

	@Override
    public int hashCode() {
        int result;
        result = (tau != null ? tau.hashCode() : 0);
        result = 31 * result + (sigma != null ? sigma.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
		if (obj != null && obj instanceof HibernateRoId) {
        	return tau != null && tau.equals(((HibernateRoId)obj).tau) && sigma != null && sigma.equals(((HibernateRoId)obj).sigma);
        }
        return false;
    }

}
