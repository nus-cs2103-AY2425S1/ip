package shnoop;

import java.io.FileNotFoundException;
import java.io.IOException;

import shnoop.command.Command;
import shnoop.exceptions.IncompleteEventOrDeadlineException;
import shnoop.exceptions.ShnoopException;
import shnoop.storage.Storage;
import shnoop.tasks.TaskList;
import shnoop.ui.Parser;
import shnoop.ui.Ui;


/**
 * Represents the main Chatbot which utilizes multiple packages to create a cohesive experience.
 */
public class Shnoop {
    private static java.nio.file.Path path;
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private String commandType;

    /**
     * Creates a Shnoop instance with a specified filepath referring to where information will be saved in.
     *
     * @param path Filepath where text file representing the task list will be stored.
     */
    public Shnoop(java.nio.file.Path path) {
        storage = new Storage(path);
        ui = new Ui();

        try {
            tasks = new TaskList(storage.load());
        } catch (ShnoopException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (FileNotFoundException efile) {
            ui.showError(efile.getMessage());
            throw new RuntimeException();
        }
    }

    public Shnoop() {
        this(java.nio.file.Paths.get(System.getProperty("user.home"), "my", "apps", "dir"));
    }

    /**
     * Starts the Shnoop instance and begin running the program.
     */
    public void run() {
        // @@author CS2103T Website
        // Reused from https://nus-cs2103-ay2425s1.github.io/website/schedule/week3/project.html
        // with minor modifications
        ui.showWelcome();
        boolean isExit = false;
        ui.showLine();
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ShnoopException e) {
                ui.showError(e.getMessage());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (IncompleteEventOrDeadlineException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        // @@author CS2103T Website
    }


    public String getCommandType() {
        return commandType;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            commandType = c.getClass().getSimpleName();
            return output;
        } catch (ShnoopException | IOException | IncompleteEventOrDeadlineException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Begins the program.
     *
     * @param args Arguments to be taken in (if any).
     */
    public static void main(String[] args) {
        // @@author Steve Hills
        // Reused from https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
        // with minor modifications
        String home = System.getProperty("user.home");
        path = java.nio.file.Paths.get(home, "my", "apps", "dir");
        // @@author Steve Hills

        Shnoop shnoop = new Shnoop(path);
        shnoop.run();
    }
}
