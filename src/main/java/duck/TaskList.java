package duck;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duck.exceptions.BeforeEarliestTimeException;
import duck.tasks.DateAndTime;
import duck.tasks.Deadline;
import duck.tasks.DoAfter;
import duck.tasks.Event;
import duck.tasks.Task;
import duck.tasks.Todo;

/**
 * Class representing a list of tasks.
 */
public class TaskList {
    // Path relative to project directory
    private static final String[] DIR_PATH_PARTS = { "store" };
    private static final String[] FILE_PATH_PARTS = { "store", "tasks.csv" };
    private static final String SAVE_DIR_PATH = String.join(File.separator, DIR_PATH_PARTS);
    private static final String SAVE_FILE_PATH = String.join(File.separator, FILE_PATH_PARTS);
    private static final File SAVE_DIR = new File(SAVE_DIR_PATH);
    private static final File SAVE_FILE = new File(SAVE_FILE_PATH);
    private static final Storage CSV_HANDLER = new Storage(SAVE_FILE);

    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param task
     */
    public void addTask(Task task) {
        tasks.add(task);
        updateFileWithTaskList();
    }

    /**
     * Gets the task with the given label.
     *
     * @param label
     * @return task with the given label
     * @throws IndexOutOfBoundsException
     */
    public Task getItem(int label) throws IndexOutOfBoundsException {
        return tasks.get(label - 1);
    }

    /**
     * Removes the task with the given label.
     *
     * @param label
     */
    public void removeItem(int label) {
        tasks.remove(label - 1);
        updateFileWithTaskList();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return task count
     */
    public int getTaskCount() {
        return tasks.size();
    }

    private Task createTaskFromRecord(List<String> record) throws BeforeEarliestTimeException {
        assert record.size() > 2;
        String taskType = record.get(0);
        Boolean isDone = Boolean.valueOf(record.get(1));
        String description = record.get(2);
        Task task = null;

        if (taskType.equals("T")) {
            assert record.size() == 3;
            task = new Todo(description);
        } else if (taskType.equals("D")) {
            assert record.size() == 4;
            task = new Deadline(description, new DateAndTime(record.get(3)));
        } else if (taskType.equals("E")) {
            assert record.size() == 5;
            task = new Event(description,
                    new DateAndTime(record.get(3)), new DateAndTime(record.get(4)));
        } else if (taskType.equals("A")) {
            assert record.size() == 4;
            task = new DoAfter(description, new DateAndTime(record.get(3)));
        }

        assert task != null;
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Loads the task list from the data file.
     */
    public void getTaskListFromFile() {
        try {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            List<List<String>> records = CSV_HANDLER.getRecords();
            for (List<String> record : records) {
                Task task = createTaskFromRecord(record);
                addTask(task);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("File read error!");
        } catch (BeforeEarliestTimeException e) {
            System.out.println("DoAfter task wrongly marked as done in file!");
        }
    }

    /**
     * Updates the data file with the current task list.
     */
    public void updateFileWithTaskList() {
        List<String> records = new ArrayList<>();

        for (Task task : tasks) {
            records.add(task.getSaveString());
        }

        try {
            if (!SAVE_DIR.exists()) {
                SAVE_DIR.mkdirs();
            }
            CSV_HANDLER.updateRecordsWithStrings(records);
        } catch (IOException e) {
            System.out.println("File write error!");
        }
    }

    /**
     * Gets a list of tasks whose string representations contain a pattern.
     */
    public TaskList filterTasksByPattern(String pattern) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(pattern)) {
                filteredTasks.addTask(task);
            }
        }
        return filteredTasks;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.valueOf(i + 1))
                    .append(". ")
                    .append(tasks.get(i).toString())
                    .append("\n");
        }
        return output.toString();
    }
}
