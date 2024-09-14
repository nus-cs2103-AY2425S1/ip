package bob;

import java.time.LocalDateTime;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone, LocalDateTime createdAt) {
        super(description, isDone, createdAt);
    }

    public static Task from(String text) {
        String[] parameters = text.split("\\s\\|\\s");
        assert parameters.length == 4 : "Number of elements after splitting should be 4";
        return new ToDo(parameters[2], parameters[1].equals("1"), LocalDateTime.parse(parameters[3]));
    }

    @Override
    public String toText() {
        return String.format("T | %s | %s | %s", super.isDone ? 1 : 0, super.description, super.getCreatedAt());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
