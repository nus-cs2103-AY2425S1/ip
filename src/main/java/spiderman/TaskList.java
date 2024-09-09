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

    public String addTask(Task task) {
        this.tasks.add(task);
        return "Cool! I'll add this to your task list!\n"
                + "You now have " + this.tasks.size() + " tasks in your task list.";
    }

    public String deleteTask(int index) {
        String removedTask = this.tasks.get(index).toString();
        this.tasks.remove(index);
        return "Alright! I will delete this task for you!\n"
                + removedTask;

    }

    public String markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
        return "Great! I've marked this task as done:\n"
                + this.tasks.get(index).toString();
    }

    public String markTaskAsNotDone(int index) {
        this.tasks.get(index).markAsNotDone();
        return "OK, this task will be marked as not done yet:\n"
                + this.tasks.get(index).toString();
    }

    public String listTasks() {
        String listOfTasks = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            listOfTasks += i + 1 + ". " + this.tasks.get(i).toString() + "\n";
        }
        return listOfTasks;
    }

    public String findTasks(String keyword) {
        int numOfMatches = 0;
        String matchingTasks = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getDescription().contains(keyword)) {
                numOfMatches++;
                matchingTasks += i + 1 + ". " + this.tasks.get(i).toString() + "\n";
            }
        }
        if (numOfMatches == 0) {
            return "No tasks found with the keyword: " + keyword;
        }
        return "Here are the matching tasks in your list:\n"
                + matchingTasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
