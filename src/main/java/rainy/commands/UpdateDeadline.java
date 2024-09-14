package rainy.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import rainy.tasks.TaskTracker;

public class UpdateDeadline extends UpdateCommand {
    private String[] updateParameters;

    public UpdateDeadline(int validResponse, TaskTracker taskTracker, String[] updateParameters) {
        super(validResponse, taskTracker);
        this.updateParameters = updateParameters;
    }

    public TaskTracker getResponse() {
        this.updateParameters.stream().forEach(x -> {
            if (x.substring(0, 5).equals("name ")) {
                this.taskTracker.updateDeadlineName(validResponse - 1, x.substring(5));
            } else if (x.substring(0, 5).equals("date")) {
                String endDate = "" + x.substring(11, 15)
                        + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5)
                        + " " + splitByTask[3].substring(5, 9);
            }
        });
    }

}