import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Parser {



    /**
     * Returns a String array based on user's commands.
     * First element = a String of Deadline's description.
     * Second element = a String of Deadline's dueDate (MMM dd yyyy hh:mm a).
     * input date and time format must be "yyyy-mm-dd HH:MM"
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @return a String[], where first elem = Deadline.description, second elem = Deadline.dueDate.
     */
    public static String[] findDeadlineDetails(String[] commandDetails) {
        int indexOfBy = 0;
        // first word in commandDetails must be deadline, so start from the i=1 word
        for (int i = 1; i < commandDetails.length; i++) {
            if (commandDetails[i].equals("/by")) {
                indexOfBy = i;
            }
        }
        // get description of Deadline
        String[] descriptionArray = Arrays.copyOfRange(commandDetails, 1, indexOfBy);
        String description = String.join(" ", descriptionArray);
        // get due date
        String inputDate  = commandDetails[commandDetails.length - 2];    // yyyy-mm-dd
        LocalDate date = LocalDate.parse(inputDate);
        String outputDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        // get time
        String inputTime = commandDetails[commandDetails.length - 1];
        LocalTime time = LocalTime.parse(inputTime);
        String outputTime = time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String dueDate = outputDate + " " + outputTime;
        return new String[]{description, dueDate};
    }


    public static Task createDeadlineCommand(String inputLine) {
        String[] commandDetails = inputLine.split(" ");
        String[] deadlineDetails = findDeadlineDetails(commandDetails);
        String dateAndTimeString = deadlineDetails[1];    // MMM dd yyyy hh:mm a
        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        LocalDateTime dateAndTime = LocalDateTime.parse(dateAndTimeString, stringToDateTime);
        return new Deadline(deadlineDetails[0], "D", dateAndTime);
    }


    /**
     * Returns a String array based on user's commands.
     * First element = a String of Event's description.
     * Second element = a String of Event's startDate (MMM dd yyyy hh:mm a).
     * Third element = a String of Event's endDate (MMM dd yyyy hh:mm a).
     * input date and time format must be "yyyy-mm-dd HH:MM"
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @return a String[], where first elem = Event.description, second elem = Event.startDate, third elem = Event.endDate.
     */
    public static String[] findEventDetails(String[] commandDetails) {
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
        // get the description
        String[] descriptionArray = Arrays.copyOfRange(commandDetails, 1, indexOfFrom);
        String description = String.join(" ", descriptionArray);

        // get the start date (yyyy-mm-dd)
        LocalDate startDate = LocalDate.parse(commandDetails[indexOfFrom + 1]);
        String outputStartDate = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        // get the start time (hh:mm, 24hr format)
        LocalTime inputStartTime = LocalTime.parse(commandDetails[indexOfFrom + 2]);
        String outputStartTime = inputStartTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String startDateAndTime = outputStartDate + " " + outputStartTime;

        // get the end date (yyyy-mm-dd)
        LocalDate endDate = LocalDate.parse(commandDetails[indexOfTo + 1]);
        String outputEndDate = endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        // get the end time
        LocalTime inputEndTime = LocalTime.parse(commandDetails[indexOfTo + 2]);
        String outputEndTime = inputEndTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        String endDateAndTime = outputEndDate + " " + outputEndTime;
        return new String[]{description, startDateAndTime, endDateAndTime};
    }


    public static Task createEventCommand(String inputLine) {
        String[] commandDetails = inputLine.split(" ");
        String[] eventDetails = findEventDetails(commandDetails);
        DateTimeFormatter stringToDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        String startDateAndTimeString = eventDetails[1];
        LocalDateTime startDateAndTime = LocalDateTime.parse(startDateAndTimeString, stringToDateTime);

        String endDateAndTimeString = eventDetails[2];
        LocalDateTime endDateAndTime = LocalDateTime.parse(endDateAndTimeString, stringToDateTime);
        return new Event(eventDetails[0], "E", startDateAndTime, endDateAndTime);
    }

}
