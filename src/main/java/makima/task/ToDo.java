package makima.task;

import java.util.ArrayList;

/**
 * Task with no specified start or end date.
 */
public class ToDo extends Task {

    public static final int SAVE_PARAMETERS = 4;

    ToDo() {
        super();
    }
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean done, PriorityLevel priorityLevel) {
        super(name, done, priorityLevel);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return String.format("T\n%s", super.toFileString());
    }

    @Override
    boolean load(ArrayList<String> data) {
        return super.load(data);
    }

    /**
     * Factory method to load ToDo from data. Returns null if data is corrupted.
     *
     * @param data List of strings representing the ToDo.
     * @return ToDo
     */
    public static ToDo loadFromData(ArrayList<String> data) {
        ToDo toDo = new ToDo();
        if (toDo.load(data)) {
            return toDo;
        }
        return null;
    }
}
