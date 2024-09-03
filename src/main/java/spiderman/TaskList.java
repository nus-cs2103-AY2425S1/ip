package spiderman;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Cool! I'll add this to your task list!");
        System.out.println("You now have " + this.tasks.size() + " tasks in your task list.");
    }

    public void deleteTask(int index) {
        System.out.println("Alright! I will delete this task for you!");
        System.out.println(this.tasks.get(index).toString());
        this.tasks.remove(index);
    }

    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
        System.out.println("Great! I've marked this task as done:");
        System.out.println(this.tasks.get(index).toString());
    }

    public void markTaskAsNotDone(int index) {
        this.tasks.get(index).markAsNotDone();
        System.out.println("OK, this task will be marked as not done yet:");
        System.out.println(this.tasks.get(index).toString());
    }

    public void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i+1) + ". " + this.tasks.get(i).toString());
        }
    }

    public void findTasks(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int numOfMatches = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getDescription().contains(keyword)) {
                numOfMatches++;
                System.out.println((i + 1) + ". " + this.tasks.get(i).toString());
            }
        }
        if (numOfMatches == 0) {
            System.out.println("No tasks found with the keyword: " + keyword);
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
