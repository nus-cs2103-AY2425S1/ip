package duke;
/**
 * Represents a Task with no date attribute.
 */
public class Todo extends Task{
    public Todo(String descr) {
        super(descr);
    }

    /**
     * Returns description and date of todo task, when TaskList.printTasks() is called.
     *
     * @return String task description and date.
     * */
    public String toString() {
        return "[T]" + super.toString();
    }
}