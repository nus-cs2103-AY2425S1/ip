package duke.parser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ShowOnDateCommand;
import duke.command.UnmarkCommand;
import duke.exception.EmptyDescriptionException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Parser {
    
    public static Command parse(String input) throws EmptyDescriptionException, UnknownCommandException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            return new MarkCommand(taskNumber);
        } else if (input.startsWith("unmark ")) {
            int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
            return new UnmarkCommand(taskNumber);
        } else if (input.startsWith("todo ")) {
            String taskDescription = input.substring(5).trim();
            if (taskDescription.isEmpty()) {
                throw new EmptyDescriptionException("todo");
            }
            return new AddCommand(new ToDo(taskDescription));
        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split(" /by ");
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                throw new EmptyDescriptionException("deadline");
            }
            String taskDescription = parts[0].trim();
            String by = parts[1].trim();
            try {
                LocalDate byDate = LocalDate.parse(by);  // Assumes the date is provided in yyyy-MM-dd format
                return new AddCommand(new Deadline(taskDescription, byDate));
            } catch (DateTimeParseException e) {
                throw new UnknownCommandException("The date format is incorrect. Please use yyyy-MM-dd format.");
            }
        } else if (input.startsWith("event ")) {
            String[] parts = input.substring(6).split(" /from | /to ");
            if (parts.length < 3 || parts[0].trim().isEmpty()) {
                throw new EmptyDescriptionException("event");
            }
            String taskDescription = parts[0].trim();
            String from = parts[1];
            String to = parts[2];
            return new AddCommand(new Event(taskDescription, from, to));
        } else if (input.startsWith("show on ")) {
            String dateStr = input.substring(8).trim();
            try {
                LocalDate date = LocalDate.parse(dateStr); // Accepting date in yyyy-MM-dd format
                return new ShowOnDateCommand(date);
            } catch (DateTimeParseException e) {
                throw new UnknownCommandException("The date format is incorrect. Please use yyyy-MM-dd format.");
            }
        } else {
            throw new UnknownCommandException(input);
        }
    }
}