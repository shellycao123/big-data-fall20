# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar
hdfs dfs -rm -r -f project_output/origin_Year
# Compile
javac -classpath `yarn classpath` -d . ../hw4/YearMapper.java
javac -classpath `yarn classpath` -d . ../hw4/YearReducer.java
javac -classpath `yarn classpath`:. -d . ../hw4/Year.java

# Create jar file
jar -cvf years.jar *.class

# Run the program
hadoop jar years.jar Years /user/sc6472/project_input/origin/hbo.csv /user/sc6472/project_output/year_distribution
hdfs dfs -cat project_output/year_distribution/part-r-00000
hdfs dfs -copyToLocal project_output/year_distribution/part-r-00000 /home/sc6472/year_distribution.txt
