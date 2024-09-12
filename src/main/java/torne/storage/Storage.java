package torne.storage;

import torne.exception.TorneInvalidCommandException;
import torne.exception.TorneInvalidDataException;
import torne.task.Task;
import torne.ui.ChatOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final ChatOutput OUTPUT = new ChatOutput();
    private static final String TASKS_FILEPATH = "data/tasks.txt";

    /**
     * Checks for the existence of the required storage directories and files. Creates them if they do not exist.
     *
     * @return True if storage files had to be created.
     */
    private boolean checkAndCreateStorageFiles() {
        // first check for ./data
        File folder = new File("data");
        if (!folder.exists() || !folder.isDirectory()) {
            // create folder
            folder.mkdirs();
        }

        // now check for file
        File f = new File(TASKS_FILEPATH);
        if (!f.exists() || f.isDirectory()) {
            try {
                // create empty file
                f.createNewFile();
            } catch (IOException e) {
                OUTPUT.error("Could not create tasks file: " + e.getMessage());
            }
            // return true
            return true;
        }
        return false;
    }

    /**
     * Loads task data from storage at <code>./data/tasks.txt</code>.
     * If the directory or file does not exist, they are created.
     *
     * @return An ArrayList with the parsed tasks.
     */
    public ArrayList<Task> loadTaskData() {
        if (checkAndCreateStorageFiles()) {
            // file had to be created - file is empty.
            return new ArrayList<>();
        }

        ArrayList<Task> tasks = new ArrayList<>();
        int errorTaskCount = 0;

        try {
            File f = new File(TASKS_FILEPATH);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                try {
                    tasks.add(Task.fromStorageString(s.nextLine()));
                } catch (TorneInvalidCommandException | TorneInvalidDataException e) {
                    errorTaskCount++;
                }
            }

            // show error if there are error tasks
            if (errorTaskCount > 0) {
                OUTPUT.error(String.format("Warning: %d tasks could not be loaded.", errorTaskCount));
            }

            return tasks;
        } catch (FileNotFoundException e) {
            OUTPUT.error("torne.task.Task storage file not found: " + e.getMessage());
            return tasks;
        }
    }

    /**
     * Saves the list of tasks to the default text file
     * @param tasks List of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        // make sure storage files exist first
        checkAndCreateStorageFiles();

        try {
            FileWriter fw = new FileWriter(TASKS_FILEPATH);
            StringBuilder sb = new StringBuilder();

            for (Task task : tasks) {
                sb.append(task.toStorageString()).append("\n");
            }

            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            OUTPUT.error("Could not write to task file: " + e.getMessage());
        }
    }
}
