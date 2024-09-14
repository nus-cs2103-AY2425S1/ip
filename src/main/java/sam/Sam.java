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
 *
 */
public class Sam {
    private Ui ui;
    private Storage storage;
    private Items items;

    /**
     * Constructs a new instance of the Sam class.
     *
     * @param filePath The file path to the storage file.
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
     * @param input the input from the user
     * @return the response based on the input
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        
        try {
            if (input.equals("bye")) {
                response.append("Goodbye!\n");
                if (items.getSize() > 0) {
                    storage.save(items.getItems());
                }

            } else if (input.equals("list")) {
                response.append("Here are the tasks in your list:\n");
                response.append(items.toString()).append("\n");
            } else if (input.startsWith("mark")) {
                response.append(markItemDone(input));
            } else if (input.startsWith("find")) {
                response.append(findItem(input));
            } else if (input.startsWith("unmark")) {
                response.append(markItemUndone(input));
            } else if (input.startsWith("delete")) {
                response.append(deleteItem(input));
            } else {
                response.append(addItem(input));
            }
            storage.save(items.getItems());
        } catch (SamException e) {
            response.append(e.getMessage()).append("\n");
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            response.append("Invalid input format. Please follow the correct format for tasks.\n");
        } catch (DateTimeParseException e) {
            response.append("Invalid date format. Please use dd-MM-yyyy.\n");
        } catch (IOException e) {
            response.append("An error occurred while saving the tasks.\n");
        } catch (Exception e) {
            response.append("An error occurred. Please try again.\n");
        }
        
        return response.toString();
    }

    /**
     * Marks a task as done based on the user input.
     *
     * @param input the user input containing the task number to mark as done
     * @return the message indicating the task has been marked as done
     * @throws SamException if the task number is invalid or out of range
     */
    private String markItemDone(String input) throws SamException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            items.getItem(index).markAsDone();
            return "Nice! I've marked this task as done:\n" + items.getItem(index).toString() + "\n";
        } catch (NumberFormatException e) {
            throw new SamException("Invalid task number. Please enter a valid task number to mark.");
        } catch (IndexOutOfBoundsException e) {
            throw new SamException("Task number out of range. Please enter a valid task number to mark.");
        }
    }

    /**
     * Marks a task as undone based on the user input.
     *
     * @param input the user input containing the task number to be marked as undone
     * @return the message indicating the task has been marked as undone
     * @throws SamException if the task number is invalid or out of range
     */
    private String markItemUndone(String input) throws SamException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            items.getItem(index).markAsUndone();
            return "OK, I've marked this task as not done yet:\n" + items.getItem(index).toString() + "\n";
        } catch (NumberFormatException e) {
            throw new SamException("Invalid task number. Please enter a valid task number to unmark.");
        } catch (IndexOutOfBoundsException e) {
            throw new SamException("Task number out of range. Please enter a valid task number to unmark.");
        }
    }

    /**
     * Deletes an item from the list based on the given input.
     *
     * @param input the input string containing the task number to be deleted
     * @return the message indicating the task has been deleted
     * @throws SamException if the input is not a valid task number or if the task number is out of range
     */
    private String deleteItem(String input) throws SamException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Item item = items.getItem(index);
            items.deleteItem(index);
            return "Noted. I've removed this task:\n" + item.toString() + "\n" +
                    String.format("Now you have %d tasks in the list.\n", items.getSize());
        } catch (NumberFormatException e) {
            throw new SamException("Invalid task number. Please enter a valid task number to delete.");
        } catch (IndexOutOfBoundsException e) {
            throw new SamException("Task number out of range. Please enter a valid task number to delete.");
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
        String[] parts = input.split(" ");
        String itemType = parts[0];
        String response;

        if ("todo".equals(itemType)) {
            if (parts.length == 1 || "".equals(input.substring(5).trim())) {
                throw new SamException("Please include the name of the ToDo task.");
            }
            items.addItem(new ToDo(input.substring(5).trim()));
            response = "Got it. I've added this task:\n" + items.getLastAdded().toString();
        } else if ("deadline".equals(itemType)) {
            String[] dParts = input.split(" /by ");
            if (dParts.length < 2 || "".equals(dParts[1].trim())) {
                throw new SamException("Please include the date of the Deadline task.");
            }
            items.addItem(new Deadline(dParts[0].substring(9).trim(), dParts[1].trim()));
            response = "Got it. I've added this task:\n" + items.getLastAdded().toString();
        } else if ("event".equals(itemType)) {
            String[] eParts = input.split(" /from | /to ");
            if (eParts.length < 3 || "".equals(eParts[1].trim()) || "".equals(eParts[2].trim())) {
                throw new SamException("Please include the dates for the Event task.");
            }
            items.addItem(new Event(eParts[0].substring(6).trim(), eParts[1].trim(), eParts[2].trim()));
            response = "Got it. I've added this task:\n" + items.getLastAdded().toString();
        } else {
            throw new SamException("I'm sorry, but I don't know what that means.");
        }

        return response + String.format("\nNow you have %d tasks in the list.\n", items.getSize());
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

    public static void main(String[] args) {
        Sam samApp = new Sam();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Sam Task Manager!");
    
        while (true) {
            String input = scanner.nextLine();
            assert input != null && !input.isEmpty() : "Input should not be null or empty";
            String response = samApp.getResponse(input);
            System.out.println(response);
            if (response.equals("Goodbye!\n")) {
                System.exit(0);
            }
        }
    }
}