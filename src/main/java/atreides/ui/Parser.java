package atreides.ui;

import java.util.Set;

import atreides.command.AddCommand;
import atreides.command.Command;
import atreides.command.DeleteCommand;
import atreides.command.ErrorCommand;
import atreides.command.ExitCommand;
import atreides.command.FindCommand;
import atreides.command.ListCommand;
import atreides.command.MarkCommand;
import atreides.command.UnMarkCommand;

/**
 * Represents the class to parse commands and creates relevant commands
 */
public class Parser {

    /**
     * Parses the message and creates relevant commands
     * @param msg
     * @return
     */
    public static Command parse(String msg) {
        Set<String> commands = Set.of("mark", "unmark", "delete", "todo", "event", "deadline", "find");
        if (msg.equals("list")) {
            return new ListCommand();
        } else if (msg.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else {
            String[] words = msg.split(" ");
            if (commands.contains(words[0]) && words.length < 2) {
                return new ErrorCommand("Description of " + words[0] + " cannot be empty");
            }
            if (words[0].equals("mark")) {
                int index = Integer.parseInt(words[1]) - 1;
                return new MarkCommand(index);
            } else if (words[0].equals("unmark")) {
                int index = Integer.parseInt(words[1]) - 1;
                return new UnMarkCommand(index);
            } else if (words[0].equals("delete")) {
                int index = Integer.parseInt(words[1]) - 1;
                return new DeleteCommand(index);
            } else if (words[0].equals("find")) {
                return new FindCommand(words[1]);
            } else {
                return new AddCommand(msg);
            }
        }
    }
}
