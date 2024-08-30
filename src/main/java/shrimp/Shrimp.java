package shrimp;

import shrimp.command.*;
import shrimp.exception.ShrimpException;
import shrimp.task.*;
import shrimp.utility.AnsiCode;
import shrimp.utility.Parser;
import shrimp.utility.Storage;
import shrimp.utility.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The {@code Shrimp} class serves as the entry point for the Shrimp chatbot application.
 * It handles the main program flow, including user interaction, task management,
 * and command execution.
 */
public class Shrimp {

    private static final Boolean NEW_EVENT_NOT_DONE = false;

    /**
     * The main method of the application.
     * It initializes and starts the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        //program initialise
        programStart();
    }

    /**
     * Initializes the program by displaying the logo and welcome message,
     * and then starts the chatbot.
     */
    static void programStart() {
        Ui ui = new Ui();

        ui.printLogo();
        ui.printWelcome();

        chatBotRun(ui);
    }

    /**
     * Runs the main loop of the chatbot, handling user input and executing commands.
     * The loop continues until the user issues the "bye" command.
     *
     * @param ui The {@code Ui} instance for interacting with the user.
     */
    static void chatBotRun(Ui ui) {
        String userInput;
        TaskList taskList;

        try {
            taskList = Storage.loadTasks();
        } catch (IOException | ShrimpException e) {
            ui.printError("Couldn't load tasks... Starting with an empty list.");
            System.out.println(AnsiCode.CYAN);
            taskList = new TaskList();
        }

        while (true) {
            try {
                userInput = ui.nextLine();  //read the next line of user input
                if (userInput == null || userInput.isEmpty()) {
                    throw new ShrimpException.InvalidCommandException();
                }

                Parser.CommandType commandType = Parser.parseCommand(userInput);

                switch (commandType) {
                    case BYE: //exits the program
                        ui.printExit();
                        return;

                    case LIST:
                        if (taskList.getCount() == 0) {
                            throw new ShrimpException.EmptyArrayException();
                        }
                        ListCommand listCommand = new ListCommand();
                        listCommand.run(taskList, ui);
                        break;

                    case MARK:
                        int indexMark = getTaskNumber(userInput, commandType);
                        if (indexMark > taskList.getCount()) {
                            throw new ShrimpException.ArrayIndexOutOfBoundException();
                        } else if (taskList.getCount() == 0) {
                            throw new ShrimpException.EmptyArrayException();
                        }
                        MarkCommand markCommand = new MarkCommand(indexMark, true);
                        markCommand.run(taskList, ui);
                        break;

                    case UNMARK:
                        int indexUnmark = getTaskNumber(userInput, commandType);
                        if (indexUnmark > taskList.getCount()) {
                            throw new ShrimpException.ArrayIndexOutOfBoundException();
                        } else if (taskList.getCount() == 0) {
                            throw new ShrimpException.EmptyArrayException();
                        }
                        MarkCommand markUnmarkCommand = new MarkCommand(indexUnmark, false);
                        markUnmarkCommand.run(taskList, ui);
                        break;

                    case DELETE:
                        int indexDelete = getTaskNumber(userInput, commandType);
                        if (indexDelete > taskList.getCount()) {
                            throw new ShrimpException.ArrayIndexOutOfBoundException();
                        } else if (taskList.getCount() == 0) {
                            throw new ShrimpException.EmptyArrayException();
                        }
                        DeleteCommand deleteCommand = new DeleteCommand(indexDelete);
                        deleteCommand.run(taskList, ui);
                        break;

                    case ADD:
                        if (userInput.length() <= 5) {
                            throw new ShrimpException.MissingArgumentException(commandType);
                        }
                        String input = userInput.substring(5);
                        Todo newTodo = new Todo(input, NEW_EVENT_NOT_DONE); //creates a new Task.Task object
                        AddCommand addTodo = new AddCommand(newTodo);
                        addTodo.run(taskList, ui);
                        break;

                    case DEADLINE:
                        if (userInput.length() <= 9 || !userInput.contains("/by")) {
                            throw new ShrimpException.MissingArgumentException(commandType);
                        }
                        String[] deadlineDetails = userInput.split("/by ");
                        String deadlineDescription = deadlineDetails[0].substring(9); // Extracting the task description
                        LocalDateTime by = getDateTime(deadlineDetails[1].trim());
                        Task newDeadline = new Deadline(deadlineDescription, by, NEW_EVENT_NOT_DONE);
                        AddCommand addDeadline = new AddCommand(newDeadline);
                        addDeadline.run(taskList, ui);
                        break;

                    case EVENT:
                        if (userInput.length() <= 6 || !userInput.contains("/from") || !userInput.contains("/to")) {
                            throw new ShrimpException.MissingArgumentException(commandType);
                        }
                        String[] eventDetails = userInput.split("/from | /to ");
                        String eventDescription = eventDetails[0].substring(6); // Extracting the task description
                        LocalDateTime from = getDateTime(eventDetails[1].trim());
                        LocalDateTime to = getDateTime(eventDetails[2].trim());
                        Task newEvent = new Event(eventDescription, from, to, NEW_EVENT_NOT_DONE);
                        AddCommand addEvent = new AddCommand(newEvent);
                        addEvent.run(taskList, ui);
                        break;

                    case CLEAR:
                        ClearCommand clearCommand = new ClearCommand();
                        clearCommand.run(taskList, ui);
                        break;

                    default:
                        throw new ShrimpException.InvalidCommandException();
                }

                Storage.saveTasks(taskList);

            } catch (ShrimpException e) {
                ui.printError(e.getMessage() + String.format(" (%s)", e.getErrorCode()));
                System.out.println(AnsiCode.CYAN);
            } catch (Exception e) {
                ui.printError("Something went wrong... Try again!");
                System.out.println(AnsiCode.CYAN);
            }
        }
    }

    /**
     * Prints out a {@code String} value when exiting the program.
     */
    static void programExit() {
        String output = "Byebye~ It's time to say goodbye for the day~ Hope you enjoyed and had fuuun~ " +
                "I'll see you later~";
        System.out.println(output);
    }

    /**
     * Extracts the task number from the user input for MARK, UNMARK, or DELETE commands.
     *
     * @param userInput The user's input containing the command and task number.
     * @param type The type of command being processed.
     * @return The task number (zero-based index).
     * @throws ShrimpException If the task number is missing or not a valid integer.
     */    static int getTaskNumber(String userInput, Parser.CommandType type) throws ShrimpException {
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ShrimpException.MissingArgumentException(type);
        } catch (NumberFormatException e) {
            throw new ShrimpException.NumberFormatException();
        }
    }

    /**
     * Parses a {@code String} into a {@code LocalDateTime} object using the defined date/time pattern.
     *
     * @param input The date/time string to be parsed.
     * @return The parsed {@code LocalDateTime} object.
     * @throws ShrimpException If the date/time format is invalid.
     */
    static LocalDateTime getDateTime(String input) throws ShrimpException {
        try {
            return LocalDateTime.parse(input, Parser.PATTERN);
        } catch (DateTimeParseException e) {
            throw new ShrimpException.InvalidDateTimeException();
        }
    }
}
