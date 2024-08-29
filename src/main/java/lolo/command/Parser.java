package lolo.command;

import lolo.LoloException;
import lolo.command.*;
import lolo.task.Deadline;
import lolo.task.Event;
import lolo.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String fullCommand) throws LoloException {
        String[] parts;
        try {
            if (fullCommand.equalsIgnoreCase("bye")) {
                return new ExitCommand();
            } else if (fullCommand.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else if (fullCommand.startsWith("todo ")) {
                return new AddCommand(new ToDo(fullCommand.substring(5)));
            } else if (fullCommand.startsWith("deadline ")) {
                parts = fullCommand.split(" /by ");
                String description = parts[0].substring(9);
                LocalDateTime by = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return new AddCommand(new Deadline(description, by));
            } else if (fullCommand.startsWith("event ")) {
                parts = fullCommand.split(" /from ");
                String description = parts[0].substring(6);
                String[] fromTo = parts[1].split(" /to ");
                LocalDateTime from = LocalDateTime.parse(fromTo[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                LocalDateTime to = LocalDateTime.parse(fromTo[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return new AddCommand(new Event(description, from, to));
            } else if (fullCommand.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                return new MarkCommand(taskNumber);
            } else if (fullCommand.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                return new UnmarkCommand(taskNumber);
            } else if (fullCommand.startsWith("delete ")) {
                int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
                return new DeleteCommand(taskNumber);
            } else if (fullCommand.startsWith("on ")) {
                LocalDateTime date = LocalDateTime.parse(fullCommand.substring(3) + " 0000", DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return new ListOnDateCommand(date);
            } else {
                throw new LoloException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (DateTimeParseException e) {
            throw new LoloException("The date and time format should be yyyy-mm-dd HHmm.");
        }
    }
}
