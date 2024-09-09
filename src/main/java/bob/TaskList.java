package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the Bob chatbot.
 * The TaskList allows tasks to be added, deleted, marked as done, and searched.
 */
public class TaskList {

    /** List of tasks managed by the TaskList */
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Sets the tasks in the TaskList to the provided list.
     *
     * @param tasks The list of tasks to set in the TaskList.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Invalid index for retrieving a task";
        return tasks.get(index);
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Lists all the tasks in the TaskList and returns them as a formatted string.
     *
     * @return A string containing all tasks in the TaskList.
     */
    public String listTasks() {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }
        return result.toString();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     * @param ui The UI object used to display the result of the operation.
     * @return A message indicating the result of the mark operation.
     * @throws ChatBotException If the index is invalid.
     */
    public String markTask(int index, Ui ui) throws ChatBotException {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                return ui.showTaskMarked(task.mark());
            } else {
                throw new ChatBotException("Invalid task number for marking.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for marking.");
        }
    }

    /**
     * Unmarks the task at the specified index.
     *
     * @param index The index of the task to unmark.
     * @param ui The UI object used to display the result of the operation.
     * @return A message indicating the result of the unmark operation.
     * @throws ChatBotException If the index is invalid.
     */
    public String unmarkTask(int index, Ui ui) throws ChatBotException {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                return ui.showTaskUnmarked(task.unmark());
            } else {
                throw new ChatBotException("Invalid task number for unmarking.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for unmarking.");
        }
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete.
     * @param ui The UI object used to display the result of the operation.
     * @return A message indicating the result of the delete operation.
     * @throws ChatBotException If the index is invalid.
     */
    public String deleteTask(int index, Ui ui) throws ChatBotException {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                tasks.remove(index);
                return ui.showTaskDeleted(task.delete(), tasks.size());
            } else {
                throw new ChatBotException("Invalid task number for deleting.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for deleting.");
        }
    }

    /**
     * Adds a new todo task to the TaskList.
     *
     * @param phrase The user input containing the description of the todo task.
     * @param ui The UI object used to display the result of the operation.
     * @return A message indicating the result of the add operation.
     * @throws ChatBotException If the description is empty or if there is an error in the input.
     */
    public String addToDo(String phrase, Ui ui) throws ChatBotException {
        assert phrase.length() > 5 : "To-do description cannot be empty";

        int oldSize = tasks.size();
        String description = phrase.substring(5);
        Task newTask = new ToDo(description, TaskType.TODO);
        tasks.add(newTask);

        assert tasks.size() == oldSize + 1 : "TaskList size should increase by 1 after adding a task";
        return ui.showTaskAdded(newTask, tasks.size());
    }

    /**
     * Adds a new deadline task to the TaskList.
     *
     * @param phrase The user input containing the description and deadline of the task.
     * @param ui The UI object used to display the result of the operation.
     * @return A message indicating the result of the add operation.
     * @throws ChatBotException If the input format is incorrect or if there is an error parsing the deadline.
     */
    public String addDeadline(String phrase, Ui ui) throws ChatBotException {
        try {
            String[] parts = phrase.split(" /by ");
            String description = parts[0].substring(9);
            String by = parts[1];
            Task newTask = new Deadline(description, by, TaskType.DEADLINE);
            tasks.add(newTask);
            return ui.showTaskAdded(newTask, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new ChatBotException("Invalid format for deadline. "
                    + "Correct format: deadline <description> /by <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Adds a new event task to the TaskList.
     *
     * @param phrase The user input containing the description, start time, and end time of the event.
     * @param ui The UI object used to display the result of the operation.
     * @return A message indicating the result of the add operation.
     * @throws ChatBotException If the input format is incorrect or if there is an error parsing the dates.
     */
    public String addEvent(String phrase, Ui ui) throws ChatBotException {
        try {
            String[] parts = phrase.split(" /from | /to ");
            String description = parts[0].substring(6);
            String from = parts[1];
            String to = parts[2];
            Task newTask = new Event(description, from, to, TaskType.EVENT);
            tasks.add(newTask);
            return ui.showTaskAdded(newTask, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new ChatBotException("Invalid format for event. "
                    + "Correct format: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Retrieves tasks occurring on a specific date and returns them as a formatted string.
     *
     * @param date The date to search for tasks.
     * @return A string containing tasks scheduled for the specified date.
     * @throws ChatBotException If the date format is invalid.
     */
    public String printTasksOnDate(String date) throws ChatBotException {
        LocalDate searchDate;
        StringBuilder result = new StringBuilder();
        try {
            searchDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd.");
        }

        result.append("Here are your tasks on ")
                .append(searchDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")))
                .append(":\n");
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                LocalDate taskDate = ((Deadline) task).getDeadline().toLocalDate();
                if (taskDate.equals(searchDate)) {
                    result.append(task).append("\n");
                }
            } else if (task instanceof Event) {
                LocalDate taskDate = ((Event) task).getFrom().toLocalDate();
                if (taskDate.equals(searchDate)) {
                    result.append(task).append("\n");
                }
            }
        }
        return result.toString();
    }

    /**
     * Searches for tasks containing a specific keyword in their description
     * and returns the results as a formatted string.
     *
     * @param phrase The user input containing the keyword to search for.
     * @param ui The UI object used to display the results of the search.
     * @return A string containing tasks that match the keyword, or an error message if no tasks are found.
     * @throws ChatBotException If the keyword is empty or the input format is incorrect.
     */
    public String searchKeyword(String phrase, Ui ui) throws ChatBotException {
        if (phrase.length() <= 5) {
            throw new ChatBotException("The keyword of a find command cannot be empty.");
        }

        // Split the input phrase to extract the keyword
        String[] tmp = phrase.split(" ", 2);
        String key = tmp[1];
        boolean found = false;
        ArrayList<Task> tasksWithKey = new ArrayList<>();

        // Iterate through tasks to find those that contain the keyword in their description
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(key.toLowerCase())) {
                tasksWithKey.add(task);
                found = true;
            }
        }

        if (found) {
            return ui.showTasksFound(tasksWithKey.toArray(new Task[0]));
        } else {
            return ui.showError("No tasks found containing the keyword: " + key);
        }
    }
}

