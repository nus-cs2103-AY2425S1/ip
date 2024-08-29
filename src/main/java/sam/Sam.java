package sam;
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
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;


public class Sam {
    private Storage storage;
    private Items items;
    private Ui ui;

    public Sam(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            // Initialize Items with tasks loaded from storage
            List<Item> loadedItems = storage.load();
            items = new Items(loadedItems);
        } catch (IOException e) {
            ui.showMessage("Error loading tasks. Starting with an empty list.");
            items = new Items();  // Start with an empty list if loading fails
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            ui.showLine();
            try {
                if (input.equals("bye")) {
                    ui.showGoodbye();
                    if (items.getSize() > 0) {
                        storage.save(items.getItems());
                    }
                    break;
                } else if (input.equals("list")) {
                    ui.showMessage("Here are the tasks in your list:");
                    ui.showMessage(items.toString());
                } else if (input.startsWith("mark")) {
                    markItemDone(input);
                } else if (input.startsWith("unmark")) {
                    markItemUndone(input);
                } else if (input.startsWith("delete")) {
                    deleteItem(input);
                } else {
                    addItem(input);
                }
                storage.save(items.getItems());
            } catch (SamException e) {
                ui.showMessage(e.getMessage());
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                ui.showMessage("Invalid input format. Please follow the correct format for tasks.");
            } catch (DateTimeParseException e) {
                ui.showMessage("Invalid date format. Please use dd-MM-yyyy.");
            } catch (IOException e) {
                ui.showMessage("An error occurred while saving the tasks.");
            } catch (Exception e) {
                ui.showMessage("An error occurred. Please try again.");
            }
            ui.showLine();
        }
        scanner.close();
    }

    /**
     * Marks a task as done based on the user input.
     *
     * @param input the user input containing the task number to mark as done
     * @throws SamException if the task number is invalid or out of range
     */
    private void markItemDone(String input) throws SamException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            items.getItem(index).markAsDone();
            ui.showMessage("Nice! I've marked this task as done:");
            ui.showMessage(items.getItem(index).toString());
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
     * @throws SamException if the task number is invalid or out of range
     */
    private void markItemUndone(String input) throws SamException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            items.getItem(index).markAsUndone();
            ui.showMessage("OK, I've marked this task as not done yet:");
            ui.showMessage(items.getItem(index).toString());
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
     * @throws SamException if the input is not a valid task number or if the task number is out of range
     */
    private void deleteItem(String input) throws SamException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            Item item = items.getItem(index);
            items.deleteItem(index);
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage(item.toString());
            ui.showMessage(String.format("Now you have %d tasks in the list", items.getSize()));
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
     * @throws SamException if the input is invalid or incomplete
     */
    private void addItem(String input) throws SamException {
        String[] parts = input.split(" ");
        String itemType = parts[0];

        if ("todo".equals(itemType)) {
            if (parts.length == 1 || "".equals(input.substring(5).trim())) {
                throw new SamException("Please include the name of the ToDo task.");
            }
            items.addItem(new ToDo(input.substring(5).trim()));
        } else if ("deadline".equals(itemType)) {
            String[] Dparts = input.split(" /by ");
            if (Dparts.length < 2 || "".equals(Dparts[1].trim())) {
                throw new SamException("Please include the date of the Deadline task.");
            }
            items.addItem(new Deadline(Dparts[0].substring(9).trim(), Dparts[1].trim()));
        } else if ("event".equals(itemType)) {
            String[] Eparts = input.split(" /from | /to ");
            if (Eparts.length < 3 || "".equals(Eparts[1].trim()) || "".equals(Eparts[2].trim())) {
                throw new SamException("Please include the dates for the Event task.");
            }
            items.addItem(new Event(Eparts[0].substring(6).trim(), Eparts[1].trim(), Eparts[2].trim()));
        } else {
            throw new SamException("I'm sorry, but I don't know what that means.");
        }

        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(items.getLastAdded().toString());
        ui.showMessage(String.format("Now you have %d tasks in the list", items.getSize()));
    }

    public static void main(String[] args) {
        new Sam("data/Sam.txt").run();
    }
}