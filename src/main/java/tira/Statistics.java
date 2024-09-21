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

    public void addToDoCount() {
        this.toDoCount++;
    }

    public void addMarkedToDo() {
        this.markedToDoCount++;
        this.unmarkedToDoCount = toDoCount - markedToDoCount;
    }

    public void addDeadlineCount() {
        this.deadlineCount++;
    }

    public void addMarkedDeadline() {
        this.markedDeadlineCount++;
        this.unmarkedDeadlineCount = deadlineCount - markedDeadlineCount;
    }

    public void addEventCount() {
        this.eventCount++;
    }

    public void addMarkedEvent() {
        this.markedEventCount++;
        this.unmarkedEventCount = eventCount - markedEventCount;
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
}
