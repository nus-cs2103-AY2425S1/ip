package mittens.parser;

import mittens.commands.*;
import mittens.task.Deadline;
import mittens.task.Event;
import mittens.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandParser {
    public Command parse(String input) throws BadInputException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]);
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new BadInputException("Argument for command 'mark' must be a number");
            }
        } else if (input.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]);
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new BadInputException("Argument for command 'mark' must be a number");
            }
        } else if (input.startsWith("delete")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]);
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new BadInputException("Argument for command 'delete' must be a number");
            }
        } else if (input.startsWith("find")) {
            String keyword = input.substring(5);

            return new FindCommand(keyword);
        } else if (input.startsWith("todo")) {
            String description = input.substring(5);

            Todo newTodo = new Todo(description);
            return new AddCommand(newTodo);
        } else if (input.startsWith("deadline")) {
            // Separate the inputs so that the first element contains the description while
            // the rest contains flags.
            String[] inputs = input.split(" /");
            String description = inputs[0].substring(9);

            LocalDate by = null;
            for (int i = 1; i < inputs.length; i++) {
                String[] flagWords = inputs[i].split(" ");
                if (flagWords[0].equals("by")) {
                    if (by == null) {
                        try {
                            by = LocalDate.parse(inputs[i].substring(3));
                        } catch (DateTimeParseException e) {
                            throw new BadInputException("Invalid date format for 'by' flag");
                        }
                    } else {
                        throw new BadInputException("Found duplicate of 'by' flag");
                    }
                } else {
                    throw new BadInputException("'%s' is not a known flag".formatted(flagWords[0]));
                }
            }

            if (by == null) {
                throw new BadInputException("Command 'deadline' must have a 'by' flag");
            }

            Deadline newDeadline = new Deadline(description, by);
            return new AddCommand(newDeadline);
        } else if (input.startsWith("event")) {
            // Separate the inputs so that the first element contains the description while
            // the rest contains flags.
            String[] inputs = input.split(" /");
            String description = inputs[0].substring(6);

            LocalDate from = null;
            LocalDate to = null;
            for (int i = 1; i < inputs.length; i++) {
                String[] flagWords = inputs[i].split(" ");
                if (flagWords[0].equals("from")) {
                    if (from == null) {
                        try {
                            from = LocalDate.parse(inputs[i].substring(5));
                        } catch (DateTimeParseException e) {
                            throw new BadInputException("Invalid date format for 'from' flag");
                        }
                    } else {
                        throw new BadInputException("Found duplicate of 'from' flag");
                    }
                } else if (flagWords[0].equals("to")) {
                    if (to == null) {
                        try {
                            to = LocalDate.parse(inputs[i].substring(3));
                        } catch (DateTimeParseException e) {
                            throw new BadInputException("Invalid date format for 'to' flag");
                        }
                    } else {
                        throw new BadInputException("Found duplicate of 'to' flag");
                    }
                } else {
                    throw new BadInputException("'%s' is not a known flag".formatted(flagWords[0]));
                }
            }

            if (from == null) {
                throw new BadInputException("Command 'event' must have a 'from' flag");
            }

            if (to == null) {
                throw new BadInputException("Command 'event' must have a 'to' flag");
            }

            Event newEvent = new Event(description, from, to);
            return new AddCommand(newEvent);
        } else {
            throw new BadInputException("'%s' is not a known command".formatted(input));
        }
    }
}
