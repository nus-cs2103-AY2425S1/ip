package nah.parser;

import static java.lang.Integer.parseInt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nah.command.Command;
import nah.command.EnumCommand;
import nah.data.Task;
import nah.exceptions.NahException;
/**
 * Handles the parsing of user input into Command object for execution.
 */
public class Parser {
    public static EnumCommand convertToEnumCommand(String s) {
        String cmd = s.trim().toLowerCase();
        switch (cmd) {
        case "" : return EnumCommand.UNKNOWN;
        case "bye" : return EnumCommand.BYE;
        case "list" : return EnumCommand.LIST;
        case "clean" : return EnumCommand.CLEAN;
        case "find" : return EnumCommand.FIND;
        case "dueon" : return EnumCommand.DUEON;
        case "mark" : return EnumCommand.MARK;
        case "unmark" : return EnumCommand.UNMARK;
        case "delete" : return EnumCommand.DELETE;
        case "todo" : return EnumCommand.TODO;
        case "deadline" : return EnumCommand.DEADLINE;
        case "event" : return EnumCommand.EVENT;
        default : return EnumCommand.UNKNOWN;
        }
    }
    /**
     * Converts a String command into an object of Command class.
     *
     * @param fullCommand the String that needs to be converted
     * @return a Command object
     * @throws NahException if something wrong happen while processing the String command
     */
    public static Command parse(String fullCommand) throws NahException {
        assert fullCommand != null : "fullCommand cannot be null";
        String[] cmd = fullCommand.split(" ", 2);
        assert cmd[0] != null : "unexpected null value for command";

        switch (cmd[0].toLowerCase()) {
        case "bye": {
            return new Command.ExitCommand();
        }
        case "clean": {
            if (cmd.length >= 2 && !cmd[1].trim().isEmpty()) {
                throw new NahException(" Nahh!!! Do not type nonsense after 'clean' command\n");
            }
            return new Command.CleanCommand();
        }
        case "list": {
            if (cmd.length >= 2 && !cmd[1].trim().isEmpty()) {
                throw new NahException(" Nahh!!! Do not type nonsense after 'list' command\n");
            }
            return new Command.ListCommand();
        }
        case "find": {
            if (cmd.length < 2) {
                return new Command.FindCommand("");
            }
            return new Command.FindCommand(cmd[1]);
        }
        case "mark": {
            if (cmd.length < 2) {
                throw new NahException(
                        " Nah!!! Mark command needs a number\n");
            }
            int i;
            try {
                i = parseInt(cmd[1]);
            } catch (NumberFormatException e) {
                throw new NahException(
                        " Nah.Nah!!! Please give me a valid ordinal number for the task\n");
            }
            return new Command.MarkCommand(i);
        }
        case "unmark": {
            if (cmd.length < 2) {
                throw new NahException(
                        " Nah!!! Unmark command needs a number\n");
            }
            int i;
            try {
                i = parseInt(cmd[1]);
            } catch (NumberFormatException e) {
                throw new NahException(
                        " Nah.Nah!!! Please give me a valid ordinal number for the task\n");
            }
            return new Command.UnmarkCommand(i);
        }
        case "delete": {
            if (cmd.length < 2) {
                throw new NahException(
                        " Nah!!! Delete command needs a number\n");
            }
            int i;
            try {
                i = parseInt(cmd[1]);
            } catch (NumberFormatException e) {
                throw new NahException(
                        " Nah.Nah!!! Please give me a valid ordinal number for the task\n");
            }
            return new Command.DeleteCommand(i);
        }
        case "help" : {
            if (cmd.length < 2 || cmd[1].trim().isEmpty()) {
                return new Command.HelpCommand(EnumCommand.UNKNOWN);
            } return new Command.HelpCommand(convertToEnumCommand(cmd[1]));
        }
        case "dueon": {
            if (cmd.length < 2) {
                throw new NahException(
                        " Nah!!! DueOn command needs a number\n");
            }
            return new Command.DueOnCommand(cmd[1]);
        }
        case "todo": {
            if (cmd.length < 2 || cmd[1].trim().isEmpty()) {
                throw new NahException.LackDescriptionException(
                        " Nahhhh!!! Todo needs description\n");
            }
            return new Command.AddCommand(new Task.ToDos(cmd[1].trim()));
        }
        case "deadline": {
            if (cmd.length < 2 || cmd[1].trim().isEmpty()) {
                throw new NahException.LackDescriptionException(
                        " Nahhhhh!!! Deadline needs description\n");
            }
            String[] des = cmd[1].split("/by", 2);
            if (des.length < 2 || des[1].trim().isEmpty()) {
                throw new NahException.LackDescriptionException(
                        " Nahhhhhhhhh!!! Deadline needs deadline\n");
            }
            LocalDateTime by;
            try {
                by = LocalDateTime.parse(des[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new NahException(
                        " Nahh!!! Time should be in the format yyyy-mm-dd hhmm, with valid date and time\n");
            }
            return new Command.AddCommand(new Task.Deadlines(des[0].trim(), by));
        }
        case "event": {
            if (cmd.length < 2 || cmd[1].trim().isEmpty()) {
                throw new NahException.LackDescriptionException(
                        " Nahhhhh!!! Event needs description\n");
            }
            String[] des = cmd[1].split("/from", 2);
            if (des.length < 2 || des[1].trim().isEmpty()) {
                throw new NahException.LackDescriptionException(
                        " Nahhhhhhhhh!!! Event needs starting time\n");
            }
            String[] time = des[1].split("/to", 2);
            if (time.length < 2 || time[1].trim().isEmpty()) {
                throw new NahException.LackDescriptionException(
                        " Nahhhhhhhhhhhh!!! Event needs ending time\n");
            }
            LocalDateTime start;
            LocalDateTime end;
            try {
                start = LocalDateTime.parse(time[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                end = LocalDateTime.parse(time[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new NahException(
                        " Nahh!!! Time should be in the format yyyy-mm-dd hhmm, with valid date and time\n");
            }

            return new Command.AddCommand(new Task.Events(des[0].trim(), start, end));
        }
        default: {
            return new Command.UnknownCommand();
        }
        }

    }
}
