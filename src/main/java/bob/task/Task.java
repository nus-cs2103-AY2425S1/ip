package bob.task;

/**
 * Task that user is keeping track of.
 */
public class Task {
    private String description;
    private boolean isDone;
    private String tag;

    /**
     * Constructor to initialise a task.
     * @param description Input based on user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Constructor to initialise a task previously recorded.
     *
     * @param description Input based on user.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tag = "";
    }

    /**
     * Constructor to initialise a task previously recorded.
     *
     * @param description Input based on user.
     */
    public Task(String description, boolean isDone, String tag) {
        this.description = description;
        this.isDone = isDone;
        this.tag = tag;
    }

    /**
     * Returns whether a task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation for a task in the printed list.
     */
    public String getTaskListItem() {
        String taskListItemPart1 = "[" + getTaskLetter() + "][" + this.getStatusIcon() + "]";
        if (!tag.equals("")) {
            taskListItemPart1 += "[#" + tag + "]";
        }
        taskListItemPart1 += " ";
        return taskListItemPart1 + this.description;
    }

    /**
     * Marks 1 task as completed or not completed.
     */
    public void mark1Task(boolean isCompleted) {
        if (isCompleted) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    /**
     * Returns the string representation of the output that will be displayed for the user.
     *
     * @param isCompleted whether the task is completed.
     */
    public String getMarkedTaskStringFromTask(boolean isCompleted) {
        if (isCompleted) {
            String finishedMarking = "OK, I've marked this task as done:\n\t"
                    + "["
                    + getTaskLetter()
                    + "]"
                    + "[X] "
                    + this.description;
            return finishedMarking;
        } else {
            String finishedUnmarking = "OK, I've marked this task as not done yet:\n\t"
                    + "["
                    + getTaskLetter()
                    + "]"
                    + "[ ] "
                    + this.description;
            return finishedUnmarking;
        }
    }

    public String getTaskLetter() {
        return " ";
    }

    /**
     * Returns a string representation of the file format in which we store the task.
     */
    public String getFileFormat() {
        String done = isDone ? "1" : "0";
        String fileFormat = this.getTaskLetter() + " | " + done + " | " + this.description;
        return fileFormat;
    }

    /**
     * Returns whether the target word can be found in the description of Task.
     * @param target The keyword that user is searching for.
     * @return
     */
    public boolean isTargetInDescription(String target) {
        String[] descriptionArray = description.split("\s+");
        boolean isPresent = false;
        for (String word: descriptionArray) {
            if (word.equals(target)) {
                isPresent = true;
            }
        }
        return isPresent;
    }

    /**
     * Tags the task.
     *
     * @param tag
     */
    public void tagTask(String tag) {
        this.tag = "";
    }

    /**
     * Returns task tag.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Sets the tag of the task.
     *
     * @param tag Tag.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
}
