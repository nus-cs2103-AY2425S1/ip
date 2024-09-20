package carine.tasks;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import carine.data.TaskDataBase;
import carine.exceptions.InvalidDateException;
import carine.exceptions.InvalidTaskInDatabaseException;
import carine.exceptions.TaskNotFoundException;

/**
 * This class represents a list containing all three forms of tasks.
 * It provides methods to add, mark, unmark, find, delete and print tasks in the list.
 * It set reminder if needed.
 */
public class TaskList {
    private static TaskList tasks;
    private List<Task> listOfTasks;

    protected TaskList() throws IOException, InvalidDateException {
        listOfTasks = TaskDataBase.load();
        assert listOfTasks != null : "Task list should not be null after loading.";
    }

    /**
     * Initializes the `TaskList` if it hasn't been initialized yet.
     *
     * @return The initialized `TaskList` instance.
     * @throws InvalidTaskInDatabaseException If tasks information stored in file is invalid.
     */
    public static TaskList init() throws InvalidTaskInDatabaseException {
        if (tasks == null) {
            try {
                tasks = new TaskList();
            } catch (IOException | InvalidDateException e) {
                throw new InvalidTaskInDatabaseException();
            }
        }
        return tasks;
    }

    /**
     * Generates a reminder message for the user, listing any overdue tasks and tasks that are
     * urgent (due within the next 3 days).
     *
     * @return A string containing the reminder message for overdue and urgent tasks.
     */
    public String setReminder() {
        StringBuilder taskReminders = new StringBuilder();
        List<Task> urgentTasks = new ArrayList<>();
        List<Task> overdueTasks = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Task task : listOfTasks) {
            if (isTaskOverdue(task, now)) {
                overdueTasks.add(task);
            } else if (isTaskUrgent(task, now)) {
                urgentTasks.add(task);
            }
        }

        if (!overdueTasks.isEmpty()) {
            taskReminders.append("Oh no! You have some tasks OVERDUE:\n");
            for (Task task : overdueTasks) {
                taskReminders.append("  ").append(task).append("\n");
            }
        }

        if (!urgentTasks.isEmpty()) {
            taskReminders.append("\nYou have some tasks that are URGENT (due in 3 days):\n");
            for (Task task : urgentTasks) {
                taskReminders.append("  ").append(task).append("\n");
            }
        }
        return taskReminders.toString();
    }

    private boolean isTaskOverdue(Task task, LocalDateTime now) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getBy().toLocalDateTime().isBefore(now) && !task.isCompleted();
        } else if (task instanceof Event) {
            return ((Event) task).getTo().toLocalDateTime().isBefore(now) && !task.isCompleted();
        }
        return false;
    }

    private boolean isTaskUrgent(Task task, LocalDateTime now) {
        LocalDateTime threeDaysFromNow = now.plusDays(3);
        if (task instanceof Deadline) {
            return ((Deadline) task).getBy().toLocalDateTime().isBefore(threeDaysFromNow) && !task.isCompleted()
                    && !isTaskOverdue(task, now);
        } else if (task instanceof Event) {
            return ((Event) task).getTo().toLocalDateTime().isBefore(threeDaysFromNow) && !task.isCompleted()
                    && !isTaskOverdue(task, now);
        }
        return false;
    }

    /**
     * Adds new task to task list.
     *
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        listOfTasks.add(task);
        String successMessage = "Got it! I've added this task:" + "\n" + "  " + task.toString() + "\n"
                + "Now you have " + listOfTasks.size() + " tasks in the list.";
        return saveTasksToDataBase(successMessage);
    }

    /**
     * Marks specific task as done.
     *
     * @param index The task number to be marked.
     * @return Message to inform the user task has been marked.
     * @throws TaskNotFoundException If the index is out of bounds, no task exists at the specified index.
     */
    public String markTask(int index) throws TaskNotFoundException {

        Task task = getTaskByIndex(index);
        task.markAsDone();
        String successMessage = "Nice! I've marked this task as done:\n" + "  " + task;
        return saveTasksToDataBase(successMessage);
    }

    /**
     * Marks specific task as not done.
     *
     * @param index The task number to be marked.
     * @return Message to inform the user task has been unmarked.
     * @throws TaskNotFoundException If the index is out of bounds, no task exists at the specified index.
     */
    public String unmarkTask(int index) throws TaskNotFoundException {
        Task task = getTaskByIndex(index);
        task.markAsNotDone();
        String successMessage = "OK! I've marked this task as not done yet:\n" + "  " + task;
        return saveTasksToDataBase(successMessage);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return Message to inform the user task has been deleted.
     * @throws TaskNotFoundException If the index is out of bounds, no task exists at the specified index.
     */
    public String deleteTask(int index) throws TaskNotFoundException {
        Task removedTask = getTaskByIndex(index);
        listOfTasks.remove(removedTask);
        String successMessage = "Noted! I've removed this task:" + "\n" + "  "
                + removedTask + "\n" + "Now you have "
                + listOfTasks.size() + " tasks in the list.";
        return saveTasksToDataBase(successMessage);
    }

    /**
     * Prints all tasks added to the list.
     *
     * @return The description of all tasks added.
     */
    public String printList() {
        if (listOfTasks.isEmpty()) {
            return "There is currently no task in your list";
        }
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < listOfTasks.size(); i++) {
            tasks.append(i + 1).append(". ").append(listOfTasks.get(i)).append("\n");
        }
        return tasks.toString().trim();
    }

    /**
     * Prints all tasks matching user input.
     *
     * @return The description of all tasks added.
     * @throws TaskNotFoundException If the index is out of bounds, no task exists at the specified index.
     */
    public String findTask(String keyword) throws TaskNotFoundException {
        List<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            throw new TaskNotFoundException();
        }

        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append((i + 1) + ". " + matchingTasks.get(i) + "\n");
        }
        return result.toString();
    }

    private Task getTaskByIndex(int index) throws TaskNotFoundException {
        if (index > 0 && index <= listOfTasks.size()) {
            return listOfTasks.get(index - 1);
        } else {
            throw new TaskNotFoundException();
        }
    }

    private String saveTasksToDataBase(String successMessage) {
        try {
            TaskDataBase.save(listOfTasks);
            return successMessage;
        } catch (IOException e) {
            return "There is an error saving task into database";
        }
    }
}
