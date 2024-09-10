package Azir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage deals with loading tasks from the file
 * and saving tasks in the file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static void addSavedTasks(Task currTask, String isCompleted, ArrayList<Task> taskList) {
        if (isCompleted.equals("Complete")) {
            currTask.setDone();
        } else {
            currTask.setNotDone();
        }
        taskList.add(currTask);
    }

    /**
     * Reads tasks from the hard disk
     *
     * @param filePath Path of current hard disk file
     * @return List of tasks in the hard disk
     * @throws FileNotFoundException If there is no hard disk file at filePath
     */
    public static ArrayList<Task> readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (s.hasNextLine()) {
            String[] arr = s.nextLine().split(" \\| ");
            if (arr[0].equals("T")) {
                Task currTask = new Todo(arr[2]);
                addSavedTasks(currTask, arr[1], taskList);
            } else if (arr[0].equals("D")) {
                Task currTask = new Deadline(arr[2], LocalDate.parse(arr[3],
                        DateTimeFormatter.ofPattern("MMM d yyyy")));
                addSavedTasks(currTask, arr[1], taskList);
            } else {
                Task currTask = new Event(arr[2], arr[3], arr[4]);
                addSavedTasks(currTask, arr[1], taskList);
            }
        }
        return taskList;
    }

    /**
     * Helper method for write, writes content to hard disk
     *
     * @param filePath Path of current hard disk file
     * @param lines List of formatted Task strings
     */
    public static void writeToFile(String filePath, ArrayList<String> lines) {
        try {
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }

    /**
     * Loads tasks from the hard disk
     *
     * @return List of tasks in the hard disk
     * @throws IOException If there are errors with the file
     */
    public ArrayList<Task> load() throws IOException {
        return readFileContents(this.filePath);
    }

    /**
     * Saves tasks to hardDisk
     *
     * @param filePath Location of hard disk file
     * @param lines List of tasks that have been formatted
     * @throws IOException If there are errors with the file
     */
    public void write(String filePath, ArrayList<String> lines) throws IOException {
        if (!Files.exists(Paths.get("./data"))) {
            Files.createDirectory(Paths.get("./data"));
        }
        writeToFile(filePath, lines);
    }
}
