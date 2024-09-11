package ipman.models;

/**
 * Represents a simple task that needs to be done
 */
public class ToDo extends Task {
    public static final char TASK_TYPE = 'T';
    public ToDo(String name) {
        super(name);
    }

    @Override
    public char getTaskType() {
        return TASK_TYPE;
    }

    /**
     * Constructs an <code>ToDo</code> from a previously serialized
     * <code>ToDo</code>.
     *
     * @param serializedToDo string from previously serialized to-do
     * @return parsed <code>ToDo</code> from the serialized string
     * @see Task#serialize()
     */
    public static ToDo deserialize(String serializedToDo) {
        String[] values = serializedToDo.split("\\|");
        ToDo toDo = new ToDo(values[2]);
        if (values[1].equals("X")) {
            toDo.markDone();
        }
        return toDo;
    }

    @Override
    public String serialize() {
        return String.format(
            "%s|%c|%s",
            this.getTaskType(),
            this.getIsDone() ? 'X' : 'O',
            this.getName()
        );
    }
}
