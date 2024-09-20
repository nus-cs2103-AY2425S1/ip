package rainy.commands;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.tasks.TaskTracker;

/**
 * Processes the user input and attempts to update the <code>Deadline</code> object with new parameters.
 */
public class UpdateDeadline extends UpdateCommand {
    private static final int PARAMETER_END = 5;
    private static final int YEAR_START = 11;
    private static final int YEAR_END = 15;
    private static final int MONTH_START = 8;
    private static final int MONTH_END = 10;
    private static final int DAY_START = 5;
    private static final int DAY_END = 7;
    private static final int TIME_START = 16;
    private static final int TIME_END = 20;
    private static final int END_DATE_INDEX = 10;
    private static final char ERROR_OUTPUT = '`';
    private static final String NAME_PARAMETER = "name ";
    private static final String DATE_PARAMETER = "date ";
    private static final int START_INDEX = 0;

    public UpdateDeadline(int validResponse, TaskTracker taskTracker, String[] updateParameters) {
        super(validResponse, taskTracker, updateParameters);
    }

    public TaskTracker getResponse() throws InvalidDeadlineParametersException, InvalidIndexException {
        Stream<String> updateParametersStream = Arrays.stream(updateParameters);
        try {
            this.taskTracker = this.processDeadlineParameters(updateParametersStream);
        } catch (Exception e) {
            System.out.println(ui.invalidDateDeadline() + ERROR_OUTPUT);
            throw new InvalidDeadlineParametersException(ui.invalidDateDeadline());
        }
        this.ui.taskHasBeenUpdated();
        return this.taskTracker;
    }

    /**
     * Updates the <code>Deadline</code> object with new parameters, if valid and applicable.
     * @param updateParametersStream  Represents a data set of strings containing the new parameter
     *                                to be updated. For <code>Deadline</code> objects, these strings
     *                                begin with either "name " or "date " which points this method to change
     *                                the appropriate parameter within the <code>TaskTracker</code> object.
     * @return                        Returns the updated <code>TaskTracker</code> object.
     */
    public TaskTracker processDeadlineParameters(Stream<String> updateParametersStream) {
        TaskTracker[] taskHolder = new TaskTracker[]{this.taskTracker};
        updateParametersStream.forEach(x -> {
            if (x.substring(START_INDEX, PARAMETER_END).equals(NAME_PARAMETER)) {
                taskHolder[START_INDEX].updateDeadlineName(validResponse - 1, x.substring(PARAMETER_END) + " ");
            } else if (x.substring(START_INDEX, PARAMETER_END).equals(DATE_PARAMETER)) {
                String endDate = "" + x.substring(YEAR_START, YEAR_END)
                        + "-" + x.substring(MONTH_START, MONTH_END) + "-" + x.substring(DAY_START, DAY_END)
                        + " " + x.substring(TIME_START, TIME_END);
                LocalDate.parse(endDate.substring(START_INDEX, END_DATE_INDEX));
                taskHolder[START_INDEX].updateDeadlineDate(validResponse - 1, endDate);
            } else {
                LocalDate.parse(x);
            }
        });
        return taskHolder[START_INDEX];
    }
}
