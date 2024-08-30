package twilight;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public String mark(int taskNum) {
        tasks.get(taskNum).setDone();
        return tasks.get(taskNum).toString();
    }

    public String unmark(int taskNum) {
        tasks.get(taskNum).SetUndone();
        return tasks.get(taskNum).toString();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public String add(Task t) {
        tasks.add(t);
        return "added: " + tasks.get(tasks.size() - 1).toString() + "\n" + "There are " + tasks.size() + " tasks in the list";
    }

    public String delete(int taskNum) throws InvalidInputException {
        try {
            tasks.remove(taskNum);
            return "The task has been successfully removed leaving: " + tasks.size() + " tasks";
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Task " + (taskNum + 1) + " does not exist");
        }
    }
}

