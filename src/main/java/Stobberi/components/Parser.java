package Stobberi.components;

import Stobberi.Command.Command;
import Stobberi.Command.DateCommand;
import Stobberi.Command.DeadlineCommand;
import Stobberi.Command.DeleteCommand;
import Stobberi.Command.EventCommand;
import Stobberi.Command.ExitCommand;
import Stobberi.Command.ListCommand;
import Stobberi.Command.MarkCommand;
import Stobberi.Command.TodoCommand;
import Stobberi.Command.UnmarkCommand;
import Stobberi.StobberiException.NoNumberStobberiException;
import Stobberi.StobberiException.NoSuchTaskStobberiException;
import Stobberi.StobberiException.StobberiException;

public class Parser {
    private Parser() {}

    public static Command parse(String fullCommand, TaskList taskList) throws StobberiException {
        String[] parts = fullCommand.split(" ");
        String command = parts[0];
        String restOfCommand = String.join(" ", java.util.Arrays.copyOfRange(parts, 1, parts.length));
        switch (command) {
            case "bye" :
                return new ExitCommand();
            case "list" :
                return new ListCommand(taskList);
            case "mark" :
                if (restOfCommand.matches("\\d+")) {
                    int taskNumber = Integer.parseInt(restOfCommand);
                    return new MarkCommand(taskList, taskNumber);
                } else {
                    throw new NoNumberStobberiException("Where is the number?");
                }
            case "unmark" :
                if (restOfCommand.matches("\\d+")) {
                    int taskNumber = Integer.parseInt(restOfCommand);
                    return new UnmarkCommand(taskList, taskNumber);
                } else {
                    throw new NoNumberStobberiException("Where is the number?");
                }
            case "delete":
                if (restOfCommand.matches("\\d+")) {
                    int taskNumber = Integer.parseInt(restOfCommand);
                    return new DeleteCommand(taskList, taskNumber);
                } else {
                    throw new NoNumberStobberiException("Where is the number?");
                }
            case "date":
                return new DateCommand(taskList, restOfCommand);
            case "todo":
                return new TodoCommand(taskList, restOfCommand);
            case "deadline":
                return new DeadlineCommand(taskList, restOfCommand);
            case "event":
                return new EventCommand(taskList, restOfCommand);
        }
        throw new NoSuchTaskStobberiException("I'm sorry! That is NOT an executable command");
    }
}