package huffmanCoding;

// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
//import huffmanCoding.PriorityQ.Node;

import java.io.*;
import java.util.*; // for Stack class


class HuffmanTree
{
	private Node root; // first node of tree
	// -------------------------------------------------------------
	public HuffmanTree() // constructor
	{ root = null; } // no nodes in tree yet
	
		
	
	//Inserts a tree pre-constructed tree into HuffmanTree:
	public void insertTree(Node huffmanRoot){
		if(root==null) // no node in root
			root = huffmanRoot;	//root 
		else{
			System.out.println("It seems as though this tree has already been populated...");
		}
	}
	
	
	
	
	
	
	

	public Node getRoot(){
		return root;
	}
	
	
	
	
	public void displayTree()
	{
		Stack globalStack = new Stack();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println(
				"......................................................");
		while(isRowEmpty==false)
		{
			Stack localStack = new Stack();
			isRowEmpty = true;
			for(int j=0; j<nBlanks; j++)
				System.out.print(' ');
			while(globalStack.isEmpty()==false)
			{
				Node temp = (Node)globalStack.pop();
				if(temp != null)
				{
					System.out.print(temp.character);
					System.out.print(temp.frequency);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);
					if(temp.leftChild != null ||
							temp.rightChild != null)
						isRowEmpty = false;
				}
				else
				{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<nBlanks*2-2; j++)
					System.out.print(' ');
			} // end while globalStack not empty
			System.out.println();
			nBlanks /= 2;
			while(localStack.isEmpty()==false)
				globalStack.push( localStack.pop() );
		} // end while isRowEmpty is false
		System.out.println(
				"......................................................");
	} // end displayTree()
	// -------------------------------------------------------------
} // end class Tree
