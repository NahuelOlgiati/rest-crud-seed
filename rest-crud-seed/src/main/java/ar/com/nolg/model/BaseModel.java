package ar.com.nolg.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("serial")
public abstract class BaseModel implements Serializable {
	/**
	 */
	public abstract Long getID();

	/**
	 */
	public abstract void setID(Long id);

	/**
	 */
	@XmlTransient
	public final boolean isNew() {
		return getID().equals(0l);
	}

	/**
	 */
	@XmlTransient
	public final boolean isPersisted() {
		return !getID().equals(0l);
	}

	/**
	 */
	@Override
	public int hashCode() {
		return getID().hashCode();
	}

	/**
	 */
	@Override
	public boolean equals(Object to) {
		return getID().equals(((BaseModel) to).getID());
	}
}