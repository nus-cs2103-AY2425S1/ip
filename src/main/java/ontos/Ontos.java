package ontos;

import java.nio.file.Path;
import java.nio.file.Paths;

import ontos.commands.Command;
import ontos.parser.Parser;
import ontos.storage.SaveManager;
import ontos.task.TaskList;
import ontos.ui.Ui;

/**
 * Represents the main application that manages the task list.
 * It handles user interactions, command parsing, task management, and saving/loading tasks.
 */
public class Ontos {
    /** The user interface used to print outputs from the chatbot in the CLI */
    private Ui ui;
    /** The list of tasks the chatbot manages */
    private TaskList tasks;
    /** The Savemanager object used to read and write to the save file */
    private SaveManager saveManager;

    /**
     * Constructs an Ontos instance with the specified save location.
     *
     * @param saveLocation The location of the save file relative to the project's root directory.
     */
    public Ontos(String saveLocation) {
        Path projectRoot = Paths.get("").toAbsolutePath();
        this.ui = new Ui();
        this.saveManager = new SaveManager(projectRoot, saveLocation);
        saveManager.createSave();
        this.tasks = saveManager.loadSave();
    }

    /**
     * Starts the main loop of the application, which handles user input, executes commands,
     * and manages the task list until the user exits.
     */
    public void run() {
        ui.showLine();
        ui.greet();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, saveManager);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                ui.badInput();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns the response from the chatbot based on the user input.
     *
     * @param input The user input to be processed.
     * @return The response from the chatbot.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, saveManager);
        } catch (IllegalArgumentException e) {
            return ui.badInput();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the greeting message from the chatbot.
     *
     * @return The greeting message.
     */
    public String getGreeting() {
        return ui.greet();
    }
}
