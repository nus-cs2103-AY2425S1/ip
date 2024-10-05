package bob.task;

import java.time.LocalDateTime;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone, LocalDateTime createdAt, LocalDateTime completedAt) {
        super(description, isDone, createdAt, completedAt);
    }

    /**
     * Creates a ToDo object from text that is correctly formatted.
     *
     * @param text Correctly formatted text from which Todo can be created.
     * @return Created ToDo object.
     */
    public static Task from(String text) {
        String[] parameters = text.split("\\s\\|\\s");
        assert parameters.length == 5 : "Number of elements after splitting should be 5";
        return new ToDo(
                parameters[2],
                parameters[1].equals("1"),
                LocalDateTime.parse(parameters[3]),
                parameters[4].equals("null") ? null : LocalDateTime.parse(parameters[4]));
    }

    @Override
    public String toText() {
        return String.format(
                "T | %s | %s | %s | %s",
                super.isDone ? 1 : 0,
                super.description,
                super.getCreatedAt(),
                super.getCompletedAt()
        );
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
