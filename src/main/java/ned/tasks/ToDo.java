package ned.tasks;

import ned.exceptions.MissingTaskDescriptionException;
import ned.exceptions.NedException;

public class ToDo extends Task {

    private static final String TODO_MISSING_TASK_DESCRIPTION_ERROR_MESSAGE = "M'lord, this saved todo task has no "
            + "task description!";

    protected ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
        this.taskType = "T";
    }

    public static ToDo createToDo(String taskDescription, boolean taskStatus) throws NedException {
        if (taskDescription.isBlank()) {
            throw new MissingTaskDescriptionException(TODO_MISSING_TASK_DESCRIPTION_ERROR_MESSAGE);
        }
        return new ToDo(taskDescription, taskStatus);
    }

    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("todo|%d|%s", status, this.taskDescription);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ToDo) {
            ToDo typeCastedObj = (ToDo) obj;
            return (isTaskDescriptionEqual(typeCastedObj) && isStatusEqual(typeCastedObj));
        }
        return false;
    }

    private boolean isStatusEqual(ToDo typeCastedObj) {
        return typeCastedObj.isDone == this.isDone;
    }

    private boolean isTaskDescriptionEqual(ToDo typeCastedObj) {
        return typeCastedObj.taskDescription.equals(this.taskDescription);
    }
}
