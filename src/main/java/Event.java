import java.util.regex.Pattern;

public class Event extends Task {
    private String fromTiming;
    private String toTiming;

    private static String EventRegexWithoutTo = "^event (.+) /from (.+)";
    private static String EventRegexWithEmptyTo = "^event (.+) /from (.+) /to\\s";
    private static String EventRegexWithoutFrom = "^event (.+) /to (.+)";
    private static String EventRegexWithEmptyFrom = "^event (.+) /from\\s/to (.+)";

    protected Event(String taskDescription, String fromTiming, String toTiming, boolean isDone) {
        super(taskDescription, isDone);
        this.fromTiming = fromTiming;
        this.toTiming = toTiming;
        this.taskType = "E";
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), this.fromTiming, this.toTiming);
    }

    public static Task createTask(String input) throws NedException {
        String[] parsed_inputs = input.split("event|/from|/to", 4);
        int parsed_inputs_len = Task.checkSizeOfInput(parsed_inputs);
        if (parsed_inputs[1].strip().isBlank()) {
            throw new NedException("M'lord, you cannot create an event task with no description");
        } else if (parsed_inputs_len <= 2) {
            if (Pattern.matches(EventRegexWithoutTo, input) || Pattern.matches(EventRegexWithEmptyTo, input)) {
                throw new NedException("M'lord, you cannot create an event task with no 'to' date."
                        + " Gods be good, fill both up!");
            } else if (Pattern.matches(EventRegexWithoutFrom, input) || Pattern.matches(EventRegexWithEmptyFrom, input)) {
                throw new NedException("M'lord, you cannot create an event task with no 'from' date."
                        + " Gods be good, fill both up!");
            } else {
                throw new NedException("M'lord, you cannot create an event task with no 'from' date " +
                        "or no 'to' date. Gods be good, fill both up!");
            }
        }
        Task newTask = new Event(parsed_inputs[1].strip(), parsed_inputs[2].strip(), parsed_inputs[3].strip(), false);
        return newTask;
    }
    public static Event createEvent(boolean taskStatus, String taskDescription, String eventFromDate, String eventToDate) throws NedException {
        if (taskDescription.isBlank()) {
            throw new NedException("M'lord, this saved event task has no task description!");
        } else if (eventFromDate.isBlank()) {
            throw new NedException("M'lord, this saved event task has no from date!");
        } else if (eventToDate.isBlank()) {
            throw new NedException("M'lord, this saved event task has no to date!");
        }
        return new Event(taskDescription, eventFromDate, eventToDate, taskStatus);
    }

    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("event|%d|%s", status,this.taskDescription);
    }

}
