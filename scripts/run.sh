# Build the jar file and run

# Remove class and jar files
rm *.class
rm *.jar

# Compile
javac -classpath `yarn classpath` -d . ../"$hw"/"$mapper".java
javac -classpath `yarn classpath` -d . ../"$hw"/"$reducer".java
javac -classpath `yarn classpath`:. -d . ../"$hw"/"$program".java

# Create jar file
jar -cvf "$jar".jar *.class

# Run the program
hadoop jar "$jar".jar "$program" /user/"$USER"/hw/input/input.txt /user/"$USER"/hw/output
