public class Deadline extends Task {
    private String deadline;
    private static Line line = new Line();

    private Deadline(String name, TaskType taskType, String deadline) {
        super(name, taskType);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeAsString(){
            return "D";
    }

    public static Deadline of(String name, TaskType taskType) throws TaskCreationException {
        try {
            String[] parts = name.split("/by",  2);
            String taskName = parts[0].trim();
            String taskDeadline= parts[1].trim();
            return new Deadline(taskName, taskType, taskDeadline);}
        catch (ArrayIndexOutOfBoundsException e) {
            line.drawLine();
            System.out.println("      Invalid deadline format. Expected format: 'task description /by date/time' ");
            line.drawLine();
            throw new TaskCreationException();
        }
    }

    @Override
    public String readTask() {
        return super.readTask() + " (by: " + deadline + ")";
    }
}
