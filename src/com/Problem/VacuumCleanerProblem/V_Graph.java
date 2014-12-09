package com.Problem.VacuumCleanerProblem;



/**
 * This class provides an abstract data type for a Graph.
 * 
 * 
 *
 * @param <T>
 *            Type of data that vertices in the graph will be holding.
 */
public class V_Graph<T> {
	private final long INFINITY = Long.MAX_VALUE;
	/**
	 * This holds the number of vertex in the graph.
	 */
	private final int V;
	/**
	 * This holds the number of edges in the graph.
	 */
	private int E;
	/**
	 * This holds array of Vertices of type T.
	 */
	private V_Vertex<T>[] vertices;

	private int[][] adjMatrix;

	/**
	 * This initializes the graph with V vertices and values.
	 * 
	 * @param V
	 * @param values
	 */
	@SuppressWarnings("unchecked")
	public V_Graph(int V, T[] values) {
		this.V = V;
		// Array is 1 based indexing.
		this.vertices = new V_Vertex[V + 1];
		this.adjMatrix = new int[V + 1][V + 1];
		for (int i = 1, j = 0; i < V + 1; i++, j++) {
			this.vertices[i] = new V_Vertex<T>(values[j]);
		}
	/*		for (int k = 1; k < V + 1; k++) {
				vertices[k].setD(INFINITY);
				vertices[k].setPredList(null);
				vertices[k].setCount(0);
				vertices[k].setN(0);
				vertices[k].visited=false;
		}*/
	}

	/**
	 * This method adds an edge between two vertices in a graph.
	 * 
	 * @param u
	 * @param v
	 * @param w
	 */
	public void addEdge(int u, int v, int w) {
		if (u <= 0 || u > V)
			throw new IndexOutOfBoundsException("Illeagal vertex numeber.");
		if (v <= 0 || v > V)
			throw new IndexOutOfBoundsException("Illeagal vertex numeber.");
		V_Vertex<T> from = this.vertices[u];
		V_Vertex<T> to = this.vertices[v];
	/*	boolean toFlag=false,fromFlag=false;
		for (Edge<Vertex<T>> edge : from.adjacencyList()){
			if(edge.equals(new Edge<Vertex<T>>(from, to, w,''))){
				if(edge.weight()>w)
					edge.setW(w);
				fromFlag=true;
			}
			
		}
		if(!fromFlag)*/
			from.adjacencyList().add(new V_Edge<V_Vertex<T>>(from, to, w,'o'));
			to.adjacencyList().add(new V_Edge<V_Vertex<T>>(to, from, w,'i'));
		adjMatrix[u][v] = w;
		E++;
	}

	/*public boolean relax(int u, int v){
		Vertex<T> from = this.vertices[u];
		Vertex<T> to = this.vertices[v];
		boolean flag=false;
		for (Edge<Vertex<T>> edge : from.adjacencyList()) {
			if (to.equals(edge.to())){
				if(to.getD()>from.getD()+edge.weight()){
					to.setD(from.getD()+edge.weight());
					to.setPredList(from);
					to.visited=true;
					to.n=to.getPred().n;
					flag=true;
				}
				else if (to.getD()==(from.getD()+edge.weight()) && to.visited==true ) {
					to.n+=from.n;
					to.setPredList(from);
					//to.visited=false;
					flag=false;
					
				}
			}
				
		}
		
		return flag;
	}*/
	
	/**
	 * This method returns the number of vertices in the graph.
	 * 
	 * @return
	 */
	public int noOfVertices() {
		return V;
	}

	/**
	 * This method returns the array of vertices.
	 * 
	 * @return
	 */
	public V_Vertex<T>[] vertices() {
		return this.vertices;
	}

	/**
	 * This method returns the number of edges in the graph.
	 * 
	 * @return
	 */
	public int noOfEdges() {
		return E;
	}

	/**
	 * This method prints the graph in the form of adjacency lists with edges in
	 * the brackets.
	 */
	public void printAllAdjacencyLists() {
		for (int i = 1; i < V + 1; i++) {
			System.out.print(vertices[i].getValue().toString() + " -> ");
			for (V_Edge<V_Vertex<T>> edge : vertices[i].adjacencyList()) {
				System.out.print(edge.to().toString() + "(" + edge.weight()
						+ ") ");
			}
			System.out.println();
		}
	}

	/**
	 * This method prints the graphs in the form of adjacency matrix.
	 */
	public void printAdjacencyMatrix() {
		for (int i = 1; i < V + 1; i++) {
			for (int j = 1; j < V + 1; j++) {
				if (this.adjMatrix[i][j] != INFINITY)
					System.out.format(" %3s ", this.adjMatrix[i][j]);
				else
					System.out.format(" %3s ", "~");
			}
			System.out.println();
		}
	}

	/**
	 * This method returns if an edge is present between two vertices or not.
	 * 
	 * @param u
	 * @param v
	 * @return
	 */
	public boolean edgePresent(int u, int v) {
		V_Vertex<T> from = this.vertices[u];
		V_Vertex<T> to = this.vertices[v];
		for (V_Edge<V_Vertex<T>> edge : from.adjacencyList()) {
			if (to.equals(edge.to()))
				return true;
		}
		return false;
	}
}
