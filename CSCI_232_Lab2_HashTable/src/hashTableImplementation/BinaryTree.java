package hashTableImplementation;
import java.util.Stack;



public class BinaryTree {

	private Node root; // first node of tree
	// -------------------------------------------------------------
	public BinaryTree() // constructor
	{ root = null; } // no nodes in tree yet
	
		
	
	//Inserts a tree pre-constructed tree into HuffmanTree:
	public void insert(Node newNode){
		if(root==null) // no node in root
			root = newNode;	//root 
		else{//Root is occupied
			Node current = root;
			Node parent;
			while(true){ //(exits internally)
				parent = current;
				if (newNode.value < current.value){ //if the Node being inserted has a smaller value than the current Node
					current = current.leftChild;	//go left
					if(current == null){//If we've hit a leaf
						parent.leftChild = newNode;	//insert the Node as a left child of that leaf
						return;	//break out of the while loop
					}
				}
				else{//Node value is greater than the current Node's value
					current = current.rightChild;
					if(current == null){//if we've hit a leaf
						parent.rightChild = newNode;//insert the Node as a right child of that leaf
						return;//break out of while loop
					}
					
				}
			}
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
					System.out.print(temp.value);
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
	
	
	public boolean find(int key){
		{ // (assumes non-empty tree)
			Node current = root; // start at root
			while(current.value != key) // while no match,
			{
				if(key < current.value) // go left?
					current = current.leftChild;
				else // or go right?
					current = current.rightChild;
				if(current == null) // if no child,
					return false; // didn't find it
			}
			return true; // found it
		} // end find()

	}
}
