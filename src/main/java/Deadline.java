public class Deadline extends Task {
    private String endTime;

    private Deadline(String name, TaskType taskType, String endTime) {
        super(name, taskType);
        this.endTime = endTime;
    }

    public String getTaskTypeAsString(){
            return "D";
    }

    public static Deadline of(String name, TaskType taskType) {
            String[] parts = name.split("/by", 2);
            String taskName = parts[0];
            String taskDeadline= parts[1];
            return new Deadline(name, taskType, taskDeadline);
    }
}
