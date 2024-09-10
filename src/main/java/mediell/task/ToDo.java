package mediell.task;

/** Represents a Task as a ToDo. */
public class ToDo extends Task{
    public ToDo() {
        super("");
    }

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks if the provided string is a valid ToDo.
     * @param format String
     * @return true if it is an ToDo else false
     */
    public static boolean isToDoFormat(String format) {
        return format.startsWith("T");
    }

    @Override
    public String taskToStorageFormat() {
        return "T|" + super.taskToStorageFormat();
    }

    @Override
    public void initStorageFormat(String format) {
        String[] temp = format.split("\\|", 2);
        super.initStorageFormat(temp[1]);
    }
}
