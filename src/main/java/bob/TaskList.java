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

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a list of existing tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Sets the tasks in the TaskList.
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
        return tasks.get(index);
    }

    /**
     * Returns the list of tasks.
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
     * Lists all the tasks in the TaskList and prints them to the UI.
     *
     * @param ui The UI object to display the list of tasks.
     */
    public void listTasks(Ui ui) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index The index of the task to mark as done.
     * @param ui The UI object to display the result.
     * @throws ChatBotException If the index is invalid.
     */
    public void markTask(int index, Ui ui) throws ChatBotException {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                ui.showTaskMarked(task.mark());
            } else {
                throw new ChatBotException("Invalid task number for marking.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for marking.");
        }
    }

    /**
     * Unmarks a task as not done at the specified index.
     *
     * @param index The index of the task to unmark.
     * @param ui The UI object to display the result.
     * @throws ChatBotException If the index is invalid.
     */
    public void unmarkTask(int index, Ui ui) throws ChatBotException {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                ui.showTaskUnmarked(task.unmark());
            } else {
                throw new ChatBotException("Invalid task number for unmarking.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for unmarking.");
        }
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to delete.
     * @param ui The UI object to display the result.
     * @throws ChatBotException If the index is invalid.
     */
    public void deleteTask(int index, Ui ui) throws ChatBotException {
        try {
            if (index >= 0 && index < tasks.size()) {
                Task task = tasks.get(index);
                tasks.remove(index);
                ui.showTaskDeleted(task.delete(), tasks.size());
            } else {
                throw new ChatBotException("Invalid task number for deleting.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ChatBotException("Invalid task number for deleting.");
        }
    }

    /**
     * Adds a ToDo task to the TaskList.
     *
     * @param phrase The input command containing the ToDo description.
     * @param ui The UI object to display the result.
     * @throws ChatBotException If the description is empty.
     */
    public void addToDo(String phrase, Ui ui) throws ChatBotException {
        if (phrase.length() <= 5) {
            throw new ChatBotException("The description of a todo cannot be empty.");
        }
        String description = phrase.substring(5);
        Task newTask = new ToDo(description, TaskType.TODO);
        tasks.add(newTask);
        ui.showTaskAdded(newTask, tasks.size());
    }

    /**
     * Adds a Deadline task to the TaskList.
     *
     * @param phrase The input command containing the Deadline description and due date.
     * @param ui The UI object to display the result.
     * @throws ChatBotException If the format is incorrect or the date cannot be parsed.
     */
    public void addDeadline(String phrase, Ui ui) throws ChatBotException {
        try {
            String[] parts = phrase.split(" /by ");
            String description = parts[0].substring(9);
            String by = parts[1];
            Task newTask = new Deadline(description, by, TaskType.DEADLINE);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new ChatBotException("Invalid format for deadline. "
                    + "Correct format: deadline <description> /by <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Adds an Event task to the TaskList.
     *
     * @param phrase The input command containing the Event description, start, and end times.
     * @param ui The UI object to display the result.
     * @throws ChatBotException If the format is incorrect or the dates cannot be parsed.
     */
    public void addEvent(String phrase, Ui ui) throws ChatBotException {
        try {
            String[] parts = phrase.split(" /from | /to ");
            String description = parts[0].substring(6);
            String from = parts[1];
            String to = parts[2];
            Task newTask = new Event(description, from, to, TaskType.EVENT);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new ChatBotException("Invalid format for event. "
                    + "Correct format: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Prints the tasks occurring on the specified date.
     *
     * @param date The date to search for tasks (in yyyy-MM-dd format).
     * @param ui The UI object to display the result.
     * @throws ChatBotException If the date format is invalid.
     */
    public void printTasksOnDate(String date, Ui ui) throws ChatBotException {
        LocalDate searchDate;
        try {
            searchDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new ChatBotException("Invalid date format. Please use yyyy-MM-dd.");
        }

        ui.showLine();
        System.out.println("Here are your tasks on "
                + searchDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                LocalDate taskDate = ((Deadline) task).getDeadline().toLocalDate();
                if (taskDate.equals(searchDate)) {
                    System.out.println(task);
                }
            } else if (task instanceof Event) {
                LocalDate taskDate = ((Event) task).getFrom().toLocalDate();
                if (taskDate.equals(searchDate)) {
                    System.out.println(task);
                }
            }
        }
    }

    /**
     * If no tasks with the specified keyword are found, displays an error message.
     * Otherwise, displays the list of tasks that contain the keyword.
     *
     * @param ui The UI object used to display the results of the search.
     */
    public void searchKeyword(String phrase, Ui ui) throws ChatBotException {
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

        // Display the found tasks or an error message
        if (found) {
            ui.showTasksFound(tasksWithKey);
        } else {
            ui.showError("No tasks found containing the keyword: " + key);
        }
    }
}

