package rainy.commands;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.rainyexceptions.InvalidEventParametersException;
import rainy.tasks.TaskTracker;

public class UpdateEvent extends UpdateCommand {
    private static char END_OF_OUTPUT = '^';
    private static String NAME_PARAMETER = "name ";
    private static String DATE_PARAMETER = "date ";
    private static String TIME_PARAMETER = "time ";
    private static int START_INDEX = 0;
    private static int PARAMETER_END = 5;
    private static int YEAR_START = 11;
    private static int YEAR_END = 15;
    private static int MONTH_START = 8;
    private static int MONTH_END = 10;
    private static int DAY_START = 5;
    private static int DAY_END = 7;
    private static int TIMEFRAME_START = 5;
    private static int TIMEFRAME_END = 17;


    public UpdateEvent(int validResponse, TaskTracker taskTracker, String[] updateParameters) {
        super(validResponse, taskTracker, updateParameters);
    }

    public TaskTracker getResponse() throws InvalidEventParametersException {
        Stream<String> updateParametersStream = Arrays.stream(updateParameters);
        try {
            this.taskTracker = this.processEventParameters(updateParametersStream);
        } catch (Exception e) {
            System.out.println(ui.invalidEventDate() + END_OF_OUTPUT);
            throw new InvalidEventParametersException(ui.invalidEventDate());
        }
        this.ui.taskHasBeenUpdated();
        return this.taskTracker;
    }

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
            }
            else {
                this.ui.invalidEventParameter();
            }
        });
        return taskHolder[START_INDEX];
    }
}