#!/bin/bash

# Compile
# javac -d target/classes -cp $PATH_TO_FX/javafx-controls.jar:$PATH_TO_FX/javafx-fxml.jar src/main/java/com/appleaster/**/*.java

# Run
# java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -cp target/classes:src/main/resources com.appleaster.main.Launcher

# javac -verbose:class -d target/classes com/appleaster/**/*.java  
# java -cp target/classes com.appleaster.main.Launcher


# Set the root directory of your project
ROOT_DIR="/Users/blackhole/Desktop/Code.nosync/ip"



# Compile
find "$ROOT_DIR/src/main/java" -name "*.java" > sources.txt
javac -d "$ROOT_DIR/target/classes" -cp "$ROOT_DIR/src/main/java" @sources.txt

# Run
java -cp "$ROOT_DIR/target/classes:$ROOT_DIR/src/main/resources" com.appleaster.main.Launcher