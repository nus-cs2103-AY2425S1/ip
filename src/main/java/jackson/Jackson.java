package jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;

import jackson.enums.Actions;
import jackson.enums.Commands;
import jackson.exceptions.DuplicatedTaskException;
import jackson.exceptions.InvalidArgumentException;
import jackson.exceptions.JacksonException;
import jackson.exceptions.OutOfListException;
import jackson.exceptions.SyntaxException;
import jackson.exceptions.UnsupportedCommandException;
import jackson.tasks.Deadline;
import jackson.tasks.Event;
import jackson.tasks.Task;
import jackson.tasks.Todo;
import jackson.utils.Parser;
import jackson.utils.Response;
import jackson.utils.Storage;
import jackson.utils.TaskList;
import jackson.utils.Ui;

/**
 * Main class for the chatbot.
 */
public class Jackson {

    // Expected number of tasks to store
    private static final int EXPECTED_SIZE = 100;

    // Path to save list data
    private static final String PATH = "data/data.txt";

    // Path list to read secret text from
    private static final String SECRET_TEXT = "TWFyaSBraXRhIHJha3lhdCBTaW5nYXB1cmEKU2FtYS1"
            + "zYW1hIG1lbnVqdSBiYWhhZ2lhCkNpdGEtY2l0YSBraXRhIHlhbmcgbXVsaWEKQmVyamF5YSBTaW5n"
            + "YXB1cmEKTWFyaWxhaCBraXRhIGJlcnNhdHUKRGVuZ2FuIHNlbWFuZ2F0IHlhbmcgYmFydQpTZW11Y"
            + "SBraXRhIGJlcnNlcnUKTWFqdWxhaCBTaW5nYXB1cmEKTWFqdWxhaCBTaW5nYXB1cmE=";

    // Stores previous command type for css style changing
    private Commands.CommandType commandType;

    // Instance variables for main loop
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs Jackson instance.
     */
    public Jackson() {
        this.taskList = new TaskList(EXPECTED_SIZE);
        this.ui = new Ui();
        this.storage = new Storage(PATH);
        this.commandType = Commands.CommandType.INTRO;
    }

    /**
     * Prints secret text.
     * Due to jar issues, this cannot be read from a file.
     * @return String response.
     */

    public String printSecret() {
        // decode from base64 to utf8
        byte[] decoded = Base64.getDecoder().decode(SECRET_TEXT);

        // print secret msg!
        return new String(decoded);
    }

    /**
     * Runs the main loop of the chatbot.
     */
    public String getResponse(String input) {
        // Variables for main loop
        Response response;

        String output;
        System.out.println(input); // for debugging purposes

        // main loop starts
        try {
            // first parse input and get response (or throw error here)
            response = Parser.parse(input);

            // decide what action to take based on action object received from parser
            output = this.makeDecision(response);

            // save task list to storage after every command
            this.storage.save(this.taskList);

        } catch (UnsupportedCommandException e) {
            // if user input not recognised, print command list
            output = this.ui.printUnrecognizedMessage();
            this.commandType = Commands.CommandType.ERROR;
        } catch (SyntaxException e) {
            // if the user input is in the wrong format for the command, print format guide
            output = this.ui.printWrongFormat(e.getMessage());
            this.commandType = Commands.CommandType.ERROR;
        } catch (OutOfListException e) {
            // if user inputs an invalid index for mark/unmark/delete, print index guide
            output = this.ui.printIndexGuide(this.taskList);
            this.commandType = Commands.CommandType.ERROR;
        } catch (DuplicatedTaskException e) {
            // if user tries to add task with a name that already exists in taskList, print de-conflict advice
            output = this.ui.printDeconflictAdvice(e.getMessage());
            this.commandType = Commands.CommandType.ERROR;
        } catch (InvalidArgumentException e) {
            output = this.ui.printInvalidDates();
            this.commandType = Commands.CommandType.ERROR;
        } catch (IOException e) {
            output = this.ui.printFileIssue();
            this.commandType = Commands.CommandType.ERROR;
        } catch (Exception e) {
            // some other error unaccounted for, print generic warning
            // here we pass an error so the stack trace can be extracted
            output = this.ui.printUnknownError(e);
            this.commandType = Commands.CommandType.ERROR;
        }
        return output;
    }

    /**
     * Decides what the output should be based on current action to take.
     * @return String response.
     */
    public String makeDecision(Response response) throws
            JacksonException, IOException {
        Actions.ActionType action = response.getAction();
        Matcher matcher = response.getMatcher();

        switch (action) {
        case LIST:
            return this.executeList();
        case TODO:
            return this.executeTodo(matcher);
        case DEADLINE:
            return this.executeDeadline(matcher);
        case EVENT:
            return this.executeEvent(matcher);
        case MARK:
            return this.executeMark(matcher);
        case UNMARK:
            return this.executeUnmark(matcher);
        case DELETE:
            return this.executeDelete(matcher);
        case FIND:
            return this.executeFind(matcher);
        case SORT:
            return this.executeSort(matcher);
        case HELP:
            return this.executeHelp(matcher);
        case BYE:
            return this.executeBye();
        case SECRET:
            return this.executeSecret();
        case INVALID:
            throw new UnsupportedCommandException();
        default:
            return "Unknown error! Contact the developer...\n";
        }
    }

    /**
     * Executes list command.
     * @return String response.
     */
    public String executeList() {
        this.commandType = Commands.CommandType.NORMAL;
        return this.ui.printList(this.taskList);
    }

    /**
     * Executes todo command.
     * @param matcher Matcher object used to parse command.
     * @return String response.
     */
    public String executeTodo(Matcher matcher) throws DuplicatedTaskException {
        Task task = new Todo(matcher.group(1));
        this.taskList.addTask(task);
        this.commandType = Commands.CommandType.LIST;
        return this.ui.printAfterAddList(task, this.taskList);
    }

    /**
     * Executes deadline command.
     * @param matcher Matcher object used to parse command.
     * @return String response.
     */
    public String executeDeadline(Matcher matcher) throws DuplicatedTaskException {
        Task task = new Deadline(matcher.group(1), matcher.group(2));
        this.taskList.addTask(task);
        this.commandType = Commands.CommandType.LIST;
        return this.ui.printAfterAddList(task, this.taskList);
    }

    /**
     * Executes event command.
     * @param matcher Matcher object used to parse command.
     * @return String response.
     */
    public String executeEvent(Matcher matcher)
            throws DuplicatedTaskException, InvalidArgumentException {
        Task task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
        this.taskList.addTask(task);
        this.commandType = Commands.CommandType.LIST;
        return this.ui.printAfterAddList(task, this.taskList);
    }

    /**
     * Executes mark command.
     * @param matcher Matcher object used to parse command.
     * @return String response.
     */
    public String executeMark(Matcher matcher) throws OutOfListException {
        int index = Integer.parseInt(matcher.group(1)) - 1;
        Task task = this.taskList.mark(index);
        this.commandType = Commands.CommandType.TASK;
        return this.ui.printAfterMark(task);
    }

    /**
     * Executes unmark command.
     * @param matcher Matcher object used to parse command.
     * @return String response.
     */
    public String executeUnmark(Matcher matcher) throws OutOfListException {
        int index = Integer.parseInt(matcher.group(1)) - 1;
        Task task = this.taskList.unmark(index);
        this.commandType = Commands.CommandType.TASK;
        return this.ui.printAfterUnmark(task);
    }

    /**
     * Executes delete command.
     * @param matcher Matcher object used to parse command.
     * @return String response.
     */
    public String executeDelete(Matcher matcher) throws OutOfListException {
        int index = Integer.parseInt(matcher.group(1)) - 1;
        Task task = this.taskList.deleteTask(index);
        this.commandType = Commands.CommandType.LIST;
        return this.ui.printAfterDeleteList(task, this.taskList);
    }

    /**
     * Executes find command.
     * @param matcher Matcher object used to parse command.
     * @return String response.
     */
    public String executeFind(Matcher matcher) {
        ArrayList<Task> tasks = this.taskList.findTasks(matcher.group(1));
        this.commandType = Commands.CommandType.LIST;
        return this.ui.printAfterFindList(tasks, matcher.group(1));
    }

    /**
     * Executes sort command.
     * @param matcher Matcher object used to parse command.
     * @return String response.
     */
    public String executeSort(Matcher matcher) throws OutOfListException {
        boolean isAscending = matcher.group(2) == null || matcher.group(2).equals("/a");
        this.taskList.sort(matcher.group(1), isAscending);
        this.commandType = Commands.CommandType.LIST;
        return this.ui.printSortedList(this.taskList);
    }

    /**
     * Executes help command.
     * @param matcher Matcher object used to parse command.
     * @return String response.
     */
    public String executeHelp(Matcher matcher) {
        String output = matcher.group(1) == null
                ? this.ui.printCommandList()
                : this.ui.printFormatGuide(matcher.group(1));
        this.commandType = Commands.CommandType.NORMAL;
        return output;
    }

    /**
     * Executes bye command.
     * @return String response.
     */
    public String executeBye() throws IOException {
        this.commandType = Commands.CommandType.EXIT;
        return this.storage.save(this.taskList);
    }

    /**
     * Executes secret command.
     * @return String response.
     */
    public String executeSecret() {
        this.commandType = Commands.CommandType.SECRET;
        return printSecret();
    }

    /**
     * Returns welcome message.
     * Used for GUI.
     * @return String representation of welcome message.
     */
    public String start() {
        return this.ui.printWelcome();
    }

    /**
     * Loads task list from save file (if it exists).
     * Used for GUI.
     * @return String representation of success or failure of loading.
     */
    public String load() {
        return this.storage.load(this.taskList);
    }

    /**
     * Returns goodbye String.
     * Used for GUI.
     * @return String representation of goodbye.
     */
    public String sayGoodbye() {
        return this.ui.printGoodbye();
    }

    /**
     * Returns last executed commandType for chatbot text box drawing.
     * Used for GUI.
     * @return {@code Commands.CommandType} of last executed command.
     */
    public Commands.CommandType getCommandType() {
        return this.commandType;
    }
}
