package ned.tasks;

import ned.exceptions.MissingTaskDescriptionException;
import ned.exceptions.NedException;

public class ToDo extends Task {

    protected ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
        this.taskType = "T";
    }

    public static ToDo createToDo(String taskDescription, boolean taskStatus) throws NedException {
        if (taskDescription.isBlank()) {
            throw new MissingTaskDescriptionException("M'lord, this saved todo task has no task description!");
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
        } else if (!(obj instanceof ToDo)) {
            return false;
        }
        ToDo typeCastedObj = (ToDo) obj;
        return typeCastedObj.taskDescription.equalsIgnoreCase(this.taskDescription);
    }
}
