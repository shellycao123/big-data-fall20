# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar
hdfs dfs -rm -r -f count_origin
# Compile
javac -classpath `yarn classpath` -d . ../hw4/CountRecsMapper.java
javac -classpath `yarn classpath` -d . ../hw4/CountRecsReducer.java
javac -classpath `yarn classpath`:. -d . ../hw4/CountRecs.java

# Create jar file
jar -cvf CountRecs.jar *.class

# Run the program
hadoop jar CountRecs.jar CountRecs /user/sc6472/input/hbo.csv /user/sc6472/count_origin
hdfs dfs -cat output/part-r-00000
