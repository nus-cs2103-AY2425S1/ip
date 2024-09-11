package botimusprime.tasks;

import botimusprime.parser.Parser;
import botimusprime.storage.Storage;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks, including operations for adding,
 * marking, deleting, and displaying tasks. It also interacts with the Storage class
 * to save changes to disk.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage storage;

    // private final String SEPARATOR = "____________________________________________________________\n";
    private final String SEPARATOR = "";
    /**
     * Constructs a TaskList with an empty list of tasks and associates it with a file for storage.
     *
     * @param fileName The name of the file where tasks are stored.
     */
    public TaskList(String fileName) {
        this.storage = new Storage(fileName);
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks and associates it with a file for storage.
     *
     * @param tasks    The initial list of tasks.
     * @param fileName The name of the file where tasks are stored.
     */
    public TaskList(ArrayList<Task> tasks, String fileName) {
        this.tasks = tasks;
        this.storage = new Storage(fileName);
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
     * Displays the list of tasks.
     * If the list is empty, it displays a message indicating that there are no tasks.
     */
    public String showList() {
        StringBuilder resultString = new StringBuilder(SEPARATOR
                + "Here are the tasks in your list:\n");
        if (tasks.isEmpty()) {
            return "Congratulations, human! Your task list is empty.";
        }

        for (int i = 0; i < tasks.size(); i++) {
            resultString.append(String.format("%d. %s%n", i + 1, tasks.get(i))).append("\n");
        }
        resultString.append(SEPARATOR);
        return resultString.toString();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int getTaskListLength() {
        return tasks.size();
    }

    /**
     * Marks a task as done based on the index provided in the input.
     * If the index is out of range, it displays an error message.
     *
     * @param idx The index of the item to be marked as done.
     */
    public String markDone(int idx) {
        if (getTaskListLength() < idx || idx < 1) {
            return "Your inputted index is invalid, human.";
        }
        tasks.get(idx - 1).markAsDone();
        storage.saveToDisk(this);
        return SEPARATOR + "Nice! I've marked this task as done:\n" +
                tasks.get(idx - 1);
    }

    /**
     * Marks a task as not done based on the index provided in the input.
     * If the index is out of range, it displays an error message.
     *
     * @param idx The index of the item to be marked as undone.
     */
    public String markUndone(int idx) {
        if (getTaskListLength() < idx || idx < 1) {
            return "Your inputted index is invalid, human.";
        }

        tasks.get(idx - 1).markAsUndone();
        storage.saveToDisk(this);
        return SEPARATOR + "OK, I've marked this task as not done yet:\n"
                + tasks.get(idx - 1);
    }

    /**
     * Deletes a task based on the index provided in the input.
     * If the input is invalid or the index is out of range, it displays an error message.
     *
     * @param idx The index of the item to be deleted.
     */
    public String delete(int idx) {
        if (getTaskListLength() < idx || idx < 1) {
            return "Your inputted index is invalid, human.";
        }

        Task task = tasks.get(idx - 1);
        tasks.remove(idx - 1);
        storage.saveToDisk(this);
        return SEPARATOR + "Noted. I've removed this task:\n"
                + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Adds a new To\Do task to the list based on the input.
     * If the input does not contain a valid description, it displays an error message.
     *
     * @param input The user input containing the full command.
     */
    public String addToDo(String input) {
        if (input.length() <= 5) {
            return "You forgot to input your task, human.";
        }

        ToDo task = new ToDo(input.substring(5), false);

        tasks.add(task);
        storage.saveToDisk(this);

        return SEPARATOR + String.format("Alright, I've added the task:\n "
                        + "%s\nNow you have %d tasks in the list.\n",
                                        task, tasks.size())
                                        + SEPARATOR;
    }

    /**
     * Adds a new Deadline task to the list based on the input.
     * If the input does not contain a valid description or deadline, it displays an error message.
     *
     * @param input The user input containing the full command.
     */
    public String addDeadline(String input) {
        if (input.length() <= 9 || !input.contains("/by")) {
            return "You forgot to input your task, human.";
        }
        String[] parser = input.split("/by ");

        if (parser.length < 2 || parser[1].trim().isEmpty()) {
            return "You forgot to enter the deadline, human.";
        }
        String description = parser[0].substring(9);
        String deadline = parser[1];

        if (deadline.isEmpty()) {
            return "You forgot to enter the due date, human.";
        } else if (description.isEmpty()) {
            return "Your task has no description, human.";
        }

        LocalDateTime date = Parser.stringToDateTime(deadline);

        if (date == null) {
            return "Your date is in the wrong format, human.";
        }

        Deadline task = new Deadline(description, false, date);

        tasks.add(task);
        storage.saveToDisk(this);

        return SEPARATOR + String.format("Alright, I've added the task:\n"
                                        + "%s\nNow you have %d tasks in the list.\n",
                                    task, tasks.size()) + SEPARATOR;
    }

    /**
     * Adds a new Event task to the list based on the input.
     * If the input does not contain valid times or description, it displays an error message.
     *
     * @param input The user input containing the full command.
     */
    public String addEvent(String input) {
        if (input.length() <= 6 || input.substring(6)
                .trim()
                        .isEmpty()) {
            return "You forgot to input your task, human.";
        } else if (!input.contains("/from") || !input.contains("/to")) {
            return "You forgot to input times in your task, human.";
        }

        String[] parser = input.split("/from ");

        if (parser.length < 2 || parser[1].trim()
                .isEmpty()) {
            return "Please input a time, human.";
        }

        String description = parser[0].substring(6);
        String times = parser[1];

        String[] fromAndTo = times.split("/to ");

        if (fromAndTo.length < 2 || fromAndTo[0].trim().
                isEmpty() || fromAndTo[1].trim()
                        .isEmpty()) {
            return "You forgot to put times in your event, human.";
        }

        String from = fromAndTo[0].trim();
        String to = fromAndTo[1].trim();

        LocalDateTime fromDate = Parser.stringToDateTime(from);
        LocalDateTime toDate = Parser.stringToDateTime(to);

        if (fromDate == null) {
            return "Your from date is in the wrong format, human.";
        } else if (toDate == null) {
            return "Your to date is in the wrong format, human.";
        }

        Event task = new Event(description, false, fromDate, toDate);

        tasks.add(task);
        storage.saveToDisk(this);

        return SEPARATOR + String.format("Alright, I've added the task:\n " +
                        "%s\nNow you have %d tasks in the list.\n",
                                task, tasks.size())
                                        + SEPARATOR;
    }

    public String findTask(String input) {
        StringBuilder resultString = new StringBuilder(SEPARATOR
                    + "Here are the matching tasks in your list:\n");
        ArrayList<Task> foundTasks = new ArrayList<>();

        if (input.length() <= 5) {
            return "Human, you did not type your task.";
        }

        String query = input.substring(5).trim();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(query.toLowerCase())) {
                foundTasks.add(task);
                resultString.append(task).append("\n");
            }
        }

        if (foundTasks.isEmpty()) {
            return "No tasks match your search, human.";
        } else {
            return resultString.toString();
        }
    }
}