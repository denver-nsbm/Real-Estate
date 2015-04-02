package com.denver.citiestate.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.denver.citiestate.pojo.ListHouse;

public class HouseFile {
	private FileOutputStream fop = null;
	private File file = new File("records.txt");
	private BufferedReader br = null;
	private StringBuilder storage = new StringBuilder();
	private SortedList list;

	/**
	 * Keep track of all the records before wirte to a file
	 * @param house
	 */
	public void addToStorage(ListHouse house) {
		storage.append(house.toString() + "\n");
	}

	/**
	 * Read records from the file
	 * @param list
	 * @return SortedList
	 */
	public SortedList readFromFile(SortedList list) {

		this.list = list;

		try {

			String line;
			br = new BufferedReader(new FileReader(file.getAbsolutePath()));

			while ((line = br.readLine()) != null) {
				String[] parts = line.split("\\|");
				ListHouse house = new ListHouse(Integer.parseInt(parts[0]),
						parts[1], parts[2], Integer.parseInt(parts[3]),
						Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
				list.insert(house);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return this.list;

	}

	/**
	 * Write to a file from Storage
	 */
	public void writeToFile() {

		try {
			fop = new FileOutputStream(file);

			if (!file.exists()) {
				file.createNewFile();
			}

			String storageString = storage.toString();
			byte[] contentInBytes = storageString.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}