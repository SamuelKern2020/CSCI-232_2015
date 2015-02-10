package huffmanCoding;

// priorityQ.java
// demonstrates priority queue
// to run this program: C>java PriorityQApp
////////////////////////////////////////////////////////////////
class PriorityQ
{
	public static int numberNodes;	//Keeps track of how many nodes are inserted into the priority queue (represents the max ever inserted)
	// array in sorted order, from max at 0 to min at size-1
	
	private int maxSize;
	private Node[] queArray;
	
	//-------------------------------------------------------------
	public PriorityQ(int s) // constructor
	{
		maxSize = s;
		queArray = new Node[maxSize];
		numberNodes = 0;
	}
	//-------------------------------------------------------------
	
	
	
	//Creates a node with specified frequency and character, then inserts
	public void createAndInsert(int frequency, char character) // insert item
	{
		Node newNode = new Node(frequency, character);
		int j;
		if(numberNodes==0) // if no items,
			queArray[numberNodes++] = newNode; // insert at 0
		else // if items,
		{
			for(j=numberNodes-1; j>=0; j--) // start at end,
			{
				if( newNode.getFrequency() > queArray[j].getFrequency() ) // if new item larger,
					queArray[j+1] = queArray[j]; // shift upward
				else // if smaller,
					break; // done shifting
			} // end for
			queArray[j+1] = newNode; // insert it
			numberNodes++;
		} // end else (nItems > 0)
	} // end createAndInsert()
	
	
	//Same as above, but inserts a pre-constructed node
	public void insertNode(Node newNode) // insert item
	{
		int j;
		if(numberNodes==0) // if no items,
			queArray[numberNodes++] = newNode; // insert at 0
		else // if items,
		{
			for(j=numberNodes-1; j>=0; j--) // start at end,
			{
				if( newNode.getFrequency() > queArray[j].getFrequency() ) // if new item larger,
					queArray[j+1] = queArray[j]; // shift upward
				else // if smaller,
					break; // done shifting
			} // end for
			queArray[j+1] = newNode; // insert it
			numberNodes++;
		} // end else (nItems > 0)
	} // end insertNode()
	//-------------------------------------------------------------
	public Node remove() // remove minimum item
	{ return queArray[--numberNodes]; }
	//-------------------------------------------------------------
	public Node peekMin() // peek at minimum item
	{ return queArray[numberNodes-1]; }
	//-------------------------------------------------------------
	public boolean isEmpty() // true if queue is empty
	{ return (numberNodes==0); }
	//-------------------------------------------------------------
	public boolean isFull() // true if queue is full
	{ return (numberNodes == maxSize); }
	
	public int size(){	//Returns the number of nodes in the queue
		return numberNodes;
	}
	//-------------------------------------------------------------
} // end class PriorityQ
////////////////////////////////////////////////////////////////
//class PriorityQApp
//{
//	public static void main(String[] args) throws IOException
//	{
//		PriorityQ thePQ = new PriorityQ(5);
//		thePQ.insert(30);
//		thePQ.insert(50);
//		thePQ.insert(10);
//		thePQ.insert(40);
//		thePQ.insert(20);
//		while( !thePQ.isEmpty() )
//		{
//			long item = thePQ.remove();
//			System.out.print(item + ��� ���); // 10, 20, 30, 40, 50
//		} // end while
//		System.out.println(������);
//	} // end main()
	//-------------------------------------------------------------
//} // end class PriorityQApp
