# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar

# Compile
javac -classpath `yarn classpath` -d . ../hw3/CountMapper.java
javac -classpath `yarn classpath` -d . ../hw3/CountReducer.java
javac -classpath `yarn classpath`:. -d . ../hw3/MapReduceCount.java

# Create jar file
jar -cvf count.jar *.class

# Run the program
hadoop jar count.jar MapReduceCount /user/"$USER"/hw/input/input.txt /user/"$USER"/hw/output
