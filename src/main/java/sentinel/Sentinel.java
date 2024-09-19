package sentinel;

import java.io.IOException;

import sentinel.command.ByeCommand;
import sentinel.command.Command;
import sentinel.exception.SentinelException;
import sentinel.storage.FileLoader;
import sentinel.storage.FileWriter;
import sentinel.ui.Ui;
import sentinel.utils.Parser;
import sentinel.utils.SentinelList;

/**
 * The {@code Sentinel} class is the main entry point for the Sentinel application.
 * It initializes the necessary components, handles user input, and executes commands.
 */
public class Sentinel {
    private static SentinelList list;
    private final Ui ui;

    /**
     * Constructs a new {@code Sentinel} instance.
     * Initializes the UI and attempts to load the task list from storage.
     * If loading fails, a new empty task list is created.
     */
    public Sentinel() {
        ui = new Ui();
        try {
            list = new FileLoader().loadTasks();
        } catch (Exception e) {
            ui.showError(e);
            list = new SentinelList();
        }
    }

    /**
     * Starts the main loop of the Sentinel application.
     * Displays the welcome message, reads user input, parses commands, and executes them.
     * The loop continues until the {@code ByeCommand} is issued.
     */
    public void run() {
        ui.showWelcome();
        Command command = null;
        do {
            try {
                String input = ui.readLine();
                ui.showLine();
                Sentinel.CommandType commandType = Parser.parseForCommand(input);
                command = Command.createCommand(commandType, ui, list);
                command.execute(input);
            } catch (IllegalArgumentException e) {
                ui.showUnrecognisedCommand();
            } catch (SentinelException e) {
                ui.showError(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ui.showLine();
            new FileWriter(list).saveTasks();
        } while (!(command instanceof ByeCommand));
    }

    /**
     * The main entry point of the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Sentinel().run();
    }

    /**
     * Enumeration of possible command types that can be issued by the user.
     */
    public enum CommandType {
        todo, deadline, event, list, find, mark, unmark, delete, reschedule, help, bye
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws SentinelException, IOException {
        Command command;
        new FileWriter(list).saveTasks();
        CommandType commandType = Parser.parseForCommand(input);
        command = Command.createCommand(commandType, ui, list);
        return command.execute(input);
    }
}
