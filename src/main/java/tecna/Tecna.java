package tecna;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import tecna.ui.Ui;

/**
 * Runs the Tecna chatbot application.
 *
 * @author DennieDan.
 */
public class Tecna {
    private CommandScanner commandScanner;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Tecna() {
        this.taskList = new TaskList();
        this.commandScanner = new CommandScanner();
        this.ui = new Ui();
    }

    /**
     * Constructs an instance of Tecna chatbot from a <b>json</b> file.
     *
     * @param taskData absolute path to the json file.
     */
    public Tecna(String taskData) {
        this.storage = new Storage(taskData);
        this.commandScanner = new CommandScanner();
        this.ui = new Ui();
        this.taskList = new TaskList();

        try {
            taskList = new TaskList(storage.load());
        } catch (IOException ioException) {
            ui.printError("I cannot open the data file!");
        } catch (ParseException parseException) {
            ui.printError("The data file is of the wrong format!");
        } catch (JsonLoadingException jsonLoadingException) {
            ui.printError(jsonLoadingException.getMessage());
        }
    }

    /**
     * Exits the chatbot by printing the goodbye lines.
     */
    public void exitChatBot() {
        storage.setFilePath("src/main/resources/data/tecna1.json");

        try {
            storage.save(this.taskList);
        } catch (IOException ioException) {
            ui.printError("I cannot access the data file " + storage.getFilePath());
        }

        ui.printSectionLine();
        ui.printGoodbyeMsg();
        ui.printSectionLine();
    }

    public String getResponse(String input) {
        CommandType command = commandScanner.readRequest(input);
        ui.printSectionLine();

        switch (command) {
        case BYE:
            return ui.printGoodbyeMsg();
        case LIST:
            return ui.printItems(this.taskList);
        case MARK:
            int index = commandScanner.getInputIndex();
            taskList.mark(index);
            return ui.printMarkMsg(taskList.getTask(index));
        case UNMARK:
            index = commandScanner.getInputIndex();
            taskList.unmark(index);
            return ui.printUnmarkMsg(taskList.getTask(index));
        case DELETE:
            index = commandScanner.getInputIndex();
            return ui.printDeleteItemMsg(taskList, index);
        case FIND:
            return ui.printFindTasksMsg(taskList, commandScanner.getKeyword());
        case TODO:
        case DEADLINE:
        case EVENT:
            return ui.printAddItemMsg(taskList, commandScanner.getInputTask());
        case TODO_WRONG_FORMAT:
            return ui.printError("Wrong format! The command should be \"todo [task_description]\".");
        case DEADLINE_WRONG_FORMAT:
            return ui.printError("Wrong format! The command should be \"deadline [task_description] /by " +
                    "[deadline in the form of yyyy-MM-dd HHmm]\".");
        case EVENT_WRONG_FORMAT:
            return ui.printError("Wrong format! The command should be \"event [task_description] /from " +
                    "[start_time in the form of yyyy-MM-dd HHmm] /to [end time in the form of yyyy-MM-dd HHmm]\".");
        case INDEX_WRONG_FORMAT:
            return ui.printError("The parameter of this command must be a number from 1 to " + taskList.getSize());
        case INVALID:
        default:
            return ui.printInvalidCmdError();
        }
    }

    /**
     * Greets the user by printing the logo.
     *
     * @return a greeting message.
     */
    public String greet() {
        ui.printLogo();
        ui.printHelloMsg();
        ui.printSectionLine();
        return ui.printLogo() + ui.printHelloMsg();
    }
}
