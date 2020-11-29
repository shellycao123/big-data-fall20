# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar
hdfs dfs -rm -r -f project/project_output/Genre_distribution
# Compile
javac -classpath `yarn classpath` -d . ../hw4/ana_code/GenreMapper.java 
javac -classpath `yarn classpath` -d . ../hw4/ana_code/GenreReducer.java
javac -classpath `yarn classpath`:. -d . ../hw4/ana_code/Genre.java

# Create jar file
jar -cvf Genre.jar *.class

# Run the program
hadoop jar Genre.jar Genre /user/sc6472/project/project_input/cleaned/cleaned.csv /user/sc6472/project/project_output/Genre_distribution
hdfs dfs -cat project/project_output/Genre_distribution/part-r-00000
hdfs dfs -copyToLocal project/project_output/Genre_distribution/part-r-00000 /home/sc6472/big-data-fall20/hw4/Genre_distribution.txt
