package cancelgpt.task;

/**
 * Represents a task with a fixed duration but no specific start or end time.
 */
public class FixedDurationTask extends Task {
    protected double durationHours;

    /**
     * Initialises FixedDurationTask with a description and a duration in hours,
     * with an initial status of not done.
     *
     * @param description   the description of the task
     * @param durationHours the duration of the task in hours
     */
    public FixedDurationTask(String description, double durationHours) {
        super(description);
        this.durationHours = durationHours;
    }

    /**
     * Initialises FixedDurationTask with a description and a duration in hours,
     * with an initial status set to isDone.
     *
     * @param isDone        the status of the task
     * @param description   the description of the task
     * @param durationHours the duration of the task in hours
     */
    public FixedDurationTask(boolean isDone, String description, double durationHours) {
        super(isDone, description);
        this.durationHours = durationHours;
    }

    /**
     * Returns a fixed duration task generated from information from savedDataArr array.
     *
     * @param savedDataArr the array containing information to generate the fixed duration task
     * @return the fixed duration task created
     * @throws NumberFormatException if the duration in the savedDataArr cannot be parsed
     */
    public static Task getTaskFromSavedDataStringArr(String[] savedDataArr) throws NumberFormatException {
        return new FixedDurationTask(getStatusBoolean(Integer.parseInt(savedDataArr[1])),
                savedDataArr[2], Double.parseDouble(savedDataArr[3]));
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (duration: " + formatDuration(durationHours) + ")";
    }

    @Override
    public String getSavedDataString() {
        return "F" + " | " + super.getSavedDataString() + " | " + String.format("%.2f", durationHours);
    }

    /**
     * Formats the duration into a human-readable string.
     *
     * @param hours the duration in hours
     * @return a formatted string representing the duration
     */
    private String formatDuration(double hours) {
        int wholeHours = (int) hours;
        int minutes = (int) Math.round((hours - wholeHours) * 60);

        if (wholeHours > 0) {
            return wholeHours + " hour" + (wholeHours != 1 ? "s" : "")
                    + (minutes > 0 ? " " + minutes + " minute" + (minutes != 1 ? "s" : "") : "");
        } else {
            return minutes + " minute" + (minutes != 1 ? "s" : "");
        }
    }
}
