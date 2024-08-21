public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        tasks[0] = new Event("return book", "Monday 2pm", "Tuesday 3pm");
        System.out.println(tasks[0]);
        tasks[0].markAsDone();
        System.out.println(tasks[0]);
    }
}
