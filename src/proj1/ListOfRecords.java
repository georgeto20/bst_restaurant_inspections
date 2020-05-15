package proj1;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class represents a list of records.
 * Its four operations are each implemented
 * using different data structures: a binary
 * search tree for finding records by name,
 * a hash table of lists for finding records
 * by name and address, a binary search tree
 * for finding records by date, and a hash table
 * of priority queues for finding records by score.
 * 
 * @author George Tolkachev
 * @version 04/27/16
 */
public class ListOfRecords {

	// The list of records
	private MyArrayList list;
	// The binary search tree of records
	// to be used for finding records by name
	private bstOfRecordsByDBA treeOfNames;
	
	// A list of records to be included in
	// the hash table used for finding records
	// by name and address
	private List<Record> nameAddressList;
	// The hash table of lists to be used for
	// finding records by name and address
	private Hashtable<String, List<Record>> nameAddressTable;
	
	// A list of dates to be used alongside
	// the binary search tree used for finding
	// records by date
	private List<Date> dateList;
	// The binary search tree of records
	// to be used for finding records by date
	private bstOfRecordsByDate treeOfDates;
	
	// A priority queue of records to be included
	// in the hash table used for finding records by score
	private PriorityQueue<Record> scoreQueue;
	// The hash table of priority queues
	// to be used for finding records by score
	private Hashtable<String, PriorityQueue<Record>> scoreTable;

	/**
	 * A default (no-arg) constructor that
	 * initializes all the data fields.
	 */
	public ListOfRecords() {
		list = new MyArrayList();
		treeOfNames = new bstOfRecordsByDBA();
		nameAddressList = new LinkedList<Record>();
		nameAddressTable = new Hashtable<String, List<Record>>();
		dateList = new LinkedList<Date>();
		treeOfDates = new bstOfRecordsByDate();
		scoreQueue = new PriorityQueue<Record>();
		scoreTable = new Hashtable<String, PriorityQueue<Record>>();
	}

	/**
	 * Adds a record to the data structures
	 * used for the four operations.
	 * 
	 * @param words a list of the data entries
	 * 				for a particular record
	 * @throws ParseException if one of the Dates of a record (inspection date,
	 * 						  grade date, or record date) cannot be parsed to a String
	 * @throws NumberFormatException if the score of a record cannot be parsed to an integer
	 */
	public void add(ArrayList<String> words) throws ParseException, NumberFormatException {
		// Create a new record with the given list of data entries
		Record r = new Record(words);
		// Add the record to the list
		list.add(r);

		/* 
		 * For finding records by NAME: 
		 */
		// Add the record to the tree
		treeOfNames.add(r);
		
		/*
		 * For finding records by NAME and ADDRESS:
		 */
		// Save the DBA and the address of a record
		// (separated by a space) as the key
		String key = r.getDba().toUpperCase() + " " + r.getAddress().toUpperCase();
		// If the hash table does not contain this key,
		// create a new list of records to which
		// records with this particular key can be added
		if (!nameAddressTable.containsKey(key)) {
			nameAddressList = new LinkedList<Record>();
		}
		// Add the record to the list
		nameAddressList.add(r);
		// Add the (key, value) pair to the hash table
		nameAddressTable.put(key, nameAddressList);
		
		/*
		 * For finding records by DATE:
		 */
		// Save the inspection date of a record
		Date date = r.getInspectionDate();
		// If the list of dates does not contains
		// this date, add the date to the list
		if (!dateList.contains(date)) {
			dateList.add(date);
		}
		// Add the record to the tree
		treeOfDates.add(r);
		
		/*
		 * For finding records by SCORE:
		 */
		// Save the zipcode of a record
		String zipcode = r.getZipcode();
		RecordComparatorByScore compScore = new RecordComparatorByScore();
		// If the hash table does not contain this
		// zipcode as a key, create a new
		// priority queue to which records with
		// this particular zipcode can be added
		if (!scoreTable.containsKey(zipcode)) {
			scoreQueue = new PriorityQueue<Record>(compScore);
		}
		// Add the record to the queue
		scoreQueue.add(r);
		// Add the (key, value) pair to the hash table
		scoreTable.put(zipcode, scoreQueue);
	}

	/**
	 * Prints information about the list of records.
	 * The information printed includes all of the 18
	 * data entries for each record.
	 */
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			Record r = list.get(i);
			// Add information about each
			// successive record in the list
			str = str + r.toString18Entries();
		}
		return str;
	}
 
	/**
	 * Sorts the list of records by one of five
	 * data entries: camis, DBA, cuisine,
	 * score, or date. Uses the sort method
	 * from the MyArrayList class.
	 * 
	 * @param key the data entry by which the
	 * 		  	  list needs to be sorted
	 */
	public void sort(String key) {
		list.sort(key);
	}
	
	/**
	 * Sorts the list of records by one of four
	 * data entries: camis, DBA, cuisine, or
	 * score. Uses the sortFaster method
	 * from the MyArrayList class.
	 * 
	 * @param key the data entry by which the
	 * 		  	  list needs to be sorted
	 */
	public void sortFaster(String key) {
		list.sortFaster(key);
	}
	
	/**
	 * Checks whether the list is sorted
	 * by the given key.
	 * 
	 * @param key the key by which the list
	 * 			  needs to be sorted
	 * @return true if the list is sorted by the
	 * 		   given key, false otherwise
	 */
	public boolean isSorted(String key) {
		boolean sorted = true;
		// Comparators for each of the four data entries
		RecordComparatorByCamis compCamis = new RecordComparatorByCamis();
		RecordComparatorByDBA compDba = new RecordComparatorByDBA();
		RecordComparatorByCuisine compCuisine = new RecordComparatorByCuisine();
		RecordComparatorByScore compScore = new RecordComparatorByScore();
		// For each element in the array, check whether
		// each element is greater than or equal to
		// the previous element, and if not,
		// set sorted to false
		for (int i = 0; i < list.size() - 1; i++) {
			if (key.equalsIgnoreCase("CAMIS")) {
				if (compCamis.compare(list.get(i), list.get(i + 1)) > 0) {
					sorted = false;
				}
			}
			else if (key.equalsIgnoreCase("DBA")) {
				if (compDba.compare(list.get(i), list.get(i + 1)) > 0) {
					sorted = false;
				}
			}
			else if (key.equalsIgnoreCase("CUISINE")) {
				if (compCuisine.compare(list.get(i), list.get(i + 1)) > 0) {
					sorted = false;
				}
			}
			else if (key.equalsIgnoreCase("SCORE")) {
				if (compScore.compare(list.get(i), list.get(i + 1)) > 0) {
					sorted = false;
				}
			}
		}
		return sorted;
	}

	/**
	 * Finds all records with the given DBA.
	 * Implemented using a binary search tree.
	 * 
	 * @param key the DBA of the restaurant
	 * 			  that needs to be found
	 * @return a list of all records with the given DBA
	 */
	public MyArrayList findByName(String key) {
		MyArrayList results = new MyArrayList();
		// If the DbaList containing records
		// with the key DBA is not null:
		if (treeOfNames.get(key) != null) {
			// For each record in the DbaList of records,
			// add the record to the list of results
			for (Record r : treeOfNames.get(key).getList())
				results.add(r);
		}
		return results;
	}

	/**
	 * Finds all records with the given
	 * DBA and address. If the date flag is "first",
	 * returns the record with the first inspection;
	 * if the date flag is "last", returns the record
	 * with the last inspection; if the date flag is
	 * "all", returns all the records with the given
	 * DBA and address.
	 * The method is implemented using a hash table.
	 * This implementation is much more efficient than
	 * the one from previous projects. In order to find
	 * the necessary records, the previous
	 * implementation used a binary search, which has an
	 * average running time of O(logN). Well-designed hash
	 * tables, meanwhile, allow the user to access any value in
	 * O(1) time, and thus have a faster running time.
	 * Another advantage of the current implementation is that
	 * it contains fewer local variables, so that the hash table
	 * can use up as much memory as it requires.
	 * 
	 * @param key1 the DBA of the restaurant
	 * 			   that needs to be found
	 * @param key2 the address of the restaurant
	 * 			   that needs to be found
	 * @param dateFlag the date flag specifying
	 * 				   whether to return the first,
	 * 				   last, or all of the records
	 * 				   with the given DBA and address
	 * @return a list containing the first, last, or all of the
	 * 		   records with the given DBA and address
	 */
	public MyArrayList findByNameAddress(String key1, String key2, String dateFlag) {
		MyArrayList results = new MyArrayList();
		// If the hash table contains the specified DBA
		// and address (separated by a space) as a key:
		if (nameAddressTable.get(key1.toUpperCase() + " " + key2.toUpperCase()) != null) {
			// Add to results all the records in the corresponding list
			for (Record r : nameAddressTable.get(key1.toUpperCase() + " " + key2.toUpperCase())) {
				results.add(r);
			}
			// If the date flag is "first":
			if (dateFlag.equalsIgnoreCase("first")) {
				results.sort("DBA");
				// Save the record with the earliest inspection date
				Record first = results.get(0);
				results.clear();
				// Return the earliest record
				results.add(first);
			}
			// If the date flag is "last", return the last record found
			else if (dateFlag.equalsIgnoreCase("last")) {
				results.sort("DBA");
				// Save the record with the latest inspection date
				Record last = results.get(results.size() - 1);
				results.clear();
				// Return the latest record
				results.add(last);
			}
		}
		// Otherwise, if the date flag is "all", return all of the records found
		return results;
	}

	/**
	 * Finds all the records that were
	 * inspected in the first given number of dates.
	 * The method is implemented using a binary search tree.
	 * This implementation is an improvement on the
	 * previous one, since the data are organized
	 * in a more intuitive way. Using the current
	 * approach, it is easier to traverse through
	 * the tree in order to find a particular record
	 * than in the original list implementation.
	 * As a result, the current implementation
	 * is more efficient than the one written
	 * for previous projects.
	 * 
	 * @param numOfDates the first given number of dates
	 * @return a list of records that were inspected
	 * 		   in the first given number of dates
	 */
	public MyArrayList findByDate(int numOfDates) {	
		MyArrayList results = new MyArrayList();
		// Sort the list of dates
		Collections.sort(dateList);
		// Create a counter to make sure that only
		// records in the first number of dates are
		// added to results
		int dateNumber = 0;
		// While the counter is less than the
		// first number of dates:
		while (dateNumber < numOfDates && dateNumber < dateList.size()) {
			// For each record in the DateList of records,
			// add the record to the list of results
			for (Record r : treeOfDates.get(dateList.get(dateNumber)).getList()) {
				results.add(r);
			}
			dateNumber++;
		}
		// Sort the list of results so that the records
		// are returned in chronological order
		results.sort("DATE");
		return results;
	}

	/**
	 * Finds all the records with a score
	 * less than or equal to the given score
	 * and with the given zipcode.
	 * The method is implemented using a hash table
	 * of priority queues. This implementation is
	 * more efficient because it uses a hash table
	 * (which essentially guarantees O(1) performance).
	 * In addition, the priority queue allows the data
	 * to be more organized than a list. These two
	 * data structures combine to create an
	 * implementation that is both efficient and
	 * easier to understand.
	 * 
	 * @param score the given score
	 * @param zipcode the given zipcode
	 * @return a list of records with a score
	 * 		   less than or equal to the
	 * 		   given score and with the
	 * 		   given zipcode
	 */
	public MyArrayList findByScore(int score, String zipcode) {
		MyArrayList results = new MyArrayList();
		// If the hash table contains the
		// specified zipcode as a key:
		if (scoreTable.get(zipcode) != null) {
			// Remove from the hash table all
			// the records with an invalid score
			while (scoreTable.get(zipcode).peek().getScore() == -1) {
				scoreTable.get(zipcode).poll();
			}
			// Add to results all the records in the
			// corresponding priority queue with a score
			// less than or equal to the specified score
			while (scoreTable.get(zipcode).peek() != null
					&& scoreTable.get(zipcode).peek().getScore() <= score) {
				results.add(scoreTable.get(zipcode).poll());
			}
		}
		return results;
	}
}