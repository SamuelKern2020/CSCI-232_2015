package lab3;

public class FloydsDriver {

	final static int MAX_VALUE = Integer.MAX_VALUE/2;
	//GraphData graph1 = new GraphData();
	
	
	public static void main(String[] args) {
		
		//Notes for this program assignment:
		/*
		 * Create a 2-D array w/ hardcoded edges between vertices (this should maybe be in a seperate class - that way a user could modify the 2-D array in a seperate class
		 * 
		 * Execute Floyd's algorithm to go through the 2-D array and update edges if edge weight is less than the current weight
		 * - Essentially a triple "for" loop
		 * 
		 * Print each step
		 */
		
		
		//Define the graph:
		
		char [] letters = {'A','B','C','D','E'};
		int numPoints = 5;
		int [][] matrix = new int [numPoints][numPoints];
		
		for(int y=0; y<numPoints; y++){
			for (int x=0; x<numPoints; x++){
				if(x==y){
					matrix[x][y] = 0;
				}
				else{
					matrix[x][y] = MAX_VALUE;
				}
				
			}
		}
		matrix[1][0] = 50;
		matrix[3][0] = 80;
		matrix[2][1] = 60;
		matrix[3][1] = 90;
		matrix[4][2] = 40;
		matrix[2][3] = 20;
		matrix[4][3] = 70;
		matrix[1][4] = 50;
		
		//printGraph(matrix, numPoints);
		System.out.println("   A    B    C    D    E");
		for(int y=0; y<numPoints; y++){
			System.out.print(letters[y] + "  ");
			for (int x=0; x<numPoints; x++){
				if(matrix[x][y] == 0){
					System.out.print("__   ");
				}
				else if (matrix[x][y]==MAX_VALUE){
					System.out.print("INF  ");
				}
				else{
					System.out.print(matrix[x][y] + "   ");
				}
			}
			System.out.println("\n");
		}
		
	
	// Compute successively better paths through vertex k.
	for (int k=0; k<numPoints;k++) {

  		// Do so between each possible pair of points.
  		for (int i=0; i<numPoints; i++) {
    		for (int j=0; j<numPoints;j++) {
    		
    
      			if (matrix[i][k]+matrix[k][j] < matrix[i][j]) {
      				matrix[i][j] = matrix[i][k]+matrix[k][j];
      				System.out.println("   A    B    C    D    E");
      				for(int y=0; y<numPoints; y++){
      					System.out.print(letters[y] + "  ");
      					for (int x=0; x<numPoints; x++){
      						if(matrix[x][y] == 0){
      							System.out.print("__   ");
      						}
      						else if (matrix[x][y]==MAX_VALUE){
      							System.out.print("INF  ");
      						}
      						else{
      							System.out.print(matrix[x][y] + "   ");
      						}
      					}
      					System.out.println("\n");
      				}
      				System.out.println();
      				//path[i][j] = path[k][j];
      			}
      		}
  		}
	}
	
	
//	public static void printGraph(int[][] graph, int n) {	
//		System.out.println("A   B   C   D   E");
//		for(int y=0; y<n; y++){
//			for (int x=0; x<n; x++){
//				if(graph[x][y]==0){
//					System.out.print("__  ");
//				}
//				else{
//					System.out.print(graph[x][y] + "  ");
//				}
//			}
//			System.out.println("\n");
//		}
//	}
	}

	
}
