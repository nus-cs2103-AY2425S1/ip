package task;

import data.InsufficientInfoException;
import utils.exceptions.IllegalValueException;

public abstract class Task {
    private final String title;
    private boolean status;

    public Task(String title) {
        this(title, false);
    }

    public Task(String title, boolean isDone) {
        this.title = title;
        this.status = isDone;
    }

    public static Task of(String info, TaskType type) throws InsufficientInfoException, IllegalValueException {

        return switch (type) {
            case TODO -> new Todo(info);
            case EVENT -> {
                String[] split = info.split(" /from ");
                if (split.length < 2) throw new InsufficientInfoException(TaskType.EVENT);
                String[] from_to = split[1].split(" /to ");
                if (from_to.length < 2) throw new InsufficientInfoException(TaskType.EVENT);
                yield new Event(split[0], from_to[0], from_to[1]);
            }
            case DEADLINE -> {
                String[] split = info.split(" /by ");
                if (split.length < 2) throw new InsufficientInfoException(TaskType.EVENT);
                yield new Deadline(split[0], split[1]);
            }
        };
    }

    public static Task of(String[] info) throws IllegalValueException {
        return switch (info[0]) {
            case "TODO" -> new Todo(info[2], Boolean.parseBoolean(info[1]));
            case "EVENT" -> new Event(info[2], info[3], info[4], Boolean.parseBoolean(info[1]));
            case "DEADLINE" -> new Deadline(info[2], info[3], Boolean.parseBoolean(info[1]));
            default -> throw new IllegalStateException("Unexpected value: " + info[0]);
        };
    }

    /**
     * Convert task data into a {@code String} to be stored in storage file.
     *
     * @return {@code String} containing data to the task.
     */
    public abstract String storageFormat();

    public String getTitle() {
        return this.title;
    }

    public String getStatus() {
        return this.status ? "True" : "False";
    }

    public void markAsDone() {
        this.status = true;
        System.out.println("Nice! I've marked this task as done: \n" + this);
    }

    public void markAsUndone() {
        this.status = false;
        System.out.println("OK, I've marked this task as not done yet: \n" + this);
    }

    public String getStatusIndicator() {
        return (status ? "[X] " : "[ ] "); // mark done task with X
    }

    @Override
    public String toString() {
        return this.getStatusIndicator() + this.getTitle();
    }
}