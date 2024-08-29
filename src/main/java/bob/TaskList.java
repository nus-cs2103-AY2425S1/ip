package bob;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a list of tasks. Supports task management, including adding,
 * deleting, marking, unmarking, listing tasks, and filtering by date.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes the TaskList with a provided list of tasks.
     *
     * @param tasks An ArrayList of existing tasks to initialize the list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Sets the tasks in the TaskList.
     *
     * @param tasks The ArrayList of tasks to set.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the task at the specified index.
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
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Lists all tasks in the task list and prints them to the console.
     *
     * @param ui The Ui instance used to interact with the user.
     */
    public void listTasks(Ui ui) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i));
        }
    }

    /**
     * Marks a task as done based on the index provided.
     *
     * @param index The index of the task to mark as done.
     * @param ui The Ui instance used to interact with the user.
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
     * Unmarks a task as not done based on the index provided.
     *
     * @param index The index of the task to unmark as not done.
     * @param ui The Ui instance used to interact with the user.
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
     * Deletes a task based on the index provided.
     *
     * @param index The index of the task to delete.
     * @param ui The Ui instance used to interact with the user.
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
     * Adds a new ToDo task to the task list.
     *
     * @param phrase The full command input by the user.
     * @param ui The Ui instance used to interact with the user.
     * @throws ChatBotException If the description of the todo is empty.
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
     * Adds a new Deadline task to the task list.
     *
     * @param phrase The full command input by the user.
     * @param ui The Ui instance used to interact with the user.
     * @throws ChatBotException If the input format is invalid.
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
     * Adds a new Event task to the task list.
     *
     * @param phrase The full command input by the user.
     * @param ui The Ui instance used to interact with the user.
     * @throws ChatBotException If the input format is invalid.
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
     * Prints tasks occurring on a specified date. Supports Deadline and Event tasks.
     *
     * @param date The date to filter tasks by, in yyyy-MM-dd format.
     * @param ui The Ui instance used to interact with the user.
     * @throws ChatBotException If the input date format is invalid.
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
}
