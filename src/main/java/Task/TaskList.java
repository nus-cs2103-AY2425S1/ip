package Task;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class TaskList {

    public static final TaskList mainTaskList = new TaskList();
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public int getNumTasks() {
        return this.tasks.size();
    }

    public void printList() {
        int idx = 1;
        System.out.println();
        for (Task task : this.tasks) {
            System.out.printf("     %s. %s\n", idx, task.toString());
            idx++;
        }
        System.out.println();
    }

    public TaskList tasksContainingTerm(String term) {
        TaskList tasksContainingTermList = new TaskList();
        for (Task task : this.tasks) {
            if (task.containsTerm(term)) {
                tasksContainingTermList.addTask(task);
            }
        }
        return tasksContainingTermList;
    }

    public void markTask(int i) {
        this.tasks.get(i).done();
    }

    public void unmarkTask(int i) {
        this.tasks.get(i).undone();
    }

    public void deleteTask(int i) {
        this.tasks.remove(i);
    }

    public String getTaskFileFormat(int i) {
        return tasks.get(i).saveFileFormat();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }
}
