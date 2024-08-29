package donna.task;

import donna.DonnaException;

public class ToDo extends Task {
    private final String description;
    public ToDo(String description) throws DonnaException {
        super(description);
        this.description = description;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (this.isDone() ? "1" : "0") + " | " + description;
    }

    @Override
    public String getType() {
        return "T";
    }
}