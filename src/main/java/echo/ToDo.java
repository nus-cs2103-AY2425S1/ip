package echo;

import java.io.Serializable;

public class ToDo extends Task implements Serializable {

    /**
     * Create a task to be completed.
     *
     * @param task without any date/time attached to it.
     */
    public ToDo(String task) {
        this.task = task;
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
}
