package bunbun.tasks;

/**
 * This class implements a TimeBox task.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class TimeBox extends Task {

    private int duration;

    /**
     * Instantiates a Time boxed task.
     *
     * @param task String description of task.
     * @param duration Int number of hours to complete task.
     */
    public TimeBox(String task, int duration) {
        super(task);
        this.duration = duration;
    }

    @Override
    public String genFileString() {
        String isComplete = (this.isComplete()) ? "true" : "false";
        String taskDescription = String.format("%s;%s;%s;%d\n", "timebox", isComplete, this.getTask(), this.duration);
        return taskDescription;
    }

    @Override
    public String toString() {
        return "[B]" + super.toString() + String.format("( needs %d hours )", this.duration);
    }
}
