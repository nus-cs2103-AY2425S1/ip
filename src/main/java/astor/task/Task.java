package astor.task;

public class Task {
    private boolean status;
    private String taskInfo;

    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        this.status = false;
    }

    public void markDone() {
        this.status = true;
    }

    public void markUndone() {
        this.status = false;
    }

    public boolean isDone() {
        return this.status;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    @Override
    public String toString() {
        String s = "";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.taskInfo;
        return s;
    }

    public static String generateParse(String deadline) {
        String[] dateAndTime = deadline.trim().split("\\s+|/");
        String parsedDate = dateAndTime[2] + "-" + dateAndTime[1] + "-" + dateAndTime[0];

        if (dateAndTime.length > 3) {
            String hour = dateAndTime[3].substring(0,2);
            String minute = dateAndTime[3].substring(2);
            parsedDate +=  "T" + hour + ":" + minute;
        } else {
            parsedDate += "T00:00:00";
        }
        return parsedDate;
    }

    public String dataDescription() {
        return "";
    }
}
