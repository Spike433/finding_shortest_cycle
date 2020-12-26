package dfs;


// DFS copied from https://java2blog.com/depth-first-search-in-java/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;


import java.util.ArrayList;


class DFS{
	
		

	
public static int[] convertToInt(String dataString) {
		
		String[] twoDim=dataString.split("\\s+");
		
		int[] result=new int[twoDim.length];
		
		
		for (int i = 0; i < twoDim.length; i++) {
			result[i]=Integer.parseInt(twoDim[i]);
		}
		return result;
}
	
	
static ArrayList<Node> nodes=new ArrayList<>();
static class Node
{
	int data;
	boolean visited;

	Node(int data)
	{
		this.data=data;

	}
}

public ArrayList<Node> findNeighbours(int adjacency_matrix[][],Node x)
{
	int nodeIndex=-1;

	ArrayList<Node> neighbours=new ArrayList<>();
	for (int i = 0; i < nodes.size(); i++) {
		if(nodes.get(i).equals(x))
		{
			nodeIndex=i;
			break;
		}
	}

	if(nodeIndex!=-1)
	{
		for (int j = 0; j < adjacency_matrix[nodeIndex].length; j++) {
			if(adjacency_matrix[nodeIndex][j]==1)
			{
				neighbours.add(nodes.get(j));
			}
		}
	}
	return neighbours;
}


private static int max=0;
private static int c=0;
// Recursive DFS
public  void dfs(int adjacency_matrix[][], Node node)
{

//	System.out.print(node.data + " ");
	ArrayList<Node> neighbours=findNeighbours(adjacency_matrix,node);
    node.visited=true;
	for (int i = 0; i < neighbours.size(); i++) {
		Node n=neighbours.get(i);
		if(n!=null && !n.visited)
		{	
			
			
			dfs(adjacency_matrix,n);
		}
	}
	if(c>max) {
		max=c;
		
	}
	c=0;
}




public static void main(String arg[])
{

	
	
	Scanner inScanner=new Scanner(System.in);
	
	System.out.print("Unesite ime datoteke: ");
	
	String fileString=inScanner.nextLine();  //uncom this  inScanner.nextLine();
	
	inScanner.close();
	
	File file =new File(fileString);
	
	boolean first=false;
	
	int vertex=0;
	
	int[][] r=new int[15][15];
	
	int i=0; // rows
	
	
	
	try {
	
		Scanner fileScanner=new Scanner(file);
		while(fileScanner.hasNextLine()) {
			String data=fileScanner.nextLine();				
//			System.out.println(data);   // remove this when done
			
			if(!first) {
				first=!first;
				vertex=Integer.parseInt(data);
			}
			else {
			if(!data.equals("")) {
			int[] arr=convertToInt(data);
			r[i++]=arr;
			}
		}
			
		}
		
		fileScanner.close();
		
//		System.out.println("\nParsed array \n"); // remove this when done
//					
//		
//		for (int j = 0; j < i; j++) {
//			for (int j2 = 0; j2 < r[j].length; j2++) {
//				System.out.print(r[j][j2]+" ");
//			}
//			System.out.println();
//		}
		
		
	 System.out.println("\n"+longestCycle(r,vertex));
	
	
	} catch (FileNotFoundException e) {
		System.out.println("NO file found!");
		e.printStackTrace();
	}	
	
	
	
	 


}

private static boolean arrr[]=new boolean[16];

private static void dfss(int pos,int poc,int brojac,int [][]r ) {
	
	if(arrr[pos]==true && pos==poc && brojac>2 ) {
		if(brojac>max) {
			max=brojac;
		}
	}
	if (arrr[pos]==true ) return ;
	
	arrr[pos]=true;
	
	for (int i = 0; i < r[pos].length; i++) {
		if(r[pos][i]==1) {
			dfss(i, poc, brojac+1, r);
		}
	}
	arrr[pos]=false;
}

private static int longestCycle(int[][] r, int vertex) {

	TreeSet<Integer> set=new TreeSet<>();

	for (int i = 0; i < vertex; i++) {
		nodes.add(new Node(i));
	}
	
	


	
	for (int i = 0; i < vertex; i++) {
	
		max=0;
		
		dfss(i, i, 0, r);
		
		set.add(max);
	}
	

	
	return set.last();
}



}
