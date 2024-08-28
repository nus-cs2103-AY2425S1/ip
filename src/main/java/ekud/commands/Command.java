package ekud.commands;

import ekud.exceptions.EkudException;
import ekud.components.Storage;
import ekud.components.Ui;
import ekud.components.TaskList;

import java.util.HashMap;

public abstract class Command {
    public enum Type {
        LIST, ADD, DELETE, MARK, UNMARK, EXIT;

        public static Type getType(String type) throws EkudException {
            Type query = aliases.get(type.toLowerCase());
            if (query == null) {
                throw new EkudException("Quit yapping buddy, I have no clue what ya saying!");
            }
            return query;
        }

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
        }
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException;

    public abstract boolean isExit();
}
