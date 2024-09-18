package kotori.tasklist;
import java.time.LocalDate;

import kotori.parser.InvalidNumberOfArgumentException;

class Event extends Task {
    private static final int lengthOfEvent = 6;
    private static final int minimalLengthWithFrom = 2;
    private static final int minimalLengthWithTo = 3;
    private static final int descriptionPos = 0;
    private static final int fromPos = 1;
    private static final int lengthOfFrom = 5;
    private static final int toPos = 2;
    private static final int lengthOfTo = 3;
    private static final int expectedArugment = 3;
    protected LocalDate from;
    protected LocalDate to;
    /**
     * Construct a Event object.
     * */
    protected Event(boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = LocalDate.parse(from.trim());
        this.to = LocalDate.parse(to.trim());

    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", getStatusIcon(), description, from, to);
    }

    /**
     * Gets the storage message of the task.
     *
     * @return the storage message.
     * */

    @Override
    public String getStorageMessage() {
        return String.format("E | %s | %s | %s | %s", getStatusIcon(), description, from, to);
    }

    /**
     * Checks if a task is related to a time.
     *
     * @param date the date.
     * @return is related or not  .
     * */

    public boolean isRelatedToDate(LocalDate date) {
        return date.isAfter(from) && date.isBefore(to);

    }
    @Override
    public int compareTo(Task t) {
        if (t instanceof DeadLine) {
            if (this.to.isBefore(((DeadLine) t).deadLine)) {
                return -1;
            } else if (((DeadLine) t).deadLine.isBefore(this.to)) {
                return 1;
            } else {
                return 0;
            }
        } else if (t instanceof Event) {
            if (this.to.isBefore(((Event) t).to)) {
                return -1;
            } else if (((Event) t).to.isBefore(this.to)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    protected static Event constructEvent(String descriptions) throws MissingInformationException,
            InvalidNumberOfArgumentException {
        String[] splittedString = splitInputs(descriptions);
        return new Event(false, splittedString[descriptionPos],
                splittedString[fromPos].substring(lengthOfFrom),
                splittedString[toPos].substring(lengthOfTo));
    }

    /**
     * Split the String input
     * @param descriptions string input.
     * @return The splitted string.
     * @throws MissingInformationException if there is information missing.
     * */
    private static String[] splitInputs(String descriptions) throws MissingInformationException,
            InvalidNumberOfArgumentException {
        String[] splittedInput = splitInput(descriptions);
        if (isMissingDescription(splittedInput)) {
            throw new MissingInformationException("description", "event");
        }
        if (isMissingFromTime(splittedInput)) {
            throw new MissingInformationException("from time", "event");
        } else if (isMissingToTime(splittedInput)) {
            throw new MissingInformationException("to time", "event");
        }
        return splittedInput;
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
     * Check is the input Missing from time.
     * @param splittedInputs the input.
     * @return is Missing from time.
     * */
    private static boolean isMissingFromTime(String[] splittedInputs) {
        return splittedInputs.length < minimalLengthWithFrom || !splittedInputs[fromPos].startsWith("from ");
    }
    /**
     * Check is the input Missing to time.
     * @param splittedInputs the input.
     * @return is Missing to time.
     * */
    private static boolean isMissingToTime(String[] splittedInputs) {
        return splittedInputs.length < minimalLengthWithTo || !splittedInputs[toPos].startsWith("to ");
    }
    /**
     * Splits the input into parameters
     * @param descriptions the input.
     * @return the parameters in an array.
     * */
    private static String[] splitInput(String descriptions) throws InvalidNumberOfArgumentException {
        String splitter = "/";
        String[] splittedInput = descriptions.substring(lengthOfEvent).split(splitter);
        if ((splittedInput).length > expectedArugment) {
            throw new InvalidNumberOfArgumentException("Sorry.... There is too many input "
                    + "parameter for me to handle QAQ");
        } else {
            return splittedInput;
        }
    }
}
