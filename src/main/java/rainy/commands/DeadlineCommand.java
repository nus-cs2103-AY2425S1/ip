package rainy.commands;

import java.time.LocalDate;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.tasks.TaskTracker;

/**
 * Represents an instruction which processes and outputs an appropriate response based on user input.
 */
public class DeadlineCommand extends Command {
    private static final char ERROR_OUTPUT = '`';
    private static final int NO_DESCRIPTION = 1;
    private static final int INVALID_DEADLINE = 4;
    private static final int START_INDEX = 0;
    private static final int START_DESC = 9;
    private static final int YEAR_TIME_INDEX = 3;
    private static final int MONTH_INDEX = 2;
    private static final int DAY_INDEX = 1;
    private static final int YEAR_END = 4;
    private static final int DAY_START = 3;
    private static final int DAY_END = 5;
    private static final int TIME_END = 9;
    private String[] splitByTask;
    private String[] userInput;
    private TaskTracker taskTracker;

    /**
     * Constructs a new <code>DeadlineCommand</code> object.
     * @param splitByTask  Houses a list of strings which is split by the character '/' from original
     *                     user input.
     * @param userInput    Houses a list of strings which is split by the character ' ' from original
     *      *                     user input.
     * @param taskTracker  <code>TaskTracker</code> object to be updated.
     */
    public DeadlineCommand(String[] splitByTask, String[] userInput, TaskTracker taskTracker) {
        this.splitByTask = splitByTask;
        this.userInput = userInput;
        this.taskTracker = taskTracker;
    }

    /**
     * Outputs an appropriate respond based on user input, such as whether there a description was provided
     * for the deadline, a valid date was provided, or whether any date was provided.
     * @return                                     This method returns an updated <code>TaskTracke</code> object.
     * @throws InvalidDeadlineParametersException  Thrown when an invalid deadline is input by user.
     */
    public TaskTracker getResponse() throws InvalidDeadlineParametersException {
        if (userInput.length == NO_DESCRIPTION) {
            System.out.println(this.ui.noDeadlineDescription() + ERROR_OUTPUT);
            throw new InvalidDeadlineParametersException(this.ui.noDeadlineDescription());
        } else if (splitByTask.length == NO_DESCRIPTION) {
            System.out.println(this.ui.noEndDateDeadline() + ERROR_OUTPUT);
            throw new InvalidDeadlineParametersException(this.ui.noEndDateDeadline());
        } else if (splitByTask.length < INVALID_DEADLINE) {
            System.out.println(this.ui.invalidDateDeadline() + ERROR_OUTPUT);
            throw new InvalidDeadlineParametersException(this.ui.invalidDateDeadline());
        } else {
            this.taskTracker = this.processDeadlineParameters();
        }
        return this.taskTracker;
    }

    /**
     * Processes the deadline in two different ways: firstly through user input, and secondly by reading data
     * from a text file.
     * @return                                     Returns an updated <code>TaskTracker</code>.
     * @throws InvalidDeadlineParametersException  Thrown when an invalid deadline is input by user.
     */
    public TaskTracker processDeadlineParameters() throws InvalidDeadlineParametersException {
        try {
            String taskName = splitByTask[START_INDEX].substring(START_DESC);
            String endDate = "" + splitByTask[YEAR_TIME_INDEX].substring(START_INDEX, YEAR_END)
                    + "-" + splitByTask[MONTH_INDEX] + "-" + splitByTask[DAY_INDEX].substring(DAY_START, DAY_END)
                    + " " + splitByTask[YEAR_TIME_INDEX].substring(DAY_END, TIME_END);
            LocalDate.parse(endDate.substring(START_INDEX, TIME_END + 1));
            this.taskTracker.addListDeadline(taskName, endDate);
        } catch (Exception e) {
            System.out.println(ui.invalidDateDeadline() + ERROR_OUTPUT);
            throw new InvalidDeadlineParametersException(ui.invalidDateDeadline());
        }
        return this.taskTracker;
    }
}
