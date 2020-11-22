# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar
hdfs dfs -rm -r -f project/project_output/genre_distribution
# Compile
javac -classpath `yarn classpath` -d . ../hw4/GenreMapper.java
javac -classpath `yarn classpath` -d . ../hw4/GenreReducer.java
javac -classpath `yarn classpath`:. -d . ../hw4/Genre.java

# Create jar file
jar -cvf genre.jar *.class

# Run the program
hadoop jar genre.jar Genre /user/sc6472/project/project_input/cleaned/cleaned.csv /user/sc6472/project/project_output/genre_distribution
hdfs dfs -cat project/project_output/genre_distribution/part-r-00000
hdfs dfs -copyToLocal project/project_output/genre_distribution/part-r-00000 /home/sc6472/big-data-fall20/hw4/genre_distribution.txt
