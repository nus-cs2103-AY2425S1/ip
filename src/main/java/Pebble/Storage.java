package pebble;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that manages the storage of tasks list in local storage
 */
public class Storage {
    /** Relative file path to store tasks list*/
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads from txt file to populate tasks list of this code
     *
     * @return ArrayList containing saved tasks
     * @throws IOException If file is not found, directory and file will be created
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasksList = new ArrayList<>();
        File file = new File(filePath);
        if (file.exists()) {
            populateTasks(file, tasksList);
        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return tasksList;
    }

    /**
     * Populate tasks list with tasks that is read from file
     * @param file File that stores the tasks locally
     * @param tasksList Array List that holds all the tasks
     * @throws FileNotFoundException Exception when no file is found
     */

    private static void populateTasks(File file, ArrayList<Task> tasksList) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task task = Parser.parseTaskFromString(line);
            tasksList.add(task);
        }
        scanner.close();
    }

    /**
     * Writes tasks list to the local device
     *
     * @param tasksList Task list to save
     * @throws IOException Handle input/output anomally
     */
    public void saveTasks(ArrayList<Task> tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasksList) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }
}
