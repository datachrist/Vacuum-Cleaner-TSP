package com.Problem.VacuumCleanerProblem;


/**
 * This class provides an abstract data type for an Edge between any two
 * vertices of any type.
 * 
 * 
 *
 * @param <T>
 *            Type of objects between which there is an edge.
 */
public class V_Edge<T> {
	/**
	 * This holds the source of this edge.
	 */
	private T u;
	
	/**
	 * This holds the destination of this edge.
	 */
	private T v;
	
	/**
	 * This holds the weight of the edge.
	 */
	private int w;
	
	private char type;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((u == null) ? 0 : u.hashCode());
		result = prime * result + ((v == null) ? 0 : v.hashCode());
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
		V_Edge other = (V_Edge) obj;
		if (u == null) {
			if (other.u != null)
				return false;
		} else if (!u.equals(other.u))
			return false;
		if (v == null) {
			if (other.v != null)
				return false;
		} else if (!v.equals(other.v))
			return false;
		return true;
	}

	/**
	 * This constructor initializes an Edge.
	 * @param from
	 * @param to
	 * @param w
	 */
	public V_Edge(T from, T to, int w,char type) {
		this.u = from;
		this.v = to;
		this.w = w;
		this.type= type;
	}

	/**
	 * This method returns the start object of the edge.
	 * @return
	 */
	public T from() {
		return u;
	}

	/**
	 * This method returns the end object of the edge.
	 * @return
	 */
	public T to() {
		return v;
	}

	/**
	 * This method returns the weight of the edge.
	 * @return
	 */
	public int weight() {
		return w;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(char type) {
		this.type = type;
	}

}
