package proj1;

/**
 * This class represents a node of
 * a binary search tree. Each node has
 * a specific DateList associated with it,
 * as well as links to its left and
 * right children.
 * 
 * @author George Tolkachev
 * @version 05/03/16
 */
public class BSTNodeByDate {

	// The DateList associated with the node
	private DateList list;
	// The reference to the left child
	private BSTNodeByDate left;
	// The reference to the right child
	private BSTNodeByDate right;
		
	/**
	 * A constructor that sets the list
	 * data field to the specified list.
	 * 
	 * @param list the list to which the list data
	 * 			   field must be initialized
	 */
	public BSTNodeByDate(DateList list) {
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
	public BSTNodeByDate(DateList list, BSTNodeByDate left, BSTNodeByDate right) {
		this.list = list;
		this.left = left;
		this.right = right;
	}
		
	/**
	 * Gets the DateList of the node.
	 * 
	 * @return the DateList
	 */
	public DateList getList() {
		return list;
	}

	/**
	 * Sets the list data field to the specified DateList.
	 * 
	 * @param list the list to which the list data
	 * 			   field must be initialized
	 */
	public void setList(DateList list) {
		this.list = list;
	}
		
	/**
	 * Gets the left child of the node.
	 * 
	 * @return the left child
	 */
	public BSTNodeByDate getLeft() {
		return left;
	}
		
	/**
	 * Sets the left child of the node to a new node.
	 * 
	 * @param left the new left child of the node
	 */
	public void setLeft(BSTNodeByDate left) {
		this.left = left;
	}
		
	/**
	 * Gets the right child of the node.
	 * 
	 * @return the right child
	 */
	public BSTNodeByDate getRight() {
		return right;
	}
		
	/**
	 * Sets the right child of the node to a new node.
	 * 
	 * @param right the new right child of the node
	 */
	public void setRight(BSTNodeByDate right) {
		this.right = right;
	}
		
	/**
	 * Compares the DateList contained within this node
	 * with that contained in another node; returns a
	 * positive value, 0, or a negative value based on
	 * the same rules as the compareTo method in the
	 * DateList class.
	 * 
	 * @return an integer value based on a
	 * 		   comparison of the dates of the two DateLists
	 */
	public int compareTo(BSTNodeByDate other) {
		return this.list.compareTo(other.list);
	}
}