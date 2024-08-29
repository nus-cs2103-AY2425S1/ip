package nayana;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import nayana.command.*;
import nayana.task.*;
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param command The user input command.
     * @return The corresponding Command object.
     * @throws NayanaException If the command format is invalid or if parsing fails.
     */
    public static Command parse(String command) throws NayanaException {
        if (command.isBlank()) {
            throw new NayanaException("Tasks cannot be empty.");
        }

        String[] commandParts;

        if (command.equals("bye")) {
            return new ExitCommand();

        } else if (command.equals("list")) {
            return new ListCommand();

        } else if (command.startsWith("mark")) {
            return createMarkCommand(command);

        } else if (command.startsWith("unmark")) {
            return createUnmarkCommand(command);

        } else if (command.startsWith("delete")) {
            return createDeleteCommand(command);

        } else if (command.startsWith("deadline")) {
            return createDeadlineCommand(command);

        } else if (command.startsWith("event")) {
            return createEventCommand(command);

        } else if (command.startsWith("todo")) {
            return createTodoCommand(command);

        } else if (command.startsWith("find")) {
            return createFindCommand(command);
        }else {
            throw new NayanaException("Invalid command. Please use one of the following: deadline, event, todo, mark, unmark, delete, list, bye.");
        }
    }

    private static Command createFindCommand(String command) throws NayanaException {
        try {
            String[] parts = command.split("find ", 2);
            String findValue = parts[1].trim();

            if (findValue.isEmpty()) {
                throw new NayanaException("find value cannot be empty");
            } else {
                return new FindCommand(findValue);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NayanaException("Invalid format for find command.");
        }


    }

    private static Command createMarkCommand(String command) throws NayanaException {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new NayanaException("Invalid format for mark command.");
        }
    }

    private static Command createUnmarkCommand(String command) throws NayanaException {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            return new UnmarkCommand(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new NayanaException("Invalid format for unmark command.");
        }
    }

    private static Command createDeleteCommand(String command) throws NayanaException {
        try {
            int index = Integer.parseInt(command.split(" ")[1]) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new NayanaException("Invalid format for delete command.");
        }
    }

    private static Command createDeadlineCommand(String command) throws NayanaException {
        String[] parts = command.split(" /by ");
        if (parts.length != 2) {
            throw new NayanaException("Invalid format for deadline command.");
        }
        String description = parts[0].substring(8).trim();
        String deadline = parts[1].trim();
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new NayanaException("Description and deadline cannot be empty.");
        }
        if (!validDateFormat(deadline)) {
            throw new NayanaException("Invalid date format for deadline.");
        }
        LocalDate date = LocalDate.parse(deadline);
        Task deadlineTask = new Deadlines(description, date);
        return new AddCommand(deadlineTask);
    }

    private static Command createEventCommand(String command) throws NayanaException {
        String[] fromParts = command.split(" /from ");
        if (fromParts.length != 2) {
            throw new NayanaException("Invalid format for event command.");
        }
        String[] toParts = fromParts[1].split(" /to ");
        if (toParts.length != 2) {
            throw new NayanaException("Invalid format for event command.");
        }
        String description = fromParts[0].substring(5).trim();
        String startTime = toParts[0].trim();
        String endTime = toParts[1].trim();
        if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new NayanaException("Description, start time, and end time cannot be empty.");
        }
        if (!validDateFormat(startTime) || !validDateFormat(endTime)) {
            throw new NayanaException("Invalid date format for event.");
        }
        LocalDate startDate = LocalDate.parse(startTime);
        LocalDate endDate = LocalDate.parse(endTime);
        Task eventTask = new Event(description, startDate, endDate);
        return new AddCommand(eventTask);
    }

    private static Command createTodoCommand(String command) throws NayanaException {
        String[] parts = command.split("todo ");
        if (parts.length != 2) {
            throw new NayanaException("Invalid format for todo command.");
        }
        String description = parts[1].trim();
        if (description.isEmpty()) {
            throw new NayanaException("Description cannot be empty.");
        }
        Task todoTask = new ToDos(description);
        return new AddCommand(todoTask);
    }

    private static boolean validDateFormat(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
