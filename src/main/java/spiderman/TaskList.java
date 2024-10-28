package spiderman;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a list of tasks. Provides methods to add, delete, mark as done,
 * and find tasks within the list.
 */
public class TaskList {
    /** The list of tasks stored as an ArrayList. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the given tasks.
     *
     * @param tasks An existing ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new Task object to the task list.
     *
     * @param task The Task object to be added.
     * @return The message for the successful addition of the task.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "Cool! I'll add this to your task list!\n"
                + "You now have " + this.tasks.size() + " tasks in your task list.";
    }

    /**
     * Deletes a task from the task list at a specified index.
     *
     * @param index The index of the task to be deleted.
     * @return The message confirming the deletion of the task.
     */
    public String deleteTask(int index) {
        if (isIndexNotInRange(index)) {
            return "The list number given is not valid!";
        }

        String removedTask = this.tasks.get(index).toString();
        this.tasks.remove(index);
        return "Alright! I will delete this task for you!\n"
                + removedTask;
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index The index of the task to be marked as done.
     * @return The message confirming the task has been marked as done.
     */
    public String markTaskAsDone(int index) {
        if (isIndexNotInRange(index)) {
            return "The list number given is not valid!";
        }

        this.tasks.get(index).markAsDone();
        return "Great! I've marked this task as done:\n"
                + this.tasks.get(index).toString();
    }

    /**
     * Marks a task as not done at the specified index.
     *
     * @param index The index of the task to be marked as not done.
     * @return The message confirming the task has been marked as not done.
     */
    public String markTaskAsNotDone(int index) {
        if (isIndexNotInRange(index)) {
            return "The list number given is not valid!";
        }

        this.tasks.get(index).markAsNotDone();
        return "OK, this task will be marked as not done yet:\n"
                + this.tasks.get(index).toString();
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return A string representation of all tasks in the task list.
     */
    public String listTasks() {
        if (this.tasks.isEmpty()) {
            return "There is no current tasks available.";
        }

        String listOfTasks = "Alright! Here is your current tasks list: \n";
        for (int i = 0; i < this.tasks.size(); i++) {
            listOfTasks += i + 1 + ". " + this.tasks.get(i).toString() + "\n";
        }
        return listOfTasks;
    }

    /**
     * Finds tasks that contain a specific keyword in their description.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A string representation of all matching tasks.
     */
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

    public String updateTask(int index, String[] updateDetails) {
        if (index >= this.tasks.size()) {
            return "The list number given is not valid!";
        }

        Task taskToUpdate = this.tasks.get(index);

        // Format: update {task number} /description /from /to /by -> these can be empty but one field is required
        String newDescription = extractField(updateDetails, "description");
        String newFromInString = extractField(updateDetails, "from");
        String newToInString = extractField(updateDetails, "to");
        String newByInString = extractField(updateDetails, "by");

        String message = "Error updating given task!";

        if (newDescription != null) {
            taskToUpdate.setDescription(newDescription);
            message = "Updated task: " + taskToUpdate;
        }

        if (newByInString != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            try {
                LocalDate newBy = LocalDate.parse(newByInString, formatter);
                ((Deadline) taskToUpdate).setBy(newBy);
                message = "Updated task: " + taskToUpdate;
            } catch (DateTimeParseException e) {
                return "The date is not in the correct format! It should be YYYY-MM-DD";
            } catch (Exception e) {
                return "The parameters given does not exist in the type of task to update!";
            }
        }

        if (newFromInString != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            try {
                LocalDateTime newFrom = LocalDateTime.parse(newFromInString, formatter);
                ((Event) taskToUpdate).setFrom(newFrom);
                message = "Updated task: " + taskToUpdate;
            } catch (DateTimeParseException e) {
                return "The date and time is not in the correct format! It should be YYYY-MM-DD HH:mm";
            } catch (Exception e) {
                return "The parameters given does not exist in the type of task to update!";
            }
        }

        if (newToInString != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            try {
                LocalDateTime newTo = LocalDateTime.parse(newToInString, formatter);
                ((Event) taskToUpdate).setFrom(newTo);
                message = "Updated task: " + taskToUpdate;
            } catch (DateTimeParseException e) {
                return "The date and time is not in the correct format! It should be YYYY-MM-DD HH:mm";
            } catch (Exception e) {
                return "The parameters given does not exist in the type of task to update!";
            }
        }
        return message;
    }

    private String extractField(String[] inputString, String field) {
        for (String parameter : inputString) {
            if (parameter.contains(field)) {
                return parameter.replaceFirst(field, "").trim();
            }
        }
        return null;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    private boolean isIndexNotInRange(int index) {
        return index < 0 || index >= this.tasks.size();
    }
}
