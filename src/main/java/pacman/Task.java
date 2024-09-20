package pacman;

/**
 * A class that encapsulate <code>Todo</code>, <code>Deadline</code>, and <code>Event</code>
 * A <code>Task</code> object corresponds a <code>String</code> as task name and
 * a <code>boolean</code> as mark of the task with default of false
 */
public class Task {
    private final String taskName;
    private boolean isMarkDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Change mark status
     *
     * @param marked The status of the mark
     */
    public void setMarkDone(boolean marked) {
        this.isMarkDone = marked;
    }

    /**
     * Return a <code>String</code> that is readable and writeable by <code>Storage</code>
     *
     * @return a <code>String</code> that is readable and writeable by <code>Storage</code>
     */
    public String toFile() {
        String mark = this.isMarkDone ? "1" : "0";
        return mark + "/" + taskName;
    }

    public boolean isMatched(String matcher) {
        return this.taskName.contains(matcher);
    }

    @Override
    public String toString() {
        String mark = this.isMarkDone ? "X" : " ";
        return "[" + mark + "] " + this.taskName;
    }
}
