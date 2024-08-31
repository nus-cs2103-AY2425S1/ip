package Ponder_Pika.Task;

import java.util.ArrayList;
import java.util.List;

import Ponder_Pika.Exception.PonderPikaException;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws PonderPikaException {
        if (index < 1 || index > tasks.size()) {
            throw new PonderPikaException("No task available at given index to be deleted!");
        }
        tasks.remove(index - 1);
    }

    public void markTask(int index) throws PonderPikaException {
        if (index < 1 || index > tasks.size()) {
            throw new PonderPikaException("No task available at given index!");
        }
        tasks.get(index - 1).markDone();
    }

    public void unmarkTask(int index) throws PonderPikaException {
        if (index < 1 || index > tasks.size()) {
            throw new PonderPikaException("No task available at given index!");
        }
        tasks.get(index - 1).markUndone();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }
}
