package huffmanCoding;

import java.util.Scanner;


public class Driver {

	static int length;
	
	
	public static void main(String[] args) {
		//Instantiate here:
		Scanner in = new Scanner(System.in);
		Huffman huffman1 = null;
		String inputString;
		
		boolean done = false;
		while(!done){
			System.out.println("Enter the first letter of enter, show, code, or decode:");
			String letter = in.nextLine();
			
			switch(letter) {
			case "e":
				System.out.println("Enter text: ");
				inputString = in.nextLine();
				huffman1 = new Huffman(inputString);
				
				break;
				
			case "s":
				huffman1.displayTree();
				break;
				
			case "c":
				huffman1.encode();
				break;
				
			case "d":
				huffman1.decode();
				break;
				
				default:
					System.out.println("INVALID ENTRY. PLEASE TRY AGAIN.");
			}
		}
	}
	
	
	
	public static void enter(){

	}
	
	
	

	public static void show(){
		
	}

	public static void code(){
		
	}
	
	public static void decode(){
		
	}

}
