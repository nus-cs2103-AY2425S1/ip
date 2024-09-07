package henry.util;

import henry.HenryException;
import henry.command.AddCommand;
import henry.command.ChangeStatusCommand;
import henry.command.Command;
import henry.command.DeleteCommand;
import henry.command.ExitCommand;
import henry.command.FindCommand;
import henry.command.PrintCommand;


/**
 * Deals with making sense of the user command
 */
public class Parser {

    /**
     * Returns a boolean to see if user input
     *                 wants to end the program
     *
     * @param input String given by user
     * @return boolean to see if user input is "bye"
     */
    public static Command parse(String input) throws HenryException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new PrintCommand();
        }

        String[] words = input.split(" ");
        String command = words[0].toLowerCase();
        if (command.equals("mark") || command.equals("unmark")) {
            return new ChangeStatusCommand(words);
        } else if (command.equals("delete")) {
            return new DeleteCommand(words[1]);
        } else if (command.equals("find")) {
            return new FindCommand(words[1]);
        } else {
            return new AddCommand(input);
        }
    }
}
