package infinity.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

import infinity.command.Command;
import infinity.infinityexception.InfinityException;
import infinity.parser.runnable.Runnable;

/**
 * This class handles the parsing of user inputs.
 */
public class Parser {
    /** Delimiter used for multi-operations */
    public static final String MULTI_OPERATION_DELIMITER = "&&";
    /** Bot reply for insufficient arguments */
    private static final String BOT_INSUFFICIENT_ARGUMENTS = "Insufficient arguments provided\n";
    /** Bot reply for invalid date */
    private static final String BOT_INVALID_DATE = "Invalid date time format\n";
    /** Bot reply for invalid inputs */
    private static final String BOT_INVALID_INPUT = "Invalid inputs given\n";

    /**
     * Checks the length of the value given whether it is within the specified range.
     * Also checks if the value is parsable to an integer.
     *
     * @param value The value to be checked as a String.
     * @param min The minimum length of the value.
     * @param max The maximum length of the value.
     * @param minNo The minimum value of the number.
     * @param maxNo The maximum value of the number.
     * @return True if the value is within the specified range and is a number, false otherwise.
     */
    private static boolean isNumberInRange(String value, int min, int max, int minNo, int maxNo) {
        boolean isWithinLength = value.length() >= min && value.length() <= max;
        boolean isValueAllDigits = value.chars().allMatch(Character::isDigit);
        boolean isWithinRange = Integer.parseInt(value) >= minNo && Integer.parseInt(value) <= maxNo;
        return isWithinLength && isValueAllDigits && isWithinRange;
    }

    /**
     * Prepends a zero to the value if it is less than 10.
     *
     * @param value The value to be checked.
     * @return The value as a String with a zero prepended if it is less than 10.
     */
    public static String prependZero(int value) {
        return value < 10 ? "0" + value : String.valueOf(value);
    }

    /**
     * Parses a LocalDateTime object to a String in the format dd-mm-yyyy hhmm.
     *
     * @param dateTime The LocalDateTime object to be parsed.
     * @return The LocalDateTime object as a String in the format dd-mm-yyyy hhmm.
     */
    public static String parseDateTimeString(LocalDateTime dateTime) {
        return prependZero(dateTime.getDayOfMonth())
                + "-"
                + prependZero(dateTime.getMonthValue())
                + "-" + dateTime.getYear()
                + " " + prependZero(dateTime.getHour())
                + prependZero(dateTime.getMinute());
    }

    /**
     * Parses a LocalDateTime object to a String in the format Month dd, yyyy, hhmm.
     *
     * @param dateTime The LocalDateTime object to be parsed.
     * @return The LocalDateTime object as a String in the format Month dd, yyyy, hhmm.
     */
    public static String parseDateTimeStringAlt(LocalDateTime dateTime) {
        return Month.of(dateTime.getMonthValue()).getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH)
                + " "
                + prependZero(dateTime.getDayOfMonth())
                + ", " + dateTime.getYear()
                + ", " + prependZero(dateTime.getHour())
                + prependZero(dateTime.getMinute());
    }

    /**
     * Parses a String to a LocalDateTime object.
     *
     * @param dateTime The String to be parsed in dd-mm-yyyy hhmm or dd/mm/yyyy hhmm,
     *                 hhmm is in 24-hour format.
     * @return The String as a LocalDateTime object.
     * @throws DateTimeException If the String is not in the correct format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DateTimeException {
        // Accepted Format: dd-mm-yyyy hhmm or dd/mm/yyyy hhmm, hhmm in 24-hour format
        String[] dateTimeBrokenDown = dateTime.replace("/", "-")
                .replace(" ", "-").split("-");

        boolean isInputLengthCorrect = dateTimeBrokenDown.length == 4;
        boolean isInputDayMonthYearInRange = isNumberInRange(dateTimeBrokenDown[0], 1, 2, 1, 31)
                && isNumberInRange(dateTimeBrokenDown[1], 1, 2, 1, 12)
                && isNumberInRange(dateTimeBrokenDown[2], 4, 4, 2024, 9999);
        boolean isHourMinuteInRange = isNumberInRange(dateTimeBrokenDown[3], 4, 4, 0, 2359)
                && isNumberInRange(dateTimeBrokenDown[3].substring(0, 2), 2, 2, 0, 23)
                && isNumberInRange(dateTimeBrokenDown[3].substring(2), 2, 2, 0, 59);

        if (!(isInputLengthCorrect && isInputDayMonthYearInRange && isHourMinuteInRange)) {
            throw new DateTimeException(BOT_INVALID_DATE);
        }

        return LocalDateTime.of(
                Integer.parseInt(dateTimeBrokenDown[2]),
                Integer.parseInt(dateTimeBrokenDown[1]),
                Integer.parseInt(dateTimeBrokenDown[0]),
                Integer.parseInt(dateTimeBrokenDown[3].substring(0, 2)),
                Integer.parseInt(dateTimeBrokenDown[3].substring(2))
        );
    }

    private static String[] sortIntegerStrings(String[] inputs, boolean isReversed) throws InfinityException {
        int[] integersInput = new int[inputs.length];
        String[] finalInputs = new String[inputs.length];

        try {
            for (int i = 0; i < inputs.length; i++) {
                integersInput[i] = Integer.parseInt(inputs[i]);
            }
        } catch (NumberFormatException e) {
            throw new InfinityException(BOT_INVALID_INPUT);
        }

        Arrays.sort(integersInput);

        if (isReversed) {
            for (int i = 0; i < integersInput.length / 2; ++i) {
                int temp = integersInput[i];
                integersInput[i] = integersInput[integersInput.length - i - 1];
                integersInput[integersInput.length - i - 1] = temp;
            }
        }

        for (int i = 0; i < inputs.length; i++) {
            finalInputs[i] = Integer.toString(integersInput[i]);
        }

        return finalInputs;
    }

    /**
     * Parses a user input and runs each operation if there are multiple of them.
     *
     * @param runnable The lambda function to run for each multi input.
     * @param input The user input.
     * @param command The command to trim.
     * @return The bot output of all executions.
     * @throws InfinityException If any execution has an error.
     */
    public static String parseAndRun(Runnable runnable,
                                     String input,
                                     Command.KnownCommands command) throws InfinityException {
        if (input.length() <= command.toString().length() + 1) {
            throw new InfinityException(BOT_INSUFFICIENT_ARGUMENTS);
        }

        String[] parsedInput = Parser.parseMultiOperations(input, command.toString());

        // Edge case for when user deletes last few in ascending order, causing taskSize to go below last index
        if (command == Command.KnownCommands.DELETE) {
            parsedInput = sortIntegerStrings(parsedInput, true);
        }

        StringBuilder botOutput = new StringBuilder();
        for (String s : parsedInput) {
            String eachOutput = runnable.run(s);
            botOutput.append(eachOutput);
        }
        return botOutput.toString();
    }

    /**
     * Parses a user input, removes the command, and returns an ArrayList of each item to execute command on.
     *
     * @param input The user input.
     * @param startsWith The command to trim.
     * @return ArrayList of String of each item to execute the command on.
     */
    public static String[] parseMultiOperations(String input, String startsWith) {
        String inputWithoutCommand = input.substring(startsWith.length());
        String[] result = inputWithoutCommand.trim().split(MULTI_OPERATION_DELIMITER);
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }
        return result;
    }
}
