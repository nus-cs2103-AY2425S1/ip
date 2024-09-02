package alisa.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public String deleteTask(int index) {
        Task removedTask = taskList.remove(index-1);
        return "Noted. I've removed this task:\n" + removedTask + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String markTask(int index) {
        taskList.get(index-1).markAsDone();
        return "Nice! I've marked this task as done:\n" + taskList.get(index-1);
    }

    public String unmarkTask(int index) {
        taskList.get(index-1).markAsNotDone();
        return "OK, I've marked this task as not done yet:\n" + taskList.get(index-1);
    }

    public String addTask(Task task) {
        taskList.add(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String getList() {
        String message = "";
        if (taskList.isEmpty()) {
            message = "The list is empty, sorry :(";
        } else {
            for (int i = 1; i <= taskList.size(); i++) {
                message += i + "." + taskList.get(i-1) + "\n";
            }
        }
        return message;
    }

    public String toFileString() {
        String fileText = "";
        for (Task task : taskList) {
            fileText += task.toFileString();
        }
        return fileText;
    }

    /**
     * Lists all the tasks that contains the keyword in their task descriptions.
     *
     * @param keyword Keyword to search for in each task description.
     * @return String listing all the tasks that contains the keyword in their descriptions.
     */
    public String findTasks(String keyword) {
        String tasks = "Here are the matching tasks in your list:\n";
        int counter = 1;
        for (Task task : taskList) {
            if (task.containsWord(keyword)) {
                tasks += counter + ". " + task + "\n";
                counter += 1;
            }
        }
        return tasks;
    }

}
