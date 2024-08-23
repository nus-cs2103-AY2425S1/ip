package Task.Tasks;

import Task.Task;

public class Event extends Task {

    String startTime = "";
    String endTime = "";

    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event: ");
        sb.append(super.toString());
        sb.append("[From :");
        sb.append(startTime);
        sb.append(" To : ");
        sb.append(endTime);
        sb.append("]");
        return sb.toString();
    }

}
