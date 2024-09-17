package count;

import java.time.format.DateTimeParseException;

import count.action.Action;
import count.action.AddTask;
import count.action.Deactivate;
import count.action.Delete;
import count.action.Find;
import count.action.Greet;
import count.action.Help;
import count.action.ListReply;
import count.action.Mark;
import count.action.Remind;
import count.action.Save;
import count.action.Unmark;
import count.exception.CountException;
import count.exception.IncorrectFormatException;
import count.exception.InvalidCommandException;
import count.task.Deadline;
import count.task.Event;
import count.task.ToDo;

/**
 * Parser contains methods which accept Strings to determine what action to take
 * @author Kieran Koh Jun Wei
 */
public class Parser {
    protected TaskList ls;
    protected String filePath;

    /**
     * Constructs Parser object
     * @param ls TaskList the Parser class receives to make changes to
     * @param filePath String of the file path the Parser class will pass to create Save objects
     */
    public Parser(TaskList ls, String filePath) {
        this.ls = ls;
        this.filePath = filePath;
    }

    /**
     * Checks if the command parsed consists of single or multiple words
     * If it has multiple words, it passes the whole command and the first word to multiWordParser
     * Else it is a single word, it passes the whole command to singleWordParser
     * @param commandFull This is the command parsed in by the user
     * @return Action decided by the parsers
     * @throws CountException upon parsing invalid commands
     */
    public Action parse(String commandFull) throws CountException {
        if (commandFull.indexOf(" ") > -1) {
            return this.multiWordParser(commandFull, commandFull.substring(0, commandFull.indexOf(" ")).trim());
        } else {
            return this.singleWordParser(commandFull);
        }
    }

    /**
     * Parses a single word String ignoring case, returning an Action
     * @param command A single word String
     * @return Action decided by the switch case
     * @throws InvalidCommandException upon encountering an unsupported command
     */
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
            return new Save(this.ls, this.filePath);
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses the whole command and the first word, returning an Action.
     * The variable rest refers to the whole command without the first word
     * @param command This is the command parsed in by the user
     * @param firstWord the first word in the command parameter
     * @return Action decided by the switch case
     * @throws CountException upon encountering invalid formatting for commands
     */
    private Action multiWordParser(String command, String firstWord) throws CountException {
        String[] temp = command.split(" ", 2);
        String rest = temp[1];
        try {
            switch (firstWord.toLowerCase()) {
            case "mark":
                return new Mark(this.ls, Integer.parseInt(rest));
            case "unmark":
                return new Unmark(this.ls, Integer.parseInt(rest));
            case "delete":
                return new Delete(this.ls, Integer.parseInt(rest));
            case "remind":
                return new Remind(this.ls, Integer.parseInt(rest));
            case "todo":
                return new AddTask(this.ls, new ToDo(rest));
            case "deadline":
                String[] commandSplitD = rest.split(" /by ", 2);
                return new AddTask(this.ls, new Deadline(commandSplitD[0], commandSplitD[1]));
            case "event":
                String[] commandSplitE = rest.split(" /from ", 2);
                String[] startEndTime = commandSplitE[1].split(" /to ", 2);
                return new AddTask(this.ls, new Event(commandSplitE[0], startEndTime[1], startEndTime[0]));
            case "find":
                return new Find(this.ls, rest);
            default:
                throw new InvalidCommandException();
            }
        } catch (NumberFormatException e) {
            throw new IncorrectFormatException("Use a number after mark/unmark/delete/remind to specify"
                    + " the task targeted!\n Type 'help' to see correct formatting examples");
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new IncorrectFormatException("Invalid format for event or deadline!\n"
                    + "Type 'help' to see correct formatting examples");
        }
    }
}
