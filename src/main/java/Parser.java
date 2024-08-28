import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String[] processInput(String input) throws ChatterBoxError {
        String[] userCommand = new String[4];
        String[] command = input.split(" ", 2);
        Commands commandType = Commands.valueOf(command[0].toUpperCase());
        userCommand[0] = command[0];
        switch (commandType) {
        case BYE, LIST:
            break;
        case MARK, UNMARK, DELETE:
            try {
                int taskNum = Integer.parseInt(command[1]) - 1;
                userCommand[1] = String.valueOf(taskNum);
                break;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new ChatterBoxMarkError();
            }
        case TODO:
            try {
                userCommand[1] = command[1];
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ChatterBoxToDoError();
            }
        case DEADLINE:
            try {
                String[] details = oneTimeExtractor(command[1]);
                userCommand[1] = details[0];
                userCommand[2] = details[1];
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ChatterBoxDeadlineError();
            } catch (ChatterBoxDeadlineError e) {
                throw e;
            }
        case EVENT:
            try {
                String[] details = twoTimeExtractor(command[1]);
                userCommand[1] = details[0];
                userCommand[2] = details[1];
                userCommand[3] = details[2];
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ChatterBoxEventError();
            } catch (ChatterBoxEventError e) {
                throw e;
            }
        default:
            throw new ChatterBoxError();
        }
        return userCommand;
    }

    public static String[] processSaveInput(String input) throws ChatterBoxError {
        String[] userCommand  = new String[2];
        try {
            String[] command = input.split(", ", 2);
            userCommand[0] = command[1];
            if (command[0].equals("done")) {
                userCommand[1] = "mark ";
            } else {
                userCommand[1] = null;
            }
            return userCommand;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatterBoxError("Unable to parse line: " + input + "\n");
        }
    }
    public static String[] oneTimeExtractor(String input) throws ChatterBoxDeadlineError {
        String oneTimeRegex = "^(.*) /by (.*)$";
        Matcher oneTimeMatcher = Pattern.compile(oneTimeRegex).matcher(input);
        String[] parsedNameTime = new String[2];
        if (oneTimeMatcher.find()) {
            parsedNameTime[0] = oneTimeMatcher.group(1);
            parsedNameTime[1] = oneTimeMatcher.group(2);
            return parsedNameTime;
        } else {
            throw new ChatterBoxDeadlineError();
        }
    }

    public static String[] twoTimeExtractor(String input) throws ChatterBoxEventError {
        String twoTimeRegex = "^(.*) /from (.*) /to (.*)$";
        Matcher twoTimeMatcher = Pattern.compile(twoTimeRegex).matcher(input);
        String[] parsedNameTime = new String[3];
        if (twoTimeMatcher.find()) {
            parsedNameTime[0] = twoTimeMatcher.group(1);
            parsedNameTime[1] = twoTimeMatcher.group(2);
            parsedNameTime[2] = twoTimeMatcher.group(3);
            return parsedNameTime;
        } else {
            throw new ChatterBoxEventError();
        }
    }

    public static LocalDateTime processDateTime(String dateString) throws ChatterBoxInvalidDateTimeError, ChatterBoxInvalidDateError {
        DateTimeFormatter dateTimeFormatter;
        boolean containsTime;
        if (dateString.trim().contains(" ")) { // Contains time
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            containsTime = true;
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            containsTime = false;
        }
        try {
            if (containsTime) {
                return LocalDateTime.parse(dateString.trim(), dateTimeFormatter);
            } else {
                return LocalDate.parse(dateString.trim(), dateTimeFormatter).atStartOfDay();
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            if (containsTime) {
                throw new ChatterBoxInvalidDateTimeError();
            } else {
                throw new ChatterBoxInvalidDateError();
            }
        }
    }
}
