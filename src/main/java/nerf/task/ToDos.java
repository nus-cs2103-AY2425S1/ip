package nerf.task;

/**
 * Todo class
 */
public class ToDos extends Task{
    public ToDos(String description) {
        super(description);
    }
    public ToDos(String description, boolean isDone, String priority ) {
        super(description, isDone, priority);
    }

    /**
     * Returns format for saving to file.
     * 
     * @return string format of task.
     */
    @Override
    public String getSaveFormat() {
        return String.format("T | %s", super.getSaveFormat());
    }

    /**
     * Returns format for printing.
     * 
     * @return string format of task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}