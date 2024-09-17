package utilities;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The Parser class processes user input and executes the corresponding actions in the Bigmouth chatbot.
 */
public class Parser {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Scanner scanner;

    /**
     * Constructs a new Parser with the specified Ui, TaskList, Storage, and Scanner.
     *
     * @param ui The user interface handler.
     * @param tasks The task list that stores all the tasks.
     * @param s The storage handler for saving and loading tasks.
     * @param scanner The scanner to capture user input.
     */
    public Parser(Ui ui, TaskList tasks, Storage s, Scanner scanner) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = s;
        this.scanner = scanner;
    }

    /**
     * Parses a date/time string and returns the corresponding LocalDateTime object.
     *
     * @param input The input string in the format 'M/d/yyyy HHmm'.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws BigmouthException If the input format is incorrect.
     */
    static LocalDateTime parseDateTime(String input) throws BigmouthException {
        try {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new BigmouthException("OOPS! Please enter the date/time " +
                    "in the format 'M/d/yyyy HHmm'.");
        }
    }

    /**
     * Continuously reads user input, parses commands, and performs actions such as adding tasks,
     * marking tasks as done, or displaying the task list. Terminates when the user types 'bye'.
     */
    protected void parseInput() {
        while (true) {
            String userInput = scanner.nextLine();

            try {
                if (userInput.equals("bye")) {
                    ui.showGoodbyeMessage();
                    break;
                }

                if (userInput.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new BigmouthException("Your task list is empty!");
                    }
                    ui.showTaskList(tasks);
                } else if (userInput.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. " +
                                "Please enter a valid task number.");
                    }
                    tasks.get(taskNumber).markAsDone();
                    storage.saveToFile();
                    ui.showTaskMarked(tasks.get(taskNumber));
                } else if (userInput.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. " +
                                "Please enter a valid task number.");
                    }
                    tasks.get(taskNumber).markAsNotDone();
                    storage.saveToFile();
                    ui.showTaskUnmarked(tasks.get(taskNumber));
                } else if (userInput.startsWith("todo ")) {
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of " +
                                "a todo cannot be empty.");
                    }
                    Todo curr = new Todo(description);
                    tasks.add(curr);
                    storage.saveToFile();
                    ui.showTaskAdded(curr, tasks.size());
                } else if (userInput.startsWith("deadline ")) {
                    String[] parts = userInput.split(" /by ");
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        throw new BigmouthException("OOPS! The deadline " +
                                "command is missing a date/time.");
                    }
                    String description = parts[0].substring(9).trim();
                    LocalDateTime by = Parser.parseDateTime(parts[1].trim());
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of " +
                                "a deadline cannot be empty.");
                    }
                    Deadline curr = new Deadline(description, by);
                    tasks.add(curr);
                    storage.saveToFile();
                    ui.showTaskAdded(curr, tasks.size());
                } else if (userInput.startsWith("event ")) {
                    String[] parts = userInput.split(" /from | /to ");
                    if (parts.length < 3 || parts[1].trim().isEmpty()
                            || parts[2].trim().isEmpty()) {
                        throw new BigmouthException("OOPS! The event command " +
                                "is missing a start or end time.");
                    }
                    String description = parts[0].substring(6).trim();
                    LocalDateTime from = Parser.parseDateTime(parts[1].trim());
                    LocalDateTime to = Parser.parseDateTime(parts[2].trim());
                    if (description.isEmpty()) {
                        throw new BigmouthException("OOPS! The description of " +
                                "an event cannot be empty.");
                    }
                    Event curr = new Event(description, from, to);
                    tasks.add(curr);
                    storage.saveToFile();
                    ui.showTaskAdded(curr, tasks.size());
                } else if (userInput.startsWith("delete ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new BigmouthException("Invalid task number. " +
                                "Please enter a valid task number.");
                    }
                    Task removedTask = tasks.remove(taskNumber);
                    storage.saveToFile();
                    System.out.println("_____________________________" +
                            "_______________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);

                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("find ")) {
                    String keyword = userInput.substring(5).trim();
                    TaskList foundTasks = tasks.find(keyword);
                    ui.showMatchingTasks(foundTasks);

                    System.out.println(" You have " + tasks.size()
                            + " tasks in the list.");
                    System.out.println("____________________________" +
                            "________________________________");

                } else {
                    throw new BigmouthException("OOPS! I don't know what " +
                            "that means. Please try again.");
                }
            } catch (BigmouthException e) {
                System.out.println("____________________________" +
                        "________________________________");
                System.out.println(" " + e.getMessage());
                System.out.println("______________________________" +
                        "______________________________");
            } catch (NumberFormatException e) {
                System.out.println("______________________________" +
                        "______________________________");
                System.out.println(" OOPS! Please enter a valid number.");
                System.out.println("______________________________" +
                        "______________________________");
            }
        }

        scanner.close();
    }
}
