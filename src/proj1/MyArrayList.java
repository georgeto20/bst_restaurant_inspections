package proj1;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents an extended
 * version of the ArrayList class.
 * It contains two different sorting
 * methods: selection sort, a slow
 * quadratic sort, and merge sort,
 * a fast O(NlogN) sort.
 * 
 * @author George Tolkachev
 * @version 02/22/16
 */
@SuppressWarnings("serial")
public class MyArrayList extends ArrayList<Record> {
	
	/**
	 * Sorts the list by one of five data entries:
	 * camis, DBA, cuisine, score, or date. Uses the
	 * technique of selection sort, a slow quadratic sort.
	 * 
	 * @param key the data entry by which the
	 * 			  list needs to be sorted
	 */
	public void sort(String key) {
		// Comparators for each of the five data entries
		RecordComparatorByCamis compCamis = new RecordComparatorByCamis();
		RecordComparatorByDBA compDba = new RecordComparatorByDBA();
		RecordComparatorByCuisine compCuisine = new RecordComparatorByCuisine();
		RecordComparatorByScore compScore = new RecordComparatorByScore();
		RecordComparatorByDate compDate = new RecordComparatorByDate();
		// Compare the first element with every
		// element succeeding it; if the data entry
		// of any successive element is smaller
		// than that of the first, exchange the two
		// elements; move on to the second element;
		// repeat until the array is sorted
		for (int i = 0; i < super.size() - 1; i++) {
			Record currentMin = super.get(i);
			int currentMinIndex = i;
			for (int j = i + 1; j < super.size(); j++) {
				// If the data entry is camis, sort the list by camis
				if (key.equalsIgnoreCase("CAMIS")) {
					if (compCamis.compare(currentMin, super.get(j)) > 0) {
						currentMin = super.get(j);
						currentMinIndex = j;
					}
				}
				// If the data entry is DBA, sort the list by DBA
				else if (key.equalsIgnoreCase("DBA")) {
					if (compDba.compare(currentMin, super.get(j)) > 0) {
						currentMin = super.get(j);
						currentMinIndex = j;
					}
				}
				// If the data entry is cuisine, sort the list by cuisine
				else if (key.equalsIgnoreCase("CUISINE")) {
					if (compCuisine.compare(currentMin, super.get(j)) > 0) {
						currentMin = super.get(j);
						currentMinIndex = j;
					}
				}
				// If the data entry is score, sort the list by score
				else if (key.equalsIgnoreCase("SCORE")) {
					if (compScore.compare(currentMin, super.get(j)) > 0) {
						currentMin = super.get(j);
						currentMinIndex = j;
					}
				}
				// If the data entry is date, sort the list by date
				else if (key.equalsIgnoreCase("DATE")) {
					if (compDate.compare(currentMin, super.get(j)) > 0) {
						currentMin = super.get(j);
						currentMinIndex = j;
					}
				}
			}
			
			// Exchange two elements if a minimum
			// value of a data entry is found in an
			// element following the original element
			if (currentMinIndex != i) {
				super.set(currentMinIndex, super.get(i));
				super.set(i, currentMin);
			}
		}
	}
	
	/**
	 * A wrapper method for sorting the list
	 * by camis, DBA, cuisine, or score
	 * using the technique of merge sort,
	 * a fast O(NlogN) sort.
	 * 
	 * @param key the data entry by which the
	 * 			  list needs to be sorted
	 */
	public void sortFaster(String key) {
		if (key.equalsIgnoreCase("CAMIS")) {
			RecordComparatorByCamis compCamis = new RecordComparatorByCamis();
			sort(compCamis);
		}
		else if (key.equalsIgnoreCase("DBA")) {
			RecordComparatorByDBA compDba = new RecordComparatorByDBA();
			sort(compDba);
		}
		else if (key.equalsIgnoreCase("CUISINE")) {
			RecordComparatorByCuisine compCuisine = new RecordComparatorByCuisine();
			sort(compCuisine);
		}
		else if (key.equalsIgnoreCase("SCORE")){
			RecordComparatorByScore compScore = new RecordComparatorByScore();
			sort(compScore);
		}
	}
	
	/**
	 * Another wrapper method for the
	 * merge sort technique.
	 * 
	 * @param comp the comparator of the data entry
	 * 			   by which the list needs to be sorted
	 */
	public void sort(Comparator<? super Record> comp) {
		mergeSortRec(comp, 0, super.size() - 1);
	}
	
	/**
	 * Splits the list into two halves and
	 * recursively sorts the halves separately
	 * before merging them together.
	 * 
	 * @param comp the comparator of the data entry
	 * 		  	   by which the list needs to be sorted
	 * @param firstIndex the index at which the first
	 * 					 half of the list begins
	 * @param lastIndex the index at which the latter
	 * 					half of the list ends
	 */
	private void mergeSortRec(Comparator<? super Record> comp, int firstIndex, int lastIndex) {
		// If the beginning index is greater than
		// or equal to the ending index, stop sorting
		if (firstIndex >= lastIndex) {
			return;
		}
		// The index at which the two halves
		// of the list separate
		int mid = (firstIndex + lastIndex) / 2;
		// Sort the first half
		mergeSortRec(comp, firstIndex, mid);
		// Sort the latter half
		mergeSortRec(comp, mid + 1, lastIndex);
		// Merge the two halves together
		merge(comp, firstIndex, mid, mid + 1, lastIndex);
	}
	
	/**
	 * Merges two sorted halves into one list.
	 * Assumes that the two halves are sorted.
	 * 
	 * @param comp the comparator of the data entry
	 * 			   by which the list needs to be sorted
	 * @param leftFirst the index at which the first
	 * 					half of the list begins
	 * @param leftLast the index at which the first
	 * 				   half of the list ends
	 * @param rightFirst the index at which the latter
	 * 					 half of the list begins
	 * @param rightLast the index at which the latter
	 * 					half of the list ends
	 */
	private void merge(Comparator<? super Record> comp, int leftFirst, int leftLast,
										int rightFirst, int rightLast) {
		// Create a temporary array of type Record
		// to contain the sorted version of the list
		Record[] temp = new Record[rightLast - leftFirst + 1];
		int indexLeft = leftFirst;
		int indexRight = rightFirst;
		int mergedIndex = 0;
		// While the two halves still contain elements:
		while (indexLeft <= leftLast && indexRight <= rightLast) {
			// If an element in the first half is smaller than
			// its corresponding element in the latter half,
			// add it to the temporary array
			if (comp.compare(super.get(indexLeft), super.get(indexRight)) < 0) {
				temp[mergedIndex++] = super.get(indexLeft++);
			}
			// Otherwise, add the element in the latter half
			// to the temporary array
			else {
				temp[mergedIndex++] = super.get(indexRight++);
			}
		}
		// If the latter half runs out of elements,
		// add the rest of the first half to the
		// temporary array
		while (indexLeft <= leftLast) {
			temp[mergedIndex++] = super.get(indexLeft++);
		}
		// If the first half runs out of elements,
		// add the rest of the latter half to the
		// temporary array
		while (indexRight <= rightLast) {
			temp[mergedIndex++] = super.get(indexRight++);
		}
		// Copy the temporary array to the list
		// so that the list is now sorted
		for (int i = leftFirst; i < temp.length + leftFirst; i++) {
			super.set(i, temp[i - leftFirst]);
		}
	}
	
	/**
	 * Checks whether the list contains a given element.
	 */
	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}
	
	/**
	 * Finds the index of an element in the list.
	 * Returns -1 if the element is not found.
	 */
	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < super.size(); i++) {
				if (super.get(i) == null) {
					return i;
				}
			}
		}
		else {
			for (int i = 0; i < super.size(); i++) {
				if (o.equals(super.get(i))) {
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Prints information about the list.
	 * The information printed includes 6 of the 18
	 * data entries for each record.
	 */
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < super.size(); i++) {
			Record r = super.get(i);
			str = str + r.toString6Entries();
		}
		return str;
	}
}