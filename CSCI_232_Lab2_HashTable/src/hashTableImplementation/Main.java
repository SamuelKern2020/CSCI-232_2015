package hashTableImplementation;

import java.util.Scanner;

public class Main {

	private static int sizeOfTable;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		//First, get the size of the hash table:
		System.out.println("Enter the size of hash table: ");
		String sizeInput = in.nextLine();
		sizeOfTable = Integer.parseInt(sizeInput);	//Convert from string to int
		System.out.println();
		
		//Creates new hashTable of given size	
		HashTable hashTable = new HashTable(sizeOfTable);
		
		do{
			System.out.println("Enter a number to insert into the hash table, enter 'd' to display the current table, or enter 'f' to find a value: ");
			String stringValue = in.nextLine();	//Read in a value
			
			
			if(stringValue.equals("d")){//Display the hash table
				hashTable.display();
			}
			
			else if(stringValue.equals("f")){//Find a value
				System.out.println("Enter key value to find: ");
				int key = Integer.parseInt(in.nextLine());	//Converts input to an int, stores as the key to find
				hashTable.find(key);
			}
			
			else{//Enter the value into the hash table
				int value = Integer.parseInt(stringValue); //Convert "value" from a String to an int
				
				try{
					//Create a new Node containing "value"
					Node nodeToInsert = new Node(value);

					//Insert the Node into the Hash table
					hashTable.insert(nodeToInsert);
				}
				catch (IndexOutOfBoundsException e) {
			        System.err.println("Value is out of range");
				
	
				}
			}

		}while(true);
		
	}


}

