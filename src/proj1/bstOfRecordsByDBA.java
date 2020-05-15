package proj1;

/**
 * This class represents a binary
 * search tree of records, which
 * are sorted by DBA. The class
 * contains methods to get a DbaList
 * with a specific DBA, add a record
 * to the necessary node in the tree, and
 * to remove a DbaList from the tree.
 * 
 * @author George Tolkachev
 * @version 04/27/16
 */
public class bstOfRecordsByDBA {

	// The root node of the tree
	private BSTNode root;
	// The list to be removed (used in the remove method)
	private DbaList listToRemove;
	
	/**
	 * A default (no-arg) constructor that
	 * sets both data fields to their
	 * initial values of null.
	 */
	public bstOfRecordsByDBA() {
		root = null;
		listToRemove = null;
	}
	
	/**
	 * Gets the DbaList containing the
	 * records with the specified DBA
	 * (or null if the tree does not have
	 * a DbaList with this DBA).
	 * This is a helper method for the
	 * get method that performs the
	 * actual recursive algorithm of
	 * getting the DbaList.
	 * 
	 * @param dba the DBA to be searched for
	 * @return a reference to the DbaList containing
	 * 		   the records with the specified DBA
	 * 		   (or null if no such DBA is found
	 * 		   within the tree)
	 */
	public DbaList get(String dba) {
		return get(dba, root);
	}
	
	/**
	 * Gets the DbaList containing the
	 * records with the specified DBA.
	 * 
	 * @param dba the DBA to be searched for
	 * @param n the node whose DBA is compared
	 * 			with the specified DBA
	 * @return a reference to the DbaList containing
	 * 		   the records with the specified DBA
	 */
	private DbaList get(String dba, BSTNode n) {
		// If the node points to null, return null
		if (n == null) {
			return null;
		}
		// If the specified DBA is lexicographically
		// smaller than the DBA of the node, recursively
		// call the method with the same DBA and the left
		// child of the node
		else if (dba.compareToIgnoreCase(n.getList().getDba()) < 0) {
			return get(dba, n.getLeft());
		}
		// If the specified DBA is lexicographically
		// greater than the DBA of the node, recursively
		// call the method with the same DBA and the right
		// child of the node
		else if (dba.compareToIgnoreCase(n.getList().getDba()) > 0) {
			return get(dba, n.getRight());
		}
		// Otherwise, if the specified DBA is lexicographically
		// equal to the DBA of the node, return the DbaList of the node
		else {
			return n.getList();
		}
	}
	
	/**
	 * Adds the given record to the node
	 * with the DbaList containing records
	 * with the same DBA as the DBA of the
	 * given record. If the DBA of the given
	 * record is not found within the tree,
	 * add a new node to the tree and add
	 * the given record to the DbaList
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
	 * @param n the node whose DBA is compared
	 * 			with the DBA of the given record
	 * @return a reference to the given node
	 */
	private BSTNode add(Record r, BSTNode n) {
		// If the node points to null, create a new
		// DbaList that contains the given record
		// and create a new node with this DbaList
		if (n == null) {
			DbaList list = new DbaList(r);
			n = new BSTNode(list);
		}
		// If the DBA of the given record is lexicographically
		// smaller than the DBA of the node, recursively
		// call the method with the same record and the left
		// child of the node
		else if (r.getDba().compareToIgnoreCase(n.getList().getDba()) < 0) {
			n.setLeft(add(r, n.getLeft()));
		}
		// If the DBA of the given record is lexicographically
		// greater than the DBA of the node, recursively
		// call the method with the same record and the right
		// child of the node
		else if (r.getDba().compareToIgnoreCase(n.getList().getDba()) > 0) {
			n.setRight(add(r, n.getRight()));
		}
		// Otherwise, if the DBA of the given record is
		// lexicographically equal to the DBA of the node,
		// add the record to the DbaList of the node
		else {
			n.getList().add(r);
		}
		return n;
	}
	
	/**
	 * Removes the DbaList containing
	 * records with the specified DBA.
	 * This is the first helper method for the
	 * remove method that performs the actual
	 * recursive algorithm of removing a
	 * node from the tree.
	 * 
	 * @param dba the DBA associated with the
	 * 		      records of the DbaList to be removed
	 * @return a reference to the removed DbaList
	 * 		   (or null if the specified DBA is not
	 * 		   found within the tree)
	 */
	public DbaList remove(String dba) {
		root = remove(dba, root);
		return listToRemove;
	}
	
	/**
	 * Removes the DbaList containing
	 * records with the specified DBA.
	 * This is the second helper method for the
	 * remove method that performs the actual
	 * recursive algorithm of removing a
	 * node from the tree.
	 * 
	 * @param dba the DBA associated with the
	 * 			  record of the DbaList to be removed
	 * @param n the node whose DBA is compared
	 * 			with the DBA of the given record
	 * @return a reference to the removed DbaList
	 * 		   (or null if the specified DBA is not
	 * 		   found within the tree)
	 */
	private BSTNode remove(String dba, BSTNode n) {
		// If the node points to null, do nothing
		if (n == null) {}
		// If the DBA of the given record is lexicographically
		// smaller than the DBA of the node, set the left child
		// of the node to the result of a recursive call to the
		// method with the same DBA and the left child of the node
		else if (dba.compareToIgnoreCase(n.getList().getDba()) < 0) {
			n.setLeft(remove(dba, n.getLeft()));
		}
		// If the DBA of the given record is lexicographically
		// greater than the DBA of the node, set the right child
		// of the node to the result of a recursive call to the
		// method with the same DBA and the right child of the node
		else if (dba.compareToIgnoreCase(n.getList().getDba()) > 0) {
			n.setRight(remove(dba, n.getRight()));
		}
		// Otherwise, if the DBA of the given record is
		// lexicographically equal to the DBA of the node:
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
	private BSTNode remove(BSTNode n) {
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
		
		// Set the DbaList of the node to the
		// DbaList of the predecessor node
		DbaList list = getPredecessor(n);
		n.setList(list);
		// Set the left child of the node to the result
		// of calling the second helper method with the
		// DBA of the node and the left child of the node
		n.setLeft(remove(list.getDba(), n.getLeft()));
		return n;
	}
	
	/**
	 * Finds the predecessor of the current node
	 * (i.e. the node whose DbaList contains records
	 * with the DBA that directly precedes the DBA
	 * of the given node lexicographically).
	 * 
	 * @param n the node whose predecessor must be found
	 * @return a reference to the DbaList of the predecessor
	 */
	private DbaList getPredecessor(BSTNode n) {
		// Begin with the left child of the given node
		BSTNode current = n.getLeft();
		// While possible, move along from the
		// left child to the right until the
		// predecessor is found
		while (current.getRight() != null) {
			current = current.getRight();
		}
		return current.getList();
	}
}