package skibidi.task;

import java.io.IOException;

public class Todo extends AbstractTask {
    public Todo(String description) {
        super(description);
    }

    public Todo(String marker, String description) {
        super(marker, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String serialize() throws IOException {
        return String.join("|", new String[]{"T", getStatusIcon(), description});
    }
}
