package Parser;
import Command.*;
import Data.Deadlines;
import Data.Events;
import Data.ToDos;
import Exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

public class Parser {
    public static Command parse(String fullCommand) throws NahException{
        String[] cmd = fullCommand.split(" ", 2);

        switch (cmd[0].toLowerCase()) {
        case "bye": {
            return new ExitCommand();
        }
        case "list": {
            return new ListCommand();
        }
        case "mark": {
            int i;
            try {
                i = parseInt(cmd[1]);
            } catch (NumberFormatException e) {
                throw new NahException(" Nah!!! Please give me a valid ordinal number for the task\n");
            }
            return new MarkCommand(i);
        }
        case "unmark": {
            int i;
            try {
                i = parseInt(cmd[1]);
            } catch (NumberFormatException e) {
                throw new NahException(" Nah!!! Please give me a valid ordinal number for the task\n");
            }
            return new UnmarkCommand(i);
        }
        case "delete": {
            int i;
            try {
                i = parseInt(cmd[1]);
            } catch (NumberFormatException e) {
                throw new NahException(" Nah!!! Please give me a valid ordinal number for the task\n");
            }
            return new DeleteCommand(i);
        }
        case "dueon": {
            return new DueOnCommand(cmd[1]);
        }
        case "todo": {
            if (cmd.length < 2 || cmd[1].trim().isEmpty()) {
                throw new LackDescriptionException(" Nahhhh!!! Todo needs description\n");
            }
            return new AddCommand(new ToDos(cmd[1].trim()));
        }
        case "deadline": {
            if (cmd.length < 2 || cmd[1].trim().isEmpty()) {
                throw new LackDescriptionException(" Nahhhhh!!! Deadline needs description\n");
            }
            String[] des = cmd[1].split("/by", 2);
            if (des.length < 2 || des[1].trim().isEmpty()) {
                throw new LackDescriptionException(" Nahhhhhhhhh!!! Deadline needs deadline\n");
            }
            LocalDateTime by;
            try {
                by = LocalDateTime.parse(des[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new NahException(" Nahh!!! Time should be in the format yyyy-mm-dd hhmm, with valid date and time\n");
            }
            return new AddCommand(new Deadlines(des[0].trim(), by));
        }
        case "event": {
            if (cmd.length < 2 || cmd[1].trim().isEmpty()) {
                throw new LackDescriptionException(" Nahhhhh!!! Event needs description\n");
            }
            String[] des = cmd[1].split("/from", 2);
            if (des.length < 2 || des[1].trim().isEmpty()) {
                throw new LackDescriptionException(" Nahhhhhhhhh!!! Event needs starting time\n");
            }
            String[] time = des[1].split("/to", 2);
            if (time.length < 2 || time[1].trim().isEmpty()) {
                throw new LackDescriptionException(" Nahhhhhhhhhhhh!!! Event needs ending time\n");
            }
            LocalDateTime start, end;
            try {
                start = LocalDateTime.parse(time[0].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                end = LocalDateTime.parse(time[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new NahException(" Nahh!!! Time should be in the format yyyy-mm-dd hhmm, with valid date and time\n");
            }

            return new AddCommand(new Events(des[0].trim(), start, end));
        }
        default: {
            return new UnknownCommand();
        }
        }

    }
}
