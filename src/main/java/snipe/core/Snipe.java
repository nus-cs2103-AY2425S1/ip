package snipe.core;

import snipe.util.Parser;
import snipe.exception.SnipeException;
import snipe.storage.Storage;
import snipe.util.Ui;
import snipe.command.Command;
import snipe.task.Task;

import java.util.ArrayList;
import java.io.IOException;

/**
 * The {@code snipe.core.Snipe} class represents a task management system that interacts with the user via snipe.command-line input.
 * It supports commands for adding, deleting, marking, unmarking, and listing tasks.
 */
public class Snipe {
    private Storage taskListStorage;
    private TaskList tasks;
    private Ui ui;

    public Snipe(String filePath) {
        this.ui = new Ui();
        this.taskListStorage = new Storage(filePath);
        try {
            this.tasks = new TaskList(taskListStorage.readTaskList());
        } catch (SnipeException | IOException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Initializes the chat, greeting the user and handling user input.
     */
    public void initChat() throws IOException, SnipeException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, taskListStorage);
                isExit = c.isExit();
            } catch (SnipeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.close();
    }

    /**
     * The main method that starts the application.
     *
     * @param args snipe.command.Command-line arguments (not used).
     */
    public static void main(String[] args) throws IOException, SnipeException {
        Snipe snipe = new Snipe("src/main/txt/taskList.txt");
        snipe.initChat();
    }
}


