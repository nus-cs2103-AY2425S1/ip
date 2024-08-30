package snowy.tasklist;

import snowy.data.SnowyException;
import snowy.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        taskList = new ArrayList<>();

    }

    public void addTask(Task t) {
        taskList.add(t);
        String str = String.format(" %s\nNow you have %d task(s) in your list\n", t, this.taskList.size());
        System.out.println(str);
        storage.writeTaskToFile(t);
    }

    public boolean isTaskDone(int i) {
        Task task = taskList.get(i);
        return task.isDone;
    }

    public void toggleTask(int i) throws SnowyException {
        if (i >= 0 && i < taskList.size()) {
            Task task = taskList.get(i);
            task.toggleStatus();
            String str = String.format("%d. %s", i + 1, task);
            System.out.println(str);
        } else {
            throw new SnowyException("Invalid index.");
        }
    }

    public void displayList() throws SnowyException {
        if (taskList.isEmpty()) {
            throw new SnowyException("No tasks, make a list first.");
        }
        System.out.println("Your list of tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            String str = String.format("%d. %s", i + 1, taskList.get(i));
            System.out.println(str);
        }
    }

    public void deleteTask(int index) throws SnowyException {
        if (taskList.isEmpty()) {
            throw new SnowyException("No tasks in list.");
        }
        if (index >= 1 && index <= taskList.size()) {
            Task task = taskList.get(index - 1);
            System.out.println("Removed task:\n " + task);
            taskList.remove(index - 1);
            storage.deleteTaskFromFile(task);
            System.out.printf("Now you have %d task(s) in your list.\n", this.taskList.size());
        } else {
            throw new SnowyException("Invalid index.");
        }
    }
}
