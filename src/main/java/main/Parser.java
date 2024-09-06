package main;

import command.*;
import exception.DashException;
import exception.EmptyDescriptionException;
import exception.IncorrectCommandUseException;
import exception.UnknownCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String input) throws DashException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        try {
            if (input.equals("bye")) {
                return new ByeCommand();
            } else if (input.equals("list")) {
                return new ListCommand();
            } else if (input.startsWith("mark")) { //mark command
                return new MarkCommand(input);
            } else if (input.startsWith("unmark")) {                       //unmark command
                return new UnmarkCommand(input);
            } else if (input.startsWith("todo")) {
                String[] string = input.split(" ", 2);
                if (string.length == 1) {
                    throw new EmptyDescriptionException("Description of todo command cannot be empty.");
                }
                String description = string[1];
                return new TodoCommand(description);
            } else if (input.startsWith("deadline")) {
                //Exception Handling
                String[] string = input.split("/", 2); //"deadline XX" and "by XX"
                if (string.length == 1) {
                    throw new EmptyDescriptionException("Description of deadline command cannot be empty. " +
                            "Do remember to include '/'.");
                }
                String[] byString = string[1].split(" ", 2); //"by" and "XX"
                if (!byString[0].equals("by")) {
                    throw new IncorrectCommandUseException("Please include the /by command.");
                }
                if (byString.length == 1) {
                    throw new IncorrectCommandUseException("Please include deadline!");
                }

                LocalDateTime deadline = LocalDateTime.parse(byString[1], inputFormatter);
                String[] string2 = string[0].split(" ", 2); //"deadline" and "XX"
                String description = string2[1];
                return new DeadlineCommand(description, deadline);
            } else if (input.startsWith("event")) {

                //Exception Handling
                String[] string = input.split("/", 3); //"event XX" and "from XX" and "to XX"
                if (string.length == 1) {
                    throw new EmptyDescriptionException("Description of todo command cannot be empty. " +
                            "Do remember to include '/'.");
                } else if (string.length == 2) {
                    throw new IncorrectCommandUseException("Incorrect use of event command!");
                }
                String[] fromString = string[1].split(" ", 2); //"from" and "XX"
                if (fromString.length == 1) {
                    throw new IncorrectCommandUseException("Incorrect use of event command!");
                }
                if (!fromString[0].equals("from")) {
                    throw new IncorrectCommandUseException("Please include the /from command.");
                }
                String[] toString = string[2].split(" ", 2); //"to" and "XX"
                if (toString.length == 1) {
                    throw new IncorrectCommandUseException("Incorrect use of event command!");
                }
                if (!toString[0].equals("to")) {
                    throw new IncorrectCommandUseException("Please include the /to command.");
                }
                LocalDateTime from = LocalDateTime.parse(fromString[1], inputFormatter);
                LocalDateTime to = LocalDateTime.parse(toString[1], inputFormatter);
                String[] eventString = string[0].split(" ", 2); //"event" and "XX"
                String description = eventString[1];
                return new EventCommand(description, from, to);
            } else if (input.startsWith("delete")) {
                return new DeleteCommand(input);
            } else {
                throw new UnknownCommandException("Unknown command.");
            }
        } catch (DashException e) {
            Ui.showError(e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.showDateTimeParseError(e.getMessage());
        } catch (Exception e) {
            Ui.showUnexpectedError(e.getMessage());
        }
        return new UnknownCommand();
    }
}
