package sam;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * The Sam class represents a task management application.
 * It allows users to add, mark, unmark, and delete tasks.
 * Tasks can be of type ToDo, Deadline, or Event.
 * The application provides a command-line interface for users to interact with.
 * Users can input commands to perform various operations on the tasks.
 * The application also supports saving and loading tasks from a file.
 *
 * The Sam class contains methods to handle user commands and perform the corresponding operations.
 * It uses the Ui class to display messages to the user.
 * It uses the Storage class to save and load tasks from a file.
 * It uses the Items class to store and manage the list of tasks.
 *
 * The main method creates an instance of the Sam class and starts the application.
 */
public class Sam {
    private Ui ui;
    private Storage storage;
    private Items items;
    private boolean isExit = false; // Flag to control application exit

    /**
     * Constructs a new instance of the Sam class.
     */
    public Sam() {
        String filePath = "data/Sam.txt";
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            // Initialize Items with tasks loaded from storage
            List<Item> loadedItems = storage.load();
            items = new Items(loadedItems);
        } catch (IOException e) {
            ui.showMessage("Error loading tasks. Starting with an empty list.");
            items = new Items(); // Start with an empty list if loading fails
        }
    }

    /**
     * Handles a single user input and returns the response as a string.
     *
     * @param input the input from the user
     * @return the response based on the input
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            CommandType commandType = parseCommandType(input);

            switch (commandType) {
            case BYE:
                response.append("Goodbye!\n");
                isExit = true;
                saveTasks();
                break;
            case LIST:
                response.append("Here are the tasks in your list:\n");
                response.append(items.toString()).append("\n");
                break;
            case MARK:
                response.append(markItemDone(input));
                saveTasks();
                break;
            case UNMARK:
                response.append(markItemUndone(input));
                saveTasks();
                break;
            case DELETE:
                response.append(deleteItem(input));
                saveTasks();
                break;
            case FIND:
                response.append(findItem(input));
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                response.append(addItem(input));
                saveTasks();
                break;
            case HELP:
                response.append(getHelpMessage());
                break;
            default:
                throw new SamException("I'm sorry, but I don't know what that means.");
            }
        } catch (SamException e) {
            response.append(e.getMessage()).append("\n");
        } catch (IOException e) {
            response.append("An error occurred while saving the tasks.\n");
        } catch (DateTimeParseException e) {
            response.append("Invalid date format. Please use dd-MM-yyyy.\n");
        }
        return response.toString();
    }

    /**
     * Parses the command type from the user input.
     *
     * @param input the user input
     * @return the CommandType enum representing the command
     */
    private CommandType parseCommandType(String input) {
        String[] parts = input.trim().split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return CommandType.BYE;
        case "list":
            return CommandType.LIST;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "delete":
            return CommandType.DELETE;
        case "find":
            return CommandType.FIND;
        case "todo":
            return CommandType.TODO;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        case "help":
            return CommandType.HELP;
        default:
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Returns the help message containing the list of available commands.
     *
     * @return a string containing the help message
     */
    private String getHelpMessage() {
        return "Here are the available commands:\n"
                + "1. list - Displays all tasks in your list.\n"
                + "2. todo <description> - Adds a ToDo task.\n"
                + "3. deadline <description> /by <date> - Adds a Deadline task.\n"
                + "4. event <description> /from <start date> /to <end date> - Adds an Event task.\n"
                + "5. mark <task number> - Marks a task as done.\n"
                + "6. unmark <task number> - Marks a task as not done.\n"
                + "7. delete <task number> - Deletes a task from the list.\n"
                + "8. find <keyword> - Finds tasks containing the keyword.\n"
                + "9. help - Displays this help message.\n"
                + "10. bye - Exits the application.\n";
    }

    /**
     * Saves the current tasks to storage.
     *
     * @throws IOException if an I/O error occurs
     */
    private void saveTasks() throws IOException {
        storage.save(items.getItems());
    }

    /**
     * Marks a task as done based on the user input.
     *
     * @param input the user input containing the task number to mark as done
     * @return the message indicating the task has been marked as done
     * @throws SamException if the task number is invalid or out of range
     */
    private String markItemDone(String input) throws SamException {
        int index = parseTaskIndex(input);
        Item item = items.getItem(index);
        item.markAsDone();
        return "Nice! I've marked this task as done:\n" + item.toString() + "\n";
    }

    /**
     * Marks a task as undone based on the user input.
     *
     * @param input the user input containing the task number to be marked as undone
     * @return the message indicating the task has been marked as undone
     * @throws SamException if the task number is invalid or out of range
     */
    private String markItemUndone(String input) throws SamException {
        int index = parseTaskIndex(input);
        Item item = items.getItem(index);
        item.markAsUndone();
        return "OK, I've marked this task as not done yet:\n" + item.toString() + "\n";
    }

    /**
     * Deletes an item from the list based on the given input.
     *
     * @param input the input string containing the task number to be deleted
     * @return the message indicating the task has been deleted
     * @throws SamException if the input is not a valid task number or if the task number is out of range
     */
    private String deleteItem(String input) throws SamException {
        int index = parseTaskIndex(input);
        Item item = items.getItem(index);
        items.deleteItem(index);
        return "Noted. I've removed this task:\n" + item.toString() + "\n";
    }

    /**
     * Parses the task index from the user input.
     *
     * @param input the user input containing the task number
     * @return the zero-based index of the task
     * @throws SamException if the input is invalid or the index is out of range
     */
    private int parseTaskIndex(String input) throws SamException {
        String[] parts = input.trim().split(" ");
        if (parts.length < 2) {
            throw new SamException("Please specify the task number.");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= items.getSize()) {
                throw new SamException("Task number out of range. Please enter a valid task number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new SamException("Invalid task number. Please enter a valid task number.");
        }
    }

    /**
     * Adds a new task to the list based on the given input.
     *
     * @param input the input string representing the task to be added
     * @return the message indicating the task has been added
     * @throws SamException if the input is invalid or incomplete
     */
    private String addItem(String input) throws SamException {
        CommandType commandType = parseCommandType(input);
        String response;

        switch (commandType) {
        case TODO:
            response = addTodoItem(input);
            break;
        case DEADLINE:
            response = addDeadlineItem(input);
            break;
        case EVENT:
            response = addEventItem(input);
            break;
        default:
            throw new SamException("I'm sorry, but I don't know what that means.");
        }

        response += String.format("\nNow you have %d tasks in the list.\n", items.getSize());
        return response;
    }

    /**
     * Adds a new ToDo task to the list.
     *
     * @param input the user input containing the ToDo task description
     * @return the message indicating the task has been added
     * @throws SamException if the description is empty
     */
    private String addTodoItem(String input) throws SamException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new SamException("The description of a ToDo cannot be empty.");
        }
        Item todo = new ToDo(description);
        items.addItem(todo);
        return "Got it. I've added this task:\n" + todo.toString();
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param input the user input containing the Deadline task description and date
     * @return the message indicating the task has been added
     * @throws SamException if the description or date is empty
     */
    private String addDeadlineItem(String input) throws SamException {
        String[] parts = input.substring(8).split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new SamException("The description and date of a Deadline cannot be empty.");
        }
        Item deadline = new Deadline(parts[0].trim(), parts[1].trim());
        items.addItem(deadline);
        return "Got it. I've added this task:\n" + deadline.toString();
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param input the user input containing the Event task description and dates
     * @return the message indicating the task has been added
     * @throws SamException if the description or dates are empty
     */
    private String addEventItem(String input) throws SamException {
        String[] parts = input.substring(5).split(" /from | /to ", 3);
        if (parts.length < 3 || parts[0].trim().isEmpty()
                || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new SamException("The description and dates of an Event cannot be empty.");
        }
        Item event = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
        items.addItem(event);
        return "Got it. I've added this task:\n" + event.toString();
    }

    /**
     * Finds and returns tasks that match the given keyword.
     *
     * @param input the user input containing the keyword
     * @return the message with the tasks that match the keyword
     */
    private String findItem(String input) {
        String keyword = input.substring(5).trim();
        assert !keyword.isEmpty() : "Keyword should not be empty after trimming";
        List<Item> foundItems = items.findItems(keyword);
        StringBuilder response = new StringBuilder();

        if (foundItems.isEmpty()) {
            response.append("No tasks found with the keyword: ").append(keyword).append("\n");
        } else {
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundItems.size(); i++) {
                response.append((i + 1)).append(".").append(foundItems.get(i).toString()).append("\n");
            }
        }
        return response.toString();
    }

    /**
     * The main method that starts the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Sam samApp = new Sam();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Sam Task Manager!");

        while (!samApp.isExit) {
            String input = scanner.nextLine();
            assert input != null && !input.isEmpty() : "Input should not be null or empty";
            String response = samApp.getResponse(input);
            System.out.println(response);
        }
        scanner.close();
    }

    /**
     * Enum representing the different command types.
     */
    private enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, FIND, TODO, DEADLINE, EVENT, HELP, UNKNOWN
    }
}
