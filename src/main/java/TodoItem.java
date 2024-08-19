/**
 * TodoItem represents a to-do entry
 */
public class TodoItem {
    /** Content that the entry stores **/
    private final String content;
    /** Status of the to-do item **/
    private boolean completed;

    /**
     * Creates a new to-do item with the specified content
     *
     * @param content The task description of the to-do item
     */
    public TodoItem(String content) {
        this.content = content.trim();
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
        return String.format("[T] [%s] %s", this.completed ? "X" : " ", content);
    }
}
