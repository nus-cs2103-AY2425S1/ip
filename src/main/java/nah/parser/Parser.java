package nah.parser;
import nah.command.Command;
import nah.data.Task;
import nah.exceptions.NahException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

public class Parser {
    /**
     * Convert a String command into a meaningfull command
     * @param fullCommand
     * @return
     * @throws NahException
     */
    public static Command parse(String fullCommand) throws NahException {
        String[] cmd = fullCommand.split(" ", 2);

        switch (cmd[0].toLowerCase()) {
        case "bye": {
            return new Command.ExitCommand();
        }
        case "clean": {
            if (cmd.length >= 2 && !cmd[1].trim().isEmpty()) {
                throw new NahException(" Nahh!!! Do not type nonsense after 'list' command\n");
            }
            return new Command.CleanCommand();
        }
        case "list": {
            if (cmd.length >= 2 && !cmd[1].trim().isEmpty()) {
                throw new NahException(" Nahh!!! Do not type nonsense after 'list' command\n");
            }
            return new Command.ListCommand();
        }
        case "mark": {
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
            int i;
            try {
                i = parseInt(cmd[1]);
            } catch (NumberFormatException e) {
                throw new NahException(
                        " Nah.Nah!!! Please give me a valid ordinal number for the task\n");
            }
            return new Command.DeleteCommand(i);
        }
        case "dueon": {
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
            LocalDateTime start, end;
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
