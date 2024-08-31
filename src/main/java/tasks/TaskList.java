package tasks;

import tasks.EventTask;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task deletedTask = taskList.get(index);
            taskList.remove(index);
            System.out.print("Noted. I've removed this task:\n" +
                    deletedTask.getDescription() +
                    String.format("Now you have %d tasks in the list.\n", taskList.size()));
        } else {
            System.out.print("There is no such task number!");
        }
    }

    public void write(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Task task : taskList) {
            String line = taskToString(task);
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    private String taskToString(Task task) {

        if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return eventTask.toString();
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return deadlineTask.toString();
        } else {
            ToDoTask toDoTask = (ToDoTask) task;
            return toDoTask.toString();
        }
    }
    public int size() {
        return this.taskList.size();
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public void printList() {
        if (taskList.isEmpty()) {
            System.out.print("No items in the list yet!\n");
        }
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String response = String.format("%d. %s", i + 1, task.getDescription());
            System.out.println(response);
        }
    }

    public void mark(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task t = taskList.get(index);
            t.markAsDone();
            String response = String.format("%s", t.getDescription());
            System.out.println("Nice I've marked this task as done: \n"
                    + line() + response);
        } else {
            System.out.println("Invalid Task Number.\n");
        }
    }

    public void unmark(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task t = taskList.get(index);
            t.unMark();
            String response = String.format("%s", t.getDescription());
            System.out.println("OK, I've marked this task as not done yet: \n"
                    + line() + response);
        } else {
            System.out.println("Invalid Task Number.\n");
        }
    }

    public String line() {
        return "____________________________________________________________\n";

    }
}
