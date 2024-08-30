package main.java.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import main.java.Task;

public class Storage {
    private static final Path FILEPATH = Paths.get("/main/java/data/Karen.txt");
    private Storage() {
        return;
    }

    /**
     * Handles the creation of Karen.txt at startup, if needed
     *
     */
    public static void initFile() {
        try {
            if (!Files.exists(FILEPATH)) {
                Files.createFile(FILEPATH);
            }
        } catch (IOException e) {
            System.out.println("Error occured when opening the file");
        }
    }

    /**
     * Writes the string list textToSave to /data/Karen.txt
     * @param taskList
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

    public static List<String> readFile() {
        //TODO Read saved data and return List<Task>
        try {
            return Files.readAllLines(FILEPATH);
        } catch (IOException e) {
            System.out.println("Error trying to read file");
            return new ArrayList<String>();
        }
    }
}
