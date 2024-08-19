/**
 * TodoItem represents a to-do entry
 */
public class TodoItem {
    /** Content that the entry stores **/
    private final String content;
    /** Status of the to-do item **/
    private boolean completed;

    public TodoItem(String content) {
        this.content = content;
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * String representation of the entry
     *
     * @return a string representation of the entry
     */
    public String toString(){
        return String.format("[%s] %s", this.completed ? "X" : " ", content);
    }
}
