package blob;

import java.util.ArrayList;

/**
 * Represents a Task that contains the following fields, a String representation of its type,
 * a name, and a boolean value of whether it is done or not.
 */
public class Task {
    protected String type;
    protected String name;
    protected boolean isDone;
    protected ArrayList<String> tags = new ArrayList<>();

    public Task(String name, boolean isDone, ArrayList<String> tags) {
        this.name = name;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Sets isDone field to true
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Sets isDone field to false
     */
    public void undo() {
        this.isDone = false;
    }

    /**
     * Returns the corresponding representation of the task's completion status.
     * @return "X" for done, and " " for not done.
     */
    public String check() {
        return isDone ? "X" : " ";
    }

    /**
     * @return String representation of the task of form "['completion status'] 'task name' - #tag1 #tag2..."
     */
    @Override
    public String toString() {
        if (this.tags.isEmpty()) {
            return "[" + this.check() + "] " + this.name;
        } else {
            StringBuilder tags = new StringBuilder("");
            for (int i = 0; i < this.tags.size(); i++) {
                String tag = this.tags.get(i);
                tags.append("#" + tag + " ");
            }
            return "[" + this.check() + "] " + this.name + " - " + tags.toString();
        }
    }

    public String displayTags() {
        if (this.tags.isEmpty()) {
            return "";
        } else {
            StringBuilder tags = new StringBuilder("");
            for (int i = 0; i < this.tags.size(); i++) {
                if (i != this.tags.size() - 1) {
                    tags.append(this.tags.get(i) + ",");
                } else {
                    tags.append(this.tags.get(i));
                }
            }
            return tags.toString();
        }
    }
}
