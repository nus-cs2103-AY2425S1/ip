package chatBot.bot;

import chatBot.task.Task;

import java.util.ArrayList;

/** TaskList serves the purpose of where all the Task is being stored
 * @param tasks - there are previous tasks stored in ./data and is retreived.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /** Create String object for storing back into file in Storage class */
    public String getTaskCommands() {
        String s = "";
        for (Task t : this.tasks) {
            s += t.getOriginalCommand() + "\n";
        }
        return s;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void listTasks() {
        String s = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            s += String.format("%d.", i + 1) +  this.tasks.get(i) + "\n";
        }
        System.out.println(s.stripTrailing());
    }

    public String getTaskToString(int index) {
        return this.tasks.get(index).toString();
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }
}
