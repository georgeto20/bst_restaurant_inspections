package proj1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 * This class represents a record with 18
 * data entries. It includes Comparator
 * classes for several of the data entries,
 * including camis, DBA, cuisine, score, and date.
 * 
 * @author George Tolkachev
 * @version 02/22/16
 */
public class Record {
	
	// The data entries of the record
	private String camis;
	private String dba;
	private String boro;
	private String building;
	private String street;
	private String zipcode;
	private String phone;
	private String cuisineDescription;
	private Date inspectionDate;
	private String action;
	private String violationCode;
	private String violationDescription;
	private String criticalFlag;
	private int score;
	private String grade;
	private Date gradeDate;
	private Date recordDate;
	private String inspectionType;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * A constructor that sets the data entries
	 * of the record to their initial values. If one of
	 * the Dates of a record (inspection date, grade
	 * date, or record date) cannot be parsed to a
	 * String, the constructor sets it equal to
	 * 01/01/1900, indicating that the restaurant
	 * was not inspected or graded. If the score of a
	 * record cannot be parsed to an integer, the
	 * constructor sets it equal to -1, indicating
	 * that no score was given.
	 * 
	 * @param entries a list of the data entries of the record
	 * @throws ParseException if one of the Dates of a record (inspection date,
	 * 						  grade date, or record date) cannot be parsed to a String
	 * @throws NumberFormatException if the score of a record cannot be parsed to an integer
	 */
	public Record(ArrayList<String> entries) throws ParseException, NumberFormatException {
		camis = entries.get(EntriesOrder.CAMIS.ordinal());
		dba = entries.get(EntriesOrder.DBA.ordinal());
		boro = entries.get(EntriesOrder.BORO.ordinal());
		building = entries.get(EntriesOrder.BUILDING.ordinal());
		street = entries.get(EntriesOrder.STREET.ordinal());
		zipcode = entries.get(EntriesOrder.ZIPCODE.ordinal());
		phone = entries.get(EntriesOrder.PHONE.ordinal());
		cuisineDescription = entries.get(EntriesOrder.CUSINE_DESCRIPTION.ordinal());
		try {
			inspectionDate = dateFormat.parse(entries.get(EntriesOrder.INSPECTION_DATE.ordinal()));
		}
		catch (ParseException e) {
			inspectionDate = dateFormat.parse("01/01/1900");
		}
		action = entries.get(EntriesOrder.ACTION.ordinal());
		violationCode = entries.get(EntriesOrder.VIOLATION_CODE.ordinal());
		violationDescription = entries.get(EntriesOrder.VIOLATION_DESCRIPTION.ordinal());
		criticalFlag = entries.get(EntriesOrder.CRITICAL_FLAG.ordinal());
		try {
			score = Integer.parseInt(entries.get(EntriesOrder.SCORE.ordinal()));
		}
		catch (NumberFormatException e) {
			score = -1;
		}
		grade = entries.get(EntriesOrder.GRADE.ordinal());
		try {
			gradeDate = dateFormat.parse(entries.get(EntriesOrder.GRADE_DATE.ordinal()));
		}
		catch (ParseException e) {
			gradeDate = dateFormat.parse("01/01/1900");
		}
		try {
			recordDate = dateFormat.parse(entries.get(EntriesOrder.RECORD_DATE.ordinal()));
		}
		catch (ParseException e) {
			recordDate = dateFormat.parse("01/01/1900");
		}
		inspectionType = entries.get(EntriesOrder.INSPECTION_TYPE.ordinal());
	}

	/**
	 * Prints information about the record.
	 * The information printed includes 6 of the 18
	 * data entries for the record. The method is to
	 * be used in the MyArrayList class.
	 */
	public String toString6Entries() {
		return String.format("%10s\t%s\t%s %s\t%s\t%10s\t%3s\n", 
								camis, setLength(dba, 20), building,
								setLength(street, 20 - (building.length() + 1)),
								setLength(cuisineDescription, 20),
								printDatefor6Entries(dateFormat.format(inspectionDate)),
								printScorefor6Entries(score));
	}
	
	/**
	 * Prints information about the record.
	 * The information printed includes all of the 18
	 * data entries for the record. The method is to
	 * be used in the ListOfRecords class.
	 */
	public String toString18Entries() {
		return String.format("\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", "
								+ "\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", "
								+ "\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", "
								+ "\"%s\", \"%s\", \"%s\"\n",
								camis, dba, boro, building, street, zipcode,
								phone, cuisineDescription,
								printDatefor18Entries(dateFormat.format(inspectionDate)),
								action, violationCode, violationDescription, criticalFlag,
								printScorefor18Entries(score), grade,
								printDatefor18Entries(dateFormat.format(gradeDate)),
								printDatefor18Entries(dateFormat.format(recordDate)), inspectionType);
	}

	/**
	 * Gets the DBA of the record.
	 * 
	 * @return the DBA
	 */
	public String getDba() {
		return dba;
	}
	
	/**
	 * Gets the inspection date of the record.
	 * 
	 * @return the inspection date
	 */
	public Date getInspectionDate() {
		return inspectionDate;
	}

	/**
	 * Gets the camis of the record.
	 * 
	 * @return the camis
	 */
	public String getCamis() {
		return camis;
	}

	/**
	 * Gets the cuisine description of the record.
	 * 
	 * @return the cuisine description
	 */
	public String getCuisineDescription() {
		return cuisineDescription;
	}

	/**
	 * Gets the score given to the record.
	 * 
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Gets the address of the record.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return building + " " + street;
	}
	
	/**
	 * Gets the zipcode of the record.
	 * 
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}
	
	/**
	 * Creates a new String of a new length
	 * that contains part or all of the
	 * original String, depending on whether
	 * the new length is smaller or greater than
	 * the original length.
	 * 
	 * @param s the original String
	 * @param newLength the length of the String to be created
	 * @return a new String with the necessary length
	 */
	private String setLength(String s, int newLength) {
		// If the new length is smaller
		// than the original length,
		if (newLength < s.length()) {
			String truncatedString = "";
			// append the first characters
			// of the original String until
			// the new length is reached
			for (int i = 0; i < newLength; i++) {
				truncatedString += s.charAt(i);
			}
			return truncatedString;
		}
		// If the new length is greater
		// than the original length,
		else {
			int originalLength = s.length();
			// add spaces until the new length is reached
			for (int i = 0; i < newLength - originalLength; i++) {
				s += ' ';
			}
			return s;
		}
	}
	
	/**
	 * Prints the score for the "print" command.
	 * If the score of a record is -1, prints a space.
	 * 
	 * @param score the score of a record
	 * @return the score or a space, depending on the score
	 */
	private String printScorefor6Entries(int score) {
		if (score == -1) {
			return " ";
		}
		else {
			return "" + score;
		}
	}
	
	/**
	 * Prints the date for the "print" command.
	 * If the date of a record is 01/01/1900, prints 10 spaces.
	 * 
	 * @param date the date of a record
	 * @return the date or 10 spaces, depending on the date
	 */
	private String printDatefor6Entries(String date) {
		if (date.equals("01/01/1900")) {
			return "          ";
		}
		else {
			return date;
		}
	}
	
	/**
	 * Prints the score for the "sortAll" command.
	 * If the score of a record is -1, prints an empty string.
	 * 
	 * @param score the score of a record
	 * @return the score or an empty string, depending on the score
	 */
	private String printScorefor18Entries(int score) {
		if (score == -1) {
			return "";
		}
		else {
			return "" + score;
		}
	}
	
	/**
	 * Prints the date for the "sortAll" command.
	 * If the date of a record is 01/01/1900, prints an empty string.
	 * 
	 * @param date the date of a record
	 * @return the date or an empty string, depending on the date
	 */
	private String printDatefor18Entries(String date) {
		if (date.equals("01/01/1900")) {
			return "";
		}
		else {
			return date;
		}
	}
}


/**
 * Defines Comparator object for the objects of type
 * record. The objects are compared by their unique
 * camis number; ties are resolved based on the
 * inspection date.
 * 
 * @author Joanna Klukowska/George Tolkachev
 *
 */
class RecordComparatorByCamis implements Comparator<Record>{
	public int compare(Record arg0, Record arg1) { 
		int compareResult = arg0.getCamis().compareTo(arg1.getCamis());
		if (compareResult == 0) {
			return arg0.getInspectionDate().compareTo(arg1.getInspectionDate());
		}
		else {
			return compareResult;
		}
	}	
}



/**
 * Defines Comparator object for the objects of type
 * record. The objects are compared by the name of the business;
 * ties are resolved based on the unique camis number
 * and then based on the inspection date. 
 * 
 * @author Joanna Klukowska/George Tolkachev
 *
 */
class RecordComparatorByDBA implements Comparator<Record>{
	
	public int compare(Record arg0, Record arg1) { 
		int compareResultByDBA = arg0.getDba().compareToIgnoreCase(arg1.getDba()) ; 
		if (compareResultByDBA == 0) {
			int compareResultByCamis = arg0.getCamis().compareTo(arg1.getCamis());
			if (compareResultByCamis == 0) {
				return arg0.getInspectionDate().compareTo(arg1.getInspectionDate());
			}
			else {
				return compareResultByCamis;
			}
		}
		else 
			return compareResultByDBA;
	}	
}


/**
 * Defines Comparator object for the objects of type
 * record. The objects are compared by the type of cuisine;
 * ties are resolved based on the unique camis number
 * and then based on the inspection date. 
 * 
 * @author Joanna Klukowska/George Tolkachev
 *
 */
class RecordComparatorByCuisine implements Comparator<Record>{
	
	public int compare(Record arg0, Record arg1) { 
		int compareResultByCuisine = arg0.getCuisineDescription().compareToIgnoreCase
													(arg1.getCuisineDescription()); 
		if (compareResultByCuisine == 0) {
			int compareResultByCamis = arg0.getCamis().compareTo(arg1.getCamis());
			if (compareResultByCamis == 0) {
				return arg0.getInspectionDate().compareTo(arg1.getInspectionDate());
			}
			else {
				return compareResultByCamis;
			}
		}
		else 
			return compareResultByCuisine;
	}	
}


/**
 * Defines Comparator object for the objects of type
 * record. The objects are compared by inspection scores;
 * ties are resolved based on the unique camis number
 * and then based on the inspection date. 
 * 
 * @author Joanna Klukowska/George Tolkachev
 *
 */
class RecordComparatorByScore implements Comparator<Record>{
	
	public int compare(Record arg0, Record arg1) { 
		int compareResultByScore = arg0.getScore() - arg1.getScore(); 
		if (compareResultByScore == 0) {
			int compareResultByCamis = arg0.getCamis().compareTo(arg1.getCamis());
			if (compareResultByCamis == 0) {
				return arg0.getInspectionDate().compareTo(arg1.getInspectionDate());
			}
			else {
				return compareResultByCamis;
			}
		}
		else 
			return compareResultByScore;
	}	
}


/**
 * Defines Comparator object for the objects of type
 * record. The objects are compared by inspection date;
 * ties are resolved based on the unique camis number. 
 * 
 * @author Joanna Klukowska
 *
 */
class RecordComparatorByDate implements Comparator<Record>{
	
	public int compare(Record arg0, Record arg1) { 
		int compareResult = arg0.getInspectionDate().compareTo( arg1.getInspectionDate()); 
		if (compareResult == 0) {
			return arg0.getCamis().compareTo( arg1.getCamis());
		}
		else 
			return compareResult;
	}	
}