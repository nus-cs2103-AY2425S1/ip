package task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private void printAddedTaskSummary(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    private void printDeletedTaskSummary(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }


    //TODO: add, delete, mark done and unmark done ca be placed here
    public void markAsDone(int taskIndex) {
        this.tasks.get(taskIndex - 1).markAsDone();
    }

    public void unmarkAsDone(int taskIndex) {
        this.tasks.get(taskIndex - 1).unmarkAsDone();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.printAddedTaskSummary(task);
    }

    public void deleteTask(int taskIndex) {
        Task deletedTask = this.tasks.remove(taskIndex - 1);
        printDeletedTaskSummary(deletedTask);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            output.append((i + 1)).append(".").append(this.tasks.get(i)).append(System.lineSeparator());
        }
        return output.toString();
    }
}
