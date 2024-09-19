package elster.tasks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import elster.Elseption;

/**
 * Parent class that determines base behaviour of all tasks, primarily that of having a description
 * and status.
 */
public abstract class Task {
    protected String description;
    protected boolean status;
    protected ArrayList<String> tags;

    /**
     * General constructor for all tasks.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.status = false;
        this.tags = new ArrayList<>();
    }

    protected static void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

    /**
     * Marks the task as done.
     * The method returns false if the task was already done.
     *
     * @return Boolean that represents whether the task was already done or not.
     */
    public boolean markAsDone() {
        if (this.status) {
            return false;

        } else {
            this.status = true;
            return true;
        }
    }


    public boolean descContains(String str) {
        return description.contains(str);
    }

    /**
     * Marks the task as undone.
     * The method returns false if the task was already undone.
     *
     * @return Boolean that represents whether the task was already undone or not.
     */
    public boolean unmarkAsUndone() {
        if (!this.status) {
            return false;

        } else {
            this.status = false;
            return true;
        }
    }

    /**
     * Tags the task with the given tag.
     * @param tag tag to tag the task with.
     * @throws Elseption if the tag already exists.
     */
    public void tag(String tag) throws Elseption {
        if (!tags.contains(tag)) {
            tags.add(tag);
        } else {
            throw new Elseption("That tag kind of already exists?");
        }
    }


    @Override
    public String toString() {
        String resultStr = "";
        if (this.status) {
            resultStr += "[X] " + this.description;
        } else {
            resultStr += "[ ] " + this.description;
        }
        for (String tag : tags) {
            resultStr += " #" + tag;
        }
        return resultStr;
    }

    /**
     * Returns a string representation of the task to be written to the save file.
     *
     * @return String representation of task suitable for writing to file.
     */
    public abstract String toFileString();
}
