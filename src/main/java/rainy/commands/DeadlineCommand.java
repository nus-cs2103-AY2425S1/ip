package rainy.commands;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.tasks.TaskTracker;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String[] splitByTask;
    private String[] userInput;
    private TaskTracker taskTracker;
    private static char END_OF_OUTPUT = '^';
    private static int NO_DESCRIPTION = 1;
    private static int INVALID_DEADLINE = 4;
    private static int START_INDEX = 0;
    private static int START_DESC = 9;
    private static int YEAR_TIME_INDEX = 3;
    private static int MONTH_INDEX = 2;
    private static int DAY_INDEX = 1;
    private static int YEAR_END = 4;
    private static int DAY_START = 3;
    private static int DAY_END = 5;
    private static int TIME_END = 9;

    public DeadlineCommand(String[] splitByTask, String[] userInput, TaskTracker taskTracker) {
        this.splitByTask = splitByTask;
        this.userInput = userInput;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidDeadlineParametersException {
        if (userInput.length == NO_DESCRIPTION) {
            this.ui.noDeadlineDescription();
        } else if (splitByTask.length == NO_DESCRIPTION) {
            this.ui.noEndDateDeadline();
        } else if (splitByTask.length < INVALID_DEADLINE) {
            System.out.println(this.ui.invalidDateDeadline());
        } else {
            this.taskTracker = this.processDeadlineParameters();
        }
        return this.taskTracker;
    }

    public TaskTracker processDeadlineParameters() throws InvalidDeadlineParametersException {
        try {
            String taskName = splitByTask[START_INDEX].substring(START_DESC);
            String endDate = "" + splitByTask[YEAR_TIME_INDEX].substring(START_INDEX, YEAR_END)
                    + "-" + splitByTask[MONTH_INDEX] + "-" + splitByTask[DAY_INDEX].substring(DAY_START, DAY_END)
                    + " " + splitByTask[YEAR_TIME_INDEX].substring(DAY_END, TIME_END);
            LocalDate.parse(endDate.substring(START_INDEX, TIME_END + 1));
            this.taskTracker.addListDeadline(taskName, endDate);
        } catch (Exception e) {
            System.out.println(ui.invalidDateDeadline() + END_OF_OUTPUT);
            throw new InvalidDeadlineParametersException(ui.invalidDateDeadline());
        }
        return this.taskTracker;
    }
}