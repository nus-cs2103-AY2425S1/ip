package fridayproject;

import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {
    private ArrayList<Tasks> tasks;

    /*
     * Constructor for TaskList with existing tasks.
     * @param tasks
     */
    public TaskList(ArrayList<Tasks> tasks) {

        // Assertion to ensure that the tasks are not null
        assert tasks != null : "Tasks should not be null";
        this.tasks = tasks;
    }
    /*
     * Constructor for TaskList with no existing tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /*
     * Adds a task to the task list.
     * @param task The task to be added.
     */
    public void addTask(Tasks task) {
        // Assertions to ensure that the task is not null
        assert task != null : "Task should not be null";
        tasks.add(task);
    }   

    /*
     * Deletes a task from the task list.
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Tasks deleteTask(int index) {
        // Assertions to ensure that the index is valid
        assert index >= 0 && index < tasks.size() : "Index should be within the range of the task list";
        return tasks.remove(index);
    }

    /*
     * Marks a task as done.
     * @param index The index of the task to be marked as done.
     */
    public void markTaskAsDone(int index) {
        // Assertions to ensure that the index is valid
        assert index >= 0 && index < tasks.size() : "Index should be within the range of the task list";
        tasks.get(index).markAsDone();
    }

    /*
     * Marks a task as undone.
     * @param index The index of the task to be marked as undone.
     */
    public void markTaskAsUndone(int index) {
        // Assertions to ensure that the index is valid
        assert index >= 0 && index < tasks.size() : "Index should be within the range of the task list";
        tasks.get(index).markAsUndone();
    }

    /*
     * Gets the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Tasks> getTasks() {
        return tasks;
    }

    /*
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /*
     * Gets a task at a specific index.
     * @param index The index of the task to be retrieved.
     *  @return The task at the specified index.
     */
    public Tasks getTask(int index) {
        // Assertions to ensure that the index is valid
        assert index >= 0 && index < tasks.size() : "Index should be within the range of the task list";
        return tasks.get(index);
    }

    /*
     * Finds tasks that contain a specific keyword.
     * @param keyword The keyword to search for.
     * @return The list of tasks that contain the keyword.
     */
    public String findTasks(String keyword) {
        // Assertions to ensure that the keyword is not null
        assert keyword != null && !keyword.isEmpty() : "Keyword should not be null";

        ArrayList<Tasks> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(keyword)) {
                matchingTasks.add(tasks.get(i));
            }
        }
        
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                message.append((i + 1)).append(". ").append(matchingTasks.get(i)
                    .getTypeIcon()).append(matchingTasks.get(i).toString()).append("\n");
            }
            return message.toString().trim();
        }
    }

    /*
     * Gets the tasks for a specific date.
     * @param date The date to search for.
     * @return The list of tasks on the specified date.
     */
    public String getTasksForSpecificDate(LocalDate date) {
        // Assertions to ensure that the date is not null
        assert date != null : "Date should not be null";

        ArrayList<Tasks> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                Deadline deadline = (Deadline) tasks.get(i);
                if (deadline.date.equals(date)) {
                    matchingTasks.add(tasks.get(i));
                }
            } else if (tasks.get(i) instanceof Event) {
                Event event = (Event) tasks.get(i);
                if (event.getStartDate().equals(date) || event.getEndDate().equals(date)) {
                    matchingTasks.add(tasks.get(i));
                }
            }
        }
        
        if (matchingTasks.isEmpty()) {
            return "No tasks found on " + date + ".";
        } else {
            StringBuilder message = new StringBuilder("Here are the tasks on this date:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                message.append((i + 1)).append(". ").append(matchingTasks.get(i)
                    .getTypeIcon()).append(matchingTasks.get(i).toString()).append("\n");
            }
            return message.toString().trim();
        }
    }
}

// done with the guidance of ChatGPT
