package rainy.commands;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.rainyexceptions.InvalidEventParametersException;
import rainy.tasks.TaskTracker;

public class UpdateEvent extends UpdateCommand {

    public UpdateEvent(int validResponse, TaskTracker taskTracker, String[] updateParameters) {
        super(validResponse, taskTracker, updateParameters);
    }

    public TaskTracker getResponse() throws InvalidEventParametersException {
        Stream<String> updateParametersStream = Arrays.stream(updateParameters);
        try {
            this.taskTracker = this.processEventParameters(updateParametersStream);
        } catch (Exception e) {
            System.out.println(ui.invalidEventDate() + '^');
            throw new InvalidEventParametersException(ui.invalidEventDate());
        }
        this.ui.taskHasBeenUpdated();
        return this.taskTracker;
    }

    public TaskTracker processEventParameters(Stream<String> updateParametersStream) {
        TaskTracker[] taskHolder = new TaskTracker[]{this.taskTracker};
        updateParametersStream.forEach(x -> {
            if (x.substring(0, 5).equals("name ")) {
                taskHolder[0].updateEventName(validResponse - 1, x.substring(5) + " ");
            } else if (x.substring(0, 5).equals("date ")) {
                String endDate = "" + x.substring(11, 15)
                        + "-" + x.substring(8, 10) + "-" + x.substring(5, 7);
                taskHolder[0].updateEventDate(validResponse - 1, endDate);
                LocalDate.parse(endDate);
            } else if (x.substring(0, 5).equals("time ")) {
                String timeframe = x.substring(5, 17);
                taskHolder[0].updateEventTime(validResponse - 1, timeframe);
            }
            else {
                this.ui.invalidEventParameter();
            }
        });
        return taskHolder[0];
    }
}