package voidcat.task;

import voidcat.exception.VoidException;
import voidcat.ui.Ui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private static final String FORMAT = "\t%s%n";

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    public Task unmarkTaskAsDone(int index) {
        tasks.get(index).unmarkAsDone();
        return tasks.get(index);
    }


    public void listTasks() throws VoidException {
        if (tasks.isEmpty()) {
            throw new VoidException("No tasks found in list yet!");
        } else {
            Ui.showLine();
            System.out.printf(FORMAT, "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("\t%d. %s%n", i + 1, tasks.get(i));
            }
            Ui.showLine();
        }
    }

    public int size() {
        return tasks.size();
    }

    public void saveTasks(BufferedWriter bw) throws IOException {
        for (Task task : tasks) {
            bw.write(task.toSaveFormat());
            bw.newLine();
        }
        bw.close();
    }

    /**
     * Finds and lists tasks that match the keyword.
     *
     * @param keyword The keyword to find in description of task.
     */
    public void findTasks(String keyword) throws VoidException {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            throw new VoidException("Aww..no matching tasks found for keyword: " + keyword);
        } else {
            Ui.showLine();
            System.out.printf(FORMAT, "Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.printf("\t%d. %s%n", i + 1, matchingTasks.get(i));
            }
            Ui.showLine();
        }
    }
}
