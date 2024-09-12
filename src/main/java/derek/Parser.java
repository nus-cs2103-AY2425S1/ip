package derek;

import java.time.format.DateTimeParseException;

import derek.command.*;
import derek.exception.IncorrectCommandException;
import derek.task.Task;


/**
 * The {@code Parser} class handles the interpretation and execution of user commands.
 * It processes commands and directs them to the appropriate command classes.
 */
public class Parser {

    private String command;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a {@code Parser} object with the specified command, storage, and UI.
     *
     * @param command the user command input
     * @param storage the storage object containing the task list
     * @param ui the UI object to interact with the user
     */
    public Parser(String command, Storage storage, Ui ui) {
        this.command = command;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses and executes the user command.
     * It determines the type of command and calls the appropriate command class.
     * If the command is invalid, an error message is printed.
     */
    public String getCommand() throws IncorrectCommandException, DateTimeParseException{

        int sizeOfTaskList = this.storage.getTaskListSize();
        String[] words = command.split("\\s+");
        String command = words[0].toUpperCase();
        Command processedCommand;

        try {
            switch (CommandEnums.valueOf(command)) {
                case BYE:
                    processedCommand = new LeavingCommand(this.command,
                            this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                case LIST:
                    processedCommand = new ListCommand(this.command,
                            this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                case DELETE:
                    processedCommand = new DeleteCommand(this.command,
                            this.storage, this.ui, sizeOfTaskList);
                    return this.executeCommand(processedCommand);
                case MARK:
                    processedCommand = new CompleteCommand(this.command,
                            this.storage, this.ui, sizeOfTaskList);
                    return this.executeCommand(processedCommand);
                case UNMARK:
                    processedCommand = new IncompleteCommand(this.command,
                            this.storage, this.ui, sizeOfTaskList);
                    return this.executeCommand(processedCommand);
                case FIND:
                    processedCommand = new FindCommand(this.command,
                            this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                case DEADLINE:
                    processedCommand = new DeadlineCommand(this.command,
                            this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                case EVENT:
                    processedCommand = new EventCommand(this.command,
                            this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                case TODO:
                    processedCommand = new TodoCommand(this.command,
                            this.storage, this.ui);
                    return this.executeCommand(processedCommand);
                default:
                    throw new IncorrectCommandException("Please respond Y or N, Derek does not " +
                            "appreciate stupid people");

            }
        } catch (IllegalArgumentException e) {
            throw new IncorrectCommandException("Is it a todo, event, or deadline?\n"
                    + "Please enter your commands correctly for Derek (e.g. todo (task)), "
                    + "he keeps throwing tantrums");
        }

    }



    public String executeCommand(Command command) throws IncorrectCommandException {
        return command.execute();
    }


    public String getConsent() throws IncorrectCommandException {
        String command = this.command.toUpperCase();
        Command processedCommand;
        try {
            switch (CommandEnums.valueOf(command)) {
                case Y:
                    processedCommand = new ConsentCommand(this.command,
                            this.ui);
                    return this.executeCommand(processedCommand);
                case N:
                    processedCommand = new DeclineCommand(this.command,
                            this.ui);
                    return this.executeCommand(processedCommand);
                default:
                    throw new IncorrectCommandException("Please respond Y or N, Derek does not " +
                            "appreciate stupid people");
            }
        } catch (IllegalArgumentException e) {
            throw new IncorrectCommandException("Please respond Y or N, Derek does not " +
                    "appreciate stupid people");
        }

    }
}
