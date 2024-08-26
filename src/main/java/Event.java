import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String t, String s, String e) throws DateTimeParseException{

        super(t);
        LocalDateTime start;
        LocalDateTime end;
        try {
            start = LocalDateTime.parse(s, Task.inputFormatter);
            end = LocalDateTime.parse(e, Task.inputFormatter);
        } catch (DateTimeParseException exception) {
            Task.deleteTask(Task.getNumTask() - 1);
            throw exception;
        }
        this.type = "[E]";
        this.start = start;
        this.end = end;

    }


    public static void load(String[] arr) {
        Event newEvent = new Event(arr[2], arr[3], arr[4]);
        if (Objects.equals(arr[1], "1")) {
            newEvent.done();
        }
    }

    @Override
    public String saveFileFormat() {
        String status;
        if (this.getCompleted()) {
            status = "1 | ";
        } else {
            status = "0 | ";
        }
        return "E | " + status + this.getTask() + " | " + this.getStart("in") + " | " + this.getEnd("in");
    }

    private String getStart(String type) {
        if (type.equalsIgnoreCase("in")) {
            return this.start.format(Task.inputFormatter);
        } else if (type.equalsIgnoreCase("out")) {
            return this.start.format(Task.outputFormatter);
        } else {
            return "";
        }
    }

    private String getEnd(String type) {
        if (type.equalsIgnoreCase("in")) {
            return this.end.format(Task.inputFormatter);
        } else if (type.equalsIgnoreCase("out")) {
            return this.end.format(Task.outputFormatter);
        } else {
            return "";
        }
    }


    @Override
    public String toString() {
        return super.toString() + " (From: " + this.getStart("out") + " To: " + this.getEnd("out") + ")";
    }
}