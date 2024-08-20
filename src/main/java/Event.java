public class Event extends Task {
    private String startTime;
    private String endTime;

    private Event(String name, TaskType taskType, String startTime, String endTime) {
        super(name, taskType);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String getTaskTypeAsString(){
            return "E";
    }

    @Override
    public String readTask() {
        return super.readTask() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    public static Event of(String name, TaskType taskType) throws TaskCreationException {
        try {
            String[] parts = name.split("/from", 2);
            String taskName = parts[0];
            String times = parts[1];
            String[] timeParts = times.split("/to", 2);
            String taskStart = timeParts[0].trim();
            String taskEnd = timeParts[1].trim();
            return new Event(taskName, taskType, taskStart, taskEnd);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskCreationException();
        }
    }

}
