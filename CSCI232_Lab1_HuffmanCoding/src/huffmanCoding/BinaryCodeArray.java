package huffmanCoding;

public class BinaryCodeArray {

	static int maxDepthOfHuffmanTree = (int) (PriorityQ.numberNodes + 1)/2 + 1; //depth = (totalNumNodes + 1)/2 
	
	
	String[] binaryDigits;	//Array that holds 1 or 0 in each cell
	
	public BinaryCodeArray(){
		binaryDigits = new String[100];	//Creates a new array of size Maximum Depth of Huffman Tree
	}
	
	private int nextOpenIndex = 0;
	
	public void addBinaryDigit(String oneOrZero){
		if(nextOpenIndex >=0){
			binaryDigits[nextOpenIndex] = oneOrZero;
			nextOpenIndex++;
		}
		else{
			System.out.println("Error");
		}
		
	}
	
	public void removeBinaryDigit(){
		binaryDigits[nextOpenIndex - 1] = null;
		nextOpenIndex--;
	}
	
	public String getBinaryCode(){
		String code = "";
		if(nextOpenIndex == 0){//if the array is empty, leave the code alone
			code = "";
		}
		else{
			for(int x = 0; x< nextOpenIndex; x++){
				code = code + binaryDigits[x];

			}
		}
		return code;
		
	}
	public boolean isEmpty(){
		if(nextOpenIndex<=0){
			return true;
		}
		else{
			return false;
		}
	}
	static String[] characterToCodeTable = new String[Driver.length];
}
