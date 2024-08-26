public class ToDo extends Task {

    protected ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
        this.taskType = "T";
    }

    public static Task createTask(String input) throws NedException {
        String[] parsed_inputs = input.split("todo", 2);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a todo task with no description");
        }
        Task newTask = new ToDo(parsed_inputs[1].strip(), false);
        return newTask;
    }

    public static ToDo createToDo(boolean taskStatus, String taskDescription) throws NedException {
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
