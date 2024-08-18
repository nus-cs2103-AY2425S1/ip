public class Deadline extends Task{
    private String deadline;
    public Deadline(String input) {
        super(input.substring(0, input.indexOf("/by") - 1));
        int index = input.indexOf("/by");
        deadline = input.substring(index + 3).trim();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (%s)", super.toString(), deadline);
    }
}
