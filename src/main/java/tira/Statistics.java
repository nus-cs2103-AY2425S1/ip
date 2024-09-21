package tira;

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

    public int getTotalTasks() {
        return this.deadlineCount + this.toDoCount + this.eventCount;
    }

    public int getTotalMarkedTasks() {
        return markedToDoCount + markedDeadlineCount + markedEventCount;
    }

    public int getTotalUnmarkedTasks() {
        return unmarkedToDoCount + unmarkedDeadlineCount + unmarkedEventCount;
    }

    public int getMarkedPercentage() {
        if (this.getTotalTasks() == 0) {
            return 0;
        } else {
            return this.getTotalMarkedTasks() * 100 / this.getTotalTasks();
        }
    }

    public void addTaskCount(String task) {
        if (task.equals("deadline")) {
            this.deadlineCount++;
        } else if (task.equals("event")) {
            this.eventCount++;
        } else if(task.equals("todo")) {
            this.toDoCount++;
        }
    }

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
