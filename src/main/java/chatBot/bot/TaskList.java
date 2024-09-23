package chatbot.bot;

import java.util.ArrayList;

import chatbot.exception.TaskAlreadyAddedException;
import chatbot.task.Task;

/** TaskList serves the purpose of storing tasks */
public class TaskList {
    private ArrayList<Task> tasks;

    /** @param tasks - obtained from storage.load(), which is essentially retrieving tasks from past data */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /** Adds a new Task to this.tasks */
    public void addTask(Task task) throws TaskAlreadyAddedException {
        boolean matchingTask = this.tasks.stream()
                .anyMatch(t -> t.equals(task));
        if (matchingTask) {
            throw new TaskAlreadyAddedException(task);
        }
        this.tasks.add(task);
    }

    /** Creates String object for storing back into file in Storage class */
    public String getTaskCommands() {
        String s = "";
        for (Task t : this.tasks) {
            s += t.getOriginalCommand() + "\n";
        }
        return s;
    }

    /** Returns size of this.tasks */
    public int size() {
        return this.tasks.size();
    }

    /** Returns the task at the specified index */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /** Prints the tasks which is relayed by ListCommand */
    public String listTasks() {
        String s = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            s += String.format("%d.", i + 1) + this.tasks.get(i) + "\n";
        }
        System.out.println(s.stripTrailing());
        return s;
    }

    /** Returns the toString() of the task at the specified index */
    public String getTaskToString(int index) {
        return this.tasks.get(index).toString();
    }

    /** Removes the task at the specified index from this.tasks */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    /** Returns ArrayList of tasksFound that contains the keyword */
    public ArrayList<Task> findKeyword(String s) {
        ArrayList<Task> tasksFound = new ArrayList<Task>();
        for (Task t : this.tasks) {
            if (t.contains(s)) {
                tasksFound.add(t);
            }
        }
        return tasksFound;
    }
}
