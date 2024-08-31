package storage;

import parser.Parser;
import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class controls the storage management
 * It contains methods to load tasks from a file and also to save user inputs to the file
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws FileNotFoundException if the file specified by filePath does not exist.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File dataSaved = new File(filePath);
        Scanner s = new Scanner(dataSaved);

        while (s.hasNext()) {
            String[] dataArr = s.nextLine().split(" \\| ");
            Task newTask = Parser.parseSavedData(dataArr);
            taskList.add(newTask);
        }
        return taskList;
    }

    /**
     * Saves the list of tasks to the specified file.
     *
     * @param taskList The ArrayList of Task objects to be saved.
     * @throws IOException if an I/O error occurs during writing to the file.
     */
    public void saveTasks(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String separation = " | ";
        for (Task task : taskList) {
            fw.write(task.toSavedFormat(separation) + "\n");
        }
    }
}
