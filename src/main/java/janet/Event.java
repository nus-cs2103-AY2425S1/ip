package janet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents an Event, with a description, symbol, start date and end date.
 */
public class Event extends ScheduledTask {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * @param inputLine A String input from the user.
     * @throws JanetException If keywords are missing, incorrect date and time format, or start date and end date are invalid.
     */
    Event(String inputLine) throws JanetException {
        // inside the program this will be called
        this(createEventFromUser(inputLine).getDescription(),
                createEventFromUser(inputLine).getSymbol(),
                createEventFromUser(inputLine).getStartDateAndTime(),
                createEventFromUser(inputLine).getEndDateAndTime());
    }

    /**
     * @param description The description of the event.
     * @param symbol The event symbol, E.
     * @param startDate The start date of the event, in LocalDateTime.
     * @param endDate The end date of the event, in LocalDateTime.
     */
    Event(String description, String symbol, LocalDateTime startDate, LocalDateTime endDate) {
        // this is used inside the static method: createEventCommand
        super(description, symbol, startDate);  // startDate is the scheduledDate.
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @param textLine A String of text from the text file to create event.
     */
    Event(String[] textLine) {
        this(createEventFromTxt(textLine).getDescription(),
                createEventFromTxt(textLine).getSymbol(),
                createEventFromTxt(textLine).getStartDateAndTime(),
                createEventFromTxt(textLine).getEndDateAndTime());
    }


    /**
     * Returns a String array based on user's commands.
     * First element = a String of janet.Event's description.
     * Second element = a String of janet.Event's startDate (MMM dd yyyy hh:mm a).
     * Third element = a String of janet.Event's endDate (MMM dd yyyy hh:mm a).
     * input date and time format must be "yyyy-mm-dd HH:MM"
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @return a String[], where first elem = janet.Event.description, second elem = janet.Event.startDate, third elem = janet.Event.endDate.
     */
    public static String[] findEventDetails(String[] commandDetails) throws JanetException {
        int indexOfFrom = getIndexOfKeyword(commandDetails, "/from");
        int indexOfTo = getIndexOfKeyword(commandDetails, "/to");
        invalidEventCommandChecker(commandDetails, indexOfFrom, indexOfTo);

        // get the description
        String description = null;
        String startDateAndTime = null;
        String endDateAndTime = null;
        try {
            String[] descriptionArray = Arrays.copyOfRange(commandDetails, 1, indexOfFrom);
            description = String.join(" ", descriptionArray);

            // converts start date from (yyyy-MM-dd) to (MM dd yyyy)
            // converts start time from (hh:mm) to (hh:mm a)
            startDateAndTime = ScheduledTask.DateAndTimeFormatter(commandDetails[indexOfFrom + 1],
                    commandDetails[indexOfFrom + 2]);

            // converts end date from (yyyy-MM-dd) to (MM dd yyyy)
            // converts end time from (hh:mm) to (hh:mm a)
            endDateAndTime = ScheduledTask.DateAndTimeFormatter(commandDetails[indexOfTo + 1],
                    commandDetails[indexOfTo + 2]);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new JanetException("WHOOPS! Ensure that the start & end date are in the format: yyyy-MM-dd HH:mm (24hr)");
        }
        return new String[]{description, startDateAndTime, endDateAndTime};
    }

    /**
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @param indexOfFrom the index of '/from' keyword in the command.
     * @param indexOfTo the index of '/to' keyword in the command.
     * @throws JanetException if '/from' or '/to' keywords not found OR more than 1 start date and time/end date and time
     * are specified.
     */
    private static void invalidEventCommandChecker(String[] commandDetails, int indexOfFrom, int indexOfTo) throws JanetException {
        if (indexOfFrom == 0 || indexOfTo == 0) {
            // '/from' or '/to' not found in the command
            throw new JanetException("WHOOPS! Missing/Wrong keywords for creating event...");
        }
        if (indexOfFrom > indexOfTo) {
            // '/from' comes after '/to' in the command
            throw new JanetException("WHOOPS! Ensure event command is in correct order!");
        }
        if (indexOfTo > indexOfFrom
                && (hasMoreThanOneEndDateAndTime(commandDetails, indexOfTo)
                    || hasMoreThanOneStartDateAndTime(commandDetails, indexOfFrom))) {
            // when there are additional texts beyond the due date and time specified (eg. ... /by 2024-01-01 18:00 blah blah)
            throw new JanetException("WHOOPS! Ensure you only have a single start/end date and start/end time provided!");
        }
    }

    /**
     * Returns true if additional texts are specified between the (start date and time) and (end date and time),
     * false otherwise.
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @param indexOfFrom the index of '/from' keyword in commandDetails.
     * @return A boolean value.
     */
    private static boolean hasMoreThanOneStartDateAndTime(String[] commandDetails, int indexOfFrom) {
        return commandDetails.length > (indexOfFrom + 5) + 1;
    }

    /**
     * Returns true if additional texts are specified after the end date and time,
     * false otherwise.
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @param indexOfTo the index of '/by' keyword in commandDetails.
     * @return A boolean value.
     */
    private static boolean hasMoreThanOneEndDateAndTime(String[] commandDetails, int indexOfTo) {
        return commandDetails.length > (indexOfTo + 2) + 1;
    }


    /**
     * Returns an Event object that was created from the user's command.
     * Based on the description, start date and end date.
     *
     * @param inputLine User's command that was typed into the command line.
     * @return new Event object.
     */
    public static Event createEventFromUser(String inputLine) throws JanetException {
        String[] commandDetails = inputLine.split(" ");
        String[] eventDetails = findEventDetails(commandDetails);
        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");    // format the date and time
        String startDateAndTimeString = eventDetails[1];
        LocalDateTime startDateAndTime = LocalDateTime.parse(startDateAndTimeString, stringToDateTime); // convert String to LocalDateTime

        String endDateAndTimeString = eventDetails[2];
        LocalDateTime endDateAndTime = LocalDateTime.parse(endDateAndTimeString, stringToDateTime);

        if (startDateAndTime.isAfter(endDateAndTime) || startDateAndTime.isEqual(endDateAndTime)) {
            throw new JanetException("WHOOPS! Your event's start date cannot be later than/equal to the end date!");
        }
        return new Event(eventDetails[0], "E", startDateAndTime, endDateAndTime);
    }

    /**
     * Returns an Event object that was created from the line of text, in .txt file saved.
     *
     * @param textLine A line of text from the .txt file saved.
     * @return An Event object.
     */
    public static Event createEventFromTxt(String[] textLine) {
        String symbol = textLine[0].trim();
        String description = textLine[2].trim();

        String[] dates = textLine[3].trim().split("-");
        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");
        String startDateString = dates[0].trim();
        LocalDateTime startDate = LocalDateTime.parse(startDateString, stringToDateTime);
        String endDateString = dates[1].trim();
        LocalDateTime endDate = LocalDateTime.parse(endDateString, stringToDateTime);

        return new Event(description, symbol, startDate, endDate);
    }


    /**
     * @return task's startDate
     */
    public String getStartDate() {
        String time = this.startDate.format(DateTimeFormatter.ofPattern("HH:mm a"));
        String date = this.startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return date + " " + time;
    }

    /**
     * @return task's endDate
     */
    public String getEndDate() {
        String time = this.endDate.format(DateTimeFormatter.ofPattern("HH:mm a"));
        String date = this.endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return date + " " + time;
    }

    public LocalDateTime getStartDateAndTime() {
        return this.startDate;
    }

    public LocalDateTime getEndDateAndTime() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("(from: %s to: %s)", this.getStartDate(), this.getEndDate());
    }
}
