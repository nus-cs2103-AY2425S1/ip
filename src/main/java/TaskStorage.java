public class TaskStorage extends MendelAction{
    private final Task[] messages;
    int counter;

    public TaskStorage() {
        this.messages = new Task[100];
        this.counter = 0;
    }

    public void marker(int serial) {
        Task task = this.messages[serial];
        task.markAsDone();
        String outputMessage = String.format("Nice! I've marked this task as done:\n  %s",
                task);
        System.out.println(new FormatText(outputMessage).wrapLines());

    }

    public void unMarker(int serial) {
        Task task = this.messages[serial];
        task.markAsUnDone();
        String outputMessage = String.format("OK, I've marked this task as not done yet:\n  %s",
                task);
        System.out.println(new FormatText(outputMessage).wrapLines());
    }

    public void add(String message) {
        String[] segments = message.split(" ");

        Task element = new Task(message);
        if(segments[0].equals("todo")) {
            String reformattedMsg = "";
            for (int i = 1; i < segments.length; i++) {
                if (i == segments.length - 1) {
                    reformattedMsg += segments[i];
                } else {
                    reformattedMsg += segments[i] + " ";
                }
            }
            element = new Todo(reformattedMsg);
        } else if (segments[0].equals("deadline")) {
            String[] slashSegments = message.split(" /by ");
            String[] mainMessage = slashSegments[0].split(" ");
            String endMsg = slashSegments[1];
            String reformattedMsg = "";
            for (int i = 1; i < mainMessage.length; i++) {
                if (i == mainMessage.length - 1) {
                    reformattedMsg += mainMessage[i];
                } else {
                    reformattedMsg += mainMessage[i] + " ";
                }
            }
            reformattedMsg += String.format(" (by: %s)", endMsg);
            element = new Deadline(reformattedMsg);
        } else if (segments[0].equals("event")) {
            String[] slashSegments = message.split(" /from ");
            String[] mainMessage = slashSegments[0].split(" ");
            String startMsg = slashSegments[1].split(" /to ")[0];
            String endMsg = slashSegments[1].split(" /to ")[1];
            String reformattedMsg = "";
            for (int i = 1; i < mainMessage.length; i++) {
                if (i == mainMessage.length - 1) {
                    reformattedMsg += mainMessage[i];
                } else {
                    reformattedMsg += mainMessage[i] + " ";
                }
            }
            reformattedMsg += String.format(" (from: %s to %s)", startMsg, endMsg);
            element = new Event(reformattedMsg);
        }

        this.messages[this.counter] = element;
        this.counter++;
        String outputMessage = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                element.toString(), this.counter);
        System.out.println(new FormatText(outputMessage).wrapLines());
    }

    public void speak() {
        String outputMessage = String.format("Here are the tasks in your list:\n%s",
                this);
        System.out.println(new FormatText(outputMessage).wrapLines());
    }

    @Override
    public String toString() {
        String finalMessage = "";
        if (counter > 0) {
            finalMessage = String.format("1.%s",
//                    this.messages[0].getStatusIcon(),
                    this.messages[0].toString());
        }
        for (int i = 1; i < counter; i++) {
            int increment = i + 1;
            finalMessage += String.format("\n%d.%s", increment,
//                    this.messages[i].getStatusIcon(),
                    this.messages[i].toString());

        }
        return finalMessage;
    }
}
