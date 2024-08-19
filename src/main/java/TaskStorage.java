public class TaskStorage extends MendelAction{
    private Task[] messages;
    int counter;

    public TaskStorage() {
        this.messages = new Task[100];
        this.counter = 0;
    }

    public void controller(String message) {
        String[] segments = message.split(" ");
        if (segments[0].equals("list")) {
            this.speak();
        } else if(segments[0].equals("mark")) {
            this.marker(Integer.parseInt(segments[1]) - 1);
        } else if(segments[0].equals("unmark")) {
            this.unMarker(Integer.parseInt(segments[1]) - 1);
        } else {
            this.add(message);
        }
    }

    public void marker(int serial) {
        Task task = this.messages[serial];
        task.markAsDone();
        String outputMessage = String.format("Nice! I've marked this task as done:\n  [%s] %s",
                task.getStatusIcon(),
                task);
        System.out.println(new FormatText(outputMessage).wrapLines());

    }

    public void unMarker(int serial) {
        Task task = this.messages[serial];
        task.markAsUnDone();
        String outputMessage = String.format("OK, I've marked this task as not done yet:\n  [%s] %s",
                task.getStatusIcon(),
                task);
        System.out.println(new FormatText(outputMessage).wrapLines());
    }

    public void add(String message) {
        Task element = new Task(message);
        this.messages[this.counter] = element;
        this.counter++;
        String outputMessage = "added: " + element.toString();
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
            finalMessage = String.format("1.[%s] %s",
                    this.messages[0].getStatusIcon(),
                    this.messages[0].toString());
        }
        for (int i = 1; i < counter; i++) {
            int increment = i + 1;
            finalMessage += String.format("\n%d.[%s] %s", increment,
                    this.messages[i].getStatusIcon(),
                    this.messages[i].toString());

        }
        return finalMessage;
    }
}
