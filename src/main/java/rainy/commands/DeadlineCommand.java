package rainy.commands;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.tasks.TaskTracker;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String[] splitByTask;
    private String[] userInput;
    private TaskTracker taskTracker;

    public DeadlineCommand(String[] splitByTask, String[] userInput, TaskTracker taskTracker) {
        this.splitByTask = splitByTask;
        this.userInput = userInput;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidDeadlineParametersException {
        if (userInput.length == 1) {
            this.ui.noDeadlineDescription();
        } else if (splitByTask.length == 1) {
            this.ui.noEndDateDeadline();
        } else if (splitByTask.length < 4) {
            System.out.println(this.ui.invalidDateDeadline());
        } else {
            this.taskTracker = this.processDeadlineParameters();
        }
        return this.taskTracker;
    }

    public TaskTracker processDeadlineParameters() throws InvalidDeadlineParametersException {
        try {
            String taskName = splitByTask[0].substring(9);
            String endDate = "" + splitByTask[3].substring(0, 4)
                    + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5)
                    + " " + splitByTask[3].substring(5, 9);
            LocalDate.parse(endDate.substring(0, 10));
            this.taskTracker.addListDeadline(taskName, endDate);
        } catch (Exception e) {
            System.out.println(ui.invalidDateDeadline() + '^');
            throw new InvalidDeadlineParametersException(ui.invalidDateDeadline());
        }
        return this.taskTracker;
    }
}