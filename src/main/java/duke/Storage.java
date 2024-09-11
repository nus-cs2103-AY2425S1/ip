package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
/**
 * Handles the storage functions of the Duck chatbot.
 */
public class Storage {
    private static final String PATH = "./data/duke.txt";

    private static final int TASK_LIST_SIZE = 100;
    private static final int TASK_PART_TYPE= 0;
    private static final int TASK_PART_STATUS= 1;
    private static final int TASK_PART_DESCRIPTION = 2;
    private static final int TASK_PART_START = 3;
    private static final int TASK_PART_END = 4;
    private static final String TASK_STATUS_MARKED = "1";
    private static final String TASK_STATUS_UNMARKED = "0";
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";

    public Storage() {
    }

    /**
     * Loads all previous tasks that user has input before.
     *
     * @return Array of all previous tasks.
     * @throws DuckException if file is corrupted.
     * */
    public static Task[] load() throws DuckException {
        File file = new File(PATH);
        Task[] tasks;
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return new Task[TASK_LIST_SIZE];
            } else {
                List<String> taskList = Files.readAllLines(Paths.get(PATH));
                int numTasks = 0;
                if (!taskList.isEmpty()) {
                    numTasks = Integer.parseInt(String.valueOf(taskList.get(0)));
                }
                assert taskList.size() == numTasks: "Size of saved task list does not match the number of saved tasks";
                tasks = new Task[TASK_LIST_SIZE];
                for (int i = 1; i<numTasks + 1; i++) {
                    tasks[i - 1] = parser(taskList.get(i));
                }
                return tasks;
            }
        } catch (IOException e) {
            throw new DuckException("Cannot load tasks.");
        }
    }

    /**
     * Loads total number of previous tasks that user has input before.
     *
     * @return Integer total of number of previous tasks.
     * @throws DuckException if file is corrupted.
     * */
    public static int loadNum() throws DuckException {
        File file = new File(PATH);
        try {
            if (!file.exists()) {
                return 0;
            } else {
                List<String> taskList = Files.readAllLines(Paths.get(PATH));
                int numTasks = 0;
                if (!taskList.isEmpty()) {
                    numTasks = Integer.parseInt(String.valueOf(taskList.get(0)));
                }
                return numTasks;
            }
        } catch (IOException e) {
            throw new DuckException("Cannot load number of tasks.");
        }
    }

    /**
     * Saves current list of tasks after user has made his or her changes.
     *
     * @param tasks Current list of all tasks to be saved.
     * @param n Number of tasks in the list to be saved.
     * @throws DuckException if the list is not formatted correctly.
     * */
    public static void save(Task[] tasks, int n) throws DuckException {
        try {
            FileWriter writer = new FileWriter(PATH);
            writer.write(n + "\n");
            for (int i = 0; i < n; i++) {
                writer.write(saveTask(tasks[i]) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DuckException("Cannot save tasks.");
        }
    }

    /**
     * Parses through a line from the Storage file to convert it from String to its corresponding Task.
     *
     * @param line Line to be parsed through.
     * @return Task that the input line corresponds to.
     * @throws DuckException if the line is not in a recognisable format.
     * */
    private static Task parser(String line) throws DuckException {
        String[] parts = line.split(" \\| ");
        if (Objects.equals(parts[TASK_PART_TYPE], TASK_TYPE_TODO)) {
            Task t = new Todo(parts[TASK_PART_DESCRIPTION]);
            if (parts[TASK_PART_STATUS].equals(TASK_STATUS_MARKED)) {
                t.mark();
            }
            return t;
        } else if (Objects.equals(parts[TASK_PART_TYPE], TASK_TYPE_DEADLINE)) {
            Task t = new Deadline(parts[TASK_PART_DESCRIPTION], parts[TASK_PART_START]);
            if (parts[TASK_PART_STATUS].equals(TASK_STATUS_MARKED)) {
                t.mark();
            }
            return t;
        } else if (Objects.equals(parts[TASK_PART_TYPE], TASK_TYPE_EVENT)) {
            Task t = new Event(parts[TASK_PART_DESCRIPTION], parts[TASK_PART_START], parts[TASK_PART_END]);
            if (parts[TASK_PART_STATUS].equals(TASK_STATUS_MARKED)) {
                t.mark();
            }
            return t;
        } else {
            throw new DuckException("Unrecognised file type.");
        }
    }
    /**
     * Saves a given task in specified format.
     *
     * @param task Task item to be saved.
     * @return String representation of task to be saved.
     * */
    private static String saveTask(Task task) {
        String done = TASK_STATUS_UNMARKED;
        if (task.isDone) {
            done = TASK_STATUS_MARKED;
        }
        String type = "";
        if (task instanceof Todo) {
            type = TASK_TYPE_TODO;
        } else if (task instanceof Deadline) {
            type = TASK_TYPE_DEADLINE;
        } else if (task instanceof Event) {
            type = TASK_TYPE_EVENT;
        }
        return type + " | " + done + " | " + task.description + task.getDates();
    }
}