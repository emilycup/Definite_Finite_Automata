// =============================================================================
// Authors: 	Emily Le and Daniel Choi
// Project:		Language Translation and Automata
//					This project will take in a language (a .txt file) with  
//					a variety of strings. The program will determine whether the
//					string is is accepted or rejected.
// =============================================================================

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	static int numberOfStates = 0;
	static int finalStates = 0;
	static String[] alphabet;
	static int i = 0;
	static String transitions;

	public static void main(String[] args) throws IOException {
		String fileName = "src/language.txt";
		readFile(fileName);
		// displayFA();
	}

	public static void readFile(String fileName) {
		String line = null;
		// // reading in the file
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader buffReader = new BufferedReader(fileReader);
			while ((line = buffReader.readLine()) != null) {
				//alphabet[i] = line;
				System.out.println(line);
				i++;
			}
			buffReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("A file was not found!");
		}
		catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
	}

	public static void displayFA() {
		System.out.println("Number of states:" + alphabet[0]);
	}
}
