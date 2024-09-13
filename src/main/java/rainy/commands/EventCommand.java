package rainy.commands;

import rainy.tasks.TaskTracker;

public class EventCommand extends Command {
    private String[] splitByTask;
    private String[] userInput;
    private TaskTracker taskTracker;

    public EventCommand(String[] splitByTask, String[] userInput, TaskTracker taskTracker) {
        this.splitByTask = splitByTask;
        this.userInput = userInput;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() {
        if (userInput.length == 1) {
            this.ui.noEventDescription();
        } else if (splitByTask.length < 5) {
            this.ui.invalidEventDate();
        } else {
            String taskName = splitByTask[0].substring(6);
            String eventDate = splitByTask[3].substring(0, 4)
                    + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5);
            String eventTime = splitByTask[4];
            this.taskTracker.updateListEvent(taskName, eventDate, eventTime);
        }
        return this.taskTracker;
    }
}