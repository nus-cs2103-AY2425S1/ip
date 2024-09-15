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

    public EventCommand(String[] splitByTask, String[] userInput, TaskTracker taskTracker) {
        this.splitByTask = splitByTask;
        this.userInput = userInput;
        this.taskTracker = taskTracker;
    }

    public TaskTracker getResponse() throws InvalidEventParametersException {
        if (userInput.length == 1) {
            this.ui.noEventDescription();
        } else if (splitByTask.length < 5) {
            this.ui.invalidEventDate();
        } else {
            this.taskTracker = this.processEventParameters();
        }
        return this.taskTracker;
    }

    public TaskTracker processEventParameters() throws InvalidEventParametersException {
        try {
            String taskName = splitByTask[0].substring(6);
            String eventDate = splitByTask[3].substring(0, 4)
                    + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5);
            String eventTime = splitByTask[4];
            String testDate = LocalDate.parse(eventDate)
                    .format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            String firstTime = LocalTime.parse(eventTime.substring(0, 2) + ":"
                    + eventTime.substring(2, 4)).format(DateTimeFormatter.ofPattern("HH:mm"));
            String secondTime = LocalTime.parse(eventTime.substring(8, 10) + ":"
                    + eventTime.substring(10, 12)).format(DateTimeFormatter.ofPattern("HH:mm"));
            this.taskTracker.addListEvent(taskName, eventDate, eventTime);
        } catch (Exception e) {
            System.out.println(ui.invalidEventDate() + '^');
            throw new InvalidEventParametersException(ui.invalidEventDate());
        }
        return this.taskTracker;
    }
}