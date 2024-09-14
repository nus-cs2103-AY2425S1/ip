package tars;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storing of the Tasks added to the list and retrieves information of Tasks when reopened
 *
 * @author csk
 * @version 1
 */
public class Storage {
    protected static File taskFile;

    /**
     * Constructor for Storage object which takes in a filePath and sets filePath field in object
     *
     * @param filePath
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        this.taskFile = new File(filePath); // create a File for the given file path

        if (!taskFile.exists()) {
            taskFile.getParentFile().mkdirs();
            taskFile.createNewFile();
        }
    }

    /**
     * Writes file that will be used for the Tars application based on tasks existing or changed in tasksList
     *
     * @param filePath
     * @param tasks
     * @throws IOException
     */
    public static void writeFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);

        assert !filePath.isEmpty() : "File path cannot be empty!";
        assert tasks != null : "Task list cannot be empty!";
        for (Task task : tasks) {
            fileWriter.write(task.toFileFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Reads file that has been passed to storage object for Tars to reuse or use for the first time
     *
     * @return ArrayList of tasks
     * @throws IOException
     */
    public static ArrayList<Task> readFile() throws IOException {
        assert taskFile.exists() : "Task file does not exist!";
        Scanner s = new Scanner(taskFile); // create a Scanner using the File as source
        ArrayList<Task> itemsList = new ArrayList<>();

        while (s.hasNext()) {
            String entry = s.nextLine(); //Task from each line
            String[] entryParts = entry.split(" ");
            Task task = writeTask(entryParts);

            if (entryParts[1].equals("1")) {
                task.mark();
            }
            itemsList.add(task);
        }
        return itemsList;
    }

    /**
     * Forms description of task given, based on entry registered im storage file
     *
     * @param entryParts and taskType.
     * @return String (task description)
     */
    public static String getTaskDesc(String[] entryParts, String taskType) {
        StringBuilder strBuild = new StringBuilder();
        int end = entryParts.length;

        if (taskType.equals("D")) {
            end = entryParts.length - 3;
        } else if (taskType.equals("E")) {
            end = entryParts.length - 5;
        }

        for (int i = 2; i < end; i++) {
            strBuild.append(entryParts[i]).append(" ");
        }

        return strBuild.toString();
    }

    /**
     * Writes LocalDateTime date for Deadline or Event based on entry stored
     *
     * @param entryParts and keyword
     * @return LocalDateTime object
     */
    public static LocalDateTime getTaskDate(String[] entryParts, String keyword) {
        StringBuilder dateStr = new StringBuilder();

        for (int i = 2; i < entryParts.length; i++) {
            if (entryParts[i].equals(keyword)) {
                dateStr.append(entryParts[i + 1]);
            }
        }

        return LocalDateTime.parse(dateStr.toString().trim());
    }

    /**
     * Creates task object based on entry read from storage file
     *
     * @param entryParts
     * @return Task
     */
    public static Task writeTask(String[] entryParts) {
        String taskType = entryParts[0];
        String taskDescript = getTaskDesc(entryParts, taskType);

        switch (taskType) {
            case "T":
                return new ToDos(taskDescript.trim());
            case "D":
                LocalDateTime deadlineDate = getTaskDate(entryParts, "by:");
                return new Deadline(taskDescript.trim(), deadlineDate);
            case "E":
                LocalDateTime fromDate = getTaskDate(entryParts, "from:");
                LocalDateTime toDate = getTaskDate(entryParts, "to:");
                return new Event(taskDescript, fromDate, toDate);
            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }
}
