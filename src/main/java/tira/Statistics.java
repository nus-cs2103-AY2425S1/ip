package tira;

/**
 * The Statistics class tracks and calculates the task statistics
 */
public class Statistics {
    protected int toDoCount;
    protected int markedToDoCount;
    protected int unmarkedToDoCount;
    protected int deadlineCount;
    protected int markedDeadlineCount;
    protected int unmarkedDeadlineCount;
    protected int eventCount;
    protected int markedEventCount;
    protected int unmarkedEventCount;

    /**
     * Constructs a new Statistics object
     */
    public Statistics() {
        toDoCount = 0;
        markedToDoCount = 0;
        unmarkedToDoCount = 0;
        deadlineCount = 0;
        markedDeadlineCount = 0;
        unmarkedDeadlineCount = 0;
        eventCount = 0;
        markedEventCount = 0;
        unmarkedEventCount = 0;
    }

    /**
     * Calculates the number of tasks in the taskList.
     * @return total task
     */
    public int getTotalTasks() {
        return this.deadlineCount + this.toDoCount + this.eventCount;
    }

    /**
     * Calculates the number of total marked task in the list
     * @return total marked tasks
     */
    public int getTotalMarkedTasks() {
        return markedToDoCount + markedDeadlineCount + markedEventCount;
    }

    /**
     * Calculates the total unmarked tasks in the list
     * @return total unmarked tasks
     */
    public int getTotalUnmarkedTasks() {
        return unmarkedToDoCount + unmarkedDeadlineCount + unmarkedEventCount;
    }

    /**
     * Calculates the percentage of marked tasks in the list
     * @return Percentage of marked tasks in integer
     */
    public int getMarkedPercentage() {
        if (this.getTotalTasks() == 0) {
            return 0;
        } else {
            return this.getTotalMarkedTasks() * 100 / this.getTotalTasks();
        }
    }

    /**
     * Adds the tally of total Task
     * @param task type of task that wants to be incremented
     */
    public void addTaskCount(String task) {
        if (task.equals("deadline")) {
            this.deadlineCount++;
        } else if (task.equals("event")) {
            this.eventCount++;
        } else if (task.equals("todo")) {
            this.toDoCount++;
        }
    }

    /**
     * Counts the number of marked and unmarked tasks
     * @param isMark Checks if the task is marked or not
     * @param task Task type whose marked or unmarked is to be incremented
     */
    public void addMarkOrUnmarkCount(boolean isMark, String task) {
        if (isMark) {
            if (task.equals("deadline")) {
                this.markedDeadlineCount++;
            } else if (task.equals("event")) {
                this.markedEventCount++;
            } else if (task.equals("todo")) {
                this.markedToDoCount++;
            }
        } else {
            if (task.equals("deadline")) {
                this.unmarkedDeadlineCount++;
            } else if (task.equals("event")) {
                this.unmarkedEventCount++;
            } else if (task.equals("todo")) {
                this.unmarkedToDoCount++;
            }
        }
    }

}
