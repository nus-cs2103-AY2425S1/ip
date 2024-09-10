package mahesh.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import mahesh.task.Deadline;
import mahesh.task.Event;
import mahesh.task.Task;
import mahesh.task.Todo;

/**
 * Handles the storage and retrieval of tasks from a file.
 */
public class Storage {

    private String pathString;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param pathString the relative path to the file for storing tasks
     */
    public Storage(String pathString) {
        this.pathString = "../" + pathString;
    }

    /**
     * Retrieves the task data from the file and returns it as a TaskList.
     * If the file does not exist, it creates a new file.
     * If the data is corrupted, it prints an error message.
     *
     * @return the TaskList containing the tasks retrieved from the file
     */
    public TaskList retrieveData() throws IOException {
        File dataFile = new File(this.pathString);
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();
        }
        Scanner fileScanner = new Scanner(dataFile);
        boolean isCorrupted = false;
        ArrayList<Task> list = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            try {
                String[] dataItem = fileScanner.nextLine().split("/");
                String type = dataItem[0];
                boolean isDone = Boolean.parseBoolean(dataItem[1]);
                switch (type) {
                case "T":
                    list.add(new Todo(dataItem[2], isDone));
                    break;
                case "D":
                    list.add(new Deadline(dataItem[2], LocalDateTime.parse(dataItem[3]), isDone));
                    break;
                case "E":
                    list.add(
                        new Event(
                            dataItem[2],
                            LocalDateTime.parse(dataItem[3]),
                            LocalDateTime.parse(dataItem[4]), isDone
                        )
                    );
                    break;
                default:
                    isCorrupted = true;
                }
            } catch (Exception e) {
                isCorrupted = true;
            }
        }
        if (isCorrupted) {
            Ui.printCorruptedDataErr();
        }
        fileScanner.close();
        return new TaskList(list);
    }

    /**
     * Updates the task data in the file with the current TaskList.
     * If the file does not exist, it creates a new file.
     *
     * @param list the TaskList containing the tasks to be saved to the file
     */
    public void updateData(TaskList list) throws IOException {
        File dataFile = new File(pathString);
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(dataFile);
        fileWriter.write(list.toFileData());
        fileWriter.close();
    }
}
