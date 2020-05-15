package proj1;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a DateList,
 * the data component stored in each node
 * of a binary search tree. Each instance
 * of the class has a specific inspection
 * date and a list of records with that
 * inspection date associated with it.
 * 
 * @author George Tolkachev
 * @version 05/03/16
 */
public class DateList {

	// The specific inspection date associated with the DateList
	private Date date;
	// The list containing all the records with the inspection date
	private List<Record> list;
		
	/**
	 * A constructor that sets the date and
	 * list data fields to their initial values
	 * based on the specified record (if this
	 * record is not null). The initial value
	 * of the date data field is the inspection
	 * date of the specified record. The list
	 * data field is initialized to a new
	 * LinkedList of records and adds the
	 * specified record to the first index.
	 * 
	 * @param r the record with the inspection
	 * 		 	date which becomes the value
	 * 			of the date data field
	 * @throws IllegalArgumentException
	 * 		   if the specified record is null
	 */
	public DateList(Record r) throws IllegalArgumentException {
		if (r == null) {
			throw new IllegalArgumentException ("Error: cannot create "
					+ "DateList with a null Record object.");
		}
		date = r.getInspectionDate();
		list = new LinkedList<Record>();
		list.add(0, r);
	}
		
	/**
	 * A constructor that sets the data and
	 * list data fields to their initial values
	 * based on the specified inspection date.
	 * 
	 * @param date the inspection date to which the 
	 * 			  date data field is initialized
	 */
	public DateList(Date date) {
		this.date = date;
		list = new LinkedList<Record>();
	}
		
	/**
	 * Gets the inspection date of the DateList.
	 * 
	 * @return the inspection date
	 */
	public Date getDate() {
		return date;
	}
		
	/**
	 * Gets the list of records contained
	 * within the DateList.
	 * 
	 * @return the list of records
	 */
	public List<Record> getList() {
		return list;
	}
	
	/**
	 * If the inspection date of the specified
	 * record is equal to the date data field,
	 * adds the record to the list of records
	 * and returns true to indicate that the
	 * operation was successful. Otherwise,
	 * returns false.
	 * 
	 * @param r the record to be added to the
	 * 			list of records
	 * @return true if the record was added successfully;
	 * 		   false, otherwise
	 */
	public boolean add(Record r) {
		if (r.getInspectionDate().equals(date)) {
			list.add(0,r);
			return true;
		}
		else {
			return false;
		}
	}
		
	/**
	 * Compares the inspection date contained within this DateList
	 * with that contained in another DateList; returns
	 * a positive value if the former is earlier
	 * than the latter, 0 is the two are equal, or
	 * a negative value if the former is later
	 * than the latter chronologically.
	 * 
	 * @return an integer value based on a
	 * 		   comparison of the inspection dates of the
	 * 		   two DateLists
	 */
	public int compareTo(DateList other) {
		return this.date.compareTo(other.date);
	}
}