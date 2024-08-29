import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Task {

    protected static final DateTimeFormatter DATE_STRING_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected static final DateTimeFormatter DATE_STRING_FORMATTER_PRINT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    protected boolean done = false;
    protected String description;
    private String type = "";

    protected Task(String description, String type) throws TheBotFatherException{
        if (Objects.equals(description, "")) throw new TheBotFatherException("Kid, Learn to read, where is the description??");
        this.description = description;
        this.type = type;
    }

    protected void markAsDone() {
        this.done = true;
    }

    protected void unmark() {
        this.done = false;
    }

    protected boolean isDone() {
        return this.done;
    }

    protected String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    protected String getType() {
        return this.type;
    }

    @Override
    public String toString(){
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description;
    }

    protected String toFile() {
        return this.type + " | " + (this.isDone() ? "1" : "0") + " | " + this.description;
    }

}