# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar
hdfs dfs -rm -r -f output
# Compile
javac -classpath `yarn classpath` -d . ../hw4/CleanMapper.java
javac -classpath `yarn classpath` -d . ../hw4/CleanReducer.java
javac -classpath `yarn classpath`:. -d . ../hw4/Clean.java

# Create jar file
jar -cvf Clean.jar *.class

# Run the program
hadoop jar Clean.jar Clean /user/sc6472/input/hbo.csv /user/sc6472/output
hdfs dfs -cat count_origin/part-r-00000
