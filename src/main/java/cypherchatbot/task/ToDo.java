package cypherchatbot.task;

import java.util.Objects;

/**
 * Represents a task with no time aspect.
 */
public class ToDo extends Task {
    /**
     * Constructs a new Todo task with a given description.
     *
     * @param desc The description of the task.
     */
    public ToDo(String desc) {
        super(desc, 1);
    }

    @Override
    public String toString() {
        String str = this.isComplete ? "[T][X] " : "[T][ ] ";
        str += this.description;
        return str;
    }

    @Override
    public String toStringInFile() {
        int val = this.isComplete ? 1 : 0;
        return String.format("T|%d|%s", val, this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            return Objects.equals(this.description, ((ToDo) o).description);
        }
        return false;
    }
}
