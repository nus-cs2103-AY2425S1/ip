package count;

import count.action.*;
import count.exception.CountException;
import count.task.*;
import count.exception.InvalidCommandException;
import count.exception.IncorrectFormatException;
import java.time.format.DateTimeParseException;
public class Parser {
    TaskList ls;
    public Parser(TaskList ls) {
        this.ls = ls;
    }

    public Action parse(String commandFull) throws CountException {
        if (commandFull.indexOf(" ") > -1) {
            return this.multiWordParser(commandFull, commandFull.substring(0, commandFull.indexOf(" ")).trim());
        } else {
            return this.singleWordParser(commandFull);
        }
    }

    private Action singleWordParser(String command) throws InvalidCommandException {
        switch (command.toLowerCase()) {
            case "hello":
                return new Greet();
            case "bye":
                return new Deactivate();
            case "list":
                return new ListReply(this.ls);
            case "help":
                return new Help();
            case "save":
                return new Save(this.ls);
            default:
                throw new InvalidCommandException();
        }
    }

    private Action multiWordParser(String command, String firstWord) throws CountException {
        String temp[] = command.split(" ", 2);
        String rest = temp[1];
        try {
            switch (firstWord.toLowerCase()) {
                case "mark":
                    return new Mark(this.ls, Integer.valueOf(rest));
                case "unmark":
                    return new Unmark(this.ls, Integer.valueOf(rest));
                case "delete":
                    return new Delete(this.ls, Integer.valueOf(rest));
                case "todo":
                    return new AddTask(this.ls, new ToDo(rest));
                case "deadline":
                    String commandSplitD[] = rest.split(" /by ", 2);
                    return new AddTask(this.ls, new Deadline(commandSplitD[0], commandSplitD[1]));
                case "event":
                    String commandSplitE[] = rest.split(" /from ", 2);
                    String startEndTime[] = commandSplitE[1].split(" /to ", 2);
                    return new AddTask(this.ls, new Event(commandSplitE[0], startEndTime[1], startEndTime[0]));
                default:
                    throw new InvalidCommandException();
            }
        } catch (NumberFormatException e) {
            throw new IncorrectFormatException("Use a number after mark/unmark/delete to specify the count.task targeted!\nType 'help' to see correct formatting examples");
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new IncorrectFormatException("Invalid format for event or deadline!\nType 'help' to see correct formatting examples");
        }
    }
}
