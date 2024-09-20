package johncena.parser;

import java.util.ArrayList;
import johncena.commands.*;
        import johncena.exceptions.*;
        import johncena.tasks.Task;

public class InputHandler {
    private ArrayList<Task> tasks;

    public InputHandler(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list should not be null";
        this.tasks = tasks;
    }

    public Command handleInput(String input) throws CenaException {
        assert input != null : "Input should not be null";
        try {
            if (input.equals("bye")) {
                return new ByeCommand();
            } else if (input.equals("list")) {
                return new ListCommand(tasks);
            } else if (input.startsWith("mark ")) {
                return createMarkCommand(input);
            } else if (input.startsWith("unmark ")) {
                return createUnmarkCommand(input);
            } else if (input.startsWith("todo ")) {
                return createTodoCommand(input);
            } else if (input.startsWith("deadline ")) {
                return createDeadlineCommand(input);
            } else if (input.startsWith("event ")) {
                return createEventCommand(input);
            } else if (input.startsWith("delete ")) {
                return createDeleteCommand(input);
            } else if (isGreeting(input)) {
                return new HelloCommand();
            } else if (isHelpCommand(input)) {
                return new HelpCommand();
            } else if (input.startsWith("on ")) {
                return createOnCommand(input);
            } else if (input.startsWith("find ")) {
                return createFindCommand(input);
            } else {
                throw new CenaUnknownCommandException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (NumberFormatException e) {
            throw new CenaInvalidTaskIndexException("Task number must be a valid integer.");
        }
    }

    private Command createMarkCommand(String input) throws CenaInvalidTaskIndexException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        return new MarkCommand(tasks, taskIndex);
    }

    private Command createUnmarkCommand(String input) throws CenaInvalidTaskIndexException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        return new UnmarkCommand(tasks, taskIndex);
    }

    private Command createTodoCommand(String input) {
        String description = input.substring(5).trim();
        return new TodoCommand(tasks, description);
    }

    private Command createDeadlineCommand(String input) throws CenaInvalidDeadlineException {
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2) {
            throw new CenaInvalidDeadlineException("Incorrect description for deadline. It should contain only /by.");
        }
        return new DeadlineCommand(tasks, parts[0], parts[1]);
    }

    private Command createEventCommand(String input) throws CenaInvalidEventException {
        String[] parts = input.substring(6).split(" /from | /to ");
        if (parts.length < 3) {
            throw new CenaInvalidEventException("Incorrect description for event. It should contain /from and /to.");
        }
        return new EventCommand(tasks, parts[0], parts[1], parts[2]);
    }

    private Command createDeleteCommand(String input) throws CenaInvalidTaskIndexException {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        return new DeleteCommand(tasks, taskIndex);
    }

    private Command createOnCommand(String input) {
        String date = input.substring(3).trim();
        return new OnCommand(tasks, date);
    }

    private Command createFindCommand(String input) {
        String keyword = input.substring(5).trim();
        return new FindCommand(tasks, keyword);
    }

    private boolean isGreeting(String input) {
        return input.equals("hello") || input.equals("hi") || input.equals("hey") || input.equals("yo") || input.equals("sup");
    }

    private boolean isHelpCommand(String input) {
        return input.equals("help") || input.equals("commands") || input.equals("command") || input.equals("cmds") || input.equals("cmd");
    }
}