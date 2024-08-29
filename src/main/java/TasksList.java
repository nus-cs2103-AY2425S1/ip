import java.util.ArrayList;
import java.util.List;

public class TasksList {
    private final List<Task> TASKS_LIST;

    public TasksList() {
        this.TASKS_LIST = new ArrayList<>();
    }

    public String addToTaskList(Task task) {
        this.TASKS_LIST.add(task);
        return task.toString();
    }

    public void displayTasksList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.TASKS_LIST.size(); i++) {
            System.out.println(i + 1 + ". " + this.TASKS_LIST.get(i));
        }
    }

    public List<Task> getTasksList() {
        return List.copyOf(this.TASKS_LIST);
    }

    public int getSize() {
        return this.TASKS_LIST.size();
    }

    public void deleteTask(int taskNumber) throws TaskDoesNotExist {
        if (taskNumber <= 0 || taskNumber > this.TASKS_LIST.size()) {
            throw new TaskDoesNotExist();
        }
        Task taskDeleted = this.TASKS_LIST.remove(taskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + taskDeleted);
        System.out.println("Now you have " + this.TASKS_LIST.size() + " tasks in the list.");
    }

    public void markTask(int taskNumber) throws TaskDoesNotExist {
        if (taskNumber <= 0 || taskNumber > this.TASKS_LIST.size()) {
            throw new TaskDoesNotExist();
        }
        this.TASKS_LIST.get(taskNumber - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + this.TASKS_LIST.get(taskNumber - 1));
    }

    public void unmarkTask(int taskNumber) throws TaskDoesNotExist {
        if (taskNumber <= 0 || taskNumber > this.TASKS_LIST.size()) {
            throw new TaskDoesNotExist();
        }
        this.TASKS_LIST.get(taskNumber - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + this.TASKS_LIST.get(taskNumber - 1));
    }
}
