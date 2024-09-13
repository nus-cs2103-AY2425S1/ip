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

            if (entryParts[0].equals("T")) {
                StringBuilder strBuild = new StringBuilder();
                for (int i = 2; i < entryParts.length; i++) {
                    strBuild.append(entryParts[i]).append(" ");
                }
                ToDos todo = new ToDos(strBuild.toString().trim());
                if (entryParts[1].equals("1")) {
                    todo.mark();
                }
                itemsList.add(todo);
            } else if (entryParts[0].equals("D")) {
                StringBuilder strBuild = new StringBuilder();
                StringBuilder dateStr = new StringBuilder();

                for (int i = 2; i < entryParts.length - 2; i++) {
                    if (i < entryParts.length - 3) {
                        strBuild.append(entryParts[i]).append(" ");
                    } else {
                        dateStr.append(entryParts[entryParts.length - 2]);
                    }
                }
                LocalDateTime deadlineDate = LocalDateTime.parse(dateStr.toString());
                Deadline deadlineTask = new Deadline(strBuild.toString().trim(), deadlineDate);
                if (entryParts[1].equals("1")) {
                    deadlineTask.mark();
                }
                itemsList.add(deadlineTask);
            } else if (entryParts[0].equals("E")) {
                StringBuilder strBuild = new StringBuilder();
                StringBuilder toStr = new StringBuilder();
                StringBuilder forStr = new StringBuilder();

                for (int i = 2; i < entryParts.length; i++) {
                    if (i < entryParts.length - 5) {
                        strBuild.append(entryParts[i]).append(" ");
                    } else if (i > entryParts.length - 5 && i < entryParts.length - 3) {
                        forStr.append(entryParts[i]).append(" ");
                    } else if (i > entryParts.length - 3 && i < entryParts.length - 1) {
                        toStr.append(entryParts[i]);
                    }
                }

                LocalDateTime fromDate = LocalDateTime.parse(forStr.toString().trim());
                LocalDateTime toDate = LocalDateTime.parse(toStr.toString());
                Event eventTask = new Event(strBuild.toString(), fromDate, toDate);
                if (entryParts[1].equals("1")) {
                    eventTask.mark();
                }
                itemsList.add(eventTask);
            }
        }
        return itemsList;
    }
}
