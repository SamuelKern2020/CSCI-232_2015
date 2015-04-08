package lab3;

import java.util.Scanner;

public class FloydsDriver {

	final static int MAX_VALUE = Integer.MAX_VALUE/2;
	
	
	public static char [] letters = {'A','B','C','D','E'};
	public static int numPoints = 5;
	public static int [][] matrix = new int [numPoints][numPoints];
	
	
	
	public static void main(String[] args) {
		
		//Define the graph:
		initialize();
		//Print out the initial graph:
		System.out.println("Adjacency Matrix:");
		printGraph();
		//Perform Floyd's algorithm to find shortest paths:
		runFloyds();

	}
	
	private static void initialize() {
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
		//Initialize distances:
		matrix[1][0] = 50;
		matrix[3][0] = 80;
		matrix[2][1] = 60;
		matrix[3][1] = 90;
		matrix[4][2] = 40;
		matrix[2][3] = 20;
		matrix[4][3] = 70;
		matrix[1][4] = 50;
		
	}
	
	private static void runFloyds() {
		Scanner in = new Scanner(System.in);
		// Compute successively better paths through vertex k.
		for (int k=0; k<numPoints;k++) {

	  		// Do so between each possible pair of points.
	  		for (int i=0; i<numPoints; i++) {
	    		for (int j=0; j<numPoints;j++) {
	    
	      			if (matrix[i][k]+matrix[k][j] < matrix[i][j]) {
	      				matrix[i][j] = matrix[i][k]+matrix[k][j];
	      				System.out.println("All-pairs shortest paths are:\n");
	      				printGraph();
	      				System.out.println("Press enter to continue:");
	      				in.nextLine();
      				}		
      			}
      		}
  		}
	}
	

	public static void printGraph() {	
		System.out.println("   A    B    C    D    E");
		System.out.println("   ======================");
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
	}
}
