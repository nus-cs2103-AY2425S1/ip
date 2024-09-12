package rainy.commands;

import rainy.tasks.TaskTracker;

public class DeadlineCommand extends Command {
    private String[] splitByTask;
    private String[] userInput;
    private TaskTracker taskTracker;

    public DeadlineCommand(String[] splitByTask, String[] userInput, TaskTracker taskTracker) {
        this.splitByTask = splitByTask;
        this.userInput = userInput;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() {
        if (userInput.length == 1) {
            this.ui.noDeadlineDescription();
        } else if (splitByTask.length == 1) {
            this.ui.noEndDateDeadline();
        } else if (splitByTask.length < 4) {
            this.ui.invalidDateDeadline();
        } else {
            String taskName = splitByTask[0].substring(9);
            String endDate = "" + splitByTask[3].substring(0, 4)
                    + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5)
                    + " " + splitByTask[3].substring(5, 9);
            this.taskTracker.updateListDeadline(taskName, endDate);
        }
        return this.taskTracker;
    }
}