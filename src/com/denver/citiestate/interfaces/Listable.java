package com.denver.citiestate.interfaces;

public interface Listable {
	/**
	 * 
	 * @param other
	 * @return
	 */
	public abstract int compareTo(Listable other);

	/**
	 * 
	 * @return Listable
	 */
	public abstract Listable copy();

}
