package rainy.commands;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.tasks.TaskTracker;

public class UpdateDeadline extends UpdateCommand {
    private static char END_OF_OUTPUT = '^';
    private static String NAME_PARAMETER = "name ";
    private static String DATE_PARAMETER = "date ";
    private static int START_INDEX = 0;
    private static int PARAMETER_END = 5;
    private static int YEAR_START = 11;
    private static int YEAR_END = 15;
    private static int MONTH_START = 8;
    private static int MONTH_END = 10;
    private static int DAY_START = 5;
    private static int DAY_END = 7;
    private static int TIME_START = 16;
    private static int TIME_END = 20;
    private static int END_DATE_INDEX = 10;


    public UpdateDeadline(int validResponse, TaskTracker taskTracker, String[] updateParameters) {
        super(validResponse, taskTracker, updateParameters);
    }

    public TaskTracker getResponse() throws InvalidDeadlineParametersException {
        Stream<String> updateParametersStream = Arrays.stream(updateParameters);
        try {
            this.taskTracker = this.processDeadlineParameters(updateParametersStream);
        } catch (Exception e) {
            System.out.println(ui.invalidDateDeadline() + END_OF_OUTPUT);
            throw new InvalidDeadlineParametersException(ui.invalidDateDeadline());
        }
        this.ui.taskHasBeenUpdated();
        return this.taskTracker;
    }

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
                this.ui.invalidDeadlineParameter();
            }
        });
        return taskHolder[START_INDEX];
    }
}