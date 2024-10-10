package kotori.tasklist;

import kotori.parser.InvalidNumberOfArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DeadLine extends Task {
    private static final int deadlineKeyWordLength = 9;
    private static final int byKeyWordLength = 3;
    private static final int byTimePos = 1;
    private static final int descriptionPos = 0;
    private static final int expectedParts = 2;
    private static final int expectedArugment = 2;
    protected LocalDate deadLine;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public DeadLine(boolean isDone, String description, String deadLine) {
        super(isDone, description);
        this.deadLine = LocalDate.parse(deadLine.trim());
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, deadLine.format(formatter));
    }

    /**
     * Gets the storage message of the task.
     *
     * @return the storage message.
     * */

    @Override
    public String getStorageMessage() {
        return String.format("D | %s | %s | %s", getStatusIcon(), description, deadLine);
    }

    /**
     * Checks if a task is related to a time.
     *
     * @param date the date.
     * @return is related or not  .
     * */

    @Override
    public boolean isRelatedToDate(LocalDate date) {
        return date.isBefore(deadLine);
    }

    /**
     * Compare urgency with another task
     * */
    @Override
    public int compareTo(Task t) {
        if (t instanceof DeadLine) {
            if (this.deadLine.isBefore(((DeadLine) t).deadLine)) {
                return -1;
            } else if (((DeadLine) t).deadLine.isBefore(this.deadLine)) {
                return 1;
            } else {
                return 0;
            }
        } else if (t instanceof Event) {
            if (this.deadLine.isBefore(((Event) t).to)) {
                return -1;
            } else if (((Event) t).to.isBefore(this.deadLine)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }
    /**
     * Constructs a deadline object based on the String input
     * @param descriptions string input.
     * @return The deadline object.
     * @throws MissingInformationException if there is information missing.
     * */
    protected static DeadLine constructDeadLine(String descriptions) throws MissingInformationException,
            InvalidNumberOfArgumentException {
        String[] splittedInput = splitInputs(descriptions);
        return new DeadLine(false, splittedInput[descriptionPos],
                splittedInput[byTimePos].substring(byKeyWordLength));
    }
    /**
     * Split the String input
     * @param descriptions string input.
     * @return The splitted string.
     * @throws MissingInformationException if there is information missing.
     * */
    private static String[] splitInputs(String descriptions) throws MissingInformationException,
            InvalidNumberOfArgumentException {

        String[] strings = splitInputHelper(descriptions);
        if (isMissingDescription(strings)) {
            throw new MissingInformationException("description", "deadline");
        }
        if (isMissingByTime(strings)) {
            throw new MissingInformationException("by time", "deadline");
        }
        return strings;
    }
    /**
     * Check is the input Missing description.
     * @param descriptions the input.
     * @return is Missing description.
     * */
    private static boolean isMissingDescription(String[] descriptions) {
        return descriptions[0].trim().equals("");
    }
    /**
     * Check is the input Missing by time.
     * @param splitInputs the input.
     * @return is Missing by time.
     * */
    private static boolean isMissingByTime(String[] splitInputs) {
        return splitInputs.length < expectedParts || !splitInputs[byTimePos].startsWith("by ");
    }

    /**
     * Splits the input into parameters
     * @param descriptions the input.
     * @return the parameters in an array.
     * */
    private static String[] splitInputHelper(String descriptions) throws InvalidNumberOfArgumentException {
        String splitter = "/";
        String[] splitInput = descriptions.substring(deadlineKeyWordLength).split(splitter);
        if ((splitInput).length > expectedArugment) {
            throw new InvalidNumberOfArgumentException("Sorry.... There is too many input "
                    + "parameter for me to handle QAQ");
        } else {
            return splitInput;
        }
    }
}
