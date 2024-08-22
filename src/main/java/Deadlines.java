public class Deadlines extends Task{

    public Deadlines(String description, String deadline) {
        super(description + " (by: " + deadline + ")");
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}
