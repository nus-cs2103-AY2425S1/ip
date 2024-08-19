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
        Task element;

        if(segments[0].equals("todo")) {
            if (segments.length == 1) {
                throw new MendelException("OOPS! todo description cannot be empty.\nAdd description.");
            }
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
            String[] misplacedSegments = message.split("/by");
            String[] mainMessage = slashSegments[0].split(" ");
            if (mainMessage.length == 1 && slashSegments.length < 2) {
                throw new MendelException("OOPS! deadline needs more details.\nAdd description.");
            } else if (misplacedSegments.length != slashSegments.length) {
                throw new MendelException("OOPS! deadline due wrongly formatted\nPlease add spaces around /by");
            } else if (mainMessage.length == 1) {
                throw new MendelException("OOPS! deadline description cannot be empty.\nAdd description.");
            } else if (slashSegments.length < 2) {
                throw new MendelException("OOPS! deadline due cannot be empty.\nPlease indicate a due.");
            } else if (slashSegments.length > 2) {
                throw new MendelException("OOPS! I am unsure of due.\nPlease specify only one due.");
            }
            String endMsg = slashSegments[1];
            if (endMsg.equals("")) {
                throw new MendelException("OOPS! I am unsure of due.\nPlease specify a due.");
            }
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
            if (slashSegments.length < 2 && slashSegments[0].equals("event")) {
                throw new MendelException("OOPS! event needs more details.\nAdd description.");
            } else if (slashSegments.length < 2) {
                if (message.split("/from").length != slashSegments.length) {
                    throw new MendelException("OOPS! deadline from wrongly formatted.\nPlease add spaces around /from.");
                } else {
                    throw new MendelException("OOPS! event start cannot be empty.\nPlease indicate a start.");
                }
            } else if (slashSegments.length > 2) {
                throw new MendelException("OOPS! I am unsure of start.\nPlease specify only one start.");
            } else if (mainMessage.length == 1) {
                throw new MendelException("OOPS! event description cannot be empty.\nAdd description.");
            } else if (slashSegments[1].split(" /to ").length != slashSegments[1].split("/to").length) {
                throw new MendelException("OOPS! deadline to wrongly formatted.\nPlease add spaces around /to.");
            }  else if (slashSegments[1].split(" /to ").length < 2) {
                throw new MendelException("OOPS! I am unsure of end.\nPlease specify an end.");
            } else if (slashSegments[1].split(" /to ").length > 2) {
                throw new MendelException("OOPS! I am unsure of end.\nPlease specify only one end.");
            }
            String startMsg = slashSegments[1].split(" /to ")[0];
            String endMsg = slashSegments[1].split(" /to ")[1];
            if (startMsg.equals("") && endMsg.equals("")) {
                throw new MendelException("OOPS! I am unsure of start and due.\nPlease specify a start and due.");
            } else if (startMsg.equals("")) {
                throw new MendelException("OOPS! I am unsure of due.\nPlease specify a due.");
            } else if (endMsg.equals("")) {
                throw new MendelException("OOPS! I am unsure of due.\nPlease specify a due.");
            }
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
        } else {
            throw new MendelException("OOPS! I cannot understand command\nCheck the first word.");
        }

        this.messages[this.counter] = element;
        this.counter++;
        String outputMessage = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                element, this.counter);
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
                    this.messages[0].toString());
        }
        for (int i = 1; i < counter; i++) {
            int increment = i + 1;
            finalMessage += String.format("\n%d.%s", increment,
                    this.messages[i].toString());

        }
        return finalMessage;
    }
}
