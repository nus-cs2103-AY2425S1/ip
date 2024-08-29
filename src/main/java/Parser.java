import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String command) throws NayanaException{
        if (command.isBlank()) {
            throw new NayanaException("tasks cannot be empty :(");

        } else if (command.equals("bye")) {
            return new ExitCommand();

        } else if (command.equals("list")) {
            return new ListCommand();

        }else if (command.startsWith("mark")) {
            try {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                return new MarkCommand(index);
            } catch (NumberFormatException e){
                throw new NayanaException("invalid format for mark");
            }

        }else if (command.startsWith("unmark")) {
            try {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new NayanaException("invalid format for mark");
            }
        } else if (command.startsWith("delete")) {
            try {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                return new DeleteCommand(index);
            } catch (NumberFormatException e){
                throw new NayanaException("invalid format for delete");
            }

        }else if (command.startsWith("deadline")) {

            String[] parts = command.split(" /by ");
            if (parts.length != 2) {
                throw new NayanaException("invalid format for deadline");
            } else if (parts[0].substring(8).trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new NayanaException("description and deadline cannot be empty.");
            } else {
                String description = parts[0].substring(8).trim();
                String deadline = parts[1].trim();
                if (!validDateFormat(deadline)) {
                    throw new NayanaException("invalid date format");
                } else {
                    LocalDate d1 = LocalDate.parse(deadline);
                    Task deadlineTask = new Deadlines(description, d1);
                    return new AddCommand(deadlineTask);
                }
            }

        } else if (command.startsWith("event")) {
            String[] fromParts = command.split(" /from ");
            if (fromParts.length != 2) {
                throw new NayanaException("invalid format for event");
            } else {
                String[] toParts = fromParts[1].split(" /to ");
                if (toParts.length != 2) {
                    throw new NayanaException("invalid format for event");
                } else if (fromParts[0].substring(5).trim().isEmpty() ||
                      toParts[0].trim().isEmpty() || toParts[1].trim().isEmpty()) {
                    throw new NayanaException("description, start time and end time cannot be empty.");
                } else {
                    String description = fromParts[0].substring(5);
                    String startTime = toParts[0].trim();
                    String endTime = toParts[1].trim();
                    if (!validDateFormat(startTime) || !validDateFormat(endTime)) {
                        throw new NayanaException("invalid date format");
                    } else {
                        LocalDate d1 = LocalDate.parse(startTime);
                        LocalDate d2 = LocalDate.parse(endTime);

                        Task eventTask = new Event(description,d1,d2);
                        return new AddCommand(eventTask);
                    }
                }
            }
        } else if (command.startsWith("todo")) {
            String[] parts = command.split("todo ");
            if (parts.length != 2) {
                throw new NayanaException("invalid format for todo");
            } else if (parts[1].trim().isEmpty()) {
                throw new NayanaException("description cannot be empty.");
            } else {
                String description = parts[1].trim();
                Task todoTask = new ToDos(description);
                return new AddCommand(todoTask);
            }
        } else {
            throw new NayanaException("Please use the correct format and start your prompts \nwith " +
                  "deadline, event, todo, mark, unmark, delete, list, bye ");
        }
    }

    private static boolean validDateFormat(String deadline) {
        try {
            LocalDate d1 = LocalDate.parse(deadline);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
