package rainy.commands;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

import rainy.rainyexceptions.InvalidDeadlineParametersException;
import rainy.tasks.TaskTracker;

public class UpdateDeadline extends UpdateCommand {

    public UpdateDeadline(int validResponse, TaskTracker taskTracker, String[] updateParameters) {
        super(validResponse, taskTracker, updateParameters);
    }

    public TaskTracker getResponse() throws InvalidDeadlineParametersException {
        Stream<String> updateParametersStream = Arrays.stream(updateParameters);
        try {
            this.taskTracker = this.processDeadlineParameters(updateParametersStream);
        } catch (Exception e) {
            System.out.println(ui.invalidDateDeadline() + '^');
            throw new InvalidDeadlineParametersException(ui.invalidDateDeadline());
        }
        this.ui.taskHasBeenUpdated();
        return this.taskTracker;
    }

    public TaskTracker processDeadlineParameters(Stream<String> updateParametersStream) {
        TaskTracker[] taskHolder = new TaskTracker[]{this.taskTracker};
        updateParametersStream.forEach(x -> {
            if (x.substring(0, 5).equals("name ")) {
                taskHolder[0].updateDeadlineName(validResponse - 1, x.substring(5) + " ");
            } else if (x.substring(0, 5).equals("date ")) {
                String endDate = "" + x.substring(11, 15)
                        + "-" + x.substring(8, 10) + "-" + x.substring(5, 7)
                        + " " + x.substring(16, 20);
                LocalDate.parse(endDate.substring(0, 10));
                taskHolder[0].updateDeadlineDate(validResponse - 1, endDate);
            } else {
                this.ui.invalidDeadlineParameter();
            }
        });
        return taskHolder[0];
    }
}