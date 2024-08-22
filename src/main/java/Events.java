public class Events extends Task{

    public Events(String desc) {
        super(desc);
    }

    @Override
    public String print() {
        String[] parts1 = super.print().split(" /from ");
        String[] parts2 = parts1[1].split(" /to ");
        return "[E]" + parts1[0] + " (from: " + parts2[0] + " to: " + parts2[1] + ")";
    }
}
