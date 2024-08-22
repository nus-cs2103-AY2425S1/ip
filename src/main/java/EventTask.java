public class EventTask extends Task {

    private String from;
    private String to;

    public EventTask(String input) {

        this.taskTypeSymbol = "E";

        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        this.name = input.substring(6, fromIndex).trim();
        this.from = input.substring(fromIndex + 6, toIndex).trim();
        this.to = input.substring(toIndex + 4).trim();
    }

    @Override
    public String toString() {
        return String.format("%1$s (from: %2$s to: %3$s)", super.toString(), this.from, this.to);
    }
}
