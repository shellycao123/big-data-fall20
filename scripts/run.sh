# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar

# Compile
javac -classpath `yarn classpath` -d . ../hw4/CleanMapper.java
javac -classpath `yarn classpath` -d . ../hw4/CleanReducer.java
javac -classpath `yarn classpath`:. -d . ../hw4/Clean.java

# Create jar file
jar -cvf clean.jar *.class

# Run the program
hadoop jar clean.jar Clean /user/sc6472/input/hbo.csv /user/sc6472/output
