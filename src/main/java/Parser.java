import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    private String command;
    private String[] descriptions;

    public Parser() {
    }

    public Command parse(String input) throws JudeException {
        int index;
        Task task;

        // Handle null input.
        if (input == null) {
            throw new JudeException("Poyo, invalid input. Try again...");
        }

        // Perform split of command, and a description, if present.
        String[] split = input.split(" ", 2);
        this.command = split[0];

        // Handle inputs with only a command (and without a description).
        if (command.equals("bye")) {

            if (split.length > 1) {
                throw new JudeException("Poyo,  The description of a " + command + " must be empty.");
            }
            return new ExitCommand();
        } else if (command.equals("list")) {

            if (split.length > 1) {
                throw new JudeException("Poyo,  The description of a " + command + " must be empty.");
            }
            return new ListCommand();
        } else if (split.length <= 1) {
            throw new JudeException("Poyo, the command either does not exist, or"
                    + " the description of a " + command + " cannot be empty.");

        }

        try {
            if (command.equals("mark")) {
                // Create an array with 1 element (without using split.)
                index = Integer.parseInt(split[1]);
                return new MarkCommand(index);
            }
            if (command.equals("unmark")) {
                // Create an array with 1 element (without using split.)
                index = Integer.parseInt(split[1]);
                return new UnmarkCommand(index);
            }
            if (command.equals("delete")) {
                // Create an array with 1 element (without using split.)
                index = Integer.parseInt(split[1]);
                return new DeleteCommand(index);
            }
        } catch (NumberFormatException ne) {
            throw new JudeException("Number format exception has occurred.");
        }

        if (command.equals("to-do")) {
            // Create an array with 1 element (without using split.)
            return new AddCommand(new Todo(split[1]));
        } else if (command.equals("deadline")) {

            descriptions = split[1].split(" /by ", 2);

            if (descriptions.length != 2) {
                throw new JudeException("Poyo,  the command "
                        + command + " must be provided with a description with the use of /by command.");
            }
            return new AddCommand(new Deadline(descriptions[0], descriptions[1]));

        } else if (command.equals("event")) {

            descriptions = split[1].split(" /from | /to ", 3);

            if (descriptions.length != 3) {
                throw new JudeException("Poyo,  the command " + command
                        + " must be provided with a description with the use of /from followed by /to command.");
            }
            return new AddCommand(new Event(descriptions[0], descriptions[1], descriptions[2]));
        }
    }
    
    public void parse(String input) throws JudeException {

        // Reset the fields.
        command = null;
        descriptions = null;

        // Handle null input.
        if (input == null) {
            throw new JudeException("Poyo, invalid input. Try again...");
        }

        // Perform split of command, and a description, if present.
        String[] split = input.split(" ", 2);
        this.command = split[0];

        // Handle inputs with only a command (and without a description).
        if (command.equals("bye") || command.equals("list")) {

            if (split.length > 1) {
                throw new JudeException("Poyo,  The description of a " + command + " must be empty.");
            }
            return;
        }

        // Handle inputs with a command and a description/s.
        else if (split.length <= 1) {
            throw new JudeException("Poyo, the command either does not exist, or"
                    + " the description of a " + command + " cannot be empty.")
        }

        if (command.equals("mark") || command.equals("unmark") || command.equals("to-do") || command.equals("delete")) {
            // Create an array with 1 element (without using split.)
            descriptions = new String[1];
            descriptions[0] = split[1];

        } else if (command.equals("deadline")) {

            descriptions = split[1].split(" /by ", 2);

            if (descriptions.length != 2) {
                throw new JudeException("Poyo,  the command "
                        + command + " must be provided with a description with the use of /by command.");
            }

        } else if (command.equals("event")) {

            descriptions = split[1].split(" /from | /to ", 3);

            if (descriptions.length != 3) {
                throw new JudeException("Poyo,  the command " + command
                        + " must be provided with a description with the use of /from followed by /to command.");
            }

            // Handle non-existing commands.
        } else {

            throw new JudeException("Poyo, the command provided does not exist.");

        }
    }

    public String getCommand() {
        return this.command;
    }

    public String[] getDescriptions() {
        return this.descriptions;
    }

    public static LocalDate convertDateFormat(String userInput) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(userInput, dateFormat);
        return date;
    }

    public static LocalTime convertTimeFormat(String userInput) {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(userInput, timeFormat);
        return time;
    }
}
