This repo contains the code for the project in my Data Structures course, which I took as a freshman during Spring 2016. The project involved writing functions to analyze a dataset of restaurant inspection records in New York City. For example, we can search for restaurants by their name, or we can find all restaurants that received an inspection in the first several days of a given year.

The main program is RestaurantInfo.java. The command-line execution of the program has the following format:

java Restaurant_Inspection_Results_X.csv print.txt output.txt

where Restaurant_Inspection_Results_X.csv is the dataset for a particular borough or year, print.txt contains the search criteria, and output.txt is the file to which the results are written. Note that print.txt must contain the search function that we are applying to the data and the value of the field by which we are searching.

For example, if we wanted to search for restaurants that were inspected in the first 6 days of the year 2016, our print.txt file would contain "findByDate 6", and our command-line execution would be:

java Restaurant_Inspection_Results_2016.csv print.txt output.txt

Examples of inputs and outputs can be found [here](example_commands).
