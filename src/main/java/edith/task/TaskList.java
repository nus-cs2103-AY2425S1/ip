package edith.task;

import edith.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This TaskList class manages a list of tasks.
 * It provides methods to add, delete, retrieve, and list tasks.
 * It also provides methods to find tasks by a keyword,
 * as well as to list tasks that are due or starting on a specific date.
 */
public class TaskList {
    private final ArrayList<Task> listOfTasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param listOfTasks The list of tasks to initialize this TaskList with.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getListOfTasks() {
        assert listOfTasks != null : "Task list should never be null";
        return this.listOfTasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        listOfTasks.add(task);
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to delete.
     * @throws IndexOutOfBoundsException if the index is out of the valid range.
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < listOfTasks.size() : "Index out of bounds when deleting task";
        listOfTasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The Task at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of the valid range.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < listOfTasks.size() : "Index out of bounds when retrieving task";
        return listOfTasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumOfTasks() {
        return listOfTasks.size();
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return A string representation of all tasks in the list.
     */
    public String listTasks() {
        if (listOfTasks.isEmpty()) {
            return "Great news, you have no outstanding tasks! Have a break!";
        } else {
            StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");

            for (int i = 0; i < listOfTasks.size(); i++) {
                response.append((i + 1)).append(") ").append(listOfTasks.get(i)).append("\n");
            }

            return response.toString();
        }
    }

    /**
     * Lists tasks that are due or starting on a specific date.
     *
     * @param date The date to filter tasks by.
     * @return A string representation of tasks due or starting on the specified date.
     */
    public String listTasksOnDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        int index = 1;
        StringBuilder response = new StringBuilder();

        response.append("Here are your tasks due by ").append(date).append(":\n");
        listDueTasks(index, response, localDate);

        response.append("Here are your events starting on ").append(date).append(":\n");
        listEventsOnDate(index, response, localDate);

        return response.toString();
    }

    /**
     * Helper method to list tasks that are due on a specific date.
     *
     * @param index The starting index for listing.
     * @param response The response StringBuilder to append task details.
     * @param localDate The date to filter tasks by.
     */
    public void listDueTasks(int index, StringBuilder response, LocalDate localDate) {
        boolean isDue = false;

        for (Task task : listOfTasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDueDate().isEqual(localDate)) {
                    response.append(index).append(") ").append(deadline).append("\n");
                    index++;
                    isDue = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getEndTime().isEqual(localDate)) {
                    response.append(index).append(") ").append(event).append("\n");
                    index++;
                    isDue = true;
                }
            }
        }

        if (!isDue) {
            response.append("NOTHING\n\n");
        } else {
            response.append("\n");
        }
    }

    /**
     * Helper method to list events that start on a specific date.
     *
     * @param index The starting index for listing.
     * @param response The response StringBuilder to append event details.
     * @param localDate The date to filter events by.
     */
    public void listEventsOnDate(int index, StringBuilder response, LocalDate localDate) {
        boolean isStartingOn = false;

        for (Task task : listOfTasks) {
            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartTime().isEqual(localDate)) {
                    response.append(index).append(") ").append(event).append("\n");
                    index++;
                    isStartingOn = true;
                }
            }
        }

        if (!isStartingOn) {
            response.append("NOTHING");
        }
    }

    /**
     * Finds and lists tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return A string representation of tasks matching the keyword.
     */
    public String findTasksByKeyword(String keyword) {
        List<Task> matchedTasks = listOfTasks.stream()
                .filter(task -> task.containsKeyword(keyword))
                .collect(Collectors.toList());

        if (matchedTasks.isEmpty()) {
            return "There are no matching tasks in your list.";
        }

        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < matchedTasks.size(); i++) {
            response.append((i + 1)).append(") ").append(matchedTasks.get(i)).append("\n");
        }

        return response.toString();
    }
}
