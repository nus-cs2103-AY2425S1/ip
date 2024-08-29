package models;

public class Deadline extends Task {
    public static final char TASK_TYPE = 'D';
    private final String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public char getTaskType() {
        return TASK_TYPE;
    }

    public static Deadline deserialize(String line) {
        String[] values = line.split("\\|");
        Deadline deadline = new Deadline(values[2], values[3]);
        if (values[1].equals("X")) {
            deadline.markDone();
        }
        return deadline;
    }

    @Override
    public String serialize() {
        return String.format(
            "%s|%c|%s|%s",
            this.getTaskType(),
            this.isDone ? 'X' : 'O',
            this.name,
            this.by
        );
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.by);
    }
}
