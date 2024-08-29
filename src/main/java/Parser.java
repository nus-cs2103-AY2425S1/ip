import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    /**
     * Parses a user input line and returns a Command object with logic encapsulated
     * within it to be executed.
     *
     * @param inputLine String that user inputted.
     * @return Command Subclass Objects
     * @throws GarfieldException An exception for Garfield chatbot
     */
    public static Command parse(String inputLine) throws GarfieldException {
        inputLine = inputLine.strip();

        if (inputLine.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        if (inputLine.equalsIgnoreCase("list")) {
            return new ListCommand();
        }

        if (inputLine.toLowerCase().startsWith("delete")) {
            try {
                return new DeleteCommand(getIntegerArg(inputLine));
            } catch (GarfieldException e) {
                throw new GarfieldException(e.getMessage() + "\n\n" + "Correct Usage: delete <task id>");
            }
        }

        if (inputLine.toLowerCase().startsWith("mark")) {
            try {
                return new MarkCommand(getIntegerArg(inputLine));
            } catch (GarfieldException e) {
                throw new GarfieldException(e.getMessage() + "\n\n" + "Correct Usage: mark <task id>");
            }
        }

        if (inputLine.toLowerCase().startsWith("unmark")) {
            try {
                return new UnmarkCommand(getIntegerArg(inputLine));
            } catch (GarfieldException e) {
                throw new GarfieldException(e.getMessage() + "\n\n" + "Correct Usage: unmark <task id>");
            }
        }

        if (inputLine.toLowerCase().startsWith("todo")) {
            try {
                Todo newTodo = parseTodo(inputLine);
                return new AddCommand(newTodo);
            } catch (GarfieldException e) {
                throw new GarfieldException(e.getMessage() + "\n\n" + "Correct Usage: todo <task description>");
            }
        }

        if (inputLine.toLowerCase().startsWith("deadline")) {
            try {
                Deadline newDeadline = parseDeadline(inputLine);
                return new AddCommand(newDeadline);
            } catch (GarfieldException e) {
                throw new GarfieldException(e.getMessage() + "\n\n"
                        + "Correct Usage: deadline <task description> /by yyyy-MM-dd HH:mm (24h time)");
            }
        }

        if (inputLine.toLowerCase().startsWith("event")) {
            try {
                Event newEvent = parseEvent(inputLine);
                return new AddCommand(newEvent);
            } catch (GarfieldException e) {
                throw new GarfieldException(e.getMessage() + "\n\n"
                        + "Correct Usage: event <task description> /from yyyy-MM-dd HH:mm (24h time)"
                        + " /to yyyy-MM-dd HH:mm (24h time)");
            }
        }

        throw new GarfieldException(inputLine + "? I’m not sure what that means. Can you give me a bit more to work with?");
    }

    /**
     * Extracts the first integer after the command keyword.
     * For commands of the form: [keyword] [integer].
     *
     * @param fullInput String that user inputted.
     * @return Integer after the keyword.
     * @throws GarfieldException An exception for Garfield chatbot.
     */
    private static int getIntegerArg(String fullInput) throws GarfieldException {
        String[] output = fullInput.trim().split("\\s+");
        if (output.length != 2) {
            throw new GarfieldException("No integer after the command to select a task!");
        }
        return Integer.parseInt(output[1]);
    }

    /**
     * Parses the user input for a todo command and returns a Todo object.
     *
     * @param fullInput String that user inputted.
     * @return Todo object.
     * @throws GarfieldException An exception for Garfield chatbot.
     */
    private static Todo parseTodo(String fullInput) throws GarfieldException {
        if (fullInput.length() <= 5) {
            throw new GarfieldException("You are missing a description!");
        }
        String todoDescription = fullInput.substring(5);

        if (todoDescription.isBlank()) {
            throw new GarfieldException("You are missing a description!");
        }

        return new Todo(todoDescription);
    }

    /**
     * Parses the user input for a deadline command and returns a Deadline object.
     *
     * @param fullInput String that user inputted.
     * @return Deadline object.
     * @throws GarfieldException An exception for Garfield chatbot.
     */
    private static Deadline parseDeadline(String fullInput) throws GarfieldException{
        if (fullInput.length() <= 9) {
            throw new GarfieldException("You are missing a description!");
        }
        String deadlineInput = fullInput.substring(9);

        if (deadlineInput.isBlank() || !deadlineInput.contains("/by")) {
            throw new GarfieldException("You are missing a description or the '/by' flag!");
        }

        String[] deadlineArgs = fullInput.split("/by");
        if (deadlineArgs.length != 2) {
            throw new GarfieldException("You didn't input a date and time after the '/by' flag!");
        }
        String deadlineDescription = deadlineArgs[0].strip();

        try {
            LocalDateTime deadlineBy = parseDateTime(deadlineArgs[1].strip());
            return new Deadline(deadlineDescription, deadlineBy);
        } catch (DateTimeParseException e) {
            throw new GarfieldException("Your datetime should be in the yyyy-MM-dd HH:mm (24h) format!");
        }
    }

    /**
     * Parses the user input for a event command and returns a Event object.
     *
     * @param fullInput String that user inputted.
     * @return Event object.
     * @throws GarfieldException An exception for Garfield chatbot.
     */
    private static Event parseEvent(String fullInput) throws GarfieldException {
        if (fullInput.length() <= 6) {
            throw new GarfieldException("You are missing a description!");
        }
        String eventInput = fullInput.substring(6);

        if (eventInput.isBlank() || !eventInput.contains("/from") || !eventInput.contains("/to")) {
            throw new GarfieldException("You are missing a description or the '/from' and '/to' flags!");
        }

        String[] eventInputTwo = eventInput.split("/from");

        if (!eventInputTwo[1].contains("/to")) {
            throw new GarfieldException("You are missing the '/to' flag!");
        }

        String[] eventArgs = eventInputTwo[1].split("/to");

        if (eventArgs.length != 2) {
            throw new GarfieldException("You didn't input a date and time after the '/to' flag!");
        }

        String eventDescription = eventInputTwo[0].strip();

        try {
            LocalDateTime eventFrom = parseDateTime(eventArgs[0].strip());
            LocalDateTime eventTo = parseDateTime(eventArgs[1].strip());
            return new Event(eventDescription, eventFrom, eventTo);
        } catch (DateTimeParseException e) {
            throw new GarfieldException("Your datetime should be in the yyyy-MM-dd HH:mm (24h) format!");
        }
    }

    /**
     * Returns a LocalDateTime object after parsing a String representation of a date and time.
     *
     * @param dateInput String representing the date and time of a Task.
     * @return LocalDateTime object.
     * @throws DateTimeParseException Error thrown if date time format is wrong in the input.
     */
    private static LocalDateTime parseDateTime(String dateInput) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateInput, formatter);
    }
}
