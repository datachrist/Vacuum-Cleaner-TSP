package com.Problem.allShortestPaths;

/**
 * This class provides an abstract data type for a vertex.
 * 
 *
 *
 * @param <T>
 *            Type of data that vertex is holding.
 */
public class A_Vertex<T> {
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
		result = prime * result + ((pred == null) ? 0 : pred.hashCode());
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
		A_Vertex other = (A_Vertex) obj;
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
		if (pred == null) {
			if (other.pred != null)
				return false;
		} else if (!pred.equals(other.pred))
			return false;
		if (visited != other.visited)
			return false;
		return true;
	}

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
	private A_Vertex<T> pred;
	/**
	 * This holds the list of edges that starts from this vertex.
	 */
	private A_Bag<A_Edge<A_Vertex<T>>> adj;

	/**
	 * This constructor initializes the vertex with a value.
	 * 
	 * @param value
	 */
	public A_Vertex(T value) {
		this.obj = value;
		this.adj = new A_Bag<A_Edge<A_Vertex<T>>>();
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
	public A_Bag<A_Edge<A_Vertex<T>>> adjacencyList() {
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
	 * @return the pred
	 */
	public A_Vertex<T> getPred() {
		return pred;
	}

	/**
	 * @param pred the pred to set
	 */
	public void setPred(A_Vertex<T> pred) {
		this.pred = pred;
	}

}
