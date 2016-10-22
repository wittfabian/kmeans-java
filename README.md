# Programming assignment k-Means

A program that implements a k-Means algorithm, whereby k is a parameter to be specified at runtime.

# Run the program

To run the program you only have to save a file with the data as txt in the same directory as the Kmeans.jar (like the cardata.txt) and start the program with the following command in the command line:

java -jar Kmeans.jar cardata outfile 10 1 5

inputs:
- cardata: input file (concatenated with „.txt“ automatically)
- outfield: output file (concatenated with „.html“ automatically)
- 10: #xvalidations
- 1: start with k = 1
- 5: end with k = 5

# Output

If the run of the program was successful it generates an output file and saves it to the same directory.

In the file you will see the confusion matrix for one single run (but its the average over #xvalidations runs). Additionally you find the percentage of the correct classified test examples (one third of the hole input data).
