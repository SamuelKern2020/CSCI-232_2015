package huffmanCoding;

public class BinaryCodeArray {

	static int maxDepthOfHuffmanTree = (int) (PriorityQ.numberNodes + 1)/2 + 1; //depth = (totalNumNodes + 1)/2 
	
	static String[] binaryDigits = new String[maxDepthOfHuffmanTree];	//Creates a new array of size Maximum Depth of Huffman Tree
	
	static int nextOpenIndex = 0;
	
	public static void addBinaryDigit(String oneOrZero){
		if(nextOpenIndex >=0){
			binaryDigits[nextOpenIndex] = oneOrZero;
			nextOpenIndex++;
		}
		else{
			System.out.println("Error");
		}
		
	}
	
	public static void removeBinaryDigit(){
		binaryDigits[nextOpenIndex - 1] = null;
		nextOpenIndex--;
	}
	
	public static String getBinaryCode(){
		String code = "";
		if(nextOpenIndex == 0){//if the array is empty, leave the code alone
			code = "";
		}
		else{
			for(int x = 0; x<= nextOpenIndex; x++){
				code = code + binaryDigits[x];

			}
		}
		return code;
		
	}
	static String[] characterToCodeTable = new String[Driver.length];
}
