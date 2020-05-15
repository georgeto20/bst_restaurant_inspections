package proj1;

import java.util.Date;

/**
 * This class represents a binary
 * search tree of records, which
 * are sorted by inspection date.
 * The class contains methods to get
 * a DateList with a specific
 * inspection date, add a record
 * to the necessary node in the tree, and
 * to remove a DateList from the tree.
 * 
 * @author George Tolkachev
 * @version 04/27/16
 */
public class bstOfRecordsByDate {

	// The root node of the tree
	private BSTNodeByDate root;
	// The list to be removed (used in the remove method)
	private DateList listToRemove;
		
	/**
	 * A default (no-arg) constructor that
	 * sets both data fields to their
	 * initial values of null.
	 */
	public bstOfRecordsByDate() {
		root = null;
		listToRemove = null;
	}
		
	/**
	 * Gets the DateList containing the
	 * records with the specified inspection
	 * date (or null if the tree does not have
	 * a DateList with this inspection date).
	 * This is a helper method for the
	 * get method that performs the
	 * actual recursive algorithm of
	 * getting the DateList.
	 * 
	 * @param date the inspection date to be searched for
	 * @return a reference to the DateList containing
	 * 		   the records with the specified inspection date
	 * 		   (or null if no such inspection date is found
	 * 		   within the tree)
	 */
	public DateList get(Date date) {
		return get(date, root);
	}
		
	/**
	 * Gets the DateList containing the records
	 * with the specified inspection date.
	 * 
	 * @param date the inspection date to be searched for
	 * @param n the node whose inspection date is compared
	 * 			with the specified inspection date
	 * @return a reference to the DateList containing
	 * 		   the records with the specified inspetion date
	 */
	private DateList get(Date date, BSTNodeByDate n) {
		// If the node points to null, return null
		if (n == null) {
			return null;
		}
		// If the specified date is earlier than
		// the date of the node, recursively
		// call the method with the same date
		// and the left child of the node
		else if (date.compareTo(n.getList().getDate()) < 0) {
			return get(date, n.getLeft());
		}
		// If the specified date is later than
		//the date of the node, recursively
		// call the method with the same date
		//and the right child of the node
		else if (date.compareTo(n.getList().getDate()) > 0) {
			return get(date, n.getRight());
		}
		// Otherwise, if the specified date is the same as
		// the date of the node, return the DateList of the node
		else {
			return n.getList();
		}
	}
		
	/**
	 * Adds the given record to the node
	 * with the DateList containing records
	 * with the same inspection date as the date of the
	 * given record. If the date of the given
	 * record is not found within the tree,
	 * add a new node to the tree and add
	 * the given record to the DateList
	 * of this new node. This is a helper
	 * method for the add method that performs
	 * the actual recursive algorithm of
	 * adding the record to the tree.
	 * 
	 * @param r the record to be added to the tree
	 */
	public void add(Record r) {
		root = add(r, root);
	}
		
	/**
	 * Adds the given record to the tree.
	 * 
	 * @param r the record to be added to the tree
	 * @param n the node whose inspection date is compared
	 * 			with that of the given record
	 * @return a reference to the given node
	 */
	private BSTNodeByDate add(Record r, BSTNodeByDate n) {
		// If the node points to null, create a new
		// DateList that contains the given record
		// and create a new node with this DateList
		if (n == null) {
			DateList list = new DateList(r);
			n = new BSTNodeByDate(list);
		}
		// If the inspection date of the given record is
		// earlier than that of the node, recursively
		// call the method with the same record and the left
		// child of the node
		else if (r.getInspectionDate().compareTo(n.getList().getDate()) < 0) {
			n.setLeft(add(r, n.getLeft()));
		}
		// If the inspection date of the given record is
		// later than that of the node, recursively
		// call the method with the same record and the right
		// child of the node
		else if (r.getInspectionDate().compareTo(n.getList().getDate()) > 0) {
			n.setRight(add(r, n.getRight()));
		}
		// Otherwise, if the inspection date of the given record is
		// record is the same as that of the node,
		// add the record to the DateList of the node
		else {
			n.getList().add(r);
		}
		return n;
	}
		
	/**
	 * Removes the DateList containing
	 * records with the specified date.
	 * This is the first helper method for the
	 * remove method that performs the actual
	 * recursive algorithm of removing a
	 * node from the tree.
	 * 
	 * @param date the date associated with the
	 * 		      records of the DateList to be removed
	 * @return a reference to the removed DateList
	 * 		   (or null if the specified date is not
	 * 		   found within the tree)
	 */
	public DateList remove(Date date) {
		root = remove(date, root);
		return listToRemove;
	}
		
	/**
	 * Removes the DateList containing
	 * records with the specified date.
	 * This is the second helper method for the
	 * remove method that performs the actual
	 * recursive algorithm of removing a
	 * node from the tree.
	 * 
	 * @param date the date associated with the
	 * 			  record of the DateList to be removed
	 * @param n the node whose date is compared
	 * 			with the date of the given record
	 * @return a reference to the removed DateList
	 * 		   (or null if the specified date is not
	 * 		   found within the tree)
	 */
	private BSTNodeByDate remove(Date date, BSTNodeByDate n) {
		// If the node points to null, do nothing
		if (n == null) {}
		// If the date of the given record is earlier
		// than the date of the node, set the left child
		// of the node to the result of a recursive call to the
		// method with the same date and the left child of the node
		else if (date.compareTo(n.getList().getDate()) < 0) {
			n.setLeft(remove(date, n.getLeft()));
		}
		// If the date of the given record is later
		// than the date of the node, set the right child
		// of the node to the result of a recursive call to the
		// method with the same date and the right child of the node
		else if (date.compareTo(n.getList().getDate()) > 0) {
			n.setRight(remove(date, n.getRight()));
		}
		// Otherwise, if the date of the given record is
		// the same as the date of the node:
		else {
			// If the list to be removed data field is null,
			// set it equal to the list of the current node
			if (listToRemove == null) {
				listToRemove = n.getList();
			}
			// Set the node equal to the result of a call
			// to the remove method that performs the actual
			// recursive algorithm of removing a node from the tree
			n = remove(n);
		}
		return n;
	}
		
	/**
	 * Removes a node from the tree.
	 * 
	 * @param n the node to be removed
	 * @return a reference to the node removed
	 */
	private BSTNodeByDate remove(BSTNodeByDate n) {
		// If the left child is null, return 
		// the right child
		if (n.getLeft() == null) {
			return n.getRight();
		}
		// If the right child is null, return
		// the left child
		if (n.getRight() == null) {
			return n.getLeft();
		}
		// Otherwise, the node has two children
			
		// Set the DateList of the node to the
		// DateList of the predecessor node
		DateList list = getPredecessor(n);
		n.setList(list);
		// Set the left child of the node to the result
		// of calling the second helper method with the
		// Date of the node and the left child of the node
		n.setLeft(remove(list.getDate(), n.getLeft()));
		return n;
	}
		
	/**
	 * Finds the predecessor of the current node
	 * (i.e. the node whose DateList contains records
	 * with the date that directly precedes the date
	 * of the given node).
	 * 
	 * @param n the node whose predecessor must be found
	 * @return a reference to the DateList of the predecessor
	 */
	private DateList getPredecessor(BSTNodeByDate n) {
		// Begin with the left child of the given node
		BSTNodeByDate current = n.getLeft();
		// While possible, move along from the
		// left child to the right until the
		// predecessor is found
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current.getList();
	}
}