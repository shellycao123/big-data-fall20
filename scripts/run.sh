# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar
hdfs dfs -rm -r -f project_output/year_distribution
# Compile
javac -classpath `yarn classpath` -d . ../hw4/YearMapper.java
javac -classpath `yarn classpath` -d . ../hw4/YearReducer.java
javac -classpath `yarn classpath`:. -d . ../hw4/Year.java

# Create jar file
jar -cvf year.jar *.class

# Run the program
hadoop jar year.jar Year /user/sc6472/project/project_input/cleaned.csv /user/sc6472/project/project_output/year_distribution
hdfs dfs -cat project/project_output/year_distribution/part-r-00000
hdfs dfs -copyToLocal project/project_output/year_distribution/part-r-00000 /home/sc6472/year_distribution.txt
