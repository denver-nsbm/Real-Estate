package com.denver.citiestate.pojo;

import com.denver.citiestate.interfaces.Listable;

public class ListHouse implements Listable {

	private int lotNumber;
	private String lastName;
	private String firstName;
	private int price;
	private int squareFeet;
	private int bedRooms;

	/**
	 * 
	 * @param lotNumber
	 * @param firstName
	 * @param lastName
	 * @param price
	 * @param squareFeet
	 * @param bedRooms
	 */
	public ListHouse(int lotNumber, String firstName, String lastName,
			int price, int squareFeet, int bedRooms) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.lotNumber = lotNumber;
		this.price = price;
		this.squareFeet = squareFeet;
		this.bedRooms = bedRooms;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getLotNumber() {
		return lotNumber;
	}

	public int getPrice() {
		return price;
	}

	public int getSquareFeet() {
		return squareFeet;
	}

	public int getBedRooms() {
		return bedRooms;
	}

	/**
	 * Compare two Listhouse from Lot numbers
	 */
	@Override
	public int compareTo(Listable other) {

		ListHouse otherHouse = (ListHouse) other;
		return (this.lotNumber - otherHouse.lotNumber);
	}

	/**
	 * Take a copy of ListHouse
	 */
	@Override
	public Listable copy() {

		return new ListHouse(lotNumber, firstName, lastName, price, squareFeet,
				bedRooms);
	}

	/**
	 * ListHouse to String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String pipe = "|";

		result.append(lotNumber + pipe);
		result.append(firstName + pipe);
		result.append(lastName + pipe);
		result.append(price + pipe);
		result.append(squareFeet + pipe);
		result.append(bedRooms);

		return result.toString();
	}

}
