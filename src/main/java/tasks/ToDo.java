package tasks;

import java.io.Serializable;

/**
 * Represents a task to be completed.
 * This class extends the Task class and implements Serializable for object serialization.
 */
public class ToDo extends Task implements Serializable {

    /**
     * Creates a task to be completed.
     *
     * @param description without any date/time attached to it.
     */
    public ToDo(String description) {
        this.description = description;
    }

    /**
     * Returns the information of the task.
     *
     * @return information the task in "[T][-] Task" format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /** Sends help information of command 'todo' to user */
    public static String getHelpMessage() {
        return "ToDo is a task without any date/time attached to it.\n"
                + "e.g. visit new theme park\n\n"
                + "Format:\n"
                + "\ttodo<whitespace>[description]\n"
                + "- [description] is the details of this task.\n\n"
                + "Remark: Please do not enter extra whitespace.";
    }
}
