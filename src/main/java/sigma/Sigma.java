package sigma;

import sigma.command.Command;
import sigma.exception.SigmaException;
import sigma.utils.Parser;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

import java.io.File;

/**
 * Represents the main class of Sigma.
 * Initializes the Ui, Storage, and TaskList.
 */
public class Sigma {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private File data;
    private String commandType;

    // Default constructor
    public Sigma() {
        this.ui = new Ui();
        storage = new Storage("data.txt");
        this.data = new File("data.txt");
        tasks = new TaskList(storage.read(data));
    }

    /**
     * Constructor for Sigma.
     * Initializes the Ui, Storage, and TaskList.
     * @param filePath
     */
    public Sigma(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        this.data = new File(filePath);
        tasks = new TaskList(storage.read(data));
    }

    /**
     * sigma.gui.Main method to run Sigma.
     * @param args
     */
    public static void main(String[] args) {
        Sigma sigma = new Sigma("data.txt");
        sigma.run();
    }

    /**
     * Runs Sigma.
     * Reads the user's input and executes the command.
     * Writes the data to the file.
     * @throws SigmaException
     * @throws NumberFormatException
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand;
            try {
                fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SigmaException e) {
                ui.print(e.getMessage());
            } catch (NumberFormatException e) {
                ui.print("What the sigma? I need a number!");
            } finally {
            }
            storage.write(this.data, tasks.getTasks());
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        // get first char of input as the command type
        try {
            Command c = Parser.parse(input);
            commandType = c.toString();
            if (c.isExit()) {
                System.exit(0);
            }
            return c.execute(tasks, ui, storage);
        } catch (SigmaException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "What the sigma? I need a number!";
        }
    }

    public String getCommandType() {
        return commandType;
    }
}







