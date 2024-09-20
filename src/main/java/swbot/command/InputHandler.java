package swbot.command;

import java.util.ArrayList;

import swbot.BuzzException;
import swbot.tasks.Deadline;
import swbot.tasks.Event;
import swbot.tasks.Task;
import swbot.tasks.Todo;

/**
 * A class that handles all the inputs given by the user and parses through them switching between
 * different commands from the user.
 */
public class InputHandler {
    /* commands available in the chatbot */
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_FIND = "find";
    private static final String COMMAND_HELP = "help";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_UNMARK = "unmark";
    private ArrayList<Task> data;
    private Storage storage;

    /**
     * Creates an inputHandler object that will take care of all the user inputs
     *
     * @param data data to be sent to the output and where the chatbot stores the tasks
     * @param storage storage object which takes care of loading and saving tasks to the database
     */
    public InputHandler(ArrayList<Task> data, Storage storage) {
        this.data = data;
        this.storage = storage;
    }
    /**
     * Persists the current list of tasks to the storage medium.
     * This method calls the `saveTasks` method from the `storage` object,
     * which writes the tasks to an output file, ensuring that the task list
     * is saved and up-to-date.
     */
    private void saveTasks() {
        this.storage.saveTasks(data);
    }

    /**
     * Handles the majority of the user inputs through other functions in the InputHandler class
     *
     * @param input user input that is being passed to the chatbot
     * @throws BuzzException if any of the commands given by the user is not a valid one
     */
    public String overallHandler(String input) throws BuzzException {
        String command = input.split(" ")[0];
        switch (command) {
        case COMMAND_MARK:
            saveTasks();
            return this.markHandle(input);
        case COMMAND_UNMARK:
            saveTasks();
            return this.unmarkHandle(input);
        case COMMAND_DELETE:
            saveTasks();
            return this.deleteHandle(input);
        case COMMAND_LIST:
            return getString();
        case COMMAND_TODO:
            saveTasks();
            return this.todoHandle(input);
        case COMMAND_DEADLINE:
            saveTasks();
            return this.deadlineHandle(input);
        case COMMAND_EVENT:
            saveTasks();
            return this.eventHandle(input);
        case COMMAND_FIND:
            saveTasks();
            return this.findHandle(input);
        case COMMAND_HELP:
            return this.helpHandle();
        default:
            throw new BuzzException("GRRR! I do not know what that means. Try again! *bzzrg*");
        }
    }

    /**
     * Generates a string representation of the tasks in the list, with each task preceded by its index.
     *
     * @return a formatted string of all tasks, each on a new line with an index.
     */
    private String getString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            res.append((i + 1)).append(".").append(data.get(i).toString()).append("\n");
        }
        return res.toString();
    }

    /**
     * Provides a help message showing the available commands for the chatbot.
     *
     * @return a string containing the help message with the list of available commands
     */
    private String helpHandle() {
        return "Welcome to the Help Page! Here are some commands you can use:\n"
                + "- list : To view all your tasks\n"
                + "- mark/unmark : To complete or unmark a task\n"
                + "- delete : To delete a task\n"
                + "- find : To find a task matching a keyword\n"
                + "- todo [task] : Add a todo task\n"
                + "- deadline [task] /by [date] : Add a task with a deadline\n"
                + "- event [task] /at [date] : Add an event\n"
                + "- help : Display this help page";
    }

    /**
     * Handles the "mark" command by parsing through it and ensuring that it is a valid index
     *
     * @param input user input that is being passed to the chatbot
     * @throws BuzzException if the index being asked to mark is out of range/ non existent
     */
    public String markHandle(String input) throws BuzzException {
        if (input.length() <= 5) {
            throw new BuzzException("What's the index? Specify which task you want to mark! *bzzt*");
        }
        int index;
        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BuzzException("WRITE A NUMBER! *bzzt*");
        }
        if (index < 0 || index >= data.size()) {
            throw new BuzzException("OOPS!!! The task doesn't exist *BEEP*");
        }
        data.get(index).setDone(true);
        return "Mission accomplished! *bzzt*\n" + data.get(index).toString();
    }

    /**
     * Handles the "unmark" command from the user and marks a task in the list as not done
     *
     * @param input user input that is being passed to the chatbot
     * @throws BuzzException if the index being asked to unmark is out of range/non-existent
     */
    public String unmarkHandle(String input) throws BuzzException {
        if (input.length() <= 7) {
            throw new BuzzException("What's the index? Specify which task you want to unmark! *bzzt*");
        }
        int index;
        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BuzzException("WRITE A NUMBER! *bzzt*");
        }
        if (index < 0 || index >= data.size()) {
            throw new BuzzException("OOPS!!! The task doesn't exist *BEEP*");
        }
        data.get(index).setDone(false);
        return "Argh next time! *bzzt*\n" + data.get(index).toString();
    }

    /**
     * Handles the "delete" command from the user and deletes the specified task from the list
     *
     * @param input user input that is being passed to the chatbot for deletion
     * @throws BuzzException if the index being asked to delete is out of range or does not exist in the list
     */
    public String deleteHandle(String input) throws BuzzException {
        if (input.length() <= 7) {
            throw new BuzzException("What's the index? Specify which task you want to delete! *bzzt*");
        }
        int index;
        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (NumberFormatException e) {
            throw new BuzzException("WRITE A NUMBER! *bzzt*");
        }
        if (index < 0 || index >= data.size()) {
            throw new BuzzException("OOPS!!! The task doesn't exist *BEEP*");
        }
        Task task = data.remove(index);
        return "*POOF* Circuits fried! Deleted the mission.\n" + task.toString() + "\n"
                + "You currently have " + data.size() + " missions available *reeeee*";
    }

    /**
     * Handles the "todo" type task from the user and adds it into the tasklist
     *
     * @param input user input that is being passed to the chatbot to add to the list
     * @throws BuzzException if the description of the task was not specified
     */
    public String todoHandle(String input) throws BuzzException {
        if (input.length() <= 5) {
            throw new BuzzException("NOOO! Description is empty *crash*");
        }
        Task task = new Todo(input.substring(5));
        if (task.getDescription().trim().isEmpty()) {
            throw new BuzzException("An empty task?? I am not going to add it *boo*");
        }
        data.add(task);
        return "Understood boss. Added!\n" + task.toString() + "\n"
                + "You currently have " + data.size() + " missions available *reeeee*";
    }

    /**
     * Handles the addition of a "deadline" task into the list
     *
     * @param input user input that is being passed to the chatbot to add as a deadline
     * @throws BuzzException if the description of the deadline task is empty
     */
    public String deadlineHandle(String input) throws BuzzException {
        String[] parts = input.split("/by");
        if (parts.length < 2 || parts[0].length() <= 9) {
            throw new BuzzException("NOOO! Description is empty *crash*");
        }
        Task task = new Deadline(parts[0].substring(9), parts[1].trim());
        if (task.getDescription().trim().isEmpty()) {
            throw new BuzzException("An empty task?? I am not going to add it *boo*");
        }
        data.add(task);
        return "Understood boss. Added!\n" + task.toString() + "\n"
                + "You currently have " + data.size() + " missions available *reeeee*";
    }

    /**
     * Handles the "event" task type and adds it into the list as an event
     *
     * @param input user input that is being passed to the chatbot to add as an event
     * @throws BuzzException if description of the event is empty
     */
    public String eventHandle(String input) throws BuzzException {
        String[] parts = input.split(" /from | /to ");
        if (parts.length != 3) {
            throw new BuzzException("Invalid event format. Correct format is: event description /from startTime "
                    + "/to endTime");
        }
        String description = parts[0].trim();
        String fromDateTime = parts[1].trim();
        String toDateTime = parts[2].trim();

        validate(description, fromDateTime, toDateTime);

        Task task = new Event(description, fromDateTime, toDateTime);
        if (task.getDescription().trim().isEmpty()) {
            throw new BuzzException("An empty task?? I am not going to add it *boo*");
        }
        data.add(task);
        return "Understood boss. Added!\n" + task.toString() + "\n"
                + "You currently have " + data.size() + " missions available *reeeee*";
    }

    /**
     * Handles the find command by the user and tries to find the corresponding keyword.
     *
     * @param input user input provided to the chatbot
     * @throws BuzzException if description is empty
     */
    public String findHandle(String input) throws BuzzException {
        StringBuilder result = new StringBuilder("I have found a few matches sir! *wooop*\n");
        if (input.length() <= 5) {
            throw new BuzzException("NOOO! Description is empty *crash*");
        }
        String wordSearch = input.substring(5).trim();
        int tasks = 1;
        for (int i = 0; i < data.size(); i++) {
            Task task = data.get(i);
            if (task.getDescription().contains(wordSearch)) {
                result.append(tasks).append(".").append(task.toString()).append("\n");
                tasks++;
            }
        }
        if (tasks == 1) {
            return "Sorry boss can't find anything :(";
        }
        return result.toString();
    }

    /**
     * Validates the description and time fields for an event.
     *
     * @param description the description of the event
     * @param fromDateTime the start date and time of the event
     * @param toDateTime the end date and time of the event
     * @throws BuzzException if any of the provided fields are empty
     */
    private void validate(String description, String fromDateTime, String toDateTime) throws BuzzException {
        if (description.isEmpty()) {
            throw new BuzzException("OOPS!!! The description of an event cannot be empty.");
        }
        if (fromDateTime.isEmpty()) {
            throw new BuzzException("OOPS!!! The start time of an event cannot be empty.");
        }
        if (toDateTime.isEmpty()) {
            throw new BuzzException("OOPS!!! The end time of an event cannot be empty.");
        }
    }
}
