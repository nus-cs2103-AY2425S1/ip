public class EventTask extends Task {
    String start;
    String end;

    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public static EventTask of(String input) {
        String[] splitInput = input.split("\\s+");
        if (input.strip().equals("event")) {
            printLine();
            System.out.println("    Elster \"kindly\" requests you to have details for your task");
            printLine();
            return null;

        } else if (!input.contains("/from")) {
            printLine();
            System.out.println("    Elster \"kindly\" requests you to have a start time with /from");
            printLine();
            return null;

        } else if (!input.contains("/to")) {
            printLine();
            System.out.println("    Elster \"kindly\" requests you to have a end time with /to");
            printLine();
            return null;

        }

        return new EventTask(
                input.substring(6 , input.indexOf("/from")).strip(),
                input.substring(input.indexOf("/from") + 6 , input.indexOf("/to")).strip(),
                input.substring(input.indexOf("/to") + 4).strip()
        );
    }
    @Override
    public String toString() {
        if (this.status) {
            return "[E][X] " + this.description + " (from: " + start  + " to: " + end + ")";
        } else {
            return "[E][ ] " + this.description + " (from: " + start  + " to: " + end + ")";
        }
    }
}
