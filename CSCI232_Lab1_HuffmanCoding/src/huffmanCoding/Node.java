package huffmanCoding;

class Node
{
	public int frequency; // data item (key)
	public char character; // data item
	public Node leftChild; // this node's left child
	public Node rightChild; // this node's right child
	
	public Node(int thisFrequency, char thisChar){
		frequency = thisFrequency;
		character = thisChar;
	}
	
	public Node(){
		
	}
	
	public int getFrequency(){
		return frequency;
	}
	public char getCharacter(){
		return character;
	}
	
	public void displayNode() // display ourself
	{
		System.out.print('{');
		System.out.print(frequency);
		System.out.print(", ");
		System.out.print(character);
		System.out.print("} ");
	}
} // end class Node
