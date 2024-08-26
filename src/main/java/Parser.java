import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static LocalDateTime formatDateTime(String input) throws DeltaException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(input, formatter);
        }
        catch (DateTimeParseException e) {
            throw new DeltaException("""
                    OOPS!!! The format used for date/time is wrong!
                    \t Please follow the proper format:
                    \t yyyy-MM-dd HHmm
                    \t eg. 2024-08-25 1800""");
        }
    }

    public static Command parse(String input) throws DeltaException {
        String[] description = input.strip().split(" ", 2);
        String task = description[0];

        if (task.equalsIgnoreCase("bye")) {
            return new ExitCommand();

        } else if (task.equalsIgnoreCase("list")) {
            return new PrintCommand();

        } else if (task.equalsIgnoreCase("todo")) {
            if (description.length == 2) {
                String taskName = description[1].strip();
                 return new AddCommand(new Todo(taskName));
            } else {
                throw new DeltaException("""
                        OOPS!!! Description of todo cannot be left blank!
                        \t Please follow the proper format:
                        \t * unmark [index of task]""");
            }

        } else if (task.equalsIgnoreCase("deadline")) {
            if (description.length == 2) {
                String[] details = description[1].strip().split(" /by ");
                if (details.length == 2) {
                    String taskName = details[0].strip();
                    LocalDateTime by = formatDateTime(details[1].strip());
                    if (by.isBefore(LocalDateTime.now())) {
                        throw new DeltaException("""
                                OOPS!!! The date/time cannot be in the past!
                                \t Please set to a future date/time!
                                \t Follow the proper format:
                                \t * deadline [description] /by [date/time]""");
                    }
                    return new AddCommand(new Deadline(taskName, by));
                } else {
                    throw new DeltaException("""
                            OOPS!!! The format for deadline is wrong!
                            \t Please follow the proper format:
                            \t * deadline [description] /by [date/time]""");
                }
            } else {
                throw new DeltaException("""
                        OOPS!!! Description of deadline cannot be left blank!
                        \t Please follow the proper format:
                        \t * deadline [description] /by [date/time]""");
            }

        } else if (task.equalsIgnoreCase("event")) {
            if (description.length == 2) {
                String[] details = description[1].strip().split(" /from ");
                if (details.length == 2) {
                    String taskName = details[0].strip();
                    String[] timings = details[1].strip().split(" /to ");
                    if (timings.length == 2) {
                        LocalDateTime start = formatDateTime(timings[0].strip());
                        LocalDateTime end = formatDateTime(timings[1].strip());
                        if (start.isBefore(LocalDateTime.now()) || end.isBefore(LocalDateTime.now())) {
                            throw new DeltaException("""
                                    OOPS!!! The date/time cannot be in the past!
                                    \t Please set to a future date/time!
                                    \t Follow the proper format:
                                    \t * event [description] /from [start] /to [end]""");
                        } else if (end.isBefore(start)) {
                            throw new DeltaException("""
                                    OOPS!!! The end date cannot be before the start date!
                                    \t Please set the correct date/time!
                                    \t Follow the proper format:
                                    \t * event [description] /from [start] /to [end]""");
                        }
                        return new AddCommand(new Event(taskName, start, end));
                    } else {
                        throw new DeltaException("""
                                OOPS!!! The format for event is wrong!
                                \t Please follow the proper format:
                                \t * event [description] /from [start] /to [end]""");
                    }
                } else {
                    throw new DeltaException("""
                            OOPS!!! The format for event is wrong!
                            \t Please follow the proper format:
                            \t * event [description] /from [start] /to [end]""");
                }
            } else {
                throw new DeltaException("""
                        OOPS!!! Description of event cannot be left blank!
                        \t Please follow the proper format:
                        \t * event [description] /from [start] /to [end]""");
            }

        } else if (task.equalsIgnoreCase("mark")) {
            if (description.length == 2) {
                int taskIdx = Integer.parseInt(description[1].strip());
                return new MarkCommand(taskIdx);
            } else {
                throw new DeltaException("""
                        OOPS!!! The format to mark tasks is wrong!
                        \t Please follow the proper format:
                        \t * mark [index of task]""");
            }

        } else if (task.equalsIgnoreCase("unmark")) {
            if (description.length == 2) {
                int taskIdx = Integer.parseInt(description[1].strip());
                return new UnmarkCommand(taskIdx);
            } else {
                throw new DeltaException("""
                        OOPS!!! The format to unmark tasks is wrong!
                        \t Please follow the proper format:
                        \t * unmark [index of task]""");
            }

        } else if (task.equalsIgnoreCase("delete")) {
            if (description.length == 2) {
                int taskIdx = Integer.parseInt(description[1].strip());
                return new DeleteCommand(taskIdx);
            } else {
                throw new DeltaException("""
                        OOPS!!! The format to delete tasks is wrong!
                        \t Please follow the proper format:
                        \t * delete [index of task]""");
            }

        } else {
            throw new DeltaException("""
                    OOPS!!! I'm sorry, but I don't know what that means :-(
                    \t Please follow the proper formats:
                    \t * todo [description]
                    \t * deadline [description] /by [date/time]
                    \t * event [description] /from [start] /to [end]
                    \t * mark [index of task]
                    \t * unmark [index of task]
                    \t * delete [index of task]""");
        }
    }
}
