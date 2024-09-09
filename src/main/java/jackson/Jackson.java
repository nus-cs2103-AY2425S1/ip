package jackson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import jackson.enums.Actions;
import jackson.enums.Commands;
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
    private static final String PATH = "src/main/resources/texts/data.txt";

    // Path list to read secret text from
    private static final String SECRET_PATH = "src/main/resources/texts/secret_text.txt";

    // Stores secret text for greedy loading
    private static String secret = "";

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

        // Code to test if asserts enabled
        // Should be the case since build.gradle explicitly enables it
        // assert false: "Asserts enabled!";
    }

    /**
     * Reads secret text from secret file and prints it.
     * If secret file not found, handles exception and prints error message.
     * @return String response.
     */
    public String readSecret() {
        String output; // variable to store the accumulated text
        File f = new File(SECRET_PATH); // File object for Stream
        if (secret.isEmpty()) {
            // load String Stream from File
            try (Stream<String> secretString = Files.lines(f.toPath())) {
                // accumulate each line
                output = secretString.reduce((line, total) -> total + line + "\n").orElse("").strip();

                // decode from base64 to utf8
                byte[] decoded = Base64.getDecoder().decode(output);
                output = new String(decoded);
            } catch (IOException e) {
                // if file path not found
                output = "Oops! Secret file not found...";
            }
        } else {
            output = secret;
        }
        // print secret msg!
        return output;
    }

    /**
     * Runs the main loop of the chatbot.
     */
    public String getResponse(String input) {
        // Variables for main loop
        Task task;
        Response response;
        Actions.ActionType action;
        Matcher matcher;
        ArrayList<Task> tasks;
        int index;
        boolean isAscending;
        String output;
        System.out.println(input);

        // main loop starts
        try {
            // first parse input and get response (or throw error here)
            response = Parser.parse(input);
            action = response.getAction();
            matcher = response.getMatcher();

            // decide what action to take based on action object received from parser
            switch (action) {
            case LIST:
                output = this.ui.printList(this.taskList);
                this.commandType = Commands.CommandType.NORMAL;
                break;
            case TODO:
                task = new Todo(matcher.group(1));
                this.taskList.addTask(task);
                output = this.ui.printAfterAddList(task, this.taskList);
                this.commandType = Commands.CommandType.TASKS;
                break;
            case DEADLINE:
                task = new Deadline(matcher.group(1), matcher.group(2));
                this.taskList.addTask(task);
                output = this.ui.printAfterAddList(task, this.taskList);
                this.commandType = Commands.CommandType.TASKS;
                break;
            case EVENT:
                task = new Event(matcher.group(1), matcher.group(2), matcher.group(3));
                this.taskList.addTask(task);
                output = this.ui.printAfterAddList(task, this.taskList);
                this.commandType = Commands.CommandType.TASKS;
                break;
            case MARK:
                index = Integer.parseInt(matcher.group(1)) - 1;
                task = this.taskList.mark(index);
                output = this.ui.printAfterMark(task);
                this.commandType = Commands.CommandType.MODIFY;
                break;
            case UNMARK:
                index = Integer.parseInt(matcher.group(1)) - 1;
                task = this.taskList.unmark(index);
                output = this.ui.printAfterUnmark(task);
                this.commandType = Commands.CommandType.MODIFY;
                break;
            case DELETE:
                index = Integer.parseInt(matcher.group(1)) - 1;
                task = this.taskList.deleteTask(index);
                output = this.ui.printAfterDeleteList(task, this.taskList);
                this.commandType = Commands.CommandType.MODIFY;
                break;
            case FIND:
                tasks = this.taskList.findTasks(matcher.group(1));
                output = this.ui.printAfterFindList(tasks, matcher.group(1));
                this.commandType = Commands.CommandType.TASKS;
                break;
            case SORT:
                if (matcher.group(2) == null) {
                    isAscending = true;
                } else {
                    isAscending = matcher.group(2).equals("/a");
                }
                this.taskList.sort(matcher.group(1), isAscending);
                output = this.ui.printSortedList(this.taskList);
                this.commandType = Commands.CommandType.MODIFY;
                break;
            case BYE:
                output = this.storage.save(this.taskList);
                this.commandType = Commands.CommandType.EXIT;
                break;
            case SECRET:
                output = this.readSecret();
                this.commandType = Commands.CommandType.SECRET;
                break;
            case INVALID:
                throw new UnsupportedCommandException(input);
            default:
                output = "Unknown error! Contact the developer...\n";
                break;
            }
        } catch (UnsupportedCommandException e) {
            // if user input not recognised, print command list
            output = this.ui.printCommandList();
            this.commandType = Commands.CommandType.ERROR;
        } catch (SyntaxException e) {
            // if the user input is in the wrong format for the command, print format guide
            output = this.ui.printFormatGuide(e.getMessage());
            this.commandType = Commands.CommandType.ERROR;
        } catch (OutOfListException e) {
            // if user inputs an invalid index for mark/unmark/delete, print index guide
            output = this.ui.printIndexGuide(this.taskList);
            this.commandType = Commands.CommandType.ERROR;
        } catch (Exception e) {
            // some other error unaccounted for, print generic warning
            output = this.ui.printUnknownError(e);
            this.commandType = Commands.CommandType.ERROR;
        }

        // save task list to storage after every command
        this.storage.save(this.taskList);
        return output;
    }

    /**
     * Returns welcome message.
     * @return String representation of welcome message.
     */
    public String start() {
        return this.ui.printWelcome();
    }

    /**
     * Loads task list from save file (if it exists).
     * @return String representation of success or failure of loading.
     */
    public String load() {
        return this.storage.load(this.taskList);
    }

    /**
     * Returns goodbye String.
     * @return String representation of goodbye.
     */
    public String sayGoodbye() {
        return this.ui.printGoodbye();
    }

    public Commands.CommandType getCommandType() {
        return this.commandType;
    }
}
