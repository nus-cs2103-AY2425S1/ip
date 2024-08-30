package main.java.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import main.java.Task;

public class Storage {
    private static final Path DIRPATH = Paths.get("./data");
    private static final Path FILEPATH = Paths.get("./data/Karen.txt");
    private Storage() {
        return;
    }

    /**
     * Handles the creation of Karen.txt at startup, if needed
     *
     */
    public static void initFile() {
        try {
            if (!Files.exists(DIRPATH)) {
                Files.createDirectory(DIRPATH);
            }

            if (!Files.exists(FILEPATH)) {
                Files.createFile(FILEPATH);
            }
        } catch (IOException e) {
            System.out.println("Error occurred when initializing the file");
        }
    }

    /**
     * Writes the string list textToSave to /data/Karen.txt
     * @param taskList A list of Tasks to save to file
     */
    public static void saveToFile(List<Task> taskList) {
        List<String> writeBuffer = new ArrayList<>();
        try {
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                writeBuffer.add(t.toFileString());
            }
            Files.write(FILEPATH, writeBuffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error while saving to file");
        }
    }

    /**
     * Returns a List of String from reading Karen.txt
     *
     */
    public static List<Task> readFile() {
        //Outer try/catch for file access errors
        try {
            List<String> readBuffer = Files.readAllLines(FILEPATH);
            List<Task> taskList = new ArrayList<>();
            for (String line : readBuffer) {
                //inner try catch to handle invalid lines without breaking loop
                try {
                    Task newTask = Task.fromFileString(line);
                    taskList.add(newTask);
                } catch (IllegalArgumentException e) {
                    //Current parsed line is invalid
                    System.out.println("Error reading line");
                } catch (DateTimeParseException e) {
                    System.out.println("Error. Bad date format from file");
                }
            }
            return taskList;
        } catch (IOException e) {
            System.out.println("Error trying to read file");
            return new ArrayList<>();
        }
    }
}
