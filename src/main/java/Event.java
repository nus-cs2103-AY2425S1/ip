public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);

        String[] startParts = startTime.split(" ");
        StringBuilder startSB = new StringBuilder(startParts[0]).append(" ");
        for (int i = 1; i < startParts.length; i++) {
            startSB.append(startParts[i]).append(" ");
        }
        this.startTime = startSB.toString();

        String[] endParts = endTime.split(" ");
        StringBuilder endSB = new StringBuilder(endParts[0]).append(" ");
        for (int i = 1; i < endParts.length - 1; i++) {
            endSB.append(endParts[i]).append(" ");
        }
        endSB.append(endParts[endParts.length - 1]);
        this.endTime = endSB.toString();
    }

    @Override
    public String toFileString() {
        return "E , " + (isComplete() ? 1 : 0) + " , " + getName() + " , " + startTime + " , " + endTime + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startTime + endTime + ")";
    }
}
