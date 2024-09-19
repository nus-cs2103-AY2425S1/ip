package crack;

import java.io.IOException;

/**
 * The Crack class serves as the main entry point for the application.
 * It initializes the user interface (Ui), task storage (Storage),
 * and task management (TaskList). It also contains the main loop that
 * processes user commands.
 */
public class Crack {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Crack instance, initializing the Ui, Storage,
     * and TaskList. Attempts to load tasks from the file specified
     * by the filePath. If loading fails, an empty task list is initialized.
     *
     * @param filePath the path to the file where tasks are stored.
     */
    public Crack(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks(ui));
        } catch (IOException e) {
            ui.showError("Unable to load tasks from file.");
            tasks = new TaskList(); // Initialize with an empty task list if load fails
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String command = Parser.parseCommand(input); // Get the command part of the input

        switch (command) {
        case "bye":
            return ui.showGoodbye();
        case "list":
            if (tasks.isEmpty()) {
                return "Your task list is empty.";
            } else {
                return "Here are the tasks in your list:\n" + tasks.listTasks();
            }
        case "mark":
            try {
                int index = Parser.parseTaskNumber(input);
                tasks.getTask(index).markAsDone();
                storage.saveTasks(tasks.getTasks(), ui);
                return "Nice! I've marked this task as done:\n   " + tasks.getTask(index);
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                return e.getMessage();
            }
        case "unmark":
            try {
                int index = Parser.parseTaskNumber(input);
                tasks.getTask(index).unmark();
                storage.saveTasks(tasks.getTasks(), ui);
                return "OK, I've marked this task as not done yet:\n   " + tasks.getTask(index);
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                return ui.showError(e.getMessage());
            }
        case "todo":
            try {
                String description = Parser.parseTodoDescription(input);
                Todo newTodo = new Todo(description);
                tasks.addTask(newTodo);
                storage.saveTasks(tasks.getTasks(), ui);
                return ui.showTaskAdded(newTodo, tasks.getSize());
            } catch (IllegalArgumentException e) {
                return ui.showError(e.getMessage());
            }
        case "deadline":
            try {
                String[] deadlineDetails = Parser.parseDeadline(input);
                Deadline newDeadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                tasks.addTask(newDeadline);
                storage.saveTasks(tasks.getTasks(), ui);
                return ui.showTaskAdded(newDeadline, tasks.getSize());
            } catch (IllegalArgumentException e) {
                return ui.showError(e.getMessage());
            }
        case "event":
            try {
                String[] eventDetails = Parser.parseEvent(input);
                Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                tasks.addTask(newEvent);
                storage.saveTasks(tasks.getTasks(), ui);
                return ui.showTaskAdded(newEvent, tasks.getSize());
            } catch (IllegalArgumentException e) {
                return ui.showError(e.getMessage());
            }
        case "find":
            try {
                String keyword = input.substring(5).trim(); // Extract the keyword
                if (keyword.isEmpty()) {
                    throw new IllegalArgumentException("Keyword cannot be empty.");
                }
                return ui.showMatchingTasks(tasks.findTasks(keyword));
            } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
                return ui.showError(e.getMessage());
            }
        case "delete":
            try {
                int index = Parser.parseTaskNumber(input);
                Task removedTask = tasks.removeTask(index);
                storage.saveTasks(tasks.getTasks(), ui);
                return ("Noted. I've removed this task:\n   " + removedTask);
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                return ui.showError(e.getMessage());
            }
        default:
            return ui.showError("Invalid Command.");
        }
    }


    /**
     * Starts the main loop of the application. It repeatedly reads user input,
     * parses commands, and performs actions based on the input. The loop continues
     * until the user enters the "bye" command, at which point the application
     * exits.
     */
    public void run() {
        while (true) {
            String input = ui.readCommand();
            String command = Parser.parseCommand(input);  // Get the command part of the input

            switch (command) {
            case "bye":
                handleByeCommand();
                return;
            case "list":
                handleListCommand();
                break;
            case "mark":
                handleMarkCommand(input);
                break;
            case "unmark":
                handleUnmarkCommand(input);
                break;
            case "todo":
                handleTodoCommand(input);
                break;
            case "deadline":
                handleDeadlineCommand(input);
                break;
            case "event":
                handleEventCommand(input);
                break;
            case "find":
                handleFindCommand(input);
                break;
            case "delete":
                handleDeleteCommand(input);
                break;
            default:
                ui.showError("Invalid Command.");
                break;
            }
        }
    }

    /**
     * Handles the "bye" command by displaying a goodbye message and closing the application.
     */
    private void handleByeCommand() {
        ui.showGoodbye();
        ui.close();
    }

    /**
     * Handles the "list" command by displaying all tasks in the task list.
     */
    private void handleListCommand() {
        if (tasks.isEmpty()) {
            ui.showMessage("Your task list is empty.");
        } else {
            ui.showMessage("Here are the tasks in your list:\n" + tasks.listTasks());
        }
    }

    /**
     * Handles the "mark" command by marking a specified task as done.
     *
     * @param input The user input containing the task number to mark as done.
     */
    private void handleMarkCommand(String input) {
        try {
            int index = Parser.parseTaskNumber(input);
            tasks.getTask(index).markAsDone();
            ui.showMessage("Nice! I've marked this task as done:\n   " + tasks.getTask(index));
            storage.saveTasks(tasks.getTasks(), ui);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the "unmark" command by marking a specified task as not done.
     *
     * @param input The user input containing the task number to unmark.
     */
    private void handleUnmarkCommand(String input) {
        try {
            int index = Parser.parseTaskNumber(input);
            tasks.getTask(index).unmark();
            ui.showMessage("OK, I've marked this task as not done yet:\n   " + tasks.getTask(index));
            storage.saveTasks(tasks.getTasks(), ui);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the "todo" command by adding a new Todo task to the task list.
     *
     * @param input The user input containing the description of the Todo task.
     */
    private void handleTodoCommand(String input) {
        try {
            String description = Parser.parseTodoDescription(input);
            Todo newTodo = new Todo(description);
            tasks.addTask(newTodo);
            ui.showTaskAdded(newTodo, tasks.getSize());
            storage.saveTasks(tasks.getTasks(), ui);
        } catch (IllegalArgumentException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the "deadline" command by adding a new Deadline task to the task list.
     *
     * @param input The user input containing the description and deadline date.
     */
    private void handleDeadlineCommand(String input) {
        try {
            String[] deadlineDetails = Parser.parseDeadline(input);
            Deadline newDeadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            tasks.addTask(newDeadline);
            ui.showTaskAdded(newDeadline, tasks.getSize());
            storage.saveTasks(tasks.getTasks(), ui);
        } catch (IllegalArgumentException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the "event" command by adding a new Event task to the task list.
     *
     * @param input The user input containing the description, start date, and end date.
     */
    private void handleEventCommand(String input) {
        try {
            String[] eventDetails = Parser.parseEvent(input);
            Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
            tasks.addTask(newEvent);
            ui.showTaskAdded(newEvent, tasks.getSize());
            storage.saveTasks(tasks.getTasks(), ui);
        } catch (IllegalArgumentException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the "find" command by searching for tasks that match a keyword.
     *
     * @param input The user input containing the keyword to search for.
     */
    private void handleFindCommand(String input) {
        try {
            String keyword = input.substring(5).trim();  // Extract the keyword
            if (keyword.isEmpty()) {
                throw new IllegalArgumentException("Keyword cannot be empty.");
            }
            ui.showMatchingTasks(tasks.findTasks(keyword));
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Handles the "delete" command by removing a specified task from the task list.
     *
     * @param input The user input containing the task number to delete.
     */
    private void handleDeleteCommand(String input) {
        try {
            int index = Parser.parseTaskNumber(input);
            Task removedTask = tasks.removeTask(index);
            ui.showMessage("Noted. I've removed this task:\n   " + removedTask);
            storage.saveTasks(tasks.getTasks(), ui);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * The main method that initializes the Crack application and starts its execution.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Crack("./data/crack.txt").run(); // Passes file path to the constructor
    }
}
