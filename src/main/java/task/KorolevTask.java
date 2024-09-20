package task;

public class KorolevTask {
    private boolean isDone = false;
    private String name;

    private String tag;
    /**
     * Constructs an object of KorolevTask.
     *
     * @param name description of the task.
     */
    public KorolevTask(String name) {
        this.name = name;
    }

    /**
     * Marks the task and change its displayed message.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task and change its displayed message.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Checks whether the task is complete.
     *
     * @return the state whether the task is complete.
     */
    public boolean isComplete() {
        return this.isDone;
    }

    /**
     * Adds tag to a specific Korolev Task.
     *
     * @param tag input about users from tag.
     */
    public void tag(String tag) {
        this.tag = tag;
    }

    /**
     * Displays tag information of a specific Korolev task.
     *
     * @return formatted tag information.
     */
    public String showTag() {
        return tag == null ? "[tag:]" : "[tag:" + this.tag + "]";
    }

    /**
     * Untags a specific tasks
     *
     */
    public void untag() {
        this.tag = null;
    }

    /**
     * Checks whether the task name contains the keyword.
     *
     * @param keyword keys input by users.
     * @return tests whether the description contains certain keywords.
     */
    public boolean match(String keyword) {
        return this.name.contains(keyword);
    }

    /**
     * Overrides toString method in KorolevTask.
     *
     * @return string representation of Object.
     */
    @Override
    public String toString() {
        return (this.isDone ? "[X]" : "[ ]") + " " + this.name;
    }
}
