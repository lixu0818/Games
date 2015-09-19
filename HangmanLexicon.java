/*
 * This program reads words from a designated file "HangmanLexicon.txt" and constructs a library for Hangman to randomly select a word from
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {
	
	ArrayList<String> pool = new ArrayList<String>(); // Hangman libary is constructed with arraylist

	public HangmanLexicon() {

		try {
			BufferedReader rd = getFileToRead(); // read line by  line from "HangmanLexicon.txt"

			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				pool.add(line);
				
			}

			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		  }
	}

	private BufferedReader getFileToRead() {

		BufferedReader rd = null;
		while (rd == null) {
			try {
				String filename = "HangmanLexicon.txt";
				rd = new BufferedReader(new FileReader(filename));
			} catch (IOException ex) {
				System.out.println("Can't open that file.");
				break; //added later
			}
		}
		return rd;
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return pool.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return pool.get(index);
		}
	}