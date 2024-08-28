package com.appleaster.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.appleaster.exception.AppleasterException;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.printf("Now you have %d task%s in the list.%n", tasks.size(), tasks.size() == 1 ? "" : "s");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
            }
        }
    }

    public void markTask(int index, boolean isDone) throws AppleasterException {
        if (index < 0 || index >= tasks.size()) {
            throw new AppleasterException("Invalid task number.");
        }
        Task task = tasks.get(index);
        task.setCompleted(isDone);
        System.out.printf("Nice! I've marked this task as %s:%n", isDone ? "done" : "not done");
        System.out.println("  " + task);
    }

    public void deleteTask(int index) throws AppleasterException {
        if (index < 0 || index >= tasks.size()) {
            throw new AppleasterException("Invalid task number.");
        }
        Task deletedTask = tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + deletedTask);
        System.out.printf("Now you have %d task%s in the list.%n", tasks.size(), tasks.size() == 1 ? "" : "s");
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void listTasksOnDate(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().toLocalDate().equals(date)) {
                    tasksOnDate.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFrom().toLocalDate().equals(date) || event.getTo().toLocalDate().equals(date)) {
                    tasksOnDate.add(task);
                }
            }
        }

        if (tasksOnDate.isEmpty()) {
            System.out.println("There are no tasks on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } else {
            System.out.println("Here are the tasks on " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");
            for (int i = 0; i < tasksOnDate.size(); i++) {
                System.out.printf("%d.%s%n", i + 1, tasksOnDate.get(i));
            }
        }
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}