package task;

/**
 * task.Task class that is used as the baseline class for defining tasks that Molly saves.
 */
public class Task {

    public String description;
    public boolean taskIsDone;


    public Task(String description) {
        this.description = description;
        this.taskIsDone = false;
    }


    public void markDone() {
        this.taskIsDone = true;
    }


    /**
     * Overrides toString method for printing of todo tasks
     * @return String
     */
    @Override
    public String toString() {
        return taskIsDone ? "[T][X] " + this.description : "[T][ ] " + this.description;
    }


    /**
     * Allows the user to toggle whether the task is marked as completed or not.
     */
    public String toggleTaskDone() {
        StringBuilder toggleResponse = new StringBuilder();
        if (this.taskIsDone) {
            toggleResponse.append("OK, I've marked this task as not done yet:").append("\n");
        } else {
            toggleResponse.append("Nice! I've marked this task as done:").append("\n");
        }
        this.taskIsDone = !this.taskIsDone;
        toggleResponse.append(this.toString()).append("\n");

        return toggleResponse.toString();

    }

}
