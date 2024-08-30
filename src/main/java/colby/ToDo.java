package colby;

import java.util.Objects;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo todo = (ToDo) o;
        return Objects.equals(description, todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
