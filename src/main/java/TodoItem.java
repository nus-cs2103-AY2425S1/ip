/**
 * TodoItem represents a to-do entry
 */
public class TodoItem {
    /** Content that the entry stores **/
    private final String content;

    public TodoItem(String content) {
        this.content = content;
    }

    /**
     * String representation of the entry
     *
     * @return a string representation of the entry
     */
    public String toString(){
        return this.content;
    }
}
