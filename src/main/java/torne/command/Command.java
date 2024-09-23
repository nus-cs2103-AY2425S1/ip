package torne.command;

import torne.command.argument.CommandArgumentList;
import torne.exception.TorneException;
import torne.storage.Storage;
import torne.task.TaskHandler;
import torne.ui.ChatOutput;

import java.util.List;
import java.util.Map;

/**
 * Abstract class determining the behaviour of Torne commands.
 */
public abstract class Command {
    protected TaskHandler taskHandler;
    protected ChatOutput output;
    protected Storage storage;
    protected String name; // name of command
    protected String description = ""; // description of command
    protected CommandArgumentList args;

    protected Command(String name, String description, CommandArgumentList args) {
        // Constructor for when the command has args it wants to use
        this.name = name;
        this.description = description;
        this.args = args;
    }

    protected Command(String name, String description) {
        // Constructor for name and description but no arguments
        this.name = name;
        this.description = description;
        this.args = CommandArgumentList.empty();
    }

    protected Command(String name) {
        // Default constructor - only a name
        this.name = name;
        this.description = "";
        this.args = CommandArgumentList.empty();
    }

    /**
     * Initialises this {@link Command} with the given {@link TaskHandler}, {@link ChatOutput}, and {@link Storage}
     * that the command will use.
     *
     * @param taskHandler Default {@link TaskHandler}.
     * @param output      Default {@link ChatOutput}.
     * @param storage     Default {@link Storage}.
     */
    public void init(TaskHandler taskHandler, ChatOutput output, Storage storage) {
        this.taskHandler = taskHandler;
        this.output = output;
        this.storage = storage;
    }

    /**
     * Executes the command. Takes in a map of arguments.
     *
     * @param arguments The `Map<String, Command>` of arguments passed to the command.
     */
    public abstract void execute(Map<String, String> arguments) throws TorneException;

    /**
     * Returns a list of arguments that this command accepts as a {@link java.util.List} of
     * {@link String}s.
     *
     * @return List of arguments. Default is the empty list [].
     */
    public List<String> getArgStringList() {
        return args.getArgParserStringList();
    }

    public String getName() {
        return name;
    }

    public CommandArgumentList getArgs() {
        return args;
    }

}
