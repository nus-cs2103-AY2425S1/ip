public class Event extends Task {
    private final String task;
    private final String from;
    private final String to;

    public Event(String str) throws TaskException {
        try {
            String[] temp = str.split("/");
            task = temp[0].split(" ", 2)[1];
            from = temp[1].split(" ", 2)[1];
            to = temp[2].split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskException("event <task> /from <time> /to <time>");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + task + "(from: " + from + " to: " + to + ")";
    }
}
