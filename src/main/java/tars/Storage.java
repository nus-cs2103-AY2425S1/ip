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
 * @author SKarthikeyan28
 */
public class Storage {
    protected static File taskFile;

    /**
     * Constructs storage object which takes in a filePath and sets filePath field in object
     *
     * @param filePath
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        //checked on Chat-GPT on creating storage file and retrieving creating storage file
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
        //checked on Chat-GPT on writing to an existing storage file
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
            String entry = s.nextLine(); //tasks from each line of file stored
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
     * @param entryParts
     * @param end
     * @return String (task description)
     */
    public static String getTaskDesc(String[] entryParts, int end) {
        StringBuilder strBuild = new StringBuilder();

        for (int i = 2; i < end; i++) {
            strBuild.append(entryParts[i]).append(" ");
        }

        return strBuild.toString();
    }

    /**
     * Writes LocalDateTime date for Deadline or Event based on entry stored
     *
     * @param entryParts
     * @param start
     * @return LocalDateTime object
     */
    public static LocalDateTime getTaskDate(String[] entryParts, int start) {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append(entryParts[start]).append(" ");
        return LocalDateTime.parse(strBuild.toString().trim());
    }

    /**
     * Creates task object based on entry read from storage file
     *
     * @param entryParts
     * @return Task
     */
    public static Task writeTask(String[] entryParts) {
        String taskType = entryParts[0];
        String taskDescript = " ";
        //no break statements for each case as return statements act as break
        switch (taskType) {
        case "T":
            taskDescript = getTaskDesc(entryParts, entryParts.length);
            return new ToDos(taskDescript.trim());
        case "D":
            int dateIndex = 0;
            for (int i = 1; i < entryParts.length; i++) {
                if (entryParts[i].equals("by:")) {
                    dateIndex = i + 1;
                }
            }

            taskDescript = getTaskDesc(entryParts, dateIndex - 1);
            LocalDateTime deadlineDate = getTaskDate(entryParts, dateIndex);
            return new Deadline(taskDescript.trim(), deadlineDate);
        case "E":
            int fromIndex = 0;
            int toIndex = 0;

            for (int i = 1; i < entryParts.length; i++) {
                if (entryParts[i].equals("from:")) {
                    fromIndex = i + 1;
                } else if (entryParts[i].equals("to:")) {
                    toIndex = i + 1;
                }
            }

            taskDescript = getTaskDesc(entryParts, fromIndex - 1);
            LocalDateTime fromDate = getTaskDate(entryParts, fromIndex);
            LocalDateTime toDate = getTaskDate(entryParts, toIndex);
            return new Event(taskDescript.trim(), fromDate, toDate);
        default:
            //checked on Chat-GPT for error to throw in default block
            throw new IllegalArgumentException("Unknown task type: " + taskType);
        }
    }
}
