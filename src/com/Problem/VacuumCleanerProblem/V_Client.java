package com.Problem.VacuumCleanerProblem;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;






public class V_Client {

	private static final long INFINITY = Long.MAX_VALUE;
	private static V_Vertex<Integer> sV ;
	public  static ArrayList<ArrayList<V_Vertex<Integer>>> shortestPathlist;
	
	public static void main(String[] args) {
		
		int tVertices = 0;
		int tEdges = 0;
		int source = 0;	


		try {

			//reading from console
			Scanner console = new Scanner(System.in);
		    Scanner lineTokenizer2;
		    int lineNum = 0;
		    V_Graph<Integer> G;
		    Scanner lineTokenizer = new Scanner(console.nextLine());
		    tVertices=lineTokenizer.nextInt(); 
	    	 tEdges= lineTokenizer.nextInt();
	    	 source=lineTokenizer.nextInt();
	    	
	    	 Integer[] values = new Integer[tVertices];
			for (int i = 0; i < tVertices; i++) {
				values[i] = i + 1;
			}
			G = new V_Graph<Integer>(values.length, values);
		    lineTokenizer.close();
		    
		    Scanner lineTokenizer3=null;
		    
		    for (int i = 0; i < tVertices; i++) {
		    	if((i)%30==0){
		    		lineTokenizer3 = new Scanner(console.nextLine());
		    	}
				G.vertices()[i+1].setReward(lineTokenizer3.nextInt());
			}
		    lineTokenizer3.close();
		    
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
			
			sV = G.vertices()[source];
			V_IndexMinPQ<Long> pq = new V_IndexMinPQ<Long>(G.vertices().length);
			
			
			
			long start = System.currentTimeMillis();
			
			// inialization
			V_Vertex<Integer>[] vertices = G.vertices();
			for (int i = 1; i < vertices.length; i++) {
				vertices[i].setD(INFINITY);
				vertices[i].setPredList(null);
				// pq.enqueue(vertices[i]);
				pq.insert(i, vertices[i].getD());
			}
			// setting source distance zero
			sV.setD(0);
			
			// main
			while (!pq.isEmpty()) {

				// delete vertex of min dist from pq
				V_Vertex<Integer> from = vertices[pq.delMin()];

				for (V_Edge<V_Vertex<Integer>> edge : from.adjacencyList()) {
					if (edge.getType() == 'o') {
						V_Vertex<Integer> to = edge.to();

						if (to.getD() > from.getD() + edge.weight()) {
							to.setD(from.getD() + edge.weight());
							pq.decreaseKey(to.getValue(), to.getD());
							List<V_Vertex<Integer>> predList = to.getPredList();
							if(predList==null)
								predList=new ArrayList<V_Vertex<Integer>>();
							predList.add(from);
							to.setPredList(predList);
							to.visited = true;							

						} else if (to.getD() == (from.getD() + edge.weight()) && to.visited == true) {
							to.n += from.n;											
							List<V_Vertex<Integer>> predList = to.getPredList();
							predList.add(from);
							to.setPredList(predList);

						}

					}
				}
			}
			for (int i = 1; i < G.vertices().length; i++)
				G.vertices()[i].visited = false;

			
			
			
			// collecting all shortest path adding into list of shortest path.
						
			
			shortestPathlist = new ArrayList<ArrayList<V_Vertex<Integer>>>();
			ArrayList<V_Vertex<Integer>> path = null;

			
			
			//recursion 
			
			for(V_Edge<V_Vertex<Integer>> edge: sV.adjacencyList()){
				path = new ArrayList<V_Vertex<Integer>>();
				V_Vertex<Integer> v = edge.to();
				
				path.add(sV);
				dfs(v, sV,path);
				
			}
			
			//finding maxreward list from list
			ArrayList<Double> rewardList = new ArrayList<Double>();
			//checking for max reward list
			List<ArrayList<Double>> correctRewards = new ArrayList<ArrayList<Double>>(shortestPathlist.size());
			for(ArrayList<V_Vertex<Integer>> shortestPath: shortestPathlist){
				ArrayList<Double> eachReward = new ArrayList<Double>(shortestPath.size());
				double reward =0.0;
				for(int i=0;i<shortestPath.size();i++){
					
					if(shortestPath.get(i).getPredList()==null&&(shortestPath.get(i).equals(sV))){
							reward+=shortestPath.get(i).getReward();
							eachReward.add(shortestPath.get(i).getReward());
					}
					else if(shortestPath.get(i).getPredList()!=null&&(shortestPath.get(i).getPredList()).contains(shortestPath.get(i-1))){
					reward+=shortestPath.get(i).getReward();
					eachReward.add(shortestPath.get(i).getReward());
					}
					else{
						eachReward.add(0.0);
					}
				}
				rewardList.add(reward);
				correctRewards.add(eachReward);
			}
			
			Double maxReward = Collections.max(rewardList);
			int indx = rewardList.indexOf(maxReward);
			
			//System.out.println("got this indrx"+shortestPathlist.get(indx));
			
			
			
			//printing output
			long last = System.currentTimeMillis();
			
			System.out.println(maxReward.longValue()+" "+(last-start));
			ArrayList<V_Vertex<Integer>> maxRewardPath = shortestPathlist.get(indx);
			maxRewardPath.add(sV);
			ArrayList<Double> maxRewardList = correctRewards.get(indx);
			maxRewardList.add(0.0);
			
			for(int i=0;i<maxRewardPath.size();i++){
				System.out.println(maxRewardPath.get(i) + "  " + maxRewardList.get(i).longValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * dfs for finding all possible shortest path from source to source
	 * 
	 * */
	@SuppressWarnings("unchecked")
	public static void dfs(V_Vertex<Integer> u,V_Vertex<Integer> destVertex,ArrayList<V_Vertex<Integer>> currentPath){
		
		if(u.equals(destVertex)){
			if(currentPath.size()>2)
			shortestPathlist.add((ArrayList<V_Vertex<Integer>>) currentPath.clone());
			return;
		}
		else{
			u.visited=true;
			currentPath.add(u);
			
			for(V_Edge<V_Vertex<Integer>> edge: u.adjacencyList()){
				V_Vertex<Integer> v= edge.to();
				if(!v.visited){
					dfs(v, destVertex, currentPath);
				}
			}			
			
			u.visited=false;
			currentPath.remove(u);
		}
		
		
		
	}
}