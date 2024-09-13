import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

public class ToDo extends Task {

    public ToDo(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatus().getStatusSymbol() + "]" + " " + getInfo();
    }

    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return "T | " + (getStatus() == TaskStatus.DONE ? "1" : "0") + " | " + getInfo();
    }
}
