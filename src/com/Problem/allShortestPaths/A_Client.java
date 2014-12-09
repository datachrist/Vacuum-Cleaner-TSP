package com.Problem.allShortestPaths;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import javax.jws.Oneway;

public class A_Client {

	public static void main(String[] args) {
		int tVertices = 0;
		int tEdges = 0;
		int source = 0;
		int target = 0;
		
		
	//	File inFile = new File(args[0]);
	//	File inFile = new File("channel-20-3.txt");

		try {
			//reading from console
			Scanner console = new Scanner(System.in);
		    Scanner lineTokenizer2;
		    int lineNum = 0;
		    A_Graph<Integer> G;
		    Scanner lineTokenizer = new Scanner(console.nextLine());
		    tVertices=lineTokenizer.nextInt(); 
	    	 tEdges= lineTokenizer.nextInt();
	    	 source=lineTokenizer.nextInt();
	    	 target = lineTokenizer.nextInt();
	    	 Integer[] values = new Integer[tVertices];
			for (int i = 0; i < tVertices; i++) {
				values[i] = i + 1;
			}
			G = new A_Graph<Integer>(values.length, values);
		    lineTokenizer.close();
		     while (console.hasNextLine()) {
		    	 
		    	 lineTokenizer2 = new Scanner(console.nextLine());
		    	 if(lineTokenizer2.hasNextInt()){
		    		  G.addEdge(lineTokenizer2.nextInt(), lineTokenizer2.nextInt(), lineTokenizer2.nextInt());
		    	 }
		    	 else{
		    		 break;
		    	 }
		    	  lineTokenizer2.close();
		    }
			
			//reading through file
			/*Scanner sc = new Scanner(inFile);
			tVertices = sc.nextInt();
			tEdges = sc.nextInt();
			source = sc.nextInt();
			target = sc.nextInt();
			Integer[] values = new Integer[tVertices];

			for (int i = 0; i < tVertices; i++) {
				values[i] = i + 1;
			}
			Vxs135730Graph<Integer> G = new Vxs135730Graph<Integer>(values.length, values);
			while (sc.hasNext()) {
				G.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
			}*/
			//getting the source vertex
			A_Vertex<Integer> sV = G.vertices()[source];

			
			//Starting timer!!
			long start = System.currentTimeMillis();
			// inialization
			sV.setD(0);
			sV.n=1;
			A_Queue<A_Vertex<Integer>> queue = new A_Queue<A_Vertex<Integer>>();
			queue.enqueue(sV);
			boolean zero=false;
			boolean negativecycle=false;
			int weightSum= 0;
			int weightPrev=0;
			while (!queue.isEmpty()) {
			A_Vertex<Integer> ver = queue.dequeue();
				for (A_Edge<A_Vertex<Integer>> e:ver.adjacencyList() ) {
					//System.out.println(ver+" " +ver.getD());
					if((ver.getD()+e.weight()-e.to().getD()==0)){
						weightPrev=weightSum;
						weightSum+=e.weight();
					}
				}
				//checking for cycle
				if(ver.count>0){
					if(weightSum-weightPrev==0 ){
						zero=true;
						System.out.println("Non-positive cycle in graph.  DAC is not applicable");
						break;
					}
					for (A_Edge<A_Vertex<Integer>> e:ver.adjacencyList() ) {
						e.to().visited=false;
						
					}
				
				}
				//checking for negative cycle
				if (ver.count++ >= G.vertices().length) {
					negativecycle=true;
					System.out.println("Negative cycle , DAC is not applicable");
					break;
				} else {
					for (A_Edge<A_Vertex<Integer>> edge : ver.adjacencyList()) {
						boolean flag = true;
						boolean ischanged=G.relax(ver.getValue(), edge.to().getValue());
						Iterator<A_Vertex<Integer>> itr = queue.iterator();
						if (itr.hasNext()) {
							if (itr.next().equals(edge.to())) {
								flag = false;
							}
						}
						if (flag && ischanged ) {
							queue.enqueue(edge.to());

						}

					}
				}
		}


			
			//Priniting the output
			
			long last = System.currentTimeMillis();
			long time=last-start;
			if( !zero && !negativecycle){
			if (G.vertices().length <= 101 ) {
				System.out.println(G.vertices()[target].getD() + " " + G.vertices()[target].getN()+" "+time);
				for (int count = 1; count < G.vertices().length; count++) {

					System.out.println(G.vertices()[count].getValue() + " "
							+ (G.vertices()[count].getD() == Long.MAX_VALUE ? "INF" : G.vertices()[count].getD())
							+ " " + (G.vertices()[count].getPred() == null ? "-" : G.vertices()[count].getPred()) + " "
							+ G.vertices()[count].getN());
				}
			}
			else{
				System.out.println(G.vertices()[target].getD() + " " + G.vertices()[target].getN()+" "+time);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	

}