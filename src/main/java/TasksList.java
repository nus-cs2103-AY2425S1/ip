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
        UI.printMessageToConsole("Here are the tasks in your list:");
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
        UI.printMessageToConsole("Noted. I've removed this task:");
        UI.printMessageToConsole(" " + taskDeleted);
        UI.printMessageToConsole("Now you have " + this.TASKS_LIST.size() + " tasks in the list.");
    }

    public void markTask(int taskNumber) throws TaskDoesNotExist {
        if (taskNumber <= 0 || taskNumber > this.TASKS_LIST.size()) {
            throw new TaskDoesNotExist();
        }
        this.TASKS_LIST.get(taskNumber - 1).mark();
        UI.printMessageToConsole("Nice! I've marked this task as done:");
        UI.printMessageToConsole(" " + this.TASKS_LIST.get(taskNumber - 1));
    }

    public void unmarkTask(int taskNumber) throws TaskDoesNotExist {
        if (taskNumber <= 0 || taskNumber > this.TASKS_LIST.size()) {
            throw new TaskDoesNotExist();
        }
        this.TASKS_LIST.get(taskNumber - 1).unmark();
        UI.printMessageToConsole("OK, I've marked this task as not done yet:");
        UI.printMessageToConsole(" " + this.TASKS_LIST.get(taskNumber - 1));
    }
}
