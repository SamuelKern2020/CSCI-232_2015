package huffmanCoding;

import java.util.Scanner;


public class Driver {

	
	
	public static void main(String[] args) {
		//Instantiate here:
		Scanner in = new Scanner(System.in);
		
		
		boolean done = false;
		while(!done){
			System.out.println("Enter the first letter of enter, show, code, or decode:");
			String letter = in.nextLine();
			if(letter.equals("e")){
				enter();
			}
			else if(letter.equalsIgnoreCase("s")){
				show();
			}
			else if(letter.equalsIgnoreCase("c")){
				code();
			}
			else if(letter.equalsIgnoreCase("d")){
				decode();
			}
			else{
				System.out.println("INVALID ENTRY. PLEASE TRY AGAIN.");
			}
		}
		

		
		

	}
	
	public static void enter(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter text: ");
		String inputString = in.nextLine();
		//String inputString = "hello world";
		
		
		//Creates an array containing all available characters
		String knownCharacters = "abcdefghijklmnopqrstuvwxyz ";
		int length = knownCharacters.length();
		
		//Instantiates an instance of PriorityQ 
		PriorityQ queue1 = new PriorityQ (length);
		
		
		//Where should I instantiate each Node?
		//Steps: create a node with a character and corresponding frequency
		
		char[] existingCharacters = new char[length];
		for (int x = 0; x < length; x++){
			existingCharacters [x] = knownCharacters.charAt(x);
			//System.out.print(existingCharacters[x] + ", ");
		}
		int[] characterFrequencyTable = new int[length];	//Array that stores frequencies. Indices correspond exactly to existingCharacters[]
		for (int x = 0; x < length; x++){
			characterFrequencyTable[x] = 0;
			//System.out.print(characterFrequencyTable[x] + ", ");
		}
		
		for (int characterIndex = 0; characterIndex < inputString.length(); characterIndex++){
			char currentCharacter = inputString.charAt(characterIndex);
			int index = 0;
			boolean found = false;
			while (index < length && !found){
				if(currentCharacter == existingCharacters[index]){
					characterFrequencyTable[index] = characterFrequencyTable[index]+1;
					found = true;
				}
				else{
					index++;
				}
			}
		}
		
		
		//For every existing character, create a new node w/ the character and its frequency, and insert that node into the priority queue. 
		for (int x =0; x < length; x++){
			if(characterFrequencyTable[x] != 0){
				//create a new node
				queue1.insert(characterFrequencyTable[x], existingCharacters[x]);
			}
		}
		
		
		
		
		//Print letters/characters + frequencies to Console
		System.out.println("------ Input & Frequency ------");
		System.out.println(inputString);
		for (int x =0; x < length; x++){
			System.out.print(existingCharacters[x] + " ");
		}
		System.out.println();
		for (int x =0; x < length; x++){
			System.out.print(characterFrequencyTable[x] + " ");
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Character : Frequency");
		
		//Remove all nodes and print their values:
		while(!queue1.isEmpty()){
			Node nodeRemoved = queue1.remove();
			System.out.println(nodeRemoved.character + " : " + nodeRemoved.frequency);
		}
		
		
	
	}
	

	public static void show(){
		
	}
	
	
	
	
	
	
	public static void code(){
		
	}
	
	public static void decode(){
		
	}

}
