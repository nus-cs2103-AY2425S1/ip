public class Event extends Task {

    String time_start;
    String time_end;

    public Event(boolean mark, String task,
                 String time_start, String time_end) {
        super(mark, task);
        this.time_start = time_start;
        this.time_end = time_end;
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format(
                "  %s", this));
        System.out.println(String.format(
                "Now you have %d tasks in the list.",
                super.task_list.size()));
    }

    @Override
    public String toString() {
        String s;
        s = String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.time_start, this.time_end);
        return s;
    }

}
