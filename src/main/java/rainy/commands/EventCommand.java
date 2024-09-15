package rainy.commands;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.rainyexceptions.InvalidEventParametersException;
import rainy.tasks.TaskTracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command {
    private String[] splitByTask;
    private String[] userInput;
    private TaskTracker taskTracker;
    private static String DATE_FORMAT = "MMM d yyyy";
    private static String TIME_FORMAT = "HH:mm";
    private static char END_OF_OUTPUT = '^';
    private static int NO_EVENT = 1;
    private static int INVALID_EVENT = 5;
    private static int START_INDEX = 0;
    private static int START_TASK = 6;
    private static int YEAR_INDEX = 3;
    private static int MONTH_INDEX = 2;
    private static int DAY_INDEX = 1;
    private static int YEAR_END = 4;
    private static int DAY_START = 3;
    private static int DAY_END = 5;
    private static int TIME_INDEX = 4;
    private static int FIRST_HOUR_END = 2;
    private static int FIRST_MINUTE_END = 4;
    private static int SECOND_HOUR_START = 8;
    private static int SECOND_HOUR_END = 10;
    private static int SECOND_MINUTE_END = 12;

    public EventCommand(String[] splitByTask, String[] userInput, TaskTracker taskTracker) {
        this.splitByTask = splitByTask;
        this.userInput = userInput;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidEventParametersException {
        if (userInput.length == NO_EVENT) {
            this.ui.noEventDescription();
        } else if (splitByTask.length < INVALID_EVENT) {
            this.ui.invalidEventDate();
        } else {
            this.taskTracker = this.processEventParameters();
        }
        return this.taskTracker;
    }

    public TaskTracker processEventParameters() throws InvalidEventParametersException {
        try {
            String taskName = splitByTask[START_INDEX].substring(START_TASK);
            String eventDate = splitByTask[YEAR_INDEX].substring(START_INDEX, YEAR_END)
                    + "-" + splitByTask[MONTH_INDEX] + "-" + splitByTask[DAY_INDEX].substring(DAY_START, DAY_END);
            String eventTime = splitByTask[TIME_INDEX];
            String testDate = LocalDate.parse(eventDate)
                    .format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            String firstTime = LocalTime.parse(eventTime.substring(START_INDEX, FIRST_HOUR_END) + ":"
                    + eventTime.substring(FIRST_HOUR_END, FIRST_MINUTE_END)).format(DateTimeFormatter.ofPattern(TIME_FORMAT));
            String secondTime = LocalTime.parse(eventTime.substring(SECOND_HOUR_START, SECOND_HOUR_END) + ":"
                    + eventTime.substring(SECOND_HOUR_END, SECOND_MINUTE_END)).format(DateTimeFormatter.ofPattern(TIME_FORMAT));
            this.taskTracker.addListEvent(taskName, eventDate, eventTime);
        } catch (Exception e) {
            System.out.println(ui.invalidEventDate() + END_OF_OUTPUT);
            throw new InvalidEventParametersException(ui.invalidEventDate());
        }
        return this.taskTracker;
    }
}