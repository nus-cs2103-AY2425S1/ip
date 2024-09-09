import java.nio.charset.StandardCharsets;

public class ToDo extends Task {

    public ToDo(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatus().getStatusSymbol() + "]" + " " + getInfo();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (getStatus() == TaskStatus.DONE ? "1" : "0") + " | " + getInfo();
    }
}
