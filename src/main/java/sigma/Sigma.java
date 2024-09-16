package sigma;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.application.Platform;
import log.MyLogger;
import sigma.command.Command;
import sigma.exception.SigmaException;
import sigma.utils.Parser;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the main class of Sigma.
 * Initializes the Ui, Storage, and TaskList.
 */
public class Sigma {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final File data;
    private String commandType;

    /**
     * Constructor for Sigma.
     * Initializes the Ui, Storage, and TaskList.
     */
    public Sigma() {
        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
        LOGGER.info("Initializing Sigma");
        this.ui = new Ui();
        storage = new Storage("data.txt");
        this.data = new File("data.txt");
        tasks = new TaskList(storage.read(data));
    }

    /**
     * Constructor for Sigma.
     * Initializes the Ui, Storage, and TaskList.
     *
     * @param filePath
     */
    public Sigma(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        this.data = new File(filePath);
        tasks = new TaskList(storage.read(data));
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            LOGGER.info("Parsed command: " + command.toString());
            commandType = command.toString();
            assert tasks != null : "TaskList cannot be null";
            assert storage != null : "Storage cannot be null";
            assert ui != null : "Ui cannot be null";
            String response = command.execute(tasks, ui, storage);
            if (command.isExit()) {
                Platform.exit();
            }
            return response;
        } catch (SigmaException e) {
            LOGGER.warning("SigmaException: " + e.getMessage());
            return e.getMessage();
        } catch (NumberFormatException e) {
            LOGGER.warning("NumberFormatException: " + e.getMessage());
            return "What the sigma? I need a number!";
        }
    }

    public String getCommandType() {
        return commandType;
    }

    public Ui getUi() {
        return this.ui;
    }
}







