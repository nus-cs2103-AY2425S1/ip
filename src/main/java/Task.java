/**
 * Task class that is used as the baseline class for defining tasks that Molly saves.
 */
public class Task {

    public String description;
    public boolean taskIsDone;

    public Task(String description) {
        this.description = description;
        this.taskIsDone = false;
    }


    /**
     * Override toString method for printing of todo tasks
     * @return String
     */
    @Override
    public String toString() {
        return taskIsDone ? "[T][X] " + this.description : "[T][ ] " + this.description;
    }


    /**
     * This toggle task done method allows the user to toggle whether the task is marked as completed or not.
     */
    public void toggleTaskDone() {
        if (this.taskIsDone) {
            System.out.println("OK, I've marked this task as not done yet:");
        } else {
            System.out.println("Nice! I've marked this task as done:");
        }
        this.taskIsDone = !this.taskIsDone;
        System.out.println(this.toString());


    }

}
