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

public class Botty {

    private final Map<String, Command> commands;
    private final TaskManager taskManager;
    private final StorageHandler storageHandler;
    private final UI ui;

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

    public static void main(String[] args) {
        Botty botty = new Botty();

        botty.run();
    }

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
    private void saveTaskList() {
        try {
            storageHandler.saveTaskList(taskManager);
        } catch (BottyException ex) {
            ui.reply(ex.getMessage());
        }
    }
}
