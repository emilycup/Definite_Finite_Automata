// ================================================================================================
// Authors: 	Emily Le and Daniel Choi
// Project:		Language Translation and Automata
//					This project will take in a language (a .txt file) with  
//					a variety of strings. The program will determine whether the
//					string is is accepted or rejected.
// ================================================================================================

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	boolean accepted = false;
	public int numberOfStates = 0;
	public int finalStates = 0;
	public String transitions;
	public ArrayList<String> DFAInfo = new ArrayList();

	public static void main(String[] args) throws IOException {
		Main start = new Main();
		String fileName = "src/language.txt";
		start.readFile(fileName);
		start.displayFA();
	}

	// ==============================================================================================
	// 	READ FILE:
	//  	Reads in and parses the .txt file for information needed
	// ==============================================================================================
	public void readFile(String fileName) {
		String line = null;
		// // reading in the file
		try {
			int i = 0;
			FileReader fileReader = new FileReader(fileName);
			BufferedReader buffReader = new BufferedReader(fileReader);
			while ((line = buffReader.readLine()) != null) {
				System.out.println(line);
				DFAInfo.add(line);
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

	// ==============================================================================================
	// 	DISPLAY RESULTS:
	//  	Will display the results and information about the Definite Finite Automata to the user
	// ==============================================================================================
	public void displayFA() {
		int i = 3;
		System.out.println("--- FINITE STATE AUTOMATION ---");
		System.out.println("1) Number of states:" + DFAInfo.get(0));
		System.out.println("2) Final states: " + DFAInfo.get(1));
		System.out.println("3) Alphabet: " + DFAInfo.get(2).replace(" ", ", "));
		System.out.println("4) Transitions: ");
		while (DFAInfo.get(i).contains("(")){
			System.out.println("\t\t" + DFAInfo.get(i).replace("(", "").replace(")", ""));
			i++;
		}
		System.out.println("5) Strings: ");
		while (i < DFAInfo.size()) {
			System.out.println("\t\t" + DFAInfo.get(i));
			i++;
		}
	}
}
