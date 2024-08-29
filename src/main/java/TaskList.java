import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.List;

import exceptions.UsageException;

public class TaskList {
    private static final ArrayList<Task> LIST = new ArrayList<>();
    private static final String[] dirPathParts = { "..", "..", "..", "store" };
    private static final String[] filePathParts = { "..", "..", "..", "store", "tasks.csv" };
    private final File SAVE_DIR = new File(String.join(File.separator, dirPathParts));
    private static final CSVHandler CSV_HANDLER = new CSVHandler(
            new File(String.join(File.separator, filePathParts)));

    public void addTask(Task task) {
        LIST.add(task);
        updateTaskList();
    }

    public Task getItem(int label) {
        return LIST.get(label - 1);
    }

    public void removeItem(int label) {
        LIST.remove(label - 1);
        updateTaskList();
    }

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

    public void getTaskList() {
        try {
            List<List<String>> records = CSV_HANDLER.getRecords();
            for (List<String> record : records) {
                String taskType = record.get(0);
                Boolean isDone = Boolean.valueOf(record.get(1));
                String description = record.get(2);
                Task task = null;

                if (taskType.equals("T")) {
                    task = new Todo(description);
                } else if (taskType.equals("D")) {
                    task = new Deadline(description, record.get(3));
                } else if (taskType.equals("E")) {
                    task = new Event(description, record.get(3), record.get(4));
                }

                if (isDone) {
                    task.markAsDone();
                }
                addTask(task);
            }
        } catch (IOException e) {
            System.out.println("File read error!");
        } catch (UsageException e) {
            System.out.println("File format error!");
        }
    }

    public void updateTaskList() {
        List<String> records = new ArrayList<>();

        for (Task task : LIST) {
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

}
