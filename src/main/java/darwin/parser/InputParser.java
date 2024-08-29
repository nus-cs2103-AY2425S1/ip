package darwin.parser;

import darwin.command.Command;
import darwin.command.ExitCommand;
import darwin.command.ListCommand;
import darwin.command.MarkCommand;
import darwin.command.UnmarkCommand;
import darwin.command.DeleteCommand;
import darwin.command.AddTaskCommand;
import darwin.exception.IllegalTaskArgumentException;
import darwin.exception.IllegalTaskTypeException;
import darwin.task.Task;

public class InputParser {
    private static final String EMPTY_ARGS = "";

    public Command parse(String in) throws IllegalTaskTypeException, IllegalTaskArgumentException {
        in = in.trim();
        String[] parts = in.split("\\s+", 2);
        String cmd = parts[0];
        String args = parts.length == 2 ? parts[1] : InputParser.EMPTY_ARGS;

        switch (cmd) {
            case ExitCommand.CMD_WORD -> {
                return this.parseExit(args);
            }
            case ListCommand.CMD_WORD -> {
                return this.parseList(args);
            }
            case MarkCommand.CMD_WORD -> {
                return this.parseMark(args);
            }
            case UnmarkCommand.CMD_WORD -> {
                return this.parseUnmark(args);
            }
            case DeleteCommand.CMD_WORD -> {
                return this.parseDelete(args);
            }
            default -> {
                return this.parseAddTask(cmd, args);
            }
        }
    }

    private Command parseExit(String args) {
        return new ExitCommand();
    }

    private Command parseList(String args) {
        return new ListCommand();
    }

    private Command parseMark(String args) throws IllegalTaskArgumentException {
        return new MarkCommand(parseTaskIdx(args));
    }

    private Command parseUnmark(String args) throws IllegalTaskArgumentException {
        return new UnmarkCommand(parseTaskIdx(args));
    }

    private Command parseDelete(String args) throws IllegalTaskArgumentException {
        return new DeleteCommand(parseTaskIdx(args));
    }

    private Command parseAddTask(String cmd, String args) throws IllegalTaskTypeException, IllegalTaskArgumentException {
        Task task = TaskFactory.userInputToTask(cmd, args);
        return new AddTaskCommand(task);
    }

    private static int parseTaskIdx(String taskIdxStr) throws IllegalTaskArgumentException {
        try {
            return Integer.parseInt(taskIdxStr) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalTaskArgumentException(String.format("%s is not a valid number", taskIdxStr));
        }
    }
}
