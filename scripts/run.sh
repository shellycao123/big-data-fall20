# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar
hdfs dfs -rm -r -f project_output/clean
# Compile
javac -classpath `yarn classpath` -d . ../hw4/CleanMapper.java
javac -classpath `yarn classpath` -d . ../hw4/CleanReducer.java
javac -classpath `yarn classpath`:. -d . ../hw4/Clean.java

# Create jar file
jar -cvf Clean.jar *.class

# Run the program
hadoop jar Clean.jar Clean /user/sc6472/input/hbo.csv /user/sc6472/project_output/clean/
hdfs dfs -cat project_output/clean/part-r-00000
hdfs dfs -copyToLocal project_output/clean/part-r-00000 /home/sc6472/cleaned.csv
