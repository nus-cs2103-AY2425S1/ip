package tomo;

import exception.ParserException;

import command.ByeCommand;
import command.Command;
import command.ListCommand;
import command.MarkCommand;
import command.ToDoCommand;
import command.UnmarkCommand;
import command.DeleteCommand;
import command.DeadlineCommand;
import command.EventCommand;
import command.FindCommand;
/**
 *  Understands of input command
 */

public class Parser {
    /**
     * Parses the user command
     * 
     * @param cmd The command input from user
     * @return Type of command and additional arguments to support next step
     * @throws ParserException If the command has invalid format
     */
    public Command parse(String cmdline) throws ParserException {
        if (cmdline.split(" ").length == 0) {
            throw new ParserException("No command");
        }
        String type = cmdline.split(" ")[0];
        if (type.equals("bye")) {
            return new ByeCommand(cmdline);
        } else if (type.equals("list")) {
            return new ListCommand(cmdline);
        } else if (type.equals("mark")) {
            return new MarkCommand(cmdline);
        } else if (type.equals("unmark")) {
            return new UnmarkCommand(cmdline);
        } else if (type.equals("delete")) {
            return new DeleteCommand(cmdline);
        } else if (type.equals("todo")) {
            return new ToDoCommand(cmdline);
        } else if (type.equals("deadline")) {
            return new DeadlineCommand(cmdline);
        } else if (type.equals("event")) {
            return new EventCommand(cmdline);
        } else if (type.equals("find")) {
            return new FindCommand(cmdline);
        } else {
            throw new ParserException("Unknown command");
        }
    }
}
