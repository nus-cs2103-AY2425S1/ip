import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;
    private final Storage storage;

    public TaskList(String fileName) {
        this.tasks = new ArrayList<>();
        this.storage = new Storage("data", fileName);
        this.taskCount = 0;

        try {
            this.loadTasks();
        } catch (DataIOException | InvalidDataFormatException e) {
            this.tasks.clear();
            this.taskCount = 0;
        }
    }

    public Task markAsDone(int taskNumber) throws DataIOException, InvalidCommandException {
        this.isValidTaskNumberCheck(taskNumber);
        this.tasks.get(taskNumber - 1).markAsDone();
        this.saveTasks();
        return this.tasks.get(taskNumber - 1);
    }

    public Task markAsNotDone(int taskNumber) throws DataIOException, InvalidCommandException {
        this.isValidTaskNumberCheck(taskNumber);
        this.tasks.get(taskNumber - 1).markAsNotDone();
        this.saveTasks();
        return this.tasks.get(taskNumber - 1);
    }

    private void isValidTaskNumberCheck(int taskNumber) throws InvalidCommandException {
        if (!this.isValidTaskNumber(taskNumber)) {
            throw new InvalidCommandException("OOPS!!! The task number is invalid.");
        }
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= this.taskCount;
    }

    public boolean isEmpty() {
        return this.taskCount == 0;
    }

    public void addTask(Task task) throws DataIOException {
        this.tasks.add(task);
        this.taskCount++;
        this.saveTasks();
    }

    public Task removeTask(int taskNumber) throws DataIOException, InvalidCommandException {
        this.isValidTaskNumberCheck(taskNumber);

        Task task = this.tasks.get(taskNumber - 1);
        this.taskCount--;

        for (int i = taskNumber - 1; i < this.taskCount; i++) {
            this.tasks.set(i, this.tasks.get(i + 1));
        }

        this.tasks.remove(this.taskCount);
        this.saveTasks();

        return task;
    }

    @Override
    public String toString() {
        StringBuilder tasksStr = new StringBuilder();

        for (int i = 0; i < this.taskCount; i++) {
            if (i == this.taskCount - 1) {
                tasksStr.append((i + 1)).append(". ").append(this.tasks.get(i));
            } else {
                tasksStr.append((i + 1)).append(". ").append(this.tasks.get(i)).append("\n");
            }
        }

        return tasksStr.toString();
    }

    public String getTaskCount() {
        return "Now you have " + this.taskCount + " tasks in the list.";
    }

    private String getSimpleTaskList() {
        StringBuilder tasksStr = new StringBuilder();

        for (int i = 0; i < this.taskCount; i++) {
            if (i == this.taskCount - 1) {
                tasksStr.append(this.tasks.get(i).simpleFormat());
            } else {
                tasksStr.append(this.tasks.get(i).simpleFormat()).append("\n");
            }
        }

        return tasksStr.toString();
    }

    private void saveTasks() throws DataIOException {
        this.storage.saveTasks(this.getSimpleTaskList());
    }

    private void loadTasks() throws DataIOException, InvalidDataFormatException {
        this.tasks = this.storage.loadTasks();
        this.taskCount = tasks.size();
    }
}
