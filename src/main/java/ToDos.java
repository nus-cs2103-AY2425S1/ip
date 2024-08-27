import java.time.format.DateTimeFormatter;

public class ToDos extends Task {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public ToDos (String description) {
        super(description);
    }

    @Override
    public String finalString() {
        return "[T]" + super.finalString();
    }

    @Override
    public String toFileFormat() {
        return "T|" + (isDone ? "X" : " ") + "|" + taskDescription + "|" + "NIL" + "|" + " NIL";
    }
}