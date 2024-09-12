package casper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import exception.CasperBotException;
import exception.CasperBotInvalidCommandException;
import exception.CasperBotInvalidDateException;
import exception.CasperBotIoException;
import exception.CasperBotMissingInputException;
import exception.CasperBotOutOfBoundsException;

/**
 * Represents the bot
 */
public class CasperBot {
    private enum CommandType {
        CREATE, TASK
    }
    private enum CreateCommand {
        EVENT, TODO, DEADLINE
    }

    private enum TaskCommand {
        MARK, UNMARK, DELETE
    }
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    /**
     * Constructor for CasperBot
     * @param filePath The file path of the local storage of the bot information
     */
    public CasperBot(String filePath) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }
    /**
     * Returns the initial greeting message upon initialisation
     */
    public String initApplication() {
        try {
            this.storage.openFile(this.taskList);
            return this.ui.greeting();
        } catch (CasperBotIoException e) {
            return this.ui.showErrorMessage(e);
        }
    }
    /**
     * Returns the response based on the input by the user
     * @param input User input
     */
    public String getResponse(String input) {
        String[] parsedInputs = this.parser.splitInputIntoTwo(input);
        assert parsedInputs.length == 2 : "The input should be parsed into 2 strings";
        try {
            String result = this.runSingleWordCommand(parsedInputs);
            if (!result.isEmpty()) {
                return result;
            }
            result = this.runTaskCommand(parsedInputs);
            if (!result.isEmpty()) {
                return result;
            }
            result = this.runCreateCommand(parsedInputs);
            if (!result.isEmpty()) {
                return result;
            }
            result = this.viewSchedule(parsedInputs);
            if (!result.isEmpty()) {
                return result;
            }
            throw new CasperBotInvalidCommandException();
        } catch (CasperBotException e) {
            return this.ui.showErrorMessage(e);
        }
    }
    /**
     * Checks if a string is a valid command
     *
     * @param command The string to be validated
     * @param commandType The type of command it should belong to (CREATE, TASK)
     * @return True if it is a valid command, False otherwise
     */
    private boolean isInvalidCommand(String command, CommandType commandType) {
        try {
            switch (commandType) {
            case CREATE:
                CreateCommand.valueOf(command.trim().toUpperCase());
                break;
            case TASK:
                TaskCommand.valueOf(command.trim().toUpperCase());
                break;
            default:
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
    private String runSingleWordCommand(String[] parsedInputs) throws CasperBotMissingInputException,
            CasperBotOutOfBoundsException {
        String command = parsedInputs[0].toLowerCase();
        switch (command) {
        case "list":
            return this.ui.displayTaskList(taskList);
        case "find":
            if (parsedInputs[1].isBlank()) {
                throw new CasperBotMissingInputException("keyword", "find");
            }
            TaskList matched = taskList.findMatchTasks(parsedInputs[1]);
            return this.ui.displayMatchedTasks(matched);
        case "bye":
            return this.ui.exit();
        default:
            return "";
        }
    }
    private String runTaskCommand(String[] parsedInputs) throws CasperBotInvalidCommandException,
            CasperBotOutOfBoundsException, CasperBotIoException {
        if (isInvalidCommand(parsedInputs[0], CommandType.TASK)) {
            return "";
        }
        int index = Integer.parseInt(parsedInputs[1]) - 1;
        TaskCommand taskCommand = TaskCommand.valueOf(parsedInputs[0].trim().toUpperCase());
        Task task = this.taskList.getTask(index);
        switch (taskCommand) {
        case MARK:
            task.markAsDone();
            this.storage.updateDoneStatus(index, true);
            return this.ui.displayUpdateMessage(Ui.TaskCommand.MARK, task);
        case UNMARK:
            task.markAsNotDone();
            this.storage.updateDoneStatus(index, false);
            return this.ui.displayUpdateMessage(Ui.TaskCommand.UNMARK, task);
        case DELETE:
            this.taskList.deleteTask(task);
            this.storage.deleteFromFile(index);
            return this.ui.displayUpdateMessage(Ui.TaskCommand.DELETE, task)
                    + System.lineSeparator()
                    + this.ui.displayTaskListLength(this.taskList.getNumberOfTasks());
        default:
            throw new CasperBotInvalidCommandException();
        }
    }
    private String runCreateCommand(String[] parsedInputs) throws CasperBotMissingInputException,
            CasperBotIoException, CasperBotInvalidCommandException, CasperBotInvalidDateException {
        if (isInvalidCommand(parsedInputs[0], CommandType.CREATE)) {
            return "";
        }
        HashMap<String, String> hashMap = new HashMap<>();
        this.parser.parseBySlash(parsedInputs[1], hashMap);
        CreateCommand createCommand = CreateCommand.valueOf(parsedInputs[0].trim().toUpperCase());
        try {
            return switch (createCommand) {
            case TODO -> handleTodo(hashMap);
            case DEADLINE -> handleDeadline(hashMap);
            case EVENT -> handleEvent(hashMap);
            default -> throw new CasperBotInvalidCommandException();
            };
        } catch (DateTimeParseException e) {
            throw new CasperBotInvalidDateException();
        }
    }
    private String handleTodo(HashMap<String, String> hashMap) throws CasperBotMissingInputException,
            CasperBotIoException {
        String todoDescription = hashMap.get("description");
        if (todoDescription.isEmpty()) {
            throw new CasperBotMissingInputException("description", "ToDo");
        }
        ToDo newToDo = new ToDo(todoDescription, false);
        this.taskList.addTask(newToDo);
        this.storage.writeToFile(newToDo);
        return this.ui.addTaskMessage(newToDo, this.taskList.getNumberOfTasks());
    }
    private String handleDeadline(HashMap<String, String> hashMap) throws CasperBotMissingInputException,
            CasperBotIoException {
        String deadlineDescription = hashMap.get("description");
        if (deadlineDescription.isEmpty()) {
            throw new CasperBotMissingInputException("description", "Deadline");
        }
        String deadline = hashMap.get("by");
        if (deadline == null || deadline.isEmpty()) {
            throw new CasperBotMissingInputException("/by", "Deadline");
        }
        LocalDate dateOfDeadline = LocalDate.parse(deadline);
        Deadline newDeadline = new Deadline(deadlineDescription, false, dateOfDeadline);
        this.taskList.addTask(newDeadline);
        this.storage.writeToFile(newDeadline);
        return this.ui.addTaskMessage(newDeadline, this.taskList.getNumberOfTasks());
    }
    private String handleEvent(HashMap<String, String> hashMap) throws CasperBotMissingInputException,
            CasperBotIoException {
        String eventDescription = hashMap.get("description");
        if (eventDescription.isEmpty()) {
            throw new CasperBotMissingInputException("description", "Event");
        }
        String start = hashMap.get("from");
        if (start == null || start.isEmpty()) {
            throw new CasperBotMissingInputException("/from", "Event");
        }
        LocalDate dateOfStart = LocalDate.parse(start);
        String end = hashMap.get("to");
        if (end == null || end.isEmpty()) {
            throw new CasperBotMissingInputException("/to", "Event");
        }
        LocalDate dateOfEnd = LocalDate.parse(end);
        Event newEvent = new Event(eventDescription, false, dateOfStart, dateOfEnd);
        this.taskList.addTask(newEvent);
        this.storage.writeToFile(newEvent);
        return this.ui.addTaskMessage(newEvent, this.taskList.getNumberOfTasks());
    }
    private String viewSchedule(String[] parsedInputs) throws CasperBotInvalidCommandException,
            CasperBotInvalidDateException, CasperBotOutOfBoundsException {
        try {
            String command = parsedInputs[0].toLowerCase();
            if (!command.equals("view")) {
                throw new CasperBotInvalidCommandException();
            }
            LocalDate viewDate = LocalDate.parse(parsedInputs[1]);
            TaskList taskListOnDay = this.taskList.findTasksByDate(viewDate);
            return this.ui.displaySchedule(taskListOnDay, viewDate);
        } catch (DateTimeParseException e) {
            throw new CasperBotInvalidDateException();
        }
    }
}
