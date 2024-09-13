package ekud.commands;

import java.util.HashMap;

import ekud.components.Storage;
import ekud.components.TaskList;
import ekud.exceptions.EkudException;
import ekud.ui.Ui;

/**
 * Represents commands made by the user.
 *
 * @author uniqly
 */
public abstract class Command {
    /**
     * Represents the types of commands the user can make.
     */
    public enum Type {
        LIST, ADD, DELETE, MARK, UNMARK, EXIT, FIND;

        /** A set of aliases for the different {@link Type Types} of {@link Command} */
        private static final HashMap<String, Type> aliases;
        // initialize command aliases
        static {
            aliases = new HashMap<>();
            aliases.put("list", LIST);
            aliases.put("todo", ADD);
            aliases.put("deadline", ADD);
            aliases.put("event", ADD);
            aliases.put("delete", DELETE);
            aliases.put("mark", MARK);
            aliases.put("unmark", UNMARK);
            aliases.put("bye", EXIT);
            aliases.put("exit", EXIT);
            aliases.put("find", FIND);
        }

        /**
         * Converts a given {@link String} into a {@link Type}.
         * @param type The {@link String} to convert.
         * @return The converted {@link Type}.
         * @throws EkudException If {@code type} does not match to any {@link Type}.
         */
        public static Type getType(String type) throws EkudException {
            Type query = aliases.get(type.toLowerCase());
            if (query == null) {
                throw new EkudException("Quit yapping buddy, I have no clue what ya saying!");
            }
            return query;
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks The {@link TaskList} of tasks that may be required for the command.
     * @param ui The {@link Ui} which is responsible for printing outputs.
     * @param storage The {@link Storage} which may be required for the command.
     * @throws EkudException If the command execution cannot be completed.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        assert tasks != null : "tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "storage should not be null";
    }

    /**
     * Returns {@code true} if the command is a terminating command.
     *
     * @return If the command is terminating.
     */
    public abstract boolean isExit();
}
