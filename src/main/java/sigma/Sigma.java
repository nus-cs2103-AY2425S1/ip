package sigma;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sigma.command.CommandType;
import sigma.exception.SigmaEmptyTaskListException;
import sigma.exception.SigmaException;
import sigma.exception.SigmaFileException;
import sigma.exception.SigmaInvalidArgException;
import sigma.exception.SigmaInvalidDateException;
import sigma.exception.SigmaInvalidDateRangeException;
import sigma.exception.SigmaInvalidTaskException;
import sigma.exception.SigmaMissingArgException;
import sigma.exception.SigmaNaNException;
import sigma.exception.SigmaUnknownCommandException;
import sigma.task.Deadline;
import sigma.task.Event;
import sigma.task.Task;
import sigma.task.TaskList;
import sigma.task.Todo;

/**
 * The {@code Sigma} class serves as the main application class, handling
 * the initialization, command processing, and execution of the task management
 * application. It interacts with the user through the {@code Ui} class,
 * manages tasks using the {@code TaskList} class, and handles file storage
 * through the {@code Storage} class.
 */
public class Sigma {
    private static final String FILEPATH = "./data/sigma.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private boolean isRunning;
    private CommandType commandType;

    /**
     * Constructs a {@code Sigma} object and initializes the required components.
     * It attempts to load the task list from the specified file.
     */
    public Sigma() {
        this.storage = new Storage(FILEPATH);
        this.ui = new Ui();
        this.isRunning = true;
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (SigmaException e) {
            this.ui.showErrorMessage(e);
        }
    }

    /**
     * Starts the application by displaying the welcome message and entering
     * the command-processing loop.
     */
    private void run() {
        System.out.println(this.ui.showWelcome());
        while (this.isRunning) {
            getResponse(this.ui.getInput());
        }
    }

    /**
     * Exits the application by saving the task list to a file, closing the
     * scanner, and setting the running flag to false.
     *
     * @throws SigmaFileException If there is an error saving the task list.
     */
    private void exit() throws SigmaFileException {
        assert this.isRunning;
        try {
            this.ui.closeScanner();
            this.storage.save(taskList.getAllTasks());
            this.isRunning = false;
        } catch (SigmaFileException e) {
            this.ui.showErrorMessage(e);
        }
    }

    /**
     * Handles the user's input by parsing and executing the corresponding command.
     * If an error occurs during command execution, an appropriate error message
     * is displayed.
     *
     * @param userInput The user's input command.
     */
    public String getResponse(String userInput) {
        try {
            commandType = Parser.parseCommand(userInput);
            switch (commandType) {
            case BYE:
                this.exit();
                return this.ui.showGoodbye();
            case LIST:
                return this.ui.showList(taskList);
            case MARK:
                return handleMarkCommand(Parser.parseArgs(CommandType.MARK, userInput));
            case UNMARK:
                return handleUnmarkCommand(Parser.parseArgs(CommandType.UNMARK, userInput));
            case TODO:
                return handleTodoCommand(Parser.parseArgs(CommandType.TODO, userInput));
            case DEADLINE:
                return handleDeadlineCommand(Parser.parseArgs(CommandType.DEADLINE, userInput));
            case EVENT:
                return handleEventCommand(Parser.parseArgs(CommandType.EVENT, userInput));
            case DELETE:
                return handleDeleteCommand(Parser.parseArgs(CommandType.DELETE, userInput));
            case FIND:
                return handleFindCommand(Parser.parseArgs(CommandType.FIND, userInput));
            case HELP:
                return this.ui.showHelp();
            case UPDATE:
                return handleUpdateCommand(Parser.parseArgs(CommandType.UPDATE, userInput));
            default:
                throw new SigmaUnknownCommandException(userInput);
            }
        } catch (SigmaException e) {
            return this.ui.showErrorMessage(e);
        }
    }

    /**
     * Handles the "mark" command, marking a task as done.
     *
     * @param userInput The task number to mark as done.
     * @throws SigmaException If the task number is invalid.
     */
    private String handleMarkCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > taskList.getSize()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            taskList.getTask(taskNumber).markAsDone();
            return this.ui.showMarkedTask(taskList.getTask(taskNumber));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.MARK);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    /**
     * Handles the "unmark" command, marking a task as not done.
     *
     * @param userInput The task number to mark as not done.
     * @throws SigmaException If the task number is invalid.
     */
    private String handleUnmarkCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > taskList.getSize()) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            taskList.getTask(taskNumber).markAsNotDone();
            return this.ui.showUnmarkedTask(taskList.getTask(taskNumber));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.UNMARK);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    /**
     * Handles the "todo" command, adding a new to-do task to the list.
     *
     * @param userInput The description of the to-do task.
     * @throws SigmaException If the description is missing or invalid.
     */
    private String handleTodoCommand(String userInput) throws SigmaException {
        try {
            Task todo = new Todo(userInput);
            taskList.addTask(todo);
            return this.ui.showAddedTask(todo, taskList.getSize());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaMissingArgException(CommandType.TODO);
        }
    }

    /**
     * Handles the "deadline" command by creating a new deadline task and adding it to the task list.
     *
     * @param userInput The description and due date of the deadline task, provided in the format:
     *                  "description /by dd/MM/yy HH:mm".
     * @return A message confirming that the deadline task has been added.
     * @throws SigmaException If the description or due date is missing or invalid.
     */
    private String handleDeadlineCommand(String userInput) throws SigmaException {
        try {
            String[] parts = userInput.split(" /by ");
            if (parts.length < 2) {
                throw new SigmaMissingArgException(CommandType.DEADLINE);
            }
            String description = parts[0].trim();
            LocalDateTime by = LocalDateTime.parse(parts[1], FORMATTER);
            Task deadline = new Deadline(description, by);
            taskList.addTask(deadline);
            return this.ui.showAddedTask(deadline, taskList.getSize());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.DEADLINE);
        } catch (DateTimeParseException e) {
            throw new SigmaInvalidDateException(CommandType.DEADLINE);
        }
    }

    /**
     * Handles the "event" command, creating a new event task and adding it to the task list.
     *
     * @param userInput The description, start time, and end time of the event task, provided in the format:
     *                  "description /from dd/MM/yy HH:mm /to dd/MM/yy HH:mm".
     * @return A message confirming that the event task has been added.
     * @throws SigmaException If the description, start time, or end time is missing or invalid.
     */
    private String handleEventCommand(String userInput) throws SigmaException {
        try {
            String[] parts = userInput.split(" /from ");
            Task event = createEvent(parts);
            taskList.addTask(event);
            return this.ui.showAddedTask(event, taskList.getSize());
        } catch (StringIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.EVENT);
        } catch (DateTimeParseException e) {
            throw new SigmaInvalidDateException(CommandType.EVENT);
        }
    }

    /**
     * Creates an event task from the provided input parts and date-time formatter.
     *
     * @param parts An array containing the description and time range of the event,
     *              split by the "/from" and "/to" delimiters.
     * @return The created event task.
     * @throws SigmaMissingArgException If any required argument (description, start time, or end time) is missing.
     * @throws SigmaInvalidDateRangeException If the end time is before the start time.
     */
    private static Task createEvent(String[] parts) throws SigmaMissingArgException,
            SigmaInvalidDateRangeException {
        if (parts.length < 2) {
            throw new SigmaMissingArgException(CommandType.EVENT);
        }
        String description = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length < 2) {
            throw new SigmaMissingArgException(CommandType.EVENT);
        }
        LocalDateTime from = LocalDateTime.parse(timeParts[0], FORMATTER);
        LocalDateTime to = LocalDateTime.parse(timeParts[1], FORMATTER);
        if (description.isEmpty()) {
            throw new SigmaMissingArgException(CommandType.EVENT);
        }
        if (to.isBefore(from)) {
            throw new SigmaInvalidDateRangeException();
        }
        return new Event(description, from, to);
    }

    /**
     * Handles the "delete" command, removing a task from the list.
     *
     * @param userInput The task number to delete.
     * @throws SigmaException If the task number is invalid.
     */
    private String handleDeleteCommand(String userInput) throws SigmaException {
        try {
            int taskNumber = Integer.parseInt(userInput);
            if (taskNumber < 1 || taskNumber > taskList.getSize() || taskList.getSize() == 0) {
                throw new SigmaInvalidTaskException(taskNumber);
            }
            assert taskList.getSize() != 0;
            String s = this.ui.showDeletedTask(taskList.getTask(taskNumber), taskList.getSize() - 1);
            taskList.deleteTask(taskNumber);
            assert taskList.getSize() == (taskList.getSize() + 1) - 1;
            return s;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SigmaInvalidArgException(CommandType.DELETE);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    /**
     * Handles the "find" command, searching for tasks containing the keyword.
     *
     * @param userInput The keyword to search for in tasks.
     * @throws SigmaException If the keyword is missing or invalid.
     */
    public String handleFindCommand(String userInput) throws SigmaException {
        if (taskList.getSize() == 0) {
            throw new SigmaEmptyTaskListException();
        }

        if (userInput.trim().split(" ").length > 1) {
            throw new SigmaInvalidArgException(CommandType.FIND);
        }

        return this.ui.showSearchedTasks(this.taskList.search(userInput));
    }

    /**
     * Updates an existing task in the task list based on the user's input.
     * <p>
     * The format for the user input is "{taskNumber} {updateDetails}". For {@code Deadline} tasks,
     * {@code updateDetails} can be "description /by dd/MM/yy HH:mm". For {@code Event} tasks,
     * it can be "description /from dd/MM/yy HH:mm /to dd/MM/yy HH:mm".
     * <p>
     * If the task number is invalid or if the update details are missing or incorrectly formatted,
     * appropriate exceptions will be thrown.
     *
     * @param userInput The input command with task number and update details.
     * @return A confirmation message of the update.
     * @throws SigmaException If the task number is invalid, or if the update details are incorrect.
     */
    public String handleUpdateCommand(String userInput) throws SigmaException {
        try {
            String[] parts = userInput.trim().split(" ", 2);
            if (parts.length < 2) {
                throw new SigmaMissingArgException(CommandType.UPDATE);
            }

            int taskNumber = Integer.parseInt(parts[0].trim());
            String updateDetails = parts[1].trim();

            if (taskNumber < 1 || taskNumber > taskList.getSize() || taskList.getSize() == 0) {
                throw new SigmaInvalidTaskException(taskNumber);
            }

            Task task = taskList.getTask(taskNumber);

            if (task instanceof Todo) {
                task.setDescription(updateDetails.trim());
            } else if (task instanceof Deadline) {
                updateDeadline((Deadline) task, updateDetails);
            } else if (task instanceof Event) {
                updateEvent((Event) task, updateDetails);
            } else {
                throw new SigmaInvalidTaskException(taskNumber);
            }

            return this.ui.showUpdatedTask(task, taskNumber);
        } catch (NumberFormatException e) {
            throw new SigmaNaNException();
        }
    }

    /**
     * Updates a Deadline task with the provided details.
     *
     * @param deadline The Deadline task to update.
     * @param updateDetails The details to update, which may include a new due date.
     * @throws SigmaException If the date format is invalid.
     */
    private void updateDeadline(Deadline deadline, String updateDetails) throws SigmaException {
        String[] updateParts = updateDetails.split("/by");
        if (updateParts.length >= 2) {
            if (!updateParts[1].trim().isEmpty()) {
                LocalDateTime newDueDate = LocalDateTime.parse(updateParts[1].trim(), FORMATTER);
                deadline.setBy(newDueDate);
            } else {
                throw new SigmaInvalidDateException(CommandType.UPDATE);
            }
        } else if (updateParts.length == 1) {
            deadline.setDescription(updateParts[0].trim());
        } else {
            throw new SigmaInvalidArgException(CommandType.UPDATE);
        }
    }

    /**
     * Updates an Event task with the provided details.
     *
     * @param event The Event task to update.
     * @param updateDetails The details to update, which may include new start and end times.
     * @throws SigmaException If the date format is invalid or if the end time is before the start time.
     */
    private void updateEvent(Event event, String updateDetails) throws SigmaException {
        String[] updateParts = updateDetails.split("/from");
        if (updateParts.length >= 2) {
            String[] timeParts = updateParts[1].split("/to");
            if (timeParts.length == 2) {
                LocalDateTime newStartTime = LocalDateTime.parse(timeParts[0].trim(), FORMATTER);
                LocalDateTime newEndTime = LocalDateTime.parse(timeParts[1].trim(), FORMATTER);
                if (newEndTime.isBefore(newStartTime)) {
                    throw new SigmaInvalidDateRangeException();
                }
                event.setFrom(newStartTime);
                event.setTo(newEndTime);
            } else {
                throw new SigmaInvalidArgException(CommandType.UPDATE);
            }
        } else if (updateParts.length == 1) {
            event.setDescription(updateParts[0].trim());
        } else {
            throw new SigmaInvalidArgException(CommandType.UPDATE);
        }
    }

    /**
     * Returns the current {@code CommandType} that was parsed from the user's input.
     *
     * @return The {@code CommandType} representing the most recent command issued by the user.
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * The main method serves as the entry point to the application.
     * It creates a new instance of {@code Sigma} and runs the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Sigma().run();
    }
}
