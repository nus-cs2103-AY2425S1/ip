package tecna;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import tecna.collection.TaskList;
import tecna.command.Command;
import tecna.command.CommandScanner;
import tecna.command.CommandType;
import tecna.exception.JsonLoadingException;
import tecna.storage.Storage;
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
        Command command = commandScanner.getCommand(input);
        return command.execute(taskList, storage, ui);
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
