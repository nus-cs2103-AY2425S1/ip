package botty;

import java.util.HashMap;
import java.util.Map;

import botty.commands.Command;
import botty.commands.DeadlineCommand;
import botty.commands.DeleteCommand;
import botty.commands.EventCommand;
import botty.commands.ExitCommand;
import botty.commands.ListCommand;
import botty.commands.MarkCommand;
import botty.commands.ParsedInput;
import botty.commands.TodoCommand;
import botty.commands.UnmarkCommand;
import botty.exceptions.BottyException;
import botty.exceptions.UnknownCommandException;
import botty.storage.StorageHandler;
import botty.tasks.TaskManager;
import botty.ui.UI;

/**
 * The main class
 */
public class Botty {
    // Map of command name to command instance
    private final Map<String, Command> commands;
    // Manager of the tasks
    private final TaskManager taskManager;
    // Storage handler to handle the saving and loading
    private final StorageHandler storageHandler;
    // UI to handle interaction with the user
    private final UI ui;

    /**
     * Constructs a {@code Botty} instance
     */
    public Botty() {
        commands = new HashMap<>();
        commands.put("list", new ListCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("todo", new TodoCommand());
        commands.put("deadline", new DeadlineCommand());
        commands.put("event", new EventCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("bye", new ExitCommand());

        ui = new UI();
        taskManager = new TaskManager();
        storageHandler = new StorageHandler("./data", "tasks");
    }

    /**
     * Runs the bot
     */
    public void run() {
        ui.displayIntroduction();

        loadTaskList();

        while (true) {
            try {
                String userInput = ui.getUserInput();

                ParsedInput parsedInput = ParsedInput.parse(userInput);

                if (!commands.containsKey(parsedInput.getCommand())) {
                    throw new UnknownCommandException(parsedInput.getCommand());
                }

                if (commands.get(parsedInput.getCommand()).isExit()) {
                    break;
                }

                ui.reply(commands.get(parsedInput.getCommand()).execute(taskManager, parsedInput));

            } catch (BottyException exception) {
                ui.reply(exception.getMessage());
            }
        }

        saveTaskList();
        ui.reply("Thank you for your continued patronage. Goodbye!");

    }

    /**
     * Loads the task list from local storage into the task manager
     */
    private void loadTaskList() {
        try {
            storageHandler.loadTaskList(taskManager);
            if (taskManager.size() > 0) {
                ui.reply("I've loaded your tasks from your previous session for you!");
            }
        } catch (BottyException ex) {
            ui.reply(ex.getMessage());
        }
    }

    /**
     * Saves the task list from task manager to local storage
     */
    private void saveTaskList() {
        ui.reply("I'll store your tasks for the future.");
        try {
            storageHandler.saveTaskList(taskManager);
        } catch (BottyException ex) {
            ui.reply(ex.getMessage());
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        Botty botty = new Botty();

        botty.run();
    }
}
