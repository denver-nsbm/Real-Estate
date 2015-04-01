package com.denver.citiestate.utils;

import com.denver.citiestate.interfaces.Listable;
import com.denver.citiestate.pojo.ListHouse;

public class SortedList extends List {

	/**
	 * 
	 * @param maxSpace
	 */
	public SortedList(int maxSpace) {
		super(maxSpace);
	}

	/**
	 * Check record exist
	 * @param record
	 * @return boolean
	 */
	public boolean recordExist(ListHouse record) {

		int i;
		for (i = 0; i < maxSpace; i++) {
			int result = record.compareTo(list[i]);
			if (result == 0 || result < 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Find from List House
	 * @param item
	 * @return Listable
	 */
	public Listable find(Listable item) {
		
		for (int i = 0; i < maxSpace; i++) {
			int result = item.compareTo(list[i]);
			if(result == 0){
				return list[i].copy();
			} else if(result < 0){
				return null;
			}
		}
		
		return null;
	}

	/**
	 * Insert to List House
	 * @param item
	 */
	public void insert(Listable item) {

		int i; // Holds the location of the List

		/* Check First element isEmpty */
		if (list[0] == null) {
			list[0] = item;
		} else {
			/* Find the sorted index */
			for (i = 0; i < maxSpace; i++) {

				if (item.compareTo(list[i]) < 0) {
					break;
				}
			}
			/* Swipe the last elements */
			for (int index = maxSpace; index > i; index--) {
				list[index] = list[index - 1];
			}
			/* Copy the element to sorted list */
			list[i] = item.copy();
		}
		maxSpace++;
	}

}
