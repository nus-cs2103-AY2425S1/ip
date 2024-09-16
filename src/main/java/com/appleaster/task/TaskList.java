package com.appleaster.task;

import com.appleaster.exception.AppleasterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks in the Appleaster application.
 * This class provides methods to add, delete, mark, and list tasks.
 */
public class TaskList {
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = 
        DateTimeFormatter.ofPattern("HH:mm");        
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     * @return A string describing the result of the operation.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d task%s in the list.",
            task, tasks.size(), tasks.size() == 1 ? "" : "s");
    }

    /**
     * Gets a string representation of all tasks in the list.
     *
     * @return A string containing all tasks, or a message if the list is empty.
     */
    public String getTaskListString() {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
            }
            return sb.toString().trim();
        }
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public List<Task> findTasks(String keyword) {
        return tasks.stream()
            .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    /**
     * Gets a string representation of tasks that match the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A string containing matching tasks, or a message if no tasks match.
     */
    public String getMatchingTasksString(String keyword) {
        List<Task> matchingTasks = findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append(String.format("%d.%s\n", i + 1, matchingTasks.get(i)));
            }
            return sb.toString().trim();
        }
    }    

    /**
     * Marks a task as done or not done.
     *
     * @param index The index of the task to be marked.
     * @param isDone True if the task should be marked as done, false otherwise.
     * @return A string describing the result of the operation.
     * @throws AppleasterException If the index is invalid.
     */
    public String markTask(int index, boolean isDone) throws AppleasterException {
        if (index < 0 || index >= tasks.size()) {
            throw new AppleasterException("Invalid task number.");
        }
        Task task = tasks.get(index);
        task.setCompleted(isDone);
        return String.format("Nice! I've marked this task as %s:\n  %s",
            isDone ? "done" : "not done", task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     * @throws AppleasterException If the index is invalid.
     */
    public Task deleteTask(int index) throws AppleasterException {
        if (index < 0 || index >= tasks.size()) {
            throw new AppleasterException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Gets a string representation of all tasks scheduled for a specific date.
     *
     * @param date The date to filter tasks by.
     * @return A string containing tasks on the specified date, or a message if no tasks are found.
     */
    public String getTasksOnDateString(LocalDate date) {
        List<Task> tasksOnDate = tasks.stream()
            .filter(task -> {
                if (task instanceof Deadline) {
                    return ((Deadline) task).getBy().toLocalDate().equals(date);
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    return event.getFrom().toLocalDate().equals(date) 
                        || event.getTo().toLocalDate().equals(date);
                }
                return false;
            })
            .collect(Collectors.toList());

        if (tasksOnDate.isEmpty()) {
            return "There are no tasks on " + date.format(DATE_FORMATTER);
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks on " + date.format(DATE_FORMATTER) + ":\n");
            for (int i = 0; i < tasksOnDate.size(); i++) {
                sb.append(String.format("%d.%s\n", i + 1, tasksOnDate.get(i)));
            }
            return sb.toString().trim();
        }
    }

    /**
     * Gets a copy of the task list.
     *
     * @return A new ArrayList containing all tasks.
     */
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Gets a string representation of the schedule for a specific date.
     *
     * @param date The date to view the schedule for.
     * @return A string containing the schedule for the specified date, or a message if no tasks are found.
     */
    public String getScheduleForDateString(LocalDate date) {
        List<Task> scheduledTasks = tasks.stream()
            .filter(task -> {
                if (task instanceof Event) {
                    Event event = (Event) task;
                    return event.getFrom().toLocalDate().equals(date) 
                        || event.getTo().toLocalDate().equals(date);
                } else if (task instanceof Deadline) {
                    return ((Deadline) task).getBy().toLocalDate().equals(date);
                }
                return false;
            })
            .sorted((t1, t2) -> {
                LocalDateTime time1 = getTaskTime(t1);
                LocalDateTime time2 = getTaskTime(t2);
                return time1.compareTo(time2);
            })
            .collect(Collectors.toList());

        if (scheduledTasks.isEmpty()) {
            return "There are no tasks scheduled for " + date.format(DATE_FORMATTER);
        } else {
            StringBuilder sb = new StringBuilder("Schedule for " + date.format(DATE_FORMATTER) + ":\n");
            for (int i = 0; i < scheduledTasks.size(); i++) {
                Task task = scheduledTasks.get(i);
                String timeString = getTaskTimeString(task);
                sb.append(String.format("%d. [%s] %s\n", i + 1, timeString, task));
            }
            return sb.toString().trim();
        }
    }

    /**
     * Gets the time of a task for sorting purposes.
     *
     * @param task The task to get the time for.
     * @return The LocalDateTime of the task.
     */
    private LocalDateTime getTaskTime(Task task) {
        if (task instanceof Event) {
            return ((Event) task).getFrom();
        } else if (task instanceof Deadline) {
            return ((Deadline) task).getBy();
        }
        return LocalDateTime.MAX; // Put Todo tasks at the end
    }

    /**
     * Gets a string representation of the task time.
     *
     * @param task The task to get the time string for.
     * @return A string representing the task time.
     */
    private String getTaskTimeString(Task task) {
        if (task instanceof Event) {
            Event event = (Event) task;
            return event.getFrom().format(TIME_FORMATTER) + " - " + event.getTo().format(TIME_FORMATTER);
        } else if (task instanceof Deadline) {
            return ((Deadline) task).getBy().format(TIME_FORMATTER);
        }
        return "All day"; // For Todo tasks
    }    
}