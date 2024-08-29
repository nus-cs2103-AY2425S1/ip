package sigma;

import sigma.command.Commands;
import sigma.exception.SigmaException;

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

    /**
     * Constructor for Sigma.
     * Initializes the Ui, Storage, and TaskList.
     * @param filePath
     */
    private Sigma(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        this.data = new File(filePath);
        tasks = new TaskList(storage.read(data));
    }

    /**
     * Main method to run Sigma.
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
                Commands c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (SigmaException e) {
                ui.print(e.getMessage());
            } catch (NumberFormatException e) {
                ui.print("What the sigma? I need a number!");
            } finally {
            }
            storage.write(this.data, tasks.getTasks());
        }
    }
}







