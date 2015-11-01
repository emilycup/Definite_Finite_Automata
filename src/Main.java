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

/**
 * The FA will properly determine whether or not an input is accepted or rejected.
 *
 * This FA will properly solve problems #1 and #2 on the hw. I will be working on #3 and #4 on Sunday.
 * There are still a few problems regarding empty string testing, separation of the data within the .txt file.
 * We need to create a FA again for each rule, which is determined by an empty line. In addition, I have updated the
 * .txt and they will include the answers to #1-#4.
 */

public class Main {
    public boolean accepted = false;
    public int numberOfStates, state, finalValue;
    public String finalStates, transitionInfo, alphabet, fscopy, acopy;
    public ArrayList<String> DFAInfo = new ArrayList();
    public char[][] transitionTable;
    Set<Character> alphabetSet, finalstateset;
    ArrayList<String> stringsToCheck = new ArrayList<String>();

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
     *      will return true if the DFA is accepted and false if it is rejected
     */
    public boolean completeTransition(String word) {
        state = 0; // always will start at 0
        for (int i = 0; i < word.length(); i++) {
            //N stands for a numeric value, which shall be theorized as 1 for the transition
            if(Character.isDigit(word.charAt(i))) {
                if(alphabetSet.contains('N')) {
                    state = Character.getNumericValue(transitionTable[state][1]);
                } else if(alphabetSet.contains(word.charAt(i))) {
                    state = Character.getNumericValue(transitionTable[state][Character.getNumericValue(word.charAt(i))]);
                } else {
                    return false;
                }
            }

            //L stands for an alphabetic value, which shall be theorized as 0 for the transition

            else if(Character.isAlphabetic(word.charAt(i))) {
                if(alphabetSet.contains('L')) {
                    state = Character.getNumericValue(transitionTable[state][0]);
                } else {
                    return false;
                }
            } else {
                return false;
            }

            // will determine if the character is in the alphabet set
//            if (alphabetSet.contains(word.charAt(i))) {
//                state = Character.getNumericValue(transitionTable[state][Character.getNumericValue(word.charAt(i))]);
//            } else {
//
//            }
        }

        if (finalstateset.contains(Character.forDigit(state, 10))) {
            return true;
        } else
            return false;
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
        int i = 0, k = 0;
        alphabetSet = new HashSet<>();
        finalstateset = new HashSet<>();

        while(i < DFAInfo.size()) {
            numberOfStates = Integer.parseInt(DFAInfo.get(i));
            i++;

            fscopy = DFAInfo.get(i);
            finalStates = DFAInfo.get(i).replace(" ", "");
            for(int j = 0; j < finalStates.length(); j++) {
                finalstateset.add(finalStates.charAt(j));
            }
            i++;

            acopy = DFAInfo.get(i);
            alphabet = DFAInfo.get(i).replace(" ", "");
            for(int j = 0; j < alphabet.length(); j++) {
                alphabetSet.add(alphabet.charAt(j));
            }
            i++;

            transitionTable = new char[numberOfStates][alphabetSet.size()];
            k = i;
            while (DFAInfo.get(i).startsWith("(")) {
                transitionInfo = DFAInfo.get(i).replace("(", "").replace(")", "").replace(" ", "");
                transitionTable[Character.getNumericValue(transitionInfo.charAt(0))][Character.getNumericValue(transitionInfo.charAt(1))] = transitionInfo.charAt(2);
                i++;
            }

            while(i < DFAInfo.size()){
                if(DFAInfo.get(i).isEmpty()) {
                    i++;
                    break;
                }
                stringsToCheck.add(DFAInfo.get(i));
                i++;
            }
            displayFA(k);
            alphabetSet.clear();
            finalstateset.clear();
            stringsToCheck.clear();
        }
//        int fileLine = 3;
//
//        /**
//         * We will be using sets in order to keep track of the alphabet and final states.
//         * These sets will be used to compare and determine whether an input is accepted or rejected.
//         */

//
//        numberOfStates = Integer.parseInt(DFAInfo.get(0));
//
//        //Populating final states
//        finalStates = DFAInfo.get(1).replace(" ", "");
//        for(int i = 0; i < finalStates.length(); i++) {
//            finalstateset.add(finalStates.charAt(i));
//        }
//
//        //Populating alphabet states
//        alphabet = DFAInfo.get(2).replace(" ", "");
//        for(int i = 0; i < alphabet.length(); i++) {
//            alphabetSet.add(alphabet.charAt(i));
//        }
//
//        // initialize transition table
//        transitionTable = new char[numberOfStates][alphabetSet.size()];
//
//        // fill transition table
//        while (DFAInfo.get(fileLine).startsWith("(")) {
//            transitionInfo = DFAInfo.get(fileLine).replace("(", "").replace(")", "").replace(" ", "");
//            transitionTable[Character.getNumericValue(transitionInfo.charAt(0))][Character.getNumericValue(transitionInfo.charAt(1))] = transitionInfo.charAt(2);
//            fileLine++;
//        }
//
//        while(fileLine < DFAInfo.size()){
//            if(DFAInfo.get(fileLine).isEmpty()) {
//
//            }
//            stringsToCheck.add(DFAInfo.get(fileLine));
//            fileLine++;
//        }
    }

    /**
     * DISPLAY RESULTS:
     *      Will display the results and information about the Definite Finite Automata to the user
     */
    public void displayFA(int i) {
//        int i = 3;
        System.out.println("--- FINITE STATE AUTOMATION ---");
        System.out.println("1) Number of states: " + numberOfStates);
        System.out.println("2) Final states: " + fscopy.replace(" ", ", "));
        System.out.println("3) Alphabet: " + acopy.replace(" ", ", "));
        System.out.println("4) Transitions: ");
        while (DFAInfo.get(i).startsWith("(")) {
            System.out.println("\t\t" + DFAInfo.get(i).replace("(", "").replace(")", ""));
            i++;
        }
        System.out.println("5) Strings: ");
        for (int j = 0; j < stringsToCheck.size(); j++) {
            System.out.print("\t\t" + stringsToCheck.get(j) + " ");

            //We will be calling on our comparison function to determine whether the input is accepted or rejected
            if(completeTransition(stringsToCheck.get(j))) {
                System.out.println("Accepted");
            } else {
                System.out.println("Rejected");
            }
        }
        System.out.print("\n");
    }

    /**
     * PRINT TRANSITION TABLE:
     *      Will print the transition table
     */
    public void printTransitionTable(){
        for (int i = 0; i < numberOfStates; i++){
            for(int j = 0; j < alphabetSet.size(); j++){
                System.out.print(transitionTable[i][j] + " ");
            }
            System.out.print("\n");
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
    }
}