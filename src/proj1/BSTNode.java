package proj1;

/**
 * This class represents a node of
 * a binary search tree. Each node has
 * a specific DbaList associated with it,
 * as well as links to its left and
 * right children.
 * 
 * @author George Tolkachev
 * @version 04/27/16
 */
public class BSTNode implements Comparable<BSTNode>{
	
	// The DbaList associated with the node
	private DbaList list;
	// The reference to the left child
	private BSTNode left;
	// The reference to the right child
	private BSTNode right;
	
	/**
	 * A constructor that sets the list
	 * data field to the specified list.
	 * 
	 * @param list the list to which the list data
	 * 			   field must be initialized
	 */
	public BSTNode(DbaList list) {
		this.list = list;
	}
	
	/**
	 * A constructor that sets the list,
	 * left, and right data fields to the
	 * specified values.
	 * 
	 * @param list the list to which the list data
	 * 			   field must be initialized
	 * @param left the new left child of the node
	 * @param right the new right child of the node
	 */
	public BSTNode(DbaList list, BSTNode left, BSTNode right) {
		this.list = list;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Gets the DbaList of the node.
	 * 
	 * @return the DbaList
	 */
	public DbaList getList() {
		return list;
	}

	/**
	 * Sets the list data field to the specified DbaList.
	 * 
	 * @param list the list to which the list data
	 * 			   field must be initialized
	 */
	public void setList(DbaList list) {
		this.list = list;
	}
	
	/**
	 * Gets the left child of the node.
	 * 
	 * @return the left child
	 */
	public BSTNode getLeft() {
		return left;
	}
	
	/**
	 * Sets the left child of the node to a new node.
	 * 
	 * @param left the new left child of the node
	 */
	public void setLeft(BSTNode left) {
		this.left = left;
	}
	
	/**
	 * Gets the right child of the node.
	 * 
	 * @return the right child
	 */
	public BSTNode getRight() {
		return right;
	}
	
	/**
	 * Sets the right child of the node to a new node.
	 * 
	 * @param right the new right child of the node
	 */
	public void setRight(BSTNode right) {
		this.right = right;
	}
	
	/**
	 * Compares the DbaList contained within this node
	 * with that contained in another node; returns a
	 * positive value, 0, or a negative value based on
	 * the same rules as the compareTo method in the
	 * DbaList class.
	 * 
	 * @return an integer value based on the lexicographic
	 * 		   comparison of the DBAs of the two DbaLists
	 */
	public int compareTo(BSTNode other) {
		return this.list.compareTo(other.list);
	}
}