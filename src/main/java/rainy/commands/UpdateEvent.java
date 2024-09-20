package rainy.commands;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import rainy.rainyexceptions.InvalidEventParametersException;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.tasks.TaskTracker;

/**
 * Processes the user input and attempts to update the <code>Event</code> object with new parameters.
 */
public class UpdateEvent extends UpdateCommand {
    private static final char END_OF_OUTPUT = '^';
    private static final String NAME_PARAMETER = "name ";
    private static final String DATE_PARAMETER = "date ";
    private static final String TIME_PARAMETER = "time ";
    private static final int START_INDEX = 0;
    private static final int PARAMETER_END = 5;
    private static final int YEAR_START = 11;
    private static final int YEAR_END = 15;
    private static final int MONTH_START = 8;
    private static final int MONTH_END = 10;
    private static final int DAY_START = 5;
    private static final int DAY_END = 7;
    private static final int TIMEFRAME_START = 5;
    private static final int TIMEFRAME_END = 17;
    private static final char ERROR_OUTPUT = '`';

    public UpdateEvent(int validResponse, TaskTracker taskTracker, String[] updateParameters) {
        super(validResponse, taskTracker, updateParameters);
    }

    public TaskTracker getResponse() throws InvalidEventParametersException, InvalidIndexException {
        Stream<String> updateParametersStream = Arrays.stream(updateParameters);
        try {
            this.taskTracker = this.processEventParameters(updateParametersStream);
        } catch (Exception e) {
            System.out.println(ui.invalidEventDate() + ERROR_OUTPUT);
            throw new InvalidEventParametersException(ui.invalidEventDate());
        }
        this.ui.taskHasBeenUpdated();
        return this.taskTracker;
    }

    /**
     * Updates the <code>Event</code> object with new parameters, if valid and applicable.
     * @param updateParametersStream  Represents a data set of strings containing the new parameter
     *                                to be updated. For <code>Event</code> objects, these strings
     *                                begin with either "name ", "date ", or "time " which points this method to change
     *                                the appropriate parameter within the <code>TaskTracker</code> object.
     * @return                        Returns the updated <code>TaskTracker</code> object.
     */
    public TaskTracker processEventParameters(Stream<String> updateParametersStream) {
        TaskTracker[] taskHolder = new TaskTracker[]{this.taskTracker};
        updateParametersStream.forEach(x -> {
            if (x.substring(START_INDEX, PARAMETER_END).equals(NAME_PARAMETER)) {
                taskHolder[START_INDEX].updateEventName(validResponse - 1, x.substring(PARAMETER_END) + " ");
            } else if (x.substring(START_INDEX, PARAMETER_END).equals(DATE_PARAMETER)) {
                String endDate = "" + x.substring(YEAR_START, YEAR_END)
                        + "-" + x.substring(MONTH_START, MONTH_END) + "-" + x.substring(DAY_START, DAY_END);
                taskHolder[START_INDEX].updateEventDate(validResponse - 1, endDate);
                LocalDate.parse(endDate);
            } else if (x.substring(START_INDEX, PARAMETER_END).equals(TIME_PARAMETER)) {
                String timeframe = x.substring(TIMEFRAME_START, TIMEFRAME_END);
                taskHolder[START_INDEX].updateEventTime(validResponse - 1, timeframe);
            } else {
                LocalDate.parse(x);
            }
        });
        return taskHolder[START_INDEX];
    }
}
