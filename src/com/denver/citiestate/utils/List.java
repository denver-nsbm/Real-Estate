package com.denver.citiestate.utils;

import com.denver.citiestate.interfaces.Listable;

public abstract class List {

	/* Keeps the maximum size of the list */
	protected int maxSpace;
	/* Keeps current position of the list */
	protected int currentIndex;
	/* List of Listable */
	protected Listable[] list;

	/**
	 * Initialize the Listable list
	 * 
	 * @param maxSpace
	 */
	public List(int maxSpace) {
		super();
		currentIndex = 0;
		list = new Listable[maxSpace];
	}

	/**
	 * Reset the pointer in the Listable list
	 */
	public void reset() {
		currentIndex = 0;
	}

	/**
	 * Check Listable isfull
	 * 
	 * @return
	 */
	public boolean isFull() {
		return (list.length == maxSpace);
	}

	/**
	 * Get the size of the Listable list
	 * 
	 * @return size
	 */
	public int size() {
		return list.length;
	}

	/**
	 * Return the next Listable
	 * 
	 * @return Listable
	 */
	public Listable next() {
		if (currentIndex == maxSpace) {
			currentIndex = 0;
			return list[currentIndex++];
		} else {
			return list[currentIndex++];

		}
	}
	
	/**
	 * get the max Space 
	 * @return
	 */
	public int getMaxSpace() {
		return maxSpace;
	}

}
