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
}
