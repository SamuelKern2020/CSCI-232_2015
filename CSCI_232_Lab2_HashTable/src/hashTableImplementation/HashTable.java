package hashTableImplementation;

public class HashTable {
	BinaryTree [] hashTable;
	int size;
	public HashTable(int setSize){
		hashTable = new BinaryTree[setSize];
		size = setSize;
	}
	
	public void insert(Node node){
		//Do hash function on node.value
		//int hashIndex = Math.abs(node.value % size);	//Math.abs accounts for negative numbers
		int hashIndex = node.value % size;	//Math.abs accounts for negative numbers

		//int hashIndex = node.value;	//Math.abs accounts for negative numbers
		
		
		
		

		//Insert node to hash location
		if(hashTable[hashIndex]==null){//if the cell is empty (it does not contain a tree)
			BinaryTree newTree = new BinaryTree();	//create a new tree
			hashTable[hashIndex] = newTree;	//Insert the newly created tree into the hash table
			hashTable[hashIndex].insert(node);//Insert the node into the tree at the hash index
		}
		else{//a tree already exists in the cell
			hashTable[hashIndex].insert(node);	//just insert the node into that tree
		}
	}
	
	public void display(){
		System.out.println("Display hash table:");
		if(size > 51){//Sets 51 as the max size that can be displayed
			System.out.println("Sorry, but the hash table is too large to display");
		}
		else{//Display the hash table
			for(int i = 0; i<size; i++){
				if (hashTable[i] == null){
					System.out.println(i + ": No Values\n\n");
				}
				else{
					System.out.println(i + ": Values in cell:\n");
					hashTable[i].displayTree();
				}
				
			}
		}
	}
	
	public void find(int keyToFind){
		int hash = keyToFind % size;
		boolean isFound = hashTable[hash].find(keyToFind);
		if(isFound){
			System.out.println(keyToFind + " was found.");
		}
		else{
			System.out.println(keyToFind + " could not be found.");
		}
	}

}
