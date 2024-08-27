package Tasks;

import Exceptions.NedException;

public class ToDo extends Task {

    private ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
        this.taskType = "T";
    }

    public static ToDo createToDo(String taskDescription, boolean taskStatus) throws NedException {
        if (taskDescription.isBlank()) {
            throw new NedException("M'lord, this saved todo task has no task description!");
        }
        return new ToDo(taskDescription, taskStatus);
    }

    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("todo|%d|%s", status, this.taskDescription);
    }
}
