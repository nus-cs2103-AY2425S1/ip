package duck.commands;

import duck.Parser;
import duck.TaskList;

/**
 * Factory class for {@code Command} objects.
 */
public enum CommandType {
    LIST("list") {
        @Override
        public Command createCommand(TaskList taskList, Parser lineBuffer) {
            return new ListCommand(taskList);
        }
    },

    FIND("find") {
        @Override
        public Command createCommand(TaskList taskList, Parser lineBuffer) {
            return new FindCommand(taskList, lineBuffer);
        }
    },

    MARK("mark") {
        @Override
        public Command createCommand(TaskList taskList, Parser lineBuffer) {
            return new MarkCommand(taskList, lineBuffer);
        }
    },

    UNMARK("unmark") {
        @Override
        public Command createCommand(TaskList taskList, Parser lineBuffer) {
            return new UnmarkCommand(taskList, lineBuffer);
        }
    },

    DELETE("delete") {
        @Override
        public Command createCommand(TaskList taskList, Parser lineBuffer) {
            return new DeleteCommand(taskList, lineBuffer);
        }
    },

    BYE("bye") {
        @Override
        public Command createCommand(TaskList taskList, Parser lineBuffer) {
            return new ByeCommand();
        }
    },

    TODO("todo") {
        @Override
        public Command createCommand(TaskList taskList, Parser lineBuffer) {
            return new TodoCommand(taskList, lineBuffer);
        }
    },

    DEADLINE("deadline") {
        @Override
        public Command createCommand(TaskList taskList, Parser lineBuffer) {
            return new DeadlineCommand(taskList, lineBuffer);
        }
    },

    EVENT("event") {
        @Override
        public Command createCommand(TaskList taskList, Parser lineBuffer) {
            return new EventCommand(taskList, lineBuffer);
        }
    },

    DOAFTER("doafter") {
        @Override
        public Command createCommand(TaskList taskList, Parser lineBuffer) {
            return new DoAfterCommand(taskList, lineBuffer);
        }
    };

    private final String name;

    private CommandType(String name) {
        this.name = name;
    }

    /**
     * Check if the name of the command (its String form)
     * equals the given String.
     *
     * @param str String to be compared against.
     * @return a boolean
     */
    public boolean equalsName(String str) {
        return this.name.equals(str);
    }

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Checks if the given string is a {@code CommandType}.
     *
     * @param test String to be tested.
     * @return Boolean indicating whether the given string is a {@code CommandType}.
     */
    public static boolean contains(String test) {
        for (CommandType c : CommandType.values()) {
            if (c.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public abstract Command createCommand(TaskList taskList, Parser lineBuffer);
}
