package mummy.ui;

import java.io.IOException;

import mummy.command.Command;
import mummy.task.TaskList;
import mummy.utility.Storage;


/**
 * Represents the main class for the Mummy application.
 * The Mummy class is responsible for initializing the application, loading tasks from storage,
 * and handling user commands.
 */
public class Mummy {
    private static final String LOGO = "Mummy";

    private static final String IO_Path = "./data/mummy.txt";

    private Storage storage;

    private TaskList taskList;

    private Command currentCommand;

    public Mummy() {
        this.storage = new Storage(IO_Path);

        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (IOException e) {
            this.taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            this.currentCommand = Command.of(input);
            return this.currentCommand.execute(this.taskList, this.storage);
        } catch (MummyException exception) {
            return exception.getMessage();
        }
    }

    public Command.CommandType getCommandType() {
        if (this.currentCommand == null) {
            return Command.CommandType.UNKNOWN;
        }

        return this.currentCommand.getCommandType();
    }

    public String generateWelcomeMessage() {
        return String.format("Hello from %s\nWhat can I do for you?", LOGO);
    }
}
