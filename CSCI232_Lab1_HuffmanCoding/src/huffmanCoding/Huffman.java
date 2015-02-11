package huffmanCoding;

public class Huffman {
	private PriorityQ queue1;
	private String inputString;        // input from user
	//private String knownCharacters = "abcdefghijklmnopqrstuvwxyz";
	private String knownCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	int length = knownCharacters.length();
	char[] existingCharacters;
	int[] characterFrequencyTable; //Array that stores frequencies. Indices correspond exactly to existingCharacters[]
	private String codeTable[];     // code for each letter
	
	
	private HuffmanTree tree1;	//instance of HuffmanTree
	private BinaryCodeArray encodingArray;
	
	private String codedMessage;        // binary string
	private String decodedMessage;      // back to original msg
	
	
	private int strlen;
	private String capsString;      // converted to all caps
	
	
	
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
				//Changes spaces to '_'
				if(currentCharacter == ' '){
					currentCharacter = '_';
				}
				//Finds matches
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
	
	//For testing purposes
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
	
	
	private void generateCodeTable(Node currentNode){
			if(currentNode == null){
				return;
			}
			if (currentNode.leftChild != null){
				//Add a 0 to binaryCode
				encodingArray.addBinaryDigit("0");	
				//System.out.println(encodingArray.getBinaryCode());
				}
			generateCodeTable(currentNode.leftChild);
			if(currentNode.character != '+'){
				//Assign the current value of binaryCode to the corresponding letter
				String currentCode = encodingArray.getBinaryCode();
				//System.out.println("Letter " + currentNode.character +": "+ currentCode);
				//Need to add code to characterToCodeTable[] found in BinaryCodeArray class
				addCodeToTable(currentNode.character, currentCode);
			}
			
			if (currentNode.rightChild != null){
				//Add a 1 to binaryCode
				encodingArray.addBinaryDigit("1");	
				//System.out.println(encodingArray.getBinaryCode());
				}
			generateCodeTable(currentNode.rightChild);
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
	
	
	
	public void decode()
	{
		decodedMessage = "";
		int cmLength = codedMessage.length();
		int j = 0;
		while(j < cmLength)
		{
			Node theNode = tree1.getRoot();  // start at root
			while(theNode.character == '+')       // until leaf,
			{
				if(codedMessage.charAt(j++) == '0'){// if '0'
					// go left
					theNode = theNode.leftChild;
				}
					
				else {// if '1'
					// go right
					theNode = theNode.rightChild;
				}
					
			}
			System.out.println(theNode.character);
			//reached a leaf node:
			
			//If '_', convert to a space
			if(theNode.character == '_'){
				theNode.character = ' ';
			}
			//Adds the character to the decoded message
			decodedMessage = decodedMessage + theNode.character; // letter at leaf node
			System.out.println(decodedMessage);
		}                                    
		System.out.println("Decoded Message: " + decodedMessage);
		System.out.println();
	}  // end decode()


}
