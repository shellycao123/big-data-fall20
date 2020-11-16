# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar
hdfs dfs -rm -r -f project_output/cleaned_count
# Compile
javac -classpath `yarn classpath` -d . ../hw4/CountRecsMapper.java
javac -classpath `yarn classpath` -d . ../hw4/CountRecsReducer.java
javac -classpath `yarn classpath`:. -d . ../hw4/CountRecs.java

# Create jar file
jar -cvf CountRecs.jar *.class

# Run the program
hadoop jar CountRecs.jar CountRecs /user/sc6472/project_input/cleaned/cleaned.csv /user/sc6472/project_output/cleaned_count
hdfs dfs -cat project_output/cleaned_count/part-r-00000
hdfs dfs -copyToLocal project_output/cleaned_count/part-r-00000 /home/sc6472/cleaned_count.csv
