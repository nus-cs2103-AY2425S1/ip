import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Angel chatbot application.
 * Handles user commands, task management, and data persistence.
 */
public class Angel {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final String FILE_PATH = "./data/Angel.txt";
    private Storage storage;
    private ArrayList<Task> tasks;

    /**
     * Constructs an Angel instance and initializes the task list.
     * Loads tasks from the storage file if it exists.
     */
    public Angel() {
        storage = new Storage(FILE_PATH);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
            tasks = new ArrayList<>();
        }
    }

    /**
     * Runs the chatbot application.
     * Continuously reads user input, processes commands, and updates task list.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Angel\nWhat can I do for you?");

        while (true) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(" ", 2);
                String command = parts[0];

                switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    listTasks();
                    break;
                case "todo":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new InvalidCommandException(
                                "The description of a todo cannot be empty. Please specify a task after 'todo'."
                        );
                    }
                    addTask(new ToDo(parts[1]));
                    break;
                case "deadline":
                    if (parts.length < 2 || !parts[1].contains(" /by ")) {
                        throw new InvalidCommandException(
                                "The description of a deadline task must include a '/by' followed by the due date/time. Example: 'deadline return book /by 2019-10-10 1800'."
                        );
                    }
                    String[] deadlineDetails = parts[1].split(" /by ");
                    LocalDateTime deadlineDate = parseDate(deadlineDetails[1]);
                    addTask(new Deadline(deadlineDetails[0], deadlineDate));
                    break;
                case "event":
                    if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                        throw new InvalidCommandException(
                                "The description of an event must include '/from' and '/to' followed by the start and end times. Example: 'event project meeting /from 2019-10-10 1800 /to 2019-10-10 2000'."
                        );
                    }
                    String[] eventDetails = parts[1].split(" /from ");
                    String[] times = eventDetails[1].split(" /to ");
                    LocalDateTime fromDate = parseDate(times[0]);
                    LocalDateTime toDate = parseDate(times[1]);
                    addTask(new Event(eventDetails[0], fromDate, toDate));
                    break;
                case "mark":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new InvalidCommandException(
                                "Please specify the task number to mark."
                        );
                    }
                    try {
                        int taskIndex = Integer.parseInt(parts[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size()) {
                            throw new TaskNotFoundException("Task number " + (taskIndex + 1) + " does not exist.");
                        }
                        markTask(taskIndex);
                    } catch (NumberFormatException e) {
                        throw new InvalidCommandException(
                                "Invalid task number for 'mark'. Please enter a valid number after 'mark'."
                        );
                    }
                    break;
                case "unmark":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new InvalidCommandException(
                                "Please specify the task number to unmark."
                        );
                    }
                    try {
                        int taskIndex = Integer.parseInt(parts[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size()) {
                            throw new TaskNotFoundException("Task number " + (taskIndex + 1) + " does not exist.");
                        }
                        unmarkTask(taskIndex);
                    } catch (NumberFormatException e) {
                        throw new InvalidCommandException(
                                "Invalid task number for 'unmark'. Please enter a valid number after 'unmark'."
                        );
                    }
                    break;
                case "delete":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new InvalidCommandException(
                                "Please specify the task number to delete."
                        );
                    }
                    try {
                        int taskIndex = Integer.parseInt(parts[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size()) {
                            throw new TaskNotFoundException("Task number " + (taskIndex + 1) + " does not exist.");
                        }
                        deleteTask(taskIndex);
                    } catch (NumberFormatException e) {
                        throw new InvalidCommandException(
                                "Invalid task number for 'delete'. Please enter a valid number after 'delete'."
                        );
                    }
                    break;
                default:
                    throw new InvalidCommandException(
                            "Unknown command: " + command + ". Please enter a valid command (e.g., list, todo, deadline, event, mark, unmark, delete, bye)."
                    );
                }
            } catch (InvalidCommandException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (TaskNotFoundException e) {
                System.out.println("Error: " + e.getMessage() + " Please check the task number and try again.");
            } catch (AngelException e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Parses a string into a LocalDateTime object using the predefined input format.
     *
     * @param dateTimeString The string to be parsed.
     * @return The corresponding LocalDateTime object.
     * @throws InvalidCommandException If the string cannot be parsed.
     */
    private LocalDateTime parseDate(String dateTimeString) throws InvalidCommandException {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    /**
     * Lists all tasks in the current task list.
     */
    private void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Adds a new task to the list and saves the updated list.
     *
     * @param task The task to be added.
     * @throws AngelException If there is an error while saving tasks.
     */
    private void addTask(Task task) throws AngelException {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n  " + task);
        saveTasks();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked.
     * @throws AngelException If the task does not exist or if there is an error while saving tasks.
     */
    private void markTask(int index) throws AngelException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException("Task not found: " + (index + 1));
        }
        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
        saveTasks();
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The index of the task to be unmarked.
     * @throws AngelException If the task does not exist or if there is an error while saving tasks.
     */
    private void unmarkTask(int index) throws AngelException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException("Task not found: " + (index + 1));
        }
        tasks.get(index).unmark();
        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
        saveTasks();
    }

    /**
     * Deletes a task from the list and saves the updated list.
     *
     * @param index The index of the task to be deleted.
     * @throws AngelException If the task does not exist or if there is an error while saving tasks.
     */
    private void deleteTask(int index) throws AngelException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskNotFoundException("Task not found: " + (index + 1));
        }
        Task task = tasks.remove(index);
        System.out.println("Noted. I've removed this task:\n  " + task);
        saveTasks();
    }

    /**
     * Saves the current task list to the storage file.
     *
     * @throws AngelException If there is an error while saving tasks.
     */
    private void saveTasks() throws AngelException {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new AngelException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Entry point of the Angel application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Angel().run();
    }
}
