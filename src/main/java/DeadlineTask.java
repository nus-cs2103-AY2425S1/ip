public class DeadlineTask extends Task{
    String deadline;
    private DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static DeadlineTask of(String input) {
        String[] splitInput = input.split("\\s+");
        if (input.strip().equals("deadline")) {
            printLine();
            System.out.println("    Elster begs of you to have details for your task");
            printLine();
            return null;

        } else if (!input.contains("/by")) {
            printLine();
            System.out.println("    Elster begs of you to have a yknow, deadline with /by");
            printLine();
            return null;

        }

        return new DeadlineTask(
                input.substring(8, input.indexOf("/by")).strip(),
                input.substring(input.indexOf("/by") + 4).strip()
        );
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[D][X] " + this.description + " (by: " + deadline + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + deadline + ")";
        }
    }
}
