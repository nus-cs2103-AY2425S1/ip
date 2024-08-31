package botty;

import java.util.HashMap;
import java.util.Map;

import botty.commands.*;
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
        commands.put("find", new FindCommand());
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

                Command command = commands.get(parsedInput.getCommand());

                if (command.isExit()) {
                    break;
                }

                ui.reply(command.execute(taskManager, parsedInput));

                if (command.changesTaskList()) {
                    saveTaskList();
                }

            } catch (BottyException exception) {
                ui.reply(exception.getMessage());
            }
        }

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
