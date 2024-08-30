import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exceptions.UsageException;
import tasks.DateAndTime;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class TaskList {
    private static final ArrayList<Task> LIST = new ArrayList<>();
    // TODO: Refactor this
    // Path relative to project directory
    private static final String[] dirPathParts = { "store" };
    private static final String[] filePathParts = { "store", "tasks.csv" };
    private final File saveDir = new File(String.join(File.separator, dirPathParts));
    private static final Storage CSV_HANDLER = new Storage(
            new File(String.join(File.separator, filePathParts)));

    /**
     * Adds a task to the list.
     *
     * @param task
     */
    public void addTask(Task task) {
        LIST.add(task);
        updateFileWithTaskList();
    }

    /**
     * Gets the task with the given label.
     *
     * @param label
     * @return task with the given label
     */
    public Task getItem(int label) {
        return LIST.get(label - 1);
    }

    /**
     * Removes the task with the given label.
     *
     * @param label
     */
    public void removeItem(int label) {
        LIST.remove(label - 1);
        updateFileWithTaskList();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return task count
     */
    public int getTaskCount() {
        return LIST.size();
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < LIST.size(); i++) {
            output.append(String.valueOf(i + 1))
                    .append(". ")
                    .append(LIST.get(i).toString())
                    .append("\n");
        }
        return output.toString();
    }

    /**
     * Loads the task list from the data file.
     */
    public void getTaskListFromFile() {
        try {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));

            List<List<String>> records = CSV_HANDLER.getRecords();
            for (List<String> record : records) {
                String taskType = record.get(0);
                Boolean isDone = Boolean.valueOf(record.get(1));
                String description = record.get(2);
                Task task = null;

                if (taskType.equals("T")) {
                    task = new Todo(description);
                } else if (taskType.equals("D")) {
                    task = new Deadline(description, new DateAndTime(record.get(3)));
                } else if (taskType.equals("E")) {
                    task = new Event(description,
                            new DateAndTime(record.get(3)), new DateAndTime(record.get(3)));
                }

                if (isDone) {
                    task.markAsDone();
                }
                addTask(task);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("File read error!");
        } catch (UsageException e) {
            System.out.println("File format error!");
        }
    }

    /**
     * Updates the data file with the current task list.
     */
    public void updateFileWithTaskList() {
        List<String> records = new ArrayList<>();

        for (Task task : LIST) {
            records.add(task.getSaveString());
        }

        try {
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            CSV_HANDLER.updateRecordsWithStrings(records);
        } catch (IOException e) {
            System.out.println("File write error!");
        }
    }

}
