package snah.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import snah.task.Deadline;
import snah.task.Event;
import snah.task.Task;
import snah.task.ToDo;

/**
 * Class to handle the storage of the chatbot
 */
public class Storage {
    private static final String SAVE_FILE_NAME = "snah.txt";

    /**
     * Constructor for the Storage class
     */
    public Storage() {
        try {
            File file = new File(SAVE_FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Reads the save file and returns the list of tasks
     * @return List of tasks
     */
    public ArrayList<Task> getTaskLists() {
        ArrayList<Task> tasksList = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(SAVE_FILE_NAME));
            for (String line : lines) {
                String[] data = line.split(":");

                if (data[0].startsWith("T")) {
                    ToDo newTask = new ToDo(data[2]);
                    if (data[1].equals("x")) {
                        newTask.markAsDone();
                    }
                    tasksList.add(newTask);
                } else if (data[0].startsWith("D")) {
                    Task newTask = new Deadline(data[2], data[3]);
                    if (data[1].equals("x")) {
                        newTask.markAsDone();
                    }
                    tasksList.add(newTask);
                } else if (data[0].startsWith("E")) {
                    Task newTask = new Event(data[2], data[3], data[4]);
                    if (data[1].equals("x")) {
                        newTask.markAsDone();
                    }
                    tasksList.add(newTask);
                }

            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasksList;
    }

    /**
     * Saves the list of tasks to the save file
     * @param tasksList List of tasks to be saved
     */
    public void saveTaskList(ArrayList<Task> tasksList) {
        try {
            Files.newBufferedWriter(Paths.get(SAVE_FILE_NAME), StandardOpenOption.TRUNCATE_EXISTING).close();

            for (Task task : tasksList) {
                String content = task.toSaveFile() + System.lineSeparator();
                Files.write(Paths.get("snah.txt"), content.getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
