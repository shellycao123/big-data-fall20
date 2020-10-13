# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar

# Compile
javac -classpath `yarn classpath` -d . ./hw4/AirbnbMapper.java
javac -classpath `yarn classpath` -d . ./hw4/AirbnbReducer.java
javac -classpath `yarn classpath`:. -d . ./hw4/AirbnbCount.java

# Create jar file
jar -cvf count.jar *.class

# Run the program
hadoop jar count.jar MapReduceCount /user/"$USER"/hw/input/input.txt /user/"$USER"/hw/output
