package mummy.command;

import java.io.IOException;
import java.util.HashMap;

import mummy.task.Task;
import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.ui.Ui;
import mummy.utility.Storage;

public abstract class Command {

    private final HashMap<String, String> arguments;

    private enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE,
        EVENT, DELETE, UNKNOWN
    }

    public Command(HashMap<String, String> arguments) {
        this.arguments = arguments;
    }

    public Command() {
        this.arguments = new HashMap<>();
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws MummyException;

    public abstract boolean isExit();

    public HashMap<String, String> getArguments() {
        return this.arguments;
    }

    public void saveTaskListToStorage(TaskList taskList, Storage storage) throws MummyException {
        try {
            storage.save(taskList.toFileRecords());
        } catch (IOException exception) {
            throw new MummyException("Something went wrong when saving to file: "
                    + exception.getMessage());
        }
    }

    public void addTask(Task task, TaskList taskList, Ui ui, Storage storage) throws MummyException {
        taskList.add(task);
        saveTaskListToStorage(taskList, storage);
        ui.show(String.format(
                "Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.\n",
                task, taskList.getCount()
        ));
    }

    public static Command of(HashMap<String, String> arguments) throws MummyException {
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(
                    arguments.getOrDefault("command", "")
                            .toUpperCase()
            );
        } catch (IllegalArgumentException exception) {
            commandType = CommandType.UNKNOWN;
        }

        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(arguments);
        case UNMARK:
            return new UnmarkCommand(arguments);
        case TODO:
            return new ToDoCommand(arguments);
        case DEADLINE:
            return new DeadlineCommand(arguments);
        case EVENT:
            return new EventCommand(arguments);
        case DELETE:
            return new DeleteCommand(arguments);
        default:
            throw new MummyException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
