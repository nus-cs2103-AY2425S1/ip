import java.nio.charset.StandardCharsets;

public class ToDo extends Task {

    public ToDo(String info) {
        super(info);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatus().getStatusSymbol() + "]" + " " + getInfo();
    }
}
