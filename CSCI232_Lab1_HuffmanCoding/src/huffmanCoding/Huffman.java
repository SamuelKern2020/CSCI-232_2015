package huffmanCoding;

public class Huffman {
	private PriorityQ queue1;
	private String inputString;        // input from user
	private String knownCharacters = "abcdefghijklmnopqrstuvwxyz ";
	int length = knownCharacters.length();
	char[] existingCharacters;
	int[] characterFrequencyTable; //Array that stores frequencies. Indices correspond exactly to existingCharacters[]
	private String codeTable[];     // code for each letter
	
	
	private HuffmanTree tree1;	//instance of HuffmanTree
	private BinaryCodeArray encodingArray;
	
	private String codedMessage;        // binary string
	
	
	
	private int strlen;
	private String capsString;      // converted to all caps
	private String decodedMsg;      // back to original msg
	
	
	public Huffman(String s){
		inputString = s;
		//Instantiates an instance of PriorityQ 
		queue1 = new PriorityQ (length);
		existingCharacters = new char[length];
		characterFrequencyTable = new int[length];	//Array that stores frequencies. Indices correspond exactly to existingCharacters[]

		codeTable = new String [length];
		encodingArray = new BinaryCodeArray();
		
		makeFrequencyTable();
		printFrequencyTable();
		createAndInsertNewNode();
		//printNodes();//removes each node from tree and prints it's value
		buildHuffmanTree();
	}
	
	
	private void makeFrequencyTable(){
		
		//Creates an array containing all available characters
		for (int x = 0; x < length; x++){
			existingCharacters [x] = knownCharacters.charAt(x);
			//System.out.print(existingCharacters[x] + ", ");
		}
		//Populates every index with a 0 to start
		for (int x = 0; x < length; x++){
			characterFrequencyTable[x] = 0;
			//System.out.print(characterFrequencyTable[x] + ", ");
		}
		
		//Creates frequency table
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
	}
	
	private void printFrequencyTable(){
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
		
	}
	
	
	private void createAndInsertNewNode(){
		//For every existing character, create a new node w/ the character and its frequency, and insert that node into the priority queue. 
		for (int x =0; x < length; x++){
			if(characterFrequencyTable[x] != 0){
				//create a new node
				queue1.createAndInsert(characterFrequencyTable[x], existingCharacters[x]);
			}
		}
	}
	
	private void printNodes(){//removes each node from tree and prints it's value
		//Remove all nodes and print their values:
				while(!queue1.isEmpty()){
					Node nodeRemoved = queue1.remove();
					System.out.println(nodeRemoved.character + " : " + nodeRemoved.frequency);
				}
	}

	
	private void buildHuffmanTree(){	//Links nodes in priorityQ together into one tree of nodes, then places root node into HuffmanTree
		//Link nodes:
		while (queue1.size()>1){
			//Remove the first 2 nodes from priority queue:
			Node child1 = queue1.remove();
			Node child2 = queue1.remove();
			//Create a new node, set frequency to be sum of child1 and child2, children set to be child1 and child2:
			Node parentNode = new Node();
			parentNode.frequency = child1.frequency + child2.frequency;
			parentNode.character = '+';
			parentNode.leftChild = child1;
			parentNode.rightChild = child2;
			//Insert back into the queue
			queue1.insertNode(parentNode);
		}
		
		//place Huffman Tree in HuffmanTree structure:
		tree1 = new HuffmanTree();
		if(queue1.size() == 1){
			tree1.insertTree(queue1.remove());	//The last node (which is the root of all other nodes) in the priority queue is placed as root in a newly created tree "tree1".
		}
		else{
			System.out.println("Sorry, but there seems to be more than one node in priorityQ 'queue1'");
		}
	}
	
	
//	//This needs to go in the show() method:
//	tree1.displayTree();
//	
//	//This needs to go in the code() method:
//	
//	tree1.generateBinaryCode(tree1.getRoot());	//Recursive traversal method that maps in binary codes to characterToCodeTable
//	
	
	
	
	
	public void displayTree(){
		if (tree1 != null){
			tree1.displayTree();
		}
	}
	
	
	
	

	public void encode(){
		
		generateCodeTable(tree1.getRoot());
		printCodeTable();
		printCodedMessage();
	}
	
						//Consider using a string instead of an array for this:
	
	private void generateCodeTable(Node currentNode){
			if(currentNode == null){
				return;
			}
			if (currentNode.leftChild != null){
				//Add a 0 to binaryCode
				encodingArray.addBinaryDigit("0");	
				System.out.println(encodingArray.getBinaryCode());
				}
			generateCodeTable(currentNode.leftChild);
			if(currentNode.character != '+'){
				//Assign the current value of binaryCode to the corresponding letter
				String currentCode = encodingArray.getBinaryCode();
				//Need to add code to characterToCodeTable[] found in BinaryCodeArray class
				//For now just printing:
				System.out.println("Letter " + currentNode.character +": "+ currentCode);
				addCodeToTable(currentNode.character, currentCode);
			}
			
			if (currentNode.rightChild != null){
				//Add a 1 to binaryCode
				encodingArray.addBinaryDigit("1");	
				System.out.println(encodingArray.getBinaryCode());
				}
			generateCodeTable(currentNode.rightChild);
			//if character in currentNode hasn't been assigned a binary code, assign code now.???
			if(!encodingArray.isEmpty()){
				//subtract a # from binaryCode[]
				encodingArray.removeBinaryDigit();
			}
			
		}
	
	private void addCodeToTable(char letter, String currentCode){
		//Locate the index of the given letter
		for(int index = 0; index<length; index++){
			if(letter == existingCharacters[index]){//Once a match is found, insert the currentCode into that cell
				codeTable[index] = currentCode;
				break;
			}
		}
	}
	
	private void printCodeTable(){
		
		for(int x=0;x<length;x++){
			if(!(codeTable[x] == null)){
				System.out.println(existingCharacters[x] + " " + codeTable[x]);
			}
		}
		System.out.println();
	}
	
	private void printCodedMessage(){
		codedMessage = "";
		for(int z=0; z<inputString.length();z++){
			for(int x=0; x<length; x++){
				if(inputString.charAt(z) == existingCharacters[x]){
					codedMessage = codedMessage + codeTable[x];
				}
			}
		}
		System.out.println("Coded Message: " + codedMessage);
	}
	
	
	
	public void decode(){
		
	}

}
