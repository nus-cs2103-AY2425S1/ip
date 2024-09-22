package commands.undo;

import java.util.HashMap;
import java.util.Map;

import commands.Command;
import commands.undo.managers.PrevCommandManager;
import commands.undo.managers.PrevCreateManager;
import commands.undo.managers.PrevDeleteManager;
import commands.undo.managers.PrevToggleManager;
import exceptions.BrockException;
import storage.task.TaskStorage;
import storage.temp.TempStorage;
import task.TaskList;

/**
 * Represents an undo command entered by the user.
 */
public class UndoCommand extends Command {
    private static final Map<String, PrevCommandManager> PREV_CMD_MANAGERS = new HashMap<>();
    /**
     * Stores the command string associated with undo command.
     *
     * @param command Command string.
     */
    public UndoCommand(String command) {
        super(command);
        PREV_CMD_MANAGERS.put("create", new PrevCreateManager());
        PREV_CMD_MANAGERS.put("delete", new PrevDeleteManager());
        PREV_CMD_MANAGERS.put("toggle", new PrevToggleManager());
    }

    /**
     * Checks if the previous command is valid.
     *
     * @param tempStorage {@code TempStorage} object that stores info required to undo previous valid command.
     * @return The previous command, if it is valid.
     * @throws BrockException If the previous command is invalid.
     */
    private String processPreviousCommand(TempStorage tempStorage) throws BrockException {
        String previousCommand = tempStorage.getPreviousCommand();
        if (previousCommand == null) {
            throw new BrockException("Previous command is invalid or does not exist, cannot be undone!");
        }
        if (previousCommand.equals("undo")) {
            throw new BrockException("Cannot undo twice in a row!");
        }
        return previousCommand;
    }

    /**
     * Gets the category associated with the previous command.
     *
     * @param previousCommand The previous command to be examined.
     * @return The associated category.
     * @throws BrockException If the command does not have an associated category.
     */
    private String getPrevCommandCategory(String previousCommand) throws BrockException {
        // CHECKSTYLE.OFF: Indentation
        return switch (previousCommand) {
            case "mark" -> {
                PREV_CMD_MANAGERS.get("toggle").setIsMark(true);
                yield "toggle";
            }
            case "unmark" -> {
                PREV_CMD_MANAGERS.get("toggle").setIsMark(false);
                yield "toggle";
            }
            case "todo", "deadline", "event" -> "create";
            case "delete" -> "delete";
            default -> throw new BrockException("Previous valid command: " + previousCommand + " cannot be undone!");
        };
        // CHECKSTYLE.ON: Indentation
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Chatbot checks if undo command is valid.
     * If so, it performs the necessary command to undo the previous command.
     * Returns a response showing more info about the undo.
     * </p>
     *
     * @throws BrockException If undo command is invalid.
     */
    @Override
    public String execute(TaskStorage taskStorage, TempStorage tempStorage, TaskList tasks) throws BrockException {
        String previousCommand = this.processPreviousCommand(tempStorage);
        String responseHeader = "Undoing previous valid command: " + previousCommand + " ...\n\n";
        String responseBody;

        String previousCommandCategory = this.getPrevCommandCategory(previousCommand);
        PrevCommandManager prevCommandManager = PREV_CMD_MANAGERS.get(previousCommandCategory);
        responseBody = prevCommandManager.undoPrevCommand(taskStorage, tempStorage, tasks);

        return responseHeader + responseBody;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCommandType() {
        return "undo";
    }
}
