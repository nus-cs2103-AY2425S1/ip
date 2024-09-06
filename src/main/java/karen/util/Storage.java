package karen.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.List;

import karen.tasks.Task;
import karen.tasks.TaskList;

/**
 * Class which handles logic relating to file I/O
 */
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
     * @param taskList A TaskList to save to file
     */
    public static void saveToFile(TaskList taskList) {
        try {
            List<String> writeBuffer = taskList.toFileStrings();
            Files.write(FILEPATH, writeBuffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error while saving to file");
        }
    }

    /**
     * Populates the provided TaskList from reading Karen.txt
     * @param taskList TaskList containing the user's Tasks
     */
    public static void readFile(TaskList taskList) {
        //Outer try/catch for file access errors
        try {
            List<String> readBuffer = Files.readAllLines(FILEPATH);
            for (String line : readBuffer) {
                //inner try catch to handle invalid lines without breaking loop
                try {
                    Task newTask = Task.fromFileString(line);
                    taskList.addTask(newTask);
                } catch (IllegalArgumentException e) {
                    //Current parsed line is invalid
                    System.out.println("Error reading line");
                } catch (DateTimeParseException e) {
                    System.out.println("Error. Bad date format from file");
                }
            }
        } catch (IOException e) {
            System.out.println("Error trying to read file");
        }
    }
}
