public class Event extends Task {
    private String startTime;
    private String endTime;

    private Event(String name, TaskType taskType, String startTime, String endTime) {
        super(name, taskType);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTaskTypeAsString(){
            return "E";
    }

    public static Event of(String name, TaskType taskType) {
        String[] parts = name.split("/from", 2);
        String taskName = parts[0];
        String times = parts[1];
        String[] timeParts = name.split("/to", 2);
        String taskStart =  timeParts[0];
        String taskEnd =  timeParts[1];
        return new Event(name, taskType, taskStart, taskEnd);
    }
}
