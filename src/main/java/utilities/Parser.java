package utilities;

import commands.Command;
import commands.GoodbyeCommand;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class processes user input and executes the corresponding actions in the Bigmouth chatbot.
 */
public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Parser with the specified Ui, TaskList, Storage, and Scanner.
     *
     * @param ui    The Ui that handles response strings in the commands
     * @param tasks The task list that stores all the tasks.
     * @param s     The storage handler for saving and loading tasks.
     */
    public Parser(Ui ui, TaskList tasks, Storage s) {
        assert ui != null : "Ui object cannot be null";
        assert tasks != null : "TaskList object cannot be null";
        assert s != null : "Storage object cannot be null";

        this.ui = ui;
        this.tasks = tasks;
        this.storage = s;
    }

    /**
     * Parses a date/time string and returns the corresponding LocalDateTime object.
     *
     * @param input The input string in the format 'M/d/yyyy HHmm'.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws BigmouthException If the input format is incorrect.
     */
    static LocalDateTime parseDateTime(String input) throws BigmouthException {
        assert input != null : "Input date/time string cannot be null";
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
    public Command parseInput(String userInput) {
        assert userInput != null : "User input cannot be null";
        String firstWord = userInput.contains(" ") ? userInput.split(" ")[0] : userInput;

        try {
            switch (firstWord) {
            case "bye":
                return new GoodbyeCommand();
            case "list":
                return this.sendListCommand();
            case "mark":
                return this.sendMarkCommand(userInput);
            case "unmark":
                return this.sendUnmarkCommand(userInput);
            case "todo":
                return this.sendTodoCommand(userInput);
            case "deadline":
                return this.sendDeadlineCommand(userInput);
            case "event":
                return this.sendEventCommand(userInput);
            case "delete":
                return this.sendDeleteCommand(userInput);
            case "find":
                return this.sendFindCommand(userInput);
            }
        } catch (BigmouthException e) {
            return new Command(e.getMessage());
        } catch (NumberFormatException e) {
            return new Command("OOPS! Please enter a valid number.");
        }

        return new Command("Idk what ur sayin leh :/");
    }

    private Command sendListCommand() throws BigmouthException {
        assert tasks != null : "Task list cannot be null";
        if (tasks.isEmpty()) {
            throw new BigmouthException("Your task list is empty!");
        }
        String response = ui.showTaskList(this.tasks);
        return new Command(response);
    }

    private Command sendMarkCommand(String userInput) throws BigmouthException {
        assert userInput.split(" ").length > 1 : "User input for mark command must have a task number";
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        assert taskNumber >= 0 && taskNumber < tasks.size() : "Task number must be valid";

        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new BigmouthException("Invalid task number. " +
                    "Please enter a valid task number.");
        }
        tasks.get(taskNumber).markAsDone();
        storage.saveToFile();
        return new Command(ui.showTaskMarked(tasks.get(taskNumber)));
    }

    private Command sendUnmarkCommand(String userInput) throws BigmouthException {
        assert userInput.split(" ").length > 1 : "User input for unmark command must have a task number";
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        assert taskNumber >= 0 && taskNumber < tasks.size() : "Task number must be valid";

        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new BigmouthException("Invalid task number. " +
                    "Please enter a valid task number.");
        }
        tasks.get(taskNumber).markAsNotDone();
        storage.saveToFile();
        return new Command(ui.showTaskUnmarked(tasks.get(taskNumber)));
    }

    private Command sendTodoCommand(String userInput) throws BigmouthException {
        assert userInput.length() > 5 : "Todo command must have a description";
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            throw new BigmouthException("OOPS! The description of " +
                    "a todo cannot be empty.");
        }
        Todo curr = new Todo(description);
        tasks.add(curr);
        storage.saveToFile();
        return new Command(ui.showTaskAdded(curr, tasks.size()));
    }

    private Command sendDeadlineCommand(String userInput) throws BigmouthException {
        assert userInput.contains(" /by ") : "Deadline command must have a description and due date";
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
        return new Command(ui.showTaskAdded(curr, tasks.size()));
    }

    private Command sendEventCommand(String userInput) throws BigmouthException {
        assert userInput.contains(" /from ") && userInput.contains(" /to ") : "Event command must have a description, start, and end time";
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
        return new Command(ui.showTaskAdded(curr, tasks.size()));
    }

    private Command sendDeleteCommand(String userInput) throws BigmouthException {
        assert userInput.split(" ").length > 1 : "Delete command must have a task number";
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        assert taskNumber >= 0 && taskNumber < tasks.size() : "Task number must be valid";

        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new BigmouthException("Invalid task number. " +
                    "Please enter a valid task number.");
        }
        Task removedTask = tasks.remove(taskNumber);
        storage.saveToFile();
        return new Command(ui.showTaskRemoved(removedTask, tasks.size()));
    }

    private Command sendFindCommand(String userInput) throws BigmouthException {
        assert userInput.length() > 5 : "Find command must have a keyword";
        String keyword = userInput.substring(5).trim();
        TaskList foundTasks = tasks.find(keyword);
        return new Command(ui.showMatchingTasks(foundTasks, tasks.size()));
    }
}
