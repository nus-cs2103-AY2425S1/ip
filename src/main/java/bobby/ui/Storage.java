package bobby.ui;

import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class is responsible for handling the storage and retrieval
 * of tasks from a file on disk. It allows tasks to be saved to the file
 * and loaded back into the program.
 */
public class Storage {

    /**
     * File path to store task data
     */
    private static String filePath= null;

    /**
     * Arraylist to keep track of what to store to disck
     */
    private static ArrayList<String> listForDisk;

    /**
     * Constructs a Storage object and initializes the file path and
     * list for disk storage.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.listForDisk = new ArrayList<>(100);
    }

    /**
     * Writes the given list of tasks to the file. Each task is converted to a
     * string format suitable for storage and then written to the file.
     *
     * @param local The TaskList containing the tasks to be saved.
     * @throws Exception If an I/O error occurs while writing to the file.
     */
    public static void writeToFile(TaskList local) throws Exception {
        FileWriter fw = new FileWriter(filePath, true);
        for (int x = 0; x < local.getSize(); x++) {
            Task curr_task = local.get(x);
            listForDisk.add(curr_task.toStore());
        }

        for (int x = 0; x < listForDisk.size(); x++) {
            fw.write(listForDisk.get(x) + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Loads tasks from the file into an ArrayList. Each line in the file is
     * parsed to reconstruct a Task object, which is then added to the list.
     *
     * @return An ArrayList containing the tasks loaded from the file.
     * @throws Exception If an I/O error occurs while reading from the file.
     */
    public static ArrayList<Task> loadFile() throws Exception {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Bobby.constructTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        }
        FileWriter fw = new FileWriter(filePath, false);
        return taskList;
    }
}
