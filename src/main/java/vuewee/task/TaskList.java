package vuewee.task;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList() {
    }

    public static TaskList deserialize(String str) {
        TaskList taskList = new TaskList();
        String[] taskStrings = str.split("\n");
        for (String taskString : taskStrings) {
            if (taskString.trim().isEmpty()) {
                continue;
            }
            String[] parts = taskString.split(Pattern.quote(Task.DELIMETER_SPACE), 2);
            TaskType taskType = TaskType.fromChar(parts[0].charAt(0));
            Task task = taskType.createTask(parts[1]);
            taskList.add(task);
        }
        return taskList;
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.tasks) {
            sb.append(task.serialize());
            sb.append("\n");
        }
        return sb.toString();
    }

    public int size() {
        return this.tasks.size();
    }

    // Add a task to the list
    public void add(Task task) {
        this.tasks.add(task);
    }

    // Get a task from the list at the specified index (0-based)
    public Task get(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index);
    }

    // Delete a task from the list at the specified index (0-based)
    public void remove(int taskNumber) throws IndexOutOfBoundsException {
        this.tasks.remove(taskNumber);
    }

    // Mark a task as done or not done
    public boolean markTask(int taskNumber, boolean isDone)
            throws IndexOutOfBoundsException {
        if (taskNumber >= this.tasks.size() || taskNumber < 0) {
            throw new IndexOutOfBoundsException();
        }

        Task task = this.tasks.get(taskNumber);
        if (isDone) {
            boolean success = task.markAsDone();
            return success;
        } else {
            boolean success = task.markAsUndone();
            return success;
        }
    }
}
