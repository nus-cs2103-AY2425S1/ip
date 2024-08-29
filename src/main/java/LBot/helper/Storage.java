package LBot.helper;

import LBot.exception.FileException;
import LBot.exception.InvalidCommandException;
import LBot.exception.ParseCommandException;
import LBot.task.Deadline;
import LBot.task.Event;
import LBot.task.Task;
import LBot.task.Todo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    private final String path;
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"); // allows user to specify time as well


    /**
     * Constructor for Storage. Creates folder and file for reading.
     *
     * @param path
     * @throws FileException
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

    public static void addTaskToList(String input, TaskList taskList) throws ParseCommandException, InvalidCommandException {
        /**
         * Code adapted from ChatGPT, using regex to possibly avoid scenario where user uses reserved characters
         * regex updated after testing on regex101.com
         * Prompt used:
         * Can you build me a regex string to detect the following pattern in java
         * [T][x] Task
         * [T][ ] Task
         * [E][x] Event (from: 12/08/2024 to: 13/08/2024)
         * [E][x] Event (from: Friday to: Friday)
         * [E][ ] Event (from: 12/08/2024 to: 13/08/2024)
         * [E][ ] Event (from: Friday to: Friday)
         * [D][x] Deadline (by: 11)
         * [D][ ] Deadline (by: 12/08/1203)
         */
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
                    taskList.addTask(new Deadline(description, isComplete, LocalDateTime.parse(dueDate, dateTimeFormat)));
                    break;
                case "E":
                    taskList.addTask(new Event(description, isComplete, LocalDateTime.parse(start, dateTimeFormat), LocalDateTime.parse(end, dateTimeFormat)));
                    break;
                default:
                    throw new ParseCommandException("Error reading file. Recreating task file.");
            }
        }
    }

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
