import commands.Command;
import exception.PrimoException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Primo {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates UI and storage. If storage path has no file, create a file for storage.
     *
     * @param filePathString The path of the storage text file
     */
    public Primo(String filePathString) {
        this.ui = new Ui();
        this.storage = new Storage(filePathString);
        try {
            this.tasks = new TaskList(storage.load()); // throws PrimoException and IOException
        } catch (PrimoException | IOException e) {
            createDataFile();
        }
    }

    private void createDataFile() {
        try {
            Path directoryPath = Paths.get("./data");
            Path filePath = directoryPath.resolve("data.txt");
            Files.createDirectories(directoryPath);
            Files.createFile(filePath);
            ui.showLoadingError();
            tasks = new TaskList(); // Initializes an empty task list on error
            this.tasks = new TaskList();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        ui.showWelcome();
        try {
            Command c = Parser.parse(input); // Parses the command, may throw PrimoException
            return c.execute(tasks, ui, storage); // Executes the command, may throw PrimoException
        } catch (PrimoException e) {
            ui.showError(e.getMessage()); // Displays error message if an exception occurs
            return e.getMessage();
        } finally {
            ui.showLine(); // Shows a divider line after each command execution
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}
