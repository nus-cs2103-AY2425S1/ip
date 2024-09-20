package utility;

import task.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The {@code Validator} class provides utility methods for validating tasks and file operations.
 * It ensures files exist, checks for duplicate tasks, and validates task indexes.
 */
public class Validator {

    /**
     * Checks if the file at the given {@code filePath} exists.
     * If the file or its directory doesn't exist, they are created.
     *
     * @param filePath The path of the file to check.
     */
    public void checkFileExists(String filePath) {
        File file = new File(filePath);
        File directory = new File(file.getParent());
        try {
            if (!directory.exists()) {
                if (!directory.mkdir()) {
                    System.out.println("Issue creating Directory");
                }
            }
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println("Issue creating File.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error in file creation.");
        }
    }


    /**
     * Checks for duplicate tasks in the provided list of tasks.
     * A task is considered a duplicate if its description matches, and for deadlines or events,
     * the respective dates also match.
     *
     * @param tasks   The list of existing tasks.
     * @param newTask The new task to check for duplicates.
     * @return {@code true} if a duplicate task is found, otherwise {@code false}.
     */
    public Boolean detectDuplicates(ArrayList<Task> tasks, Task newTask) {
        for (Task task : tasks) {
            if (!task.getDesc().equals(newTask.getDesc())) {
                continue;
            }
            if (isDuplicateDeadline(task, newTask) | isDuplicateEvent(task, newTask) | isDuplicateToDo(task, newTask)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if two {@code Deadline} tasks are duplicates by comparing their due dates.
     *
     * @param task    The existing task in the list.
     * @param newTask The new task to check.
     * @return {@code true} if both tasks are {@code Deadline} tasks with the same due date, otherwise {@code false}.
     */
    private Boolean isDuplicateDeadline(Task task, Task newTask) {
        if (task instanceof Deadline existingDeadline && newTask instanceof Deadline newDeadline) {
            return existingDeadline.getBy().equals(newDeadline.getBy());
        }
        return false;
    }

    /**
     * Checks if two {@code Event} tasks are duplicates by comparing their start and end times.
     *
     * @param task    The existing task in the list.
     * @param newTask The new task to check.
     * @return {@code true} if both tasks are {@code Event} tasks with the same start and end times, otherwise {@code false}.
     */
    private Boolean isDuplicateEvent(Task task, Task newTask) {
        if (task instanceof Event existingEvent && newTask instanceof Event newEvent) {
            return existingEvent.getFrom().equals(newEvent.getFrom()) &&
                    existingEvent.getTo().equals(newEvent.getTo());
        }
        return false;
    }

    /**
     * Checks if two {@code ToDo} tasks are duplicates by comparing their descriptions.
     *
     * @param task    The existing task in the list.
     * @param newTask The new task to check.
     * @return {@code true} if both tasks are {@code ToDo} tasks with the same description, otherwise {@code false}.
     */
    private Boolean isDuplicateToDo(Task task, Task newTask) {
        return task instanceof ToDo && newTask instanceof ToDo;
    }


    /**
     * Checks if the given index is within the bounds of the task list.
     *
     * @param limit The size of the task list.
     * @param index The index to validate.
     * @return {@code true} if the index is within the bounds, otherwise {@code false}.
     */
    public Boolean outOfBound(int limit, int index) {
        return index >= 1 && index <= limit;
    }
}
