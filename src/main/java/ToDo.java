import java.io.IOException;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void save() throws IOException {
        Storage.getWriter().write("T | " + (super.getIsDone() ? "1" : "0") + " | " + super.getDescription() + "\n");
    }
}
