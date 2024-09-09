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

    Event(String inputLine) throws JanetException {
        // inside the program this will be called
        this(createEventCommand(inputLine).getDescription(),
                createEventCommand(inputLine).getSymbol(),
                createEventCommand(inputLine).getStartDateAndTime(),
                createEventCommand(inputLine).getEndDateAndTime());
    }

    Event(String description, String symbol, LocalDateTime startDate, LocalDateTime endDate) {
        // this is used inside the static method: createEventCommand
        super(description, symbol, startDate.toLocalDate());  // startDate is the scheduledDate.
        this.startDate = startDate;
        this.endDate = endDate;
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
        int indexOfFrom = 0;
        int indexOfTo = 0;
        // first word in commandDetails must be event, so start from i=1 word
        for (int i = 1; i < commandDetails.length; i++) {
            if (commandDetails[i].equals("/from")) {
                indexOfFrom = i;
            }
            if (commandDetails[i].equals("/to")) {
                indexOfTo = i;
            }
        }
        if (indexOfFrom == 0 || indexOfTo == 0) {
            throw new JanetException("WHOOPS! Missing/Wrong keywords for creating event...");
        }
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
            throw new JanetException("WHOOPS! Ensure that the start & end date are in the format: yyyy-MM-dd hh:mm (24hr)");
        }
        return new String[]{description, startDateAndTime, endDateAndTime};
    }


    /**
     * Returns an Event object that was created from the user's command.
     * Based on the description, start date and end date.
     *
     * @param inputLine User's command that was typed into the command line.
     * @return new Event object.
     */
    public static Event createEventCommand(String inputLine) throws JanetException {
        String[] commandDetails = inputLine.split(" ");
        String[] eventDetails = findEventDetails(commandDetails);
        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");    // format the date and time
        String startDateAndTimeString = eventDetails[1];
        LocalDateTime startDateAndTime = LocalDateTime.parse(startDateAndTimeString, stringToDateTime); // convert String to LocalDateTime

        String endDateAndTimeString = eventDetails[2];
        LocalDateTime endDateAndTime = LocalDateTime.parse(endDateAndTimeString, stringToDateTime);
        return new Event(eventDetails[0], "E", startDateAndTime, endDateAndTime);
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
