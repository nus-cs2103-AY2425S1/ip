public class Deadline extends Task{
    private final static String TASK_TYPE = "D";
    private String deadline;
    public Deadline(String input) {
        super(input.substring(0, input.indexOf("/by") - 1));
        int index = input.indexOf("/by");
        deadline = input.substring(index + 3).trim();
    }

    public Deadline(String input, String date, Boolean isDone) {
        super(input.substring(0, input.indexOf("/by") - 1), isDone);
        int index = input.indexOf("/by");
        deadline = input.substring(index + 3).trim();
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (%s)", TASK_TYPE, super.toString(), deadline);
    }

    @Override
    public String stringData() {
        return String.format("%s | %s | %s", TASK_TYPE, super.stringData(), deadline);
    }
}
