package Task.Tasks;

import Task.Task;

class Event extends Task {

    String startTime = "";
    String endTime = "";

    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
