package slave;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import slave.task.Deadline;
import slave.task.Event;
import slave.task.RecurringTypeTask;
import slave.task.Task;
import slave.task.Todo;

/**
 * An object containing all the method
 * Contains a reference to the taskList as well as the address of the save file
 */
public class Storage {
    private String filePath;
    private List<Task> list;

    /**
     * @param list     is the list of tasks which the user is editing
     * @param filePath is the address of the text file to which any changes are saved to
     */
    public Storage(List<Task> list, String filePath) {
        this.list = list;
        this.filePath = filePath;
    }

    /**
     * Converts the task list to a string format and writes it to the savefile "./src/main/data/savefile.txt".
     * Every line contains only 1 task.
     * String format is as per the return value of toString() method of the respective task.
     * Creates a new file at "./src/main/data" called "savefile.txt" in the event of a missing save file.
     */
    public void save() {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task t : list) {
                sb.append(t.saveFormat());
                sb.append("\n");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException ioe) {
            System.out.println("Save failed");
            System.out.println(ioe.getMessage());
        }
    }

    /**
     * Attempts to load the pre saved tasks from the save file "./src/main/data/savefile.txt".
     * If file does not exist at the path, does nothing.
     * In the event of corrupted save file, skips the line.
     * Save file format: as per toString() function of each task, with one task per line.
     */
    public void load() {
        try {
            Scanner sc = new Scanner(new File(filePath));
            int success = 0;
            int failed = 0;
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                if (loadSingleTask(task)) {
                    success++;
                } else {
                    failed++;
                }
            }
            if (failed > 0) {
                System.out.println("Deleting buggy save file");
                deleteSaveFile();
            }
            if (success > 0) {
                save();
            }
        } catch (FileNotFoundException fnfe) {
            createSaveFile();
        }
    }

    /**
     * Loads a single task from the save file into the task list.
     * Formats for tasks are as follows:
     * Todo:
     * (Task type),(isCompleted),(Task name)
     * Deadline:
     * (Task type),(isCompleted),(recurring type),(Task name),(deadline)
     * Event:
     * (Task type),(isCompleted),(recurring type),(Task name),(startDate),(endDate)
     *
     * @param task is the string representation of the task.
     * @return a boolean indicating whether the task is successfully loaded.
     */
    protected boolean loadSingleTask(String task) {
        try {
            String[] taskInfo = task.split(",");
            if (!taskInfo[1].equals("true") && !taskInfo[1].equals("false")) {
                // to check that isCompleted is a valid boolean string
                throw new IllegalArgumentException();
            }
            boolean isCompleted = Boolean.parseBoolean(taskInfo[1]);

            // identify the type of task:
            switch (taskInfo[0]) {
            // all non-RecurringTypeTasks listed first
            case "todo":
                list.add(new Todo(taskInfo[2], isCompleted));
                return true;
            // all RecurringTypeTasks listed from here on
            case "deadline":
                list.add(new Deadline(taskInfo[3], isCompleted, checkTaskRecurringStatus(taskInfo[2]),
                        LocalDate.parse(taskInfo[4])));
                return true;
            case "event":
                LocalDate start = LocalDate.parse(taskInfo[4]);
                LocalDate end = LocalDate.parse(taskInfo[5]);
                list.add(new Event(taskInfo[3], isCompleted, checkTaskRecurringStatus(taskInfo[2]), start, end));
                return true;
            default:
                return false;
            }
        } catch (DateTimeParseException | IndexOutOfBoundsException | InvalidChronologicalOrderException
                 | IllegalArgumentException e) {
            return false;
        }
    }

    private RecurringTypeTask.RecurringType checkTaskRecurringStatus(String recurringType)
            throws IllegalArgumentException {
        System.out.println(recurringType);
        switch (recurringType.toLowerCase()) {
        case "never":
            return RecurringTypeTask.RecurringType.NEVER;
        case "daily":
            return RecurringTypeTask.RecurringType.DAILY;
        case "weekly":
            return RecurringTypeTask.RecurringType.WEEKLY;
        case "bimonthly":
            return RecurringTypeTask.RecurringType.BIMONTHLY;
        case "monthly":
            return RecurringTypeTask.RecurringType.MONTHLY;
        case "quarterly":
            return RecurringTypeTask.RecurringType.QUARTERLY;
        case "biannually":
            return RecurringTypeTask.RecurringType.BIANNUALLY;
        case "annually":
            return RecurringTypeTask.RecurringType.ANNUALLY;
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Deletes the save file at the specified file path.
     */
    protected void deleteSaveFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("");
            writer.close();
            System.out.println("Buggy save file deleted");
        } catch (IOException e) {
            System.out.println("Failed to purge buggy save file");
        }
    }

    /**
     * Creates a save file at the specified file path.
     */
    protected void createSaveFile() {
        System.out.println("no save file found, creating new save file");
        Path saveFilePath = Paths.get(filePath);
        Path directory = saveFilePath.getParent();
        //@@author mkyong -reused
        // source: https://mkyong.com/java/how-to-create-directory-in-java/
        // code for creating directory reused from the tutorial website above
        try {
            //@@author Baeldung -reused
            // source: https://www.baeldung.com/java-file-directory-exists
            // code for checking if directories and files exist reused from website above
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
                System.out.println("Creating directory: " + directory);
            }
            if (!Files.exists(saveFilePath)) {
                //@@author tutorialspoint -reused
                // source: https://www.tutorialspoint.com/javaexamples/file_dir.htm
                // code for creating files using Files.createFile() reused from website
                Files.createFile(saveFilePath);
                System.out.println("Creating file: " + saveFilePath.getFileName());
                //@@author
            }
            //@@author
            System.out.println("Save file created at " + saveFilePath);
        } catch (IOException e) {
            System.out.println("Error in creating file / directory: " + e.getMessage());
        }
        //@@author
    }
}
