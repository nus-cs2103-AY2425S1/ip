package bob.task;

import bob.ui.Ui;

/**
 * Task that user is keeping track of.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor to initialise a task.
     * @param description Input based on user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor to initialise a task previously recorded.
     * @param description Input based on user.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns whether or not a task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation for a task in the printed list.
     */
    public String getTaskListItem() {
        return ("[" + getTaskLetter() + "][" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Marks the task as completed or not completed.
     * @param value Whether the task is completed.
     */
    public void markTask(boolean value) {
        if (value) {
            this.isDone = true;
            String finishedMarking = "OK, I've marked this task as done:\n\t"
                    + "["
                    + getTaskLetter()
                    + "]"
                    + "[X] "
                    + this.description;
            Ui.printLines(finishedMarking);
        } else {
            this.isDone = false;
            String finishedUnmarking = "OK, I've marked this task as not done yet:\n\t"
                    + "["
                    + getTaskLetter()
                    + "]"
                    + "[ ] "
                    + this.description;
            Ui.printLines(finishedUnmarking);
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
        return this.getTaskLetter() + " | " + done + " | " + this.description;
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
}
