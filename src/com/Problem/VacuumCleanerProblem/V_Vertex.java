package com.Problem.VacuumCleanerProblem;


import java.util.List;

/**
 * This class provides an abstract data type for a vertex.
 * 
 *
 * @param <T>
 *            Type of data that vertex is holding.
 */
public class V_Vertex<T> {
	
	
	/**
	 * This holds the data that vertex is storing.
	 */
	private T obj;
	
	// shortest weight
	private long d;
	//check if there is negative cycle
	public int count;
	//total no of shortest path
	public long n;
//to check the vertex is visited or not
	public boolean visited;
	// predessor vertex for shortest path of max reward
	private List<V_Vertex<Integer>> predList;
	
	// reward
	private double reward;
	/**
	 * This holds the list of edges that starts from this vertex.
	 */
	private V_Bag<V_Edge<V_Vertex<T>>> adj;

	/**
	 * This constructor initializes the vertex with a value.
	 * 
	 * @param value
	 */
	public V_Vertex(T value) {
		this.obj = value;
		this.adj = new V_Bag<V_Edge<V_Vertex<T>>>();
	}

	/**
	 * This method returns the value the vertex is holding.
	 * 
	 * @return
	 */
	public T getValue() {
		return obj;
	}

	/**
	 * This method returns the list of edges which have this vertex has starting
	 * point.
	 * 
	 * @return
	 */
	public V_Bag<V_Edge<V_Vertex<T>>> adjacencyList() {
		return this.adj;
	}

	/**
	 * This method returns the string interpretation of the valur stored in the
	 * vertex.
	 */
	public String toString() {
		return obj.toString();
	}

	/**
	 * @return the d
	 */
	public long getD() {
		return d;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(long d) {
		this.d = d;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the n
	 */
	public long getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 */
	public void setN(long n) {
		this.n = n;
	}

	
	/**
	 * @return the reward
	 */
	public double getReward() {
		return reward;
	}

	/**
	 * @param reward the reward to set
	 */
	public void setReward(double reward) {
		this.reward = reward;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected V_Vertex<T> clone() throws CloneNotSupportedException {
		V_Vertex<T> cloneVertex = new V_Vertex<T>(this.getValue());
		cloneVertex.adj=(V_Bag<V_Edge<V_Vertex<T>>>) this.adj.clone();
		cloneVertex.count=this.count;
		cloneVertex.d=this.d;
		cloneVertex.count=this.count;
		cloneVertex.n=this.n;
		cloneVertex.obj=this.obj;
		cloneVertex.predList=this.predList;
		cloneVertex.reward=this.reward;
		cloneVertex.visited=this.visited;
		return cloneVertex;
	}

	/**
	 * @return the predList
	 */
	public List<V_Vertex<Integer>> getPredList() {
		return predList;
	}

	/**
	 * @param predList the predList to set
	 */
	public void setPredList(List<V_Vertex<Integer>> predList) {
		this.predList = predList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adj == null) ? 0 : adj.hashCode());
		result = prime * result + count;
		result = prime * result + (int) (d ^ (d >>> 32));
		result = prime * result + (int) (n ^ (n >>> 32));
		result = prime * result + ((obj == null) ? 0 : obj.hashCode());
		result = prime * result + ((predList == null) ? 0 : predList.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reward);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (visited ? 1231 : 1237);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		V_Vertex other = (V_Vertex) obj;
		if (adj == null) {
			if (other.adj != null)
				return false;
		} else if (!adj.equals(other.adj))
			return false;
		if (count != other.count)
			return false;
		if (d != other.d)
			return false;
		if (n != other.n)
			return false;
		if (this.obj == null) {
			if (other.obj != null)
				return false;
		} else if (!this.obj.equals(other.obj))
			return false;
		if (predList == null) {
			if (other.predList != null)
				return false;
		} else if (!predList.equals(other.predList))
			return false;
		if (Double.doubleToLongBits(reward) != Double.doubleToLongBits(other.reward))
			return false;
		if (visited != other.visited)
			return false;
		return true;
	}

}
