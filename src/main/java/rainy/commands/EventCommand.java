package rainy.commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import rainy.rainyexceptions.InvalidEventParametersException;
import rainy.tasks.TaskTracker;

/**
 * Represents an instruction which processes and outputs an appropriate response based on user input to add event.
 */
public class EventCommand extends Command {
    private static final String DATE_FORMAT = "MMM d yyyy";
    private static final String TIME_FORMAT = "HH:mm";
    private static final char END_OF_OUTPUT = '^';
    private static final int NO_EVENT = 1;
    private static final int INVALID_EVENT = 5;
    private static final int START_INDEX = 0;
    private static final int START_TASK = 6;
    private static final int YEAR_INDEX = 3;
    private static final int MONTH_INDEX = 2;
    private static final int DAY_INDEX = 1;
    private static final int YEAR_END = 4;
    private static final int DAY_START = 3;
    private static final int DAY_END = 5;
    private static final int TIME_INDEX = 4;
    private static final int FIRST_HOUR_END = 2;
    private static final int FIRST_MINUTE_END = 4;
    private static final int SECOND_HOUR_START = 8;
    private static final int SECOND_HOUR_END = 10;
    private static final int SECOND_MINUTE_END = 12;
    private static final char ERROR_CHARACTER = '`';
    private String[] splitByTask;
    private String[] userInput;
    private TaskTracker taskTracker;

    /**
     * Constructs a new EventCommand object.
     * @param splitByTask  Contains a list of string split from the original input by delimiter '/'.
     * @param userInput    Contains a list of string split from the original input by delimiter ' '.
     * @param taskTracker  <code>TaskTracker</code> object to be updated.
     */
    public EventCommand(String[] splitByTask, String[] userInput, TaskTracker taskTracker) {
        this.splitByTask = splitByTask;
        this.userInput = userInput;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidEventParametersException {
        if (userInput.length == NO_EVENT) {
            System.out.println(this.ui.noEventDescription() + ERROR_CHARACTER);
            throw new InvalidEventParametersException(this.ui.noEventDescription());
        } else if (splitByTask.length < INVALID_EVENT) {
            System.out.println(this.ui.invalidEventDate() + ERROR_CHARACTER);
            throw new InvalidEventParametersException(this.ui.invalidEventDate());
        } else {
            this.taskTracker = this.processEventParameters();
        }
        return this.taskTracker;
    }

    /**
     * Processes the user input and produces the date and timeframe of the event in the appropriate
     * format for the creation of a new <code>Event</code> object.
     * @return                                  Returns a <code>TaskTracker</code> object with the newly added
     *                                          event.
     * @throws InvalidEventParametersException  Thrown when invalid parameters are detected.
     */
    public TaskTracker processEventParameters() throws InvalidEventParametersException {
        try {
            String taskName = splitByTask[START_INDEX].substring(START_TASK);
            String eventDate = splitByTask[YEAR_INDEX].substring(START_INDEX, YEAR_END)
                    + "-" + splitByTask[MONTH_INDEX] + "-" + splitByTask[DAY_INDEX].substring(DAY_START, DAY_END);
            String eventTime = splitByTask[TIME_INDEX];
            String testDate = LocalDate.parse(eventDate)
                    .format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            String firstTime = LocalTime.parse(eventTime.substring(START_INDEX, FIRST_HOUR_END) + ":"
                    + eventTime.substring(FIRST_HOUR_END, FIRST_MINUTE_END))
                        .format(DateTimeFormatter.ofPattern(TIME_FORMAT));
            String secondTime = LocalTime.parse(eventTime.substring(SECOND_HOUR_START, SECOND_HOUR_END) + ":"
                    + eventTime.substring(SECOND_HOUR_END, SECOND_MINUTE_END))
                        .format(DateTimeFormatter.ofPattern(TIME_FORMAT));
            this.taskTracker.addListEvent(taskName, eventDate, eventTime);
        } catch (Exception e) {
            System.out.println(ui.invalidEventDate() + ERROR_CHARACTER);
            throw new InvalidEventParametersException(ui.invalidEventDate());
        }
        return this.taskTracker;
    }
}
