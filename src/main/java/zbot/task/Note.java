package zbot.task;

/**
 * Represents a note for a Task
 */
public class Note {
    private String content;

    /**
     * Constructor for Note.
     */
    public Note() {
        this.content = "";
    }

    /**
     * Constructor for Note with content.
     *
     * @param content
     */
    public Note(String content) {
        this.content = content;
    }

    /**
     * Get the content of the note.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Check if the note is empty.
     */
    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    @Override
    public String toString() {
        return this.content;
    }

}
