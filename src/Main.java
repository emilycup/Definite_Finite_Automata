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
import java.util.HashSet;
import java.util.Set;

public class Main {
    public boolean accepted = false;
    public int numberOfStates;
    public String finalStates;
    public String alphabet;
    public String transitions;
    public ArrayList<String> DFAInfo = new ArrayList();
    public char[][] transitionTable;
    String transitionInfo;
    Set<Character> alphabetSet;
    ArrayList<String> stringsToCheck = new ArrayList<String>();
    public int state;
    public int finalValue;


    /**
     * READ FILE:
     * Reads in and parses the .txt file for information needed
     *
     * @param fileName
     */
    public void readFile(String fileName) {
        String line = null;
        // // reading in the file
        try {
            int i = 0;
            FileReader fileReader = new FileReader(fileName);
            BufferedReader buffReader = new BufferedReader(fileReader);
            while ((line = buffReader.readLine()) != null) {
                DFAInfo.add(line);
                i++;
            }
            buffReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("A file was not found!");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    /**
     * TRANSITION:
     *      will return truee if the DFA is accepted and false if it is rejected
     */
    public boolean completeTransition(String word){
        state = 0; // always will start at 0
        finalValue = numberOfStates;

        for (int i = 0; i < word.length(); i++){

            // will determine if the character is in the alphabet set
            if(!alphabetSet.contains(word.charAt(i))){
                return false;
            }
            else{
                  state = Character.getNumericValue(transitionTable[state][Character.getNumericValue(word.charAt(i))]);
            }
        }
        // if (state = numberOfStates){
        //      this is a word :) so return true
        // } else
        //      return false;
        // }
        return false; // REPLACE THIS WHEN FINISHED
    }


//	     state ← initial_state;   exit ← false;
//	     while not exit do
//			     begin
//	          symbol ← NextSymbol();
//	          if symbol is in alphabet then begin
//	                 state ← NextState(state, symbol);
//	                 if state is dead_end then begin exit ← true;  Reject();  end
//	                 end
//	          else begin
//		       exit ← true;
//	               if symbol is not the endmarker then Reject()
//	               else if state is final then Accept()
//	               else Reject();
//	         end //if
//	    end //while


    /**
     * SET VALUES:
     *      Will display the results and information about the Definite Finite Automata to the user
     */
    public void setValues() {
        int fileLine = 3;
        numberOfStates = Integer.parseInt(DFAInfo.get(0));
        finalStates = DFAInfo.get(1);

        alphabet = DFAInfo.get(2).replace(" ", "");
        alphabetSet = new HashSet<>();

        // adding characters to the set
        for (int i = 0; i < alphabet.length(); i++){
            alphabetSet.add(alphabet.charAt(i));
        }

        // initialize transition table
        transitionTable = new char[numberOfStates][alphabet.length()];

        // fill transition table
        while (DFAInfo.get(fileLine).startsWith("(")) {
            transitionInfo = DFAInfo.get(fileLine).replace("(", "").replace(")", "").replace(" ", "");
            transitionTable[Character.getNumericValue(transitionInfo.charAt(0))][Character.getNumericValue(transitionInfo.charAt(1))] = transitionInfo.charAt(2);
            fileLine++;
        }

        while(fileLine < DFAInfo.size()){
            stringsToCheck.add(DFAInfo.get(fileLine));
            fileLine++;
        }
    }

    /**
     * DISPLAY RESULTS:
     *      Will display the results and information about the Definite Finite Automata to the user
     */
    public void displayFA() {
        int i = 3;
        System.out.println("--- FINITE STATE AUTOMATION ---");
        System.out.println("1) Number of states: " + numberOfStates);
        System.out.println("2) Final states: " + finalStates);
        System.out.println("3) Alphabet: " + DFAInfo.get(2).replace(" ", ", "));
        System.out.println("4) Transitions: ");
        while (DFAInfo.get(i).startsWith("(")) {
            System.out.println("\t\t" + DFAInfo.get(i).replace("(", "").replace(")", ""));
            i++;
        }
        System.out.println("5) Strings: ");
        while (i < DFAInfo.size()) {
            System.out.println("\t\t" + DFAInfo.get(i));
            i++;
        }
    }

    /**
     * PRINT TRANSITION TABLE:
     *      Will print the transition table
     */
    public void printTransitionTable(){
        for (int i = 0; i < numberOfStates; i++){
            for(int j = 0; j < alphabet.length(); j++){
                System.out.println(transitionTable[i][j]);
            }

        }
    }

    /**
     * This is the main class that will call on other classes to run the program
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Main start = new Main();
        String fileName = "src/language.txt";
        start.readFile(fileName);
        start.setValues();
        start.completeTransition("10");
        start.displayFA();
    }
}
