public class Deadline extends Task {
    public String deadlineTiming;

    protected Deadline(String taskDescription, String deadlineTiming, boolean isDone) {
        super(taskDescription, isDone);
        this.deadlineTiming = deadlineTiming;
        this.taskType = "D";
    }

    @Override
    public String toString() {
        return super.toString() + " " + String.format("(by: %s)", this.deadlineTiming);
    }

    public static Task createTask(String input) throws NedException {
        String[] parsed_inputs = input.split("deadline|/by", 3);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create a deadline task with no description");
        } else if (parsed_inputs_len == 1) {
            throw new NedException("M'lord, you cannot create a deadline task with no due date");
        }
        ;
        Task newTask = new Deadline(parsed_inputs[1].strip(), parsed_inputs[2].strip(), false);
        return newTask;
    };

    public static Deadline createDeadline(boolean taskStatus, String taskDescription, String deadlineTiming) throws NedException {
        if (taskDescription.isBlank()) {
            throw new NedException("M'lord, this saved deadline task has no task description!");
        } else if (deadlineTiming.isBlank()) {
            throw new NedException("M'lord, this saved deadline task has no due date!");
        };
        return new Deadline(taskDescription, deadlineTiming, taskStatus);
    }

    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("deadline|%d|%s", status, this.taskDescription);
    }
}