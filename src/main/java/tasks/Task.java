package tasks;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the general form of a task.
 * A <code>Task</code> object minimally has a name and a flag to show if it is done
 */
public abstract class Task {
    protected String name;
    protected boolean isDone = false;
    protected ArrayList<String> tags = new ArrayList<>();

    public String getName() {
        return this.name;
    }

    /**
     * Returns the string representation of how a task should be saved
     *
     * @return save format of task.
     */
    public abstract String toSave();

    /**
     * Marks the task as done by setting flag to true
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task by setting flag to false
     */
    public void unMark() {
        this.isDone = false;
    }


    /**
     * Returns the string representation a task, including all its relevant information
     *
     * @return string representation of a task.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (this.isDone) {
            res.append("[X] ");
        } else {
            res.append("[ ] ");
        }
        res.append(this.name);

        return res.toString();
    }


    /**
     * Adds all tags to task.
     *
     * @param tagsArr The string array of tags to add to the task.
     */
    public void addTags(String[] tagsArr) {
        Collections.addAll(tags, tagsArr);
    }

    /**
     * Removes all tags in tagsArr from task.
     *
     * @param tagsArr The string array of tags to remove from the task.
     */
    public void removeTags(String[] tagsArr) {
        for (String s : tagsArr) {
            tags.remove(s);
        }
    }
}
