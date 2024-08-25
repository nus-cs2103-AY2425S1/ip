import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TASKS;

    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    public Task addTask(Command type, String[] response) {
        Task task = new Task(response[0]);
        switch (type) {
            case TODO:
                task = new Todo(response[1]);
                break;
            case DEADLINE:
                // response[0] = description, response[1] = deadline
                task = new Deadline(response[0], response[1].strip());
                break;
            case EVENT:
                String[] times = response[1].split("/to ");
                task = new Event(response[0], times[0].strip(), times[1].strip());
                break;
            default:
                break;
        }
        TASKS.add(task);
        return task;
    }

    public void addTask(Task task) {
        TASKS.add(task);
    }

    public Task markTask(int taskNumber) throws InvalidTaskException{
        if (taskNumber > TASKS.size()) {
            throw new InvalidTaskException("", taskNumber);
        }
        Task markTask = TASKS.get(taskNumber - 1);
        markTask.markAsDone();
        return markTask;
    }

    public Task unmarkTask(int taskNumber) throws InvalidTaskException{
        if (taskNumber > TASKS.size()) {
            throw new InvalidTaskException("", taskNumber);
        }
        Task unmarkTask = TASKS.get(taskNumber - 1);
        unmarkTask.markAsUndone();
        return unmarkTask;
    }

    public Task deleteTask(int taskNumber) throws InvalidTaskException{
        if (taskNumber > TASKS.size()) {
            throw new InvalidTaskException("", taskNumber);
        }
        return TASKS.remove(taskNumber - 1);
    }

    public int getSize() {
        return this.TASKS.size();
    }

    public String listTasks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < TASKS.size(); i++) {
            String taskInfo = String.format("%d. %s", i + 1, TASKS.get(i).toString());
            s.append(taskInfo).append("\n");
        }
        return s.toString();
    }

    public Task getTask(int index) {
        return TASKS.get(index);
    }
}
