package kitty.tasks;

/**
 * Provides a skeleton for tasks.Task objects.
 */
public class Task {
    private final String name;
    private boolean isDone = false;
    private String tag;

    protected final String deli = "~!!";

    /**
     * Provides constructor for the class.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this(name, "");
    }

    public Task(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public void updateTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns task description in String to write in the file.
     */
    public String getTaskData() {
        String str = " " + deli;
        if (isDone) {
            str += " 1 ";
        } else {
            str += " 0 ";
        }

        str += deli + " " + name;

        if (!tag.isEmpty()) {
            str += " #" + tag;
        }

        return str;
    }

    /**
     * Marks the tasks.Task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the tasks.Task as undone.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Checks if the tasks.Task contains the keyword to utilize searching.
     *
     * @param key The keyword to check.
     */
    public boolean containsKeyword(String key) {
        return name.contains(key);
    }

    /**
     * Returns tasks.Task description in String for ChatBot interaction.
     */
    @Override
    public String toString() {
        String str = "[";
        if (isDone) {
            str += "X";
        } else {
            str += " ";
        }

        str += "] " + name;

        if (!tag.isEmpty()) {
            str += " #" + tag;
        }
        return str;
    }

    public boolean isDone() {
        return isDone;
    }
}
