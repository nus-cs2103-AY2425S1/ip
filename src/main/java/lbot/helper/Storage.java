package lbot.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lbot.exception.FileException;
import lbot.exception.ParseCommandException;
import lbot.task.Deadline;
import lbot.task.Event;
import lbot.task.Task;
import lbot.task.Todo;

/**
 * This class handles the I/O of tasks to the specified file.
 */
public class Storage {
    private static final DateTimeFormatter dateTimeFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // allows user to specify time as well
    private final String path;

    /**
     * Constructor for Storage.
     * Creates folder and file for reading.
     *
     * @param path location of text file.
     * @throws FileException thrown when file cannot be created.
     */
    public Storage(String path) throws FileException {
        this.path = path;
        File file = new File(this.path);
        File parent = file.getParentFile();
        // Create folder if it does not exist
        if (!parent.exists()) {
            parent.mkdirs();
        }
        // create file if it does not exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new FileException(e.getMessage());
            }
        }
    }

    /**
     * Reads and adds tasks into TaskList.
     *
     * @return Arraylist of tasks
     * @throws FileException
     */
    public ArrayList<Task> readTasksFromFile() throws FileException {
        TaskList taskList = new TaskList();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {
            while ((line = br.readLine()) != null) {
                addTaskToList(line, taskList);
            }
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }
        return taskList.getTaskList();
    }

    /**
     * Adds tasks read from text file.
     *
     * @param input    line read from file.
     * @param taskList for task to be added into.
     * @throws ParseCommandException   thrown when error encountered while parsing line.
     */
    public static void addTaskToList(String input, TaskList taskList)
            throws ParseCommandException {
        // Code adapted from ChatGPT, using regex to possibly avoid scenario where user uses reserved characters
        // regex updated after testing on regex101.com
        String regex = "\\[([TED])]\\[(x|\\s?)] (.*?)(?: \\(from: ([^)]*?) to: ([^)]*?)\\)| \\(by: ([^)]*?)\\))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String type = matcher.group(1);
            boolean isComplete = Objects.equals(matcher.group(2), "x");
            String description = matcher.group(3);

            // Conditional captures for event/deadline
            String start = matcher.group(4); // start datetime (matches from:...)
            String end = matcher.group(5); // end datetime (matches to:...)
            String dueDate = matcher.group(6); // due datetime (matches by:...)

            switch (type) {
            case "T":
                taskList.addTask(new Todo(description, isComplete));
                break;
            case "D":
                taskList.addTask(new Deadline(description, isComplete,
                        LocalDateTime.parse(dueDate, dateTimeFormat)));
                break;
            case "E":
                taskList.addTask(new Event(description, isComplete,
                        LocalDateTime.parse(start, dateTimeFormat), LocalDateTime.parse(end, dateTimeFormat)));
                break;
            default:
                throw new ParseCommandException("Error reading file. Recreating task file.");
            }
        }
    }

    /**
     * Rewrites entire text file.
     *
     * @param taskList contains tasks to be written into file.
     * @throws FileException thrown when error encountered writing into file.
     */
    public void saveTaskToFile(TaskList taskList) throws FileException {
        // credits to https://github.com/nus-cs2103-AY2425S1/forum/issues/86#issuecomment-2313881167
        try (BufferedWriter br = new BufferedWriter(new FileWriter(this.path, false))) {
            for (Task task : taskList.getTaskList()) {
                br.write(task.toString());
                br.newLine();
            }
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }
}
