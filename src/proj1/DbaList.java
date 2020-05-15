package proj1;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a DbaList,
 * the data component stored in each node
 * of a binary search tree. Each instance
 * of the class has a specific DBA and
 * a list of records with that DBA
 * associated with it.
 * 
 * @author Joanna Klukowska
 * @version 04/27/16
 */
public class DbaList implements Comparable<DbaList> {
	
	// The specific DBA associated with the DbaList
	private String dba;
	// The list containing all the records with the DBA
	private List<Record> list;
	
	/**
	 * A constructor that sets the dba and
	 * list data fields to their initial values
	 * based on the specified record (if this
	 * record is not null). The initial value
	 * of the dba data field is the dba of the
	 * specified record. The list data field
	 * is initialized to a new LinkedList of
	 * records and adds the specified record
	 * to the first index.
	 * 
	 * @param r the record with the DBA which
	 * 		 	becomes the value of the dba
	 * 			data field
	 * @throws IllegalArgumentException
	 * 		   if the specified record is null
	 */
	public DbaList(Record r) throws IllegalArgumentException {
		if (r == null) {
			throw new IllegalArgumentException ("Error: cannot create "
					+ "DbaList with a null Record object.");
		}
		dba = r.getDba();
		list = new LinkedList<Record>();
		list.add(0, r);
	}
	
	/**
	 * A constructor that sets the dba and
	 * list data fields to their initial values
	 * based on the specified DBAA.
	 * 
	 * @param dba the DBA to which the dba 
	 * 			  data field is initialized
	 */
	public DbaList(String dba) {
		this.dba = dba;
		list = new LinkedList<Record>();
	}
	
	/**
	 * Gets the DBA of the DbaList.
	 * 
	 * @return the DBA
	 */
	public String getDba() {
		return dba;
	}
	
	/**
	 * Gets the list of records contained
	 * within the DbaList.
	 * 
	 * @return the list of records
	 */
	public List<Record> getList() {
		return list;
	}
	
	/**
	 * If the DBA of the specified record is
	 * equal to the dba data field, adds the
	 * record to the list of records and returns
	 * true to indicate that the add operation
	 * was successful. Otherwise, returns false.
	 * 
	 * @param r the record to be added to the
	 * 			list of records
	 * @return true if the record was added successfully;
	 * 		   false, otherwise
	 */
	public boolean add(Record r) {
		if (r.getDba().equalsIgnoreCase(dba)) {
			list.add(0,r);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Compares the DBA contained within this DbaList
	 * with that contained in another DbaList; returns
	 * a positive value if the former is lexicographically
	 * greater than the latter, 0 is the two are equal, or
	 * a negative value if the latter is lexicographically
	 * greater than the former.
	 * 
	 * @return an integer value based on the lexicographic
	 * 		   comparison of the DBAs of the two DbaLists
	 */
	public int compareTo(DbaList other) {
		return this.dba.compareToIgnoreCase(other.dba);
	}
}