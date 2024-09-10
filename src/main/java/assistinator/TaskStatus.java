package assistinator;

/**
 * Represents status of task
 */
public enum TaskStatus {
    NOTDONE, DONE;

    static boolean isDone(String string) {
        return valueOf(string).equals(DONE);
    }
}
