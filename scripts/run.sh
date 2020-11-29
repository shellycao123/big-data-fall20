# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar
hdfs dfs -rm -r -f project/project_output/Year_distribution
# Compile
javac -classpath `yarn classpath` -d . ../hw4/ana_code/YearMapper.java
javac -classpath `yarn classpath` -d . ../hw4/ana_code/YearReducer.java
javac -classpath `yarn classpath`:. -d . ../hw4/ana_code/Year.java

# Create jar file
jar -cvf Year.jar *.class

# Run the program
hadoop jar Year.jar Year /user/sc6472/project/project_input/cleaned/cleaned.csv /user/sc6472/project/project_output/Year_distribution
hdfs dfs -cat project/project_output/Year_distribution/part-r-00000
hdfs dfs -copyToLocal project/project_output/Year_distribution/part-r-00000 /home/sc6472/big-data-fall20/hw4/Year_distribution.txt
