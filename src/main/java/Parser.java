import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    public CommandType commandTypeParser(String input) {
        String commandString = input.split(" ")[0];
        CommandType command = CommandType.fromString(commandString);
        return command;
    }

    public Command parseCommand(String userInput) throws JustbotException {
        String[] words = userInput.split(" ", 2);
        CommandType commandType = CommandType.fromString(words[0].trim());

        switch (commandType) {
            case TODO:
                return new TodoCommand(words.length > 1 ? words[1].trim() : "");
            case DEADLINE:
                if (words.length < 2) {
                    throw new JustbotException("Invalid deadline command format. Use: deadline <description> /by <yyyy-MM-dd HH:mm>");
                }
                String[] deadlineParts = words[1].split("/by", 2);
                if (deadlineParts.length < 2) {
                    throw new JustbotException("Invalid deadline command format. Use: deadline <description> /by <yyyy-MM-dd HH:mm>");
                }
                String deadlineDescription = deadlineParts[0].trim();
                LocalDateTime byDateTime = parseDateTime(deadlineParts[1].trim());
                return new DeadlineCommand(deadlineDescription, byDateTime);
            case EVENT:
                if (words.length < 2) {
                    throw new JustbotException("Invalid event command format. Use: event <description> /from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>");
                }
                String[] eventParts = words[1].split("/from", 2);
                if (eventParts.length < 2) {
                    throw new JustbotException("Invalid event command format. Use: event <description> /from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>");
                }
                String description = eventParts[0].trim();
                String[] timeParts = eventParts[1].split("/to", 2);
                if (timeParts.length < 2) {
                    throw new JustbotException("Invalid event time format. Use: /from <yyyy-MM-dd HH:mm> /to <yyyy-MM-dd HH:mm>");
                }
                LocalDateTime startDateTime = parseDateTime(timeParts[0].trim());
                LocalDateTime endDateTime = parseDateTime(timeParts[1].trim());
                return new EventCommand(description, startDateTime, endDateTime);
            case LIST:
                return new ListCommand();
            case MARK:
                if (words.length < 2) {
                    throw new JustbotException("Please provide a task number to mark.");
                }
                int markNumber = Integer.parseInt(words[1].trim());
                return new MarkCommand(markNumber);
            case UNMARK:
                if (words.length < 2) {
                    throw new JustbotException("Please provide a task number to unmark.");
                }
                int unmarkNumber = Integer.parseInt(words[1].trim());
                return new UnmarkCommand(unmarkNumber);
            case DELETE:
                if (words.length < 2) {
                    throw new JustbotException("Please provide a task number to delete.");
                }
                int deleteNumber = Integer.parseInt(words[1].trim());
                return new DeleteCommand(deleteNumber);
            case BYE:
                return new ByeCommand();
            case UNKNOWN:
            default:
                throw new JustbotException("Unknown command: " + words[0]);
        }
    }

    private LocalDateTime parseDateTime(String dateTimeStr) throws JustbotException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTimeStr.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new JustbotException("Invalid date and time format. Please use 'yyyy-MM-dd HH:mm'.");
        }
    }
    public int extractTaskNumber(String input) throws JustbotException {
        String[] splitInputMark = input.split(" ");

        if (splitInputMark.length != 2) {
            throw new JustbotException("Hey man you have provided me an invalid format for mark.\n" +
                    "Use the format: mark [task number]");
        }
        try {
            return Integer.parseInt(splitInputMark[1]);
        } catch (NumberFormatException e) {
            throw new JustbotException("Hey man please input a number for the task number!");
        }
    }

    public Deadline parseDeadlineTask(String input) throws JustbotException {
        String deadlineDescription = extractDeadlineDescription(input);
        LocalDateTime deadlineDateTime = extractDeadlineDateTime(input);
        return new Deadline(deadlineDescription, deadlineDateTime);
    }

    public String extractDeadlineDescription(String input) throws JustbotException {
        String[] splitPartsDeadline = input.split("/by");

        if (splitPartsDeadline.length != 2) {
            throw new JustbotException("Hey man please enter the deadline in the following format:\n" +
                    "  deadline [description] /by DD/MM/YYYY HHmm\n\n" +
                    "For example:\n" +
                    "  deadline run /by 26/09/2024 1800");
        }

        String commandAndDescriptionDeadline = splitPartsDeadline[0].trim();
        String deadlineDescription = commandAndDescriptionDeadline.substring(8).trim();

        if (deadlineDescription.isBlank()) {
            throw new JustbotException("Hey man the description cannot be blank!");
        }

        return deadlineDescription;
    }

    public LocalDateTime extractDeadlineDateTime(String input) throws JustbotException {
        String[] splitPartsDeadline = input.split("/by");

        if (splitPartsDeadline.length != 2) {
            throw new JustbotException("Hey man you have provided an invalid format for the deadline.\n" +
                    "Use the format: deadline [description] /by DD/MM/YYYY HHmm");
        }

        String deadlineDateTimeString = splitPartsDeadline[1].trim();

        if (deadlineDateTimeString.isBlank()) {
            throw new JustbotException("Hey man the deadline date cannot be blank!");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        if (!deadlineDateTimeString.matches("\\d{2}/\\d{2}/\\d{4} \\d{4}")) {
            throw new JustbotException("Hey man the deadline date and time must be in the format: dd/MM/yyyy HHmm");
        }

        try {
            return LocalDateTime.parse(deadlineDateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new JustbotException("Hey man please enter the deadline in the following format:\n" +
                    "  deadline [description] /by DD/MM/YYYY HHmm\n\n" +
                    "For example:\n" +
                    "  deadline run /by 26/09/2024 1800");
        }
    }

    public Event parseEventTask(String input) throws JustbotException {
        String[] eventParts = extractEventParts(input);
        String eventDescription = extractEventDescription(eventParts);
        String[] eventTimings = extractEventTimings(eventParts[1]);
        LocalDateTime startDateTime = parseEventDateTime(eventTimings[0]);
        LocalDateTime endDateTime = parseEventDateTime(eventTimings[1]);

        validateEventTimings(startDateTime, endDateTime);

        return new Event(eventDescription, startDateTime, endDateTime);
    }

    public String[] extractEventParts(String input) throws JustbotException {
        String[] splitPartsEvent = input.split("/from");

        if (splitPartsEvent.length < 2) {
            throw new JustbotException("Hey man, you have provided me an invalid format for an event.\n" +
                    "Please enter the event in the following format:\n" +
                    "  event [description] /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm\n\n" +
                    "For example:\n" +
                    "  event meeting /from 26/09/2024 1400 /to 26/09/2024 1600");
        }

        return splitPartsEvent;
    }

    public String extractEventDescription(String[] eventParts) throws JustbotException {
        String commandAndDescriptionEvent = eventParts[0].trim();
        String eventDescription = commandAndDescriptionEvent.substring(5).trim();

        if (eventDescription.isBlank()) {
            throw new JustbotException("Hey man, the description cannot be blank!");
        }

        return eventDescription;
    }

    public String[] extractEventTimings(String startAndEnd) throws JustbotException {
        String[] splitStartEnd = startAndEnd.split("/to");

        if (splitStartEnd.length < 2) {
            throw new JustbotException("Hey man, you have provided me an invalid format for event timing.\n" +
                    "Please enter the event in the following format:\n" +
                    "  event [description] /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm\n\n" +
                    "For example:\n" +
                    "  event meeting /from 26/09/2024 1400 /to 26/09/2024 1600");
        }

        String eventStart = splitStartEnd[0].trim();
        String eventEnd = splitStartEnd[1].trim();

        if (eventStart.isBlank()) {
            throw new JustbotException("Hey man, the start of the event cannot be blank!");
        }

        if (eventEnd.isBlank()) {
            throw new JustbotException("Hey man, the end of the event cannot be blank!");
        }

        return new String[]{eventStart, eventEnd};
    }

    public LocalDateTime parseEventDateTime(String dateTimeString) throws JustbotException {
        if (!dateTimeString.matches("\\d{2}/\\d{2}/\\d{4} \\d{4}")) {
            throw new JustbotException("Event date and time must be in the format: dd/MM/yyyy HHmm");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new JustbotException("Hey man, please enter the date and time in the correct format: dd/MM/yyyy HHmm");
        }
    }

    public void validateEventTimings(LocalDateTime startDateTime, LocalDateTime endDateTime) throws JustbotException {
        if (startDateTime.isAfter(endDateTime)) {
            throw new JustbotException("Hey man, why is the event start time after the event end time?");
        }
    }

    public Todo parseTodoTask(String input) throws JustbotException {
        String description = extractTodoDescription(input);
        return new Todo(description);
    }

    public String extractTodoDescription(String input) throws JustbotException {
        String[] splitPartsTodo = input.split(" ", 2);

        if (splitPartsTodo.length < 2 || splitPartsTodo[1].trim().isEmpty()) {
            throw new JustbotException("Hey man, the description for a todo cannot be blank!\n" +
                    "Use the format: todo [description]");
        }

        return splitPartsTodo[1].trim();
    }

    public int extractDeleteTaskNumber(String input) throws JustbotException {
        String[] splitInputMark = input.split(" ");

        if (splitInputMark.length != 2) {
            throw new JustbotException("Hey man you have provided me an invalid format for delete.\n" +
                    "Use the format: delete [task number]");
        }

        try {
            return Integer.parseInt(splitInputMark[1]);
        } catch (NumberFormatException e) {
            throw new JustbotException("Hey man please input a valid number for the task number!");
        }
    }

}
