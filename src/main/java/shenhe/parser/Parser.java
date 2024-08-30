package shenhe.parser;

import shenhe.command.*;
import shenhe.exception.EmptyTaskDescriptionException;
import shenhe.exception.InvalidListEnquiry;
import shenhe.exception.UnknownTaskException;
import shenhe.task.Deadline;
import shenhe.task.Event;
import shenhe.task.Task;
import shenhe.task.Todo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Parser {
    public static Task parseFile(String line) {
        // Check the first character to determine the task type
        if (line.startsWith("T")) {
            // Assume the format is "T | isDone | description"
            String[] parts = line.split(" \\| ");
            boolean isDone = Objects.equals(parts[1], "1");
            return new Todo(parts[2], isDone); // Adjust constructor as per your shenhe.task.Todo class definition
        } else if (line.startsWith("D")) {
            // Assume the format is "D | isDone | description | by"
            String[] parts = line.split(" \\| ");
            boolean isDone = parts[1].equals("1");
            try {
                LocalDateTime by = DateParser.parseFile(parts[3]); // Parse date string
                return new Deadline(parts[2], isDone, by);
            } catch (Exception e) {
                System.out.println("Invalid date/time format.");
                return null; // Return null if parsing fails
            }
        } else if (line.startsWith("E")) {
            // Assume the format is "E | isDone | description | from | to"
            String[] parts = line.split(" \\| ");
            boolean isDone = parts[1].equals("1");
            try {
                String from = parts[3]; // Parse "from" date string
                String to = parts[4];   // Parse "to" date string
                return new Event(parts[2], isDone, from, to); // Adjust constructor as per your shenhe.task.Event class definition
            } catch (Exception e) {
                System.out.println("Invalid date/time format.");
                return null; // Return null if parsing fails
            }
        }

        // Handle unknown types by returning null
        return null;
    }
    public static Command parse(String userInput) throws Exception {

        if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.startsWith("mark")) {
            return new MarkCommand(userInput);
        } else if (userInput.startsWith("unmark")) {
            return new UnmarkCommand(userInput);
        } else if (userInput.startsWith("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.startsWith(("todo"))) {
            if (userInput.trim().length() == 4) {
                throw new EmptyTaskDescriptionException();
            }
            return new TodoCommand(userInput);
        } else if (userInput.startsWith("deadline")) {
            if (userInput.trim().length() == 8) {
                throw new EmptyTaskDescriptionException();
            }
            return new DeadlineCommand(userInput);
        } else if (userInput.startsWith("event")) {
            if (userInput.trim().length() == 5) {
                throw new EmptyTaskDescriptionException();
            }
            return new EventCommand(userInput);
        } else if (userInput.startsWith("list")) {
           if (userInput.trim().length() != 4) {
               throw new InvalidListEnquiry();
           }
            return new ListCommand(userInput);
        }
        throw new UnknownTaskException();
    }

}
